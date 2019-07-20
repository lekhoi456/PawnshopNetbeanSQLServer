package Exception;

public class ContractException extends Exception {

    public ContractException(String message) {
        super("Contract Exception: " + message);
    }
}