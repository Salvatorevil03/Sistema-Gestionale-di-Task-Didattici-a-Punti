package exceptions;

public class SubmissionExistingException extends RuntimeException{
    public SubmissionExistingException(){
        super("Soluzione già consegnata");
    }

}
