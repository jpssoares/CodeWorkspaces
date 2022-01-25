package network.dataStructures;

/**
 * 
 * @author Joao Soares, Ricardo Airton
 *
 * @param <K>
 * @param <V>
 */
public interface Entry<K,V> {
	
	//Returns the key in the entry
	K getKey();
	
	//Returns the value in the entry
	V getValue();

	//Changes the value in the entry
	void setValue(V value);
}
