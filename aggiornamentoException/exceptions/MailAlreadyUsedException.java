package exceptions;

public class MailAlreadyUsedException extends RuntimeException {

    public MailAlreadyUsedException() {
        super("L'email che stai tentando di usare è già presente nel sistema");
    }

    public MailAlreadyUsedException(String message) {
        super(message);
    }

    public MailAlreadyUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailAlreadyUsedException(Throwable cause) {
        super(cause);
    }
}
