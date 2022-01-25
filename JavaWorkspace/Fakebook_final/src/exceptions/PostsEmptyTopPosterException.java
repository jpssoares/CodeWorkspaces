package exceptions;

public class PostsEmptyTopPosterException extends Exception {

    private static final String POST_EMPTY_TOP_POSTER_MESSAGE = "Social distancing has reached fakebook. Post something to become the king of posters.";

    public PostsEmptyTopPosterException() {
        super(POST_EMPTY_TOP_POSTER_MESSAGE);
    }
}
