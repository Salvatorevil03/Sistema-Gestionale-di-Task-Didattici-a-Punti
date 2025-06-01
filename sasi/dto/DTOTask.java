package dto;

public class DTOTask {

    private int id;
    private String titolo;
    private String descrizione;
    private String dataScadenza;
    private int maxPuntiAssegnabili;

    public DTOTask(int id, String titolo, String descrizione, String dataScadenza, int maxPuntiAssegnabili) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataScadenza = dataScadenza;
        this.maxPuntiAssegnabili = maxPuntiAssegnabili;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public int getMaxPuntiAssegnabili() {
        return maxPuntiAssegnabili;
    }

    public void setMaxPuntiAssegnabili(int maxPuntiAssegnabili) {
        this.maxPuntiAssegnabili = maxPuntiAssegnabili;
    }
}
