package exceptions;

public class InvalidDateException extends RuntimeException{
    public InvalidDateException(){
        super("Non è possibile inserire una data non esistente o passata");
    }
}
