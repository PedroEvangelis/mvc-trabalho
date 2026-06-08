package evangelz.service;

import evangelz.model.Animal;
import evangelz.repository.AnimalRepository;
import evangelz.repository.TutorRepository;

import java.util.List;

public class AnimalService {
    private final AnimalRepository animalRepository = new AnimalRepository();
    private final TutorRepository tutorRepository = new TutorRepository();

    public void cadastrar(String nome, String especie, String raca, int idTutor) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do animal é obrigatório.");
        }
        if (especie == null || especie.trim().isEmpty()) {
            throw new IllegalArgumentException("Espécie do animal é obrigatória.");
        }
        if (tutorRepository.buscarPorId(idTutor) == null) {
            throw new IllegalArgumentException("Tutor não encontrado.");
        }
        animalRepository.cadastrar(new Animal(nome.trim(), especie.trim(), raca != null ? raca.trim() : null, idTutor));
    }

    public List<Animal> listarPorTutor(int idTutor) {
        if (tutorRepository.buscarPorId(idTutor) == null) {
            throw new IllegalArgumentException("Tutor não encontrado.");
        }
        return animalRepository.listarPorTutor(idTutor);
    }

    public Animal buscarPorId(int id) {
        Animal a = animalRepository.buscarPorId(id);
        if (a == null) throw new IllegalArgumentException("Animal não encontrado.");
        return a;
    }

    public void atualizar(int id, String nome, String especie, String raca) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do animal é obrigatório.");
        }
        if (especie == null || especie.trim().isEmpty()) {
            throw new IllegalArgumentException("Espécie do animal é obrigatória.");
        }
        Animal a = buscarPorId(id);
        a.setNome(nome.trim());
        a.setEspecie(especie.trim());
        a.setRaca(raca != null ? raca.trim() : null);
        animalRepository.atualizar(a);
    }

    public void deletar(int id) {
        Animal a = animalRepository.buscarPorId(id);
        if (a == null) throw new IllegalArgumentException("Animal não encontrado.");
        if (animalRepository.temConsultas(id)) {
            throw new IllegalStateException("Animal possui consultas registradas e não pode ser excluído.");
        }
        animalRepository.deletar(id);
    }
}
