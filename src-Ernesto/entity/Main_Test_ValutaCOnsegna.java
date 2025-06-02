package entity;
import DTO.DTOStudente;
import controller.*;

import controller.Controller;

public class Main_Test_ValutaCOnsegna {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        //test valuta consegna---DONE
//        System.out.println(Controller.valutaConsegna("29","10",5));
//        //test get elenco dei task---DONE
//        System.out.println(Controller.getTasks("1001"));
//        //test del getConsegne---DONE
//        System.out.println(Controller.getConsegna("AS"));
//        //test di getClassi--DONE
//        System.out.println(Controller.getClassi("2000"));
        //test di getStudenti---DONE
        //System.out.println(Controller.getStudenti(""));

        //test di getStudenti---DONE
        //System.out.println(Controller.getStatistiche("sasa"));

        //test di getTask---DONE
        //System.out.println(Controller.getTask("1000"));

        //test di getClassificaPunteggio---DONE
        //System.out.println(Controller.getClassificaPunteggio("11111111111"));

        //test di getCLassificaTask---DONE
        //System.out.println(Controller.getClassificaTask(""));

        //test di getStudentiSenzaCLasse---DONE
        //System.out.println(Controller.getStudentiSenzaClasse());

        //test di getClasseId---DONE
        //System.out.println(Controller.getClasseId("3"));

        //test di creaTask----DONE
        //System.out.println(Controller.creaTask("4","ISAGI DEMON KING","Task creato da umani per umani","01-02-2022",13));

        //test di creaClasse-----DONE
        //System.out.println(Controller.creaClasse("AVALON","434343"));

        //test di iscrizione
        //System.out.println(Controller.iscrizione("2","1"));

        //test di consegnaSoluzione------DONE
        //System.out.println(Controller.consegnaSoluzione("SADDS","","nel mezzo di cammin di nostra vita"));
        long end = System.currentTimeMillis();
        System.out.println("Tempo di esecuzione: " + (end - start) + " ms");

    }
}
