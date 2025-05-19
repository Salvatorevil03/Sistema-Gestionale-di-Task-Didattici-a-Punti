package entity;

import database.DBStudente;

public class EntityStudente extends Utente{
    public EntityStudente(int id) {
        this.id = id;
        DBStudente studente = new DBStudente(id);

        this.nome = studente.getNome();
        this.cognome = studente.getCognome();
        this.mail = studente.getMail();
        this.password = studente.getPassword();
    }

    public EntityStudente(DBStudente studente) {
        this.id = studente.getId();
        this.nome = studente.getNome();
        this.cognome = studente.getCognome();
        this.mail = studente.getMail();
        this.password = studente.getPassword();
    }



    @Override
    public String toString() {
        return "EntityStudente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
