package storage;

import dataStructures.*;

public class Premium extends AbstractUser {

	public Premium(String email) {
		this.email = email;
		this.space = (super.GB * 5);
		this.type = ("premium");
		documents = new ArrayClass<Document>();
	}
	
	@Override
	public void addDocument(String name, int size) {
		documents.insertLast(new DocumentClass (name, this.email, size, false, true));
	}
	
	@Override
	public void receive(String email, String name, int size) {
		documents.insertLast(new DocumentClass (name, email, size, true, false));
	}
	
	@Override
	public int getSpace() {
		int used = 0;
		
		for (int i = 0; i < documents.size(); i++) {
			if (!documents.get(i).isShared()) {
				used += documents.get(i).getSize();
			}
		}
		return (space - used);
	}
}
