package DTO;

public class DTOClasse {
    private int codice;
    private String nome;
    private int numeroTask;

    public DTOClasse() {
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroTask() {
        return numeroTask;
    }

    public void setNumeroTask(int numeroTask) {
        this.numeroTask = numeroTask;
    }

    @Override
    public String toString() {
        return "DTOClasse{" +
                "codice=" + codice +
                ", nome='" + nome + '\'' +
                ", numeroTask=" + numeroTask +
                '}';
    }
}
