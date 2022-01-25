package exceptions;

public class UnexistingUserException extends Exception {

    private static final String UNEXISTING_USER_MESSAGE = "%s does not exist!\n";

    public UnexistingUserException() {
        super(UNEXISTING_USER_MESSAGE);
    }
}
