package exceptions;

public class InvalidCommentStanceException extends Exception {

    private static final String INVALID_COMMENT_STANCE_MESSAGE = "Invalid comment stance!";

    public InvalidCommentStanceException() {
        super(INVALID_COMMENT_STANCE_MESSAGE);
    }
}
