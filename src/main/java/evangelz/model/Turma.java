package evangelz.model;

public class Turma {
    private int id;
    private int idCurso;
    private String nomeTurma;
    private String sala;
    private String turno;
    private int vagasTotais;
    private int vagasDisponiveis;

    public Turma() {}

    public Turma(int idCurso, String nomeTurma, String sala, String turno, int vagasTotais) {
        this.idCurso = idCurso;
        this.nomeTurma = nomeTurma;
        this.sala = sala;
        this.turno = turno;
        this.vagasTotais = vagasTotais;
        this.vagasDisponiveis = vagasTotais;
    }

    public Turma(int id, int idCurso, String nomeTurma, String sala, String turno, int vagasTotais, int vagasDisponiveis) {
        this.id = id;
        this.idCurso = idCurso;
        this.nomeTurma = nomeTurma;
        this.sala = sala;
        this.turno = turno;
        this.vagasTotais = vagasTotais;
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getNomeTurma() { return nomeTurma; }
    public void setNomeTurma(String nomeTurma) { this.nomeTurma = nomeTurma; }

    public String getSala() { return sala; }
    public void setSala(String sala) { this.sala = sala; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    public int getVagasTotais() { return vagasTotais; }
    public void setVagasTotais(int vagasTotais) { this.vagasTotais = vagasTotais; }

    public int getVagasDisponiveis() { return vagasDisponiveis; }
    public void setVagasDisponiveis(int vagasDisponiveis) { this.vagasDisponiveis = vagasDisponiveis; }

    @Override
    public String toString() {
        return id + " - " + nomeTurma + " | Vagas: " + vagasDisponiveis + "/" + vagasTotais;
    }
}
