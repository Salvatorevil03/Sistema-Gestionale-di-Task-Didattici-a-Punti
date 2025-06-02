package entity;

import database.DBDocente;

public class EntityDocente extends Utente {
    //private ArrayList<EntityClasse> classi;


    public EntityDocente() {
        super();
        //this.classi = new ArrayList<EntityClasse>();
    }

    public EntityDocente(int id) {
        DBDocente docente = new DBDocente(id);
        this.id = id;
        this.nome = docente.getNome();
        this.cognome = docente.getCognome();
        this.mail = docente.getMail();
        this.password = docente.getPassword();
        //this.classi = new ArrayList<EntityClasse>();
    }

    public EntityDocente(DBDocente docente) {
        this.id = docente.getId();
        this.nome = docente.getNome();
        this.cognome = docente.getCognome();
        this.mail = docente.getMail();
        this.password = docente.getPassword();
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

    //SASI
    public EntityDocente(String nome, String cognome, String mail, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.mail = mail;
        this.password = password;
    }

    public int inserisciSuDB(){
        DBDocente docente = new DBDocente(this.nome, this.cognome, this.mail, this.password);
        return docente.inserisciSuDB();
    }

    //###########################################################
    //METODI PER POSSIBILI DIPENDENZE
//    public void caricaClassi(DBDocente docente){
//        for(int i=0;i<docente.getClassi().size();i++){
//            EntityClasse classe= new EntityClasse(docente.getClassi().get(i));
//            this.classi.add(classe);
//        }
//    }
}
