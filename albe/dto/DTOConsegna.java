package dto;

public class DTOConsegna {

    private int id; // PK
    private int punteggio;
    private String soluzione;

    public DTOConsegna(int id, int punteggio, String soluzione) {
        this.id = id;
        this.punteggio = punteggio;
        this.soluzione = soluzione;
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
}
