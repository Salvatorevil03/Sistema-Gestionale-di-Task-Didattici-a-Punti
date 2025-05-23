package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBClasse {
    private int codice;
    private String nome;
    private int numeroTask;
    private DBDocente docente;
    //private ArrayList<DBStudente> studenti;
    //private ArrayList<DBTask> task;
    //RICORDA LA CREAZIONE DI QUESTI ATTRIBUTI -> MODIFICA DEL TO STRING

    public DBClasse() {
        super();
    }

    public DBClasse(int codice) {
        this.codice = codice;
        caricaDaDB();
    }

    public DBClasse(DBClasse classe) {
        this.codice=classe.getCodice();
        this.nome=classe.getNome();
        this.numeroTask=classe.getNumeroTask();
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

    @Override
    public String toString() {
        return "DBClasse{" +
                "codice=" + codice +
                ", nome='" + nome + '\'' +
                ", numeroTask=" + numeroTask +
                ", docente=" + docente +
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
