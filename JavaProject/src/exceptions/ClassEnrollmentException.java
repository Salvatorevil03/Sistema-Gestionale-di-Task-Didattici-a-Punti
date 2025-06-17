package exceptions;

public class ClassEnrollmentException extends RuntimeException {

    public ClassEnrollmentException() {
        super("La classe a cui stai tentando di iscriverti non esiste");
    }
}
