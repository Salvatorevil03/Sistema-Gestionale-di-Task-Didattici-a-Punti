package exceptions;

public class StudentException extends RuntimeException{
    public StudentException(){
        super("Non è stato possibile trovare lo studente ");
    }
}
