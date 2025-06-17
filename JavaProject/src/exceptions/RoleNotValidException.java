package exceptions;

public class RoleNotValidException extends RuntimeException {

    public RoleNotValidException() {
        super("Il ruolo specificato non è valido.");
    }

    public RoleNotValidException(String message) {
        super(message);
    }

    public RoleNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleNotValidException(Throwable cause) {
        super(cause);
    }
}
