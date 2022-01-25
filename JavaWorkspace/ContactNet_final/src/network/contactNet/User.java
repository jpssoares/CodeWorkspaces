package network.contactNet;

import network.dataStructures.Entry;
import network.dataStructures.Iterator;
import network.exceptions.*;

/**
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public interface User extends Comparable<User> {

	/**
	 * Devolve o login do utilizador.
	 * 
	 * @return login
	 */
	String getLogin();

	/**
	 * Devolve o nome do utilizador.
	 * 
	 * @return nome
	 */
	String getName();

	/**
	 * Devolve a idade do utilizador.
	 * 
	 * @return idade (numero inteiro)
	 */
	int getAge();

	/**
	 * Devolve a morada do utiliador.
	 * 
	 * @return morada
	 */
	String getAddress();

	/**
	 * Devolve a profissao do utilizador.
	 * 
	 * @return profissao
	 */
	String getProfession();

	/**
	 * Insere um utilizador na lista de contactos.
	 *
	 * @param login - login do user
	 * @param user  - objeto da classe User
	 * @throws ContactExists - ocorre caso o utilizador ja pertenca a lista de
	 *                       contactos
	 */
	void insertContact(String login, User user) throws ContactExists;

	/**
	 * Verifica se um dado utilizador pertence a lista de contactos.
	 * 
	 * @param login - login do User
	 * @return <code>true<code> caso o utilizador pertenca a lista de contactos
	 */
	boolean hasContact(String login);

	/**
	 * Verifica se o utilizador nao tem contactos.
	 * 
	 * @return <code>true<code> caso a lista de contactos esteja vazia
	 */
	boolean noContacts();

	/**
	 * Remove um contacto da lista.
	 * 
	 * @param login - login do User
	 * @throws ContactNotExists - ocorre caso o utilizador dado nao pertenca a lista
	 *                          de contactos
	 */
	void removeContact(String login) throws ContactNotExists;

	/**
	 * Devolve a lista de contactos.
	 * 
	 * @return iterador de contactos
	 * @throws NoContacts - ocorre caso a lista de contactos esteja vazia
	 */
	Iterator<Entry<String, User>> contactIterator() throws NoContacts;

	/**
	 * Adiciona um grupo a lista.
	 * 
	 * @param g - objeto da classe Group
	 * @throws SubscriptionExists - ocorre caso o grupo ja pertenca a lista
	 */
	void addGroup(Group g) throws SubscriptionExists, GroupFull;

	/**
	 * Adiciona uma mensagem recebida a lista.
	 * 
	 * @param m - objeto da classe Message
	 */
	void addMessage(Message m);

	/**
	 * Envia uma mensagem a todos os contactos e a todos os grupos das listas.
	 * 
	 * @param m - objeto da classe Message
	 */
	void sendMessage(Message m);

	/**
	 * Remove um dado grupo da lista.
	 * 
	 * @param g - objeto da classe Group
	 * @throws SubscriptionNotExists - ocorre caso o grupo nao pertenca a lista
	 */
	void removeSubscription(Group g) throws SubscriptionNotExists;

	/**
	 * Verifica se o utilizador recebeu alguma mensagem.
	 * 
	 * @return <code>true<code> caso tenha recebido alguma mensagem.
	 */
	boolean hasMessages();

	/**
	 * Devolve a lista de mensagens rcebidas.
	 * 
	 * @return iterador de mensagens
	 * @throws NoContactMessages - ocorre caso nao tenha recebido mensagens.
	 */
	Iterator<Message> listMessages() throws NoContactMessages;;
}
