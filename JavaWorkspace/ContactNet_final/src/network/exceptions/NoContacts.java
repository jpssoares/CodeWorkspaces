package network.exceptions;

/**
 * Esta excepcao e lancada quando o utilizador dado nao possui contactos.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class NoContacts extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MSG = "Inexistencia de contactos.";

	public NoContacts() {
		super(DEFAULT_MSG);
	}
}
