package network.dataStructures;

/**
 * 
 * @author Joao Soares, Ricardo Airton
 *
 * @param <K>
 * @param <V>
 */
public class ChainedHashTableIterator<K, V> implements TwoWayIterator<Entry<K, V>> { //TODO two way ou one way iterator?

	private static final long serialVersionUID = 1L;
	private Dictionary<K, V>[] table;
    private int current;
    private TwoWayIterator<Entry<K, V>> entryIterator;

    public ChainedHashTableIterator(Dictionary<K, V>[] table) {
        this.table = table;
        this.rewind();
    }

    @Override
    public boolean hasNext() {
        if(entryIterator == null)
            return false;
        return entryIterator.hasNext();
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if(!hasNext())
            throw new NoSuchElementException();
        Entry<K, V> entry = entryIterator.next();
        if(!hasNext()) {
            current++;
            nextIterator();
        }
        return entry;
    }

    private TwoWayIterator<Entry<K, V>> nextIterator() {
        for(int i = current; i < table.length; i++)
            if(!table[i].isEmpty()) {
                current = i;
                return (TwoWayIterator<Entry<K, V>>) table[i].iterator();
            }
        return null;
    }

    @Override
    public void rewind() {
        current = 0;
        entryIterator = nextIterator();
    }

    @Override
    public boolean hasPrevious() {
        if(entryIterator == null)
            return false;
        return entryIterator.hasNext();
    }

    @Override
    public Entry<K, V> previous() throws NoSuchElementException {
        if(!hasPrevious())
            throw new NoSuchElementException();

        Entry <K, V> entry = entryIterator.previous();
        if(!hasPrevious()) {
            entryIterator = previousIterator();
            if(entryIterator != null)
                entryIterator.fullForward();
        }

        return entry;
    }

    @Override
    public void fullForward() {
        current = table.length - 1;
        entryIterator = previousIterator();
    }

    private TwoWayIterator<Entry<K, V>> previousIterator() { //TODO verificar se esta certo
        for(int i = current; current >= 0; current--)
            if(!table[i].isEmpty()) {
                current = i;
                TwoWayIterator<Entry<K, V>> it = (TwoWayIterator<Entry<K,V>>) table[i].iterator();
                it.fullForward();
                return it;
            }
        return null;
    }

}
