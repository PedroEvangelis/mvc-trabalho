package evangelz.controller;

import evangelz.model.Turma;
import evangelz.service.TurmaService;

import java.util.List;
import java.util.Scanner;

public class TurmaController {
    private final TurmaService turmaService = new TurmaService();

    public void menu(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- TURMAS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar por curso");
            System.out.println("3 - Buscar");
            System.out.println("4 - Atualizar");
            System.out.println("5 - Excluir");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            try { opcao = Integer.parseInt(scanner.nextLine()); } catch (NumberFormatException e) { opcao = -1; }

            switch (opcao) {
                case 1 -> cadastrar(scanner);
                case 2 -> listarPorCurso(scanner);
                case 3 -> buscar(scanner);
                case 4 -> atualizar(scanner);
                case 5 -> excluir(scanner);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("ID do curso: ");
        try {
            int idCurso = Integer.parseInt(scanner.nextLine());
            System.out.print("Nome da turma: "); String nome = scanner.nextLine();
            System.out.print("Sala: "); String sala = scanner.nextLine();
            System.out.print("Turno: "); String turno = scanner.nextLine();
            System.out.print("Total de vagas: "); int vagas = Integer.parseInt(scanner.nextLine());
            turmaService.cadastrar(idCurso, nome, sala, turno, vagas);
            System.out.println("Turma cadastrada!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void listarPorCurso(Scanner scanner) {
        System.out.print("ID do curso: ");
        try {
            List<Turma> lista = turmaService.listarPorCurso(Integer.parseInt(scanner.nextLine()));
            if (lista.isEmpty()) { System.out.println("Nenhuma turma."); return; }
            lista.forEach(System.out::println);
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void buscar(Scanner scanner) {
        System.out.print("ID: ");
        try { System.out.println(turmaService.buscarPorId(Integer.parseInt(scanner.nextLine()))); }
        catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void atualizar(Scanner scanner) {
        System.out.print("ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Turma t = turmaService.buscarPorId(id);
            System.out.println("Atual: " + t);
            System.out.print("Nome: "); String nome = scanner.nextLine();
            System.out.print("Sala: "); String sala = scanner.nextLine();
            System.out.print("Turno: "); String turno = scanner.nextLine();
            turmaService.atualizar(id, nome, sala, turno);
            System.out.println("Atualizada!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void excluir(Scanner scanner) {
        System.out.print("ID: ");
        try {
            turmaService.deletar(Integer.parseInt(scanner.nextLine()));
            System.out.println("Turma excluída.");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
}
