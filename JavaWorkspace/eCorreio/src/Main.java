
import java.util.Iterator;
import java.util.Scanner;
import eCorreio.*;
import exceptions.*;

/**
 * Main program for the app eCorreio.
 * 
 * @author Joao Soares N57609
 *
 */
public class Main {

	// Constantes que definem as mensagens para o utilizador
	public static final String TOPICS1 = "data | assunto | email";
	public static final String TOPICS2 = TOPICS1 + " | texto";
	public static final String MESSAGE_REGISTERED = "Mensagem registada.";
	public static final String MESSAGE_EXISTS = "Mensagem duplicada.";
	public static final String SUBJECT_EMPTY = "Nao existem mensagens trocadas com esse assunto.";
	public static final String EMAIL_EMPTY = "Nao existem mensagens trocadas com esse email.";
	public static final String EXIT = "A terminar.";

	public static void main(String[] args) {
		Database d = new DatabaseClass();
		Scanner in = new Scanner(System.in);
		String comm = getCommand(in);

		while (!comm.equals("SAIR")) {
			switch (comm) {
			case ("ENVIAR"):
				send(in, d);
				break;
			case ("RECEBER"):
				receive(in, d);
				break;
			case ("ENVIADAS"):
				sentMessages(d);
				break;
			case ("RECEBIDAS"):
				receivedMessages(d);
				break;
			case ("ASSUNTO"):
				subjectMessages(in, d);
				break;
			case ("EMAIL"):
				emailMessages(in, d);
				break;
			case ("ASSUNTOS"):
				allSubjects(d);
				break;
			}
			System.out.println();
			comm = getCommand(in);
		}
		System.out.println(EXIT);
		System.out.println();
		in.close();
	}

	private static void send(Scanner in, Database d) {
		String subject = in.nextLine();
		String address = in.nextLine();
		String text = in.nextLine();
		String date = in.nextLine();
		
		try {
			d.send(address, date, subject, text);
			System.out.println(MESSAGE_REGISTERED);
		} catch (DuplicateMessageException e) {
			System.out.println(MESSAGE_EXISTS);
		}
	}

	private static void receive(Scanner in, Database d) {
		String subject = in.nextLine();
		String address = in.nextLine();
		String text = in.nextLine();
		String date = in.nextLine();
		
		try {
			d.receive(address, date, subject, text);
			System.out.println(MESSAGE_REGISTERED);
		} catch (DuplicateMessageException e) {
			System.out.println(MESSAGE_EXISTS);
		}
	}

	private static void sentMessages(Database d) {
		Iterator<Email> it = d.listSentMessages();
		System.out.println(TOPICS1);
		while (it.hasNext()) {
			Email e = it.next();
			String s = e.getDate() + " | " + e.getSubject() + " | " + e.getAddress();
			System.out.println(s);
		}
	}

	private static void receivedMessages(Database d) {
		Iterator<Email> it = d.listReceivedMessages();
		System.out.println(TOPICS1);
		while (it.hasNext()) {
			Email e = it.next();
			String s = e.getDate() + " | " + e.getSubject() + " | " + e.getAddress();
			System.out.println(s);
		}

	}

	private static void subjectMessages(Scanner in, Database d) {
		String subject = in.nextLine();
		try {
			Iterator<Email> it = d.listSubjectMessages(subject);
			System.out.println(TOPICS2);
			while (it.hasNext()) {
				Email e = it.next();
				String s = e.getDate() + " | " + e.getSubject() + " | " + e.getAddress() + " | " + e.getText();
				System.out.println(s);
			}
		} catch (ListEmptyException e) {
			System.out.println(SUBJECT_EMPTY);
		}
	
	}

	private static void emailMessages(Scanner in, Database d) {
		String address = in.nextLine();
		try {
			Iterator<Email> it = d.listEmailMessages(address);
			System.out.println(TOPICS2);
			while (it.hasNext()) {
				Email e = it.next();
				String s = e.getDate() + " | " + e.getSubject() + " | " + e.getAddress() + " | " + e.getText();
				System.out.println(s);
			}
		} catch (ListEmptyException e) {
			System.out.println(EMAIL_EMPTY);
		}
	}

	private static void allSubjects(Database d) {
		Iterator<String> it = d.listSubjects();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}

	}

	private static String getCommand(Scanner in) {
		String input;
		input = in.nextLine().toUpperCase();
		return input;
	}
}
