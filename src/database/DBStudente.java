package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBStudente {
    private int id; //PK
    private String nome;
    private String cognome;
    private String mail;
    private String password;


    public  DBStudente(int id) {
        this.id = id;
        caricaDaDB();
    }

    public void caricaDaDB() {

        String query = "SELECT * FROM studente WHERE id='"+this.id+"';";
        System.out.println(query); //per debug

        try {

            ResultSet rs = DBConnectionManager.selectQuery(query);

            if(rs.next()) {

                this.setNome(rs.getString("nome"));
                this.setCognome(rs.getString("cognome"));
                this.setMail(rs.getString("mail"));
                this.setPassword(rs.getString("password"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // costruttore di base
    public DBStudente() {
        super();
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
}
