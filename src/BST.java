public class BST<T extends Comparable<T>> {

	private Node<T> root;

	public BST() {
		root = null;
	}

	private int compare(T x, T y) {
		return x.compareTo(y);
	}

	private class Node<T> {
		private T data;
		private Node<T> left, right;

		public Node(T data) {
			this(data, null, null);
		}

		public Node(T data, Node<T> left, Node<T> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			return data.toString();
		}

	}

	public void insert(T data) {
		root = insert(data, root);
	}

	private Node<T> insert(T data, Node<T> currentPointer) {

		if (currentPointer == null) {
			return new Node<T>(data);
		}

		if (compare(data, currentPointer.data) == 0) {
			return currentPointer;
		}

		if (compare(data, currentPointer.data) < 0) {
			currentPointer.left = insert(data, currentPointer.left);
		} else {
			currentPointer.right = insert(data, currentPointer.right);
		}

		return currentPointer;
	}

	public void preOrderTraversal() {
		preOrderHelper(root);
	}

	private void preOrderHelper(Node<T> currentPointer) {
		if (currentPointer != null) {
			System.out.print(currentPointer + " ");
			preOrderHelper(currentPointer.left);
			preOrderHelper(currentPointer.right);
		}
	}

	public boolean isIdentical(Node<T> a, Node<T> b) {
		// check for reference equality and nulls
		if (a == b)
			return true; // note this picks up case of two nulls
		if (a == null)
			return false;
		if (b == null)
			return false;

		// check for data inequality
		if (a.data != b.data) {
			if ((a.data == null) || (b.data == null))
				return false;
			if (!(a.data.equals(b.data)))
				return false;
		}

		// recursively check branches
		if (!isIdentical(a.left, b.left))
			return false;
		if (!isIdentical(a.right, b.right))
			return false;

		return true;
	}

	public static void main(String[] args) {
		Integer[] a1 = { 7, 1, 0, 3, 2, 5, 4, 6, 9, 8, 10 };
		BST<Integer> bst1 = new BST<Integer>();
		for (Integer n : a1)
			bst1.insert(n);

		Integer[] a2 = { 7, 1, 0, 3, 2, 5, 4, 6, 9, 8, 10 };
		BST<Integer> bst2 = new BST<Integer>();
		for (Integer n : a2)
			bst2.insert(n);

		bst1.preOrderTraversal();
		System.out.println();

		bst2.preOrderTraversal();
		System.out.println();

		System.out.println(bst1.isIdentical(bst1.root, bst2.root));

	}
}