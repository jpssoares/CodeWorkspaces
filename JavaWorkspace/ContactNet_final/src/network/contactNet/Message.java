package network.contactNet;

/**
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public interface Message {

	/**
	 * Devolve o titulo da mensagem.
	 * 
	 * @return titulo
	 */
	String getTitle();

	/**
	 * Devolve o textio da mensagem.
	 * 
	 * @return texto
	 */
	String getText();

	/**
	 * Devolve o URL da mensagem.
	 * 
	 * @return url
	 */
	String getUrl();

}
