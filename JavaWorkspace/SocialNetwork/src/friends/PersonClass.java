package friends;

/**
 * Uma pessoa, com uma lista de amigos e varios posts associados.
 * 
 * @author Joao Soares N57609
 *
 */
public class PersonClass implements Person {

	private String name, email, status;

	private String[] friends; // Array de amigos
	private Post[] timeline; // Mural de posts
	private int counter, fcounter; // Contador de posts e de amigos, respetivamente

	public PersonClass(String name, String email, String status) {
		friends = new String[MAX_FRIENDS];
		timeline = new Post[DEFAULT];
		fcounter = 0;
		counter = 0;
		this.name = name;
		this.email = email;
		this.status = status;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public boolean hasFriendship(String friend) {
		if (fcounter != 0) {
			for (int i = 0; i < fcounter; i++) {
				if (friend.equals(friends[i])) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void changeStatus(String msg) {
		status = msg;
	}

	@Override
	public void addFriendship(String friend) {
		if (fcounter < MAX_FRIENDS) {
			friends[fcounter++] = friend;
		}
	}

	@Override
	public void addPost(String name, String msg) {
		if (counter == DEFAULT) {
			resize();
		}
		timeline[counter++] = new PostClass(name, msg);
	}

	/**
	 * Metodo auxiliar para duplicar o tamanho do vector.
	 */
	private void resize() {
		Post[] tmp = new Post[2 * counter];
		for (int i = 0; i < counter; i++)
			tmp[i] = timeline[i];
		timeline = tmp;
	}

	@Override
	public String[] getFriends() {
		return friends;
	}

	@Override
	public int getFriendsCounter() {
		return fcounter;
	}

	@Override
	public boolean hasFriends() {
		return (fcounter != 0);
	}

	@Override
	public Post[] getPosts() {
		return timeline;
	}

	@Override
	public int getPostsCounter() {
		return counter;
	}

}
