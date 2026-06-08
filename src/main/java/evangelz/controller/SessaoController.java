package evangelz.controller;

import evangelz.model.Veterinario;
import evangelz.service.SessaoService;

import java.util.List;
import java.util.Scanner;

public class SessaoController {
    private final SessaoService sessaoService = new SessaoService();

    public void iniciarSessao(Scanner scanner) {
        System.out.println("\n=== BEM-VINDO AO SISTEMA CLÍNICA VETERINÁRIA ===");

        while (true) {
            System.out.println("\n1 - Já sou cadastrado (selecionar)");
            System.out.println("2 - Novo veterinário");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int opcao;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {
                case 1 -> {
                    List<Veterinario> lista = sessaoService.listarVeterinarios();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum veterinário cadastrado. Cadastre um novo.");
                        break;
                    }
                    System.out.println("\nVeterinários cadastrados:");
                    lista.forEach(v -> System.out.println(v.getId() + " - " + v.getNome()));
                    System.out.print("Digite o ID: ");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        Veterinario v = sessaoService.selecionarVeterinario(id);
                        System.out.println("Bem-vindo(a), Dr(a). " + v.getNome() + "!");
                        return;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("Digite o nome do veterinário: ");
                    String nome = scanner.nextLine().trim();
                    try {
                        Veterinario v = sessaoService.cadastrarVeterinario(nome);
                        System.out.println("Veterinário cadastrado com ID " + v.getId());
                        System.out.println("Bem-vindo(a), Dr(a). " + v.getNome() + "!");
                        return;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 0 -> {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
