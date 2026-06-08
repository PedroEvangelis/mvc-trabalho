package evangelz.service;

import evangelz.model.Aluno;
import evangelz.repository.AlunoRepository;

import java.util.List;

public class AlunoService {
    private final AlunoRepository repository = new AlunoRepository();

    public void cadastrar(String nome, String email, String telefone) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome do aluno é obrigatório.");
        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("Email do aluno é obrigatório.");
        repository.cadastrar(new Aluno(nome.trim(), email.trim(), telefone != null ? telefone.trim() : null));
    }

    public List<Aluno> listar() { return repository.listar(); }

    public Aluno buscarPorId(int id) {
        Aluno a = repository.buscarPorId(id);
        if (a == null) throw new IllegalArgumentException("Aluno não encontrado.");
        return a;
    }

    public void atualizar(int id, String nome, String email, String telefone) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome do aluno é obrigatório.");
        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("Email do aluno é obrigatório.");
        Aluno a = buscarPorId(id);
        a.setNome(nome.trim());
        a.setEmail(email.trim());
        a.setTelefone(telefone != null ? telefone.trim() : null);
        repository.atualizar(a);
    }

    public void deletar(int id) {
        if (repository.buscarPorId(id) == null)
            throw new IllegalArgumentException("Aluno não encontrado.");
        if (repository.temMatriculas(id))
            throw new IllegalStateException("Aluno possui matrículas e não pode ser excluído.");
        repository.deletar(id);
    }
}
