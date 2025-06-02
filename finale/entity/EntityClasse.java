package entity;
import DTO.DTOStudente;
import DTO.DTOTask;
import database.DBClasse;

import java.util.ArrayList;

public class EntityClasse {
    private int codice;
    private String nome;
    private int numeroTask;
    //private EntityDocente docente;
    private ArrayList<EntityStudente> studenti;
    private ArrayList<EntityTask> task;

    public EntityClasse() {
        super();
        this.studenti = new ArrayList<EntityStudente>();
        this.task = new ArrayList<EntityTask>();
    }

    public EntityClasse(int codice) {
        DBClasse classe= new DBClasse(codice);
        this.codice = codice;
        this.nome = classe.getNome();
        this.numeroTask = classe.getNumeroTask();
        this.studenti= new ArrayList<EntityStudente>();
        this.task=new ArrayList<EntityTask>();
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

    public ArrayList<DTOTask> getTasks() {
        DBClasse classe= new DBClasse(codice);
        classe.caricaTaskDaDB();
        this.caricaTask(classe);
        ArrayList<DTOTask> lista=this.creaDTOTaskList();
        return lista;
    }

    private ArrayList<DTOTask> creaDTOTaskList() {
        ArrayList<DTOTask> lista= new ArrayList<>();
        for(int i=0;i<task.size();i++){
            DTOTask dto= new DTOTask();
            dto.setId(task.get(i).getId());
            dto.setTitolo(task.get(i).getTitolo());
            dto.setDescrizione(task.get(i).getDescrizione());
            dto.setMaxPuntiAssegnabili(task.get(i).getMaxPuntiAssegnabili());
            dto.setDataScadenza(task.get(i).getDataScadenza());
            lista.add(dto);
        }
        return lista;
    }

    //###########################################################
    //METODI PER POSSIBILI DIPENDENZE
//    public void caricaDocente(DBClasse classe) {
//        EntityDocente docente = new EntityDocente(classe.getDocente());
//        this.docente = docente;
//    }
//
    public void caricaStudenti(DBClasse classe) {
        for(int i=0; i<classe.getStudenti().size(); i++) {
            EntityStudente studente = new EntityStudente(classe.getStudenti().get(i));
            this.studenti.add(studente);
        }
    }
    public void caricaTask(DBClasse classe) {
        for(int i=0; i<classe.getTask().size(); i++) {
            EntityTask task = new EntityTask(classe.getTask().get(i));
            this.task.add(task);
        }
    }

    public ArrayList<DTOStudente> getStudenti() {
        DBClasse classe= new DBClasse(codice);
        classe.caricaStudentiDaDB();
        this.caricaStudenti(classe);
        ArrayList<DTOStudente> lista=this.creaDTOStudentiList();
        return lista;
    }

    private ArrayList<DTOStudente> creaDTOStudentiList() {
        ArrayList<DTOStudente> lista= new ArrayList<>();
        for(int i=0;i<studenti.size();i++){
            DTOStudente dto= new DTOStudente();
            dto.setId(studenti.get(i).getId());
            dto.setNome(studenti.get(i).getNome());
            dto.setMail(studenti.get(i).getMail());
            dto.setCognome(studenti.get(i).getCognome());
            dto.setPassword(studenti.get(i).getPassword());
            dto.setNumTaskCompletati(studenti.get(i).getNumTaskCompletati());
            dto.setNumTaskValutati(studenti.get(i).getNumTaskValutati());
            dto.setPunteggioTotaleOttenuto(studenti.get(i).getPunteggioTotaleOttenuto());
            lista.add(dto);
        }
        return lista;
    }

    public int creaTask(String titolo, String descrizione, String dataScadenza, int maxPunteggio,int id_classe) {
        DBClasse classe= new DBClasse(id_classe);
        int esito=classe.creaTask(titolo,descrizione,dataScadenza,maxPunteggio,id_classe);
        return esito;
    }

    public int iscrizione(int id_studente,int id_classe) {
        int esito=0;
        DBClasse classe= new DBClasse();
        esito=classe.iscrizioneSuDB(id_studente,id_classe);
        return esito;
    }
}