package evangelz.repository;

import evangelz.model.OrdemServico;
import evangelz.util.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoRepository {

    public int cadastrar(OrdemServico os, Connection conn) throws SQLException {
        String sql = "INSERT INTO ordem_servico (id_veiculo, data_abertura, descricao_problema, status, status_pagamento) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, os.getIdVeiculo());
            stmt.setDate(2, Date.valueOf(os.getDataAbertura()));
            stmt.setString(3, os.getDescricaoProblema());
            stmt.setString(4, os.getStatus());
            stmt.setString(5, os.getStatusPagamento());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                os.setId(id);
                return id;
            }
            throw new SQLException("Falha ao gerar ID da OS.");
        }
    }

    public OrdemServico buscarPorId(int id) {
        String sql = "SELECT * FROM ordem_servico WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return buildOS(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar OS: " + e.getMessage(), e);
        }
        return null;
    }

    public List<OrdemServico> listarPorVeiculo(int idVeiculo) {
        String sql = "SELECT * FROM ordem_servico WHERE id_veiculo = ? ORDER BY data_abertura DESC";
        List<OrdemServico> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVeiculo);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) lista.add(buildOS(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar OS: " + e.getMessage(), e);
        }
        return lista;
    }

    public void atualizarStatus(int id, String status) {
        String sql = "UPDATE ordem_servico SET status = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status da OS: " + e.getMessage(), e);
        }
    }

    public void atualizarStatusPagamento(int id, String status) {
        String sql = "UPDATE ordem_servico SET status_pagamento = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar pagamento da OS: " + e.getMessage(), e);
        }
    }

    private OrdemServico buildOS(ResultSet rs) throws SQLException {
        return new OrdemServico(
                rs.getInt("id"),
                rs.getInt("id_veiculo"),
                rs.getDate("data_abertura").toLocalDate(),
                rs.getString("descricao_problema"),
                rs.getString("status"),
                rs.getString("status_pagamento")
        );
    }
}
