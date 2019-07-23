package Exception;

public class EmployeeException extends Exception {

    /**
     * Employee Exception
     *
     * @param message
     */
    public EmployeeException(String message) {
        super("Employee Exception: " + message);
    }
}
