package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return recursive_height(this.root);
	}

	private int recursive_height(BSTNode<T> node) {
		if (node.isEmpty()) return -1;
		else return 1 + Math.max(recursive_height((BSTNode<T>) node.getLeft()), recursive_height((BSTNode<T>) node.getRight()));
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element == null) return new BSTNode<>();
		else return search_recursive(this.root, element);
	}

	private BSTNode<T> search_recursive(BSTNode<T> node, T element) {
		if (node.isEmpty()) return new BSTNode<>();
		else if (node.getData().compareTo(element) == 0){
			return node;
		}
		else if (element.compareTo(node.getData()) > 0){
			return search_recursive((BSTNode<T>) node.getRight(), element);
		}
		else  {
			return search_recursive((BSTNode<T>) node.getLeft(), element);
		}
	}

	@Override
	public void insert(T element) {
		if (element != null){
			if (isEmpty()){
				this.root = (BSTNode<T>) new BSTNode.Builder<T>()
						.data(element)
						.left(new BSTNode<>())
						.right(new BSTNode<>())
						.build();
			}else {
				insert_recursive(this.root, element);
			}
		}
	}

	private void insert_recursive(BSTNode<T> node, T element) {
		BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>()
				.data(element)
				.right(new BSTNode<>())
				.left(new BSTNode<>())
				.parent(node)
				.build();
		if (element.compareTo(node.getData()) > 0){
			if (node.getRight().isEmpty()){
				node.setRight(newNode);
			} else {
				insert_recursive((BSTNode<T>) node.getRight(), element);
			}
		} else {
			if (node.getLeft().isEmpty()) {
				node.setLeft(newNode);
			} else {
				insert_recursive((BSTNode<T>) node.getLeft(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (isEmpty()) return null;
		else return maximum_recursive(this.root);
	}

	private BSTNode<T> maximum_recursive(BSTNode<T> node) {
		if (node.getRight().isEmpty()){
			return node;
		} else {
			return maximum_recursive((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if (isEmpty()) return null;
		else return minimum_recursive(this.root);
	}

	private BSTNode<T> minimum_recursive(BSTNode<T> node) {
		if (node.getLeft().isEmpty()){
			return node;
		} else {
			return minimum_recursive((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		if (isEmpty() || element == null || element.compareTo(maximum().getData()) >= 0 ) return null;
		BSTNode<T> node = search_recursive(this. root, element);
		if (!node.isEmpty()){
			if (!node.getRight().isEmpty()){
				return minimum_recursive((BSTNode<T>) node.getRight());
			} else {
				BSTNode<T> nodeAux = (BSTNode<T>) node.getParent();
				while (nodeAux.getData().compareTo(node.getData()) < 0){
					nodeAux = (BSTNode<T>) nodeAux.getParent();
				}
				return nodeAux;
			}
		}
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		if (isEmpty() || element == null || element.compareTo(minimum().getData()) <= 0 ) return null;
		BSTNode<T> node = search_recursive(this. root, element);
		if (!node.isEmpty()){
			if (!node.getLeft().isEmpty()){
				return maximum_recursive((BSTNode<T>) node.getLeft());
			} else {
				BSTNode<T> nodeAux = (BSTNode<T>) node.getParent();
				while (nodeAux.getData().compareTo(node.getData()) > 0){
					nodeAux = (BSTNode<T>) nodeAux.getParent();
				}
				return nodeAux;
			}
		}
		return null;
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
