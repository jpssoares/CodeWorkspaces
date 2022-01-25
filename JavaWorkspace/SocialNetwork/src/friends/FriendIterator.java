package friends;

/**
 * Esta classe e utilizada para devolver elementos do array de amigos(friends),
 * um de cada vez.
 * 
 * @author Joao Soares N57609
 *
 */
public class FriendIterator {

	private String[] friends;
	private int counter;
	private int nextFriend;

	public FriendIterator(String[] friends, int counter) {
		this.friends = friends;
		this.counter = counter;
		nextFriend = 0;
	}

	public boolean hasNext() {
		return nextFriend < counter;
	}

	public String next() {
		return friends[nextFriend++];
	}
}
