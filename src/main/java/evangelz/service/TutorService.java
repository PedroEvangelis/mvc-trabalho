package evangelz.service;

import evangelz.model.Tutor;
import evangelz.repository.TutorRepository;

import java.util.List;

public class TutorService {
    private final TutorRepository repository = new TutorRepository();

    public void cadastrar(String nome, String telefone, String endereco) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do tutor é obrigatório.");
        }
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone do tutor é obrigatório.");
        }
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereco do tutor é obrigatório.");
        }
        repository.cadastrar(new Tutor(nome.trim(), telefone.trim(), endereco.trim()));
    }

    public List<Tutor> listar() {
        return repository.listar();
    }

    public Tutor buscarPorId(int id) {
        Tutor t = repository.buscarPorId(id);
        if (t == null) throw new IllegalArgumentException("Tutor não encontrado.");
        return t;
    }

    public void atualizar(int id, String nome, String telefone, String endereco) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do tutor é obrigatório.");
        }
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone do tutor é obrigatório.");
        }
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço do tutor é obrigatório.");
        }
        Tutor t = buscarPorId(id);
        t.setNome(nome.trim());
        t.setTelefone(telefone.trim());
        t.setEndereco(endereco.trim());
        repository.atualizar(t);
    }

    public void deletar(int id) {
        Tutor t = repository.buscarPorId(id);
        if (t == null) throw new IllegalArgumentException("Tutor não encontrado.");
        repository.deletar(id);
    }
}
