package friends;

/**
 * A rede social para varios amigos
 * 
 * @author Joao Soares N57609
 *
 */

public interface SocialNetwork {

	/**
	 * Devolve o numero de pessoas registadas no SocialNetwork.
	 * 
	 * @return numero de pessoas
	 */
	int numberOfPeople();

	/**
	 * Devolve o estado da pessoa com o nome dado.
	 */
	String getStatus(String name);

	/**
	 * Adiciona uma nova pessoa ao SocialNetwork.
	 * 
	 * @param name   - nome da pessoa nova
	 * @param email  da pessoa nova
	 * @param status da pessoa nova
	 */
	void addPerson(String name, String email, String status);

	/**
	 * Adiciona um novo post ao mural de uma pessoa.
	 * 
	 * @param name   - nome do dono do mural
	 * @param author - nome do autor do post
	 * @param msg    - mensagem do post
	 */
	void addPost(String name, String author, String msg);

	/**
	 * Adiciona uma nova amizade no SocialNetwork. Os nomes sao adicionados as
	 * listas de amigos um do outro.
	 * 
	 * @pre hasFriendship(n1, n2) != true
	 * @param n1 - nome da primeira pessoa
	 * @param n2 - nome da segunda pessoa
	 */
	void addFriendship(String n1, String n2);

	/**
	 * Modifica o estado da pessoa.
	 * 
	 * @param msg - novo estado
	 */
	void changeStatus(String name, String status);

	/**
	 * Consulta se uma dada pessoa esta registada no sistema.
	 * 
	 * @param name - nome da pessoa em questao
	 * @return <code>true<code> no caso de existir uma pessoa no SocialNetwork com o
	 *         nome dado
	 */
	boolean hasPerson(String name);

	/**
	 * Consulta se uma amizade existe no sistema.
	 * 
	 * @param n1 - nome da primeira pessoa
	 * @param n2 - nome da segunda pessoa
	 * @return <code>true<code> no caso de as duas pessoas existirem no
	 *         SocialNetwork e ambas tiverem o nome uma da outra na sua lista de
	 *         amigos
	 */
	boolean hasFriendship(String n1, String n2);

	/**
	 * Verifica se existem uma ou mais pessoas registadas no sistema
	 * 
	 * @return <code>true<code> se nao hourem pessoas registadas
	 */
	boolean isEmpty();

	/**
	 * Obtem os dados disponiveis acerca da pessoa com o nome dado.
	 * 
	 * @param name - nome da pessoa
	 * @return Pessoa
	 */
	Person getPerson(String name);

	/**
	 * Obtem os dados disponiveis acerca da pessoa com o indice dado.
	 * 
	 * @param num - indice da pessoa
	 * @return Pessoa
	 */
	Person getPersonWithIndex(int num);

	/**
	 * Devolve a lista de amigos da pessoa com o nome dado.
	 * 
	 * @param name - nome da pessoa em questao
	 * @return lista de amigos
	 */
	FriendIterator listFriends(String name);

	/**
	 * Devolve a lista de posts do mural da pessoa com o nome dado.
	 * 
	 * @param name - nome da pessoa em questao
	 * @return lista de posts
	 */
	PostIterator listPosts(String name);
}
