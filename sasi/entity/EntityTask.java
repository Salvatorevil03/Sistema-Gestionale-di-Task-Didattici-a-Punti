package entity;
import DTO.DTOConsegna;
import DTO.DTOTask;
import database.DBConsegna;
import database.DBTask;
import java.util.ArrayList;

public class EntityTask {
    private int id;
    private String titolo;
    private String descrizione;
    private String dataScadenza;
    private int maxPuntiAssegnabili;
    //private EntityClasse classe;
    private ArrayList<EntityConsegna> consegne;

    public EntityTask() {
        this.titolo = "";
        this.descrizione = "";
        this.dataScadenza = "";
        this.maxPuntiAssegnabili = 0;
        //this.classe = null;
        this.consegne = null;
    }

    //costruttore con PK
    public EntityTask(int idTask) {
        this.id = idTask;
        DBTask task = new DBTask(idTask); //carico dal DB con la PK
        this.titolo = task.getTitolo();
        this.descrizione = task.getDescrizione();
        this.dataScadenza = task.getDataScadenza();
        this.maxPuntiAssegnabili = task.getMaxPuntiAssegnabili();
        //this.classe = null;
        this.consegne = new ArrayList<EntityConsegna>();
    }

    //costruttore copia da oggettoBD
    public EntityTask(DBTask task) {
        this.id = task.getId();
        this.titolo = task.getTitolo();
        this.descrizione = task.getDescrizione();
        this.dataScadenza = task.getDataScadenza();
        this.maxPuntiAssegnabili = task.getMaxPuntiAssegnabili();
        //this.consegne=task.getConsegne();
    }

    //GETTER E SETTER
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

    //##############################################
    //METODI PER POSSIBILI DIPENDENZE
    private void caricaConsegne(DBTask task) {
        for(int i=0; i<task.getConsegne().size(); i++) {
            EntityConsegna consegna = new EntityConsegna(task.getConsegne().get(i));
            this.consegne.add(consegna);
        }
    }
//
//    public void caricaClasse(DBTask task){
//        EntityClasse classe = new EntityClasse(task.getClasse());
//        this.setClasse(classe);
//    }

    //---------------------------------------------------
    //BUSINESS LOGIC
    public int valutaConsegna(int consegna_id, int punteggio){
        if(punteggio>this.maxPuntiAssegnabili){return -1;}
        //caricamento delle consegne del task
        DBTask task = new DBTask(this.id);
        task.caricaConsegneDaDB();
        this.caricaConsegne(task);
        //ricerca della consegna da valutare
        EntityConsegna consegna_da_valutare=ricercaConsegna(consegna_id);
        if (consegna_da_valutare==null){
            return -1;
        }

        if (consegna_da_valutare.getPunteggio()!=-1){return -1;}
        DBConsegna DB_consegna_da_valutare = new DBConsegna(consegna_da_valutare.getId());
        int esito = consegna_da_valutare.impostaPunteggio(punteggio,DB_consegna_da_valutare);
        //passo il riferimento DAO della consegna da valuatre esternamente così da non dover creare più volte l'oggetto per modificarlo all'interno dei singoli metodi
        if(esito==-1) {return esito;}
        esito=consegna_da_valutare.aggiornaStatistiche(punteggio,DB_consegna_da_valutare); //Aggiorna Punteggio e NumTaskValutati studente
        if(esito==-1) {return esito;}
        return esito;
    }

    private EntityConsegna ricercaConsegna(int consegna_id){
        for(int i=0;i<this.consegne.size();i++){
            if(this.consegne.get(i).getId()==consegna_id) {
                return consegne.get(i);
            }
        }
        return null;
    }

    public ArrayList<DTOConsegna> getConsegne() {
        DBTask task = new DBTask(this.id);
        task.caricaConsegneDaDB();
        this.caricaConsegne(task);
        ArrayList<DTOConsegna> lista=this.creaDTOTaskList();
        return lista;
    }

    private ArrayList<DTOConsegna> creaDTOTaskList() {
        ArrayList<DTOConsegna> lista=new ArrayList<>();
        for(int i=0;i<this.consegne.size();i++){
            DTOConsegna consegna =new DTOConsegna();
            consegna.setId(this.consegne.get(i).getId());
            consegna.setPunteggio(this.consegne.get(i).getPunteggio());
            consegna.setSoluzione(this.consegne.get(i).getSoluzione());
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


    //Metodo Creazione Consegna
    public int consegnaSoluzione(int taskID, String soluzione, int studenteID){
        int lunghezzaMassima = 30000;
        //String
        if(soluzione.length() > lunghezzaMassima) {return -1;}
        int ret = EntityConsegna.creaConsegna(taskID, soluzione, studenteID);
        if(ret==-1) {return ret;}
        ret = EntityConsegna.updateNumTaskCompletati(studenteID);
        return ret;
    }
}