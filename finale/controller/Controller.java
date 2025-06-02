package controller;
import DTO.DTOClasse;
import DTO.DTOConsegna;
import DTO.DTOStudente;
import DTO.DTOTask;
import database.DBTask;
import DTO.DTOUtente;
import entity.*;

import java.util.ArrayList;
import java.util.Comparator;

public class Controller {

    public static int registrazione(String ruolo,String nome,String cognome, String mail, String password){
        return Piattaforma.registrazione(ruolo,nome,cognome,mail,password);
    }

    public static DTOUtente login(String mail, String password){
        return Piattaforma.login(mail,password);
    }

    public static int creaClasse(String nome, String pkDocente){
        //UNICO PROBLEMA è LA FORMATTAZIONE DEI DATI, ' O " CAUSANO PROBL. CHE SI RISOLVONO BENE SOLO CON PREPARED STATEMENT
        if(nome==""){return -1;}
        int id_docente=0;
        try{
            id_docente=Integer.parseInt(pkDocente);
        }catch(NumberFormatException e){
            return -1;
        }
        if(id_docente<=0){return -1;}
        EntityDocente docente = new EntityDocente(id_docente);
        int esito=docente.creaClasse(nome);
        return esito;
    }

    public static int iscrizione(String pkStudente, String pkClasse){
        int id_studente=0;
        int id_classe=0;
        int esito=0;
        try{
            id_studente=Integer.parseInt(pkStudente);
            id_classe=Integer.parseInt(pkClasse);
        } catch (NumberFormatException e) {
            esito=-1;
        }
        EntityClasse classe=new EntityClasse();
        esito=classe.iscrizione(id_studente,id_classe);
        return esito;
    }

    public static ArrayList<DTOStudente> getStudentiSenzaClasse(){
        ArrayList<DTOStudente> lista= Piattaforma.getStudentiSenzaClasse();
        return lista;
    }

    public static ArrayList<DTOClasse> getClassi(String pkDocente){
        //sempre da gui controllo non sia null
        int id_docente=0;
        try {
            id_docente = Integer.parseInt(pkDocente);
        }catch (NumberFormatException e){
            return null;
        }
        if(id_docente<=0){return null;}
        EntityDocente docente = new EntityDocente(id_docente);
        ArrayList<DTOClasse> DTOclassi=docente.getElencoClassi();
        return DTOclassi;
    }

    public static int creaTask(String pkClasse, String titolo, String descrizione, String dataScadenza, int maxPunteggio){
        //UNICO PROBLEMA è LA FORMATTAZIONE DEI DATI, ' O " CAUSANO PROBL. CHE SI RISOLVONO BENE SOLO CON PREPARED STATEMENT
        int id_classe=0;
        try{
            id_classe = Integer.parseInt(pkClasse);
        }catch (NumberFormatException e){
            return -1;
        }
        if(id_classe<=0){return -1;}
        if(maxPunteggio<0){return -1;}
        if(titolo=="" || descrizione=="" || dataScadenza==""){return -1;}
        EntityClasse classe = new EntityClasse(id_classe);
        int esito=classe.creaTask(titolo,descrizione,dataScadenza,maxPunteggio,id_classe);
        return esito;
    }

    public static ArrayList<DTOTask> getTasks(String pkClasse){
        //sempre da gui controllo non sia null
        int id_classe=0;
        try {
             id_classe = Integer.parseInt(pkClasse);
        }catch (NumberFormatException e){
            return null;
        }
        if(id_classe<=0){return null;}
        EntityClasse classe = new EntityClasse(id_classe);
        ArrayList<DTOTask> DTOTasks=classe.getTasks();
        return DTOTasks;
    }

    public static ArrayList<DTOStudente> getStudenti(String pkClasse){
        //sempre da gui controllo non sia null
        int id_classe=0;
        try {
            id_classe = Integer.parseInt(pkClasse);
        }catch (NumberFormatException e){
            return null;
        }
        if(id_classe<=0){return null;}
        EntityClasse classe = new EntityClasse(id_classe);
        ArrayList<DTOStudente> DTOStudenti=classe.getStudenti();
        return DTOStudenti;
    }

    public static ArrayList<DTOConsegna> getConsegne(String pkTask){
        //sempre da gui controllo non sia null
        int id_Task=0;
        try {
            id_Task = Integer.parseInt(pkTask);
        }catch (NumberFormatException e){
            return null;
        }
        if(id_Task<=0){return null;}
        EntityTask task = new EntityTask(id_Task);
        ArrayList<DTOConsegna> DTOconsegne=task.getConsegne();
        return DTOconsegne;
    }

    public static int valutaConsegna(String pkTask, String pkConsegna, int voto){
        // if(pkTask==null || pkConsegna==null) {return -1;} nella GUI anche questo?
        int id_task=0;
        int id_consegna=0;
        try {
            id_task = Integer.parseInt(pkTask);
            id_consegna = Integer.parseInt(pkConsegna);
        }catch (NumberFormatException e){
            return -1;
        }
        if(id_task<=0 || id_consegna<=0 || voto<=0){ return -1;}
        EntityTask task= new EntityTask(id_task);
        int esito= task.valutaConsegna(id_consegna,voto);
        return esito;
    }

    public static ArrayList<Integer> getStatistiche(String pkStudente){
        int id_studente=0;
        try{
            id_studente = Integer.parseInt(pkStudente);
        }catch (NumberFormatException e){
            return null;
        }
        if(id_studente<=0){return null;}
        EntityStudente studente = new EntityStudente(id_studente);
        if (studente.getNome()==null){return null;}
        ArrayList<Integer> lista=studente.getStatistiche();
        return lista;
    }

    public static ArrayList<DTOStudente> getClassificaPunteggio(String pkClasse){
        int id_classe=0;
        try{
            id_classe = Integer.parseInt(pkClasse);
        }catch (NumberFormatException e){
            return null;
        }
        if(id_classe<=0){return null;}
        EntityClasse classe = new EntityClasse(id_classe);
        ArrayList<DTOStudente> lista=classe.getStudenti();
        lista.sort(Comparator.comparingInt(DTOStudente::getPunteggioTotaleOttenuto).reversed());
        return lista;
    }

    public static ArrayList<DTOStudente> getClassificaTask(String pkClasse){
        //classifica basata sul numTaskCompletati
        int id_classe=0;
        try{
            id_classe = Integer.parseInt(pkClasse);
        }catch (NumberFormatException e){
            return null;
        }
        if(id_classe<=0){return null;}
        EntityClasse classe = new EntityClasse(id_classe);
        ArrayList<DTOStudente> lista=classe.getStudenti();
        lista.sort(Comparator.comparingInt(DTOStudente::getNumTaskCompletati).reversed());
        return lista;
    }

    public static DTOTask getTask(String pkTask){
        //STAMPARE INFORMAZIONI DEL TASK
        int id_task=0;
        try{
            id_task = Integer.parseInt(pkTask);
        }catch (NumberFormatException e){
            return null;
        }
        if(id_task<=0){return null;}
        EntityTask task = new EntityTask(id_task);
        DTOTask dtoTask=task.getInfo();
        if(dtoTask.getTitolo()==null){return null;}
        return dtoTask;
    }

    public static int consegnaSoluzione(String pkStudente, String pkTask, String soluzione){
        int taskID = 0;
        int studenteID =0;
        try{
            taskID = Integer.parseInt(pkTask);
            studenteID = Integer.parseInt(pkStudente);
            }catch(NumberFormatException e){
            return -1;
        }
        EntityTask task = new EntityTask(taskID);
        return task.consegnaSoluzione(taskID, soluzione, studenteID);
    }

    public static int getClasseID(String pkStudente){
        //restituisce -1 se classe_codice è NULL
        int id_studente=0;
        try{
            id_studente = Integer.parseInt(pkStudente);
        }catch (NumberFormatException e){
            return -1;
        }
        if(id_studente<=0){return -1;}
        EntityStudente studente = new EntityStudente(id_studente);
        int id_classe=studente.getCodiceClasse();
        if(studente.getNome()==null){return -1;}
        return id_classe;
    }

}
