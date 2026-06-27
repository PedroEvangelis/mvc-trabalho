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
        AlunoController alunoController = new AlunoController();
        CursoController cursoController = new CursoController();
        TurmaController turmaController = new TurmaController();
        MatriculaController matriculaController = new MatriculaController();

        int opcao;
        do {
            System.out.println("\n========== ESCOLA DE CURSOS LIVRES ==========");
            System.out.println("1 - Alunos");
            System.out.println("2 - Cursos");
            System.out.println("3 - Turmas");
            System.out.println("4 - Matrículas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> alunoController.menu(scanner);
                case 2 -> cursoController.menu(scanner);
                case 3 -> turmaController.menu(scanner);
                case 4 -> matriculaController.menu(scanner);
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
