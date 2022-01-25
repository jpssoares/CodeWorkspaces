package exceptions;

public class UserAlreadyExistsException extends Exception {

    private static final String USER_ALREADY_EXISTS_MESSAGE = "%s already exists!\n";

    public UserAlreadyExistsException() {
        super(USER_ALREADY_EXISTS_MESSAGE);
    }
}
