package exceptions;

public class InvalidNumberPostsException extends Exception {

    private static final String INVALID_NUMBER_POSTS = "Invalid number of posts to present!";

    public InvalidNumberPostsException() {
        super(INVALID_NUMBER_POSTS);
    }
}
