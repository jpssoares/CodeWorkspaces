import java.util.InputMismatchException;
import java.util.Scanner;

import cbook.*;
import exceptions.*;

public class Main {
	//Constantes que definem os comandos
	 public static final String ADD_CONTACT    = "AC";
	 public static final String REMOVE_CONTACT = "RC";
	 public static final String GET_PHONE      = "GP";
	 public static final String GET_EMAIL      = "GE";
	 public static final String SET_PHONE      = "SP";
	 public static final String SET_EMAIL      = "SE";
	 public static final String LIST_CONTACTS  = "LC";
	 public static final String QUIT           = "Q";
	 
	 //Constantes que definem as mensagens para o utilizador
	 public static final String CONTACT_EXISTS = "Contact already exists.";
	 public static final String NAME_NOT_EXIST = "Contact does not exist.";
	 public static final String CONTACT_ADDED = "Contact added.";
	 public static final String CONTACT_REMOVED = "Contact removed.";
	 public static final String CONTACT_UPDATED = "Contact updated.";
	 public static final String BOOK_EMPTY = "Contact book empty.";
	 public static final String INVALID_PHONE = "Not a valid phone number.";
	 
	 public static void main(String[] args) {   
		 Scanner in = new Scanner(System.in);
		 ContactBook cBook = new ContactBookClass();
		 String comm = getCommand(in);
	  
		 while (!comm.equals(QUIT)){
			 switch (comm) {
			 case ADD_CONTACT: 
				 addContact(in,cBook);
				 System.out.println();
				 break;
			 case REMOVE_CONTACT:
				 deleteContact(in,cBook);
				 System.out.println();
				 break;
			 case GET_PHONE:
				 getPhone(in,cBook);
				 System.out.println();
				 break;
			 case GET_EMAIL: 
				 getEmail(in,cBook);
				 System.out.println();
				 break;
			 case SET_PHONE:
				 setPhone(in,cBook);
				 System.out.println();
				 break;
			 case SET_EMAIL:
				 setEmail(in,cBook);
				 System.out.println();
				 break;
			 case LIST_CONTACTS:
				 listAllContacts(cBook);
				 System.out.println();
				 break;
			 default:
			 }
			 
			 comm = getCommand(in);
		 }
		 System.out.println("Goodbye!");
		 System.out.println();
		 in.close();
	 }
	
	private static String getCommand(Scanner in) {
		String input;
		input = in.nextLine().toUpperCase();
		return input;
	}

	private static void addContact(Scanner in, ContactBook cBook) throws InputMismatchException{
		String name, email;
		int phone;

		name = in.nextLine();
		try {
			phone = in.nextInt(); in.nextLine();
			email = in.nextLine();
			cBook.addContact(name, phone, email);
			System.out.println(CONTACT_ADDED);
		} catch (InputMismatchException e) {
			System.out.println(INVALID_PHONE);
		} catch	(ContactAlreadyExistsException e) {
			System.out.println(CONTACT_EXISTS);
		}
	}
	
	private static void deleteContact(Scanner in, ContactBook cBook) {
		String name;
		name = in.nextLine();
		try {
			cBook.deleteContact(name);
			System.out.println(CONTACT_REMOVED);
		} catch	(ContactDoesNotExistException e) {
			System.out.println(NAME_NOT_EXIST);
		}
	}
	
	private static void getPhone(Scanner in, ContactBook cBook) {
		String name;
		name = in.nextLine();
		try {
			System.out.println(cBook.getPhone(name));
		} catch (ContactDoesNotExistException e ) {
			System.out.println(NAME_NOT_EXIST);
		}
	}
	
	private static void getEmail(Scanner in, ContactBook cBook) {
		String name;
		name = in.nextLine();
		try {
			System.out.println(cBook.getEmail(name));
		} catch	(ContactDoesNotExistException e) {
			System.out.println(NAME_NOT_EXIST);
		}
	}
	
	private static void setPhone(Scanner in, ContactBook cBook) throws InputMismatchException{
		String name;
		try {
			int phone;
			name = in.nextLine();
			phone = in.nextInt(); in.nextLine();
			cBook.setPhone(name,phone);
			System.out.println(CONTACT_UPDATED);
		} catch (InputMismatchException e) {
			System.out.println(INVALID_PHONE);
		} catch	(ContactDoesNotExistException e) {
			System.out.println(NAME_NOT_EXIST);
		}
	}
	
	private static void setEmail(Scanner in, ContactBook cBook) {
		String name;
		String email;
		name = in.nextLine();
		email = in.nextLine();
		try {
			cBook.setEmail(name,email);
			System.out.println(CONTACT_UPDATED);
		} catch	(ContactDoesNotExistException e) {
			System.out.println(NAME_NOT_EXIST);
		}
	}

	private static void listAllContacts(ContactBook cBook) {
		try {
			Iterator it = cBook.listContacts();
			while(it.hasNext()) {
				System.out.println(it.next());
			}
		} catch(ListEmptyException e) {
			System.out.println(BOOK_EMPTY);
		}
	}
}
