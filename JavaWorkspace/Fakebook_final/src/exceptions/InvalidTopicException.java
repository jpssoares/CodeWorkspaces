package exceptions;

public class InvalidTopicException extends Exception {

    private static final String INVALID_TOPIC_MESSAGE = "Oh please, who would write about %s?\n";

    public InvalidTopicException() {
        super(INVALID_TOPIC_MESSAGE);
    }
}