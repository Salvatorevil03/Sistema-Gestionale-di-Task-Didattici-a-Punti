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
        //TODO: this.task
        //CARICAMENTO DELLO STUDENTE A CUI LA CONSEGNA è COLLEGATA
        consegna.caricaStudentiDaDB();
        caricaStudenti(consegna);
    }

    public EntityConsegna(DBConsegna consegna) {
        this.id = consegna.getId();
        this.punteggio = consegna.getPunteggio();
        this.soluzione = consegna.getSoluzione();

        consegna.caricaStudentiDaDB();
    }

    public EntityConsegna() {
    }

    public void caricaStudenti(DBConsegna consegna) {
        EntityStudente studente = new EntityStudente(consegna.getStudente());
        this.studente=studente;

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
