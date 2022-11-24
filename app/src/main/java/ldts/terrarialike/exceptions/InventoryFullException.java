package ldts.terrarialike.exceptions;

public class InventoryFullException extends Throwable{
    public InventoryFullException(String error_message){
        super(error_message);
    }
}
