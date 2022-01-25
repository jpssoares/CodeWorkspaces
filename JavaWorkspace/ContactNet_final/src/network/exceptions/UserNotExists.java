package network.exceptions;

/**
 * Esta excepcao e lancada quando o utilizador dado nao existe no sistema.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class UserNotExists extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MSG = "Inexistencia do utilizador referido.";

	public UserNotExists() {
		super(DEFAULT_MSG);
	}

}
