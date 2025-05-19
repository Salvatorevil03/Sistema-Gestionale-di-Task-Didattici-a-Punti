package entity;

import database.DBDocente;

public class EntityDocente extends Utente{

    //Costruttore
    public EntityDocente(int id){
        DBDocente docente = new DBDocente(id); //carico studente da DB
        this.nome = docente.getNome();
        this.cognome = docente.getCognome();
        this.mail = docente.getMail();
        this.password=docente.getPassword();
    }

    public EntityDocente(DBDocente docente){
        this.id=docente.getId();
        this.nome = docente.getNome();
        this.cognome = docente.getCognome();
        this.mail = docente.getMail();
        this.cognome=docente.getCognome();
    }

    public EntityDocente(){
        this.nome = "";
        this.password = "";
        this.mail = "";
        this.cognome = "";
        this.id = -1;
    }

    //GETTER AND SETTER
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getMail() {return mail;}
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPassword() {return password;}
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getId() {return id;}

    @Override
    public String toString() {
        return "EntityDocente{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", cognome='" + cognome + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
