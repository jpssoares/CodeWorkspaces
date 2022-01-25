package network.exceptions;

/**
 * Esta excepcao e lancada quando os dois utilizadores ja tem um contacto
 * registado no sistema.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class ContactExists extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MSG = "Existencia do contacto referido.";

	public ContactExists() {
		super(DEFAULT_MSG);
	}

}
