package database;

import java.sql.ResultSet;
import java.sql.SQLException;

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

}
