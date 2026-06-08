package evangelz.repository;

import evangelz.model.Aluno;
import evangelz.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {

    public void cadastrar(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, email, telefone) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getTelefone());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) aluno.setId(rs.getInt(1));
        } catch (SQLException e) {
            if (e.getMessage().contains("unique") || e.getMessage().contains("duplicate"))
                throw new RuntimeException("Email já cadastrado.", e);
            throw new RuntimeException("Erro ao cadastrar aluno: " + e.getMessage(), e);
        }
    }

    public List<Aluno> listar() {
        String sql = "SELECT * FROM aluno ORDER BY nome";
        List<Aluno> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next())
                lista.add(new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone")));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos: " + e.getMessage(), e);
        }
        return lista;
    }

    public Aluno buscarPorId(int id) {
        String sql = "SELECT * FROM aluno WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return new Aluno(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno: " + e.getMessage(), e);
        }
        return null;
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, email = ?, telefone = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getTelefone());
            stmt.setInt(4, aluno.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar aluno: " + e.getMessage(), e);
        }
    }

    public boolean temMatriculas(int idAluno) {
        String sql = "SELECT COUNT(*) FROM matricula WHERE id_aluno = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar matrículas: " + e.getMessage(), e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM aluno WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir aluno: " + e.getMessage(), e);
        }
    }
}
