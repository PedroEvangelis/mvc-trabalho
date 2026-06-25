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
        SessaoController sessaoController = new SessaoController();
        sessaoController.iniciarSessao(scanner);

        TutorController tutorController = new TutorController();
        AnimalController animalController = new AnimalController();
        ConsultaController consultaController = new ConsultaController();

        int opcao;
        do {
            System.out.println("\n========== MENU PRINCIPAL ==========");
            System.out.println("1 - Tutores");
            System.out.println("2 - Animais");
            System.out.println("3 - Consultas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> tutorController.menu(scanner);
                case 2 -> animalController.menu(scanner);
                case 3 -> consultaController.menu(scanner);
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
            System.out.println("Erro ao conectar: " + e.getMessage());
            System.exit(1);
        }
    }
}
