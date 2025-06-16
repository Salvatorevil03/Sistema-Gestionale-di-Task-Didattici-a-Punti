package database;
import exceptions.DBException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConsegna {
    private int id; /// PK
    private int punteggio;
    private String soluzione;

    public DBConsegna() {
        super();
    }

    public DBConsegna(int id) {
        this.id = id;
        caricaDaDB();
    }

    public DBConsegna(DBConsegna consegna){
        this.id=consegna.getId();
        this.punteggio=consegna.getPunteggio();
        this.soluzione=consegna.getSoluzione();
    }

    ///CARICAMENTO DA DB
    public void caricaDaDB() {
        String query = "SELECT * FROM consegne WHERE id='"+this.id+"';";
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

    ///GETTER E SETTER
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

    @Override
    public String toString() {
        return "DBConsegna{" +
                "id=" + id +
                ", punteggio=" + punteggio +
                ", soluzione='" + soluzione + '\'' +
                '}';
    }

    public DBStudente caricaStudenteDaDB() {
        ///Le consegne prelevano gli studenti a esse relative
        String query1= "SELECT * FROM STUDENTI S JOIN CONSEGNE C ON C.studente_id = S.id WHERE C.id="+this.id+";";
        try {

            ResultSet rs1 = DBConnectionManager.selectQuery(query1);

            if(rs1.next()) {
                DBStudente studente = new DBStudente();
                studente.setId(rs1.getInt("S.id"));
                studente.setNome(rs1.getString("nome"));
                studente.setCognome(rs1.getString("cognome"));
                studente.setMail(rs1.getString("mail"));
                studente.setPassword(rs1.getString("password"));
                studente.setNumTaskCompletati(rs1.getInt("numTaskCompletati"));
                studente.setNumTaskValutati(rs1.getInt("numTaskValutati"));
                studente.setPunteggioTotaleOttenuto(rs1.getInt("punteggioTotaleOttenuto"));
                ///SALVATAGGIO RISULTATO
                return studente;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void salvaInDB() {
        String query = "UPDATE consegne SET punteggio = "+this.punteggio+ " WHERE id= "+this.id+";";
        try{
            DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            throw new DBException();
        }
    }

    public void inserisciSuDB(int taskID, int studenteID){

        String query = "INSERT INTO consegne (punteggio,soluzione,task_id,studente_id) VALUES("+this.punteggio+ ", '"+this.soluzione+ "', "+taskID+ ", "+studenteID +");";
        try{
            DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
           throw new DBException();
        }
    }

}