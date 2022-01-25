package storage;

import dataStructures.*;

/**
 * Database that stores all the users and documents and their respective info.
 * 
 * @author Joao Soares N57609
 *
 */
public interface Dropbox {

	/**
	 * Adds a basic account to the system.
	 * 
	 * @param email of the account
	 */
	void addBasic(String email);

	/**
	 * Adds a premium account to the system.
	 * 
	 * @param email of the account
	 */
	void addPremium(String email);

	/**
	 * Upload a document to the system.
	 * 
	 * @param email of the user
	 * @param name  of the document
	 * @param size  of the document
	 */
	void upload(String email, String name, int size);

	/**
	 * Checks if the user has sufficient space to upload or share a document.
	 * 
	 * @param email of the user
	 * @param size  of the document
	 * @return <code>true<code> if the user has sufficient space
	 */
	boolean hasSufficientSpace(String email, int size);

	/**
	 * Checks there is a user in the system with the given email.
	 * 
	 * @param email of the user
	 * @return
	 */
	boolean hasUser(String email);

	/**
	 * Checks there is a document in the system with the given name.
	 * 
	 * @param email of the user
	 * @param name  of the document
	 * @return
	 */
	boolean hasDocument(String email, String name);

	/**
	 * Shares a document with a user.
	 * 
	 * @param e1   - email of the owner
	 * @param e2   - email of the other user
	 * @param name of the document
	 */
	void share(String e1, String e2, String name);

	/**
	 * Returns the users account type.
	 * 
	 * @param email of the user
	 * @return basic/premium
	 */
	String getType(String email);

	/**
	 * Checks if a certain user type is basic.
	 * 
	 * @param email of the user
	 * @return <code>true<code> if the user is basic
	 */
	boolean isBasic(String email);

	/**
	 * Checks if the document has already been shared with the user.
	 * 
	 * @param e1   - email of the owner
	 * @param e2   - email of the other user
	 * @param name of the document
	 * @return <code>true<code> if the document has been shared
	 */
	boolean hasBeenShared(String e1, String e2, String name);

	/**
	 * Returns the user with the least amount of space available.
	 * 
	 * @return user
	 */
	User minSpace();

	/**
	 * Lists all the files from the user.
	 * 
	 * @param email of the user
	 * @return list of files
	 */
	Iterator<Document> listFiles(String email);

	/**
	 * Lists all the registered users.
	 * 
	 * @return list of users
	 */
	Iterator<User> listAll();

	/**
	 * Returns the user with the given email.
	 * 
	 * @param email of the user
	 * @return user
	 */
	User getUser(String email);
}
