package MergeSort;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Implementation of the merge sort algorithm using the top-down approach.
 * Uses generics and if no comparator is provided attempts to use the objects
 * compareTo method.
 * @author Tim Fleece
 * @param <T> the array or ArrayList to be sorted.
 */
@SuppressWarnings("unused")
public class MergeSort<T extends Comparable<? super T>> {
	Comparator<T> cmp;
	
	public MergeSort() {
		cmp = new NaturalComparator<T>();
	}
	
	public MergeSort(Comparator<T> cmp) {
		this.cmp = cmp;
	}
	
	public T[] sort(T[] arr) {
		return null;
	}
	
	private T[] merge(T[] arr, int start, int end, T[] buffer) {
		return null;
	}
	
	
	private ArrayList<T> merge(ArrayList<T> arr, int start, int end, ArrayList<T> buffer) {
		return null;
	}
	
	public ArrayList<T> sort(ArrayList<T> arr) {
		return null;
	}
	
	
}

class NaturalComparator<T extends Comparable<? super T>> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		return o1.compareTo(o2);
	}
	
}