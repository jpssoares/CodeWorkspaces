package exceptions;

public class ImpossibleCommentException extends Exception {

    private static final String IMPOSSIBLE_COMMENT_MESSAGE = "%s cannot comment on this post!\n";

    public ImpossibleCommentException() {
        super(IMPOSSIBLE_COMMENT_MESSAGE);
    }
}
