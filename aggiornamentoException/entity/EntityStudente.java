package entity;

import database.DBStudente;
import exceptions.DBException;
import exceptions.MailAlreadyUsedException;
import exceptions.MailNotValidException;
import exceptions.StudentException;

import java.util.ArrayList;
import java.util.List;

public class EntityStudente extends Utente {
    private int numTaskCompletati;
    private int numTaskValutati;
    private int punteggioTotaleOttenuto;

    public EntityStudente() {
        super();
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

    public EntityStudente(String nome, String cognome, String mail, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.password = password;
    }

    ///GETTER AND SETTER-----------------
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

    public List<Integer> getStatistiche() {
        ArrayList<Integer> lista=new ArrayList<>();
        lista.add(numTaskCompletati);
        lista.add(numTaskValutati);
        lista.add(punteggioTotaleOttenuto);
        return lista;
    }

    public int getCodiceClasse() {
        DBStudente studente=new DBStudente(id);
        if(studente.getNome()==null){return -1;}
        return studente.getCodiceClasse();
    }

    protected static void incrementaNumTaskCompletati(int studenteID){
        DBStudente studente = new DBStudente(studenteID);
        studente.setNumTaskCompletati(studente.getNumTaskCompletati()+1);
        try {
            studente.salvaTaskCompletatiInDB();
        }catch(DBException e){
            throw new StudentException();
        }
    }

    public void inserisciSuDB(){
        DBStudente studente = new DBStudente(this.nome,this.cognome,this.mail,this.password);
        if (Utente.isEmailValid(studente.getMail()))
            throw new MailNotValidException();

        try {
            studente.inserisciSuDB();
        }catch (DBException e){
            throw new MailAlreadyUsedException();
        }
    }
}
