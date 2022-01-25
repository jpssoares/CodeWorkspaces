package exceptions;

public class SameUserFriendshipException extends Exception {

    private static final String SAME_USER_FRIENDSHIP_MESSAGE = "%s cannot be the same as %s!\n";

    public SameUserFriendshipException() {
        super(SAME_USER_FRIENDSHIP_MESSAGE);
    }
}
