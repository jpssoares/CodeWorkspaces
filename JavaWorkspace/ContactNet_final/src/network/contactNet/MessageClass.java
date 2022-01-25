package network.contactNet;

/**
 *
 * @author Joao Soares, Ricardo Silva
 *
 */
public class MessageClass implements Message {

	// Declaracao e inicializacao das variaveis de instancia de cada mensagem
	private String title, text, url;

	MessageClass(String title, String text, String url) {
		// Inicializacao dos atributos de cada mensagem
		this.title = title;
		this.text = text;
		this.url = url;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public String getUrl() {
		return this.url;
	}

}
