package evangelz.controller;

import evangelz.model.*;
import evangelz.repository.AlunoRepository;
import evangelz.repository.CursoRepository;
import evangelz.repository.TurmaRepository;
import evangelz.service.MatriculaService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class MatriculaController {
    private final MatriculaService matriculaService = new MatriculaService();
    private final AlunoRepository alunoRepository = new AlunoRepository();
    private final TurmaRepository turmaRepository = new TurmaRepository();
    private final CursoRepository cursoRepository = new CursoRepository();

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void menu(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- MATRÍCULAS ---");
            System.out.println("1 - Realizar matrícula");
            System.out.println("2 - Listar por turma");
            System.out.println("3 - Listar por aluno");
            System.out.println("4 - Baixar pagamento");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            try { opcao = Integer.parseInt(scanner.nextLine()); } catch (NumberFormatException e) { opcao = -1; }

            switch (opcao) {
                case 1 -> realizar(scanner);
                case 2 -> listarPorTurma(scanner);
                case 3 -> listarPorAluno(scanner);
                case 4 -> baixarPagamento(scanner);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void realizar(Scanner scanner) {
        System.out.print("ID do aluno: ");
        try {
            int idAluno = Integer.parseInt(scanner.nextLine());
            Aluno aluno = alunoRepository.buscarPorId(idAluno);
            if (aluno == null) { System.out.println("Aluno não encontrado."); return; }
            System.out.println("Aluno: " + aluno.getNome());

            System.out.print("ID da turma: ");
            int idTurma = Integer.parseInt(scanner.nextLine());
            Turma turma = turmaRepository.buscarPorId(idTurma);
            if (turma == null) { System.out.println("Turma não encontrada."); return; }
            Curso curso = cursoRepository.buscarPorId(turma.getIdCurso());
            System.out.println("Turma: " + turma.getNomeTurma()
                    + (curso != null ? " (" + curso.getNome() + ")" : "")
                    + " | Vagas: " + turma.getVagasDisponiveis() + "/" + turma.getVagasTotais());

            System.out.print("Data (dd/MM/yyyy) ou Enter para hoje: ");
            String dataStr = scanner.nextLine().trim();
            LocalDate data = dataStr.isEmpty() ? LocalDate.now() : LocalDate.parse(dataStr, FMT);

            System.out.print("Valor: R$ ");
            double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));

            matriculaService.realizarMatricula(idAluno, idTurma, data, valor);
            System.out.println("Matrícula realizada com sucesso!");
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido.");
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarPorTurma(Scanner scanner) {
        System.out.print("ID da turma: ");
        try {
            int idTurma = Integer.parseInt(scanner.nextLine());
            List<Matricula> lista = matriculaService.listarPorTurma(idTurma);
            if (lista.isEmpty()) { System.out.println("Nenhuma matrícula."); return; }
            for (Matricula m : lista) {
                Aluno a = alunoRepository.buscarPorId(m.getIdAluno());
                System.out.println("  " + m + " | Aluno: " + (a != null ? a.getNome() : "?"));
            }
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void listarPorAluno(Scanner scanner) {
        System.out.print("ID do aluno: ");
        try {
            int idAluno = Integer.parseInt(scanner.nextLine());
            List<Matricula> lista = matriculaService.listarPorAluno(idAluno);
            if (lista.isEmpty()) { System.out.println("Nenhuma matrícula."); return; }
            for (Matricula m : lista) {
                Turma t = turmaRepository.buscarPorId(m.getIdTurma());
                Curso c = t != null ? cursoRepository.buscarPorId(t.getIdCurso()) : null;
                System.out.println("  " + m + " | Turma: " + (t != null ? t.getNomeTurma() : "?")
                        + (c != null ? " (" + c.getNome() + ")" : ""));
            }
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private void baixarPagamento(Scanner scanner) {
        System.out.print("ID da matrícula: ");
        try {
            matriculaService.baixarPagamento(Integer.parseInt(scanner.nextLine()));
            System.out.println("Pagamento baixado!");
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }
}
