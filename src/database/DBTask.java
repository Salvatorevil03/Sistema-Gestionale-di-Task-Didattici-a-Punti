package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBTask {

    private int id;
    private String titolo;
    private String descrizione;
    private String dataScadenza;
    private int maxPuntiAssegnabili;
    //private DBClasse classe;
    private ArrayList<DBConsegna> consegne;

    public DBTask() {
        this.titolo = "";
        this.descrizione = "";
        this.dataScadenza = "";
        this.maxPuntiAssegnabili = 0;
        //this.classe = new DBClasse();
        this.consegne = new ArrayList<DBConsegna>();
    }

    public DBTask(int idTask) {
        this.id = idTask;
        //this.classe = new DBClasse();
        this.consegne = new ArrayList<DBConsegna>();
        caricaDaDB();
    }

    public DBTask(DBTask dbTask) {
        this.id = dbTask.getId();
        this.titolo = dbTask.getTitolo();
        this.descrizione = dbTask.getDescrizione();
        this.dataScadenza = dbTask.getDataScadenza();
        this.maxPuntiAssegnabili = dbTask.getMaxPuntiAssegnabili();
        this.consegne=dbTask.getConsegne();
    }

    //CARICAMENTO DA DB
    public void caricaDaDB() {
        //1. definisco la query
        String query = "SELECT * FROM task WHERE id='" + this.id + "';";
        //System.out.println(query); //per debug
        try {
            //2 faccio di query di select
            // - crea la connessione
            // - statement
            ResultSet rs = DBConnectionManager.selectQuery(query);
            if (rs.next()) { //se ho un risultato
                //mi vado a prendere i dati, accedendo tramite il nome dell'attributo-colonna
                this.setTitolo(rs.getString("titolo"));
                this.setDescrizione(rs.getString("descrizione"));
                this.setDataScadenza(rs.getString("dataScadenza"));
                this.setMaxPuntiAssegnabili(rs.getInt("maxPuntiAssegnabili"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
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
    public ArrayList<DBConsegna> getConsegne() {
        return consegne;
    }

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

    //################################################################################
    //METODI PER POSSIBILI CARICAMENTI DI DIPENDENZE
//    public void caricaClasseTaskDaDB(){
//
//        String query = new String("select * from task t join classi c on t.classe_codice = c.codice where t.id = ' "+this.id+"'");
//
//        try{
//
//            ResultSet rs = DBConnectionManager.selectQuery(query);
//
//            while(rs.next()) {
//
//                DBClasse classe = new DBClasse();
//                classe.setCodice(rs.getInt("codice"));
//                classe.setNome(rs.getString("nome"));
//                classe.setNumeroTask(rs.getInt("numeroTask"));
//
//                this.classe = classe;
//
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public void caricaClasseDaDB() {
//        String query1 = "SELECT * FROM TASK T JOIN CLASSI C ON T.classe_codice = C.codice WHERE T.classe_codice=" + this.classe.getCodice() + ";";
//        try {
//            ResultSet rs1 = DBConnectionManager.selectQuery(query1);
//            if (rs1.next()) {
//                DBClasse classe= new DBClasse();
//                classe.setCodice(rs1.getInt("codice"));
//                classe.setNome(rs1.getString("nome"));
//                classe.setNumeroTask(rs1.getInt("numeroTask"));
//
//                //SALVATAGGIO RISULTATO
//                this.classe = classe;
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    public void caricaConsegneDaDB(){
        String query1 = "SELECT * FROM TASK T JOIN CONSEGNE C ON T.id = C.task_id WHERE T.id=" + this.id + ";";
        //System.out.println(query1);
        try {
            ResultSet rs1 = DBConnectionManager.selectQuery(query1);
            while (rs1.next()) {
                DBConsegna consegna= new DBConsegna();
                consegna.setId(rs1.getInt("C.id"));
                consegna.setPunteggio(rs1.getInt("punteggio"));
                consegna.setSoluzione(rs1.getString("soluzione"));

                //SALVATAGGIO RISULTATO
                this.consegne.add(consegna);
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}