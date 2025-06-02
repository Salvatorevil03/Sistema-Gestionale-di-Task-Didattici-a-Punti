package entity;

import DTO.DTOStudente;
import database.DBPiattaforma;
import database.DBStudente;

import java.util.ArrayList;

public class EntityPiattaforma {

    public static int registrazione(){return -1;}
    public static int login(){return -1;}
    public static ArrayList<DTOStudente> getStudentiSenzaClasse(){
        ArrayList<DTOStudente> lista=new ArrayList<>();
        ArrayList<DBStudente> DBlista = DBPiattaforma.getStudentiSenzaClasse();
        for(int i=0; i<DBlista.size(); i++){
            lista.add(convertiToDTO(DBlista.get(i)));
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
