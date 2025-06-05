package dto;

public class DTOTask {
    private int id;
    private String titolo;
    private String descrizione;
    private String dataScadenza;
    private int maxPuntiAssegnabili;

    public DTOTask() {
        super();
    }

    public DTOTask(int id, String titolo, String descrizione, String dataScadenza, int maxPuntiAssegnabili) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.dataScadenza = dataScadenza;
        this.maxPuntiAssegnabili = maxPuntiAssegnabili;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }
    public void setMaxPuntiAssegnabili(int maxPuntiAssegnabili) {
        this.maxPuntiAssegnabili = maxPuntiAssegnabili;
    }
    public int getId() {
        return id;
    }
    public String getTitolo() {
        return titolo;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public String getDataScadenza() {
        return dataScadenza;
    }
    public int getMaxPuntiAssegnabili() {
        return maxPuntiAssegnabili;
    }

    @Override
    public String toString() {
        return "DTOTask{" +
                "id=" + id +
                ", titolo='" + titolo + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", dataScadenza='" + dataScadenza + '\'' +
                ", maxPuntiAssegnabili=" + maxPuntiAssegnabili +
                '}';
    }
}
