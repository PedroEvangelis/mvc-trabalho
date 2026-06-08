package evangelz.model;

import java.time.LocalDate;

public class Consulta {
    private int id;
    private int idAnimal;
    private int idVeterinario;
    private LocalDate data;
    private String motivo;
    private double valor;
    private String statusPagamento;

    public Consulta() {}

    public Consulta(int idAnimal, int idVeterinario, LocalDate data, String motivo, double valor) {
        this.idAnimal = idAnimal;
        this.idVeterinario = idVeterinario;
        this.data = data;
        this.motivo = motivo;
        this.valor = valor;
        this.statusPagamento = "PENDENTE";
    }

    public Consulta(int id, int idAnimal, int idVeterinario, LocalDate data, String motivo, double valor, String statusPagamento) {
        this.id = id;
        this.idAnimal = idAnimal;
        this.idVeterinario = idVeterinario;
        this.data = data;
        this.motivo = motivo;
        this.valor = valor;
        this.statusPagamento = statusPagamento;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdAnimal() { return idAnimal; }
    public void setIdAnimal(int idAnimal) { this.idAnimal = idAnimal; }

    public int getIdVeterinario() { return idVeterinario; }
    public void setIdVeterinario(int idVeterinario) { this.idVeterinario = idVeterinario; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getStatusPagamento() { return statusPagamento; }
    public void setStatusPagamento(String statusPagamento) { this.statusPagamento = statusPagamento; }

    @Override
    public String toString() {
        return "Consulta " + id + " | " + data + " | R$ " + String.format("%.2f", valor) + " | " + statusPagamento;
    }
}
