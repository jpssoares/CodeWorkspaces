package friends;

/**
 * Uma pessoa, com uma lista de amigos e varios posts associados.
 * 
 * @author Joao Soares N57609
 *
 */
public interface Person {
	int MAX_FRIENDS = 50;
	int DEFAULT = 200;

	/**
	 * Devolve o nome da pessoa em questao.
	 * 
	 * @return nome da pessoa
	 */
	String getName();

	/**
	 * Devolve o email da pessoa em questao.
	 * 
	 * @return email da pessoa
	 */
	String getEmail();

	/**
	 * Devolve o estado da pessoa em questao.
	 * 
	 * @return estado da pessoa
	 */
	String getStatus();

	/**
	 * Verifica se a pessoa tem algum amigo.
	 * 
	 * @return <code>true<code> caso a pessoa tenha um ou mais amigos
	 */
	boolean hasFriends();

	/**
	 * Verifica se uma pessoa tem outra pessoa na sua lista de amigos.
	 * 
	 * @param friend - nome do amigo em questao
	 * @return devolve <code>true</code> se a amizade existir
	 */
	boolean hasFriendship(String friend);

	/**
	 * Modifica o estado da pessoa.
	 * 
	 * @param msg - novo estado
	 */
	void changeStatus(String msg);

	/**
	 * Adiciona uma pessoa a lista de amigos.
	 * 
	 * @pre !hasFriendship(friend)
	 * @param friend - nome do amigo que sera adicionado
	 */
	void addFriendship(String friend);

	/**
	 * Adiciona um post ao mural da pessoa.
	 * 
	 * @param name - o nome do autor do post
	 * @param msg  - mensagem do post
	 */
	void addPost(String name, String msg);

	/**
	 * Devolve os nomes dos amigos da pessoa.
	 * 
	 * @return array de amigos da pessoa
	 */
	String[] getFriends();

	/**
	 * Devolve o numero de amigos.
	 * 
	 * @return numero de amigos
	 */
	int getFriendsCounter();

	/**
	 * Devolve os posts do mural da pessoa.
	 * 
	 * @return posts do mural
	 */
	Post[] getPosts();

	/**
	 * Devolve o numero de posts no mural.
	 * 
	 * @return numero de posts
	 */
	int getPostsCounter();

}
