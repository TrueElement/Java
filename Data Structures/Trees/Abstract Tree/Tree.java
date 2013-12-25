package AbstractTree;
//Import Statements
//Local Imports
import TreeComparators.NaturalComparator;
import TreeComparators.NodeComparator;
import TreeExceptions.ChildIndexOutOfBoundsException;
import TreeExceptions.NullChildException;
//External Imports
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;



public abstract class Tree<T extends Comparable<? super T>, NodeType extends Node<?,?>> {
	protected Comparator<T> cmp;
	protected NodeComparator<T, NodeType> ncmp;
	protected int size;
	protected int element_count;
	protected int order;
	protected int min_keys;
	protected int max_keys;
	protected boolean ignore_exceptions;
	protected NodeType root;
	
	public Tree(int order) {
		this.order = order;
		this.element_count = 0;
		this.size = 1;
		this.min_keys = (order / 2) - 1;
		this.max_keys = (order - 1);
		cmp = new NaturalComparator<T>();
	}
	
	public Tree(int order, Comparator<T> cmp) {
		this.order = order;
		this.cmp = cmp;
		this.size = 1;
		this.min_keys = (order / 2) - 1;
		this.max_keys = (order - 1);
		this.cmp = cmp;
	}
	
	/**
	 * Prevent tree from throwing exceptions. Errors will be printed to console.
	 * @param ignore_exceptions
	 */
	public void ignoreExceptions(boolean ignore_exceptions) {
		this.ignore_exceptions = ignore_exceptions;
	}
	
	/**
	 * Create and return a new node.
	 * @return
	 */
	public abstract NodeType newNode();
	
	/**
	 * Get the number of nodes in this tree.
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Get the number of elements in this tree.
	 * @return
	 */
	public int elements() {
		return element_count;
	}
	
	/**
	 * Get the order of the tree.
	 * @return
	 */
	public int order() {
		return order;
	}
	
	/**
	 * Check if an element is in the tree.
	 * @param key
	 * @return
	 */
	public abstract boolean contains(T key);
	
	/**
	 * Add an element to the tree.
	 * @param element
	 * @throws ChildIndexOutOfBoundsException 
	 */
	public abstract void add(T key) throws ChildIndexOutOfBoundsException;
	
	/**
	 * Remove an element from the tree.
	 * @param element
	 * @return
	 * @throws NullChildException 
	 * @throws ChildIndexOutOfBoundsException 
	 */
	public abstract boolean remove(T key) throws ChildIndexOutOfBoundsException, NullChildException;
	
	/**
	 * Remove an element starting at node gp from the tree.
	 * @param gp
	 * @param key
	 * @return
	 * @throws NullChildException 
	 * @throws ChildIndexOutOfBoundsException 
	 */
	public abstract boolean remove(NodeType gp, T key) throws ChildIndexOutOfBoundsException, NullChildException;
	
	/**
	 * Get an array of the nodes in this tree in order.
	 * @return
	 */
	public abstract ArrayList<NodeType> inOrderArray();
	
	/**
	 * Get an array of the nodes in this tree stopping when
	 * max_level is reached.
	 * @param max_level
	 * @return
	 */
	public abstract ArrayList<NodeType> inOrderArray(int max_level);
	
	/**
	 * Get an array of the nodes in this tree in pre-order.
	 * @return
	 */
	public abstract ArrayList<NodeType> preOrderArray();
	
	/**
	 * Get an array of the nodes in this tree in post order.
	 * @return
	 */
	public abstract ArrayList<NodeType> postOrderArray();
	
	/**
	 * Get an iterator that will iterate over the tree elements
	 * in order.
	 * @return
	 */
	public abstract Iterator<T> inOrderIterator();
	
	/**
	 * Get an iterator that will iterate over the tree elements
	 * in pre-order.
	 * @return
	 */
	public abstract Iterator<T> preOrderIterator();
	
	/**
	 * Get an iterator that will iterate over the tree elements
	 * in post order.
	 * @return
	 */
	public abstract Iterator<T> postOrderIterator();
	
	
}


