package Exception;

public class ContractException extends Exception {

    /**
     * Contract Exception
     *
     * @param message
     */
    public ContractException(String message) {
        super("Contract Exception: " + message);
    }
}
