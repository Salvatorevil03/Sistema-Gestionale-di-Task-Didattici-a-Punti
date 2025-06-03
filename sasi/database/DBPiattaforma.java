package database;

import DTO.DTOStudente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBPiattaforma {
    public static DBUtente login(String mail, String password){
        String query = "SELECT id,nome " +
                "FROM studenti " +
                "WHERE mail = '"+mail+"'" +
                "  AND password = '"+password+"'"+";";
        String query2 = "SELECT id,nome " +
                "FROM docenti " +
                "WHERE mail = '"+mail+"'" +
                "  AND password = '"+password+"'"+";";
        System.out.println(query);
        System.out.println(query2);
        try {

            ResultSet rs = DBConnectionManager.selectQuery(query);

            if(rs.next()) {
                DBStudente studente = new DBStudente(rs.getInt("id"),rs.getString("nome"));
                return studente;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        try {

            ResultSet rs = DBConnectionManager.selectQuery(query2);

            if(rs.next()) {
                DBDocente docente = new DBDocente(rs.getInt("id"),rs.getString("nome"));
                return docente;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

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

