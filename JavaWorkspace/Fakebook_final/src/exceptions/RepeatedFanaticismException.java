package exceptions;

public class RepeatedFanaticismException extends Exception {

    private static final String REPEATED_FANATICISM_MESSAGE = "Invalid fanaticism list!";

    public RepeatedFanaticismException() {
        super(REPEATED_FANATICISM_MESSAGE);
    }
}
