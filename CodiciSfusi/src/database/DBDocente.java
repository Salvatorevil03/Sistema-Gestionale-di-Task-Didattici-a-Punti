package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBDocente {
    private int id;
    private String nome;
    private String cognome;
    private String mail;
    private String password;

    //costruttore con la chiave primaria
    public DBDocente(int id){
        this.id=id;
        caricaDaDB();
    }

    //costruttore vuoto
    public DBDocente(){
        super();
    }

    //GETTER AND SETTER
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getMail() {return mail;}
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPassword() {return password;}
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getId() {return id;}

    public void caricaDaDB() {
        //1. definisco la query
        String query = "SELECT * FROM docenti WHERE id='"+this.id+"';";
        //System.out.println(query); //per debug
        try {
            //2 faccio di query di select
            // - crea la connessione
            // - statement
            ResultSet rs = DBConnectionManager.selectQuery(query);
            if(rs.next()) { //se ho un risultato
                //mi vado a prendere i dati, accedendo tramite il nome dell'attributo-colonna
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
}
