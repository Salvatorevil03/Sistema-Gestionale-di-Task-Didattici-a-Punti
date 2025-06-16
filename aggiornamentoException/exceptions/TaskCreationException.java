package exceptions;

public class TaskCreationException extends RuntimeException {

    public TaskCreationException() {
        super("Errore durante la creazione del task.");
    }
}