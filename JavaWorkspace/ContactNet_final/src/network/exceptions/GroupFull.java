package network.exceptions;

/**
 * Esta excepcao e lancada quando o grupo ao que o utilizador esta a ser
 * adicionado esta cheio.
 *
 * @author Joao Soares, Ricardo Silva
 *
 */
public class GroupFull extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MSG = "Grupo ja atingiu o limite de participantes.";

	public GroupFull() {
		super(DEFAULT_MSG);
	}
}
