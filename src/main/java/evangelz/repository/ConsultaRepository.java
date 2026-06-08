package evangelz.repository;

import evangelz.model.Consulta;
import evangelz.util.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultaRepository {

    public void cadastrar(Consulta consulta) {
        String sql = "INSERT INTO consulta (id_animal, id_veterinario, data, motivo, valor, status_pagamento) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, consulta.getIdAnimal());
            stmt.setInt(2, consulta.getIdVeterinario());
            stmt.setDate(3, Date.valueOf(consulta.getData()));
            stmt.setString(4, consulta.getMotivo());
            stmt.setDouble(5, consulta.getValor());
            stmt.setString(6, consulta.getStatusPagamento());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) consulta.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar consulta: " + e.getMessage(), e);
        }
    }

    public List<Consulta> listarPorAnimal(int idAnimal) {
        String sql = "SELECT * FROM consulta WHERE id_animal = ? ORDER BY data DESC";
        List<Consulta> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAnimal);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(buildConsulta(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas: " + e.getMessage(), e);
        }
        return lista;
    }

    public Consulta buscarPorId(int id) {
        String sql = "SELECT * FROM consulta WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return buildConsulta(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar consulta: " + e.getMessage(), e);
        }
        return null;
    }

    public void atualizarStatusPagamento(int id, String status) {
        String sql = "UPDATE consulta SET status_pagamento = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar pagamento: " + e.getMessage(), e);
        }
    }

    private Consulta buildConsulta(ResultSet rs) throws SQLException {
        return new Consulta(
                rs.getInt("id"),
                rs.getInt("id_animal"),
                rs.getInt("id_veterinario"),
                rs.getDate("data").toLocalDate(),
                rs.getString("motivo"),
                rs.getDouble("valor"),
                rs.getString("status_pagamento")
        );
    }
}
