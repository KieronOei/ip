/**
 * Exception specific to Cortana chatbot application.
 * Represents errors or exceptional conditions related to Cortana operations.
 */
public class CortanaException extends Exception {

    /**
     * Creates a new CortanaException with the specified error message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public CortanaException(String message) {
        super(message);
    }
}
