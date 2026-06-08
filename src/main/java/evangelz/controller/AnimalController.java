package evangelz.controller;

import evangelz.model.Animal;
import evangelz.service.AnimalService;

import java.util.List;
import java.util.Scanner;

public class AnimalController {
    private final AnimalService animalService = new AnimalService();

    public void menu(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- ANIMAIS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar por tutor");
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
                case 2 -> listarPorTutor(scanner);
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
        System.out.print("Espécie: ");
        String especie = scanner.nextLine();
        System.out.print("Raça (opcional): ");
        String raca = scanner.nextLine();
        System.out.print("ID do tutor: ");
        try {
            int idTutor = Integer.parseInt(scanner.nextLine());
            animalService.cadastrar(nome, especie, raca.isEmpty() ? null : raca, idTutor);
            System.out.println("Animal cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarPorTutor(Scanner scanner) {
        System.out.print("ID do tutor: ");
        try {
            int idTutor = Integer.parseInt(scanner.nextLine());
            List<Animal> animais = animalService.listarPorTutor(idTutor);
            if (animais.isEmpty()) {
                System.out.println("Nenhum animal para este tutor.");
                return;
            }
            animais.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void buscar(Scanner scanner) {
        System.out.print("ID do animal: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println(animalService.buscarPorId(id));
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void atualizar(Scanner scanner) {
        System.out.print("ID do animal: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Animal a = animalService.buscarPorId(id);
            System.out.println("Dados atuais: " + a);
            System.out.print("Novo nome: ");
            String nome = scanner.nextLine();
            System.out.print("Nova espécie: ");
            String especie = scanner.nextLine();
            System.out.print("Nova raça (opcional): ");
            String raca = scanner.nextLine();
            animalService.atualizar(id, nome, especie, raca.isEmpty() ? null : raca);
            System.out.println("Animal atualizado!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void excluir(Scanner scanner) {
        System.out.print("ID do animal: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            animalService.deletar(id);
            System.out.println("Animal excluído.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
