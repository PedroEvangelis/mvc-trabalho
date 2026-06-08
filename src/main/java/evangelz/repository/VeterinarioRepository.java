package evangelz.repository;

import evangelz.model.Veterinario;
import evangelz.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioRepository {

    public void cadastrar(Veterinario veterinario) {
        String sql = "INSERT INTO veterinario (nome) VALUES (?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, veterinario.getNome());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) veterinario.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar veterinário: " + e.getMessage(), e);
        }
    }

    public List<Veterinario> listar() {
        String sql = "SELECT * FROM veterinario ORDER BY nome";
        List<Veterinario> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Veterinario(rs.getInt("id"), rs.getString("nome")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar veterinários: " + e.getMessage(), e);
        }
        return lista;
    }

    public Veterinario buscarPorId(int id) {
        String sql = "SELECT * FROM veterinario WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Veterinario(rs.getInt("id"), rs.getString("nome"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar veterinário: " + e.getMessage(), e);
        }
        return null;
    }
}
