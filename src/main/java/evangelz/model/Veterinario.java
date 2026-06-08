package evangelz.model;

public class Veterinario {
    private int id;
    private String nome;

    public Veterinario() {}

    public Veterinario(String nome) {
        this.nome = nome;
    }

    public Veterinario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
