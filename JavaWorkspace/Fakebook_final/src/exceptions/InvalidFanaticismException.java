package exceptions;

public class InvalidFanaticismException extends Exception {

    private static final String INVALID_FANATICISM_MESSAGE = "Oh please, who would be a fanatic of %s?\n";

    public InvalidFanaticismException() {
        super(INVALID_FANATICISM_MESSAGE);
    }
}