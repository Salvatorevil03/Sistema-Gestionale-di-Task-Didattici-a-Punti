package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBStudente extends DBUtente {
    private int id; ///PK
    private int numTaskCompletati;
    private int numTaskValutati;
    private int punteggioTotaleOttenuto;

    ///Costruttore vuoto
    public DBStudente() {
        super();
    }

    ///Contruttore con PK
    public  DBStudente(int id) {
        this.id = id;
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

    ///CARICAMENTO DA DB
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

    ///GETTER E SETTER
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
            ret=-1;
        }
        return ret;
    }

    public int getCodiceClasse() {
        String query="SELECT * FROM STUDENTI WHERE id='"+this.id+"';";
        int codiceClasse=-1;
        try {
            ResultSet rs = DBConnectionManager.selectQuery(query);
            if(rs.next()) {
                codiceClasse=rs.getInt("classe_codice");
            }
        } catch (ClassNotFoundException|SQLException e) {
            return -1;
        }
        return codiceClasse;
    }

    public int salvaTaskCompletatiInDB() {
        int ret=0;
        String query = "UPDATE studenti SET numTaskCompletati = "+this.numTaskCompletati+ " WHERE id= "+this.id+";";
        try{
            ret=DBConnectionManager.updateQuery(query);
        } catch (ClassNotFoundException | SQLException e) {
            ret=-1;
        }
        return ret;
    }

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