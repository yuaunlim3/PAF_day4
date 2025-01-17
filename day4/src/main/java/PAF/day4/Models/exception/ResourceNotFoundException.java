package PAF.day4.Models.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super();
    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }

    public ResourceNotFoundException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
