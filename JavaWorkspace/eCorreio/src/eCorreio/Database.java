package eCorreio;

import exceptions.*;
import java.util.*;

/**
 * 
 * @author Joao Soares N57609
 *
 */
public interface Database {

	/**
	 * Adds a new email to the sentEmails map.
	 * 
	 * @param address of the email
	 * @param date    when the email was sent
	 * @param subject of the message
	 * @param text    of the message
	 */
	void send(String address, String date, String subject, String text) throws DuplicateMessageException;

	/**
	 * Adds a new email to the receivedEmails map.
	 * 
	 * @param address of the email
	 * @param date    when the email was received
	 * @param subject of the message
	 * @param text    of the message
	 */
	void receive(String address, String date, String subject, String text) throws DuplicateMessageException;

	/**
	 * Checks if a certain email has already been sent.
	 * 
	 * @param address of the email
	 * @param date    when the email was sent
	 * @param subject of the message
	 * @return <code>true<code> if there is an email with the given parameters in
	 *         the sentEmails map
	 */
	boolean hasSentEmail(String address, String date, String subject);

	/**
	 * Checks if a certain email has already been received.
	 * 
	 * @param address of the email
	 * @param date    when the email was received
	 * @param subject of the message
	 * @return <code>true<code> if there is an email with the given parameters in
	 *         the receivedEmails map
	 */
	boolean hasReceivedEmail(String address, String date, String subject);

	/**
	 * Checks if there are any email with a given subject.
	 * 
	 * @param subject of the email
	 * @return <code>true<code> if there are email messages with the given subject
	 */
	boolean hasSubject(String subject);

	/**
	 * Checks if there are any email with a given subject.
	 * 
	 * @param address of the email
	 * @return <code>true<code> if there are email messages with the given address
	 */
	boolean hasAddress(String address);

	/**
	 * List all sent messages.
	 * 
	 * @return list of messages
	 */
	Iterator<Email> listSentMessages();

	/**
	 * List all received messages.
	 * 
	 * @return list of messages
	 */
	Iterator<Email> listReceivedMessages();

	/**
	 * List all messages with the given subject.
	 * 
	 * @param subject
	 * @return list of messages
	 */
	Iterator<Email> listSubjectMessages(String subject) throws ListEmptyException;

	/**
	 * List all the messages (sent and received) with the given email address.
	 * 
	 * @param address
	 * @return list of messages
	 */
	Iterator<Email> listEmailMessages(String address) throws ListEmptyException;

	/**
	 * Lists all the different subjects from all the messages.
	 * 
	 * @return list of subjects
	 */
	Iterator<String> listSubjects();

}
