package Exception;

public class EmployeeException extends Exception {

    public EmployeeException(String message) {
        super("Employee Exception: " + message);
    }
}