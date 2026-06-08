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
        String[] sqls = {
            "CREATE TABLE IF NOT EXISTS veterinario (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL)",
            "CREATE TABLE IF NOT EXISTS tutor (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL, telefone VARCHAR(20) NOT NULL)",
            "CREATE TABLE IF NOT EXISTS animal (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL, especie VARCHAR(50) NOT NULL, raca VARCHAR(50), id_tutor INTEGER NOT NULL, FOREIGN KEY (id_tutor) REFERENCES tutor(id) ON DELETE CASCADE)",
            "CREATE TABLE IF NOT EXISTS consulta (id SERIAL PRIMARY KEY, id_animal INTEGER NOT NULL, id_veterinario INTEGER NOT NULL, data DATE NOT NULL, motivo VARCHAR(255) NOT NULL, valor DECIMAL(10,2) NOT NULL CHECK (valor >= 0), status_pagamento VARCHAR(20) NOT NULL DEFAULT 'PENDENTE' CHECK (status_pagamento IN ('PENDENTE', 'PAGO')), FOREIGN KEY (id_animal) REFERENCES animal(id) ON DELETE CASCADE, FOREIGN KEY (id_veterinario) REFERENCES veterinario(id))"
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
