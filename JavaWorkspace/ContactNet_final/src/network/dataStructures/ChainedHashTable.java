package network.dataStructures;

/**
 * Chained Hash table implementation
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value 
 */

public class ChainedHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 
	//The array of dictionaries.
    protected Dictionary<K,V>[] table;


    /**
     * Constructor of an empty chained hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    public ChainedHashTable( int capacity ) {
        initializeTable(capacity);
    }

    @SuppressWarnings("unchecked")
    private void initializeTable(int capacity) {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            table[i] = new CollisionList<K,V>();
        maxSize = capacity;
        currentSize = 0;
    }


    public ChainedHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Returns the hash value of the specified key.
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }

    @Override
    public V find( K key ) {
        return table[hash(key)].find(key);
    }

    @Override
    public V insert( K key, V value ) {
        if ( this.isFull() )
            rehash();

        V v = table[hash(key)].insert(key, value);

        if(v == null) //Se v != null significa que apenas foi substituido o value da entry antiga
            currentSize++;

        return v;
    }

    @Override
    public V remove( K key ) {
        V value = null;
        if(!isEmpty()) {
            value = table[hash(key)].remove(key);
        }

        return value;
    }

    @Override
    public Iterator<Entry<K,V>> iterator( ) {
        return new ChainedHashTableIterator<>(table);
    }

    private void rehash() { //TODO verificar se foi bem feito
        maxSize = maxSize * 2;
        Iterator<Entry<K, V>> it = this.iterator();
        initializeTable(maxSize);

        while(it.hasNext()) {
            Entry<K, V> e = it.next();
            insert(e.getKey(), e.getValue());
        }
    }
}
