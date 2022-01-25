package network.dataStructures;



public class AVLTree <K extends Comparable<K>,V> 
	extends AdvancedBSTree<K,V> {
	
static class AVLNode<K,V> extends BSTNode<K,V> {
		// Altura do no
		protected int height;
		
		public AVLNode(K key, V value) {
			super(key, value);
			height = 1;
		}
		
		public AVLNode(K key, V value, AVLNode<K,V> parent, AVLNode<K,V> left, AVLNode<K,V> right){ 
			super(key, value, parent, left, right);
			height = 1 + Math.max(getHeight(left),getHeight(right));
		}
		
		protected int getHeight(AVLNode<K,V> node) {
			//precisamos deste metodo porque node pode ser null
			if (node==null)
				return 0;
			return node.getHeight();
		}
		
		public int getHeight() {
			return height;
		}

		public boolean isBalance() {
			int dif= getHeight((AVLNode<K,V>)this.getLeft()) -
					 getHeight((AVLNode<K,V>)this.getRight());
			return dif==0 ||dif==-1 ||dif ==1;
		}
		
		public int setHeight() {
			height= 1 + Math.max(getHeight((AVLNode<K,V>)this.getLeft()),
								 getHeight((AVLNode<K,V>)this.getRight()));
			return height;
		}

		/**
		 * Devolve o filho com a maior altura
		 */
		protected AVLNode<K,V> tallerChild()  {
			 AVLNode<K,V> leftChild = (AVLNode<K,V>) this.getLeft();
			 AVLNode<K,V> rightChild = (AVLNode<K,V>) this.getRight();
			 int leftChildHeight  = getHeight((AVLNode<K,V>) leftChild);
			 int rightChildHeight = getHeight((AVLNode<K,V>) rightChild);

			 if (leftChildHeight > rightChildHeight)
				 return leftChild;
			 else if (leftChildHeight < rightChildHeight)
				 return rightChild;
			return null;
		}
}

	public AVLTree() {
		super();
	}
	
/**  
 * Rebalance method called by insert and remove.  Traverses the path from 
 * zPos to the root. For each node encountered, we recompute its height 
 * and perform a trinode restructuring if it's unbalanced.
 * the rebalance is completed with O(log n)running time
 */
  protected void rebalance(AVLNode<K,V> zPos) {
    if(zPos.isInternal())
       zPos.setHeight();
    /*//TODO tentar melhorar// Fazer rebalanceForRemove?
	  if(zPos == root) {
		  if (!zPos.isBalance()) {
			  //perform a trinode restructuring at zPos's tallest grandchild
			  //If yPos (tallerChild(zPos)) denote the child of zPos with greater height.
			  //Finally, let xPos be the child of yPos with greater height
			  AVLNode<K,V> xPos = zPos.tallerChild().tallerChild();
			  zPos = (AVLNode<K, V>) restructure(xPos); // tri-node restructure (from parent class)
			  //note that zPos now may be a different node (the new root of the tri-node)

			  // recompute heights for these 3 nodes
			  ((AVLNode<K, V>) zPos.getLeft()).setHeight();
			  ((AVLNode<K, V>) zPos.getRight()).setHeight();
			  zPos.setHeight();
		  }
	  }
	  // TODO*/
    // Melhorar se possivel
    while (zPos.getParent()!=null) {  // traverse up the tree towards the root
      zPos = (AVLNode<K, V>) zPos.getParent();
      zPos.setHeight();
      if (!zPos.isBalance()) { 
    	  //perform a trinode restructuring at zPos's tallest grandchild
    	  //If yPos (tallerChild(zPos)) denote the child of zPos with greater height. 
    	  //Finally, let xPos be the child of yPos with greater height
    	  AVLNode<K,V> xPos = zPos.tallerChild().tallerChild();
    	  zPos = (AVLNode<K, V>) restructure(xPos); // tri-node restructure (from parent class)
    	  //note that zPos now may be a different node (the new root of the tri-node)
        
    	  // recompute heights for these 3 nodes
    	  ((AVLNode<K, V>) zPos.getLeft()).setHeight();  
    	  ((AVLNode<K, V>) zPos.getRight()).setHeight();
    	  zPos.setHeight();
      }
    }
  }

	private AVLNode<K, V> findPlaceToInsert(AVLNode<K, V> node, K key) {
		int compValue = key.compareTo(node.getKey());
		if (compValue != 0) {
			if (compValue < 0) {
				AVLNode<K, V> left = (AVLNode<K, V>) node.getLeft();
				if (left != null)
					return findPlaceToInsert(left, key);
			} else {
				AVLNode<K, V> right = (AVLNode<K, V>) node.getRight();
				if (right != null)
					return findPlaceToInsert(right, key);
			}
		}
		return node;
	}
 
@Override
public V insert(K key, V value) { //TODO E suposto fazer assim? Metodo e quase uma replica do insert da BST
	//TODO
//	V valueToReturn = null;
	AVLNode<K,V> newNode; // node where the new entry is being inserted (if find(key)==null)
	// insert the new Entry (if find(key)==null)
	// or set the new value (if find(key)!=null)
	if(root == null)
		root = new AVLNode<>(key, value);
	else {
		AVLNode<K, V> parent  = findPlaceToInsert((AVLNode<K, V>) root, key);
		newNode = new AVLNode<>(key, value, parent, null, null);
		int compResult = key.compareTo(parent.getKey());
		if(compResult == 0) {
			V oldValue = parent.getValue();
			parent.setValue(value);
			return oldValue;
		} else if(compResult > 0) {
			parent.setRight(newNode);
		} else {
			parent.setLeft(newNode);
		}

		rebalance(newNode); // rebalance up from the inserted node
		//rebalance checks if it needs to call restructure
	}

	currentSize++;
	return null;
}

@Override
public V remove(K key) {
	// TODO
	V valueToReturn;
	BSTNode<K, V> nodeToRemove = findNode(root, key);
	AVLNode<K, V> node = null;
	if(nodeToRemove != null)
		node = (AVLNode<K, V>) nodeToRemove.getParent(); // father of node where the key was deleted

	valueToReturn = removeNode(nodeToRemove);

	if (node != null) //(if find(key)==null)
		rebalance(node); // rebalance up from the node

	return valueToReturn;
  }

}
