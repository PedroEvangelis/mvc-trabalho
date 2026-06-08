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
        String[] sqls = {
            "CREATE TABLE IF NOT EXISTS aluno (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL, email VARCHAR(100) NOT NULL UNIQUE, telefone VARCHAR(20))",
            "CREATE TABLE IF NOT EXISTS curso (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL, descricao VARCHAR(255), carga_horaria INTEGER NOT NULL CHECK (carga_horaria > 0))",
            "CREATE TABLE IF NOT EXISTS turma (id SERIAL PRIMARY KEY, id_curso INTEGER NOT NULL, nome_turma VARCHAR(100) NOT NULL, sala VARCHAR(50), turno VARCHAR(20), vagas_totais INTEGER NOT NULL CHECK (vagas_totais > 0), vagas_disponiveis INTEGER NOT NULL CHECK (vagas_disponiveis >= 0), FOREIGN KEY (id_curso) REFERENCES curso(id))",
            "CREATE TABLE IF NOT EXISTS matricula (id SERIAL PRIMARY KEY, id_aluno INTEGER NOT NULL, id_turma INTEGER NOT NULL, data_matricula DATE NOT NULL, valor DECIMAL(10,2) NOT NULL CHECK (valor >= 0), status_pagamento VARCHAR(20) NOT NULL DEFAULT 'PENDENTE' CHECK (status_pagamento IN ('PENDENTE', 'PAGO')), FOREIGN KEY (id_aluno) REFERENCES aluno(id), FOREIGN KEY (id_turma) REFERENCES turma(id), UNIQUE (id_aluno, id_turma))"
        };

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement()) {
            for (String sql : sqls) {
                stmt.executeUpdate(sql);
            }
            System.out.println("Banco de dados inicializado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
            System.exit(1);
        }
    }
}
