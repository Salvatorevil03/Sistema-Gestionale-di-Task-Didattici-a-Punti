package exceptions;

public class ClassCreationException extends RuntimeException {

    public ClassCreationException() {
        super("Non è stato possibile creare la classe");
    }

}

