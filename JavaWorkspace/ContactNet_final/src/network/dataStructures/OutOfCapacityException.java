package network.dataStructures;

/**
 * Esta excepcao e usada quando a estrura de dados excede a sua capacidade
 * maxima.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class OutOfCapacityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MSG = "Full data structure.";

	public OutOfCapacityException() {
		super(DEFAULT_MSG);
	}

	public OutOfCapacityException(String msg) {
		super(msg);
	}
}
