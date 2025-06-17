package exceptions;

public class TitleLengthException extends RuntimeException{
    public TitleLengthException(){
        super("Il titolo non può essere più lungo di 25 caratteri");
    }
}
