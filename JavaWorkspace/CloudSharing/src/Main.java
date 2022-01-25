import java.util.Scanner;

import storage.*;
import dataStructures.*;

/**
 * Main program for the app CloudSharing.
 * 
 * @author Joao Soares N57609
 *
 */
public class Main {

	/**
	 * Feedback given by the program
	 */
	private static final String EXITING = "Exiting...";
	/**
	 * Main program. Calls the command interpreter.
	 * 
	 * @param args - arguments for the program execution
	 */
	public static void main(String[] args) {
		Main.commands();
	}

	/**
	 * Command interpreter.
	 */
	private static void commands() {
		DropboxClass d = new DropboxClass();
		Scanner in = new Scanner(System.in);
		String command = in.next().toUpperCase();

		while (!command.equals("EXIT")) {
			switch (command) {
			case ("ADD"):
				add(in, d);
				break;
			case ("LISTALL"):
				listAll(d);
				break;
			case ("UPLOAD"):
				upload(in, d);
				break;
			case ("SHARE"):
				share(in, d);
				break;
			case ("MINSPACE"):
				minSpace(d);
				break;
			case ("LISTFILES"):
				listFiles(in, d);
				break;
			case ("UPDATE"):
				update(in,d);
				break;
			case ("LASTUPDATE"):
				lastUpdate(in, d);
				break;
			default:
				//nothing happens
				break;
			}
			System.out.println();
			command = in.next().toUpperCase();
		}
		System.out.println(EXITING);
		System.out.println();
		in.close();
	}

	private static void add(Scanner in, DropboxClass d) {
		String email = in.next();
		String type = in.next();
		
		if (d.hasUser(email)) {
			System.out.println("Account already exists.");
		} else {
			System.out.println("Account was added.");
			if (type.equals("basic")) {
				d.addBasic(email);
			} else d.addPremium(email);
		}
	}

	private static void listAll(DropboxClass d) {
		System.out.println("All accounts:");
		
		if (!d.isUsersEmpty()) {
			Iterator<User> ui = d.listAll();
			while (ui.hasNext()) {
				User u = ui.next();
				System.out.println(u.getEmail() + " (" + u.getType().substring(0, 1).toUpperCase() + u.getType().substring(1) + ")");
			}
		}
	}

	private static void upload(Scanner in, DropboxClass d) {
		String email = in.next();
		String name = in.next();
		int size = in.nextInt();
		
		if (!d.hasUser(email)) {
			System.out.println("Account does not exist.");
		} else if (d.getUser(email).hasDocument(name)) {
			System.out.println("File already exists in the account.");
		} else if (!d.hasSufficientSpace(email, size)) {
			System.out.println("File size exceeds account capacity.");
		} else {
			System.out.println("File uploaded into account.");
			d.upload(email, name, size);
		}
	}

	private static void share(Scanner in, DropboxClass d) {
		String e1 = in.next();
		String e2 = in.next();
		String name = in.next();
		
		if (!d.hasUser(e1) || !d.hasUser(e2)) {
			System.out.println("Account does not exist.");
		} else if (!d.getUser(e1).hasDocument(name)) {
			System.out.println("File does not exist.");
		} else if (d.isBasic(e1)) {
			System.out.println("Account does not allow file sharing.");
		} else if (d.getUser(e1).getDocument(name).hasInShares(e2)) {
			System.out.println("File already shared.");
		} else if (d.isBasic(e2) && !d.hasSufficientSpaceReceive(e2, (d.getUser(e1).getDocument(name).getSize()))) {
			System.out.println("File size exceeds account capacity.");
		} else {
			System.out.println("File was shared.");
			d.share(e1, e2, name);
		}
	}

	private static void minSpace(DropboxClass d) {
		if (d.isUsersEmpty()) {
			System.out.println("No accounts.");
		} else {
			User u = d.minSpace();
			System.out.println("Account with least free space: " + u.getEmail());
		}
	}

	private static void listFiles(Scanner in, DropboxClass d) {
		String email = in.next();
		
		if (!d.hasUser(email)) {
			System.out.println("Account does not exist.");
		} else {
			System.out.println("Account files:");
			Iterator<Document> di = d.listFiles(email);
			while (di.hasNext()) {
				Document doc = di.next();
				System.out.print(doc.getName() + " (" + doc.getSize() + " MB)");
				if (doc.isShared()) System.out.print(" (shared)");
				System.out.print("\n");
			}
		}
	}
	
	private static void update(Scanner in, DropboxClass d) {
		String id1 = in.next(); // owner id
		String id2 = in.next(); // id of the user accessing the document
		String name = in.next(); // name of document

		if (!d.hasUser(id1) || !d.hasUser(id2)) {
			System.out.println("Account does not exist.");
		} else if (!d.hasDocument(id1, name)) {
			System.out.println("File does not exist.");
		} else if (d.getUser(id1).getDocument(name).isShared()) {
			System.out.println("File does not exist.");
		} else if (!id1.equals(id2) && !d.getUser(id2).hasDocumentShared(name)) {
			System.out.println("File not shared.");
		} else {
			d.update(name, id1, id2);
			System.out.println("File was updated.");
		}
	}
	
	private static void lastUpdate(Scanner in, DropboxClass d) {
		String email = in.next();
		String name = in.next();
		
		if (!d.hasUser(email)) {
			System.out.println("Account does not exist.");
		} else if (!d.hasDocument(email, name)) {
			System.out.println("File does not exist.");
		} else {
			System.out.print("Last update: ");
			if (!d.getUser(email).getDocument(name).hasBeenUpdated()) {
				System.out.print(email + "\n");
			} else {
				System.out.print(d.getUser(email).getDocument(name).lastUpdate() + "\n");
			}
		}
	}

}
