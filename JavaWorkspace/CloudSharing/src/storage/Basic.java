package storage;

import dataStructures.*;

public class Basic extends AbstractUser {

	public Basic(String email) {
		this.email = email;
		this.space = (super.GB * 2);
		this.type = ("basic");
		documents = new ArrayClass<Document>();
	}

	@Override
	public void addDocument(String name, int size) {
		documents.insertLast(new DocumentClass(name, this.email, size, false, false));
	}

	@Override
	public void receive(String email, String name, int size) {
		documents.insertLast(new DocumentClass(name, this.email, size, true, false));
	}

	@Override
	public int getSpace() {
		int used = 0;
		
		for (int i = 0; i < documents.size(); i++) {
			if (documents.get(i).isShared()) {
				used += (documents.get(i).getSize() / 2);
			} else {
				used += documents.get(i).getSize();
			}
		}
		return (space - used);
	}

}
