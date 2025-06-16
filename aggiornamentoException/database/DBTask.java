package database;

import exceptions.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBTask {

    private int id;
    private String titolo;
    private String descrizione;
    private String dataScadenza;
    private int maxPuntiAssegnabili;
    private ArrayList<DBConsegna> consegne;

    public DBTask() {
        this.titolo = "";
        this.descrizione = "";
        this.dataScadenza = "";
        this.maxPuntiAssegnabili = 0;
        this.consegne = new ArrayList<>();
    }

    public DBTask(int idTask) {
        this.id = idTask;
        this.consegne = new ArrayList<>();
        caricaDaDB();
    }

    public DBTask(DBTask dbTask) {
        this.id = dbTask.getId();
        this.titolo = dbTask.getTitolo();
        this.descrizione = dbTask.getDescrizione();
        this.dataScadenza = dbTask.getDataScadenza();
        this.maxPuntiAssegnabili = dbTask.getMaxPuntiAssegnabili();
        this.consegne= (ArrayList<DBConsegna>) dbTask.getConsegne();
    }

    ///CARICAMENTO DA DB
    public void caricaDaDB() {
        ///1. definisco la query
        String query = "SELECT * FROM task WHERE id='" + this.id + "';";
        try {
            ///2 faccio di query di select
            /// - crea la connessione
            /// - statement
            ResultSet rs = DBConnectionManager.selectQuery(query);
            if (rs.next()) { ///Se ho un risultato
            ///mi vado a prendere i dati, accedendo tramite il nome dell'attributo-colonna
                this.setTitolo(rs.getString("titolo"));
                this.setDescrizione(rs.getString("descrizione"));
                this.setDataScadenza(rs.getString("dataScadenza"));
                this.setMaxPuntiAssegnabili(rs.getInt("maxPuntiAssegnabili"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    ///GETTER E SETTER
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public List<DBConsegna> getConsegne() {return consegne;}

    @Override
    public String toString() {
        return "DBTask{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", dataScadenza='" + dataScadenza + '\'' +
                ", maxPuntiAssegnabili=" + maxPuntiAssegnabili +
                '}';
    }

    public void caricaConsegneDaDB(){
        String query1 = "SELECT * FROM TASK T JOIN CONSEGNE C ON T.id = C.task_id WHERE T.id=" + this.id + ";";
        try {
            ResultSet rs1 = DBConnectionManager.selectQuery(query1);
            while (rs1.next()) {
                DBConsegna consegna= new DBConsegna();
                consegna.setId(rs1.getInt("C.id"));
                consegna.setPunteggio(rs1.getInt("punteggio"));
                consegna.setSoluzione(rs1.getString("soluzione"));

                ///SALVATAGGIO RISULTATO
                this.consegne.add(consegna);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new DBException();
        }

    }

}