package evangelz.service;

import evangelz.model.Turma;
import evangelz.repository.CursoRepository;
import evangelz.repository.TurmaRepository;

import java.util.List;

public class TurmaService {
    private final TurmaRepository turmaRepository = new TurmaRepository();
    private final CursoRepository cursoRepository = new CursoRepository();

    public void cadastrar(int idCurso, String nomeTurma, String sala, String turno, int vagasTotais) {
        if (nomeTurma == null || nomeTurma.trim().isEmpty())
            throw new IllegalArgumentException("Nome da turma é obrigatório.");
        if (vagasTotais <= 0)
            throw new IllegalArgumentException("Total de vagas deve ser maior que zero.");
        if (cursoRepository.buscarPorId(idCurso) == null)
            throw new IllegalArgumentException("Curso não encontrado.");
        turmaRepository.cadastrar(new Turma(idCurso, nomeTurma.trim(), sala != null ? sala.trim() : null,
                turno != null ? turno.trim() : null, vagasTotais));
    }

    public List<Turma> listarPorCurso(int idCurso) {
        if (cursoRepository.buscarPorId(idCurso) == null)
            throw new IllegalArgumentException("Curso não encontrado.");
        return turmaRepository.listarPorCurso(idCurso);
    }

    public Turma buscarPorId(int id) {
        Turma t = turmaRepository.buscarPorId(id);
        if (t == null) throw new IllegalArgumentException("Turma não encontrada.");
        return t;
    }

    public void atualizar(int id, String nomeTurma, String sala, String turno) {
        if (nomeTurma == null || nomeTurma.trim().isEmpty())
            throw new IllegalArgumentException("Nome da turma é obrigatório.");
        Turma t = buscarPorId(id);
        t.setNomeTurma(nomeTurma.trim());
        t.setSala(sala != null ? sala.trim() : null);
        t.setTurno(turno != null ? turno.trim() : null);
        turmaRepository.atualizar(t);
    }

    public void deletar(int id) {
        if (turmaRepository.buscarPorId(id) == null)
            throw new IllegalArgumentException("Turma não encontrada.");
        if (turmaRepository.temMatriculas(id))
            throw new IllegalStateException("Turma possui matrículas e não pode ser excluída.");
        turmaRepository.deletar(id);
    }
}
