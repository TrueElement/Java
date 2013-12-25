package BTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import AbstractTree.Node;
import TreeComparators.NodeComparator;
import TreeExceptions.ChildIndexOutOfBoundsException;
import TreeExceptions.NullChildException;

public class BTreeNode<T extends Comparable<? super T>> extends Node<T, BTreeNode<T>> {

	public BTreeNode(Comparator<T> cmp, NodeComparator<T,BTreeNode<T>> ncmp, int min_keys, int max_keys) {
		super(cmp,ncmp,min_keys,max_keys);
		keys = new ArrayList<T>();
		children = new ArrayList<BTreeNode<T>>();
	}

	public boolean contains(T key) {
		boolean to_return = false;
		return to_return;
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	public boolean isEmpty() {
		return keys.size() == 0;
	}

	public boolean isFull() {
		return keys.size() == max_keys;
	}

	public boolean overFull() {
		return key_count > max_keys;
	}

	public boolean underFull() {
		return key_count < min_keys;
	}

	public boolean hasMin() {
		return key_count == min_keys;
	}

	public boolean childOverFull() {
		return child_count > key_count + 1;
	}

	public boolean childUnderFull() {
		return child_count < key_count + 1;
	}

	public int fillStatus() {
		int to_return = -2;
		if(underFull()) {
			to_return = -1;
		} else if(isFull()) {
			to_return = 1;
		} else if(hasMin()) {
			to_return = 0;
		} else if(overFull()) {
			to_return = 2;
		}
		return to_return;
	}

	public boolean hasLeft(int index) {
		boolean to_return = false;
		if(index - 1 <= 0) {
			to_return = false;
		} else if(index - 1 >= child_count) {
			to_return = false;
		} else {
			to_return = true;
		}
		return to_return;
	}

	public BTreeNode<T> getLeft(int index) {
		return children.get(index - 1);
	}

	public int getLeftIndex(int index) {
		return index - 1;
	}

	public boolean hasRight(int index) {
		boolean to_return = false;
		if(index + 1 >= child_count) {
			to_return = false;
		} else if(index + 1 < child_count) {
			to_return = true;
		} else {
			to_return = false;
		}
		return to_return;
	}

	public BTreeNode<T> getRight(int index) {
		return children.get(index + 1);
	}

	public int getRightIndex(int index) {
		return index + 1;
	}

	public void addKey(T key) {
		keys.add(key);
		Collections.sort(keys, cmp);
		key_count += 1;
	}

	public void addKey(int index, T key) {
		keys.add(index, key);
		key_count += 1;
	}

	public void addKey(ArrayList<T> keys) {
		for(int i = 0; i < keys.size(); i++){
			this.keys.add(keys.get(i));
		}
		key_count = this.keys.size();
		Collections.sort(this.keys, cmp);
	}

	public void setKey(int index, T key) {
		keys.set(index, key);
	}

	public T min() {
		return keys.get(0);
	}

	public T max() {
		if(key_count > 0) {
			return keys.get(key_count - 1);
		} else {
			return keys.get(0);
		}
	}

	public T mid() {
		return keys.get(key_count / 2);
	}

	public int midIndex() {
		return key_count / 2;
	}

	public int keyIndex(T key) {
		return keys.indexOf(key);
	}

	public ArrayList<T> leftKeys(int index) {
		ArrayList<T> left = new ArrayList<T>();
		for(int i = 0; i < index; i++) {
			left.add(keys.get(i));
		}
		return left;
	}

	public ArrayList<T> rightKeys(int index) {
		ArrayList<T> right = new ArrayList<T>();
		for(int i = index + 1; i < key_count; i++) {
			right.add(0, keys.get(i));
		}
		return right;
	}

	public ArrayList<T> removeLeftKeys(int index) {
		ArrayList<T> left = new ArrayList<T>();
		int removed, remove_count;
		remove_count = keys.size() - index;
		removed = 0;
		while(removed != remove_count && keys.size() != 1) {
			left.add(keys.remove(0));
			key_count -= 1;
			removed++;
		}
		return left;
	}

	public ArrayList<T> removeRightKeys(int index) {
		ArrayList<T> right = new ArrayList<T>();
		int removed, remove_count;
		remove_count = keys.size() - index;
		removed = 0;
		while(removed != remove_count && keys.size() != 1) {
			right.add(0, keys.remove(keys.size() -1));
			key_count -= 1;
			removed++;
		}
		return right;
	}

	public boolean removeKey(T key) {
		boolean to_return = false;
		int index = keys.indexOf(key);
		if(index != -1) {
			to_return = true;
			removeKey(index);
		}
		return to_return;
	}

	public T removeKey(int index) {
		T to_return = keys.remove(index);
		key_count -= 1;
		Collections.sort(keys, cmp);
		return to_return;
	}

	public T removeMaxKey() {
		T to_return = keys.remove(key_count - 1);
		key_count -= 1;
		return to_return;
	}

	public T removeMinKey() {
		T to_return = keys.remove(0);
		key_count -= 1;
		return to_return;
	}

	public T removeMidKey() {
		T to_return = keys.remove(key_count / 2);
		key_count -= 1;
		return to_return;
	}

	@Override
	public void addChild(BTreeNode<T> child) {
		children.add(child);
		child_count += 1;
	}

	public void addChild(BTreeNode<T> child, int index) {
		children.add(index, child);
		child_count += 1;
	}

	public void addLeftChildren(ArrayList<BTreeNode<T>> left) {
		while(!left.isEmpty()) {
			children.add(0, left.remove(left.size() - 1));
		}
	}

	public void addRightChildren(ArrayList<BTreeNode<T>> right) {
		while(!right.isEmpty()) {
			children.add(right.remove(0));
		}
	}

	public boolean hasChild(BTreeNode<T> child) {
		return children.contains(child);
	}

	public boolean childHas(T key) {
		boolean to_return = false;
		int min_compare = cmp.compare(key, min());
		int max_compare = cmp.compare(key, max());
		if(min_compare > 0 || max_compare < 0) {
			to_return = false;
		}else if(min_compare == 0 || max_compare == 0) {
			to_return = true;
		} else {
			BTreeNode<T> current_child;
			for(int i = 0; i < child_count; i++) {
				current_child = children.get(i);
				if(current_child.contains(key)) {
					to_return = true;
					break;
				}
			}
		}
		return to_return;
	}

	public BTreeNode<T> getChildContaining(T key) {
		BTreeNode<T> child = null;
		BTreeNode<T> current_child;
		int min_compare = cmp.compare(key, min());
		int max_compare = cmp.compare(key, max());
		if(min_compare > 0 || max_compare < 0) {
			child = null;
		} else if(min_compare == 0) {
			child = minChild();
		} else if(max_compare == 0) {
			child = maxChild();
		} else {
			for(int i = 0; i < child_count; i++) {
				current_child = children.get(i);
				if(current_child.contains(key)) {
					child = current_child;
					break;
				}
			}
		}

		return child;
	}

	public int childIndex(BTreeNode<T> child) {
		int to_return = -1;
		for(int i = 0; i < child_count; i++) {
			if(children.get(i).equals(child)) {
				to_return = i;
				break;
			}
		}
		return to_return;
	}

	public BTreeNode<T> minChild() {
		return children.get(0);
	}

	public BTreeNode<T> maxChild() {
		return children.get(key_count);
	}

	public int maxChildIndex() {
		return children.size() - 1;
	}

	public BTreeNode<T> midChild() {
		return children.get(child_count / 2);
	}

	public int midChildIndex() {
		return child_count / 2;
	}

	public void sortChildren() {
		Collections.sort(children, this.ncmp);
	}
	
	public void sortKeys() {
		Collections.sort(keys, cmp);
	}
	
	public void sort() {
		sortKeys();
		sortChildren();
	}

	public ArrayList<BTreeNode<T>> getLeftChildren(int index) {
		ArrayList<BTreeNode<T>> left_children = new ArrayList<BTreeNode<T>>();
		for(int i = 0; i < index; i++) {
			left_children.add(children.get(i));
		}
		return left_children;
	}

	public ArrayList<BTreeNode<T>> getRightChildren(int index) {
		ArrayList<BTreeNode<T>> right_children = new ArrayList<BTreeNode<T>>();
		for(int i = index + 1; i < child_count; i++) {
			right_children.add(children.get(i));
		}
		return right_children;
	}

	public ArrayList<BTreeNode<T>> removeLeftChildren(int index) {
		ArrayList<BTreeNode<T>> left_children = new ArrayList<BTreeNode<T>>();
		int removed, remove_count;
		removed = 0;
		remove_count = children.size() - index;
		while(removed != remove_count && children.size() > 0) {
			left_children.add(children.get(0));
			removed += 1;
			child_count -= 1;
		}
		return left_children;
	}

	public ArrayList<BTreeNode<T>> removeRightChildren(int index) {
		ArrayList<BTreeNode<T>> right_children = new ArrayList<BTreeNode<T>>();
		int removed, remove_count;
		remove_count = children.size() - index;
		removed = 0;
		while(removed != remove_count && children.size() > 0) {
			right_children.add(children.remove(index));
			remove_count += 1;
			child_count -= 1;
		}
		return right_children;

	}

	public BTreeNode<T> removeChild(int index) throws ChildIndexOutOfBoundsException {
		if(index < 0 || index >= child_count) {
			throw new ChildIndexOutOfBoundsException(index, "removeChild - index");
		}
		BTreeNode<T> to_return = children.remove(index);
		child_count -= 1;
		return to_return;
	}

	public boolean removeChild(BTreeNode<T> child) throws ChildIndexOutOfBoundsException {
		boolean to_return = false;
		int child_index = childIndex(child);
		if(child_index < 0) {
			throw new ChildIndexOutOfBoundsException(child_index, "removeChild - No index");
		} else {
			BTreeNode<T> removed_child = removeChild(child_index);
			if(removed_child != null) {
				to_return = true;
			}
		}
		return to_return;
	}

	public BTreeNode<T> removeMaxChild() {
		BTreeNode<T> to_return = children.remove(child_count - 1);
		child_count -= 1;
		return to_return;
	}

	public BTreeNode<T> removeMinChild() {
		BTreeNode<T> to_return = children.remove(0);
		child_count -= 1;
		return to_return;
	}

	public BTreeNode<T> childAt(int index) throws ChildIndexOutOfBoundsException {
		if(index < 0 || index >= child_count) {
			throw new ChildIndexOutOfBoundsException(index, "childAt");
		}
		return children.get(index);
	}

	public int closestChildIndex(T key) {
		int to_return = -1;
		int min_cmp = cmp.compare(keys.get(0), key);
		if(min_cmp > 0) {
			to_return = 0;
		} else {
			int max_cmp = cmp.compare(keys.get(key_count - 1), key);
			if(max_cmp < 0) {
				to_return = key_count;
			} else {
				T current_key = null;
				int current_cmp = 0;
				for(int i = 1; i < key_count - 1; i++) {
					current_key = keys.get(i);
					current_cmp = cmp.compare(current_key, key);
					if(current_cmp < 0) {
						continue;
					} else {
						to_return = i;
						break;
					}
				}
			}
		}
		return to_return;
	}

	public BTreeNode<T> closestChild(T key) throws ChildIndexOutOfBoundsException {
		BTreeNode<T> to_return = null;
		int closest_index = closestChildIndex(key);
		to_return = childAt(closest_index);
		return to_return;
	}

	public void split(int index) throws ChildIndexOutOfBoundsException {
		BTreeNode<T> child, left, right;
		ArrayList<T> left_keys, right_keys;
		ArrayList<BTreeNode<T>> left_children, right_children;
		T mid_key;
		int mid_key_index, mid_child_index;

		//Get the child
		child = childAt(index);
		//Get the middle key and its index
		mid_key = child.mid();
		mid_key_index = child.keyIndex(mid_key);

		//Create the new left and right children.
		left = newNode();
		right = newNode();

		//Get the left and right keys. 
		//Need to reset the middle index because of key removal.
		left_keys = child.removeLeftKeys(mid_key_index);
		mid_key_index = child.keyIndex(mid_key);
		right_keys = child.removeRightKeys(mid_key_index);

		//Add the left keys to left and the right keys to right
		left.addKey(left_keys);
		right.addKey(right_keys);

		//Check for children and if found
		//Add them to the new left and right.
		if(!child.isLeaf()) {
			mid_child_index = child.midChildIndex();
			left_children = child.removeLeftChildren(mid_child_index);
			right_children = child.removeRightChildren(0);
			left.addLeftChildren(left_children);
			right.addRightChildren(right_children);
		}

		child.addChild(left);
		child.addChild(right);
	}

	public void split() {
		int mid_index;
		T mid_key;
		BTreeNode<T> left, right;
		ArrayList<T> left_keys, right_keys;
		ArrayList<BTreeNode<T>> left_children, right_children;

		left = newNode();
		right = newNode();
		mid_key = this.mid();
		mid_index = this.midIndex();
		left_keys = this.removeLeftKeys(mid_index);
		mid_index = this.keyIndex(mid_key);
		right_keys = this.removeRightKeys(mid_index);
		left.addKey(left_keys);
		right.addKey(right_keys);

		if(!isLeaf()) {
			left_children = this.removeLeftChildren(this.midChildIndex());
			right_children = this.removeRightChildren(0);
			left.addLeftChildren(left_children);
			right.addRightChildren(right_children);
		}
		this.addChild(left);
		this.addChild(right);
	}

	public void steal(BTreeNode<T> toSteal, BTreeNode<T> toFill, int steal_index, int fill_index) {
		T stolen_key, parent_key;
		BTreeNode<T> stolen_link;

		if(steal_index > fill_index) {
			stolen_key = toSteal.removeMinKey();
			parent_key = this.removeKey(fill_index);
			this.addKey(fill_index, stolen_key);
			toFill.addKey(parent_key);
			if(!toSteal.isLeaf()) {
				stolen_link = toSteal.removeMinChild();
				toFill.addChild(stolen_link);
			}
		} else {
			stolen_key = toSteal.removeMaxKey();
			parent_key = this.removeKey(steal_index);
			this.addKey(steal_index, stolen_key);
			toFill.addKey(0, parent_key);

			if(!toSteal.isLeaf()) {
				stolen_link = toSteal.removeMaxChild();
				toFill.addChild(stolen_link, 0);
			}
		}

	}

	public void merge(BTreeNode<T> toRemove, BTreeNode<T> toFill, int remove_index, int fill_index) throws ChildIndexOutOfBoundsException {		
		T parent_key;

		if(remove_index > fill_index) {
			parent_key = this.removeKey(fill_index);
			toFill.addKey(parent_key);
			while(!toRemove.isEmpty()) {
				toFill.addKey(toRemove.removeMinKey());
			}

			while(!toRemove.isLeaf()) {
				toFill.addChild(toRemove.removeMinChild());
			}
		} else {
			parent_key = this.removeKey(remove_index);
			toFill.addKey(parent_key);
			while(!toRemove.isEmpty()) {
				toFill.addKey(toRemove.removeMaxKey());
			}

			while(!toRemove.isLeaf()) {
				toFill.addChild(toRemove.removeMaxChild(), 0);
			}
		}
		this.removeChild(remove_index);
	}

	public boolean repair(int index) throws ChildIndexOutOfBoundsException {
		boolean to_return = false;
		if(index > -1 && index < child_count) {
			BTreeNode<T> child = children.get(index);
			to_return = repair(child, index);
		} else {
			throw new ChildIndexOutOfBoundsException(index, "repair");
		}
		return to_return;
	}

	public boolean repair(BTreeNode<T> child) throws NullChildException, ChildIndexOutOfBoundsException {
		boolean to_return = false;
		if(child != null) {
			int index = children.indexOf(child);
			to_return = repair(child, index);
		} else {
			throw new NullChildException("Trying to repair null child.");
		}
		return to_return;
	}

	private boolean repair(BTreeNode<T> child, int child_index) throws ChildIndexOutOfBoundsException {
		BTreeNode<T> right, left;
		int right_fill, left_fill, right_index, left_index;
		boolean has_right = hasRight(child_index);
		boolean has_left = hasLeft(child_index);
		boolean repaired = false;
		if(has_right) {
			right = getRight(child_index);
			right_index = getRightIndex(child_index);
			right_fill = right.fillStatus();
			if(right_fill > 0) {
				steal(right, child, child_index, right_index);
				repaired = true;
			} else if(right_fill == 0) {
				merge(child, right, child_index, right_index);
				repaired = true;
			}
		} else if(has_left && !repaired) {
			left = getLeft(child_index);
			left_index = getLeftIndex(child_index);
			left_fill = left.fillStatus();
			if(left_fill > 0) {
				steal(left, child, child_index, left_index);
				repaired = true;
			} else if(left_fill == 0) {
				merge(child, left, child_index, left_index);
				repaired = true;
			}
		}
		return repaired;
	}

	public T predecessor(T key) throws ChildIndexOutOfBoundsException, NullChildException {
		T to_return = null;
		if(!this.isLeaf()) {
			int starting_index = closestChildIndex(key);
			to_return = predecessor(this, key, starting_index);
		}
		return to_return;
	}
	private T predecessor(BTreeNode<T> node, T key, int index) throws ChildIndexOutOfBoundsException, NullChildException {
		T to_return = null;
		BTreeNode<T> child = node.childAt(index);
		if(child == null) {
			throw new NullChildException("predecessor", key.toString());
		}

		if(child.isLeaf()) {
			if(!child.isEmpty()) {
				to_return = child.removeKey(child.keyCount() - 1);
				int key_index = node.keyIndex(key);
				node.setKey(key_index, to_return);
			}
		} else {
			int max_child = child.maxChildIndex();
			to_return = child.predecessor(node, key, max_child);
		}
		return to_return;
	}

	public BTreeNode<T> newNode() {
		return new BTreeNode<T>(cmp, ncmp, min_keys, max_keys);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		boolean to_return = false;
		int eq_cmp = ncmp.compare(this, (BTreeNode<T>) obj);
		if(eq_cmp == 0) {
			to_return = true;
		}
		return to_return;
	}

	public String toString() {
		return keys.toString();
	}
}
