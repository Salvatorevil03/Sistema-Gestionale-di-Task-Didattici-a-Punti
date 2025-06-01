package entity;

public class Main_Test_ValutaCOnsegna {
    public static void main(String[] args) {
//        EntityTask task1=new EntityTask(4);
//        System.out.println("IL TASK: "+task1);
//        task1.valutaConsegna(2,99);
        EntityStudente studente = new EntityStudente("nome","cognome","mail","password");
        int res = studente.creaStudente();
        System.out.println(res);
    }
}
