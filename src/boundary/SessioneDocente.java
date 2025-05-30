package taskdidatticiNEW;

public class SessioneDocente {

    private static SessioneDocente instance;

    private String nomeDocente;
    private int idDocente;
    private String pkClasseSelezionata;
    private String pkTaskSelezionato;
    private int idStudenteSelezionato;

    private SessioneDocente() {}

    public static SessioneDocente getInstance() {
        if (instance == null) {
            instance = new SessioneDocente();
        }
        return instance;
    }
    
    public static void logout() {
        instance = null;
    }
    
    public String getNomeDocente() {
        return nomeDocente;
    }

    public void setNomeDocente(String nomeDocente) {
        this.nomeDocente = nomeDocente;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public String getPkClasseSelezionata() {
        return pkClasseSelezionata;
    }

    public void setPkClasseSelezionata(String pkClasseSelezionata) {
        this.pkClasseSelezionata = pkClasseSelezionata;
    }

    public String getPkTaskSelezionato() {
        return pkTaskSelezionato;
    }

    public void setPkTaskSelezionato(String pkTaskSelezionato) {
        this.pkTaskSelezionato = pkTaskSelezionato;
    }

    public int getIdStudenteSelezionato() {
        return idStudenteSelezionato;
    }

    public void setIdStudenteSelezionato(int idStudenteSelezionato) {
        this.idStudenteSelezionato = idStudenteSelezionato;
    }

}
