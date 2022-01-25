package network.dataStructures;

public class AdvancedBSTree<K extends Comparable<K>, V> extends BinarySearchTree<K, V> {

	// Metodos comuns a arvores binarias de pesquisa auto-equilibradas.
	// Operacoes basicas para alterar a forma da arvore tratando
	// de reduzir a sua altura.

	public AdvancedBSTree() {
		super();
	}

	/**
	 * Efetua uma rotacao singular direita com centro no no Y. No X era filho
	 * esquerdo de Y antes da rotacao. Depois da rotacao, Y passa a ser o filho
	 * direito de X.
	 * 
	 * Y X / turns into: \ X Y
	 * 
	 * @param y - centro da rotacao
	 * @pre: Y tem um filho esquerdo
	 */
	protected void rotateRight(BSTNode<K, V> y) {
		BSTNode<K, V> parent = y.getParent();
		BSTNode<K, V> x = y.getLeft();
		x.setParent(parent);
		if (parent != null) { // caso o parent nao seja a root
			if (parent.getLeft() == y)
				parent.setLeft(x);
			else
				parent.setRight(x);
		} else
			root = x;
		y.setParent(x);
		BSTNode<K, V> tmp = x.getRight();
		x.setRight(y);
		y.setLeft(tmp);
		if (tmp != null)
			tmp.setParent(y);

		// uma rotacao singular modifica a numero constante de relacoes pai-filho,
		// pode ser implementada em O(1) unidades de tempo
	}

	/**
	 * Efetua uma rotacao singular esquerda com centro no no Y. Performs a single
	 * left rotation rooted at Y node. No X era filho direito de Y antes da rotacao.
	 * Depois da rotacao, Y passa a ser o filho esquerdo de X.
	 * 
	 * @param y - root of the rotation
	 * @pre: Y has a right child Y X \ turns into: / X Y
	 */
	protected void rotateLeft(BSTNode<K, V> y) {
		BSTNode<K, V> parent = y.getParent();
		BSTNode<K, V> x = y.getRight();
		x.setParent(parent);
		if (parent != null) {// caso o parent nao seja a root
			if (parent.getRight() == y)
				parent.setRight(x);
			else
				parent.setLeft(x);
		} else
			root = x;
		y.setParent(x);
		BSTNode<K, V> tmp = x.getLeft();
		x.setLeft(y);
		y.setRight(tmp);
		if (tmp != null)
			tmp.setParent(y);

		// uma rotacao singular modifica a numero constante de relacoes pai-filho,
		// pode ser implementada em O(1) unidades de tempo

	}

	/**
	 * Efetua uma restruturacao de tres nos (rotacao singular ou dupla). Assume que
	 * os nos estao numa das seguintes configuracoes:
	 *
	 * <pre>
	 *          z=c       z=c        z=a         z=a
	 *         /  \      /  \       /  \        /  \
	 *       y=b  t4   y=a  t4    t1  y=c     t1  y=b
	 *      /  \      /  \           /  \         /  \
	 *    x=a  t3    t1 x=b        x=b  t4       t2 x=c
	 *   /  \          /  \       /  \             /  \
	 *  t1  t2        t2  t3     t2  t3           t3  t4
	 * </pre>
	 * 
	 * @param x - um no com pai Y e avo Z
	 * @return nova raiz da sub-arvore restruturada
	 */
	protected BSTNode<K, V> restructure(BSTNode<K, V> x) {

		/*
		 * 1 A modificacao duma arvore T causada por uma operacao de restruturacao de
		 * tres nos pode ser implementada atraves de analise de casos, por rotacao
		 * singular ou por rotacao dupla.
		 */

		/*
		 * 2 A rotacao dupla ocorre quando a posicao x tem o meio das tres chaves
		 * relevantes e e primeiro rodada acima do seu pai y, e depois acima do no que
		 * era originalmente o seu avo z.
		 */

		BSTNode<K, V> y = x.getParent();
		BSTNode<K, V> z = y.getParent();
		BSTNode<K, V> newRoot;

		if (y == z.getLeft()) { // rotateRight
			if (x == y.getLeft()) {
				// 1
				rotateRight(z);
				newRoot = y;
			} else {
				// 2
				rotateLeft(y);
				rotateRight(z);
				newRoot = x;
			}
		} else { // y == z.getRight / rotateLeft
			if (x == y.getRight()) {
				// 1
				rotateLeft(z);
				newRoot = y;
			} else {
				// 2
				rotateRight(y);
				rotateLeft(z);
				newRoot = x;
			}
		}
		return newRoot;
		// Em qualquer um dos casos, restruturacao de tres nos fica completa com O(1)
		// tempo de execucao.
	}
}
