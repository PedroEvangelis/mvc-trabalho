package evangelz.controller;

import evangelz.model.Tutor;
import evangelz.service.TutorService;

import java.util.List;
import java.util.Scanner;

public class TutorController {
    private final TutorService tutorService = new TutorService();

    public void menu(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- TUTORES ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

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
        String telefone = scanner.nextLine();
        try {
            tutorService.cadastrar(nome, telefone);
            System.out.println("Tutor cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listar() {
        List<Tutor> tutores = tutorService.listar();
        if (tutores.isEmpty()) {
            System.out.println("Nenhum tutor cadastrado.");
            return;
        }
        System.out.println("\nTutores cadastrados:");
        tutores.forEach(System.out::println);
    }

    private void buscar(Scanner scanner) {
        System.out.print("ID do tutor: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Tutor t = tutorService.buscarPorId(id);
            System.out.println(t);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void atualizar(Scanner scanner) {
        System.out.print("ID do tutor: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Tutor t = tutorService.buscarPorId(id);
            System.out.println("Dados atuais: " + t);
            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();
            System.out.print("Novo telefone: ");
            String telefone = scanner.nextLine();
            tutorService.atualizar(id, nome, telefone);
            System.out.println("Tutor atualizado!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void excluir(Scanner scanner) {
        System.out.print("ID do tutor: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            tutorService.deletar(id);
            System.out.println("Tutor excluído (cascata aplicada).");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
