package exceptions;

public class ScoreEvaluationException extends RuntimeException{
    public ScoreEvaluationException(){
        super("La valutazione è più alta del punteggio massimo assegnabile per questa consegna o è negativa");
    }
}
