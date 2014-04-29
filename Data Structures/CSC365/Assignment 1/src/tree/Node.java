package tree;


public class Node<T> {
	T key;
	Node<T> left, right;
	int count;
	public Node(T key) {
		this.key = key;
		left = null;
		right = null;
		count = 1;
	}

	public boolean isLeaf() {
		return left == null && right == null;
	}
	
	public int getCount() {
		return count;
	}
}