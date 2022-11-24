package ldts.terrarialike.exceptions;

public class InvalidQuantityException extends Throwable{
    public InvalidQuantityException(String error_message){
        super(error_message);
    }
}

