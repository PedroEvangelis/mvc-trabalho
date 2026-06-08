package evangelz.repository;

import evangelz.model.Servico;
import evangelz.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoRepository {

    public void cadastrar(Servico servico) {
        String sql = "INSERT INTO servico (nome, descricao, valor_base) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getValorBase());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) servico.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar serviço: " + e.getMessage(), e);
        }
    }

    public List<Servico> listar() {
        String sql = "SELECT * FROM servico ORDER BY nome";
        List<Servico> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Servico(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("descricao"), rs.getDouble("valor_base")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar serviços: " + e.getMessage(), e);
        }
        return lista;
    }

    public Servico buscarPorId(int id) {
        String sql = "SELECT * FROM servico WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Servico(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("descricao"), rs.getDouble("valor_base"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar serviço: " + e.getMessage(), e);
        }
        return null;
    }

    public void atualizar(Servico servico) {
        String sql = "UPDATE servico SET nome = ?, descricao = ?, valor_base = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getValorBase());
            stmt.setInt(4, servico.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar serviço: " + e.getMessage(), e);
        }
    }

    public boolean temVinculoComOS(int idServico) {
        String sql = "SELECT COUNT(*) FROM item_ordem_servico WHERE id_servico = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idServico);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar vínculo do serviço: " + e.getMessage(), e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM servico WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir serviço: " + e.getMessage(), e);
        }
    }
}
