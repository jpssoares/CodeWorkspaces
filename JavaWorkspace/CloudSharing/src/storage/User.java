package storage;

import dataStructures.*;

/**
 * A User with its own methods.
 * 
 * @author Joao Soares N57609
 *
 */
public interface User {

	/**
	 * Return the email assigned to this user.
	 *
	 * @return - email of the user
	 */
	String getEmail();

	/**
	 * Return the type of user(either basic or premium).
	 *
	 * @return type
	 */
	String getType();

	/**
	 * Returns the usable storage space.
	 *
	 * @return storage space
	 */
	int getSpace();

	/**
	 * Gathers the available data about the document with the given name.
	 *
	 * @param name - name of the document
	 * @return Document
	 */
	Document getDocument(String name);

	/**
	 * Gathers the available data about the document with the given index.
	 *
	 * @param i - index of the document
	 * @return Document
	 */
	Document getDocumentIndex(int i);

	/**
	 * Returns all the documents in the document array.
	 * 
	 * @return documents
	 */
	Iterator<Document> listFiles();

	/**
	 * Returns the number of documents uploaded and shared.
	 * 
	 * @return number of documents
	 */
	int getDocumentCounter();

	/**
	 * Checks if there are any documents registered in the system.
	 *
	 * @return <code>true<code> if there are no documents in the system
	 */
	boolean isDocumentEmpty();

	/**
	 * Checks if a certain document is registered in the system.
	 *
	 * @param name - name of the document
	 * @return <code>true<code> if there is a document with the given name
	 */
	boolean hasDocument(String name);

	/**
	 * Adds a new document to the document array.
	 *
	 * @param name of the document
	 * @param size of the document
	 */
	void addDocument(String name, int size);

	/**
	 * Adds a new shared document to the document array.
	 * 
	 * @param email of the owner
	 * @param name  of the document
	 * @param size  of the document
	 */
	void receive(String email, String name, int size);

	/**
	 * Checks if a certain document was shared with the user.
	 *
	 * @param name - name of the document
	 * @return <code>true<code> if there is a document with the given name
	 */
	boolean hasDocumentShared(String name);

	/**
	 * Updates a registered document.
	 * @param id of the user updating the document
	 * @param name of the document
	 */
	void update(String id, String name);

}
