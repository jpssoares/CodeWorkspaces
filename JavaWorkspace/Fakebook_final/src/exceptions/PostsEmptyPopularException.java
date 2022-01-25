package exceptions;

public class PostsEmptyPopularException extends Exception {

    private static final String POSTS_EMPTY_POPULAR_POST_MESSAGE = "Social distancing has reached fakebook. Please post something.";

    public PostsEmptyPopularException() {
        super(POSTS_EMPTY_POPULAR_POST_MESSAGE);
    }
}
