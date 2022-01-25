package network.contactNet;

import network.dataStructures.*;
import network.exceptions.*;

/**
 *
 * @author Joao Soares, Ricardo Silva
 *
 */
public class GroupClass implements Group {

	// Declaracao das variaveis de instancia dos grupos e das respectivas estruturas
	// de dados
	private String name, description;

	private OrderedDictionary<String, User> participants; // alteramos de ordered sequence(usado na primeira fase) para tree
	private DoublyLinkedList<Message> groupMessages; // utilizamos DoublyLL porque facilita a iteracao na ordem de insercao

	GroupClass(String name, String description) {
		// Inicializacao dos atributos de cada grupo
		this.name = name;
		this.description = description;
		participants = new AVLTree<>();
		groupMessages = new DoublyLinkedList<>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void insertParticipant(String login, User u) {
		participants.insert(login, u);
	}

	@Override
	public void removeParticipant(String login) throws SubscriptionNotExists {
		if (!hasParticipant(login))
			throw new SubscriptionNotExists();

		participants.remove(login);
	}

	@Override
	public void insertGroupMessage(Message m) {
		groupMessages.addFirst(m);
	}

	@Override
	public boolean hasParticipant(String login) {
		return (participants.find(login) != null);
	}

	@Override
	public Iterator<Message> listGroupMessages() throws NoGroupMessages {
		if (!hasMessages())
			throw new NoGroupMessages();
		return groupMessages.iterator();
	}

	@Override
	public boolean hasMessages() {
		return !groupMessages.isEmpty();
	}

	@Override
	public Iterator<Entry<String, User>> listParticipants() throws NoParticipants {
		if (!hasParticipants())
			throw new NoParticipants();
		return participants.iterator();
	}

	@Override
	public boolean hasParticipants() {
		return !(participants.size() == 0);
	}

}
