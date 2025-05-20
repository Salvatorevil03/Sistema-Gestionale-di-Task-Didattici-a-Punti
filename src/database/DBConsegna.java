package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConsegna {
    private int id; // PK
    private int punteggio;
    private String soluzione;
    private DBTask task;
    private DBStudente studente;

    public DBConsegna() {
        super();
    }

    public DBConsegna(int id) {
        this.id = id;
        caricaDaDB();
    }

    public void caricaDaDB() {

        String query = "SELECT * FROM consegne WHERE id='"+this.id+"';";
        System.out.println(query); //per debug

        try {

            ResultSet rs = DBConnectionManager.selectQuery(query);

            if(rs.next()) {
                this.setPunteggio(rs.getInt("punteggio"));
                this.setSoluzione(rs.getString("soluzione"));
            }

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }

    // get e set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public String getSoluzione() {
        return soluzione;
    }

    public void setSoluzione(String soluzione) {
        this.soluzione = soluzione;
    }

    public DBTask getTask() {
        return task;
    }

    public void setTask(DBTask task) {
        this.task = task;
    }

    public DBStudente getStudente() {
        return studente;
    }

    public void setStudente(DBStudente studente) {
        this.studente = studente;
    }
}
