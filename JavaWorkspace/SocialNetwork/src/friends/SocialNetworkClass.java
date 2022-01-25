package friends;

/**
 * 
 * @author Joao Soares N57609
 *
 */

public class SocialNetworkClass implements SocialNetwork {

	private static final int MAX_PEOPLE = 500;

	/**
	 * O vector de amigos.
	 */
	private Person[] people;

	/**
	 * O numero de amigos actual
	 */
	private int counter;

	public SocialNetworkClass() {
		people = new Person[MAX_PEOPLE];
		counter = 0;
	}

	@Override
	public String getStatus(String name) {
		return (getPerson(name).getStatus());
	}

	@Override
	public void addPerson(String name, String email, String status) {
		people[counter] = new PersonClass(name, email, status);
		counter++;
	}

	@Override
	public void addFriendship(String n1, String n2) {
		getPerson(n1).addFriendship(n2);
		getPerson(n2).addFriendship(n1);
	}

	@Override
	public void changeStatus(String name, String status) {
		getPerson(name).changeStatus(status);
	}

	@Override
	public boolean hasPerson(String name) {
		if (!isEmpty()) {
			for (int i = 0; i < counter; i++) {
				if (people[i].getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasFriendship(String n1, String n2) {
		return getPerson(n1).hasFriendship(n2);
	}

	@Override
	public Person getPerson(String name) {
		for (int i = 0; i < counter; i++) {
			if (people[i].getName().equals(name)) {
				return people[i];
			}
		}
		return null;
	}

	@Override
	public FriendIterator listFriends(String name) {
		return new FriendIterator(getPerson(name).getFriends(), getPerson(name).getFriendsCounter());
	}

	@Override
	public boolean isEmpty() {
		return (counter == 0);
	}

	@Override
	public int numberOfPeople() {
		return counter;
	}

	@Override
	public Person getPersonWithIndex(int num) {
		return people[num];
	}

	@Override
	public PostIterator listPosts(String name) {
		return new PostIterator(getPerson(name).getPosts(), getPerson(name).getPostsCounter());
	}

	@Override
	public void addPost(String name, String author, String msg) {
		getPerson(name).addPost(author, msg);
	}
}
