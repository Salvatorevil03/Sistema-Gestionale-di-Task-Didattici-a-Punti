package dto;

public class DTODocente extends DTOUtente {

    public DTODocente(int id, String nome, String cognome, String mail, String password) {
        super(id, nome, cognome, mail, password);
    }

    public DTODocente(DTOUtente dtoUtente) {
        super(dtoUtente.getId(), dtoUtente.getNome(), dtoUtente.getCognome(), dtoUtente.getMail(), dtoUtente.getPassword());
    }
}
