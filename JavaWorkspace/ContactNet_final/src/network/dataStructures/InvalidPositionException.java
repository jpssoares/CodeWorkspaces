package network.dataStructures;

/**
 * Esta excepcao e lancada quando a posicao do lista dada nao e valida.
 * @author Joao Soares, Ricarod Silva
 *
 */
public class InvalidPositionException extends RuntimeException {

    static final long serialVersionUID = 0L;
    
    private static final String DEFAULT_MSG = "Invalid position.";

    public InvalidPositionException( )
    {
        super(DEFAULT_MSG);
    }

    public InvalidPositionException( String message )
    {
        super(message);
    }

}

