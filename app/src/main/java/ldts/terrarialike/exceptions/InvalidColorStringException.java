package ldts.terrarialike.exceptions;

public class InvalidColorStringException extends Throwable {

    public InvalidColorStringException() {
    }

    public InvalidColorStringException(String message) {
        super(message);
    }

    public InvalidColorStringException(Throwable cause) {
        super(cause);
    }

    public InvalidColorStringException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidColorStringException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
