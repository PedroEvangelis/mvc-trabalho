package evangelz.service;

import evangelz.model.*;
import evangelz.repository.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrdemServicoService {
    private final OrdemServicoRepository osRepository = new OrdemServicoRepository();
    private final ItemOrdemServicoRepository itemRepository = new ItemOrdemServicoRepository();
    private final VeiculoRepository veiculoRepository = new VeiculoRepository();
    private final ServicoRepository servicoRepository = new ServicoRepository();

    public void abrir(int idVeiculo, String descricao, List<ItemOrdemServico> itens) {
        if (veiculoRepository.buscarPorId(idVeiculo) == null)
            throw new IllegalArgumentException("Veículo não encontrado.");
        if (descricao == null || descricao.trim().isEmpty())
            throw new IllegalArgumentException("Descrição do problema é obrigatória.");
        if (itens == null || itens.isEmpty())
            throw new IllegalArgumentException("A OS deve ter pelo menos um serviço.");
        for (ItemOrdemServico item : itens) {
            if (servicoRepository.buscarPorId(item.getIdServico()) == null)
                throw new IllegalArgumentException("Serviço ID " + item.getIdServico() + " não encontrado.");
            if (item.getQuantidade() <= 0)
                throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
            if (item.getValorUnitario() < 0)
                throw new IllegalArgumentException("Valor unitário não pode ser negativo.");
        }

        Connection conn = null;
        try {
            conn = Conexao.getConnection();
            conn.setAutoCommit(false);

            OrdemServico os = new OrdemServico(idVeiculo, LocalDate.now(), descricao.trim());
            int osId = osRepository.cadastrar(os, conn);

            for (ItemOrdemServico item : itens) {
                item.setIdOrdemServico(osId);
                itemRepository.cadastrar(item, conn);
            }

            conn.commit();
        } catch (SQLException e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ignored) {}
            throw new RuntimeException("Erro ao abrir OS: " + e.getMessage(), e);
        } finally {
            try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException ignored) {}
            try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
        }
    }

    public OrdemServico buscarPorId(int id) {
        OrdemServico os = osRepository.buscarPorId(id);
        if (os == null) throw new IllegalArgumentException("OS não encontrada.");
        return os;
    }

    public List<OrdemServico> listarPorVeiculo(int idVeiculo) {
        if (veiculoRepository.buscarPorId(idVeiculo) == null)
            throw new IllegalArgumentException("Veículo não encontrado.");
        return osRepository.listarPorVeiculo(idVeiculo);
    }

    public void fechar(int id) {
        OrdemServico os = buscarPorId(id);
        if ("CONCLUIDA".equals(os.getStatus()))
            throw new IllegalStateException("OS já está concluída.");
        osRepository.atualizarStatus(id, "CONCLUIDA");
    }

    public void baixarPagamento(int id) {
        OrdemServico os = buscarPorId(id);
        if ("ABERTA".equals(os.getStatus()))
            throw new IllegalStateException("Finalize a OS antes de baixar o pagamento.");
        if ("PAGO".equals(os.getStatusPagamento()))
            throw new IllegalStateException("OS já está paga.");
        osRepository.atualizarStatusPagamento(id, "PAGO");
    }

    public List<ItemOrdemServico> listarItens(int idOS) {
        return itemRepository.listarPorOS(idOS);
    }

    public double calcularValorTotal(int idOS) {
        return itemRepository.listarPorOS(idOS).stream()
                .mapToDouble(ItemOrdemServico::getSubtotal)
                .sum();
    }
}
