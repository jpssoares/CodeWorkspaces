package network.exceptions;

/**
 * Esta excepcao e lancada quando o grupo dado nao possui participantes.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class NoParticipants extends Exception {
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MSG = "Inexistencia de participantes.";

	public NoParticipants() {
		super(DEFAULT_MSG);
	}
}
