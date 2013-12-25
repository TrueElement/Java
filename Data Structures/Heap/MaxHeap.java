package Heap;
//Import Statements
//External Imports
import java.util.Comparator;
import java.util.ArrayList;

/**
 * A generic Max-Heap using an ArrayList for storage.
 * @author Tim Fleece
 * @param <T>
 */
public class MaxHeap<T extends Comparable<? super T>> {
	Comparator<T> cmp;
	ArrayList<T> heap;
	
	public MaxHeap() {
		this.cmp = new NaturalComparator<T>();
		this.heap = new ArrayList<T>();
	}
	
	public MaxHeap(Comparator<T> cmp) {
		this.cmp = cmp;
		this.heap = new ArrayList<T>();
	}
	
	public void add(T obj) {
		
	}
	
	public T top() {
		T to_return = null;
		return to_return;
	}
	
	public T peek() {
		return heap.get(0);
	}
	
	public int size() {
		return heap.size();
	}
}

