import java.util.Scanner;

import friends.*;
/**
 * Programa principal para demonstracao da aplicacao TheStupidFriendsBook.
 * 
 * @author Miguel Goulao / Adriano Lopes / Carla Ferreira 
 * 
 */
public class Main {
	// Comandos do utilizador
	private static final String QUIT = "SAIR";
	private static final String NEW = "NOVO";
	private static final String ADD_FRIEND = "ADICIONA";
	private static final String REM_FRIEND = "REMOVE";
	private static final String DO = "FAZ";
	private static final String VOTE = "VOTA";
	private static final String GOOD = "BEM";
	private static final String BAD = "MAL";
	private static final String LIST = "LISTA";
	private static final String BORING = "ABORRECIDO";
	private static final String INTELLIGENT = "INTELIGENTE";
	private static final String STUPID = "ESTUPIDO";
	private static final String BANDIT = "BANDIDO";
	private static final String ANGEL = "ANGINHO";
	
	// Feedback dado pelo programa
	private static final String OK = "Ok";
	private static final String BYE = "Adeus";
	private static final String ALREADY_EXISTS = " ja existe";
	private static final String DOES_NOT_EXIST = " nao existe";
	private static final String NOT = " nao ";
	private static final String ALREADY = " ja ";
	
	/**
	 * Programa principal. Invoca interpretador de comandos.
	 * @param args - argumentos para execucao da aplicacao. Nao sao usados, neste programa.
	 */
	public static void main(String[] args) {
		Main.commands();
	}

	/**
	 * Interpretador de comandos.
	 */
	private static void commands() {
		/**************************/
		/*** Criacao do objecto ***/
		/**************************/
		StupidFriendsBook fb = new StupidFriendsBookInList();
		Scanner in = new Scanner(System.in);
		String command = in.nextLine().toUpperCase();
		while (!command.equals(QUIT)) {
			switch (command) {
			case Main.NEW:
				resetStupidFriendsBook(fb);
				break;
			case Main.ADD_FRIEND:
				addFriend(in, fb);
				break;
			case Main.REM_FRIEND:
				removeFriend(in, fb);
				break;
			case Main.DO:
				doSomething(in, fb);
				break;
			case Main.VOTE:
				vote(in, fb);
				break;
			case Main.LIST:
				list(in, fb);
				break;
			case Main.BORING:
				boring(fb);
				break;
			default:
				break; // Nao faz nada com comandos desconhecidos.
			}
			command = in.nextLine().toUpperCase();
		}
		System.out.println(Main.BYE);
		in.close();
	}
	
	/**
	 * Limpa os registos do StupidFriendsBook.
	 * @param fb - o StupidFriendsBook do qual os registos sao apagados.
	 */
	private static void resetStupidFriendsBook(StupidFriendsBook fb) {
		fb.reset();
	}
	
	/**
	 * Adiciona um novo amigo, se ele ainda nao existir. Se ja existir, nao faz nada.
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param fb - o  StupidFriendsBook no qual se pretende inscrever o amigo.
	 */
	private static void addFriend(Scanner in, StupidFriendsBook fb) {
		String name = in.nextLine();
		if (!fb.hasFriend(name)) {
			fb.addFriend(name);
			System.out.println(Main.OK);
		}
		else
			System.out.println(name + Main.ALREADY_EXISTS);
	}
	
	/**
	 * Remove um amigo, se ele exisitir. Se nao existir nao faz nada.
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param fb - o StupidFriendsBook no qual se pretende remover o amigo.
	 */
	private static void removeFriend(Scanner in, StupidFriendsBook fb) {
		String name = in.nextLine();
		if (fb.hasFriend(name)) {
			fb.removeFriend(name);
			System.out.println(Main.OK);
		}
		else
			System.out.println(name + " " + Main.DOES_NOT_EXIST);
	}
	
	/**
	 * Acrescenta uma accao ao amigo. Se o amigo nao existir, ou se existir, 
	 * mas ja tiver outra accao com semelhante descricao, nao faz nada.
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param fb - o StupidFriendsBook no qual se pretende registar a accao do amigo.
	 */
	private static void doSomething(Scanner in, StupidFriendsBook fb) {
		String name = in.nextLine();
		String description = in.nextLine();
		if (!fb.hasAction(name,description)) {
			fb.addAction(name, description);
			System.out.println(Main.OK);
		}
		else
			System.out.println(name + NOT + description);
			
	}
	
	/**
	 * Vota numa accao de uma determinada pessoa. Se a pessoa nao existir, ou se, existindo, nao tiver feito essa accao, nao faz nada.
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param fb - o StupidFriendsBook no qual se pretende votar na accao de um amigo.
	 */
	private static void vote(Scanner in, StupidFriendsBook fb) {
		String name = in.nextLine();
		String description = in.nextLine();
		boolean selfBenefit = Main.benefitStatus(in.nextLine());
		boolean othersBenefit = Main.benefitStatus(in.nextLine());
		if (fb.hasAction(name,description)) {
			fb.vote(name, description, selfBenefit, othersBenefit);
			System.out.println(Main.OK);
		}
		else
			System.out.println(name + Main.ALREADY + description);
	}
	
	/**
	 * Metodo auxiliar para transformar a <code>String</code> com a apreciacao do impacto de uma accao num valor booleano.
	 * @param status - a <code>String</code> com a apreciacao da accao.
	 * @return - <code>true</code>, se a accao for benefica, <code>false</code>, caso contrario.
	 */
	private static boolean benefitStatus(String status) {
		if (status.equalsIgnoreCase(Main.BAD))
			return false;
		else if (status.equalsIgnoreCase(Main.GOOD))
			return true;
		else
			return true; // Por omissao, retorna true. Nunca deve ocorrer, sendo apenas acrescentado por completude.
	}
	
	/**
	 * 
	 * @param in - o input de onde os dados vao ser lidos.
	 * @param fb - o StupidFriendsBook a listar informacao dos amigos com uma dada personalidade.
	 */
	private static void list(Scanner in, StupidFriendsBook fb) {
		String result = "";
		String trait = in.nextLine();
		int personality;
		switch (trait) {
		case Main.INTELLIGENT:
			personality = StupidFriendsBook.INTELLIGENT;
			break;
		case Main.STUPID:
			personality = StupidFriendsBook.STUPID;
			break;
		case Main.BANDIT:
			personality = StupidFriendsBook.BANDIT;
			break;
		case Main.ANGEL:
			personality = StupidFriendsBook.ANGEL;
			break;
		default:
			personality = StupidFriendsBook.INTELLIGENT; // por omissao, assume-se que e inteligente. Nunca deve ocorrer.
		}
		
		// Para listar as pessoas com um determinado traco, usamos o iterador de 
		// pessoas, filtrando as pessoas por personalidade, antes de as 
		// acrescentar a String que vamos imprimir.
		fb.initialize(personality);
		while (fb.hasNext()) {
			result += fb.next().getName() + ";";
		}
		System.out.println(result);
	}
	
	/**
	 * Imprime o amigo mais aborrecido de todos.
	 * @param fb - o StupidFriendsBook no qual se quer determinar o amigo mais aborrecido.
	 */
	private static void boring(StupidFriendsBook fb) {
		if (fb.numberOfFriends()>0)
			System.out.println(fb.mostBoringFriend());
	}
}
