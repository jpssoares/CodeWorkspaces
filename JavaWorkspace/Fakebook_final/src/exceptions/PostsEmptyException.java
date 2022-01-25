package exceptions;

public class PostsEmptyException extends Exception {

    private static final String POSTS_EMPTY_MESSAGE = "%s has no posts!\n";

    public PostsEmptyException() {
        super(POSTS_EMPTY_MESSAGE);
    }
}
