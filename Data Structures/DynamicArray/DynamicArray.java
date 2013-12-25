package DynamicArray;

import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T>, Cloneable {
	private final String NEW_LINE = System.getProperty("line.separator");
	private Object[] arr;
	private int size;
	private int grow_by;
	private int capacity;
	private int min_capacity;
	public DynamicArray() {
		arr = new Object[1];
		size = 0;
		grow_by = 1;
		capacity = 1;
		min_capacity = 1;
	}
	
	public DynamicArray(int initialCapacity) {
		arr = new Object[initialCapacity];
		size = 0;
		grow_by = 1;
		capacity = 1;
		min_capacity = initialCapacity;
	}
	
	public void setGrowthFactor(int grow_by) {
		this.grow_by = grow_by;
	}
	
	public void ensureCapacity(int capacity) {
		this.min_capacity = capacity;
		checkCapacity();
	}
	
	public int size() {
		return size;
	}
	
	public int capacity() {
		return capacity;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int indexOf(T obj) {
		int to_return = -1;
		for(int i = 0; i < size; i++) {
			if(obj.equals(arr[i])) {
				to_return = i;
				break;
			}
		}
		return to_return;
	}
	
	public int lastIndexOf(T obj) {
		int to_return = -1;
		for(int i = size - 1; i >= 0; i--) {
			if(obj.equals(arr[i])) {
				to_return  = i;
				break;
			}
		}
		return to_return;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		boolean to_return = true;
		DynamicArray<T> darr = (DynamicArray<T>) obj;
		if(darr.size() == size) {
			for(int i = 0; i < size; i++) {
				if(!arr[i].equals(darr.at(i))) {
					to_return = false;
					break;
				} 
			}
		} else {
			to_return = false;
		}
		return to_return;
	}
	
	@SuppressWarnings("unchecked")
	public Object clone() {
		DynamicArray<T> new_arr = new DynamicArray<T>();
		for(int i = 0; i < size; i++) {
			new_arr.add((T) arr[i]);
		}
		new_arr.ensureCapacity(min_capacity);
		return new_arr;
	}
	
	@SuppressWarnings("unchecked")
	public T at(int index) {
		rangeCheck(index);
		return (T) arr[index];
	}
	
	public T get(int index) {
		return at(index);
	}
	
	public void add(T obj) {
		if(size < capacity) {
			arr[size] = obj;
			size += 1;
		} else {
			grow();
			arr[size] = obj;
			size += 1;
		}
	}
	
	public void add(int index, T obj) {
		rangeCheck(index);
		shiftRight(index);
		arr[index] = obj;
		size += 1;
		checkCapacity();
	}
	
	public T remove(T obj) {
		int index = indexOf(obj);
		return remove(index);
	}
		
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		rangeCheck(index);
		T to_return = (T) arr[index];
		shiftLeft(index);
		size -= 1;
		checkCapacity();
		return to_return;
	}
	
	public void set(int index, T obj) {
		rangeCheck(index);
		arr[index] = obj;
	}
	
	public Object[] copy(int start, int end, Object[] obj) {
		rangeCheck(start, end);
		rangeCheck(obj);
		for(int i = start; i < end; i++) {
			obj[i] = arr[i];
		}
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public T[] copy(T[] obj, int start, int end) {
		rangeCheck(start, end);
		rangeCheck(obj);
		for(int i = start; i < end; i++) {
			obj[i] = (T) arr[i];
		}
		return obj;
	}
	
	public void clear() {
		arr = new Object[min_capacity];
		size = 0;
		capacity = min_capacity;
	}
	
	private void grow() {
		int new_capacity = capacity + grow_by;
		Object[] new_arr = new Object[new_capacity];
		arr = copy(0, size, new_arr);
		this.capacity = new_capacity;
	}
	
	private void grow(int amt) {
		int new_capacity = capacity + amt;
		Object[] new_arr = new Object[new_capacity];
		arr = copy(0, size, new_arr);
		this.capacity = new_capacity;
	}
	
	private void checkCapacity() {
		if(capacity < min_capacity) {
			grow(min_capacity - capacity);
		} else if(capacity > size + grow_by) {
			shrink();
		}
	}
		
	private void shrink() {
		int new_capacity = size;
		Object[] new_arr = new Object[new_capacity];
		
		for(int i = 0; i < new_capacity; i++) {
			new_arr[i] = arr[i];
		}
		this.capacity = new_capacity;
		this.arr = new_arr;
	}
	
	private void shiftRight(int index) {
		if(capacity < size + 1) {
			grow(1);
		}
		
		for(int i = index; i < size; i++) {
			arr[i + 1] = arr[i];
		}
	}
	
	private void shiftLeft(int index) {
		rangeCheck(index - 1);
		for(int i = index; i < size; i++) {
			arr[i] = arr[i + 1];
		}
	}
	
	private void rangeCheck(int index) {
		String message = new String();
		boolean throw_error = false;
		if(index < 0) {
			message = "Index out of bounds (Less than 0) : " + Integer.toString(index);
			message += NEW_LINE;
			throw_error = true;
		}
		
		if(index >= capacity) {
			message += "Index out of buonds (Greater than capacity) : " + Integer.toString(index);
			message += NEW_LINE;
			throw_error = true;
		}
		
		if(throw_error) {
			throw new IndexOutOfBoundsException(message);
		}
	}
	
	private void rangeCheck(int start, int end) {
		String message = new String();
		boolean throw_error = false;
		if(start < 0) {
			message = "Lower index out of bounds (Less than 0): " + Integer.toString(start);
			message += NEW_LINE;
			throw_error = true;
		}
		
		if(start >= capacity) {
			message += "Lower index out of bounds (Greater than Capacity): " + Integer.toString(start);
			message += NEW_LINE;
			throw_error = true;
		}
		
		if(end <= 0) {
			message += "Upper index out of bounds (Less than 0): " + Integer.toString(end);
			message += NEW_LINE;
			throw_error = true;
		}
		
		if(end >= capacity) {
			message += "Upper index out of bounds (Greater than Capacity: " + Integer.toString(end);
			message += NEW_LINE;
			throw_error = true;
		}
		
		if(throw_error) {
			throw new IndexOutOfBoundsException(message);
		}
	}
	
	private void rangeCheck(Object[] obj) {
		if(obj.length < size) {
			throw new IndexOutOfBoundsException("Given array is smaller than current array.");
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new DynamicArrayIterator<T>(this);
	}
}

class DynamicArrayIterator<T> implements Iterator<T> {
	DynamicArray<T> darr;
	int index;
	
	public DynamicArrayIterator(DynamicArray<T> darr) {
		this.darr = darr;
		index = 0;
	}

	@Override
	public boolean hasNext() {
		return index < darr.size();
	}

	@Override
	public T next() {
		return darr.at(index++);
	}

	@Override
	public void remove() {
		darr.remove(index);
	}
}