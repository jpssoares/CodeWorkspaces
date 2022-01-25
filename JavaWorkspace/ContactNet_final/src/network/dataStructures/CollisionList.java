package network.dataStructures;

/**
 * 
 * @author Joao Soares, Ricardo Airton
 *
 * @param <K>
 * @param <V>
 */
public class CollisionList<K extends Comparable<K>, V> implements OrderedDictionary<K, V> { //TODO implements ordered dictionary ou dictionary?

    private int currentSize;
    private DListNode<Entry<K,V>> head,tail;

    public CollisionList() {
        head = null;
        tail = null;
        currentSize = 0;
    }

    @Override
    public Entry<K,V> minEntry() {
        return head.getElement();
    }

    @Override
    public Entry<K,V> maxEntry() {
        return tail.getElement();
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public V find(K key) {
        DListNode<Entry<K, V>> auxNo = head;
        for(int pos = 0; pos < currentSize; pos++) {
            Entry<K, V> entry = auxNo.getElement();
            if(entry.getKey().equals(key))
                return entry.getValue();
            auxNo = auxNo.getNext();
        }
        return null;
    }

    private DListNode<Entry<K, V>> getNode(K key) {
        DListNode<Entry<K, V>> auxNo = head;
        while(auxNo != null) {
            Entry<K, V> entry = auxNo.getElement();
            int comparison = entry.getKey().compareTo(key);
            if(comparison >= 0)
                if(comparison == 0)
                    return auxNo;
                else
                    return null;                  // Caso passe da key pretendida
            auxNo = auxNo.getNext();
        }
        return null;
    }

    // Procura o node depois do qual sera inserido o node com a dada key
    private DListNode<Entry<K, V>> findInsertionNode(K key) { //TODO perguntar prof se vale a pena comecar da head e tail simultaneamente
        DListNode<Entry<K, V>> auxNo = head;

        while(auxNo != null) {
            int comparison = auxNo.getElement().getKey().compareTo(key);
            if(comparison >= 0) {
                return auxNo;   // Caso chegue/passe da key pretendida retorna o node
            }
            auxNo = auxNo.getNext();
        }
        return auxNo; //Retorna null caso o elemento seja depois de todos os outros
    }

    /* INSERT ANTIGO @Override
    public V insert(K key, V value) {
        if(head == null) {
            addEmpty(key, value);
        } else {
            DListNode<Entry<K, V>> insertionNode = findInsertionNode(key);
            Entry<K, V> e = insertionNode.getElement();
            if(e.getKey().equals(key)) {
                V oldValue = e.getValue();
                insertionNode.setElement(new EntryClass<>(key, value));
                return oldValue;
            } else if (insertionNode.equals(head))
                addFirst(key, value);
            else if (insertionNode.equals(tail))
                addLast(key, value);
            else
                addMiddle(key, value, insertionNode);
        }
        currentSize++;
        return null;
    }*/

    @Override
    public V insert(K key, V value) { //TODO MELHORAR METODO
        if (head == null) addEmpty(key, value);
        else {
            DListNode<Entry<K, V>> insertionNode = findInsertionNode(key);
            if (insertionNode == null) {
                addLast(key, value);
            } else {
                Entry<K, V> e = insertionNode.getElement();
                int comp = e.getKey().compareTo(key);
                if (comp == 0) {
                    V oldValue = e.getValue();
                    insertionNode.setElement(new EntryClass<>(key, value));
                    return oldValue;
                } else if (insertionNode.equals(head)) {
                    if(comp > 0)
                        addFirst(key, value);
                    else
                        addLast(key, value);
                } else
                    addMiddle(key, value, insertionNode);
            }
        }
        currentSize++;
        return null;
    }

    private void addEmpty(K key, V value) {
        DListNode<Entry<K, V>> node = new DListNode<>(new EntryClass<>(key, value));
        head = node;
        tail = node;
    }

    private void addFirst(K key, V value) {
        DListNode<Entry<K, V>> node = new DListNode<>(new EntryClass<>(key, value), null, head);
        head.setPrevious(node);
        head = node;
    }

    private void addMiddle(K key, V value, DListNode<Entry<K, V>> rightNode) { //E passado como argumento o node a direita do new Node
        DListNode<Entry<K, V>> leftNode = rightNode.getPrevious();
        DListNode<Entry<K, V>> newNode = new DListNode<>(new EntryClass<>(key, value), leftNode, rightNode);
        rightNode.setPrevious(newNode);
        leftNode.setNext(newNode);
    }

    private void addLast(K key, V value) {
        DListNode<Entry<K, V>> node = new DListNode<>(new EntryClass<>(key, value), tail, null);
        tail.setNext(node);
        tail = node;
    }

    @Override
    public V remove(K key) {
        DListNode<Entry<K, V>> node = getNode(key);
        V value = null;
        if (node != null) {
            if(currentSize == 1) {
                value  = head.getElement().getValue();
                head = null;
                tail = null;
            } else if(node.equals(head))
                value = removeFirst();
            else if(node.equals(tail))
                value = removeLast();
            else
                value = removeMiddle(node);

            currentSize--;
        }
        return value;
    }

    private V removeMiddle(DListNode<Entry<K, V>> node) {
        DListNode<Entry<K, V>> leftNode = node.getPrevious();
        DListNode<Entry<K, V>> rightNode = node.getNext();
        leftNode.setNext(rightNode);
        rightNode.setPrevious(leftNode);
        return node.getElement().getValue();
    }

    private V removeFirst() {
        V value = head.getElement().getValue();
        head = head.getNext();
        head.setPrevious(null);
        return value;
    }

    private V removeLast() {
        V value = tail.getElement().getValue();
        tail = tail.getPrevious();
        tail.setNext(null);
        return value;
    }

    @Override
    public Iterator<Entry<K,V>> iterator() {
        return new DoublyLLIterator<>(head, tail); //TODO usar doublyLLIterator?
    }
}
