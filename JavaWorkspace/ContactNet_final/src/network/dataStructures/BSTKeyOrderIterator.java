package network.dataStructures;

import network.dataStructures.BinarySearchTree.BSTNode;

public class BSTKeyOrderIterator<K, V> implements Iterator<Entry<K, V>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BSTNode<K, V> nextNode;

	public BSTKeyOrderIterator(BSTNode<K, V> root) {
		nextNode = root;
	}

	@Override
	public boolean hasNext() {
		return nextNode != null;
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {

		return getNextNode().getEntry();
	}

	private BSTNode<K, V> getNextNode() {
		// Primeiro verificar a sub-arvore esquerda. Se for null, verificar o no atual e
		// mover para a sub-arvore direita
		if (nextNode.getLeft() == null) {
			// visitar o runner
			BSTNode<K, V> res = nextNode;
			nextNode = nextNode.getRight();
			return res;
		} else {
			// Se a sub-arvore esquerda nao for null, significa que ainda nao visitamos a
			// sub-arvore ou o no atual

			// Loop while continua ate encontrar-se o proximo elemento da ordem
			while (nextNode.getLeft() != null) {
				// Verifica se o apontador direito do pai aponta para o no atual
				BSTNode<K, V> parent = nextNode.getLeft();

				while (parent.getRight() != null && parent.getRight() != nextNode) {
					parent = parent.getRight();
				}

				if (parent.getRight() == nextNode) {
					// Ja visitou a sub-arvore esquerda. Visita o no atual
					parent.setRight(null);
					BSTNode<K, V> res = nextNode;
					nextNode = nextNode.getRight();
					return res;
				} else {
					// Visita a sub-arvore esquerda primeiro
					parent.setRight(nextNode);
					nextNode = nextNode.getLeft();
				}
			}

			BSTNode<K, V> nxt = nextNode;
			nextNode = nextNode.getRight();
			return nxt;

		}
	}

	@Override
	public void rewind() {
		this.rewind();

	}

}
