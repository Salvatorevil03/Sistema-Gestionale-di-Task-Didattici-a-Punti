package entity;

import dto.DTODocente;
import dto.DTOStudente;
import dto.DTOUtente;
import database.DBDocente;
import database.DBStudente;
import database.DBUtente;

import java.util.ArrayList;

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
            if(utente instanceof DBStudente){
                return new DTOStudente(((DBStudente) utente).getId(), ((DBStudente) utente).getNome(), "","","",0,0,0);
            }else {
                return new DTODocente(((DBDocente) utente).getId(), ((DBDocente) utente).getNome(), "","","");
            }
        }
        return null;

    }

    public static ArrayList<DTOStudente> getStudentiSenzaClasse(){
        ArrayList<DTOStudente> lista = new ArrayList<>();
        ArrayList<DBStudente> DBlista = DBUtente.getStudentiSenzaClasse();
        for (DBStudente dbStudente : DBlista) {
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
