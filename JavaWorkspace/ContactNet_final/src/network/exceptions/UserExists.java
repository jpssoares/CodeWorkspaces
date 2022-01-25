package network.exceptions;

/**
 * Esta excepcao e lancada quando o utilizador ja existe no sistema.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class UserExists extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MSG = "Utilizador ja existente.";

	public UserExists() {
		super(DEFAULT_MSG);
	}

}
