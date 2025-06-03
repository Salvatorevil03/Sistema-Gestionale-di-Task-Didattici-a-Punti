package entity;

import database.DBConsegna;
import database.DBStudente;
import mail.MailSender;

public class EntityConsegna {
    private int id; // PK
    private int punteggio;
    private String soluzione;
    //private EntityTask task;
    //private EntityStudente studente;

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

    public EntityConsegna() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPunteggio() {
        return punteggio;
    }
    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }
    public String getSoluzione() {
        return soluzione;
    }
    public void setSoluzione(String soluzione) {
        this.soluzione = soluzione;
    }

    @Override
    public String toString() {
        return "EntityConsegna{" +
                "id=" + id +
                ", punteggio=" + punteggio +
                ", soluzione='" + soluzione + '\'' +
                '}';
    }

    //########################################################
    //METODI PER POSSIBILI DIPENDENZE
//    public void caricaStudenti(DBConsegna consegna) {
//        EntityStudente studente = new EntityStudente(consegna.getStudente());
//        this.studente=studente;
//    }
//
//    public void caricaTask(DBConsegna consegna) {
//        EntityTask task = new EntityTask(consegna.getTask());
//        this.task=task;
//    }

    //---------------------------------------------------------
    //BUSINESS LOGIC
    public int impostaPunteggio(int punteggio,DBConsegna consegna) {
        this.punteggio = punteggio;
        //DBConsegna consegna = new DBConsegna(id);
        consegna.setPunteggio(punteggio);
        int esito=consegna.salvaInDB();
        return esito;
    }

    private void aggiornaPunteggioTotaleOttenuto(int punteggio,DBStudente studente) {
        studente.setPunteggioTotaleOttenuto(studente.getPunteggioTotaleOttenuto()+punteggio);
    }

    public int aggiornaStatisticheAndInviaMail(int punteggio, DBConsegna consegna,String titolo_task) {
        //DBConsegna consegna = new DBConsegna(this.id);
        DBStudente studente= consegna.caricaStudenteDaDB();
        int esito=0;
        this.aggiornaPunteggioTotaleOttenuto(punteggio,studente);
        this.aggiornaNumTaskValutati(studente);
        esito =studente.salvaInDB(); //salvataggio contemporaneo di entrambi i set
        MailSender.inviaValutazioneTask(studente.getMail(),titolo_task,Integer.toString(punteggio),"La tua consegna è stata valutata.");
        return esito;
    }

    private void aggiornaNumTaskValutati(DBStudente studente) {
        studente.setNumTaskValutati(studente.getNumTaskValutati()+1);
    }

    protected static int creaConsegna(int taskID, String soluzione, int studenteID){
        DBConsegna consegna = new DBConsegna();
        consegna.setPunteggio(-1);
        consegna.setSoluzione(soluzione);
        return consegna.InserisciSuDB(taskID, studenteID);
    }

    protected static int updateNumTaskCompletati(int studenteID){
        return EntityStudente.incrementaNumTaskCompletati(studenteID);
    }

}