package storage;

import dataStructures.*;

/**
 * Database that stores all the users and documents and their respective info.
 * 
 * @author Joao Soares N57609
 *
 */
public class DropboxClass implements Dropbox {
	
	private Array<User> users;
	
	public DropboxClass() {
		users = new ArrayClass<User>();
	}
	@Override
	public void addBasic(String email) {
		users.insertLast(new Basic(email));
		
	}

	@Override
	public void addPremium(String email) {
		users.insertLast(new Premium(email));
	}

	@Override
	public void upload(String email, String name, int size) {
		getUser(email).addDocument(name, size);
	}

	@Override
	public boolean hasSufficientSpace(String email, int size) {
		return (getUser(email).getSpace() >= size);
	}
	
	public boolean hasSufficientSpaceReceive(String email, int size) {
		int s = (size / 2);
		return (getUser(email).getSpace() >= s);
	}
	
	@Override
	public boolean hasUser(String email) {
			return this.indexOf(email) != -1;
	}

	@Override
	public boolean hasDocument(String email, String name) {
		return (getUser(email).hasDocument(name));
	}

	@Override
	public void share(String e1, String e2, String name) {
		getUser(e1).getDocument(name).share(e2);
		getUser(e2).receive(e1, name, getUser(e1).getDocument(name).getSize());
	}

	@Override
	public boolean isBasic(String email) {
		return (getUser(email).getType().equals("basic"));
	}

	@Override
	public boolean hasBeenShared(String e1, String e2, String name) {
		return (getUser(e1).getDocument(name).hasInShares(e2));
	}

	@Override
	public User minSpace() {
		int min = -1;
		int u = 0;
		for (int i = 0; i < users.size(); i++) {
			if (min == -1 || users.get(i).getSpace() < min) {
				u = i;
				min = users.get(i).getSpace();
			}
		}
		return users.get(u);
	}

	@Override
	public Iterator<User> listAll() {
		return users.iterator();
	}
	@Override
	public User getUser(String email) {
		if ((this.indexOf(email)) != -1) {
			return users.get(this.indexOf(email));
		}
		return null;
	}

	public void update(String name, String id1, String id2) {
		getUser(id1).update(id2, name);
		
	}
	
	// Auxiliary methods
		
		private int indexOf(String email) {
			Iterator<User> it = users.iterator();
			int i = 0;
			while (it.hasNext()) {
				User users = it.next();
				if (users.getEmail().contentEquals(email))
					return i;
				i++;
			}
			return -1;
		}
		@Override
		public String getType(String email) {
			return getUser(email).getType();
		}
		
		@Override
		public Iterator<Document> listFiles(String email) {
			return getUser(email).listFiles();
		}
		public boolean isUsersEmpty() {
			return users.isEmpty();
		}
}
