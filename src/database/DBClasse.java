package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBClasse {
    private int codice;
    private String nome;
    private int numeroTask;
    private DBDocente docente;
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
        this.docente=classe.getDocente();
        this.studenti=classe.studenti;
        this.task=classe.task;
    }

    public void caricaDocenteDaDB() {
        String query1 = "SELECT * FROM DOCENTI D JOIN CLASSI C ON C.docente_id = D.id WHERE C.codice=" + this.codice + ";";
        try {
            ResultSet rs1 = DBConnectionManager.selectQuery(query1);
            if (rs1.next()) {
                DBDocente docente = new DBDocente();
                docente.setId(rs1.getInt("id"));
                docente.setNome(rs1.getString("nome"));
                docente.setCognome(rs1.getString("cognome"));
                docente.setMail(rs1.getString("mail"));
                docente.setPassword(rs1.getString("password"));

                //SALVATAGGIO RISULTATO
                this.docente = docente;
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void caricaStudentiDaDB() {
        String query1 = "SELECT * FROM STUDENTI S JOIN CLASSI C ON S.classe_codice=C.codice WHERE C.codice=" + this.codice + ";";
        try {
            ResultSet rs1 = DBConnectionManager.selectQuery(query1);
            while (rs1.next()) {
                DBStudente studente = new DBStudente();
                studente.setId(rs1.getInt("id"));
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

                //SALVATAGGIO RISULTATO
                this.task.add(task);
            }
            rs1.close();
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

    public DBDocente getDocente() {
        return docente;
    }

    public void setDocente(DBDocente docente) {
        this.docente = docente;
    }

    public ArrayList<DBStudente> getStudenti() {
        return studenti;
    }

    public void setStudenti(ArrayList<DBStudente> studenti) {
        this.studenti = studenti;
    }

    public ArrayList<DBTask> getTask() {
        return task;
    }

    public void setTask(ArrayList<DBTask> task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "DBClasse{" +
                "codice=" + codice +
                ", nome='" + nome + '\'' +
                ", numeroTask=" + numeroTask +
                ", docente=" + docente +
                ", studenti=" + studenti +
                ", task=" + task +
                '}';
    }

    //--------------------------------------------
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
}
