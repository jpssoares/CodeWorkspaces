package network.dataStructures;

/**
 * Esta excepcao e lancada quando nao e encontrado nenhuma elemento da lista igual ao elemento dado.
 * @author Joao Soares, Ricardo Silva
 *
 */
public class NoSuchElementException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_MSG = "No such element.";

	public NoSuchElementException( )
	{
		super(DEFAULT_MSG);
	}
	
	public NoSuchElementException( String msg )
	{
		super(msg);
	}
}
