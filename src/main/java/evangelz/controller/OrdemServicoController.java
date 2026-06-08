package evangelz.controller;

import evangelz.model.*;
import evangelz.repository.ServicoRepository;
import evangelz.repository.VeiculoRepository;
import evangelz.service.OrdemServicoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrdemServicoController {
    private final OrdemServicoService osService = new OrdemServicoService();
    private final VeiculoRepository veiculoRepository = new VeiculoRepository();
    private final ServicoRepository servicoRepository = new ServicoRepository();

    public void menu(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- ORDENS DE SERVIÇO ---");
            System.out.println("1 - Abrir OS");
            System.out.println("2 - Listar por veículo");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Fechar OS");
            System.out.println("5 - Baixar pagamento");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            try { opcao = Integer.parseInt(scanner.nextLine()); } catch (NumberFormatException e) { opcao = -1; }

            switch (opcao) {
                case 1 -> abrir(scanner);
                case 2 -> listarPorVeiculo(scanner);
                case 3 -> buscar(scanner);
                case 4 -> fechar(scanner);
                case 5 -> baixarPagamento(scanner);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void abrir(Scanner scanner) {
        System.out.print("ID do veículo: ");
        try {
            int idVeiculo = Integer.parseInt(scanner.nextLine());
            Veiculo v = veiculoRepository.buscarPorId(idVeiculo);
            if (v == null) { System.out.println("Veículo não encontrado."); return; }
            System.out.println("Veículo: " + v);

            System.out.print("Descrição do problema: ");
            String descricao = scanner.nextLine();

            List<ItemOrdemServico> itens = new ArrayList<>();
            System.out.println("Adicione os serviços (deixe ID vazio para finalizar):");
            while (true) {
                System.out.print("  ID do serviço (Enter para sair): ");
                String idServicoStr = scanner.nextLine();
                if (idServicoStr.trim().isEmpty()) break;
                try {
                    int idServico = Integer.parseInt(idServicoStr);
                    Servico s = servicoRepository.buscarPorId(idServico);
                    if (s == null) { System.out.println("  Serviço não encontrado."); continue; }
                    System.out.println("  Serviço: " + s.getNome() + " | Valor base: R$ " + String.format("%.2f", s.getValorBase()));
                    System.out.print("  Quantidade: ");
                    int qtd = Integer.parseInt(scanner.nextLine());
                    System.out.print("  Valor unitário (R$): ");
                    double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
                    itens.add(new ItemOrdemServico(0, idServico, qtd, valor));
                    System.out.println("  Item adicionado!");
                } catch (NumberFormatException e) {
                    System.out.println("  Valor inválido.");
                }
            }

            if (itens.isEmpty()) {
                System.out.println("É necessário adicionar ao menos um serviço.");
                return;
            }

            osService.abrir(idVeiculo, descricao, itens);
            System.out.println("OS aberta com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarPorVeiculo(Scanner scanner) {
        System.out.print("ID do veículo: ");
        try {
            int idVeiculo = Integer.parseInt(scanner.nextLine());
            List<OrdemServico> lista = osService.listarPorVeiculo(idVeiculo);
            if (lista.isEmpty()) { System.out.println("Nenhuma OS para este veículo."); return; }
            for (OrdemServico os : lista) {
                double total = osService.calcularValorTotal(os.getId());
                System.out.println(os + " | Total: R$ " + String.format("%.2f", total));
            }
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void buscar(Scanner scanner) {
        System.out.print("ID da OS: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            OrdemServico os = osService.buscarPorId(id);
            System.out.println(os);
            System.out.println("Itens:");
            List<ItemOrdemServico> itens = osService.listarItens(id);
            for (ItemOrdemServico item : itens) {
                Servico s = servicoRepository.buscarPorId(item.getIdServico());
                System.out.println("  - " + (s != null ? s.getNome() : "Serviço #" + item.getIdServico())
                        + " | Qtd: " + item.getQuantidade()
                        + " | R$ " + String.format("%.2f", item.getValorUnitario())
                        + " | Sub: R$ " + String.format("%.2f", item.getSubtotal()));
            }
            System.out.println("Valor total: R$ " + String.format("%.2f", osService.calcularValorTotal(id)));
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void fechar(Scanner scanner) {
        System.out.print("ID da OS: ");
        try {
            osService.fechar(Integer.parseInt(scanner.nextLine()));
            System.out.println("OS concluída!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void baixarPagamento(Scanner scanner) {
        System.out.print("ID da OS: ");
        try {
            osService.baixarPagamento(Integer.parseInt(scanner.nextLine()));
            System.out.println("Pagamento baixado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
}
