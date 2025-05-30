package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBStudente {
    private int id; //PK
    private String nome;
    private String cognome;
    private String mail;
    private String password;
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

}