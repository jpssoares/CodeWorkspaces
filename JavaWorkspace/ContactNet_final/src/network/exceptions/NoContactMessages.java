package network.exceptions;

/**
 * Esta excepcao e lancada quando o contacto entre os utilizadores com os logins
 * dados nao tem mensagens registadas.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class NoContactMessages extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MSG = "Contacto nao tem mensagens.";

	public NoContactMessages() {
		super(DEFAULT_MSG);
	}
}
