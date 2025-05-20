package entity;

import database.DBDocente;
import java.util.ArrayList;

public class EntityDocente extends Utente{
    private ArrayList<EntityClasse> classi;

    //Costruttore
    public EntityDocente(int id){
        DBDocente docente = new DBDocente(id); //carico studente da DB
        this.nome = docente.getNome();
        this.cognome = docente.getCognome();
        this.mail = docente.getMail();
        this.password=docente.getPassword();
        this.classi= new ArrayList<EntityClasse>();

        docente.caricaClassiDaDB();
        caricaClassi(docente);
    }

    public EntityDocente(DBDocente docente){
        this.id=docente.getId();
        this.nome = docente.getNome();
        this.cognome = docente.getCognome();
        this.mail = docente.getMail();
        this.password=docente.getPassword();
        this.classi=new ArrayList<EntityClasse>();
        docente.caricaClassiDaDB();
        caricaClassi(docente);
    }

    public EntityDocente(){
        super();
        this.classi=new ArrayList<EntityClasse>();
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
    public ArrayList<EntityClasse> getClassi() {
        return classi;
    }
    public void setClassi(ArrayList<EntityClasse> classi) {
        this.classi = classi;
    }

    @Override
    public String toString() {
        return "EntityDocente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", classi=" + classi +
                '}';
    }

    //--------------------------------------------------------------------
    public void caricaClassi(DBDocente docente){
        for(int i=0;i<docente.getClassi().size();i++){
            EntityClasse classe= new EntityClasse(docente.getClassi().get(i));
            this.classi.add(classe);
        }
    }
}
