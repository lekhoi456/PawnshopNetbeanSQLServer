package Exception;

public class StoreException extends Exception {

    public StoreException(String message) {
        super("Store Exception: " + message);
    }
}