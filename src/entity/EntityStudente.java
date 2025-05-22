package entity;

import database.DBClasse;
import database.DBConsegna;
import database.DBStudente;

import java.util.ArrayList;

public class EntityStudente extends Utente{

    private int numTaskCompletati;
    private int numTaskValutati;
    private int punteggioTotaleOttenuto;
    private EntityClasse classe;
    private ArrayList<EntityConsegna> consegne;

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

        studente.caricaClasseStudenteDaDB();
        caricaClasse(studente);

        this.consegne = new ArrayList<EntityConsegna>();
        studente.caricaConsegneStudenteDaDB();
        caricaConsegne(studente);

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

    public void caricaClasse(DBStudente studente){
        EntityClasse classe = new EntityClasse(studente.getClasse());
        this.setClasse(classe);
    }

    public void caricaConsegne(DBStudente studente){
        for(int i=0;i<studente.getConsegne().size();i++) {

            EntityConsegna consegna = new EntityConsegna(studente.getConsegne().get(i));
            this.consegne.add(consegna);
        }
    }

    public int getNumTaskCompletati() {
        return numTaskCompletati;
    }

    public void setNumTaskCompletati(int numTaskCompletati) {
        this.numTaskCompletati = numTaskCompletati;
    }

    public int getNumTaskValutati() {
        return numTaskValutati;
    }

    public void setNumTaskValutati(int numTaskValutati) {
        this.numTaskValutati = numTaskValutati;
    }

    public int getPunteggioTotaleOttenuto() {
        return punteggioTotaleOttenuto;
    }

    public void setPunteggioTotaleOttenuto(int punteggioTotaleOttenuto) {
        this.punteggioTotaleOttenuto = punteggioTotaleOttenuto;
    }

    public EntityClasse getClasse() {
        return classe;
    }

    public void setClasse(EntityClasse classe) {
        this.classe = classe;
    }

    public ArrayList<EntityConsegna> getConsegne() {
        return consegne;
    }

    public void setConsegne(ArrayList<EntityConsegna> consegne) {
        this.consegne = consegne;
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
