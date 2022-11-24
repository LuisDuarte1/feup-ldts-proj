package ldts.terrarialike.exceptions;

public class ItemNotFoundException extends Throwable{

    public ItemNotFoundException(String error_message){

        super(error_message);
    }
}
