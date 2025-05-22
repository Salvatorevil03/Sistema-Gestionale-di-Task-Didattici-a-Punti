package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBTask {

    public int id;
    public String titolo;
    public String descrizione;
    public String dataScadenza;
    public int maxPuntiAssegnabili;
    public DBClasse classe;
    public ArrayList<DBConsegna> consegne;

    public DBTask() {
        this.titolo = "";
        this.descrizione = "";
        this.dataScadenza = "";
        this.maxPuntiAssegnabili = 0;
        this.classe = new DBClasse();
        this.consegne = new ArrayList<DBConsegna>();
    }

    public DBTask(int idTask) {
        this.id = idTask;
        this.classe = new DBClasse();
        this.consegne = new ArrayList<DBConsegna>();
        caricaDaDB();
    }

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

    public void caricaClasseTaskDaDB(){

        String query = new String("select * from task t join classi c on t.classe_codice = c.codice where t.id = ' "+this.id+"'");

        try{

            ResultSet rs = DBConnectionManager.selectQuery(query);

            while(rs.next()) {

                DBClasse classe = new DBClasse();
                classe.setCodice(rs.getInt("codice"));
                classe.setNome(rs.getString("nome"));
                classe.setNumeroTask(rs.getInt("numeroTask"));

                this.classe = classe;

            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void caricaConsegneTaskDaDB(){

        String query = new String("select * from task t join consegne c on c.task_id = t.id where t.id = ' "+this.id+"'");


    }

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

    public int getMaxPuntiAssegnabili() {
        return maxPuntiAssegnabili;
    }

    public void setMaxPuntiAssegnabili(int maxPuntiAssegnabili) {
        this.maxPuntiAssegnabili = maxPuntiAssegnabili;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public DBClasse getClasse() {
        return this.classe;
    }

    public ArrayList<DBConsegna> getConsegne(){
        return this.consegne;
    }
}