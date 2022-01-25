package network.exceptions;

/**
 * Esta excepcao e lancada quando se tenta remover um contacto entre dois
 * utilizadores iguais.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class ContactNotRemoved extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MSG = "Contacto nao pode ser removido.";

	public ContactNotRemoved() {
		super(DEFAULT_MSG);
	}
}
