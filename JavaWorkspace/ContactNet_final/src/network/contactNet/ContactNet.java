package network.contactNet;

import network.dataStructures.Entry;
import network.dataStructures.Iterator;
import network.exceptions.*;

/**
 * Interface da Classe Topo.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public interface ContactNet {

	// Tem como funcionalidades principais:
	// - registar um utilizador u;
	// - registar um contacto c (pessoa com quem o utilizador u contacta - por
	// exemplo, o contacto c pode ser um funcionario do cafe que o utilizador u
	// costuma frequentar);
	// - registrar um grupo de contactos(por exemplo, Judo-Caparica-Quarta);
	// - inserir um utilizador num grupo;
	// - consultar publicacoes(mensagens) de um contacto ou um grupo.

	/**
	 * Consulta os dados de um dado utilizador.
	 * 
	 * @param login - login do utilizador
	 * @return Utilizador - objeto da class User
	 * @throws UserNotExists - ocorre quando o login dado nao existe no sistema
	 */
	User showUser(String login) throws UserNotExists;

	/**
	 * Regista um novo utilizador no sistema.
	 * 
	 * @param login      - login do utilizador(tem de ser unico)
	 * @param name       - nome do utilizador
	 * @param age        - idade do utilizador
	 * @param address    - localidade do utilizador
	 * @param profession - profissao do utilizador
	 * @throws UserExists - ocorre quando o login dado ja existe no sistema
	 */
	void insertUser(String login, String name, int age, String address, String profession) throws UserExists;

	/**
	 * Regista contacto entre dois utilizadores.
	 * 
	 * @param login1 - login do primeiro utilizador
	 * @param login2 - - login do segundo utilizador
	 * @throws UserNotExists - ocorre quando pelo menos um dos logins dados nao
	 *                       existe no sistema
	 * @throws ContactExists - ocorre quando os logins dados sao iguais ou quando o
	 *                       ja existe um contacto entre os dois utilizadores
	 */
	void insertContact(String login1, String login2) throws UserNotExists, ContactExists;

	/**
	 * Remove contacto entre dois utilizadores.
	 * 
	 * @param login1 - login do primeiro utilizador
	 * @param login2 - login do segundo utilizador
	 * @throws UserNotExists     - ocorre quando pelo menos um dos logins dados nao
	 *                           existe no sistema
	 * @throws ContactNotExists  - ocorre quando os logins dados nao tem contacto um
	 *                           com o outro
	 * @throws ContactNotRemoved - ocorre quando os logins dados sao iguais
	 */
	void removeContact(String login1, String login2) throws UserNotExists, ContactNotExists, ContactNotRemoved;

	/**
	 * Consulta os dados de um dado grupo.
	 * 
	 * @param group - nome do grupo
	 * @return Grupo - objeto da classe Group
	 * @throws GroupNotExists - ocorre quando o nome dado nao existe no sistema
	 */
	Group showGroup(String group) throws GroupNotExists;

	/**
	 * Regista um novo grupo no sistema.
	 * 
	 * @param group       - nome do grupo
	 * @param description - descricao do grupo
	 * @throws GroupExists - ocorre quando o nome dado ja existe no sistema
	 */
	void insertGroup(String group, String description) throws GroupExists;

	/**
	 * Remove um grupo do sistema.
	 * 
	 * @param group - nome do grupo
	 * @throws GroupNotExists - ocorre quando o nome dado nao existe no sistema
	 */
	void removeGroup(String group) throws GroupNotExists;

	/**
	 * Insere um utilizador num grupo.
	 * 
	 * @param login - login do utilizador
	 * @param group - nome do grupo
	 * @throws UserNotExists      - ocorre quando o login dado nao existe no sistema
	 * @throws GroupNotExists     - ocorre quando o nome dado nao existe no sistema
	 * @throws SubscriptionExists - ocorre quando o login dado ja esta inserido no
	 *                            grupo
	 */
	void subscribeGroup(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionExists, GroupFull;

	/**
	 * Remove um participante de um grupo.
	 * 
	 * @param login - login do utilizador
	 * @param group - nome do grupo
	 * @throws UserNotExists         - ocorre quando o login dado nao existe no
	 *                               sistema
	 * @throws GroupNotExists        - ocorre quando o nome dado nao existe no
	 *                               sistema
	 * @throws SubscriptionNotExists - ocorre quando o login dado nao esta inserido
	 *                               no grupo
	 */
	void removeSubscription(String login, String group) throws UserNotExists, GroupNotExists, SubscriptionNotExists;

	/**
	 * Regista mensagem associada a um dado utilizador.
	 * 
	 * @param login - login do utilizador
	 * @param title - titulo da mensagem
	 * @param text  - texto das mensagem
	 * @param url   - url da mensagem
	 * @throws UserNotExists - ocorre quando o login dado nao existe no sistema
	 */
	void insertMessage(String login, String title, String text, String url) throws UserNotExists;

	/**
	 * Lista os contactos de um utilizador.
	 * 
	 * @param login - login do utilizador
	 * @return Lista de contactos - iterador de utilizadores
	 * @throws UserNotExists - ocorre quando o login dado ja existe no sistema
	 * @throws NoContacts    - ocorre quando o login dado nao tem nenhum contacto
	 */
	Iterator<Entry<String, User>> listContacts(String login) throws UserNotExists, NoContacts;

	/**
	 * Lista os utilizadores inseridos num grupo.
	 * 
	 * @param group - nome do grupo
	 * @return Lista de utilizadores - iterador de utilizadores
	 * @throws GroupNotExists - ocorre quando o nome dado nao existe no sistema
	 * @throws NoParticipants - ocorre quando o grupo em questao nao tem nenhum
	 *                        utilizador
	 */
	Iterator<Entry<String, User>> listParticipants(String group) throws GroupNotExists, NoParticipants;

	/**
	 * Lista as mensagem de um contacto.
	 * 
	 * @param login1 - login do primeiro utilizador
	 * @param login2 - login do segundo utilizador
	 * @return Lista de mensagens - iterador de mensagens
	 * @throws UserNotExists     - ocorre quando pelo menos um dos logins dados nao
	 *                           existe no sistema
	 * @throws ContactNotExists  - ocorre quando o login dado nao existe no sistema
	 * @throws NoContactMessages - ocorre caso o utilizador nao tenha recebido
	 *                           nenhuma mensagem
	 */
	Iterator<Message> listContactMessages(String login1, String login2)
			throws UserNotExists, ContactNotExists, NoContactMessages;

	/**
	 * Lista as mensagem de um grupo.
	 * 
	 * @param group - nome do grupo
	 * @param login - login do utilizador
	 * @return Lista de mensagens - iterador de mensagens
	 * @throws GroupNotExists        - ocorre quando o nome dado nao existe no
	 *                               sistema
	 * @throws UserNotExists         - ocorre quando o login dado nao existe no
	 *                               sistema
	 * @throws SubscriptionNotExists - ocorre quando o login dado nao esta inserido
	 *                               no grupo
	 * @throws NoGroupMessages       - ocorre quando nao foram enviadas mensagens
	 *                               para o grupo
	 */
	Iterator<Message> listGroupMessages(String group, String login)
			throws GroupNotExists, UserNotExists, SubscriptionNotExists, NoGroupMessages;
}
