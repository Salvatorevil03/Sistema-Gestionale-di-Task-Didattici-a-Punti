package dto;

public class DTOStudente extends DTOUtente{

    private int numTaskCompletati;
    private int numTaskValutati;
    private int punteggioTotaleOttenuto;

    public DTOStudente(int id, String nome, String cognome, String mail, String password,int numTaskCompletati, int numTaskValutati, int punteggioTotaleOttenuto) {
        super(id, nome, cognome, mail, password);
        this.numTaskCompletati = numTaskCompletati;
        this.numTaskValutati = numTaskValutati;
        this.punteggioTotaleOttenuto = punteggioTotaleOttenuto;
    }

    public int getNumTaskCompletati() {
        return numTaskCompletati;
    }

    public void setNumTaskCompletati(int numTaskCompletati) {
        this.numTaskCompletati = numTaskCompletati;
    }

    public int getNumTaskValutati() {
        return numTaskValutati;
    }

    public void setNumTaskValutati(int numTaskValutati) {
        this.numTaskValutati = numTaskValutati;
    }

    public int getPunteggioTotaleOttenuto() {
        return punteggioTotaleOttenuto;
    }

    public void setPunteggioTotaleOttenuto(int punteggioTotaleOttenuto) {
        this.punteggioTotaleOttenuto = punteggioTotaleOttenuto;
    }
}
