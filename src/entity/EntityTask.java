package entity;
import dto.DTOConsegna;
import dto.DTOTask;
import database.DBConsegna;
import database.DBTask;
import java.util.ArrayList;

public class EntityTask {
    private int id;
    private String titolo;
    private String descrizione;
    private String dataScadenza;
    private int maxPuntiAssegnabili;
    private ArrayList<EntityConsegna> consegne;

    public EntityTask() {
        this.titolo = "";
        this.descrizione = "";
        this.dataScadenza = "";
        this.maxPuntiAssegnabili = 0;
        this.consegne = null;
    }

    ///Costruttore con PK
    public EntityTask(int idTask) {
        this.id = idTask;
        DBTask task = new DBTask(idTask); ///Carico dal DB con la PK
        this.titolo = task.getTitolo();
        this.descrizione = task.getDescrizione();
        this.dataScadenza = task.getDataScadenza();
        this.maxPuntiAssegnabili = task.getMaxPuntiAssegnabili();
        this.consegne = new ArrayList<EntityConsegna>();
    }

    ///Costruttore copia da oggettoBD
    public EntityTask(DBTask task) {
        this.id = task.getId();
        this.titolo = task.getTitolo();
        this.descrizione = task.getDescrizione();
        this.dataScadenza = task.getDataScadenza();
        this.maxPuntiAssegnabili = task.getMaxPuntiAssegnabili();
    }

    ///GETTER E SETTER
    public int getId() {
        return id;
    }
    public String getTitolo() {
        return titolo;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public String getDataScadenza() {
        return dataScadenza;
    }
    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }
    public int getMaxPuntiAssegnabili() {
        return maxPuntiAssegnabili;
    }
    public void setMaxPuntiAssegnabili(int maxPuntiAssegnabili) {
        this.maxPuntiAssegnabili = maxPuntiAssegnabili;
    }

    @Override
    public String toString() {
        return "EntityTask{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", dataScadenza='" + dataScadenza + '\'' +
                ", maxPuntiAssegnabili=" + maxPuntiAssegnabili +
                '}';
    }

    private void caricaConsegne(DBTask task) {
        for(int i=0; i<task.getConsegne().size(); i++) {
            EntityConsegna consegna = new EntityConsegna(task.getConsegne().get(i));
            this.consegne.add(consegna);
        }
    }

    public int valutaConsegna(int consegnaID, int punteggio){
        if(punteggio>this.maxPuntiAssegnabili){return -1;}
        ///Caricamento delle consegne del task
        DBTask task = new DBTask(this.id);
        task.caricaConsegneDaDB();
        this.caricaConsegne(task);

        ///Ricerca della consegna da valutare
        EntityConsegna consegnaDaValutare = ricercaConsegna(consegnaID);
        if (consegnaDaValutare==null){
            return -1;
        }

        if (consegnaDaValutare.getPunteggio()!=-1){return -1;}

        DBConsegna DBConsegnaDaValutare = new DBConsegna(consegnaDaValutare.getId());
        int esito = consegnaDaValutare.impostaPunteggio(punteggio,DBConsegnaDaValutare);
        ///Passo il riferimento DAO della consegna da valutare esternamente così da non dover creare più volte l'oggetto per modificarlo all'interno dei singoli metodi
        if(esito==-1) {return esito;}

        ///Aggiorna Punteggio e NumTaskValutati studente
        return consegnaDaValutare.aggiornaStatisticheAndInviaMail(punteggio,DBConsegnaDaValutare,this.titolo);
    }

    private EntityConsegna ricercaConsegna(int consegnaID){
        for (EntityConsegna entityConsegna : this.consegne) {
            if (entityConsegna.getId() == consegnaID) {
                return entityConsegna;
            }
        }
        return null;
    }

    public ArrayList<DTOConsegna> getConsegne() {
        DBTask task = new DBTask(this.id);
        task.caricaConsegneDaDB();
        this.caricaConsegne(task);

        return creaDTOTaskList();
    }

    private ArrayList<DTOConsegna> creaDTOTaskList() {
        ArrayList<DTOConsegna> lista=new ArrayList<>();
        for (EntityConsegna entityConsegna : this.consegne) {
            DTOConsegna consegna = new DTOConsegna();
            consegna.setId(entityConsegna.getId());
            consegna.setPunteggio(entityConsegna.getPunteggio());
            consegna.setSoluzione(entityConsegna.getSoluzione());
            lista.add(consegna);
        }
        return lista;
    }

    public DTOTask getInfo() {
        DTOTask dtoTask = new DTOTask();
        dtoTask.setId(this.id);
        dtoTask.setTitolo(this.titolo);
        dtoTask.setDescrizione(this.descrizione);
        dtoTask.setDataScadenza(this.dataScadenza);
        dtoTask.setMaxPuntiAssegnabili(this.maxPuntiAssegnabili);
        return dtoTask;
    }


    ///Metodo Creazione Consegna
    public int consegnaSoluzione(int taskID, String soluzione, int studenteID){
        int ret = EntityConsegna.creaConsegna(taskID, soluzione, studenteID);
        if(ret==-1) {return ret;}

        return EntityConsegna.updateNumTaskCompletati(studenteID);
    }
}