package network.exceptions;

/**
 * Esta excepcao e lancada quando o grupo dado nao tem mensagens.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class NoGroupMessages extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MSG = "Grupo nao tem mensagens.";

	public NoGroupMessages() {
		super(DEFAULT_MSG);
	}
}
