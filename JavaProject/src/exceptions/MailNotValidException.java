package exceptions;

public class MailNotValidException extends RuntimeException {

    public MailNotValidException() {
        super("L'indirizzo email inserito non è valido.");
    }

}
