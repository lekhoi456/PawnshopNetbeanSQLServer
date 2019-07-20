package Exception;

public class CustomerException extends Exception {

    public CustomerException(String message) {
        super("Customer Exception: " + message);
    }
}