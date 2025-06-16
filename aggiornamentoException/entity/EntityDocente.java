package entity;

import dto.DTOClasse;
import database.DBDocente;

import java.util.ArrayList;
import java.util.List;

import exceptions.ClassCreationException;
import exceptions.DBException;
import exceptions.MailAlreadyUsedException;
import exceptions.MailNotValidException;

public class EntityDocente extends Utente {
    private ArrayList<EntityClasse> classi;


    public EntityDocente() {
        super();
        this.classi = new ArrayList<>();
    }

    public EntityDocente(int id) {
        DBDocente docente = new DBDocente(id);
        this.id = id;
        this.nome = docente.getNome();
        this.cognome = docente.getCognome();
        this.mail = docente.getMail();
        this.password = docente.getPassword();
        this.classi = new ArrayList<>();
    }

    public EntityDocente(DBDocente docente) {
        this.id = docente.getId();
        this.nome = docente.getNome();
        this.cognome = docente.getCognome();
        this.mail = docente.getMail();
        this.password = docente.getPassword();
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


    @Override
    public String toString() {
        return "EntityDocente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public List<DTOClasse> getElencoClassi() {
        DBDocente docente = new DBDocente(id);
        docente.caricaClassiDaDB();
        this.caricaClassi(docente);
        return creaDTOListaClassi();
    }

    private ArrayList<DTOClasse> creaDTOListaClassi() {
        ArrayList<DTOClasse> lista= new ArrayList<>();
        for (EntityClasse entityClasse : classi) {
            DTOClasse classe = new DTOClasse();
            classe.setNome(entityClasse.getNome());
            classe.setCodice(entityClasse.getCodice());
            classe.setNumeroTask(entityClasse.getNumeroTask());
            lista.add(classe);
        }
        return lista;
    }

    public void creaClasse(String nome) {
        DBDocente docente = new DBDocente();
        try {
            docente.creaClasse(nome, this.id);
        }catch(DBException e){
            throw new ClassCreationException();
        }
    }

    public void caricaClassi(DBDocente docente){
        for(int i=0;i<docente.getClassi().size();i++){
            EntityClasse classe= new EntityClasse(docente.getClassi().get(i));
            this.classi.add(classe);
        }
    }

    public EntityDocente(String nome, String cognome, String mail, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.password = password;
    }

    public void inserisciSuDB(){
        DBDocente docente = new DBDocente(this.nome, this.cognome, this.mail, this.password);
        if (Utente.isEmailValid(docente.getMail()))
            throw new MailNotValidException();

        try {
            docente.inserisciSuDB();
        }catch (DBException e){
            throw new MailAlreadyUsedException();
        }
    }


}
