package exceptions;

public class UnexistingFriendshipException extends Exception {

    private static final String UNEXISITNG_FRIENDSHIP_MESSAGE = "%s has no access to post %s by %s!\n";

    public UnexistingFriendshipException() {
        super(UNEXISITNG_FRIENDSHIP_MESSAGE);
    }
}
