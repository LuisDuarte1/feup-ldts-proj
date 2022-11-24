package ldts.terrarialike.exceptions;

public class InvalidSizeException extends Throwable{
    public InvalidSizeException(String error_message){
        super(error_message);
    }
}
