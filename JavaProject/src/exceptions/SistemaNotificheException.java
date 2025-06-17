package exceptions;

public class SistemaNotificheException extends RuntimeException{
    public SistemaNotificheException(){
        super("Errore durante la comunicazione con il sistema di notifiche.");
    }
}
