package eCorreio;

/**
 * A email message and its own methods.
 * 
 * @author Joao Soares N57609
 *
 */
public interface Email {
	// Variables

	/**
	 * Return the email of the user.
	 * 
	 * @return email of the user
	 */
	String getAddress();

	/**
	 * Return the date of the email
	 * 
	 * @return date
	 */
	String getDate();

	/**
	 * Return the message subject.
	 * 
	 * @return subject
	 */
	String getSubject();

	/**
	 * Return the message text.
	 * 
	 * @return text
	 */
	String getText();

}
