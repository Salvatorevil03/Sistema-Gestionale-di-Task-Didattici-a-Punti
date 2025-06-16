package exceptions;

public class SubmissionEvaluationException extends RuntimeException{
    public SubmissionEvaluationException(){
        super("Errore durante la valutazione della consegna");
    }
}
