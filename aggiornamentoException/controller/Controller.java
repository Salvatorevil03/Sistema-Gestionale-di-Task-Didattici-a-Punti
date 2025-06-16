package controller;
import dto.DTOClasse;
import dto.DTOConsegna;
import dto.DTOStudente;
import dto.DTOTask;
import dto.DTOUtente;
import entity.*;
import exceptions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    private static final String  QUOTES_REGEX = "['\"]";

    private Controller() {}

    /**
     * Registra un nuovo utente nel DB verificando che l'email appartenga alla lista delle
     * email valide e rimpiazzando tutti alcuni caratteri che potrebbero portare a SQLInjection
     * come i singoli e doppi apici
     *
     * @param ruolo (docente o studente)
     * @param nome Nome utente
     * @param cognome Cognome utente
     * @param mail mail dell'utente
     * @param password password dell'utente
     *
     */
    public static void registrazione(String ruolo,String nome,String cognome, String mail, String password){
        String fixedNome = nome.replaceAll(QUOTES_REGEX, " ").replaceAll("\\s+", " ").trim();
        String fixedCognome = cognome.replaceAll(QUOTES_REGEX, " ").replaceAll("\\s+", " ").trim();
        String fixedPassword = password.replaceAll(QUOTES_REGEX, " ").replaceAll("\\s+", "").trim();
        Piattaforma.registrazione(ruolo,fixedNome,fixedCognome,mail,fixedPassword);
    }

    /**
     *  Un utente registrato effettua la validazione delle sue credenziali
     *  per effettuare un login nel sistema.
     * 
      * @param mail mail istituzionale dell'utente
     * @param password password dell'utente
     * 
     * @return un'istanza di DTOUtente: contiene le informazioni di base del docente o dello studente
     * che hanno fatto il login
     * 
     */
    public static DTOUtente login(String mail, String password){
        return Piattaforma.login(mail,password);
    }

    /**
     * <p>Un docente crea una nuova classe con il nome specificato.</p>
     *
     * <p>La Stringa che contiene il nome viene formattata correttamente per
     * evitare errori nell'inserimento sul DB.
     * <br>La formattazione avviene mediante l'eliminazione di tutti gli apici singoli e/o doppi
     * e la successiva eliminazione di tutti gli spazi non necessari.
     * <br>Questa operazione viene effettuata anche per prevenire fenomeni basilari di SQLInjection</p>
     *
     * @param nome      nome della classe da creare.
     * @param pkDocente identificatore del docente che crea la classe.
     */

    public static void creaClasse(String nome, String pkDocente){

        String replacedNome = nome.replaceAll(QUOTES_REGEX, " ").replaceAll("\\s+", " ").trim();
        int docenteID = parseAndValidatePK(pkDocente);

        if (docenteID==-1){
            throw new ClassCreationException();
        }

        //EntityDocente docente = new EntityDocente(docenteID);
        //docente.creaClasse(replacedNome);
        Piattaforma.creaClasse(docenteID, replacedNome);
    }

    /**
     * Iscrizione di uno Studente a una Classe.
     *
     * @param pkStudente identificatore dello studente
     * @param pkClasse   identificatore della classe
     */
    public static void iscrizione(String pkStudente, String pkClasse){

        int studenteID = parseAndValidatePK(pkStudente);
        int classeID = parseAndValidatePK(pkClasse);

        if(studenteID==-1 || classeID==-1){
            throw new ClassEnrollmentException();
        }
        //EntityClasse classe=new EntityClasse();
        //classe.iscrizione(studenteID, classeID);
        Piattaforma.iscrizioneClasse(studenteID,classeID);
    }

    /**
     * Metodo per visualizzare tutti gli studenti senza classe.
     * 
     * @return un ArrayList di DTOStudente: contiene tutti gli studenti senza classe o è vuoto
     * se non ci sono studenti senza classe.
     */
    public static List<DTOStudente> getStudentiSenzaClasse(){
        return Piattaforma.getStudentiSenzaClasse();
    }

    /**
     * Restituisce un ArrayList di DTOClasse contenente tutte le classi di un determinato docente.
     *
     * <p>Se non vi sono classi, restituisce un ArrayList vuoto gestito opportunamente dalla GUI che
     * chiama il metodo</p>
     *
     * @param pkDocente identificatore del docente che ha classi da visualizzare
     *
     * @return un ArrayList di DTOClasse: contiene tutte le classi di un determinato docente identificato
     * dalla sua PK o è vuoto se il docente non ha classi.
     */    
    public static List<DTOClasse> getClassi(String pkDocente){
        
        int docenteID = parseAndValidatePK(pkDocente);
        if(docenteID==-1){return new ArrayList<>();}

        //EntityDocente docente = new EntityDocente(docenteID);
        List<DTOClasse> dtoClassi= Piattaforma.getElencoClassi(docenteID); //docente.getElencoClassi();

        return dtoClassi.isEmpty() ? new ArrayList<>() : dtoClassi;
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
     * @param pkClasse identificatore della classe in cui viene creato il task.
     * @param titolo titolo del task da creare.
     * @param descrizione descrizione del task da creare.
     * @param dataScadenza data di scadenza del task da creare.
     * @param maxPunteggio punteggio massimo del task da creare.
     *
     */
    public static void creaTask(String pkClasse, String titolo, String descrizione, String dataScadenza, int maxPunteggio){

        String replacedTitolo = titolo.replaceAll(QUOTES_REGEX, " ").replaceAll("\\s+", " ").trim();
        String replacedDescrizione = descrizione.replaceAll(QUOTES_REGEX, " ").replaceAll("\\s+", " ").trim();

        int classeID = parseAndValidatePK(pkClasse);
        if (classeID==-1){
            throw new TaskCreationException();
        }

        //EntityClasse classe = new EntityClasse(classeID);
        //classe.creaTask(replacedTitolo,replacedDescrizione, dataScadenza, maxPunteggio);
        Piattaforma.creaTask(classeID,replacedTitolo,replacedDescrizione, dataScadenza, maxPunteggio);
    }

    /**
     * Restituisce un ArrayList di DTOTask contenente tutti i task di una determinata classe.
     *
     * <p>Se non vi sono task, restituisce un ArrayList vuoto gestito opportunamente dalla GUI che
     * chiama il metodo</p>
     *
     * @param pkClasse identificatore della classe di cui si vogliono ottenere i task.
     *
     * @return un ArrayList di DTOTask: contiene la lista dei Task della classe identificata
     * da pkClasse o è vuoto se non ci sono task nella classe.
     */
    public static List<DTOTask> getTasks(String pkClasse){

        int classeID = parseAndValidatePK(pkClasse);
        if(classeID==-1){return new ArrayList<>();}

        //EntityClasse classe = new EntityClasse(classeID);
        List<DTOTask> dtoTasks = Piattaforma.getTasks(classeID);//classe.getTasks();

        return dtoTasks.isEmpty() ? new ArrayList<>() : dtoTasks;
    }

    /**
     * Restituisce un ArrayList di DTOStudente contenente tutti gli studenti di una determinata classe.
     *
     * <p>Se non vi sono studenti, restituisce un ArrayList vuoto gestito opportunamente dalla GUI che
     * chiama il metodo</p>
     *
     * @param pkClasse identificatore della classe di cui si vogliono ottenere i studenti.
     *
     * @return un ArrayList di DTOStudente: contiene la lista dei Studenti della classe identificata
     * da pkClasse o è vuoto se non ci sono studenti nella classe.
     */
    public static List<DTOStudente> getStudenti(String pkClasse){

        int classeID = parseAndValidatePK(pkClasse);
        if(classeID==-1){return new ArrayList<>();}

        //EntityClasse classe = new EntityClasse(classeID);
        List<DTOStudente> dtoStudenti= Piattaforma.getStudenti(classeID);//classe.getStudenti();

        return dtoStudenti.isEmpty() ? new ArrayList<>() : dtoStudenti;
    }

    /**
     * Restituisce un ArrayList di DTOConsegna contenente tutti le consegne di un determinata task.
     *
     * <p>Se non vi sono consegne, restituisce un ArrayList vuoto gestito opportunamente dalla GUI che
     * chiama il metodo</p>
     *
     * @param pkTask identificatore del task di cui si vogliono ottenere le consegne.
     *
     * @return un ArrayList di DTOConsegna: contiene la lista delle consegne del task identificato
     * da pkTask o è vuoto se non ci sono consegne relative al task.
     */
    public static List<DTOConsegna> getConsegne(String pkTask){

        int taskID = parseAndValidatePK(pkTask);
        if(taskID==-1){return new ArrayList<>();}

        //EntityTask task = new EntityTask(taskID);

        List<DTOConsegna> dtoConsegne= Piattaforma.getConsegne(taskID);//task.getConsegne();
        return dtoConsegne.isEmpty() ? new ArrayList<>() : dtoConsegne;
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
     * @param pkTask identificatore del task di cui si vuole valutare la consegna.
     * @param pkConsegna identificatore della consegna da valutare.
     * @param voto voto della soluzione.
     *
     */
    public static void valutaConsegna(String pkTask, String pkConsegna, int voto){
        int taskID = parseAndValidatePK(pkTask);
        int consegnaID = parseAndValidatePK(pkConsegna);
        if(taskID==-1 || consegnaID==-1){ throw new SubmissionEvaluationException();}

        //EntityTask task= new EntityTask(taskID);
        //task.valutaConsegna(consegnaID,voto);
        Piattaforma.valutaConsegna(consegnaID, taskID, voto);
    }



    /**
     * Metodo per ottenere un ArrayList di Integer contenente le statistiche relative allo studente
     * identificato da pkStudente.
     *
     * <p>Se non vi sono statistiche, restituisce un ArrayList vuoto gestito opportunamente dalla GUI che
     * chiama il metodo</p>
     *
     * @param pkStudente identificatore dello studente di cui si vogliono ottenere le statistiche.
     *
     * @return un ArrayList di Integer: contiene le statistiche dello studente identificato da
     * pkStudente o è vuoto
     */
    public static List<Integer> getStatistiche(String pkStudente){
        int studenteID = parseAndValidatePK(pkStudente);
        if(studenteID==-1){return new ArrayList<>();}

        //EntityStudente studente = new EntityStudente(studenteID);

        return Piattaforma.getNomeStudente(studenteID)==null ? new ArrayList<>() : Piattaforma.getStatisticheStudente(studenteID);
        //return studente.getNome()==null ? new ArrayList<>() : studente.getStatistiche();
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
     * @param pkClasse identificatore della classe di cui si vuole ottenere la classifica per punteggio.
     *
     * @return un ArrayList di DTOStudente: contiene la lista ordinata dei Studenti della classe
     * identificata da pkClasse in ordine decrescente sul punteggioTotaleOttenuto
     */
    public static List<DTOStudente> getClassificaPunteggio(String pkClasse){
        int classeID = parseAndValidatePK( pkClasse );
        if(classeID==-1){return new ArrayList<>();}

        //EntityClasse classe = new EntityClasse(classeID);
        List<DTOStudente> lista= Piattaforma.getStudenti(classeID);//classe.getStudenti();
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
     * @param pkClasse identificatore della classe di cui si vuole ottenere la classifica per task completati.
     *
     * @return un ArrayList di DTOStudente: contiene la lista ordinata dei Studenti della classe
     * identificata da pkClasse in ordine decrescente sul numTaskCompletati
     */
    public static List<DTOStudente> getClassificaTask(String pkClasse){
        int classeID = parseAndValidatePK( pkClasse);
        if(classeID==-1){return new ArrayList<>();}

        //EntityClasse classe = new EntityClasse(classeID);
        List<DTOStudente> lista= Piattaforma.getStudenti(classeID);//classe.getStudenti();
        lista.sort(Comparator.comparingInt(DTOStudente::getNumTaskCompletati).reversed());

        return lista.isEmpty() ? new ArrayList<>() : lista;
    }

    /**
     * Restituisce DTOTask contenenti le informazioni del task ricercato,
     * identificato da pkTask.
     *
     * <p>Se il task non esiste, restituisce null.</p>
     *
     * @param pkTask identificatore del task da recuperare.
     *
     * @return un DTOTask: contiene le informazioni del task identificato da pkTask
     * o null se il task non esiste.
     */
    public static DTOTask getTask(String pkTask){
        int taskID = parseAndValidatePK(pkTask);
        if(taskID==-1){return null;}

        //EntityTask task = new EntityTask(taskID);
        DTOTask dtoTask= Piattaforma.getInfoTask(taskID);//task.getInfo();

        return dtoTask.getTitolo()==null ? null : dtoTask;
    }

    /**
     * Metodo per consegnare la soluzione del task identificato da pkTask
     * da parte dello studente identificato da pkStudente.
     *
     *
     * @param pkStudente identificatore dello studente che consegna la soluzione.
     * @param pkTask identificatore del task di cui si vuole consegnare la soluzione.
     * @param soluzione soluzione del task da consegnare.
     *
     */
    public static void consegnaSoluzione(String pkStudente, String pkTask, String soluzione){
        String replacedSoluzione = soluzione.replaceAll(QUOTES_REGEX, " ").replaceAll("\\s+", " ").trim();
        int taskID = parseAndValidatePK(pkTask);
        int studenteID = parseAndValidatePK(pkStudente);
        if(taskID==-1 || studenteID==-1){throw new SubmissionException();}

        //EntityTask task = new EntityTask(taskID);
        //task.consegnaSoluzione(taskID, replacedSoluzione, studenteID);
        Piattaforma.consegnaSoluzione(taskID, replacedSoluzione, studenteID);
    }

    /**
     * Restituisce il codice della classe dello studente identificato da pkStudente.
     *
     * @param pkStudente identificatore dello studente di cui si vuole ottenere il codice della classe a
     *                   cui appartiene.
     *
     * @return id della classe dello studente: -1 (errore) o il codice della classe dello studente
     */
    public static int getClasseID(String pkStudente){
        int studenteID= parseAndValidatePK(pkStudente);
        if(studenteID==-1){return -1;}

        EntityStudente studente = new EntityStudente(studenteID);
        //if(studente.getNome()==null){return -1;}

        //return studente.getCodiceClasse();
        return Piattaforma.getNomeStudente(studenteID)==null ? -1 : Piattaforma.getCodiceClasse(studenteID);
    }

    /**
     * Metodo aggiunto per applicare il pattern di Refactoring Extract Method.
     *
     * @param pk identificatore da validare
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
