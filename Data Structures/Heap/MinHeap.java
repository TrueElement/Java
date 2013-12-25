package Heap;
//Import Statements
//External Imports
import java.util.ArrayList;
import java.util.Comparator;

public class MinHeap<T extends Comparable<? super T>> {
	Comparator<T> cmp;
	ArrayList<T> heap;
	
	public MinHeap() {
		this.cmp = new NaturalComparator<T>();
		this.heap = new ArrayList<T>();
	}
	
	public MinHeap(Comparator<T> cmp) {
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

