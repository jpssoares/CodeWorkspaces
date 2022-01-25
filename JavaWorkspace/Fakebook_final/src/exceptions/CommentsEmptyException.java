package exceptions;

public class CommentsEmptyException extends Exception {

    private static final String COMMENTS_EMPTY_MESSAGE = "No comments!";

    public CommentsEmptyException() {
        super(COMMENTS_EMPTY_MESSAGE);
    }
}
