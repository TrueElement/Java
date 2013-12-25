package BubbleSort;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A class that implements the bubble sort algorithm.
 * Uses generics and if no comparator is provided, attempts 
 * to use the objects compareTo method. 
 * @author Tim Fleece
 * @param <E> the array or ArrayList to be sorted.
 */
public class BubbleSort<T extends Comparable<? super T>> {
	Comparator<T> cmp;
	
	public BubbleSort() {
		cmp = new NaturalComparator<T>();
	}
	
	public BubbleSort(Comparator<T> cmp) {
		this.cmp = cmp;
	}
	
	public T[] sort(T[] arr) {
		boolean swapped = false;
		int size = arr.length;
		do {
			swapped = false;
			for(int i = 0; i < size; i++) {
				if(cmp.compare(arr[i], arr[i + 1]) < 0) {
					swap(arr, i, i + 1);
					swapped = true;
				} else {
					continue;
				}
			}
			size -= 1;
		} while(swapped == true);
		return arr;
	}
	
	public ArrayList<T> sort(ArrayList<T> arr) {
		boolean swapped = false;
		int size = arr.size();
		do {
			swapped = false;
			for(int i = 0; i < size; i++) {
				if(cmp.compare(arr.get(i), arr.get(i + 1)) < 0) {
					arr = swap(arr, i, i + 1);
					swapped = true;
				} else {
					continue;
				}
			}
			size -= 1;
		} while(swapped == true);
		return arr;
	}
	
	private ArrayList<T> swap(ArrayList<T> arr, int lhs, int rhs) {
		T lhs_obj = arr.get(lhs);
		arr.set(lhs, arr.get(rhs));
		arr.set(rhs, lhs_obj);
		return arr;
	}
	
	private T[] swap(T[] arr, int lhs, int rhs) {
		T lhs_obj = arr[lhs];
		arr[lhs] = arr[rhs];
		arr[rhs] = lhs_obj;
		return arr;
		
	}
}

class NaturalComparator<T extends Comparable<? super T>> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		return o1.compareTo(o2);
	}


	
}

