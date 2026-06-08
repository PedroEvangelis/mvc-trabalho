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
        ClienteController clienteController = new ClienteController();
        VeiculoController veiculoController = new VeiculoController();
        ServicoController servicoController = new ServicoController();
        OrdemServicoController ordemServicoController = new OrdemServicoController();

        int opcao;
        do {
            System.out.println("\n========== OFICINA MECÂNICA ==========");
            System.out.println("1 - Clientes");
            System.out.println("2 - Veículos");
            System.out.println("3 - Serviços");
            System.out.println("4 - Ordens de Serviço");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> clienteController.menu(scanner);
                case 2 -> veiculoController.menu(scanner);
                case 3 -> servicoController.menu(scanner);
                case 4 -> ordemServicoController.menu(scanner);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void criarTabelas() {
        String[] sqls = {
            "CREATE TABLE IF NOT EXISTS cliente (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL, telefone VARCHAR(20) NOT NULL)",
            "CREATE TABLE IF NOT EXISTS veiculo (id SERIAL PRIMARY KEY, placa VARCHAR(10) NOT NULL, modelo VARCHAR(100) NOT NULL, ano INTEGER NOT NULL, id_cliente INTEGER NOT NULL, FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE)",
            "CREATE TABLE IF NOT EXISTS servico (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL, descricao VARCHAR(255), valor_base DECIMAL(10,2) NOT NULL CHECK (valor_base >= 0))",
            "CREATE TABLE IF NOT EXISTS ordem_servico (id SERIAL PRIMARY KEY, id_veiculo INTEGER NOT NULL, data_abertura DATE NOT NULL, descricao_problema VARCHAR(255) NOT NULL, status VARCHAR(20) NOT NULL DEFAULT 'ABERTA' CHECK (status IN ('ABERTA', 'CONCLUIDA')), status_pagamento VARCHAR(20) NOT NULL DEFAULT 'PENDENTE' CHECK (status_pagamento IN ('PENDENTE', 'PAGO')), FOREIGN KEY (id_veiculo) REFERENCES veiculo(id) ON DELETE CASCADE)",
            "CREATE TABLE IF NOT EXISTS item_ordem_servico (id SERIAL PRIMARY KEY, id_ordem_servico INTEGER NOT NULL, id_servico INTEGER NOT NULL, quantidade INTEGER NOT NULL CHECK (quantidade > 0), valor_unitario DECIMAL(10,2) NOT NULL CHECK (valor_unitario >= 0), FOREIGN KEY (id_ordem_servico) REFERENCES ordem_servico(id) ON DELETE CASCADE, FOREIGN KEY (id_servico) REFERENCES servico(id))"
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
