package evangelz.repository;

import evangelz.model.Animal;
import evangelz.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {

    public void cadastrar(Animal animal) {
        String sql = "INSERT INTO animal (nome, especie, raca, id_tutor) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setString(3, animal.getRaca());
            stmt.setInt(4, animal.getIdTutor());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) animal.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar animal: " + e.getMessage(), e);
        }
    }

    public List<Animal> listarPorTutor(int idTutor) {
        String sql = "SELECT * FROM animal WHERE id_tutor = ? ORDER BY nome";
        List<Animal> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTutor);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Animal(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("especie"), rs.getString("raca"),
                        rs.getInt("id_tutor")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar animais: " + e.getMessage(), e);
        }
        return lista;
    }

    public Animal buscarPorId(int id) {
        String sql = "SELECT * FROM animal WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Animal(rs.getInt("id"), rs.getString("nome"),
                        rs.getString("especie"), rs.getString("raca"),
                        rs.getInt("id_tutor"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar animal: " + e.getMessage(), e);
        }
        return null;
    }

    public void atualizar(Animal animal) {
        String sql = "UPDATE animal SET nome = ?, especie = ?, raca = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setString(3, animal.getRaca());
            stmt.setInt(4, animal.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar animal: " + e.getMessage(), e);
        }
    }

    public boolean temConsultas(int idAnimal) {
        String sql = "SELECT COUNT(*) FROM consulta WHERE id_animal = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAnimal);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar consultas do animal: " + e.getMessage(), e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM animal WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir animal: " + e.getMessage(), e);
        }
    }
}
