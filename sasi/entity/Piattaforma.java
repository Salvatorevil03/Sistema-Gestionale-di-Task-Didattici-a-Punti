package entity;

import database.DBPiattaforma;

public class Piattaforma {

    public static int registrazione(String ruolo,String nome,String cognome, String mail, String password){
        String ruoloLower = ruolo.toLowerCase();
        if(ruoloLower.equals("docente")) {
            //creo docente entiny cion costruttore nuovo, anche db
            //metodo salva stato (chiama salva stato di db che fa insert)
        }else {

        }
        return -1;
    }

    public static int login(){
        return -1;
    }

}
