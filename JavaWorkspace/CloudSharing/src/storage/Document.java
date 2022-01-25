package storage;

/**
 * A Document with its own methods.
 * 
 * @author Joao Soares N57609
 *
 */
public interface Document {

	/**
	 * Returns the name of the document.
	 *
	 * @return name
	 */
	String getName();

	/**
	 * Returns the size of the document.
	 * 
	 * @return document size
	 */
	int getSize();

	/**
	 * Returns the email of the owner of the document.
	 * 
	 * @return email of the owner
	 */
	String getOwner();

	/**
	 * Checks if the document has been shared with the given user.
	 * 
	 * @param email of the user
	 * @return <code>true<code> if the document has been shared with the user
	 */
	boolean hasInShares(String email);

	/**
	 * Adds a user to the list of users that the document was shared with.
	 * 
	 * @param email of the user
	 */
	void share(String email);

	/**
	 * Checks if the document is sharable with other users.
	 * @return<code>true<code> if the document is sharable
	 */
	boolean isViral();

	/**
	 * Checks if the document was shared with the user.
	 * 
	 * @return <code>true<code> if the document was shared
	 */
	boolean isShared();

	/**
	 * Checks if the document has been updated.
	 * 
	 * @return <code>true<code> if the document was updated
	 */
	boolean hasBeenUpdated();

	/**
	 * Adds an id to the updates array.
	 * 
	 * @param id of the user that updated the document
	 */
	void update(String id);

	/**
	 * Return the last update email.
	 * 
	 * @return email
	 */
	String lastUpdate();

}