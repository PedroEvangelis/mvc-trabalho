package evangelz.controller;

import evangelz.model.Curso;
import evangelz.service.CursoService;

import java.util.List;
import java.util.Scanner;

public class CursoController {
    private final CursoService cursoService = new CursoService();

    public void menu(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- CURSOS ---");
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
        System.out.print("Descrição: "); String desc = scanner.nextLine();
        System.out.print("Carga horária: ");
        try {
            int ch = Integer.parseInt(scanner.nextLine());
            cursoService.cadastrar(nome, desc, ch);
            System.out.println("Curso cadastrado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void listar() {
        List<Curso> lista = cursoService.listar();
        if (lista.isEmpty()) { System.out.println("Nenhum curso."); return; }
        lista.forEach(System.out::println);
    }

    private void buscar(Scanner scanner) {
        System.out.print("ID: ");
        try { System.out.println(cursoService.buscarPorId(Integer.parseInt(scanner.nextLine()))); }
        catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void atualizar(Scanner scanner) {
        System.out.print("ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Curso c = cursoService.buscarPorId(id);
            System.out.println("Atual: " + c);
            System.out.print("Nome: "); String nome = scanner.nextLine();
            System.out.print("Descrição: "); String desc = scanner.nextLine();
            System.out.print("Carga horária: "); int ch = Integer.parseInt(scanner.nextLine());
            cursoService.atualizar(id, nome, desc, ch);
            System.out.println("Atualizado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void excluir(Scanner scanner) {
        System.out.print("ID: ");
        try {
            cursoService.deletar(Integer.parseInt(scanner.nextLine()));
            System.out.println("Curso excluído.");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
}
