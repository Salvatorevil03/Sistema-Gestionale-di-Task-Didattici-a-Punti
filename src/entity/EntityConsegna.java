package entity;

import database.DBConsegna;
import database.DBStudente;
import database.DBTask;

public class EntityConsegna {
    private int id; // PK
    private int punteggio;
    private String soluzione;
    private EntityTask task;
    private EntityStudente studente;

    public EntityConsegna(int id) {
        this.id = id;
        DBConsegna consegna = new DBConsegna(id);

        this.punteggio = consegna.getPunteggio();
        this.soluzione = consegna.getSoluzione();
    }

    public EntityConsegna(DBConsegna consegna) {
        this.id = consegna.getId();
        this.punteggio = consegna.getPunteggio();
        this.soluzione = consegna.getSoluzione();
    }

    @Override
    public String toString() {
        return "EntityConsegna{" +
                "id=" + id +
                ", punteggio=" + punteggio +
                ", soluzione='" + soluzione + '\'' +
                ", task=" + task +
                ", studente=" + studente +
                '}';
    }
}
