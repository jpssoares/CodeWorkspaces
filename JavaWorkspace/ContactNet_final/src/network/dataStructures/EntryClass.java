package network.dataStructures;

/**
 * 
 * @author Joao Soares, Ricardo Airton
 *
 * @param <K>
 * @param <V>
 */
public class EntryClass<K, V> implements Entry<K, V> {

    private K key;
    private V value;

    public EntryClass(K key, V value) {
    	// Inicializacao dos atributos de cada Entry
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public void setValue(V value) {
        this.value = value;
    }
}
