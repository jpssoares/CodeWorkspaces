package exceptions;

public class FriendListEmptyException extends Exception {

    private static final String FRIEND_LIST_EMPTY_MESSAGE = "%s has no friends!\n";

    public FriendListEmptyException() {
        super(FRIEND_LIST_EMPTY_MESSAGE);
    }
}
