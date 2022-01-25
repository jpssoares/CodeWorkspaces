package exceptions;

public class UserListEmptyException extends Exception {

    private static final String USER_LIST_EMPTY_MESSAGE = "There are no users!";

    public UserListEmptyException() {
        super(USER_LIST_EMPTY_MESSAGE);
    }
}
