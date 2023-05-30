/**
 * Custom exception class to handle invalid argument size.
 */
package fr.cs.Group13.myVelib.VelibCLUI;

public class InvalidArgumentSizeException extends RuntimeException {

    /**
     * Constructs an InvalidArgumentSizeException with the specified error message.
     *
     * @param message The error message.
     */
    public InvalidArgumentSizeException(String message) {
        super(message);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}