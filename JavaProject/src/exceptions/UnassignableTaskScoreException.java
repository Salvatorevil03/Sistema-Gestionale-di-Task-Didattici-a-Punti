package exceptions;

public class UnassignableTaskScoreException extends RuntimeException{
    public UnassignableTaskScoreException(){
        super("Non è possibile assegnare un punteggio non positivo o superiore a 100");
    }
}
