package network.contactNet;

import network.dataStructures.*;
import network.exceptions.*;

/**
 * Classe responsavel pelo tratamento de comandos.
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class ContactNetClass implements ContactNet {

	// Declaracao dos dicionarios que serao usadas para armazenar os utilizadores e
	// grupos no sistema

	// Um utilizador(User) e identificado por um login(String)
	private Dictionary<String, User> users;

	// Um grupo(Group) e identificado pelo seu nome(String)
	private Dictionary<String, Group> groups;

	public ContactNetClass() {
		// Inicializacao das ChainedHashTables que serao usadas para armazenar os dados
		// da classe topo
		users = new ChainedHashTable<>();
		groups = new ChainedHashTable<>();
	}

	@Override
	public void insertUser(String login, String name, int age, String address, String profession) throws UserExists {
		if (hasUser(login))
			throw new UserExists();
		User u = new UserClass(login, name, age, address, profession);
		users.insert(login, u);
	}

	@Override
	public User showUser(String login) throws UserNotExists {
		User u = users.find(login);

		if (u == null)
			throw new UserNotExists();
		return u;
	}

	@Override
	public void insertContact(String login1, String login2) throws UserNotExists, ContactExists {
		User user1 = showUser(login1);
		User user2 = showUser(login2);
		if (login1.equals(login2))
			throw new ContactExists();
		user1.insertContact(login2, user2);
		user2.insertContact(login1, user1);
	}

	@Override
	public void removeContact(String login1, String login2) throws UserNotExists, ContactNotExists, ContactNotRemoved {
		User user1 = showUser(login1);
		User user2 = showUser(login2);
		if (user1.equals(user2))
			throw new ContactNotRemoved();
		if (!user1.hasContact(login2))
			throw new ContactNotExists();
		user1.removeContact(login2);
		user2.removeContact(login1);
	}

	@Override
	public Iterator<Entry<String, User>> listContacts(String login) throws UserNotExists, NoContacts {
		return showUser(login).contactIterator();
	}

	@Override
	public void insertGroup(String group, String description) throws GroupExists {
		if (hasGroup(group))
			throw new GroupExists();

		Group g = new GroupClass(group, description);
		groups.insert(group, g);
	}

	@Override
	public Group showGroup(String group) throws GroupNotExists {
		Group g = groups.find(group);

		if (g == null)
			throw new GroupNotExists();
		return g;
	}

	@Override
	public void removeGroup(String group) throws GroupNotExists { // TODO groupNotExists necessaria?
		Group g = groups.remove(group);
		if (g == null)
			throw new GroupNotExists();
	}

	@Override
	public void subscribeGroup(String login, String group)
			throws UserNotExists, GroupNotExists, SubscriptionExists, GroupFull {
		User u = showUser(login);
		Group g = showGroup(group);

		if (g.hasParticipant(login))
			throw new SubscriptionExists();

		g.insertParticipant(login, u); // TODO VERIFICAR ERRO COM AVL TREE NESTE METODO
		u.addGroup(g);

	}

	@Override
	public void removeSubscription(String login, String group)
			throws UserNotExists, GroupNotExists, SubscriptionNotExists {
		User u = showUser(login);
		Group g = showGroup(group);

		g.removeParticipant(login);
		u.removeSubscription(g);

	}

	@Override
	public void insertMessage(String login, String title, String text, String url) throws UserNotExists {
		showUser(login).sendMessage(new MessageClass(title, text, url));
	}

	@Override
	public Iterator<Message> listContactMessages(String login1, String login2)
			throws UserNotExists, ContactNotExists, NoContactMessages {
		User user1 = showUser(login1);
		showUser(login2);
		if (!login1.equals(login2) && !user1.hasContact(login2))
			throw new ContactNotExists();

		return user1.listMessages();
	}

	@Override
	public Iterator<Message> listGroupMessages(String group, String login)
			throws GroupNotExists, UserNotExists, SubscriptionNotExists, NoGroupMessages {
		Group g = showGroup(group);
		showUser(login);
		if (!g.hasParticipant(login))
			throw new SubscriptionNotExists();

		return g.listGroupMessages();
	}

	@Override
	public Iterator<Entry<String, User>> listParticipants(String group) throws GroupNotExists, NoParticipants {
		return showGroup(group).listParticipants();
	}

	// Auxiliary methods

	/**
	 * @param login - Login do utilizador
	 * @return - Retorna true se o utilizador com o dado login existe
	 */
	private boolean hasUser(String login) {
		return (users.find(login) != null);
	}

	/**
	 * @param group - Nome do grupo
	 * @return - Retorna true se o gruoo com o nome dado existe
	 */
	private boolean hasGroup(String group) {
		return (groups.find(group) != null);
	}

}
