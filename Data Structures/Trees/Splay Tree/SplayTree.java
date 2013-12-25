package SplayTree;
//Import Statements
//Local Imports
import TreeComparators.NaturalComparator;

//External Imports
import java.util.Comparator;

/**
 * Implementation of a Splay Tree in Java.
 * Already added values are ignored.
 * @author Tim Fleece
 * @param <T>
 */
@SuppressWarnings("unused")
public class SplayTree<T extends Comparable<? super T>> {
	BinaryNode<T> root;
	Comparator<T> cmp;
	private int size;
	private int height;
	private int element_count;
	public SplayTree() {
		cmp = new NaturalComparator<T>();
		root = new BinaryNode<T>(cmp);
		size = 1;
		height = 1;
	}
	
	public SplayTree(Comparator<T> cmp) {
		this.cmp = cmp;
		root = new BinaryNode<T>(cmp);
		size = 1;
		height = 1;
	}
	
	public void add(T obj) {
		if(root.isLeaf() || root.isEmpty()) {
			root.set(obj);
			size += 1;
		} else {
			add(root, obj);
			splay(obj);
		}
	}
	
	private boolean add(BinaryNode<T> gp, T obj) {
		BinaryNode<T> parent = null, child = null;
		int cmp1, cmp2;
		boolean to_return = false;
		if(gp.isLeaf()) {
			if(gp.isEmpty()) {
				gp.set(obj);
			} else {
				cmp1 = cmp.compare(gp.get(), obj);
				child = gp.newNode();
				child.set(obj);
				size += 1;
				to_return = true;
				if(cmp1 > 0) {
					gp.setLeft(child);
				} else if(cmp1 < 0) {
					gp.setRight(child);
				}
			}
		} else {
			cmp1 = cmp.compare(gp.get(), obj);
			to_return = checkChild(gp, cmp1, obj);
			if(to_return == false) {
				parent = gp.getChild(cmp1);
				if(parent.isLeaf()) {
					child = parent.newNode();
					child.set(obj);
					size += 1;
					to_return = true;
					if(cmp1 < 0) {
						parent.setRight(child);
					} else if(cmp1 > 0) {
						parent.setLeft(child);
					}
				} else {
					to_return = checkChild(parent, cmp1, obj);
					if(to_return == false) {
						child = parent.getChild(cmp1);
						if(child.isLeaf()) {
							cmp2 = cmp.compare(child.get(), obj);
						} else {
							
						}
					}
				}
			}
		}
		return to_return;
	}
	
	private boolean checkChild(BinaryNode<T> parent, int cmp1, T obj) {
		boolean to_return = false;
		BinaryNode<T> child;
		if(cmp1 < 0) {
			if(!parent.hasRight()) {
				child = parent.newNode();
				child.set(obj);
				size += 1;
				parent.setRight(child);
				to_return = true;
			}
		} else if(cmp1 > 0) {
			if(!parent.hasLeft()) {
				child = parent.newNode();
				child.set(obj);
				size += 1;
				parent.setLeft(child);
				to_return = true;
			}
		}
		return to_return;
	}
	
	public void splay(T obj) {
		splay(root,obj);
	}
	
	private void splay(BinaryNode<T> gp, T obj) {
		
	}
	

	//      Pivot = Root.OS
	//		Root.OS = Pivot.RS
	//		Pivot.RS = Root
	//		Root = Pivot
	private BinaryNode<T> rotateLeft(BinaryNode<T> parent) {
		BinaryNode<T> pivot = parent.right();
			parent.setRight(pivot.right());
			pivot.setLeft(parent);
		return pivot;
	}
	
	private BinaryNode<T> rotateRight(BinaryNode<T> parent) {
		BinaryNode<T> pivot = parent.left();
			parent.setLeft(pivot.left());
			pivot.setRight(parent);
		return pivot;
	}
	
	public boolean remove(T obj) {
		boolean to_return = false;
		return to_return;
	}
	
	public int size() {
		return size;
	}
	
	public int height() {
		return height;
	}
	
	public int elements() {
		return element_count;
	}
}

class BinaryNode<T extends Comparable<? super T>> {
	BinaryNode<T> left, right;
	Comparator<T> cmp;
	T value;
	
	public BinaryNode(Comparator<T> cmp) {
		this.cmp = cmp;
	}
	
	public BinaryNode(Comparator<T> cmp, T value) {
		this.cmp = cmp;
		this.value = value;
	}
	
	public void set(T value) {
		this.value = value;
	}
	
	public T get() {
		return value;
	}
	
	public void setLeft(BinaryNode<T> left) {
		this.left = left;
	}
	
	public BinaryNode<T> left() {
		return left;
	}
	
	public void setRight(BinaryNode<T> right) {
		this.right = right;
	}
	
	public BinaryNode<T> right() {
		return right;
	}
	
	public BinaryNode<T> newNode() {
		return new BinaryNode<T>(cmp);
	}
	
	public boolean isLeaf() {
		return left == null && right == null;
	}
	
	public boolean isEmpty() {
		return value == null;
	}
	
	public boolean hasRight() {
		return right != null;
	}
	
	public boolean hasLeft() {
		return left != null;
	}
	
	public BinaryNode<T> getChild(int cmp1) {
		BinaryNode<T> child = null;
		if(cmp1 < 0) {
			return right;
		} else if(cmp1 > 0) {
			return left;
		}
		return child;
	}
}

