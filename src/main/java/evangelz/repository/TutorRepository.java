package evangelz.repository;

import evangelz.model.Tutor;
import evangelz.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TutorRepository {

    public void cadastrar(Tutor tutor) {
        String sql = "INSERT INTO tutor (nome, telefone, endereco) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tutor.getNome());
            stmt.setString(2, tutor.getTelefone());
            stmt.setString(3, tutor.getEndereco());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) tutor.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar tutor: " + e.getMessage(), e);
        }
    }

    public List<Tutor> listar() {
        String sql = "SELECT * FROM tutor ORDER BY nome";
        List<Tutor> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Tutor(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("endereco")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar tutores: " + e.getMessage(), e);
        }
        return lista;
    }

    public Tutor buscarPorId(int id) {
        String sql = "SELECT * FROM tutor WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Tutor(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("endereco"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar tutor: " + e.getMessage(), e);
        }
        return null;
    }

    public void atualizar(Tutor tutor) {
        String sql = "UPDATE tutor SET nome = ?, telefone = ? WHERE id = ?, endereco = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tutor.getNome());
            stmt.setString(2, tutor.getTelefone());
            stmt.setString(3, tutor.getEndereco());
            stmt.setInt(4, tutor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar tutor: " + e.getMessage(), e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM tutor WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir tutor: " + e.getMessage(), e);
        }
    }
}
