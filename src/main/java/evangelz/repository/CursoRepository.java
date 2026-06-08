package evangelz.repository;

import evangelz.model.Curso;
import evangelz.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoRepository {

    public void cadastrar(Curso curso) {
        String sql = "INSERT INTO curso (nome, descricao, carga_horaria) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) curso.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar curso: " + e.getMessage(), e);
        }
    }

    public List<Curso> listar() {
        String sql = "SELECT * FROM curso ORDER BY nome";
        List<Curso> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next())
                lista.add(new Curso(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"), rs.getInt("carga_horaria")));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar cursos: " + e.getMessage(), e);
        }
        return lista;
    }

    public Curso buscarPorId(int id) {
        String sql = "SELECT * FROM curso WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return new Curso(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"), rs.getInt("carga_horaria"));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar curso: " + e.getMessage(), e);
        }
        return null;
    }

    public void atualizar(Curso curso) {
        String sql = "UPDATE curso SET nome = ?, descricao = ?, carga_horaria = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setInt(4, curso.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar curso: " + e.getMessage(), e);
        }
    }

    public boolean temTurmas(int idCurso) {
        String sql = "SELECT COUNT(*) FROM turma WHERE id_curso = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCurso);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar turmas: " + e.getMessage(), e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM curso WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir curso: " + e.getMessage(), e);
        }
    }
}
