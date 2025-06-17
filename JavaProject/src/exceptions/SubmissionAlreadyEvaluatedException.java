package exceptions;

public class SubmissionAlreadyEvaluatedException extends RuntimeException{
    public SubmissionAlreadyEvaluatedException(){
        super("La consegna è già stata valutata");
    }
}
