/**
 * Custom exception class to handle invalid sorting policy.
 */
package fr.cs.Group13.myVelib.VelibCLUI;

public class InvalidSortingPolicyException extends RuntimeException {

    /**
     * Constructs an InvalidSortingPolicyException with the specified error message.
     *
     * @param message The error message.
     */
    public InvalidSortingPolicyException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
