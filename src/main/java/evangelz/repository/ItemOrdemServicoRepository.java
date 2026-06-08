package evangelz.repository;

import evangelz.model.ItemOrdemServico;
import evangelz.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemOrdemServicoRepository {

    public void cadastrar(ItemOrdemServico item, Connection conn) throws SQLException {
        String sql = "INSERT INTO item_ordem_servico (id_ordem_servico, id_servico, quantidade, valor_unitario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, item.getIdOrdemServico());
            stmt.setInt(2, item.getIdServico());
            stmt.setInt(3, item.getQuantidade());
            stmt.setDouble(4, item.getValorUnitario());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) item.setId(rs.getInt(1));
        }
    }

    public List<ItemOrdemServico> listarPorOS(int idOrdemServico) {
        String sql = "SELECT * FROM item_ordem_servico WHERE id_ordem_servico = ?";
        List<ItemOrdemServico> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idOrdemServico);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new ItemOrdemServico(rs.getInt("id"), rs.getInt("id_ordem_servico"),
                        rs.getInt("id_servico"), rs.getInt("quantidade"), rs.getDouble("valor_unitario")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar itens da OS: " + e.getMessage(), e);
        }
        return lista;
    }
}
