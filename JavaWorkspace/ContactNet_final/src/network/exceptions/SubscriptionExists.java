package network.exceptions;

/**
 * Esta excepcao e lancada quando o utilizador ja pertence ao grupo dado.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class SubscriptionExists extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MSG = "Existencia de aderencia referida.";

	public SubscriptionExists() {
		super(DEFAULT_MSG);
	}
}
