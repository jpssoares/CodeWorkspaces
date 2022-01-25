package exceptions;

public class InvalidHashtagsException extends Exception {

    private static final String INVALID_HASHTAGS_MESSAGE = "Invalid hashtags list!";

    public InvalidHashtagsException() {
        super(INVALID_HASHTAGS_MESSAGE);
    }

}
