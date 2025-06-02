package entity;

import database.DBDocente;
import database.DBPiattaforma;
import database.DBStudente;
import database.DBUtente;
import dto.DTODocente;
import dto.DTOStudente;
import dto.DTOUtente;

public class Piattaforma {

    public static int registrazione(String ruolo,String nome,String cognome, String mail, String password){
        String ruoloLower = ruolo.toLowerCase();
        if(ruoloLower.equals("docente")) {
            EntityDocente docente = new EntityDocente(nome,cognome,mail,password);
            return docente.inserisciSuDB();
        }else if (ruoloLower.equals("studente")) {
            EntityStudente studente = new EntityStudente(nome,cognome,mail,password);
            return studente.inserisciSuDB();
        }
        return -1;
    }

    public static DTOUtente login(String mail, String password){
       DBUtente utente = DBPiattaforma.login(mail,password);
       if(utente != null){
           if(utente instanceof DBStudente){
               DTOStudente studente = new DTOStudente(((DBStudente) utente).getId(), ((DBStudente) utente).getNome(), "","","",0,0,0);
               return studente;
           }else {
               DTODocente docente = new DTODocente(((DBDocente) utente).getId(), ((DBDocente) utente).getNome(), "","","");
               return docente;
           }
       }
       return null;
    }

}
