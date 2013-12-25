package QuickSort;

import java.util.ArrayList;
import java.util.Comparator;

public class QuickSort<E extends Comparable<E>> {
	Comparator<E> cmp;
	
	public QuickSort() {
		cmp = new NaturalComparator<E>();
	}
	
	public QuickSort(Comparator<E> cmp) {
		this.cmp = cmp;
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