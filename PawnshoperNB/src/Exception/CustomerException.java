package Exception;

public class CustomerException extends Exception {

    /**
     * Customer Exception
     *
     * @param message
     */
    public CustomerException(String message) {
        super("Customer Exception: " + message);
    }
}
