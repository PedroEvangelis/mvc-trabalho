package evangelz.service;

import evangelz.model.*;
import evangelz.repository.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MatriculaService {
    private final MatriculaRepository matriculaRepository = new MatriculaRepository();
    private final AlunoRepository alunoRepository = new AlunoRepository();
    private final TurmaRepository turmaRepository = new TurmaRepository();

    public void realizarMatricula(int idAluno, int idTurma, LocalDate data, double valor) {
        if (alunoRepository.buscarPorId(idAluno) == null)
            throw new IllegalArgumentException("Aluno não encontrado.");
        if (turmaRepository.buscarPorId(idTurma) == null)
            throw new IllegalArgumentException("Turma não encontrada.");
        if (data == null) throw new IllegalArgumentException("Data é obrigatória.");
        if (valor < 0) throw new IllegalArgumentException("Valor não pode ser negativo.");

        Connection conn = null;
        try {
            conn = Conexao.getConnection();
            conn.setAutoCommit(false);

            if (matriculaRepository.existeMatricula(idAluno, idTurma, conn))
                throw new IllegalStateException("Aluno já matriculado nesta turma.");

            Turma turma = turmaRepository.buscarPorId(idTurma);
            if (turma.getVagasDisponiveis() <= 0)
                throw new IllegalStateException("Turma sem vagas disponíveis.");

            Matricula m = new Matricula(idAluno, idTurma, data, valor);
            matriculaRepository.cadastrar(m, conn);
            turmaRepository.decrementarVagas(idTurma, conn);

            conn.commit();
        } catch (IllegalStateException e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ignored) {}
            throw e;
        } catch (SQLException e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ignored) {}
            if (e.getMessage().contains("unique") || e.getMessage().contains("duplicate"))
                throw new IllegalStateException("Aluno já matriculado nesta turma.");
            throw new RuntimeException("Erro ao realizar matrícula: " + e.getMessage(), e);
        } finally {
            try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException ignored) {}
            try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
        }
    }

    public List<Matricula> listarPorTurma(int idTurma) {
        if (turmaRepository.buscarPorId(idTurma) == null)
            throw new IllegalArgumentException("Turma não encontrada.");
        return matriculaRepository.listarPorTurma(idTurma);
    }

    public List<Matricula> listarPorAluno(int idAluno) {
        if (alunoRepository.buscarPorId(idAluno) == null)
            throw new IllegalArgumentException("Aluno não encontrado.");
        return matriculaRepository.listarPorAluno(idAluno);
    }

    public void baixarPagamento(int id) {
        Matricula m = matriculaRepository.buscarPorId(id);
        if (m == null) throw new IllegalArgumentException("Matrícula não encontrada.");
        if ("PAGO".equals(m.getStatusPagamento()))
            throw new IllegalStateException("Matrícula já está paga.");
        matriculaRepository.atualizarStatusPagamento(id, "PAGO");
    }
}
