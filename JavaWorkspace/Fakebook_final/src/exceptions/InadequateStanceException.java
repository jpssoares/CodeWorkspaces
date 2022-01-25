package exceptions;

public class InadequateStanceException extends Exception {

    private static final String INADEQUATE_STANCE_MESSAGE = "Inadequate stance!";

    public InadequateStanceException() {
        super(INADEQUATE_STANCE_MESSAGE);
    }

}
