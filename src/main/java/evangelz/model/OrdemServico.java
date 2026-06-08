package evangelz.model;

import java.time.LocalDate;

public class OrdemServico {
    private int id;
    private int idVeiculo;
    private LocalDate dataAbertura;
    private String descricaoProblema;
    private String status;
    private String statusPagamento;

    public OrdemServico() {}

    public OrdemServico(int idVeiculo, LocalDate dataAbertura, String descricaoProblema) {
        this.idVeiculo = idVeiculo;
        this.dataAbertura = dataAbertura;
        this.descricaoProblema = descricaoProblema;
        this.status = "ABERTA";
        this.statusPagamento = "PENDENTE";
    }

    public OrdemServico(int id, int idVeiculo, LocalDate dataAbertura, String descricaoProblema, String status, String statusPagamento) {
        this.id = id;
        this.idVeiculo = idVeiculo;
        this.dataAbertura = dataAbertura;
        this.descricaoProblema = descricaoProblema;
        this.status = status;
        this.statusPagamento = statusPagamento;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdVeiculo() { return idVeiculo; }
    public void setIdVeiculo(int idVeiculo) { this.idVeiculo = idVeiculo; }

    public LocalDate getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDate dataAbertura) { this.dataAbertura = dataAbertura; }

    public String getDescricaoProblema() { return descricaoProblema; }
    public void setDescricaoProblema(String descricaoProblema) { this.descricaoProblema = descricaoProblema; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getStatusPagamento() { return statusPagamento; }
    public void setStatusPagamento(String statusPagamento) { this.statusPagamento = statusPagamento; }

    @Override
    public String toString() {
        return "OS " + id + " | " + dataAbertura + " | " + status + " | Pag: " + statusPagamento;
    }
}
