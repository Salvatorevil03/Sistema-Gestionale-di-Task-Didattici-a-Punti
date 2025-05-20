package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBClasse {
    private int codice;
    private String nome;
    private int numeroTask;

    public DBClasse() {
        super();
    }

    public DBClasse(int codice) {
        this.codice = codice;
        caricaDaDB();
    }

    public DBClasse(DBClasse classe) {
        this.codice=classe.getCodice();
        this.nome=classe.getNome();
        this.numeroTask=classe.getNumeroTask();
    }
    //GETTER AND SETTER--------------------------------------------

    public int getCodice() {
        return codice;
    }
    public void setCodice(int codice) {
        this.codice = codice;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getNumeroTask() {
        return numeroTask;
    }
    public void setNumeroTask(int numeroTask) {
        this.numeroTask = numeroTask;
    }

    @Override
    public String toString() {
        return "DBClasse{" +
                "codice=" + codice +
                ", nome='" + nome + '\'' +
                ", numeroTask='" + numeroTask + '\'' +
                '}';
    }

    //--------------------------------------------
    public void caricaDaDB(){
        String query= "select * from classi where codice='"+this.codice+"';";
        try {
            ResultSet rs = DBConnectionManager.selectQuery(query);
            if(rs.next()) {
                this.setCodice(rs.getInt("codice"));
                this.setNome(rs.getString("nome"));
                this.setNumeroTask(rs.getInt("numeroTask"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
