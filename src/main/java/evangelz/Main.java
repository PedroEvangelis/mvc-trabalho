package evangelz;

import evangelz.controller.*;
import evangelz.util.Conexao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        criarTabelas();

        Scanner scanner = new Scanner(System.in);
        ClienteController clienteController = new ClienteController();
        VeiculoController veiculoController = new VeiculoController();
        ServicoController servicoController = new ServicoController();
        OrdemServicoController ordemServicoController = new OrdemServicoController();

        int opcao;
        do {
            System.out.println("\n========== OFICINA MECÂNICA ==========");
            System.out.println("1 - Clientes");
            System.out.println("2 - Veículos");
            System.out.println("3 - Serviços");
            System.out.println("4 - Ordens de Serviço");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> clienteController.menu(scanner);
                case 2 -> veiculoController.menu(scanner);
                case 3 -> servicoController.menu(scanner);
                case 4 -> ordemServicoController.menu(scanner);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void criarTabelas() {

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement()) {
            System.out.println("Banco de dados inicializado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
            System.exit(1);
        }
    }
}
