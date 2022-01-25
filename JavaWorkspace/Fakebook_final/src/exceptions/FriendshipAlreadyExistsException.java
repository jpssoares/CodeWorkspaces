package exceptions;

public class FriendshipAlreadyExistsException extends Exception {

    private static final String FRIENDSHIP_ALREADY_EXISTS_MESSAGE = "%s must really admire %s!\n";

    public FriendshipAlreadyExistsException() {
        super(FRIENDSHIP_ALREADY_EXISTS_MESSAGE);
    }
}
