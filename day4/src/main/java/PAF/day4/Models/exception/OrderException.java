package PAF.day4.Models.exception;

public class OrderException extends RuntimeException{
    public OrderException(){
        super();
    }

    public OrderException(String msg){
        super(msg);
    }

    public OrderException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
