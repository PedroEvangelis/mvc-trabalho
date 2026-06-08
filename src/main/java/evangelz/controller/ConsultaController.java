package evangelz.controller;

import evangelz.model.Animal;
import evangelz.model.Consulta;
import evangelz.model.Tutor;
import evangelz.model.Veterinario;
import evangelz.repository.AnimalRepository;
import evangelz.repository.TutorRepository;
import evangelz.repository.VeterinarioRepository;
import evangelz.service.ConsultaService;
import evangelz.service.SessaoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsultaController {
    private final ConsultaService consultaService = new ConsultaService();
    private final SessaoService sessaoService = new SessaoService();
    private final AnimalRepository animalRepository = new AnimalRepository();
    private final VeterinarioRepository veterinarioRepository = new VeterinarioRepository();
    private final TutorRepository tutorRepository = new TutorRepository();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void menu(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n--- CONSULTAS ---");
            System.out.println("1 - Registrar consulta");
            System.out.println("2 - Histórico por animal");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Baixar pagamento");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> registrar(scanner);
                case 2 -> historicoPorAnimal(scanner);
                case 3 -> buscar(scanner);
                case 4 -> baixarPagamento(scanner);
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void registrar(Scanner scanner) {
        Veterinario vet = sessaoService.getVeterinarioLogado();
        if (vet == null) {
            System.out.println("Erro: Nenhum veterinário logado.");
            return;
        }

        System.out.print("ID do animal: ");
        try {
            int idAnimal = Integer.parseInt(scanner.nextLine());
            Animal animal = animalRepository.buscarPorId(idAnimal);
            if (animal == null) {
                System.out.println("Animal não encontrado.");
                return;
            }
            Tutor tutor = tutorRepository.buscarPorId(animal.getIdTutor());
            System.out.println("Paciente: " + animal.getNome() + " (Tutor: " + (tutor != null ? tutor.getNome() : "?") + ")");

            System.out.print("Data (dd/MM/yyyy) ou Enter para hoje: ");
            String dataStr = scanner.nextLine().trim();
            LocalDate data = dataStr.isEmpty() ? LocalDate.now() : LocalDate.parse(dataStr, FORMATTER);

            System.out.print("Motivo: ");
            String motivo = scanner.nextLine();

            System.out.print("Valor: R$ ");
            double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));

            consultaService.registrar(idAnimal, vet.getId(), data, motivo, valor);
            System.out.println("Consulta registrada com sucesso! Status: PENDENTE");
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use dd/MM/yyyy.");
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void historicoPorAnimal(Scanner scanner) {
        System.out.print("ID do animal: ");
        try {
            int idAnimal = Integer.parseInt(scanner.nextLine());
            Animal animal = animalRepository.buscarPorId(idAnimal);
            if (animal == null) {
                System.out.println("Animal não encontrado.");
                return;
            }
            Tutor tutor = tutorRepository.buscarPorId(animal.getIdTutor());
            System.out.println("\nHistórico de " + animal.getNome()
                    + " (" + animal.getEspecie() + ")"
                    + (tutor != null ? " | Tutor: " + tutor.getNome() : ""));

            List<Consulta> consultas = consultaService.listarPorAnimal(idAnimal);
            if (consultas.isEmpty()) {
                System.out.println("Nenhuma consulta registrada.");
                return;
            }
            for (Consulta c : consultas) {
                Veterinario v = veterinarioRepository.buscarPorId(c.getIdVeterinario());
                System.out.println("  " + c + (v != null ? " | Vet: " + v.getNome() : ""));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void buscar(Scanner scanner) {
        System.out.print("ID da consulta: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            consultaService.buscarPorId(id);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void baixarPagamento(Scanner scanner) {
        System.out.print("ID da consulta: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            consultaService.baixarPagamento(id);
            System.out.println("Pagamento baixado com sucesso! Status: PAGO");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
