package entity;

import dto.DTODocente;
import dto.DTOStudente;
import dto.DTOUtente;
import database.DBDocente;
import database.DBStudente;
import database.DBUtente;

import java.util.ArrayList;
import java.util.List;

public class Piattaforma {

    private Piattaforma() {}

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
        DBUtente utente = DBUtente.login(mail,password);
        if(utente != null){

            if(utente instanceof DBStudente dbStudente){
                DTOUtente dtoUtente = new DTOUtente(dbStudente.getId(), dbStudente.getNome(), "","","");
                return new DTOStudente(dtoUtente,0,0,0);
            }else if(utente instanceof DBDocente dbDocente) {
                return new DTODocente(dbDocente.getId(), dbDocente.getNome(), "","","");
            }
        }
        return null;

    }

    public static List<DTOStudente> getStudentiSenzaClasse(){
        ArrayList<DTOStudente> lista = new ArrayList<>();

        for (DBStudente dbStudente : DBUtente.getStudentiSenzaClasse()) {
            lista.add(convertiToDTO(dbStudente));
        }
        return lista;
    }

    private static DTOStudente convertiToDTO(DBStudente dbStudente) {
        DTOStudente studente=new DTOStudente();
        studente.setId(dbStudente.getId());
        studente.setNome(dbStudente.getNome());
        studente.setCognome(dbStudente.getCognome());
        studente.setMail(dbStudente.getMail());
        studente.setPassword(dbStudente.getPassword());
        studente.setNumTaskCompletati(dbStudente.getNumTaskCompletati());
        studente.setPunteggioTotaleOttenuto(dbStudente.getPunteggioTotaleOttenuto());
        studente.setNumTaskValutati(dbStudente.getNumTaskValutati());
        return studente;
    }
}
