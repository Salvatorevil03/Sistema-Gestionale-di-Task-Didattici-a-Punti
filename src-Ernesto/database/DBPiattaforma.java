package database;

import DTO.DTOStudente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBPiattaforma {
    public static int registrazione(){return -1;}
    public static int login(){return -1;}

    public static ArrayList<DBStudente> getStudentiSenzaClasse(){
        ArrayList<DBStudente> lista=new ArrayList<>();
        String query = "SELECT * FROM taskdidattici.studenti WHERE classe_codice IS NULL;";
        preleva(query,lista);
        return lista;
    }

    private static void preleva(String query, ArrayList<DBStudente> lista){
        try {
            ResultSet rs = DBConnectionManager.selectQuery(query);
            while(rs.next()) {
                DBStudente studente = new DBStudente();
                studente.setId(rs.getInt("id"));
                studente.setNome(rs.getString("nome"));
                studente.setCognome(rs.getString("cognome"));
                studente.setMail(rs.getString("mail"));
                studente.setPassword(rs.getString("password"));
                studente.setNumTaskValutati(rs.getInt("numTaskValutati"));
                studente.setNumTaskCompletati(rs.getInt("numTaskCompletati"));
                studente.setPunteggioTotaleOttenuto(rs.getInt("punteggioTotaleOttenuto"));
                lista.add(studente);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    }

