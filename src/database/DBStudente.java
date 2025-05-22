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
    private DBClasse classe;
    private ArrayList<DBConsegna> consegne;

    // costruttore vuoto
    // contruttore con PK

    public DBStudente() {
        super();
        this.consegne = new ArrayList<DBConsegna>();
    }

    public  DBStudente(int id) {
        this.id = id;
        this.consegne = new ArrayList<DBConsegna>();
        caricaDaDB();
    }

    public void caricaDaDB() {

        String query = "SELECT * FROM studenti WHERE id='"+this.id+"';";
        System.out.println(query); //per debug

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

    public void caricaClasseStudenteDaDB(){

        String query = "SELECT c.codice, c.nome, c.numeroTask FROM studenti s JOIN classi c ON s.classe_codice = c.codice WHERE s.id = '"+this.id+"';";
        System.out.println(query); //per debug

        try {

            ResultSet rs = DBConnectionManager.selectQuery(query);

            if(rs.next()) {

                DBClasse classe = new DBClasse();
                classe.setCodice(rs.getInt("codice"));
                classe.setNome(rs.getString("nome"));
                classe.setNumeroTask(rs.getInt("numeroTask"));

                this.setClasse(classe);
            }

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }

    public void caricaConsegneStudenteDaDB(){

        String query = "SELECT * FROM consegne WHERE studente_id='"+this.id+"';";
        System.out.println(query); //per debug

        try {

            ResultSet rs = DBConnectionManager.selectQuery(query);

            while(rs.next()) {

                DBConsegna consegna = new DBConsegna();
                consegna.setId(rs.getInt("id"));
                consegna.setPunteggio(rs.getInt("punteggio"));
                consegna.setSoluzione(rs.getString("soluzione"));
                this.consegne.add(consegna);
            }

        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }


    // set e get
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

    public DBClasse getClasse() {
        return classe;
    }

    public void setClasse(DBClasse classe) {
        this.classe = classe;
    }

    public ArrayList<DBConsegna> getConsegne() {
        return consegne;
    }

    public void setConsegne(ArrayList<DBConsegna> consegne) {
        this.consegne = consegne;
    }
}
