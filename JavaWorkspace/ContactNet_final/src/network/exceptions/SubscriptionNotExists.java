package network.exceptions;

/**
 * Esta excepcao e lancada quando o utilizador nao pertence ao grupo dado.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class SubscriptionNotExists extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MSG = "Inexistencia de aderencia referida.";

	public SubscriptionNotExists() {
		super(DEFAULT_MSG);
	}
}
