package network.exceptions;

/**
 * Esta excepcao e lancada quando o nome nao corresponde a nenhum dos grupos
 * registados no sistema.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class GroupNotExists extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MSG = "Inexistencia do grupo referido.";

	public GroupNotExists() {
		super(DEFAULT_MSG);
	}
}
