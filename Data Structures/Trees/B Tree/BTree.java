package BTree;
//Import Statements
//Local Imports
import AbstractTree.Node;
import AbstractTree.Tree;
import TreeComparators.NodeComparator;
import TreeExceptions.ChildIndexOutOfBoundsException;
import TreeExceptions.NullChildException;
//External Imports
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Iterator;



/**
 * Implementation of a generic B-Tree.
 * Will work for whatever order is set upon creation.
 * Duplicate keys are not added to the tree. If no comparator
 * is provided uses defined compareTo method in a NaturalComparator
 * setup.
 * @author Tim Fleece
 * @param <T>
 */
public class BTree<T extends Comparable<? super T>> extends Tree<T, BTreeNode<T>> {

	
	public BTree(int order) {
		super(order);
		ncmp = new BTreeNodeComparator<T>(this.cmp);
		root = new BTreeNode<T>(this.cmp, ncmp, min_keys, max_keys);

	}

	public BTree(int order, Comparator<T> cmp) {
		super(order, cmp);
		ncmp = new BTreeNodeComparator<T>(this.cmp);
		root = new BTreeNode<T>(this.cmp, ncmp, min_keys, max_keys);
	}

	public int size() {
		size = size(root, 0, 0);
		return size;
	}
	
	private int size(BTreeNode<T> node, int size, int level) {
		if(node.isLeaf()) {
			size += 1;
			node.setLevel(level);
		} else {
			BTreeNode<T> child;
			size += 1;
			node.setLevel(level);
			for(int i = 0; i < node.childCount(); i++) {
				try {
					child = node.childAt(i);
					size = size(child, size, level + 1);
				} catch (ChildIndexOutOfBoundsException e) {
					System.out.println(e.getMessage());
				}
				
			}
		}
		return size;
	}

	public boolean isEmpty() {
		return element_count == 0;
	}

	public boolean contains(T key) {
		boolean to_return = false;
		to_return = root.contains(key);
		if(to_return == false) {
			to_return = contains(root, key);
		}
		return to_return;
	}

	private boolean contains(BTreeNode<T> gp, T key) {
		boolean to_return = false;
		return to_return;
	}

	public void add(T key) throws ChildIndexOutOfBoundsException {
		if(ignore_exceptions) {
			try {
				add(root, key);
			} catch(ChildIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}
		} else {
			add(root, key);

		}
	}

	private void add(BTreeNode<T> gp, T key) throws ChildIndexOutOfBoundsException {
		BTreeNode<T> parent, child;
		int parent_index, child_index;
		if(gp.isLeaf() && !gp.contains(key)) {
			gp.addKey(key);
			element_count += 1;
			if(gp.overFull()) {
				gp.split();
			}
		} else {
			parent = gp.closestChild(key);
			parent_index = gp.childIndex(parent);
			if(parent.isLeaf() && !parent.contains(key)) {
				parent.addKey(key);
				element_count += 1;
				if(parent.overFull()) {
					gp.split(parent_index);
					if(gp.overFull()) {
						gp.split();
					}
				}
			} else {
				child = parent.closestChild(key);
				child_index = parent.childIndex(child);
				if(child.isLeaf() && !child.contains(key)) {
					child.addKey(key);
					element_count += 1;
					if(child.overFull()) {
						parent.split(child_index);
						if(parent.overFull()) {
							gp.split(parent_index);
						}
					}
				} else {
					add(parent, key);
				}
			}

		}
	}

	public boolean remove(T key) throws ChildIndexOutOfBoundsException, NullChildException {
		boolean to_return = false;
		if(ignore_exceptions) {
			try {
				remove(root, key);
			} catch(ChildIndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			} catch (NullChildException e) {
				System.out.println(e.getMessage());
			}
		} else {
			remove(root, key);
		}
		if(to_return) {
			element_count -= 1;
		}

		if(root.keyCount() == 0 && root.childCount() == 1) {
			BTreeNode<T> new_root = root.removeMinChild();
			root = null;
			root = new_root;
		}
		return to_return;
	}

	@Override
	public boolean remove(BTreeNode<T> gp, T key) throws ChildIndexOutOfBoundsException, NullChildException {
		BTreeNode<T> parent = null, child = null;
		boolean to_return = false;
		if(gp.contains(key)) {
			if(gp.isLeaf()) {
				gp.removeKey(key);
				to_return = true;
			} else {
				predecessorRemove(null, (BTreeNode<T>) gp, key);
			}
		} else {
			parent = (BTreeNode<T>) gp.closestChild(key);
			if(parent.contains(key)) {
				if(parent.isLeaf()) {
					parent.removeKey(key);
					to_return = true;
				} else {
					to_return = predecessorRemove((BTreeNode<T>) gp, parent, key);
				}
			} else {
				if(parent.isLeaf()) {
					to_return = false;
				} else {
					child = parent.closestChild(key);
					if(child.isLeaf()) {
						if(child.contains(key)) {
							child.removeKey(key);
							to_return = true;
						} else {
							to_return = false;
						}
					} else {
						if(child.contains(key)) {
							to_return = predecessorRemove(parent, child, key);
						} else {
							to_return = remove(parent, key);
						}
					}
				}
			}
		}
		checkRepairStatus(parent, child);
		checkRepairStatus((BTreeNode<T>) gp, parent);
		return to_return;
	}


	private boolean predecessorRemove(BTreeNode<T> parent, BTreeNode<T> child, T key) throws ChildIndexOutOfBoundsException, NullChildException {
		boolean to_return = false;
		T predecessor;
		if(parent == null) {
			if(child.contains(key)) {
				predecessor = child.predecessor(key);
				if(predecessor != null) {
					to_return = true;
				}
				repairTree(child);
			}
		} else {
			if(child.contains(key)) {
				predecessor = child.predecessor(key);
				if(predecessor != null) {
					to_return = true;
				} 
				repairTree(parent);
			}
		}
		return to_return;
	}
	
	public void checkRepairStatus(BTreeNode<T> parent, BTreeNode<T> child) throws ChildIndexOutOfBoundsException, NullChildException {
		if(parent != null && child != null) {
			int child_status = child.fillStatus();
			if(child_status == -1 && !child.isEmpty()) {
				parent.repair(child);
			} else if(child_status == 2) {
				child.sort();
				T max_key = child.removeMaxKey();
				add(parent, max_key);
			}
		}
	}
	
	private void repairTree(BTreeNode<T> gp) throws NullChildException, ChildIndexOutOfBoundsException {
		BTreeNode<T> parent, child;
		if(!gp.isLeaf()) {
			for(int i = 0; i < gp.childCount(); i++) {
				parent = gp.childAt(i);
				if(!parent.isLeaf()) {
					for(int j = 0; j < parent.childCount(); j++) {
						child = parent.childAt(j);
						if(!child.isLeaf()) {
							repairTree(parent);
						} 
						if(!child.isEmpty()) {
							if(child.fillStatus() == -1) {
								parent.repair(child);
							}
						} else {
							j = 0;
							continue;
						}
					}
				}
				if(!parent.isEmpty()) {
					if(parent.fillStatus() == -1) {
						gp.repair(parent);
					}
				} else {
					i = 0;
					continue;
				}
			}
		}
	}

	@Override
	public ArrayList<BTreeNode<T>> inOrderArray() {
		return null;
	}
	
	private ArrayList<BTreeNode<T>> inOrderArray(BTreeNode<T> current, ArrayList<BTreeNode<T>> arr) {
		T min_val, max_val;
		if(current != null) {
			min_val = current.min();
			max_val = current.max();
		}
		return arr;
	}
	
	@Override
	public ArrayList<BTreeNode<T>> inOrderArray(int max_level) {
		return null;
	}

	@Override
	public ArrayList<BTreeNode<T>> preOrderArray() {
		return null;
	}

	@Override
	public ArrayList<BTreeNode<T>> postOrderArray() {
		return null;
	}

	@Override
	public Iterator<T> inOrderIterator() {
		return null;
	}

	@Override
	public Iterator<T> preOrderIterator() {
		return null;
	}

	@Override
	public Iterator<T> postOrderIterator() {
		return null;
	}



	@Override
	public BTreeNode<T> newNode() {
		return new BTreeNode<T>(this.cmp, ncmp, min_keys, max_keys);
	}

	

}


class NaturalComparator<T extends Comparable<? super T>> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		return o1.compareTo(o2);
	}
}

class BTreeNodeComparator<T extends Comparable<? super T>> extends NodeComparator<T, BTreeNode<T>> {
	
	public BTreeNodeComparator(Comparator<T> cmp) {
		super(cmp);
	}

	@Override
	public int compare(Node<T, BTreeNode<T>> o1, Node<T, BTreeNode<T>> o2) {
		int to_return = 999;
		T o1_min = o1.min();
		T o2_min = o2.min();
		int min_cmp = cmp.compare(o1_min, o2_min);
		if(min_cmp < 0) {
			to_return = -1;
		} else if(min_cmp == 0) {
			if(o1.keyCount() - 1 > -1 && o2.keyCount() - 1 > -1) {
				T o1_max = o1.max();
				T o2_max = o2.max();
				int max_cmp = cmp.compare(o1_max, o2_max);
				if(max_cmp < 0) {
					to_return = -1;
				} else if(max_cmp == 0 && min_cmp == 0) {
					to_return = 0;
				} else if(max_cmp > 0) {
					to_return = 1;
				}
			}
		}
		return to_return;
	}	
}

class Cast {
	static <T> T as(Class<T> type, Object o) {
		if(type.isInstance(o)) {
			return type.cast(o);
		} else {
			return null;
		}
	}
}







