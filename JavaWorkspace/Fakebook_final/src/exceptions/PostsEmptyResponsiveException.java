package exceptions;

public class PostsEmptyResponsiveException extends Exception {

    private static final String POSTS_EMPTY_RESPONSIVE_MESSAGE = "Social distancing has reached fakebook. Post something and then comment your own post to become the king of responsiveness.";

    public PostsEmptyResponsiveException() {
        super(POSTS_EMPTY_RESPONSIVE_MESSAGE);
    }
}
