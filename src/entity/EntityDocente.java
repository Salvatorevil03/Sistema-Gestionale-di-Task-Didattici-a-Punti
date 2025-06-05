package entity;

import dto.DTOClasse;
import database.DBDocente;

import java.util.ArrayList;

public class EntityDocente extends Utente {
    private int id;
    private ArrayList<EntityClasse> classi;


    public EntityDocente() {
        super();
        this.classi = new ArrayList<EntityClasse>();
    }

    public EntityDocente(int id) {
        DBDocente docente = new DBDocente(id);
        this.id = id;
        this.nome = docente.getNome();
        this.cognome = docente.getCognome();
        this.mail = docente.getMail();
        this.password = docente.getPassword();
        this.classi = new ArrayList<EntityClasse>();
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

    public ArrayList<DTOClasse> getElencoClassi() {
        DBDocente docente = new DBDocente(id);
        docente.caricaClassiDaDB();
        this.caricaClassi(docente);
        ArrayList<DTOClasse> lista= this.creaDTOListaClassi();
        return lista;
    }

    private ArrayList<DTOClasse> creaDTOListaClassi() {
        ArrayList<DTOClasse> lista= new ArrayList<>();
        for(int i=0; i<classi.size(); i++){
            DTOClasse classe = new DTOClasse();
            classe.setNome(classi.get(i).getNome());
            classe.setCodice(classi.get(i).getCodice());
            classe.setNumeroTask(classi.get(i).getNumeroTask());
            lista.add(classe);
        }
        return lista;
    }

    public int creaClasse(String nome) {
        DBDocente docente = new DBDocente();
        int esito=docente.creaClasse(nome,this.id);
        return esito;
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

    public int inserisciSuDB(){
        DBDocente docente = new DBDocente(this.nome, this.cognome, this.mail, this.password);
        return docente.inserisciSuDB();
    }


}
