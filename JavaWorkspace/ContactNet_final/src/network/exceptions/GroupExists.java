package network.exceptions;

/**
 * Esta excepcao e lancada quando ja existe um grupo no sistema igual ao que
 * esta a ser criado.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class GroupExists extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MSG = "Grupo ja existente.";

	public GroupExists() {
		super(DEFAULT_MSG);
	}
}
