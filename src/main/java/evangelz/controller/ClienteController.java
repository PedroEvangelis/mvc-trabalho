package evangelz.controller;

import evangelz.model.Cliente;
import evangelz.service.ClienteService;

import java.util.List;
import java.util.Scanner;

public class ClienteController {
    private final ClienteService clienteService = new ClienteService();

    public void menu(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- CLIENTES ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) { opcao = -1; }

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
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String tel = scanner.nextLine();
        try {
            clienteService.cadastrar(nome, tel);
            System.out.println("Cliente cadastrado!");
        } catch (IllegalArgumentException e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void listar() {
        List<Cliente> lista = clienteService.listar();
        if (lista.isEmpty()) { System.out.println("Nenhum cliente."); return; }
        lista.forEach(System.out::println);
    }

    private void buscar(Scanner scanner) {
        System.out.print("ID: ");
        try { System.out.println(clienteService.buscarPorId(Integer.parseInt(scanner.nextLine()))); }
        catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void atualizar(Scanner scanner) {
        System.out.print("ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Cliente c = clienteService.buscarPorId(id);
            System.out.println("Atual: " + c);
            System.out.print("Nome: "); String nome = scanner.nextLine();
            System.out.print("Telefone: "); String tel = scanner.nextLine();
            clienteService.atualizar(id, nome, tel);
            System.out.println("Atualizado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void excluir(Scanner scanner) {
        System.out.print("ID: ");
        try {
            clienteService.deletar(Integer.parseInt(scanner.nextLine()));
            System.out.println("Cliente excluído.");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
}
