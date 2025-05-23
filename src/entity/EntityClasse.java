package entity;

import database.DBClasse;

public class EntityClasse {
    private int codice;
    private String nome;
    private int numeroTask;
    private EntityDocente docente;
    //private ArrayList<EntityStudente> studenti;
    //private ArrayList<EntityTask> task;
    //RICORDA LA CREAZIONE DI QUESTI ATTRIBUTI -> MODIFICA DEL TO STRING


    public EntityClasse() {
        super();
    }

    public EntityClasse(int codice) {
        DBClasse classe= new DBClasse(codice);
        this.codice = codice;
        this.nome = classe.getNome();
        this.numeroTask = classe.getNumeroTask();

        classe.caricaDocenteDaDB();
        caricaDocente(classe);
    }

    public EntityClasse(DBClasse classe) {
        this.codice = classe.getCodice();
        this.nome = classe.getNome();
        this.numeroTask = classe.getNumeroTask();
    }

    public void caricaDocente(DBClasse classe) {
        EntityDocente docente = new EntityDocente(classe.getDocente());
        this.docente = docente;
    }

    //GETTER AND SETTER-----------------
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
        return "EntityClasse{" +
                "codice=" + codice +
                ", nome='" + nome + '\'' +
                ", numeroTask=" + numeroTask +
                ", docente=" + docente +
                '}';
    }
}
