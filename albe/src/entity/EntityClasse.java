package entity;
import dto.DTOStudente;
import dto.DTOTask;
import database.DBClasse;
import sistemadinotifiche.MailSender;

import java.util.ArrayList;
import java.util.List;


public class EntityClasse {
    private int codice;
    private String nome;
    private int numeroTask;
    private ArrayList<EntityStudente> studenti;
    private ArrayList<EntityTask> task;

    public EntityClasse() {
        super();
        this.studenti = new ArrayList<>();
        this.task = new ArrayList<>();
    }

    public EntityClasse(int codice) {
        DBClasse classe= new DBClasse(codice);
        this.codice = codice;
        this.nome = classe.getNome();
        this.numeroTask = classe.getNumeroTask();
        this.studenti= new ArrayList<>();
        this.task=new ArrayList<>();
    }

    public EntityClasse(DBClasse classe) {
        this.codice = classe.getCodice();
        this.nome = classe.getNome();
        this.numeroTask = classe.getNumeroTask();
    }

    ///GETTER AND SETTER-----------------
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

    public List<DTOTask> getTasks() {
        DBClasse classe= new DBClasse(codice);
        classe.caricaTaskDaDB();
        this.caricaTask(classe);

        return creaDTOTaskList();
    }

    private ArrayList<DTOTask> creaDTOTaskList() {
        ArrayList<DTOTask> lista= new ArrayList<>();
        for (EntityTask entityTask : task) {
            DTOTask dto = new DTOTask();
            dto.setId(entityTask.getId());
            dto.setTitolo(entityTask.getTitolo());
            dto.setDescrizione(entityTask.getDescrizione());
            dto.setMaxPuntiAssegnabili(entityTask.getMaxPuntiAssegnabili());
            dto.setDataScadenza(entityTask.getDataScadenza());
            lista.add(dto);
        }
        return lista;
    }

    public void caricaStudenti(DBClasse classe) {
        for(int i=0; i<classe.getStudenti().size(); i++) {
            EntityStudente studente = new EntityStudente(classe.getStudenti().get(i));
            this.studenti.add(studente);
        }
    }

    public void caricaTask(DBClasse classe) {
        for(int i=0; i<classe.getTask().size(); i++) {
            EntityTask taskDaCaricare = new EntityTask(classe.getTask().get(i));
            this.task.add(taskDaCaricare);
        }
    }

    public List<DTOStudente> getStudenti() {
        DBClasse classe= new DBClasse(codice);
        classe.caricaStudentiDaDB();
        this.caricaStudenti(classe);
        return creaDTOStudentiList();
    }

    private ArrayList<DTOStudente> creaDTOStudentiList() {
        ArrayList<DTOStudente> lista= new ArrayList<>();
        for (EntityStudente entityStudente : studenti) {
            DTOStudente dto = new DTOStudente();
            dto.setId(entityStudente.getId());
            dto.setNome(entityStudente.getNome());
            dto.setMail(entityStudente.getMail());
            dto.setCognome(entityStudente.getCognome());
            dto.setPassword(entityStudente.getPassword());
            dto.setNumTaskCompletati(entityStudente.getNumTaskCompletati());
            dto.setNumTaskValutati(entityStudente.getNumTaskValutati());
            dto.setPunteggioTotaleOttenuto(entityStudente.getPunteggioTotaleOttenuto());
            lista.add(dto);
        }
        return lista;
    }

    /**
     * Crea un nuovo task nella classe corrente.
     *
     * <p>Dopo le verifiche effettuate nella GUI {@link taskdidatticiNEW.GUIClasseDocente}
     * il Task verrà creato nel DB e verrà restituito contestualmente un valore di ritorno.</p>
     * <br>Se il Task viene correttamente inserito nel DB il sistema invia automaticamente una
     * mail di notifica a tutti gli studenti iscritti alla classe.
     *
     * <p>Per inviare in maniera effettiva delle mail di notifica abbiamo utilizzato Jakarta
     * Mail.
     * <br>Il metodo ParallelStream è utilizzato per creare una connessione parallela migliorando
     * notevolmente la velocità di invio</p>
     *
     * @param titolo titolo del task da creare.
     * @param descrizione descrizione del task da creare.
     * @param dataScadenza data di scadenza del task da creare.
     * @param maxPunteggio punteggio massimo del task da creare.
     *
     * @return un intero che rappresenta il risultato della creazione del task: -1 (errore) o 1 (successo)
     */
    public int creaTask(String titolo, String descrizione, String dataScadenza, int maxPunteggio) {
        DBClasse classe = new DBClasse(this.codice);
        int ret = classe.creaTask(titolo,descrizione,dataScadenza,maxPunteggio);
        if(ret == -1) {
            return -1;
        }else{
            classe.caricaStudentiDaDB();
            this.caricaStudenti(classe);
            this.studenti.parallelStream().forEach(studente ->
                    MailSender.inviaCreazioneTask(studente.getMail(),titolo,"Invia la tua soluzione prima che scada!!"));
        }
        return ret;
    }

    public int iscrizione(int studenteID,int classeID) {
        DBClasse classe= new DBClasse();
        return classe.iscrizioneSuDB(studenteID,classeID);
    }
}