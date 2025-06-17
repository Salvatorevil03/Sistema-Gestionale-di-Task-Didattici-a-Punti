package exceptions;

public class SubmissionException extends RuntimeException{
    public SubmissionException(){
        super("Errore durante la consegna");
    }
}
