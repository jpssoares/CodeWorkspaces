package friends;

/**
 * Uma publicacao a associar a uma pessoa, considerando que esta pode ter sido
 * escrita por si ou por um amigo
 * 
 * @author Joao Soares N57609
 *
 */
public class PostClass implements Post {
	/**
	 * Devolve o autor
	 */
	private String author;
	/**
	 * Devolve a mensagem
	 */
	private String msg;

	/**
	 * Construtor de PostClass.
	 * 
	 * @param author - Quem escreveu
	 * @param msg    - O que esta escrito
	 */
	public PostClass(String author, String msg) {
		this.author = author;
		this.msg = msg;

	}

	@Override
	public String getAuthor() {
		return author;
	}

	@Override
	public String getMessage() {
		return msg;
	}

}
