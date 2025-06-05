package database;

//NOTA: Cancellare la stampa query in inserisciSuDB

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class DBDocente extends DBUtente {
    private int id;
    private String nome;
    private String cognome;
    private String mail;
    private String password;
    private ArrayList<DBClasse> classi;

    ///Costruttore con la chiave primaria
    public DBDocente(int id){
        this.id=id;
        this.classi=new ArrayList<DBClasse>();
        caricaDaDB();
    }

    ///Costruttore vuoto
    public DBDocente(){
        super();
        this.classi=new ArrayList<DBClasse>();
    }

    public DBDocente(DBDocente docente){
        this.nome=docente.getNome();
        this.cognome=docente.getCognome();
        this.mail=docente.getMail();
        this.password=docente.getPassword();
    }

    ///CARICAMENTO DA DB
    public void caricaDaDB() {
        ///1. definisco la query
        String query = "SELECT * FROM docenti WHERE id='"+this.id+"';";
        try {
            ///2 faccio di query di select
            /// - crea la connessione
            /// - statement
            ResultSet rs = DBConnectionManager.selectQuery(query);
            if(rs.next()) { ///Se ho un risultato
            ///Mi vado a prendere i dati, accedendo tramite il nome dell'attributo-colonna
                this.setNome(rs.getString("nome"));
                this.setCognome(rs.getString("cognome"));
                this.setMail(rs.getString("mail"));
                this.setPassword(rs.getString("password"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    ///GETTER AND SETTER
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
    public void setId(int id) {this.id = id;}
    public ArrayList<DBClasse> getClassi() {
        return classi;
    }

    @Override
    public String toString() {
        return "DBDocente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int creaClasse(String nome, int docente_id) {
       DBClasse classe=new DBClasse();
       classe.setNome(nome);
       classe.setNumeroTask(0);
       int esito=this.inserisciSuDB(classe,docente_id);
       return esito;
    }

    private int inserisciSuDB(DBClasse classe, int docente_id) {
        int esito=1;
        String query="INSERT INTO CLASSI (nome,numeroTask,docente_id) VALUES ('"+classe.getNome()+"',"+classe.getNumeroTask()+","+docente_id+")";
        try{
            DBConnectionManager.updateQuery(query);
        }catch(ClassNotFoundException | SQLException e){
            esito=-1;
        }
        return esito;
    }

    public void caricaClassiDaDB() {
        String query = "SELECT * FROM classi WHERE docente_id='"+this.id+"';";
        try {
            ///2 faccio di query di select
            /// - crea la connessione
            /// - statement
            ResultSet rs = DBConnectionManager.selectQuery(query);
            while(rs.next()) {
                DBClasse classe = new DBClasse();
                classe.setCodice(rs.getInt("codice"));
                classe.setNome(rs.getString("nome"));
                classe.setNumeroTask(rs.getInt("numeroTask"));
                this.classi.add(classe);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public DBDocente(String nome, String cognome, String mail, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.password = password;
    }

    public DBDocente(int id,String nome) {
        this.id=id;
        this.nome = nome;
    }

    public int inserisciSuDB(){
        ///1. definisco la query
        String query = "INSERT INTO docenti (nome, cognome, mail, password) VALUES ('"
                + this.nome + "', '"
                + this.cognome + "', '"
                + this.mail + "', '"
                + this.password + "')";

        System.out.println(query); //per debug
        try {
            return DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


}