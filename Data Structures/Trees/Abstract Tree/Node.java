package AbstractTree;
//Import Statements
//Local Imports
import TreeComparators.NodeComparator;
import TreeExceptions.ChildIndexOutOfBoundsException;
//External Imports
import java.util.ArrayList;
import java.util.Comparator;



/**
 * Abstract class defining a generic tree node class.
 * Not necessary for any of the trees to work. Allows for
 * other classes to use the trees easily. Just remove the implements
 * code from the class and change all NodeInterface references to the 
 * name of the node class.
 * @author Tim Fleece
 * @param <T>
 */
public abstract class Node<T extends Comparable<? super T>, E extends Node<?,?>> {
	protected Comparator<T> cmp;
	protected NodeComparator<T, E> ncmp;
	protected ArrayList<T> keys;
	protected ArrayList<E> children;
	protected int level;
	protected int key_count;
	protected int child_count;
	protected int min_keys;
	protected int max_keys;
	
	
	public Node(Comparator<T> cmp, NodeComparator<T, E> ncmp, int min_keys, int max_keys) {
		this.cmp = cmp;
		this.ncmp = ncmp;
		this.min_keys = min_keys;
		this.max_keys = max_keys;
		this.level = -1;
		this.key_count = 0;
		this.child_count = 0;
		keys = new ArrayList<T>();
		children = new ArrayList<E>();
	}
	
	/**
	 * Return a new node instance.
	 * @return
	 */
	public abstract E newNode();
	
	/**
	 * Check if this node is a leaf.
	 * @return true if the node has no children. False otherwise.
	 */
	public boolean isLeaf() {
		return child_count == 0;
	}
	
	/**
	 * Check if this node is empty / doesn't have any keys.
	 * @return true if the node is empty. False otherwise.
	 */
	public boolean isEmpty() {
		return key_count == 0;
	}
	
	/**
	 * Check if the given key is in this node.
	 * @param key
	 * @return
	 */
	public abstract boolean contains(T key);
	/**
	 * Set the level of the node. The level should be the node's distance
	 * from the root.
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Get the level of the node.
	 * @return the distance of the node from the root. A level of 0 means that the
	 * node is the root.
	 */
	public int level() {
		return level;
	}
	
	/**
	 * Return the amount of keys in a node.
	 * @return
	 */
	public int keyCount() {
		return key_count;
	}
	
	/**
	 * Return the amount of children this node has.
	 * @return
	 */
	public int childCount() {
		return child_count;
	}
	
	/**
	 * Add a key to this node.
	 * @param key
	 */
	public abstract void addKey(T key);
	
	/**
	 * Add a key at the specified index to the tree.
	 * @param index
	 * @param key
	 */
	public abstract void addKey(int index, T key);
	
	/**
	 * Get the minimum key in this node.
	 * @return
	 */
	public abstract T min();
	
	/**
	 * Get the maximum key in this node.
	 * @return
	 */
	public abstract T max();
	
	/**
	 * Get the middle key in this node.
	 * @return
	 */
	public abstract T mid();
	
	/**
	 * Remove the given key from this node.
	 * @param key
	 * @return
	 */
	public abstract boolean removeKey(T key);
	
	/**
	 * Add a child to this node.
	 * @param child
	 */
	public abstract void addChild(E child);
	
	/**
	 * Add a child at the specified index to this node.
	 * @param index
	 * @param child
	 */
	public abstract void addChild(E child, int index);
	
	/**
	 * Remove the given child from this node.
	 * @param child
	 * @throws ChildIndexOutOfBoundsException 
	 */
	public abstract boolean removeChild(E child) throws ChildIndexOutOfBoundsException;
	
	/**
	 * Remove and return the child at the given index from this node.
	 * @param index
	 * @return
	 * @throws ChildIndexOutOfBoundsException 
	 */
	public abstract E removeChild(int index) throws ChildIndexOutOfBoundsException;
	
	/**
	 * Return the lowest valued child of this node.
	 * @return
	 */
	public abstract E minChild();
	
	/**
	 * Return the highest valued child of this node.
	 * @return
	 */
	public abstract E maxChild();
	
	/**
	 * Get the child that is closest in value to the given key.
	 * @param key
	 * @return
	 * @throws ChildIndexOutOfBoundsException
	 */
	public abstract E closestChild(T key) throws ChildIndexOutOfBoundsException;
}