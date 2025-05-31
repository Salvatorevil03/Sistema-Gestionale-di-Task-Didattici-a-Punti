package entity;

import database.DBStudente;

public class EntityStudente extends Utente {
    private int numTaskCompletati;
    private int numTaskValutati;
    private int punteggioTotaleOttenuto;
    //private EntityClasse classe;
    //private ArrayList<EntityConsegna> consegne;

    public EntityStudente() {
        super();
        //this.consegne = new ArrayList<EntityConsegna>();
    }

    public  EntityStudente(int id) {
        DBStudente studente = new DBStudente(id);
        this.id = id;
        this.nome = studente.getNome();
        this.cognome = studente.getCognome();
        this.mail = studente.getMail();
        this.password = studente.getPassword();
        this.numTaskCompletati = studente.getNumTaskCompletati();
        this.numTaskValutati = studente.getNumTaskValutati();
        this.punteggioTotaleOttenuto = studente.getPunteggioTotaleOttenuto();
        //this.consegne = new ArrayList<EntityConsegna>();
    }

    public EntityStudente(DBStudente studente) {
        this.id = studente.getId();
        this.nome = studente.getNome();
        this.cognome = studente.getCognome();
        this.mail = studente.getMail();
        this.password = studente.getPassword();
        this.numTaskCompletati = studente.getNumTaskCompletati();
        this.numTaskValutati = studente.getNumTaskValutati();
        this.punteggioTotaleOttenuto = studente.getPunteggioTotaleOttenuto();
    }

    //GETTER AND SETTER-----------------
    public int getId() { return id; }
    public void setId(int id) { this.id = id;}
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public int getNumTaskCompletati() { return numTaskCompletati; }
    public void setNumTaskCompletati(int numTaskCompletati){ this.numTaskCompletati = numTaskCompletati; }
    public int getNumTaskValutati() { return numTaskValutati; }
    public void setNumTaskValutati(int numTaskValutati) { this.numTaskValutati = numTaskValutati; }
    public int getPunteggioTotaleOttenuto() { return punteggioTotaleOttenuto; }
    public void setPunteggioTotaleOttenuto(int punteggioTotaleOttenuto) { this.punteggioTotaleOttenuto = punteggioTotaleOttenuto; }

    @Override
    public String toString() {
        return "EntityStudente{" +
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

    //###########################################################
    //METODI PER POSSIBILI DIPENDENZE
//    public void caricaClasse(DBStudente studente){
//        EntityClasse classe = new EntityClasse(studente.getClasse());
//        this.setClasse(classe);
//    }

//    public void caricaConsegne(DBStudente studente){
//        for(int i=0;i<studente.getConsegne().size();i++) {

//            EntityConsegna consegna = new EntityConsegna(studente.getConsegne().get(i));
//            this.consegne.add(consegna);
//        }
//    }
}
