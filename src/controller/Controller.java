package controller;
import dto.DTOClasse;
import dto.DTOConsegna;
import dto.DTOStudente;
import dto.DTOTask;
import database.DBTask;
import dto.DTOUtente;

import java.util.ArrayList;

public class Controller {

    public static int registrazione(String nome,String cognome, String mail, String password){
        return 0;
    }

    public static DTOUtente login(String mail, String password){
        return null;
    }

    public static int creaClasse(String nome){
        return 0;
    }

    public static int iscrizione(String pkStudente, String pkClasse){
        return 0;
    }

    public static ArrayList<DTOStudente> getStudentiSenzaClasse(){
        return null;
    }

    public static ArrayList<DTOClasse> getClassi(String pkDocente){
        return null;
    }

    public static int creaTask(String pkClasse, String titolo, String descrizione, String dataScadenza, int maxPunteggio){
        return 0;
    }

    public static ArrayList<DTOTask> getTasks(String pkClasse){
        return null;
    }

    public static ArrayList<DTOStudente> getStudenti(String pkClasse){
        return null;
    }

    public static ArrayList<DTOConsegna> getConsegna(String pkTask){
        return null;
    }

    public static int valutaConsegna(String pkConsegna, int voto){
        return 0;
    }

    public static ArrayList<Integer> getStatistiche(String pkStudente){
        return null;
    }

    public static ArrayList<DTOStudente> getClassificaPunteggio(String pkClasse){
        return null;
    }

    public static ArrayList<DTOStudente> getClassificaTask(String pkClasse){
        return null;
    }

    public static DTOTask getTask(String pkTask){
        return null;
    }

    public static int consegnaSoluzione(String pkStudente, String pkTask, String soluzione){
        return 0;
    }

    public static int getClasseID(String pkStudente){
        return 0;
    }

}
