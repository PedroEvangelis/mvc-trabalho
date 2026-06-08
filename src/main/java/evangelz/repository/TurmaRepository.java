package evangelz.repository;

import evangelz.model.Turma;
import evangelz.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurmaRepository {

    public void cadastrar(Turma turma) {
        String sql = "INSERT INTO turma (id_curso, nome_turma, sala, turno, vagas_totais, vagas_disponiveis) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, turma.getIdCurso());
            stmt.setString(2, turma.getNomeTurma());
            stmt.setString(3, turma.getSala());
            stmt.setString(4, turma.getTurno());
            stmt.setInt(5, turma.getVagasTotais());
            stmt.setInt(6, turma.getVagasDisponiveis());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) turma.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar turma: " + e.getMessage(), e);
        }
    }

    public List<Turma> listarPorCurso(int idCurso) {
        String sql = "SELECT * FROM turma WHERE id_curso = ? ORDER BY nome_turma";
        List<Turma> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCurso);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                lista.add(buildTurma(rs));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar turmas: " + e.getMessage(), e);
        }
        return lista;
    }

    public Turma buscarPorId(int id) {
        String sql = "SELECT * FROM turma WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return buildTurma(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar turma: " + e.getMessage(), e);
        }
        return null;
    }

    public void atualizar(Turma turma) {
        String sql = "UPDATE turma SET nome_turma = ?, sala = ?, turno = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, turma.getNomeTurma());
            stmt.setString(2, turma.getSala());
            stmt.setString(3, turma.getTurno());
            stmt.setInt(4, turma.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar turma: " + e.getMessage(), e);
        }
    }

    public boolean temMatriculas(int idTurma) {
        String sql = "SELECT COUNT(*) FROM matricula WHERE id_turma = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTurma);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar matrículas: " + e.getMessage(), e);
        }
    }

    public void decrementarVagas(int idTurma, Connection conn) throws SQLException {
        String sql = "UPDATE turma SET vagas_disponiveis = vagas_disponiveis - 1 WHERE id = ? AND vagas_disponiveis > 0";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTurma);
            int rows = stmt.executeUpdate();
            if (rows == 0) throw new SQLException("Turma sem vagas disponíveis.");
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM turma WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir turma: " + e.getMessage(), e);
        }
    }

    private Turma buildTurma(ResultSet rs) throws SQLException {
        return new Turma(rs.getInt("id"), rs.getInt("id_curso"), rs.getString("nome_turma"),
                rs.getString("sala"), rs.getString("turno"), rs.getInt("vagas_totais"), rs.getInt("vagas_disponiveis"));
    }
}
