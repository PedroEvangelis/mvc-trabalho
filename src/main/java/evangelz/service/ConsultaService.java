package evangelz.service;

import evangelz.model.Consulta;
import evangelz.repository.*;

import java.time.LocalDate;
import java.util.List;

public class ConsultaService {
    private final ConsultaRepository consultaRepository = new ConsultaRepository();
    private final AnimalRepository animalRepository = new AnimalRepository();
    private final VeterinarioRepository veterinarioRepository = new VeterinarioRepository();

    public void registrar(int idAnimal, int idVeterinario, LocalDate data, String motivo, double valor) {
        if (animalRepository.buscarPorId(idAnimal) == null) {
            throw new IllegalArgumentException("Animal não encontrado.");
        }
        if (veterinarioRepository.buscarPorId(idVeterinario) == null) {
            throw new IllegalArgumentException("Veterinário não encontrado.");
        }
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException("Motivo da consulta é obrigatório.");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data da consulta é obrigatória.");
        }
        if (valor < 0) {
            throw new IllegalArgumentException("Valor da consulta não pode ser negativo.");
        }
        Consulta c = new Consulta(idAnimal, idVeterinario, data, motivo.trim(), valor);
        consultaRepository.cadastrar(c);
    }

    public List<Consulta> listarPorAnimal(int idAnimal) {
        if (animalRepository.buscarPorId(idAnimal) == null) {
            throw new IllegalArgumentException("Animal não encontrado.");
        }
        return consultaRepository.listarPorAnimal(idAnimal);
    }

    public Consulta buscarPorId(int id) {
        Consulta c = consultaRepository.buscarPorId(id);
        if (c == null) throw new IllegalArgumentException("Consulta não encontrada.");
        return c;
    }

    public void baixarPagamento(int id) {
        Consulta c = buscarPorId(id);
        if ("PAGO".equalsIgnoreCase(c.getStatusPagamento())) {
            throw new IllegalStateException("Consulta já está paga.");
        }
        consultaRepository.atualizarStatusPagamento(id, "PAGO");
    }
}
