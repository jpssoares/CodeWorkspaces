/**
 * 
 */
package friends;

/**
 * @author Miguel Goulao / Adriano Lopes / Carla Ferreira
 *
 */
public class StupidFriendsBookClass implements StupidFriendsBook {
	
	private static final int DEFAULT = 10;
	
	/**
	 * O vector de amigos.
	 */
	private Person[] friends;
	
	/**
	 * O numero de amigos actual
	 */
	private int counter;
	
	/**
	 * O amigo corrente
	 */
	private int current;
	
	/**
	 * A personalidade corrente
	 */
	private int personality;
	
	/**
	 * Construtor por omissao
	 */
	public StupidFriendsBookClass(){
		friends = new Person[DEFAULT];
		counter = 0;
		current = 0;
	}
	
	@Override
	public void reset() {
		counter = 0;
		current = 0;
	}
	
	@Override
	public boolean hasFriend(String name) {
		return indexOf(name) != -1;
	}
	
	@Override
	public void addFriend(String name) {
		if (counter == friends.length)  // se necessario, aumentar vector
			resize();
		friends[counter++] = new PersonClass(name);
	}
	
	/**
	 * Metodo auxiliar para duplicar o tamanho do vector. 
	 */
	private void resize() {
		Person[] tmp = new Person[counter * 2];
		for (int i = 0; i < counter; i++)
			tmp[i] = friends[i];
		friends = tmp;
	}
	
	@Override
	public void removeFriend(String name) {
		int index = indexOf(name);
		for (int i = index; i < counter-1; i++)
			friends[i] = friends[i+1];
		counter--;
	}
	
	@Override
	public boolean hasAction(String name, String description) {
		int index = indexOf(name);
		if (index != -1) {
			return friends[index].hasAction(description);
		}
		return false;
	}
	
	@Override
	public void addAction(String name, String description) {
		friends[indexOf(name)].addAction(description);
	}
	
	@Override
	public void vote(String name, String description, boolean goodForPerson, boolean goodForOthers) {
		friends[indexOf(name)].vote(description, goodForPerson, goodForOthers);
	}
	
	@Override
	public int numberOfFriends() {
		return counter;
	}
	
	@Override
	public String mostBoringFriend() {
		String name="";
		double record = Integer.MAX_VALUE;
		for (int i = 0; i < counter; i++)
			if (friends[i].getBoredom() < record) {
				record = friends[i].getBoredom();
				name = friends[i].getName();
			}
		
		return name;
	}
	
	@Override
	public void initialize(int kind) {
		this.personality = kind;
		current = 0;
		while ( (current < counter) && friends[current].getPersonality() != personality)
			current++;
	}
	
	@Override
	public boolean hasNext() {
		return current < counter;
	}
	
	@Override
	public Person next() {
		Person res = friends[current++];
		while ( (current < counter) && friends[current].getPersonality() != personality )
			current++;
		return res;
	}
	
	/**
	 * Metodo auxiliar para pesquisar amigos. 
	 * Percorre os amigos e devolve o indice onde este se encontra ou -1 se nao encontrar
	 */
	private int indexOf(String name) {
		int result = -1;
		int i = 0;
		while (i < counter && result == -1) {
			if (friends[i].getName().equals(name))
				result = i;
			else
				i++;
		}
		return result;
	}
}
