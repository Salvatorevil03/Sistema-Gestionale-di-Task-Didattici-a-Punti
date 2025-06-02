package DTO;

public class DTOStudente extends DTOUtente{
    private int numTaskCompletati;
    private int numTaskValutati;
    private int punteggioTotaleOttenuto;

    public DTOStudente() {
    }

    public int getNumTaskValutati() {
        return numTaskValutati;
    }

    public void setNumTaskValutati(int numTaskValutati) {
        this.numTaskValutati = numTaskValutati;
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    public int getNumTaskCompletati() {
        return numTaskCompletati;
    }
    public void setNumTaskCompletati(int numTaskCompletati) {
        this.numTaskCompletati = numTaskCompletati;
    }
    public int getPunteggioTotaleOttenuto() {
        return punteggioTotaleOttenuto;
    }
    public void setPunteggioTotaleOttenuto(int punteggioTotaleOttenuto) {
        this.punteggioTotaleOttenuto = punteggioTotaleOttenuto;
    }

    @Override
    public String toString() {
        return "DTOStudente{" +
                "numTaskCompletati=" + numTaskCompletati +
                ", numTaskValutati=" + numTaskValutati +
                ", punteggioTotaleOttenuto=" + punteggioTotaleOttenuto +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
