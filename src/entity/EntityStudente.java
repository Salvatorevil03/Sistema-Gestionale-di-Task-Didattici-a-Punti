package entity;

import database.DBClasse;
import database.DBConsegna;
import database.DBStudente;

import java.util.ArrayList;

public class EntityStudente extends Utente{

    private int numTaskCompletati;
    private int numTaskValutati;
    private int punteggioTotaleOttenuto;
    private DBClasse classe;
    private ArrayList<DBConsegna> consegne;

    public EntityStudente(int id) {
        this.id = id;
        DBStudente studente = new DBStudente(id);

        this.nome = studente.getNome();
        this.cognome = studente.getCognome();
        this.mail = studente.getMail();
        this.password = studente.getPassword();
        this.numTaskCompletati = studente.getNumTaskCompletati();
        this.numTaskValutati = studente.getNumTaskValutati();
        this.punteggioTotaleOttenuto = studente.getPunteggioTotaleOttenuto();
    }

    public EntityStudente(DBStudente studente) {
        this.id = studente.getId();
        this.nome = studente.getNome();
        this.cognome = studente.getCognome();
        this.mail = studente.getMail();
        this.password = studente.getPassword();
        this.numTaskCompletati = studente.getNumTaskCompletati();
        this.numTaskValutati = studente.getNumTaskValutati();
        this.punteggioTotaleOttenuto = studente.getPunteggioTotaleOttenuto();
    }

    @Override
    public String toString() {
        return "EntityStudente{" +
                "numTaskCompletati=" + numTaskCompletati +
                ", numTaskValutati=" + numTaskValutati +
                ", punteggioTotaleOttenuto=" + punteggioTotaleOttenuto +
                ", classe=" + classe +
                ", consegne=" + consegne +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
