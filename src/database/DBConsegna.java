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
        //System.out.println(query); //per debug

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

    public void caricaStudentiDaDB() {
        //Le consegne prelevano gli studenti ad esse relative
        //String query = "SELECT * FROM studente WHERE studente_id='"+this.studente.getId()+"';";
        String query1= "SELECT * FROM STUDENTI S JOIN CONSEGNE C ON C.studente_id = S.id WHERE C.id="+this.id+";";
        //System.out.println(query1);
        try {

            ResultSet rs1 = DBConnectionManager.selectQuery(query1);

            if(rs1.next()) {
                DBStudente studente = new DBStudente();
                studente.setId(rs1.getInt("id"));
                studente.setNome(rs1.getString("nome"));
                studente.setCognome(rs1.getString("nome"));
                studente.setMail(rs1.getString("mail"));
                studente.setPassword(rs1.getString("password"));
                studente.setNumTaskCompletati(rs1.getInt("numTaskCompletati"));
                studente.setNumTaskValutati(rs1.getInt("numTaskValutati"));
                studente.setPunteggioTotaleOttenuto(rs1.getInt("punteggioTotaleOttenuto"));
                //SALVATAGGIO RISULTATO
                this.studente = studente;
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
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
