package exceptions;

public class UnexistingPostException extends Exception {

    private static final String UNEXISTENT_POST_MESSAGE = "%s has no post %s!\n";

    public UnexistingPostException() {
        super(UNEXISTENT_POST_MESSAGE);
    }
}
