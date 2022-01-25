package friends;

/**
 * O stupidFriendsBook, para varios amigos
 * @author Miguel Goulao / Adriano Lopes / Carla Ferreira
 *
 */

public interface StupidFriendsBook {
	// Constantes de perfis de personalidade
	static final int INTELLIGENT = 0,ANGEL = 1,BANDIT = 2,STUPID = 3;
	
	/**
	 * Limpa a lista de amigos.
	 */
	void reset();
	
	/**
	 * Verifica se o amigo de nome <code>name</code> existe.
	 * @param name - nome do amigo a verificar a existencia na rede social 
	 * @return - devolve <code>true</code> se o amigo de nome <code>name</code> existe, 
	 * ou <code>false</code> caso contrario
	 */
	boolean hasFriend(String name);

	/**
	 * Adiciona o amigo de nome <code>name</code>
	 * @pre !hasFriend(name)
	 * @param name Nome do amigo a adicionar a rede social
	 */
	void addFriend(String name);

	/**
	 * Remove o amigo de nome <code>name</code>
	 * @pre hasFriend(name)
	 * @param name - nome do amigo a remover.
	 */
	void removeFriend(String name);
	
	
	/**
	 * Verifica se a accao <code>description</code> ja esta associada o amigo de nome <code>name</code>.
	 * @param name - nome da pessoa cuja accao vai ser registada 
	 * @param description - descricao da accao a ser registada
	 * @return - devolve <code>true</code> caso exista uma accao <code>description</code> 
	 * feita por um amigo de nome <code>name</code>, ou <code>true</code> caso contrario
	 */
	boolean hasAction(String name,String description);

	/**
	 * Acrescenta a accao <code>description</code> a pessoa de nome <code>name</code>.
	 * @pre hasFriend(name) && !hasAction(name,description)
	 * @param name - nome da pessoa cuja accao vai ser registada
	 * @param description - descricao da accao a ser registada
	 */
	void addAction(String name, String description);

	/**
	 * Vota numa accao praticada pela pessoa de nome <code>name</code>, com a
	 * descricao <code>description</code>, indicando se ela e positiva, ou 
	 * negativa, para o proximo e para outras pessoas.
	 * @pre hasAction(name,description)
	 * @param name - nome da pessoa cuja accao vai ser votada
	 * @param description - descricao da accao que vai ser votada
	 * @param goodForPerson - indicacao sobre a accao ser positiva (<code>true</code>) ou negativa para o proprio
	 * @param goodForOthers - indicacao sobre a accao ser positiva (<code>true</code>) ou negativa para os outros
	 */
	void vote(String name, String description,
			boolean goodForPerson, boolean goodForOthers);

	/**
	 * Devolve o numero de amigos existentes.
	 * @return - devolve o numero total de amigos
	 */
	int numberOfFriends();

	
	/**
	 * Devolve o nome da pessoa mais aborrecida.
	 * @pre numberOfFriends() > 0
	 * @return - o nome da pessoa mais aborrecida
	 */
	String mostBoringFriend();

	/**
	 * Inicializa o iterador de amigos.
	 * @param kind
	 */
	void initialize(int kind);

	/**
	 * Verifica se existem mais pessoas a iterar.
	 * @return - <code>true</code> se houver mais pessoas a iterar, 
	 * ou <code>false</code> caso contrario.
	 */
	boolean hasNext();

	/**
	 * Devolve a proxima pessoa a iterar, avancando com o iterador
	 * @return - a proxima pessoa.
	 */
	Person next();
}