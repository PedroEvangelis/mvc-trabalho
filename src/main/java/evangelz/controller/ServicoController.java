package evangelz.controller;

import evangelz.model.Servico;
import evangelz.service.ServicoService;

import java.util.List;
import java.util.Scanner;

public class ServicoController {
    private final ServicoService servicoService = new ServicoService();

    public void menu(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- SERVIÇOS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            try { opcao = Integer.parseInt(scanner.nextLine()); } catch (NumberFormatException e) { opcao = -1; }

            switch (opcao) {
                case 1 -> cadastrar(scanner);
                case 2 -> listar();
                case 3 -> buscar(scanner);
                case 4 -> atualizar(scanner);
                case 5 -> excluir(scanner);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("Descrição: "); String desc = scanner.nextLine();
        System.out.print("Valor base: R$ ");
        try {
            double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
            servicoService.cadastrar(nome, desc, valor);
            System.out.println("Serviço cadastrado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void listar() {
        List<Servico> lista = servicoService.listar();
        if (lista.isEmpty()) { System.out.println("Nenhum serviço."); return; }
        lista.forEach(System.out::println);
    }

    private void buscar(Scanner scanner) {
        System.out.print("ID: ");
        try { System.out.println(servicoService.buscarPorId(Integer.parseInt(scanner.nextLine()))); }
        catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void atualizar(Scanner scanner) {
        System.out.print("ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Servico s = servicoService.buscarPorId(id);
            System.out.println("Atual: " + s);
            System.out.print("Nome: "); String nome = scanner.nextLine();
            System.out.print("Descrição: "); String desc = scanner.nextLine();
            System.out.print("Valor base: R$ ");
            double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
            servicoService.atualizar(id, nome, desc, valor);
            System.out.println("Atualizado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void excluir(Scanner scanner) {
        System.out.print("ID: ");
        try {
            servicoService.deletar(Integer.parseInt(scanner.nextLine()));
            System.out.println("Serviço excluído.");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
}
