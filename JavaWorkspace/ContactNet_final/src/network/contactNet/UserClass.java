package network.contactNet;

import network.dataStructures.*;
import network.exceptions.*;

/**
 * 
 * @author Joao Soares, Ricardo Silva
 *
 */
public class UserClass implements User {

	// Declaracao e inicializacao das variaveis de instancia de cada utilizador,
	// assim como as suas estruturas de dados
	private String login, name, address, profession;

	private int age;

	private OrderedDictionary<String, User> contactList;
	private List<Message> messages;
	private List<Group> groups;

	UserClass(String login, String name, int age, String address, String profession) {
		// Inicializacao dos atributos de cada utilizador
		this.login = login;
		this.name = name;
		this.age = age;
		this.address = address;
		this.profession = profession;
		contactList = new AVLTree<>(); // utilizamos BST porque possui acessos mais rapidos a uma estrutura ordenada
		groups = new DoublyLinkedList<>(); // utilizamos DoublyLL porque facilita a iteracao na ordem de insercao
		messages = new DoublyLinkedList<>();
	}

	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public String getProfession() {
		return profession;
	}

	@Override
	public void insertContact(String login, User user) throws ContactExists {
		if (hasContact(login))
			throw new ContactExists();
		contactList.insert(login, user);
	}

	@Override
	public boolean hasContact(String login) {
		return (contactList.find(login) != null);
	}

	@Override
	public boolean noContacts() {
		return contactList.size() == 0;
	}

	@Override
	public void removeContact(String login) {
		contactList.remove(login);
	}

	@Override
	public Iterator<Entry<String, User>> contactIterator() throws NoContacts {
		if (noContacts())
			throw new NoContacts();
		return contactList.iterator();
	}

	@Override
	public int compareTo(User u) {
		return u.getLogin().compareTo(login);
	}

	@Override
	public void addGroup(Group g) throws GroupFull {
		// Verificacao nao consta nos testes porem achamos necessaria pois o limite de
		// grupos por participante e 10
		if (groups.size() < 10)
			groups.addLast(g);
		else
			throw new GroupFull();
	}

	@Override
	public void addMessage(Message m) {
		messages.addFirst(m);
	}

	@Override
	public void sendMessage(Message m) {
		Iterator<Entry<String, User>> it = contactList.iterator();
		User u;
		while (it.hasNext()) {
			u = it.next().getValue();
			u.addMessage(m);
		}
		Iterator<Group> it2 = groups.iterator();
		Group g;
		while (it2.hasNext()) {
			g = it2.next();
			g.insertGroupMessage(m);
		}
		messages.addFirst(m);
	}

	@Override
	public void removeSubscription(Group g) {
		groups.remove(g);
	}

	@Override
	public boolean hasMessages() {
		return !messages.isEmpty();
	}

	@Override
	public Iterator<Message> listMessages() throws NoContactMessages {
		if (!hasMessages())
			throw new NoContactMessages();
		return messages.iterator();
	}

}
