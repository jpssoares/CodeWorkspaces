package storage;

import dataStructures.*;

/**
 * A User and its own methods.
 * 
 * @author Joao Soares N57609
 *
 */
public abstract class AbstractUser implements User {
	
	protected final int GB = 1024;

	protected String email;
	protected String type;
	protected int space;

	protected Array<Document> documents;

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public Document getDocument(String name) {
		if ((this.indexOf(name)) != -1) {
			return documents.get(this.indexOf(name));
		}
		return null;
	}

	@Override
	public Document getDocumentIndex(int i) {
		return documents.get(i);
	}

	@Override
	public Iterator<Document> listFiles() {
		return documents.iterator();
	}

	@Override
	public int getDocumentCounter() {
		return documents.size();
	}
	
	@Override
	public boolean isDocumentEmpty() {
		return documents.isEmpty();
	}

	@Override
	public boolean hasDocument(String name) {
		return this.indexOf(name) != -1;
	}
	
	@Override
	public boolean hasDocumentShared(String name) {
		if (hasDocument(name)) {
			return getDocument(name).isShared();
		}
		return false;
	}
	
	@Override
	public void update(String id, String name) {
		getDocument(name).update(id);
		
	}
	
	// Auxiliary methods
	
			private int indexOf(String name) {
				Iterator<Document> it = documents.iterator();
				int i = 0;
				while (it.hasNext()) {
					Document documents = it.next();
					if (documents.getName().equals(name))
						return i;
					i++;
				}
				return -1;
			}
}
