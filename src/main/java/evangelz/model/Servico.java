package evangelz.model;

public class Servico {
    private int id;
    private String nome;
    private String descricao;
    private double valorBase;

    public Servico() {}

    public Servico(String nome, String descricao, double valorBase) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorBase = valorBase;
    }

    public Servico(int id, String nome, String descricao, double valorBase) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valorBase = valorBase;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValorBase() { return valorBase; }
    public void setValorBase(double valorBase) { this.valorBase = valorBase; }

    @Override
    public String toString() {
        return id + " - " + nome + " | R$ " + String.format("%.2f", valorBase);
    }
}
