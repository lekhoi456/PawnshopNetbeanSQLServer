package Exception;

public class StoreException extends Exception {

    /**
     * Store Exception
     *
     * @param message
     */
    public StoreException(String message) {
        super("Store Exception: " + message);
    }
}
