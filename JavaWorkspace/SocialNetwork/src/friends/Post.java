package friends;

/**
 * Uma publicacao a associar a uma pessoa, considerando que esta pode ter sido
 * escrita por si ou por um amigo
 * 
 * @author Joao Soares N57609
 *
 */
public interface Post {
	/**
	 * Devolve o nome da pessoa que adicionou o post.
	 * 
	 * @return nome do autor
	 */
	String getAuthor();

	/**
	 * Devolve a mensagem do post.
	 * 
	 * @return o post
	 */
	String getMessage();
}
