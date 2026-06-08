package evangelz.service;

import evangelz.model.Curso;
import evangelz.repository.CursoRepository;

import java.util.List;

public class CursoService {
    private final CursoRepository repository = new CursoRepository();

    public void cadastrar(String nome, String descricao, int cargaHoraria) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome do curso é obrigatório.");
        if (cargaHoraria <= 0)
            throw new IllegalArgumentException("Carga horária deve ser maior que zero.");
        repository.cadastrar(new Curso(nome.trim(), descricao != null ? descricao.trim() : null, cargaHoraria));
    }

    public List<Curso> listar() { return repository.listar(); }

    public Curso buscarPorId(int id) {
        Curso c = repository.buscarPorId(id);
        if (c == null) throw new IllegalArgumentException("Curso não encontrado.");
        return c;
    }

    public void atualizar(int id, String nome, String descricao, int cargaHoraria) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Nome do curso é obrigatório.");
        if (cargaHoraria <= 0)
            throw new IllegalArgumentException("Carga horária deve ser maior que zero.");
        Curso c = buscarPorId(id);
        c.setNome(nome.trim());
        c.setDescricao(descricao != null ? descricao.trim() : null);
        c.setCargaHoraria(cargaHoraria);
        repository.atualizar(c);
    }

    public void deletar(int id) {
        if (repository.buscarPorId(id) == null)
            throw new IllegalArgumentException("Curso não encontrado.");
        if (repository.temTurmas(id))
            throw new IllegalStateException("Curso possui turmas vinculadas e não pode ser excluído.");
        repository.deletar(id);
    }
}
