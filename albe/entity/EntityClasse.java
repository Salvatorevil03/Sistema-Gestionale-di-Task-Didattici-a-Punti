package entity;
import database.DBClasse;
import java.util.ArrayList;

public class EntityClasse {
    private int codice;
    private String nome;
    private int numeroTask;
    //private EntityDocente docente;
    //private ArrayList<EntityStudente> studenti;
    //private ArrayList<EntityTask> task;

    public EntityClasse() {
        super();
        //this.studenti = new ArrayList<EntityStudente>();
        //this.task = new ArrayList<EntityTask>();
    }

    public EntityClasse(int codice) {
        DBClasse classe= new DBClasse(codice);
        this.codice = codice;
        this.nome = classe.getNome();
        this.numeroTask = classe.getNumeroTask();
        //this.studenti= new ArrayList<EntityStudente>();
        //this.task=new ArrayList<EntityTask>();
    }

    public EntityClasse(DBClasse classe) {
        this.codice = classe.getCodice();
        this.nome = classe.getNome();
        this.numeroTask = classe.getNumeroTask();
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
                '}';
    }

    //###########################################################
    //METODI PER POSSIBILI DIPENDENZE
//    public void caricaDocente(DBClasse classe) {
//        EntityDocente docente = new EntityDocente(classe.getDocente());
//        this.docente = docente;
//    }
//
//    public void caricaStudenti(DBClasse classe) {
//        for(int i=0; i<classe.getStudenti().size(); i++) {
//            EntityStudente studente = new EntityStudente(classe.getStudenti().get(i));
//            this.studenti.add(studente);
//        }
//    }
//    public void caricaTask(DBClasse classe) {
//        for(int i=0; i<classe.getTask().size(); i++) {
//            EntityTask task = new EntityTask(classe.getTask().get(i));
//            this.task.add(task);
//        }
//    }
}