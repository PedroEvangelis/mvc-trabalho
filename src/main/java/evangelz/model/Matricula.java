package evangelz.model;

import java.time.LocalDate;

public class Matricula {
    private int id;
    private int idAluno;
    private int idTurma;
    private LocalDate dataMatricula;
    private double valor;
    private String statusPagamento;

    public Matricula() {}

    public Matricula(int idAluno, int idTurma, LocalDate dataMatricula, double valor) {
        this.idAluno = idAluno;
        this.idTurma = idTurma;
        this.dataMatricula = dataMatricula;
        this.valor = valor;
        this.statusPagamento = "PENDENTE";
    }

    public Matricula(int id, int idAluno, int idTurma, LocalDate dataMatricula, double valor, String statusPagamento) {
        this.id = id;
        this.idAluno = idAluno;
        this.idTurma = idTurma;
        this.dataMatricula = dataMatricula;
        this.valor = valor;
        this.statusPagamento = statusPagamento;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdAluno() { return idAluno; }
    public void setIdAluno(int idAluno) { this.idAluno = idAluno; }

    public int getIdTurma() { return idTurma; }
    public void setIdTurma(int idTurma) { this.idTurma = idTurma; }

    public LocalDate getDataMatricula() { return dataMatricula; }
    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getStatusPagamento() { return statusPagamento; }
    public void setStatusPagamento(String statusPagamento) { this.statusPagamento = statusPagamento; }

    @Override
    public String toString() {
        return "Matrícula " + id + " | R$ " + String.format("%.2f", valor) + " | " + statusPagamento;
    }
}
