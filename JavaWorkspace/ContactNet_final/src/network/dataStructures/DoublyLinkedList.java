package network.dataStructures;

/**
 * Doubly linked list Implementation
 * 
 * @author Joao Soares, Ricardo Silva
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
public class DoublyLinkedList<E> implements List<E> {

	/**
	 * Serial Version UID of the Class
	 */
	static final long serialVersionUID = 0L;

	// Node at the head of the list.
	private DListNode<E> head;

	// Node at the tail of the list.
	private DListNode<E> tail;

	// Number of elements in the list.
	private int currentSize;

	public DoublyLinkedList() {
		head = null;
		tail = null;
		currentSize = 0;
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
	public int find(E element) {
		DListNode<E> auxNo = head;
		for (int pos = 0; pos < currentSize; pos++) {
			if (auxNo.getElement().equals(element))
				return pos;
			auxNo = auxNo.getNext();
		}
		return -1;
	}

	@Override
	public E getFirst() throws NoElementException {
		if (isEmpty())
			throw new NoElementException();
		return head.getElement();
	}

	@Override
	public E getLast() throws NoElementException {
		if (isEmpty())
			throw new NoElementException();
		return tail.getElement();
	}

	@Override
	public E get(int position) throws InvalidPositionException {
		if (position < 0 || position >= currentSize)
			throw new InvalidPositionException();
		return getNode(position).getElement();
	}

	private DListNode<E> getNode(int position) {
		DListNode<E> auxNode;
		if (currentSize / 2 >= position) {
			auxNode = head;
			for (int i = 1; i <= position; i++) {
				auxNode = auxNode.getNext();
			}
		} else {
			auxNode = tail;
			for (int i = currentSize - 2; i >= 0; i--)
				auxNode = auxNode.getPrevious();
		}
		return auxNode;
	}

	@Override
	public void addFirst(E element) {
		if (head == null) {
			DListNode<E> node = new DListNode<>(element);
			head = node;
			tail = node;
		} else {
			DListNode<E> node = new DListNode<>(element, null, head);
			head.setPrevious(node);
			head = node;
		}
		currentSize++;
	}

	@Override
	public void addLast(E element) {
		if (head == null) {
			DListNode<E> node = new DListNode<>(element);
			head = node;
			tail = node;
		} else {
			DListNode<E> node = new DListNode<>(element, tail, null);
			tail.setNext(node);
			tail = node;
		}
		currentSize++;
	}

	private void addMiddle(int position, E element) {
		if (head == null) {
			DListNode<E> node = new DListNode<>(element);
			head = node;
			tail = node;
		} else {
			DListNode<E> auxNode = getNode(position - 1);
			DListNode<E> auxNode2 = auxNode.getNext();

			DListNode<E> newNode = new DListNode<>(element, auxNode, auxNode2);

			auxNode.setNext(newNode);
			auxNode2.setPrevious(newNode);
		}
		currentSize++;
	}

	@Override
	public void add(int position, E element) throws InvalidPositionException {
		if (position < 0 || position > currentSize)
			throw new InvalidPositionException();
		if (position == 0)
			addFirst(element);
		else if (position == currentSize)
			addLast(element);
		else {
			addMiddle(position, element);
		}
	}

	/**
	 * Removes the first node in the list. Pre-condition: the list is not empty.
	 */
	private void removeFirstNode() {
		if (currentSize == 1) {
			head = null;
			tail = null;
		} else {
			head = head.getNext();
			head.setPrevious(null);
		}
		currentSize--;
	}

	@Override
	public E removeFirst() throws NoElementException {
		if (isEmpty())
			throw new NoElementException();

		E element = head.getElement();
		this.removeFirstNode();
		return element;
	}

	/**
	 * Removes the last node in the list. Pre-condition: the list is not empty.
	 */
	private void removeLastNode() {
		if (currentSize == 1) {
			head = null;
			tail = null;
		} else {
			tail = tail.getPrevious();
			tail.setNext(null);
		}
		currentSize--;
	}

	@Override
	public E removeLast() throws NoElementException {
		if (isEmpty())
			throw new NoElementException();

		E element = tail.getElement();
		this.removeLastNode();
		return element;
	}

	/**
	 * Removes the specified node from the list. Pre-condition: the node is neither
	 * the head nor the tail of the list.
	 * 
	 * @param node - middle node to be removed
	 */
	private void removeMiddleNode(DListNode<E> node) {
		if (currentSize == 1) {
			head = null;
			tail = null;
		} else {
			DListNode<E> auxNode1 = node.getPrevious();
			DListNode<E> auxNode2 = node.getNext();
			auxNode1.setNext(auxNode2);
			auxNode2.setPrevious(auxNode1);
		}
		currentSize--;
	}

	private E removeMiddle(int position) {
		DListNode<E> nodeToRemove = this.getNode(position);
		this.removeMiddleNode(nodeToRemove);
		return nodeToRemove.getElement();
	}

	@Override
	public E remove(int position) throws InvalidPositionException {
		if (position < 0 || position >= currentSize)
			throw new InvalidPositionException();
		if (position == 0)
			return removeFirst();
		if (position == currentSize - 1)
			return removeLast();
		return removeMiddle(position);
	}

	@Override
	public boolean remove(E element) {
		DListNode<E> node = this.findNode(element);
		if (node == null)
			return false;
		else {
			if (node == head)
				this.removeFirstNode();
			else if (node == tail)
				this.removeLastNode();
			else
				this.removeMiddleNode(node);
			return true;
		}
	}

	private DListNode<E> findNode(E element) {
		DListNode<E> current = head;
		for (int i = 0; i < currentSize; i++) {
			if (current.getElement().equals(element))
				return current;
			current = current.getNext();
		}
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		return new DoublyLLIterator<E>(head, tail);
	}

	public TwoWayIterator<E> backwardsIterator() {
		TwoWayIterator<E> it = new DoublyLLIterator<E>(head, tail);
		it.fullForward();
		return it;
	}

	/**
	 * Removes all of the elements from the specified list and inserts them at the
	 * end of the list (in proper sequence).
	 * 
	 * @param list - list to be appended to the end of this
	 */
	public void append(DoublyLinkedList<E> list) {
		(list.head).setPrevious(this.tail);
		(this.tail).setNext(list.head);
		this.tail = list.tail;
		currentSize += list.size();

		list.head = null;
		list.tail = null;
	}

}
