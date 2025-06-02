package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientException;
import java.util.ArrayList;
import java.util.Map;

public class DBClasse {
    private int codice;
    private String nome;
    private int numeroTask;
    //private DBDocente docente;
    private ArrayList<DBStudente> studenti;
    private ArrayList<DBTask> task;


    public DBClasse() {
        super();
        this.studenti = new ArrayList<DBStudente>();
        this.task = new ArrayList<DBTask>();
    }

    public DBClasse(int codice) {
        this.codice = codice;
        this.studenti = new ArrayList<DBStudente>();
        this.task=new ArrayList<DBTask>();
        caricaDaDB();
    }

    public DBClasse(DBClasse classe) {
        this.codice=classe.getCodice();
        this.nome=classe.getNome();
        this.numeroTask=classe.getNumeroTask();
//        this.docente=classe.getDocente();
        this.studenti=classe.studenti;
        this.task=classe.task;
    }

    //CARICAMENTO DATI DAL DB
    public void caricaDaDB(){
        String query= "select * from classi where codice='"+this.codice+"';";
        try {
            ResultSet rs = DBConnectionManager.selectQuery(query);
            if(rs.next()) {
                this.setCodice(rs.getInt("codice"));
                this.setNome(rs.getString("nome"));
                this.setNumeroTask(rs.getInt("numeroTask"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //GETTER AND SETTER--------------------------------------------

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
    public ArrayList<DBTask> getTask() {
        return task;
    }
    public ArrayList<DBStudente> getStudenti() {
        return studenti;
    }
    //METODO TO STRING

    @Override
    public String toString() {
        return "DBClasse{" +
                "codice=" + codice +
                ", nome='" + nome + '\'' +
                ", numeroTask=" + numeroTask +
                '}';
    }

    //##########################################################################################
    //METODI PER POSSIBILI CARICAMENTI DI DIPENDENZE

//    public void caricaDocenteDaDB() {
//        String query1 = "SELECT * FROM DOCENTI D JOIN CLASSI C ON C.docente_id = D.id WHERE C.codice=" + this.codice + ";";
//        try {
//            ResultSet rs1 = DBConnectionManager.selectQuery(query1);
//            if (rs1.next()) {
//                DBDocente docente = new DBDocente();
//                docente.setId(rs1.getInt("id"));
//                docente.setNome(rs1.getString("nome"));
//                docente.setCognome(rs1.getString("cognome"));
//                docente.setMail(rs1.getString("mail"));
//                docente.setPassword(rs1.getString("password"));
//
//                //SALVATAGGIO RISULTATO
//                this.docente = docente;
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
    public void caricaStudentiDaDB() {
        String query1 = "SELECT * FROM STUDENTI S JOIN CLASSI C ON S.classe_codice=C.codice WHERE C.codice=" + this.codice + ";";
        try {
            ResultSet rs1 = DBConnectionManager.selectQuery(query1);
            while (rs1.next()) {
                DBStudente studente = new DBStudente();
                studente.setId(rs1.getInt("S.id"));
                studente.setNome(rs1.getString("nome"));
                studente.setCognome(rs1.getString("cognome"));
                studente.setMail(rs1.getString("mail"));
                studente.setPassword(rs1.getString("password"));
                studente.setNumTaskCompletati(rs1.getInt("numTaskCompletati"));
                studente.setNumTaskValutati(rs1.getInt("numTaskValutati"));
                studente.setPunteggioTotaleOttenuto(rs1.getInt("punteggioTotaleOttenuto"));
                this.studenti.add(studente);
            }
            rs1.close();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
//
    public void caricaTaskDaDB() {

        String query1 = "SELECT * FROM TASK T JOIN CLASSI C ON T.classe_codice=C.codice WHERE C.codice=" + this.codice + ";";
        try {
            ResultSet rs1 = DBConnectionManager.selectQuery(query1);
            while(rs1.next()) {
                DBTask task = new DBTask();
                task.setId(rs1.getInt("id"));
                task.setTitolo(rs1.getString("titolo"));
                task.setDescrizione(rs1.getString("descrizione"));
                task.setMaxPuntiAssegnabili(rs1.getInt("maxPuntiAssegnabili"));
                task.setDataScadenza(rs1.getString("dataScadenza"));

                //SALVATAGGIO RISULTATO
                this.task.add(task);
            }
            rs1.close();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int creaTask(String titolo, String descrizione, String dataScadenza, int maxPunteggio,int id_classe) {
        DBTask task = new DBTask();
        task.setTitolo(titolo);
        task.setDescrizione(descrizione);
        task.setDataScadenza(dataScadenza);
        task.setMaxPuntiAssegnabili(maxPunteggio);
        int esito=this.inserisciSuDB(task,id_classe);
        return esito;
    }

    private int inserisciSuDB(DBTask task,int id_classe) {
        int esito=1;
        String query="INSERT INTO TASK (titolo,descrizione,dataScadenza,maxPuntiAssegnabili,classe_codice) VALUES ('" + task.getTitolo() + "','" +
                task.getDescrizione() + "','" + task.getDataScadenza() + "'," +
                task.getMaxPuntiAssegnabili()+","+id_classe+ ")";

        //System.out.println(query);
        try{
            esito=DBConnectionManager.updateQuery(query);
        }catch(ClassNotFoundException | SQLException e){
            //e.printStackTrace();
            esito=-1;
        }
        if(esito==-1){return esito;}
        this.setNumeroTask(this.numeroTask+1);
        esito=this.salvaNumTaskSuDB(id_classe);
        return esito;
    }

    private int salvaNumTaskSuDB(int id_classe) {
        int ret=1;
        String query = "UPDATE classi SET numeroTask = "+this.numeroTask+" WHERE codice= "+this.codice+";";
        //System.out.println(query);
        try{
            ret=DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
            ret=-1;
        }
        //System.out.println("RET: "+ret);
        return ret;
    }

    public int iscrizioneSuDB(int idStudente, int idClasse) {
        int ret=0;
        String query = "UPDATE studenti SET classe_codice = "+idClasse+" WHERE id= "+idStudente+";";
        try{
            ret=DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            ret=-1;
        }
        return ret;
    }

//
//    public DBDocente getDocente() {
//        return docente;
//    }
//    public void setDocente(DBDocente docente) {
//        this.docente = docente;
//    }
//    public ArrayList<DBStudente> getStudenti() {
//        return studenti;
//    }
//    public void setStudenti(ArrayList<DBStudente> studenti) {
//        this.studenti = studenti;
//    }
//    public ArrayList<DBTask> getTask() {
//        return task;
//    }
//    public void setTask(ArrayList<DBTask> task) {
//        this.task = task;
//    }
}