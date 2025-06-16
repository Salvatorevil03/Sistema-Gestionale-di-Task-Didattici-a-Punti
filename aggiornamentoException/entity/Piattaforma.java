package entity;

import dto.*;
import database.DBDocente;
import database.DBStudente;
import database.DBUtente;

import java.util.ArrayList;
import java.util.List;
import exceptions.*;

public class Piattaforma {

    private Piattaforma() {
    }

    public static void registrazione(String ruolo, String nome, String cognome, String mail, String password) {
        String ruoloLower = ruolo.toLowerCase();
        if (ruoloLower.equals("docente")) {
            EntityDocente docente = new EntityDocente(nome, cognome, mail, password);
            docente.inserisciSuDB();
        } else if (ruoloLower.equals("studente")) {
            EntityStudente studente = new EntityStudente(nome, cognome, mail, password);
            studente.inserisciSuDB();
        } else {
            throw new RoleNotValidException();
        }
    }

    public static DTOUtente login(String mail, String password) {
        DBUtente utente = DBUtente.login(mail, password);
        if (utente != null) {

            if (utente instanceof DBStudente dbStudente) {
                DTOUtente dtoUtente = new DTOUtente(dbStudente.getId(), dbStudente.getNome(), "", "", "");
                return new DTOStudente(dtoUtente, 0, 0, 0);
            } else if (utente instanceof DBDocente dbDocente) {
                return new DTODocente(dbDocente.getId(), dbDocente.getNome(), "", "", "");
            }
        }
        return null;

    }

    public static List<DTOStudente> getStudentiSenzaClasse() {
        ArrayList<DTOStudente> lista = new ArrayList<>();

        for (DBStudente dbStudente : DBUtente.getStudentiSenzaClasse()) {
            lista.add(convertiToDTO(dbStudente));
        }
        return lista;
    }

    private static DTOStudente convertiToDTO(DBStudente dbStudente) {
        DTOStudente studente = new DTOStudente();
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
    ///testo da togliere, metodi aggiunti per facade
    public static void valutaConsegna(int consegnaID, int taskID, int voto) {
        EntityTask task = new EntityTask(taskID);
        task.valutaConsegna(consegnaID, voto);
    }

    public static void creaClasse(int docenteID, String nome) {
        EntityDocente docente = new EntityDocente(docenteID);
        docente.creaClasse(nome);
    }

    public static void iscrizioneClasse(int studenteID, int classeID) {
        EntityClasse classe = new EntityClasse();
        classe.iscrizione(studenteID, classeID);
    }

    public static List<DTOClasse> getElencoClassi(int docenteID) {
        EntityDocente docente = new EntityDocente(docenteID);
        return docente.getElencoClassi();
    }

    public static void creaTask(int classeID, String titolo, String descrizione, String dataScadenza, int maxPunteggio) {
        EntityClasse classe = new EntityClasse(classeID);
        classe.creaTask(titolo, descrizione, dataScadenza, maxPunteggio);
    }

    public static List<DTOTask> getTasks(int classeID) {
        EntityClasse classe = new EntityClasse(classeID);
        return classe.getTasks();
    }

    public static List<DTOStudente> getStudenti(int classeID){
        EntityClasse classe = new EntityClasse(classeID);
        return classe.getStudenti();
    }

    public static List<DTOConsegna> getConsegne(int taskID){
        EntityTask task = new EntityTask(taskID);
        return task.getConsegne();
    }

    public static String getNomeStudente(int studenteID){
        EntityStudente studente = new EntityStudente(studenteID);
        return studente.getNome();
    }

    public static List<Integer> getStatisticheStudente(int studenteID){
        EntityStudente studente = new EntityStudente(studenteID);
        return studente.getStatistiche();
    }

    public static DTOTask getInfoTask(int taskID){
        EntityTask task = new EntityTask(taskID);
        return task.getInfo();
    }

    public static void consegnaSoluzione(int taskID, String soluzione, int studenteID){
        EntityTask task = new EntityTask(taskID);
        task.consegnaSoluzione(taskID, soluzione, studenteID);
    }

    public static int getCodiceClasse(int studenteID){
        EntityStudente studente = new EntityStudente(studenteID);
        return studente.getCodiceClasse();
    }

}
