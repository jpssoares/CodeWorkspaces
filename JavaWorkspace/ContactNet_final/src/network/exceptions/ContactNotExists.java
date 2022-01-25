package network.exceptions;

/**
 * Esta excepcao e lancada quando os logins dados nao tem contacto um com o
 * outro.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class ContactNotExists extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MSG = "Inexistencia do contacto referido.";

	public ContactNotExists() {
		super(DEFAULT_MSG);
	}
}
