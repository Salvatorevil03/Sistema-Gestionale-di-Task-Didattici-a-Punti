package database;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConsegna {
    private int id; // PK
    private int punteggio;
    private String soluzione;
    //private DBTask task;
    //private DBStudente studente;

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

    //CARICAMENTO DA DB
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

    //GETTER E SETTER
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

    //###########################################################################
    // METODI PER POSSIBILI DIPENDENZE

    public DBStudente caricaStudenteDaDB() {
        //Le consegne prelevano gli studenti ad esse relative
        //String query = "SELECT * FROM studente WHERE studente_id='"+this.studente.getId()+"';";
        String query1= "SELECT * FROM STUDENTI S JOIN CONSEGNE C ON C.studente_id = S.id WHERE C.id="+this.id+";";
        //System.out.println(query1);
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
                //SALVATAGGIO RISULTATO
                //this.studente = studente;
                return studente;
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

//    public void caricaTaskDaDB() {
//        String query1= "SELECT * FROM TASK T JOIN CONSEGNE C ON C.task_id = T.id WHERE C.id="+this.id+";";
//        try {
//            ResultSet rs1 = DBConnectionManager.selectQuery(query1);
//            if(rs1.next()) {
//                DBTask task = new DBTask();
//                task.setId(rs1.getInt("id"));
//                task.setTitolo(rs1.getString("titolo"));
//                task.setDescrizione(rs1.getString("descrizione"));
//                task.setDataScadenza(rs1.getString("dataScadenza"));
//                task.setMaxPuntiAssegnabili(rs1.getInt("maxPuntiAssegnabili"));
//                //SALVATAGGIO RISULTATO
//                this.task = task;
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    //---------------------------------------
    //BUSINESS LOGIC
    public int salvaInDB() {
        int ret=0;
        //UPDATE taskdidattici.consegne SET punteggio = 15 WHERE id = 1;
        String query = "UPDATE consegne SET punteggio = "+this.punteggio+ " WHERE id= "+this.id+";";
        try{
            ret=DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
            ret=-1;
        }
        return ret;
    }

    public int InserisciSuDB(int taskID, int studenteID){
        int ret=0;
        String query = "INSERT INTO consegne (punteggio,soluzione,task_id,studente_id) VALUES("+this.punteggio+ ", '"+this.soluzione+ "', "+taskID+ ", "+studenteID +");";
        try{
            ret = DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            //System.err.println("Errore durante l'inserimento nel database: " + e.getMessage());
            //System.err.println("Tipo di eccezione: " + e.getClass().getSimpleName());
            ret=-1;
        }
        return ret;
    }

}