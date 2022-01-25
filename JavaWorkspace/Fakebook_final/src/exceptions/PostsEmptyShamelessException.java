package exceptions;

public class PostsEmptyShamelessException extends Exception {

    private static final String POSTS_EMPTY_SHAMELESS_MESSAGE = "Social distancing has reached fakebook. Post a lie and become the king of liars.";

    public PostsEmptyShamelessException() {
        super(POSTS_EMPTY_SHAMELESS_MESSAGE);
    }
}
