package exceptions;

public class ClassEnrollmentException extends RuntimeException {

    public ClassEnrollmentException() {
        super("Errore durante l'iscrizione alla classe.");
    }
}
