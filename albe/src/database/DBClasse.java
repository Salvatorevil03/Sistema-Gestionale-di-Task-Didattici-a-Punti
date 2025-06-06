package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBClasse {
    private int codice;
    private String nome;
    private int numeroTask;
    private ArrayList<DBStudente> studenti;
    private ArrayList<DBTask> task;


    public DBClasse() {
        super();
        this.studenti = new ArrayList<>();
        this.task = new ArrayList<>();
    }

    public DBClasse(int codice) {
        this.codice = codice;
        this.studenti = new ArrayList<>();
        this.task=new ArrayList<>();
        caricaDaDB();
    }

    public DBClasse(DBClasse classe) {
        this.codice=classe.getCodice();
        this.nome=classe.getNome();
        this.numeroTask=classe.getNumeroTask();
        this.studenti=classe.studenti;
        this.task=classe.task;
    }

    ///CARICAMENTO DATI DAL DB
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
            e.printStackTrace();
        }
    }

    ///GETTER AND SETTER--------------------------------------------

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
    public List<DBTask> getTask() {
        return task;
    }
    public List<DBStudente> getStudenti() {
        return studenti;
    }

    ///METODO TO STRING
    @Override
    public String toString() {
        return "DBClasse{" +
                "codice=" + codice +
                ", nome='" + nome + '\'' +
                ", numeroTask=" + numeroTask +
                '}';
    }

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
            e.printStackTrace();
        }
    }

    public void caricaTaskDaDB() {

        String query1 = "SELECT * FROM TASK T JOIN CLASSI C ON T.classe_codice=C.codice WHERE C.codice=" + this.codice + ";";
        try {
            ResultSet rs1 = DBConnectionManager.selectQuery(query1);
            while(rs1.next()) {
                DBTask taskToBeLoad = new DBTask();
                taskToBeLoad.setId(rs1.getInt("id"));
                taskToBeLoad.setTitolo(rs1.getString("titolo"));
                taskToBeLoad.setDescrizione(rs1.getString("descrizione"));
                taskToBeLoad.setMaxPuntiAssegnabili(rs1.getInt("maxPuntiAssegnabili"));
                taskToBeLoad.setDataScadenza(rs1.getString("dataScadenza"));

                ///SALVATAGGIO RISULTATO
                this.task.add(taskToBeLoad);
            }
            rs1.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public int creaTask(String titolo, String descrizione, String dataScadenza, int maxPunteggio) {
        DBTask taskToBeCreate = new DBTask();
        taskToBeCreate.setTitolo(titolo);
        taskToBeCreate.setDescrizione(descrizione);
        taskToBeCreate.setDataScadenza(dataScadenza);
        taskToBeCreate.setMaxPuntiAssegnabili(maxPunteggio);
        return this.inserisciSuDB(taskToBeCreate);
    }

    private int inserisciSuDB(DBTask task) {
        int esito;
        String query="INSERT INTO TASK (titolo,descrizione,dataScadenza,maxPuntiAssegnabili,classe_codice) VALUES ('" + task.getTitolo() + "','" +
                task.getDescrizione() + "','" + task.getDataScadenza() + "'," +
                task.getMaxPuntiAssegnabili()+","+this.codice+ ")";

        try{
            esito = DBConnectionManager.updateQuery(query);
            if (esito == 1){
                this.setNumeroTask(this.numeroTask+1);
                this.salvaNumTaskSuDB();
            }
        }catch(ClassNotFoundException | SQLException e){
            esito = -1;
        }
        return esito;
    }

    private int salvaNumTaskSuDB() {
        int ret;
        String query = "UPDATE classi SET numeroTask = "+this.numeroTask+" WHERE codice= "+this.codice+";";
        try{
            ret = DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            ret = -1;
        }
        return ret;
    }

    public int iscrizioneSuDB(int idStudente, int idClasse) {
        int ret=0;
        String query = "UPDATE studenti SET classe_codice = "+idClasse+" WHERE id= "+idStudente+";";
        try{
            ret=DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            ret=-1;
        }
        return ret;
    }

}