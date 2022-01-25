import java.util.Scanner;

import friends.SocialNetworkClass;

/**
 * Programa principal da Rede Social.
 * 
 * @author Joao Soares N57609
 *
 */

public class Main {
	// Comandos do utilizador
	private static final String QUIT = "SAIR";
	private static final String CHECK_PERSON = "CONSULTAPESSOA";
	private static final String REGISTER = "REGISTA";
	private static final String FRIENDS = "AMIGOS";
	private static final String CHECK_FRIENDSHIP = "CONSULTAAMIZADE";
	private static final String CHECK_FRIENDS = "CONSULTAAMIGOS";
	private static final String STATUS = "NOVOESTADO";
	private static final String CHECK_STATUS = "CONSULTAESTADO";
	private static final String PEOPLE = "PESSOAS";

	// Comandos do utilizador(extras)
	private static final String POST_PUBLIC = "POSTARPUBLICO";
	private static final String POST = "POSTAR";
	private static final String TIMELINE = "MURAL";

	// Feedback dado pelo programa
	private static final String REGISTERED = "Pessoa registada.";
	private static final String NOT_REGISTERED = "Sem registo.";
	private static final String REGISTERED_SUCESSFULLY = "Pessoa registada com sucesso.";
	private static final String EXISTING_FRIENDSHIP = "Amizade existente.";
	private static final String UNEXISTING_FRIENDSHIP = "Amizade inexistente.";
	private static final String INVALID_FRIENDSHIP = "Amizade invalida.";
	private static final String NEW_FRIENDSHIP = "Amizade criada.";
	private static final String NO_FRIENDS = "Nao tem amigos registados.";
	private static final String LIST_FRIENDS = "Lista de amigos:";
	private static final String LIST_PEOPLE = "Lista de pessoas registadas:";
	private static final String NEW_STATUS = "Estado alterado.";
	private static final String EMPTY = "Rede Social vazia.";
	private static final String GOODBYE = "Adeus.";

	// Feedback dado pelo programa(extras)
	private static final String POST_REGISTERED = "Post registado.";

	/**
	 * Programa principal. Interpretador de comandos.
	 * 
	 * @param args - argumentos para execucao da aplicacao. Nao sao usados, neste
	 *             programa.
	 */
	public static void main(String[] args) {

		SocialNetworkClass sn = new SocialNetworkClass();
		Scanner in = new Scanner(System.in);
		String command = in.nextLine().toUpperCase();

		while (!command.equals(QUIT)) {
			switch (command) {
			case CHECK_PERSON:
				checkPerson(in, sn);
				System.out.println();
				break;
			case REGISTER:
				register(in, sn);
				System.out.println();
				break;
			case FRIENDS:
				friends(in, sn);
				System.out.println();
				break;
			case CHECK_FRIENDSHIP:
				checkFriendship(in, sn);
				System.out.println();
				break;
			case CHECK_FRIENDS:
				checkFriends(in, sn);
				System.out.println();
				break;
			case STATUS:
				status(in, sn);
				System.out.println();
				break;
			case CHECK_STATUS:
				checkStatus(in, sn);
				System.out.println();
				break;
			case PEOPLE:
				people(in, sn);
				System.out.println();
				break;
			case POST_PUBLIC:
				postPublic(in, sn);
				System.out.println();
				break;
			case POST:
				post(in, sn);
				System.out.println();
				break;
			case TIMELINE:
				timeline(in, sn);
				System.out.println();
				break;
			default:
				break;// Se nao for reconhecido nenhum comando, nada acontece
			}
			command = in.nextLine().toUpperCase();
		}
		System.out.println(GOODBYE);
		System.out.println();
		in.close();
	}

	private static void timeline(Scanner in, SocialNetworkClass sn) {
		String friend = in.nextLine();
		String owner = in.nextLine();

		if (sn.hasPerson(friend) && sn.hasPerson(owner) && !sn.isEmpty()) {
			if (sn.hasFriendship(friend, owner)) {
				System.out.println("Mural de " + owner + ":");
				friends.PostIterator it = sn.listPosts(owner);
				while (it.hasNext()) {
					friends.Post n = it.next();
					System.out.println("POST " + n.getAuthor() + ": " + n.getMessage());
				}
			} else {
				System.out.println(UNEXISTING_FRIENDSHIP);
			}
		} else {
			System.out.println(NOT_REGISTERED);
		}
	}

	private static void post(Scanner in, SocialNetworkClass sn) {
		String author = in.nextLine();
		String msg = in.nextLine();
		String name = in.nextLine();

		if (sn.hasPerson(name) && sn.hasPerson(author) && !sn.isEmpty()) {
			if (sn.hasFriendship(name, author)) {
				sn.addPost(name, author, msg);
				System.out.println(POST_REGISTERED);
			} else {
				System.out.println(UNEXISTING_FRIENDSHIP);
			}
		} else {
			System.out.println(NOT_REGISTERED);
		}
	}

	private static void postPublic(Scanner in, SocialNetworkClass sn) {
		String name = in.nextLine();
		String msg = in.nextLine();

		if (sn.hasPerson(name) && !sn.isEmpty()) {
			sn.addPost(name, name, msg);
			System.out.println(POST_REGISTERED);
		} else {
			System.out.println(NOT_REGISTERED);
		}
	}

	private static void people(Scanner in, SocialNetworkClass sn) {
		if (!sn.isEmpty()) {
			System.out.println(LIST_PEOPLE);
			int counter = sn.numberOfPeople();

			for (int i = 0; i < counter; i++) {
				System.out.println(sn.getPersonWithIndex(i).getName() + "; " + sn.getPersonWithIndex(i).getEmail());
			}
		} else
			System.out.println(EMPTY);
	}

	private static void checkStatus(Scanner in, SocialNetworkClass sn) {
		String name = in.nextLine();

		if (sn.hasPerson(name)) {
			System.out.println(sn.getStatus(name));
		} else
			System.out.println(NOT_REGISTERED);
	}

	private static void status(Scanner in, SocialNetworkClass sn) {
		String name = in.nextLine();
		String status = in.nextLine();

		if (sn.hasPerson(name)) {
			sn.changeStatus(name, status);
			System.out.println(NEW_STATUS);
		} else
			System.out.println(NOT_REGISTERED);

	}

	private static void checkFriends(Scanner in, SocialNetworkClass sn) {
		String name = in.nextLine();

		if (sn.hasPerson(name)) {
			if (sn.getPerson(name).hasFriends()) {
				System.out.println(LIST_FRIENDS);
				friends.FriendIterator it = sn.listFriends(name);
				while (it.hasNext()) {
					String n = it.next();
					System.out.println(n + "; " + sn.getPerson(n).getEmail());
				}
			} else
				System.out.println(NO_FRIENDS);
		} else
			System.out.println(NOT_REGISTERED);

	}

	private static void checkFriendship(Scanner in, SocialNetworkClass sn) {
		String n1 = in.nextLine();
		String n2 = in.nextLine();

		if (sn.hasPerson(n1) && sn.hasPerson(n2) && sn.hasFriendship(n1, n2)) {
			System.out.println(EXISTING_FRIENDSHIP);
		} else
			System.out.println(UNEXISTING_FRIENDSHIP);
	}

	private static void friends(Scanner in, SocialNetworkClass sn) {
		String n1 = in.nextLine();
		String n2 = in.nextLine();

		if (sn.hasPerson(n1) && sn.hasPerson(n2)) {
			if (!sn.hasFriendship(n1, n2)) {
				if (!n1.equals(n2)) {
					sn.addFriendship(n1, n2);
					System.out.println(NEW_FRIENDSHIP);
				} else
					System.out.println(INVALID_FRIENDSHIP);
			} else
				System.out.println(EXISTING_FRIENDSHIP);
		} else
			System.out.println(NOT_REGISTERED);
	}

	private static void register(Scanner in, SocialNetworkClass sn) {
		String name = in.nextLine();
		String email = in.nextLine();
		String status = in.nextLine();

		if (!sn.hasPerson(name)) {
			sn.addPerson(name, email, status);
			System.out.println(REGISTERED_SUCESSFULLY);
		} else {
			System.out.println(REGISTERED);
		}
	}

	private static void checkPerson(Scanner in, SocialNetworkClass sn) {
		String name = in.nextLine();

		if (sn.hasPerson(name)) {
			System.out.println(REGISTERED);
		} else {
			System.out.println(NOT_REGISTERED);
		}
	}
}
