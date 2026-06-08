package evangelz.repository;

import evangelz.model.Matricula;
import evangelz.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaRepository {

    public int cadastrar(Matricula matricula, Connection conn) throws SQLException {
        String sql = "INSERT INTO matricula (id_aluno, id_turma, data_matricula, valor, status_pagamento) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, matricula.getIdAluno());
            stmt.setInt(2, matricula.getIdTurma());
            stmt.setDate(3, Date.valueOf(matricula.getDataMatricula()));
            stmt.setDouble(4, matricula.getValor());
            stmt.setString(5, matricula.getStatusPagamento());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                matricula.setId(rs.getInt(1));
                return rs.getInt(1);
            }
            throw new SQLException("Falha ao gerar ID da matrícula.");
        }
    }

    public boolean existeMatricula(int idAluno, int idTurma, Connection conn) throws SQLException {
        String sql = "SELECT COUNT(*) FROM matricula WHERE id_aluno = ? AND id_turma = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            stmt.setInt(2, idTurma);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    public List<Matricula> listarPorTurma(int idTurma) {
        String sql = "SELECT * FROM matricula WHERE id_turma = ? ORDER BY data_matricula DESC";
        List<Matricula> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTurma);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) lista.add(buildMatricula(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar matrículas: " + e.getMessage(), e);
        }
        return lista;
    }

    public List<Matricula> listarPorAluno(int idAluno) {
        String sql = "SELECT * FROM matricula WHERE id_aluno = ? ORDER BY data_matricula DESC";
        List<Matricula> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) lista.add(buildMatricula(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar matrículas: " + e.getMessage(), e);
        }
        return lista;
    }

    public Matricula buscarPorId(int id) {
        String sql = "SELECT * FROM matricula WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return buildMatricula(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar matrícula: " + e.getMessage(), e);
        }
        return null;
    }

    public void atualizarStatusPagamento(int id, String status) {
        String sql = "UPDATE matricula SET status_pagamento = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar pagamento: " + e.getMessage(), e);
        }
    }

    private Matricula buildMatricula(ResultSet rs) throws SQLException {
        return new Matricula(rs.getInt("id"), rs.getInt("id_aluno"), rs.getInt("id_turma"),
                rs.getDate("data_matricula").toLocalDate(), rs.getDouble("valor"), rs.getString("status_pagamento"));
    }
}
