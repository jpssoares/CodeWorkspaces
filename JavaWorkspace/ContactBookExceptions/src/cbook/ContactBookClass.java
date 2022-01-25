package cbook;

import exceptions.*;

public class ContactBookClass implements ContactBook {
	static final int DEFAULT_SIZE = 100;

	private int counter;
	private ContactClass[] contacts;

	public ContactBookClass() {
		counter = 0;
		contacts = new ContactClass[DEFAULT_SIZE];
	}

	public boolean hasContact(String name) {
		return searchIndex(name) >= 0;
	}

	public int getNumberOfContacts() {
		return counter;
	}

	public void addContact(String name, int phone, String email) throws ContactAlreadyExistsException {
		if (hasContact(name)) {
			throw new ContactAlreadyExistsException();
		}else {
			if (counter == contacts.length) 
				resize();
			contacts[counter] = new ContactClass(name, phone, email);
			counter++;
		}
	}

	public void deleteContact(String name) throws ContactDoesNotExistException{
		if (!hasContact(name)) {
			throw new ContactDoesNotExistException();
		}
		contacts[searchIndex(name)] = contacts[counter-1];
		counter--;
	}

	public int getPhone(String name)  throws ContactDoesNotExistException{
		if (!hasContact(name)) {
			throw new ContactDoesNotExistException();
		}
		return contacts[searchIndex(name)].getPhone();
	}

	public String getEmail(String name)  throws ContactDoesNotExistException{
		if (!hasContact(name)) {
			throw new ContactDoesNotExistException();
		}
		return contacts[searchIndex(name)].getEmail();
	}

	public void setPhone(String name, int phone) throws ContactDoesNotExistException{
		if (!hasContact(name)) {
			throw new ContactDoesNotExistException();
		}
		contacts[searchIndex(name)].setPhone(phone);
	}

	public void setEmail(String name, String email)  throws ContactDoesNotExistException{
		if (!hasContact(name)) {
			throw new ContactDoesNotExistException();
		}
		contacts[searchIndex(name)].setEmail(email);
	}

	public Iterator listContacts() throws ListEmptyException {
		if (getNumberOfContacts() == 0){
			throw new ListEmptyException();
		} else {
			return new ContactIterator(contacts,counter);
		}
	}
	
	private int searchIndex(String name) {
		int i = 0;
		int result = -1;
		boolean found = false;
		while (i<counter && !found)
			if (contacts[i].getName().equals(name))
				found = true;
			else
				i++;
		if (found) result = i;
		return result;
	}

	private void resize() {
		ContactClass tmp[] = new ContactClass[2*contacts.length];
		for (int i=0;i<counter; i++)
			tmp[i] = contacts[i];
		contacts = tmp;
	}

	
}
