package evangelz.controller;

import evangelz.model.Aluno;
import evangelz.service.AlunoService;

import java.util.List;
import java.util.Scanner;

public class AlunoController {
    private final AlunoService alunoService = new AlunoService();

    public void menu(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- ALUNOS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Buscar");
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
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Telefone: "); String tel = scanner.nextLine();
        try {
            alunoService.cadastrar(nome, email, tel);
            System.out.println("Aluno cadastrado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void listar() {
        List<Aluno> lista = alunoService.listar();
        if (lista.isEmpty()) { System.out.println("Nenhum aluno."); return; }
        lista.forEach(System.out::println);
    }

    private void buscar(Scanner scanner) {
        System.out.print("ID: ");
        try { System.out.println(alunoService.buscarPorId(Integer.parseInt(scanner.nextLine()))); }
        catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void atualizar(Scanner scanner) {
        System.out.print("ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Aluno a = alunoService.buscarPorId(id);
            System.out.println("Atual: " + a);
            System.out.print("Nome: "); String nome = scanner.nextLine();
            System.out.print("Email: "); String email = scanner.nextLine();
            System.out.print("Telefone: "); String tel = scanner.nextLine();
            alunoService.atualizar(id, nome, email, tel);
            System.out.println("Atualizado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void excluir(Scanner scanner) {
        System.out.print("ID: ");
        try {
            alunoService.deletar(Integer.parseInt(scanner.nextLine()));
            System.out.println("Aluno excluído.");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
}
