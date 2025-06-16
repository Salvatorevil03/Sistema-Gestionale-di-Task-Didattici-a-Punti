package exceptions;

public class DescriptionLengthException extends RuntimeException{
    public DescriptionLengthException(){
        super("La descrizione non può essere più lunga di 200 caratteri");
    }
}
