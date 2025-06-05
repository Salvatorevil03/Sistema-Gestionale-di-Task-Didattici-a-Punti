package taskdidatticiNEW;

/**
 * Classe che rappresenta la sessione dello studente.
 * <br>La classe è realizzata per implementare ul Creational Pattern Singleton.
 * <br>Quando lo studente effettua il login nella GUI {@link GUILogin} viene creata
 * un'istanza della classe {@link SessioneDocente} che conserverà informazioni relative
 * alla sessione dello studente.
 */
public class SessioneStudente {

    private static SessioneStudente instance;

    private String nomeStudente;
    private int idStudente;
    private String pkClasse;
    private String pkTask;

    private SessioneStudente() {}

    public static SessioneStudente getInstance() {
        if (instance == null) {
            instance = new SessioneStudente();
        }
        return instance;
    }
    
    public static void logout() {
        instance = null;
    }

    /// Getter e Setter
    public String getNomeStudente() {
        return nomeStudente;
    }

    public void setNomeStudente(String nomeStudente) {
        this.nomeStudente = nomeStudente;
    }

    public int getIdStudente() {
        return idStudente;
    }

    public void setIdStudente(int idStudente) {
        this.idStudente = idStudente;
    }

    public String getPkClasse() {
        return pkClasse;
    }

    public void setPkClasse(String pkClasse) {
        this.pkClasse = pkClasse;
    }

    public String getPkTask() {
        return pkTask;
    }

    public void setPkTask(String pkTask) {
        this.pkTask = pkTask;
    }
    
}
