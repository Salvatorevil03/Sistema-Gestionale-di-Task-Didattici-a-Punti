package database;

import entity.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBStudente extends DBUtente {
    private int id; //PK
    private int numTaskCompletati;
    private int numTaskValutati;
    private int punteggioTotaleOttenuto;
    //private DBClasse classe;
    //private ArrayList<DBConsegna> consegne;

    // costruttore vuoto
    // contruttore con PK

    public DBStudente() {
        super();
        //this.consegne = new ArrayList<DBConsegna>();
    }

    public  DBStudente(int id) {
        this.id = id;
        //this.consegne = new ArrayList<DBConsegna>();
        caricaDaDB();
    }

    public DBStudente(DBStudente studente) {
        this.id = studente.getId();
        this.nome = studente.getNome();
        this.cognome = studente.getCognome();
        this.mail = studente.getMail();
        this.password = studente.getPassword();
        this.numTaskCompletati = studente.getNumTaskCompletati();
        this.numTaskValutati = studente.getNumTaskValutati();
        this.punteggioTotaleOttenuto = studente.getPunteggioTotaleOttenuto();
    }

    //CARICAMENTO DA DB
    public void caricaDaDB() {

        String query = "SELECT * FROM studenti WHERE id='"+this.id+"';";

        try {

            ResultSet rs = DBConnectionManager.selectQuery(query);

            if(rs.next()) {

                this.setNome(rs.getString("nome"));
                this.setCognome(rs.getString("cognome"));
                this.setMail(rs.getString("mail"));
                this.setPassword(rs.getString("password"));
                this.setNumTaskCompletati(rs.getInt("numTaskCompletati"));
                this.setNumTaskValutati(rs.getInt("numTaskValutati"));
                this.setPunteggioTotaleOttenuto(rs.getInt("punteggioTotaleOttenuto"));
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
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getNumTaskCompletati() {
        return numTaskCompletati;
    }
    public void setNumTaskCompletati(int numTaskCompletati) {
        this.numTaskCompletati = numTaskCompletati;
    }
    public int getNumTaskValutati() {
        return numTaskValutati;
    }
    public void setNumTaskValutati(int numTaskValutati) {
        this.numTaskValutati = numTaskValutati;
    }
    public int getPunteggioTotaleOttenuto() {
        return punteggioTotaleOttenuto;
    }
    public void setPunteggioTotaleOttenuto(int punteggioTotaleOttenuto) {
        this.punteggioTotaleOttenuto = punteggioTotaleOttenuto;
    }

    @Override
    public String toString() {
        return "DBStudente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", numTaskCompletati=" + numTaskCompletati +
                ", numTaskValutati=" + numTaskValutati +
                ", punteggioTotaleOttenuto=" + punteggioTotaleOttenuto +
                '}';
    }

    public int salvaInDB() {
        int ret=0;
        String query = "UPDATE studenti SET punteggioTotaleOttenuto = "+this.punteggioTotaleOttenuto+ ",numTaskValutati= "+this.numTaskValutati+" WHERE id= "+this.id+";";
        try{
            ret=DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            ret=-1;
        }
        return ret;
    }

    public int getCodiceClasse() {
        String query="SELECT * FROM STUDENTI WHERE id='"+this.id+"';";
        int codice_classe=-1;
        try {
            ResultSet rs = DBConnectionManager.selectQuery(query);
            if(rs.next()) {
                codice_classe=rs.getInt("classe_codice");
            }
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
        return codice_classe;
    }

    //############################################################################
    //POSSIBILI METODI PER CARICAMENTO DI DIPENDENZE
//
//    public void caricaClasseStudenteDaDB(){
//
//        String query = "SELECT c.codice, c.nome, c.numeroTask FROM studenti s JOIN classi c ON s.classe_codice = c.codice WHERE s.id = '"+this.id+"';";
//        System.out.println(query); //per debug
//
//        try {
//
//            ResultSet rs = DBConnectionManager.selectQuery(query);
//
//            if(rs.next()) {
//
//                DBClasse classe = new DBClasse();
//                classe.setCodice(rs.getInt("codice"));
//                classe.setNome(rs.getString("nome"));
//                classe.setNumeroTask(rs.getInt("numeroTask"));
//
//                this.setClasse(classe);
//            }
//
//        } catch (ClassNotFoundException|SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void caricaConsegneStudenteDaDB(){
//
//        String query = "SELECT * FROM consegne WHERE studente_id='"+this.id+"';";
//        System.out.println(query); //per debug
//
//        try {
//
//            ResultSet rs = DBConnectionManager.selectQuery(query);
//
//            while(rs.next()) {
//
//                DBConsegna consegna = new DBConsegna();
//                consegna.setId(rs.getInt("id"));
//                consegna.setPunteggio(rs.getInt("punteggio"));
//                consegna.setSoluzione(rs.getString("soluzione"));
//                this.consegne.add(consegna);
//            }
//
//        } catch (ClassNotFoundException|SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public int salvaTaskCompletatiInDB() {
        int ret=0;
        String query = "UPDATE studenti SET numTaskCompletati = "+this.numTaskCompletati+ " WHERE id= "+this.id+";";
        try{
            ret=DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            //e.printStackTrace();
            ret=-1;
        }
        return ret;
    }

    //SASI
    public DBStudente(String nome, String cognome, String mail, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.password = password;
        this.numTaskCompletati = 0;
        this.numTaskValutati = 0;
        this.punteggioTotaleOttenuto = 0;
    }

    public DBStudente(int id,String nome) {
        this.id=id;
        this.nome = nome;
    }

    public int inserisciSuDB(){
        int ret=0;
        String query = "INSERT INTO studenti (nome, cognome, mail, password) VALUES ('"
                + this.nome + "', '"
                + this.cognome + "', '"
                + this.mail + "', '"
                + this.password + "')";
        try{
            ret=DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            ret=-1;
        }
        return ret;
    }

}