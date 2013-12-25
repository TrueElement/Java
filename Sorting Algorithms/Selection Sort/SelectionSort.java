package SelectionSort;

import java.util.ArrayList;
import java.util.Comparator;

public class SelectionSort<E extends Comparable<E>> {
	Comparator<E> cmp;
	
	public SelectionSort() {
		cmp = new NaturalComparator<E>();
	}
	
	public E[] sort(E[] arr) {
		return null;
	}
	
	public ArrayList<E> sort(ArrayList<E> arr) {
		return null;
	}
	
}

class NaturalComparator<E extends Comparable<E>> implements Comparator<E> {

	@Override
	public int compare(E o1, E o2) {
		return o1.compareTo(o2);
	}
	
}