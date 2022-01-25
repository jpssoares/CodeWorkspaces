package network.contactNet;

import network.dataStructures.Entry;
import network.dataStructures.Iterator;
import network.exceptions.*;

/**
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public interface Group {

	/**
	 * Devolve o nome do grupo.
	 * 
	 * @return nome
	 */
	String getName();

	/**
	 * Devolve a descricao do grupo.
	 * 
	 * @return descricao
	 */
	String getDescription();

	/**
	 * Insere um utilizador na lista de participantes.
	 *
	 * @param login - login do user
	 * @param u     - utilizador
	 * @throws SubscriptionExists - ocorre quando o utilizador ja pertence ao grupo
	 */
	void insertParticipant(String login, User u) throws SubscriptionExists;

	/**
	 * Remove um utilizador da lista de participantes.
	 *
	 * @param login - login do user
	 * @throws SubscriptionNotExists - ocorre quando o utilizador nao pertence ao
	 *                               grupo
	 */
	void removeParticipant(String login) throws SubscriptionNotExists;

	/**
	 * Insere uma mensagem na lista de mensagens do grupo.
	 * 
	 * @param m - mensagem
	 */
	void insertGroupMessage(Message m);

	/**
	 * Verifica se um dado utilizador faz parte do grupo.
	 * 
	 * @param login - utilizador
	 * @return <code>true<code> caso haja um utilizador na lista de participantes
	 *         igual ao dado
	 */
	boolean hasParticipant(String login);

	/**
	 * Devolve a lista de mensagens do grupo.
	 * 
	 * @return iterador da lista de mensagens
	 */
	Iterator<Message> listGroupMessages() throws NoGroupMessages;

	/**
	 * Verifica se existem mensagens na lista de mensagens do grupo.
	 * 
	 * @return <code>true<code> caso haja mensagens
	 */
	boolean hasMessages();

	/**
	 * Devolve a lista de participantes.
	 * 
	 * @return iterador da lista de participantes
	 * @throws NoParticipants - ocorre quando nao existem participantes
	 */
	Iterator<Entry<String, User>> listParticipants() throws NoParticipants;

	/**
	 * Verifica se existem partipantes.
	 * 
	 * @return <code>true<code> caso haja participantes
	 */
	boolean hasParticipants();
}
