package controller;
import dto.DTOClasse;
import dto.DTOConsegna;
import dto.DTOStudente;
import dto.DTOTask;
import dto.DTOUtente;
import entity.*;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A causa della presenza di codice duplicato all'interno di tutti i metodi di questa classe,
 * tranne che in registrazione e login, abbiamo deciso di applicare il pattern di Refactoring
 * Extract Method.
 *
 * <p>Il metodo estratto parseAndValidatePK è stato definito come private static. </p>
 *
 * <p>Dove possibile abbiamo deciso di usare l'operatore ternario come parametro di return
 * per migliorare la leggibilità del codice</p>
 */
public class Controller {


    /**
     * Registra un nuovo utente nel DB.
     *
     * @param ruolo
     * @param nome
     * @param cognome
     * @param mail
     * @param password
     * 
     * @return risultato della registrazione: -1 (errore) o 1 (successo)
     */
    public static int registrazione(String ruolo,String nome,String cognome, String mail, String password){
        return Piattaforma.registrazione(ruolo,nome,cognome,mail,password);
    }

    /**
     *  Un utente registrato effettua la validazione delle sue credenziali
     *  per effettuare un login nel sistema.
     * 
      * @param mail
     * @param password
     * 
     * @return un'istanza di DTOUtente: contiene le informazioni di base del docente o dello studente
     * che hanno fatto il login
     * 
     */
    public static DTOUtente login(String mail, String password){
        return Piattaforma.login(mail,password);
    }

    /**
     * La creazione della classe fallisce se si inserisce un stringa vuota o se contiene
     * i caratteri non validi " o '.
     *
     * <p>Questa scelta è dettata dal fatto di garantire una maggiore sicurezza nell'inserimento
     * del nome della classe sul DB.</p>
     *
     * <p>La sequenza di escape per il carattere ' è usata solo per motivi di leggibilità</p>
     * 
     * @param nome
     * @param pkDocente
     * 
     * @return risultato della creazione della classe: -1 (errore) o 1 (successo)
     */
    public static int creaClasse(String nome, String pkDocente){

        if( nome.isEmpty() || nome.contains("\"") || nome.contains("\'")){return -1;}
        int docenteID = parseAndValidatePK(pkDocente);
        if (docenteID==-1){return docenteID;}

        EntityDocente docente = new EntityDocente(docenteID);
        return docente.creaClasse(nome);
    }

    /**
     * Iscrizione di uno Studente a una Classe.
     * 
     * @param pkStudente
     * @param pkClasse
     * 
     * @return risultato dell'iscrizione: -1 (errore) o 1 (successo)
     */
    public static int iscrizione(String pkStudente, String pkClasse){

        int studenteID = parseAndValidatePK(pkStudente);
        int classeID = parseAndValidatePK(pkClasse);

        if(studenteID==-1 || classeID==-1){
            return -1;
        }
        EntityClasse classe=new EntityClasse();
        return classe.iscrizione(studenteID,classeID);
    }

    /**
     * Metodo per visualizzare tutti gli studenti senza classe.
     * 
     * @return un ArrayList di DTOStudente: contiene tutti gli studenti senza classe o è vuoto
     * se non ci sono studenti senza classe.
     */
    public static ArrayList<DTOStudente> getStudentiSenzaClasse(){
        return Piattaforma.getStudentiSenzaClasse();
    }

    /**
     * Restituisce un ArrayList di DTOClasse contenente tutte le classi di un determinato docente.
     *
     * <p>Se non vi sono classi, restituisce un ArrayList vuoto gestito opportunamente dalla GUI che
     * chiama il metodo</p>
     *
     * @param pkDocente
     *
     * @return un ArrayList di DTOClasse: contiene tutte le classi di un determinato docente identificato
     * dalla sua PK o è vuoto se il docente non ha classi.
     */    
    public static ArrayList<DTOClasse> getClassi(String pkDocente){
        
        int docenteID = parseAndValidatePK(pkDocente);
        if(docenteID==-1){return new ArrayList<>();}

        EntityDocente docente = new EntityDocente(docenteID);
        ArrayList<DTOClasse> DTOclassi=docente.getElencoClassi();

        if(DTOclassi.isEmpty()){return new ArrayList<>();}
        return DTOclassi;
    }

    /**
     * Crea un nuovo task nella classe identificata da pkClasse
     * usando i parametri titolo, descrizione, dataScadenza e maxPunteggio.
     *
     * <p>Per gestire i problemi causati dall'inserimento all'interno del titolo o
     * della descrizione degli apici singoli e/o doppi, viene utilizzato il metodo
     * replaceAll della classe String, seguito dal metodo trim.</p>
     *
     * <p>Il primo replaceAll sostituisce tutti gli apici singoli e/o doppi con uno spazio.</p>
     *
     * <p>Il secondo replaceAll elimina tutti gli spazi multipli che potrebbero essere stati
     * generati dal primo metodo ricercando tutti i caratteri di spaziatura (\\s) multipli
     * (+) e sostituendoli con un singolo spazio.</p>
     *
     * <p>Il metodo trim rifinisce la stringa eliminando gli spazi iniziali e finali.</p>
     *
     * @param pkClasse
     * @param titolo
     * @param descrizione
     * @param dataScadenza
     * @param maxPunteggio
     *
     * @return risultato della creazione del task: -1 (errore) o 1 (successo)
     */
    public static int creaTask(String pkClasse, String titolo, String descrizione, String dataScadenza, int maxPunteggio){

        String replacedTitolo = titolo.replaceAll("['\"]", " ").replaceAll("\\s+", " ").trim();
        String replacedDescrizione = descrizione.replaceAll("['\"]", " ").replaceAll("\\s+", " ").trim();

        int classeID = parseAndValidatePK(pkClasse);
        if (classeID==-1){return -1;}

        EntityClasse classe = new EntityClasse(classeID);
        return classe.creaTask(replacedTitolo,replacedDescrizione, dataScadenza, maxPunteggio);
    }

    /**
     * Restituisce un ArrayList di DTOTask contenente tutti i task di una determinata classe.
     *
     * <p>Se non vi sono task, restituisce un ArrayList vuoto gestito opportunamente dalla GUI che
     * chiama il metodo</p>
     *
     * @param pkClasse
     *
     * @return un ArrayList di DTOTask: contiene la lista dei Task della classe identificata
     * da pkClasse o è vuoto se non ci sono task nella classe.
     */
    public static ArrayList<DTOTask> getTasks(String pkClasse){

        int classeID = parseAndValidatePK(pkClasse);
        if(classeID==-1){return new ArrayList<>();}

        EntityClasse classe = new EntityClasse(classeID);
        ArrayList<DTOTask> DTOTasks = classe.getTasks();

        return DTOTasks.isEmpty() ? new ArrayList<>() : DTOTasks;
    }

    /**
     * Restituisce un ArrayList di DTOStudente contenente tutti gli studenti di una determinata classe.
     *
     * <p>Se non vi sono studenti, restituisce un ArrayList vuoto gestito opportunamente dalla GUI che
     * chiama il metodo</p>
     *
     * @param pkClasse
     *
     * @return un ArrayList di DTOStudente: contiene la lista dei Studenti della classe identificata
     * da pkClasse o è vuoto se non ci sono studenti nella classe.
     */
    public static ArrayList<DTOStudente> getStudenti(String pkClasse){

        int classeID = parseAndValidatePK(pkClasse);
        if(classeID==-1){return new ArrayList<>();}

        EntityClasse classe = new EntityClasse(classeID);
        ArrayList<DTOStudente> DTOStudenti=classe.getStudenti();

        return DTOStudenti.isEmpty() ? new ArrayList<>() : DTOStudenti;
    }

    /**
     * Restituisce un ArrayList di DTOConsegna contenente tutti le consegne di un determinata task.
     *
     * <p>Se non vi sono consegne, restituisce un ArrayList vuoto gestito opportunamente dalla GUI che
     * chiama il metodo</p>
     *
     * @param pkTask
     *
     * @return un ArrayList di DTOConsegna: contiene la lista delle consegne del task identificato
     * da pkTask o è vuoto se non ci sono consegne relative al task.
     */
    public static ArrayList<DTOConsegna> getConsegne(String pkTask){

        int taskID = parseAndValidatePK(pkTask);
        if(taskID==-1){return new ArrayList<>();}

        EntityTask task = new EntityTask(taskID);

        ArrayList<DTOConsegna> DTOconsegne=task.getConsegne();
        return DTOconsegne.isEmpty() ? new ArrayList<>() : DTOconsegne;
    }

    /**
     * Metodo per la valutazione di una consegna, identificata da pkConsegna, relativamente
     * a un task, identificato da pkTask.
     *
     * <p>Il metodo verifica che pkTask e pkConsegna siano validi e che il voto sia sempre non
     * negativo.</p>
     *
     * <p>Ulteriori verifiche vengono effettuate nella classe EntityTask mediante il metodo
     * valutaConsegna.</p>
     *
     * @param pkTask
     * @param pkConsegna
     * @param voto
     *
     * @return risultato della valutazione della consegna: -1 (errore) o 1 (successo)
     */
    public static int valutaConsegna(String pkTask, String pkConsegna, int voto){
        int taskID = parseAndValidatePK(pkTask);
        int consegnaID = parseAndValidatePK(pkConsegna);
        if(taskID==-1 || consegnaID==-1 || voto<=0){ return -1;}

        EntityTask task= new EntityTask(taskID);

        return task.valutaConsegna(consegnaID,voto);
    }


    /**
     * Metodo per ottenere un ArrayList di Integer contenente le statistiche relative allo studente
     * identificato da pkStudente.
     *
     * <p>Se non vi sono statistiche, restituisce un ArrayList vuoto gestito opportunamente dalla GUI che
     * chiama il metodo</p>
     *
     * @param pkStudente
     *
     * @return un ArrayList di Integer: contiene le statistiche dello studente identificato da
     * pkStudente o è vuoto
     */
    public static ArrayList<Integer> getStatistiche(String pkStudente){
        int studenteID = parseAndValidatePK(pkStudente);
        if(studenteID==-1){return new ArrayList<>();}

        EntityStudente studente = new EntityStudente(studenteID);

        return studente.getNome()==null ? new ArrayList<>() : studente.getStatistiche();
    }

    /**
     * Metodo per ottenere un ArrayList di DTOStudente contenente una lista ordinata per punteggio ottenuto
     * di tutti gli studenti di una determinata classe, in ordine decrescente.
     *
     * <p>Se non vi sono studenti, restituisce un ArrayList vuoto gestito opportunamente dalla GUI che
     * chiama il metodo</p>
     *
     * <p>L'operatore ternario nel return è usato per leggibilità</p>
     *
     * @param pkClasse
     *
     * @return un ArrayList di DTOStudente: contiene la lista ordinata dei Studenti della classe
     * identificata da pkClasse in ordine decrescente sul punteggioTotaleOttenuto
     */
    public static ArrayList<DTOStudente> getClassificaPunteggio(String pkClasse){
        int classeID = parseAndValidatePK( pkClasse );
        if(classeID==-1){return new ArrayList<>();}

        EntityClasse classe = new EntityClasse(classeID);
        ArrayList<DTOStudente> lista=classe.getStudenti();
        lista.sort(Comparator.comparingInt(DTOStudente::getPunteggioTotaleOttenuto).reversed());

        return lista.isEmpty() ? new ArrayList<>() : lista;
    }


    /**
     * Metodo per ottenere un ArrayList di DTOStudente contenente una lista ordinata per task completati
     * di tutti gli studenti di una determinata classe, in ordine decrescente.
     *
     * <p>Se non vi sono studenti, restituisce un ArrayList vuoto gestito opportunamente dalla GUI che
     * chiama il metodo</p>
     *
     * <p>L'operatore ternario nel return è usato per leggibilità</p>
     *
     * @param pkClasse
     *
     * @return un ArrayList di DTOStudente: contiene la lista ordinata dei Studenti della classe
     * identificata da pkClasse in ordine decrescente sul numTaskCompletati
     */
    public static ArrayList<DTOStudente> getClassificaTask(String pkClasse){
        int classeID = parseAndValidatePK( pkClasse);
        if(classeID==-1){return new ArrayList<>();}

        EntityClasse classe = new EntityClasse(classeID);
        ArrayList<DTOStudente> lista=classe.getStudenti();
        lista.sort(Comparator.comparingInt(DTOStudente::getNumTaskCompletati).reversed());

        return lista.isEmpty() ? new ArrayList<>() : lista;
    }

    /**
     * Restituisce DTOTask contenenti le informazioni del task ricercato,
     * identificato da pkTask.
     *
     * <p>Se il task non esiste, restituisce null.</p>
     *
     * @param pkTask
     *
     * @return un DTOTask: contiene le informazioni del task identificato da pkTask
     * o null se il task non esiste.
     */
    public static DTOTask getTask(String pkTask){
        int taskID = parseAndValidatePK(pkTask);
        if(taskID==-1){return null;}

        EntityTask task = new EntityTask(taskID);
        DTOTask dtoTask=task.getInfo();

        return dtoTask.getTitolo()==null ? null : dtoTask;
    }

    /**
     * Metodo per consegnare la soluzione del task identificato da pkTask
     * da parte dello studente identificato da pkStudente.
     *
     *
     * @param pkStudente
     * @param pkTask
     * @param soluzione
     *
     * @return risultato della consegna della soluzione: -1 (errore) o 1 (successo)
     */
    public static int consegnaSoluzione(String pkStudente, String pkTask, String soluzione){
        String replacedSoluzione = soluzione.replaceAll("['\"]", " ").replaceAll("\\s+", " ").trim();
        int taskID = parseAndValidatePK(pkTask);
        int studenteID = parseAndValidatePK(pkStudente);
        if(taskID==-1 || studenteID==-1){return -1;}

        EntityTask task = new EntityTask(taskID);
        return task.consegnaSoluzione(taskID, replacedSoluzione, studenteID);
    }

    /**
     * Restituisce il codice della classe dello studente identificato da pkStudente.
     *
     * @param pkStudente
     *
     * @return id della classe dello studente: -1 (errore) o il codice della classe dello studente
     */
    public static int getClasseID(String pkStudente){
        //restituisce -1 se classe_codice è NULL
        //faccio returm stidemte.getcodiceclasse perchè codice classe o è -1(se null) o è già stato
        //controllato quando ho iscritto lo studente alla classe in iscrizione
        int studenteID= parseAndValidatePK(pkStudente);
        if(studenteID==-1){return -1;}

        EntityStudente studente = new EntityStudente(studenteID);
        if(studente.getNome()==null){return -1;}

        return studente.getCodiceClasse();
    }

    /**
     * Metodo aggiunto per applicare il pattern di Refactoring Extract Method.
     *
     * @param pk
     *
     * @return risultato della validazione della stringa: -1 (errore) o il valore intero dell'id
     */
    private static int parseAndValidatePK(String pk) {
        try {
            int parsedId = Integer.parseInt(pk);
            return parsedId <= 0 ? -1 : parsedId;
        } catch (NumberFormatException e) {
            return -1;
        }
    }


}
