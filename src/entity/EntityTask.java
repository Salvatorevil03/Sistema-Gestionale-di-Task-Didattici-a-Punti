package entity;

import database.DBTask;
import java.util.ArrayList;

public class EntityTask {

    public int id;
    public String titolo;
    public String descrizione;
    public String dataScadenza;
    public int maxPuntiAssegnabili;
    //public EntityClasse classe;
    //public ArrayList<EntityConsegna> consegne;

    public EntityTask() {
        this.titolo = "";
        this.descrizione = "";
        this.dataScadenza = "";
        this.maxPuntiAssegnabili = 0;
        //this.classe = null;
        //this.consegne = null;
    }

    //costruttore con PK
    public EntityTask(int idTask) {

        this.id = idTask;
        DBTask task = new DBTask(idTask); //carico dal DB con la PK
        this.titolo = task.getTitolo();
        this.descrizione = task.getDescrizione();
        this.dataScadenza = task.getDataScadenza();
        this.maxPuntiAssegnabili = task.getMaxPuntiAssegnabili();

    }

    //costruttore copia da oggettoBD
    public EntityTask(DBTask task) {

        this.id = task.getId();
        this.titolo = task.getTitolo();
        this.descrizione = task.getDescrizione();
        this.dataScadenza = task.getDataScadenza();
        this.maxPuntiAssegnabili = task.getMaxPuntiAssegnabili();

    }

    //Setter e Getter
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
                ", titolo=" + titolo +
                ", descrizione=" + descrizione +
                ", dataScadenza=" + dataScadenza +
                ", maxPuntiAssegnabili=" + maxPuntiAssegnabili +
                ", classe="  + ", consegne=" +
                '}';
    }
}
