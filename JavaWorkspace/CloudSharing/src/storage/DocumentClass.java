package storage;

import dataStructures.*;

public class DocumentClass implements Document {
	
	private String name, owner;
	private int size;
	private boolean shared, viral, updated;
	
	private Array<String> updates = new ArrayClass<String>();
	
	private Array<String> shares = new ArrayClass<String>();

	public DocumentClass(String name, String owner, int size, boolean shared, boolean viral) {
		this.size = size;
		this.name = name;
		this.owner = owner;
		this.shared = shared;
		this.viral = viral;
		updated = false;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public String getOwner() {
		return owner;
	}

	@Override
	public boolean hasInShares(String email) {
		for (int i = 0; i < shares.size(); i++) {
			if (shares.get(i).equals(email)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void share(String email) {
		shares.insertLast(email);
	}
	
	public boolean isShared() {
		return shared;
	}
	public boolean isViral() {
		return viral;
	}

	@Override
	public boolean hasBeenUpdated() {
		return updated;
	}

	@Override
	public void update(String id) {
		updates.insertLast(id);;
		updated = true;
	}

	@Override
	public String lastUpdate() {
		return updates.get(updates.size() - 1);
	}
}
