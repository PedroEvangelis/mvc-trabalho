package evangelz.model;

public class ItemOrdemServico {
    private int id;
    private int idOrdemServico;
    private int idServico;
    private int quantidade;
    private double valorUnitario;

    public ItemOrdemServico() {}

    public ItemOrdemServico(int idOrdemServico, int idServico, int quantidade, double valorUnitario) {
        this.idOrdemServico = idOrdemServico;
        this.idServico = idServico;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public ItemOrdemServico(int id, int idOrdemServico, int idServico, int quantidade, double valorUnitario) {
        this.id = id;
        this.idOrdemServico = idOrdemServico;
        this.idServico = idServico;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdOrdemServico() { return idOrdemServico; }
    public void setIdOrdemServico(int idOrdemServico) { this.idOrdemServico = idOrdemServico; }

    public int getIdServico() { return idServico; }
    public void setIdServico(int idServico) { this.idServico = idServico; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(double valorUnitario) { this.valorUnitario = valorUnitario; }

    public double getSubtotal() { return quantidade * valorUnitario; }

    @Override
    public String toString() {
        return "Servico #" + idServico + " | Qtd: " + quantidade + " | R$ " + String.format("%.2f", valorUnitario) + " | Subtotal: R$ " + String.format("%.2f", getSubtotal());
    }
}
