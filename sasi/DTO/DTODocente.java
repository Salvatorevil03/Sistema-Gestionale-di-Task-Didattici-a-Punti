package DTO;

import entity.Utente;

public class DTODocente extends DTOUtente {

    public DTODocente(int id, String nome, String cognome, String mail, String password) {
        super(id, nome, cognome, mail, password);
    }

}
