package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBDocente {
    private int id;
    private String nome;
    private String cognome;
    private String mail;
    private String password;
    private ArrayList<DBClasse> classi;

    //costruttore con la chiave primaria
    public DBDocente(int id){
        this.id=id;
        this.classi=new ArrayList<DBClasse>();
        caricaDaDB();
    }

    //costruttore vuoto
    public DBDocente(){
        super();
        this.classi=new ArrayList<DBClasse>();
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
    public void setId(int id) {this.id = id;}
    public ArrayList<DBClasse> getClassi() {
        return classi;
    }
    public void setClassi(ArrayList<DBClasse> classi) {
        this.classi = classi;
    }

    @Override
    public String toString() {
        return "DBDocente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", classi=" + classi +
                '}';
    }

    //--------------------------------------------------
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

    public void caricaClassiDaDB() {
        String query = "SELECT * FROM classi WHERE docente_id='"+this.id+"';";
        //System.out.println(query);
        try {
            //2 faccio di query di select
            // - crea la connessione
            // - statement
            ResultSet rs = DBConnectionManager.selectQuery(query);
            while(rs.next()) {
                DBClasse classe = new DBClasse();
                classe.setCodice(rs.getInt("codice"));
                classe.setNome(rs.getString("nome"));
                classe.setNumeroTask(rs.getInt("numeroTask"));
                this.classi.add(classe);
            }

        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
