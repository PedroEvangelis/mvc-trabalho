package evangelz.controller;

import evangelz.model.Veiculo;
import evangelz.service.VeiculoService;

import java.util.List;
import java.util.Scanner;

public class VeiculoController {
    private final VeiculoService veiculoService = new VeiculoService();

    public void menu(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- VEÍCULOS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar por cliente");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            try { opcao = Integer.parseInt(scanner.nextLine()); } catch (NumberFormatException e) { opcao = -1; }

            switch (opcao) {
                case 1 -> cadastrar(scanner);
                case 2 -> listarPorCliente(scanner);
                case 3 -> buscar(scanner);
                case 4 -> atualizar(scanner);
                case 5 -> excluir(scanner);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("Placa: "); String placa = scanner.nextLine();
        System.out.print("Modelo: "); String modelo = scanner.nextLine();
        System.out.print("Ano: "); String anoStr = scanner.nextLine();
        System.out.print("ID do cliente: "); String idClienteStr = scanner.nextLine();
        try {
            veiculoService.cadastrar(placa, modelo, Integer.parseInt(anoStr), Integer.parseInt(idClienteStr));
            System.out.println("Veículo cadastrado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void listarPorCliente(Scanner scanner) {
        System.out.print("ID do cliente: ");
        try {
            List<Veiculo> lista = veiculoService.listarPorCliente(Integer.parseInt(scanner.nextLine()));
            if (lista.isEmpty()) { System.out.println("Nenhum veículo."); return; }
            lista.forEach(System.out::println);
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void buscar(Scanner scanner) {
        System.out.print("ID: ");
        try { System.out.println(veiculoService.buscarPorId(Integer.parseInt(scanner.nextLine()))); }
        catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void atualizar(Scanner scanner) {
        System.out.print("ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Veiculo v = veiculoService.buscarPorId(id);
            System.out.println("Atual: " + v);
            System.out.print("Placa: "); String placa = scanner.nextLine();
            System.out.print("Modelo: "); String modelo = scanner.nextLine();
            System.out.print("Ano: "); int ano = Integer.parseInt(scanner.nextLine());
            veiculoService.atualizar(id, placa, modelo, ano);
            System.out.println("Atualizado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void excluir(Scanner scanner) {
        System.out.print("ID: ");
        try {
            veiculoService.deletar(Integer.parseInt(scanner.nextLine()));
            System.out.println("Veículo excluído.");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
}
