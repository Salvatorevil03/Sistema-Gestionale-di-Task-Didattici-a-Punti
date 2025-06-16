package entity;

import java.util.Arrays;
import java.util.List;

public class Utente {
    protected int id;
    protected String nome;
    protected String cognome;
    protected String mail;
    protected String password;

    // Lista di domini email validi
    private static final List<String> DOMINI_EMAIL_VALIDI = Arrays.asList(
            // Provider principali
            "gmail.com", "outlook.com", "hotmail.com", "yahoo.com", "icloud.com",
            "live.com", "msn.com", "yahoo.it", "libero.it", "virgilio.it",
            "tiscali.it", "alice.it", "tin.it", "fastwebnet.it", "email.it",
            "inwind.it", "kataweb.it", "supereva.it", "interfree.it",

            // Domini educativi italiani - Università principali
            "edu.it", "unibo.it", "unimi.it", "uniroma1.it", "polimi.it",
            "unifi.it", "unina.it", "unipd.it", "unige.it", "unict.it",
            "unipi.it", "uniba.it", "unical.it", "unimore.it", "univr.it",
            "unica.it", "uniss.it", "unimib.it", "unipr.it", "unipv.it",
            "unife.it", "unimol.it", "unisannio.it", "unisalento.it",
            "unich.it", "uniud.it", "units.it", "unitn.it", "unibz.it",
            "univaq.it", "unicam.it", "uniurb.it", "uniroma2.it", "uniroma3.it",
            "lumsa.it", "iulm.it", "bocconi.it", "cattolica.it"
    );



    /**
     * Metodo utilizzato contestualmente al metodo di verifica di correttezza delle mail.
     * <br>Il metodo verifica che la mail appartenga alla lista di email validate
     *
     * @param email mail di cui verificare il dominio
     * @return un valore booleano: false (errore) o true (successo)
     */
    protected static boolean isDominioEmailValido(String email){
        if (!email.contains("@")){
            return false;
        }
        String dominioEmail = email.substring(email.lastIndexOf("@") + 1).toLowerCase();
        return DOMINI_EMAIL_VALIDI.contains(dominioEmail);
    }

    /**
     * Metodo utilizzato per la verifica della email inserita.
     * <br>Il metodo verifica che la mail sia strutturata in un certo modo:
     * <ul>
     *     <ul>
     *         <li>Prima parte: tutti i caratteri appartenenti all'elenco [a-zA-Z0-9_+&*-] </li>
     *         <li>Seconda parte: una chiocciola @</li>
     *         <li>Terza parte: tutti i caratteri appartenenti alla lista [a-zA-Z0-9-] multipli (+) con un
     *         intervallo di lunghezza {1,10} e alla lista [a-zA-Z] subito dopo il punto con un intervallo
     *         di lunghezza {2,7}</li>
     *     </ul>
     * </ul>
     *
     * Esempio:
     *<ul>
     *     <ul>
     *         <li>Valido: test@gmail.com</li>
     *         <li>Invalido: test@gmail......com</li>
     *         <li>Invalido: testgmail.com</li>
     *     </ul>
     *</ul>
     * @param email email da verificare
     * @return un valore booleano: false (errore) o true (successo)
     */
    protected static boolean isEmailValid(String email){

        String emailRegex = "^[a-zA-Z0-9_+&*-]+@" +
                "(?:[a-zA-Z0-9-]+\\.){1,10}[a-zA-Z]{2,7}$";

        return !email.matches(emailRegex) || !isDominioEmailValido(email);
    }
}