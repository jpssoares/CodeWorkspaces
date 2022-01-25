package network.dataStructures;

/**
 * BinarySearchTree implementation
 * 
 * @author AED team
 * @version 1.0
 * @param <K> Generic type Key, must extend comparable
 * @param <V> Generic type Value
 */
public class BinarySearchTree<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {

	/**
	 * BST node implementation
	 *
	 * @author AED team
	 * @version 1.0
	 *
	 * @param <K> Generic type Key
	 * @param <V> Generic type Value
	 */
	static class BSTNode<K, V> {

		// Entry stored in the node.
		private EntryClass<K, V> entry;

		// (Pointer to) the parent node.
		private BSTNode<K, V> parent;

		// (Pointer to) the left child.
		private BSTNode<K, V> leftChild;

		// (Pointer to) the right child.
		private BSTNode<K, V> rightChild;

		/**
		 * Constructor for BST nodes
		 *
		 * @param key   to be stored in this BST tree node
		 * @param value to be stored in this BST tree node
		 * @param left  sub-tree of this node
		 * @param right sub-tree of this node
		 */
		public BSTNode(K key, V value, BSTNode<K, V> parent, BSTNode<K, V> left, BSTNode<K, V> right) {
			entry = new EntryClass<K, V>(key, value);
			this.parent = parent;
			leftChild = left;
			rightChild = right;
		}

		/**
		 * Constructor for BST nodes
		 *
		 * @param key   to be stored in this BST tree node
		 * @param value to be stored in this BST tree node
		 */
		public BSTNode(K key, V value) {
			this(key, value, null, null, null);
		}

		/**
		 * Returns the parent node of the current node.
		 *
		 * @return
		 */
		public BSTNode<K, V> getParent() {
			return parent;
		}

		/**
		 * Returns the left child node of the current node.
		 *
		 * @return
		 */
		public BSTNode<K, V> getLeft() {
			return leftChild;
		}

		/**
		 * Returns the right child node of the current node.
		 *
		 * @return
		 */
		public BSTNode<K, V> getRight() {
			return rightChild;
		}

		/**
		 * Returns the entry (key and value) of the current node.
		 *
		 * @return
		 */
		public EntryClass<K, V> getEntry() {
			return entry;
		}

		/**
		 * Returns the key of the current node.
		 *
		 * @return
		 */
		public K getKey() {
			return entry.getKey();
		}

		/**
		 * Returns the value of the current node.
		 *
		 * @return
		 */
		public V getValue() {
			return entry.getValue();
		}

		/**
		 * Assigns a new entry (key and value) to the current BST node
		 *
		 * @param newEntry
		 */
		public void setEntry(EntryClass<K, V> newEntry) {
			entry = newEntry;
		}

		/**
		 * Sets the new value object of the current node.
		 *
		 * @param newValue
		 */
		public void setValue(V newValue) {
			entry.setValue(newValue);
		}

		/**
		 * Sets the new left child node of this node
		 *
		 * @param newLeft the new left child node of the current node
		 */
		public void setLeft(BSTNode<K, V> newLeft) {
			leftChild = newLeft;
		}

		/**
		 * Sets the new right child node of this node
		 *
		 * @param newRight the new right child node of the current node
		 */
		public void setRight(BSTNode<K, V> newRight) {
			rightChild = newRight;
		}

		/**
		 * Sets the new parent of this node
		 *
		 * @param newParent the new parent of the current node
		 */
		public void setParent(BSTNode<K, V> newParent) {
			parent = newParent;
		}

		/**
		 * Returns true iff the current node is internal.
		 */
		public boolean isInternal() {
			return (leftChild != null || rightChild != null);
		}

		/**
		 * Returns true iff the node is a leaf.
		 *
		 * @return
		 */
		public boolean isLeaf() {
			return (leftChild == null && rightChild == null);
		}

	}

	// The root of the tree.
	protected BSTNode<K, V> root;

	// Number of nodes in the tree.
	protected int currentSize;

	/**
	 * Tree Constructor - creates an empty tree.
	 */
	public BinarySearchTree() {
		root = null;
		currentSize = 0;
	}

	/**
	 * Returns the number of children of node.
	 *
	 * @param node
	 * @return the number of children of node
	 */
	protected int numChildren(BSTNode<K, V> node) {
		// TODO node so pode ter 2 filhos certo?
		int nChildren = 0;
		if (node.getRight() != null)
			nChildren++;
		if (node.getLeft() != null)
			nChildren++;
		return nChildren;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public int size() {
		return currentSize;
	}

	/**
	 * Returns the node whose key is the specified key; or null if no such node
	 * exists.
	 *
	 * @param node where the search starts
	 * @param key  to be found
	 * @return the found node, when the search is successful
	 */
	protected BSTNode<K, V> findNode(BSTNode<K, V> node, K key) {
		if (node == null)
			return null;
		else {
			int compResult = key.compareTo(node.getKey());
			if (compResult == 0)
				return node;
			else if (compResult < 0)
				return this.findNode(node.getLeft(), key);
			else
				return this.findNode(node.getRight(), key);
		}
	}

	@Override
	public V find(K key) {
		BSTNode<K, V> node = this.findNode(root, key);
		if (node == null)
			return null;
		else
			return node.getValue();
	}

	@Override
	public Entry<K, V> minEntry() throws NoElementException {
		if (isEmpty())
			throw new NoElementException();

		return this.minNode(root).getEntry();
	}

	@Override
	public Entry<K, V> maxEntry() throws NoElementException {
		if (this.isEmpty())
			throw new NoElementException();

		return this.maxNode(root).getEntry();
	}

	/**
	 * Returns the node with the largest key in the tree rooted at the specified
	 * node. Requires: node != null.
	 * 
	 * @param node that roots the tree
	 * @return node with the largest key in the tree
	 */
	private BSTNode<K, V> maxNode(BSTNode<K, V> node) {
		if (node.getRight() == null)
			return node;
		else
			return this.maxNode(node.getRight());
	}

	/**
	 * Returns the node with the smallest key in the tree rooted at the specified
	 * node. Requires: node != null.
	 * 
	 * @param node that roots the tree
	 * @return node with the largest key in the tree
	 */
	private BSTNode<K, V> minNode(BSTNode<K, V> node) {
		if (node.getLeft() == null)
			return node;
		else
			return this.minNode(node.getLeft());
	}

	public V insert(K key, V value) {
		// caso especial:
		// caso a arvore esteja vazia, inserir raiz
		if (root == null)
			root = new BSTNode<K, V>(key, value);
		else {
			// findPlaceToInsert - parecido com o findNode mas devolve pai do novo no
			BSTNode<K, V> parent = findPlaceToInsert(root, key);

			int compResult = key.compareTo(parent.getKey());
			/*
			 * Se chave de parent == key Alterar valor de parent para value devolver value
			 * antigo
			 */
			if (compResult == 0) {
				V oldValue = parent.getValue();
				parent.setValue(value);
				return oldValue;
			}

			/*
			 * Senao inserir novo no como filho esquerdo ou direito de parent
			 */
			else if (compResult > 0) {
				parent.setRight(new BSTNode<>(key, value, parent, null, null));
			} else {
				parent.setLeft(new BSTNode<>(key, value, parent, null, null));
			}

		}
		// devolver null(nao esquecer o currentSize)
		currentSize++;
		return null;
	}

	// Retorna o pai do novo no
	private BSTNode<K, V> findPlaceToInsert(BSTNode<K, V> node, K key) {
		int compValue = key.compareTo(node.getKey());
		if (compValue != 0) {
			if (compValue < 0) {
				BSTNode<K, V> left = node.getLeft();
				if (left != null)
					return findPlaceToInsert(left, key);
			} else {
				BSTNode<K, V> right = node.getRight();
				if (right != null)
					return findPlaceToInsert(right, key);
			}
		}
		return node;
	}

	public V remove(K key) {
		return removeNode(findNode(root, key));
	}

	protected V removeNode(BSTNode<K, V> nodeToRemove) {
		if (nodeToRemove != null) {
			V value = nodeToRemove.getValue();
			int nChildren = 0;
			BSTNode<K, V> leftChild = nodeToRemove.getLeft();
			if (leftChild != null)
				nChildren++;
			BSTNode<K, V> rightChild = nodeToRemove.getRight();
			if (rightChild != null)
				nChildren++;

			if (nChildren == 0) {
				removeNoChildren(nodeToRemove);
			} else if (nChildren == 1) {
				if (leftChild != null)
					replaceParentWithChild(nodeToRemove, leftChild);
				else
					replaceParentWithChild(nodeToRemove, rightChild);
			} else {
				remove2Children(nodeToRemove);
			}
			currentSize--;
			return value;

		} else // nao encontra no para remover
			return null;
	}

	/**
	 * Remove um no folha.
	 * 
	 * @param nodeToRemove - no a remover
	 */
	private void removeNoChildren(BSTNode<K, V> nodeToRemove) {

		// se nodeToRemove e folha o pai fica a pontar para null

		BSTNode<K, V> parent = nodeToRemove.getParent();
		if (parent == null)
			root = null;
		else if (nodeToRemove.equals(parent.getRight()))
			parent.setRight(null);
		else
			parent.setLeft(null);
	}

	/**
	 * Remove um no com apenas um filho.
	 * 
	 * @param nodeToRemove - no a remover
	 * @param child        - filho
	 */
	private void replaceParentWithChild(BSTNode<K, V> nodeToRemove, BSTNode<K, V> child) { // TODO mandar nodeToRemove

		// se nodeToRemove so tem um filho o pai fica a apontar para esse filho

		BSTNode<K, V> parent = nodeToRemove.getParent();
		child.setParent(parent);
		if (parent != null) { // caso o parent nao seja a root
			int compResult = nodeToRemove.getKey().compareTo(parent.getKey());
			if (compResult > 0)
				parent.setRight(child);
			else
				parent.setLeft(child);
		} else
			root = child;
	}

	/**
	 * Remove um no com dois filhos.
	 * 
	 * @param nodeToRemove - no a remover
	 */
	private void remove2Children(BSTNode<K, V> nodeToRemove) {
		/*
		 * Procurar no substituto (na subarvore esq. ou na dir.) remover no substituto
		 * trocar entry do nodeToRemove pela entry do no substituto
		 */
		BSTNode<K, V> minNode = minNode(nodeToRemove.getRight()); // TODO verificar se esta bem feita a remocao
		BSTNode<K, V> minParent = minNode.getParent();
		BSTNode<K, V> rightNode = minNode.getRight();

		if (minParent != nodeToRemove)
			minParent.setLeft(rightNode);
		else
			minParent.setRight(rightNode);

		if (rightNode != null)
			rightNode.setParent(minParent);

		nodeToRemove.setEntry(minNode.getEntry());
	}

	/**
	 * Devolve um iterador das entradas num dicionario que preserva a relacao entre
	 * a ordem das chaves.
	 * 
	 * @return Iterador das entradas no Dicionario
	 */
	public Iterator<Entry<K, V>> iterator() {
		return new BSTKeyOrderIterator<>(root);
	}

}
