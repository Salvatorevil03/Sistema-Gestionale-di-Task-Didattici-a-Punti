package exceptions;

public class FormattedDateException extends RuntimeException{
    public FormattedDateException(){
        super("La data non è formattata correttamente");
    }
}
