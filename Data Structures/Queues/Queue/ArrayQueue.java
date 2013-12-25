package Queue;

/**
 * A generic implementation of a queue using an array.
 * Not strongly typed. Could make it faster/more optimized. 
 * Goal was to code quickly.
 * @author Tim Fleece
 * @param <T>
 */
public class ArrayQueue<T> {
	private Object[] queue;
	private int size;
	private int capacity;
	private int grow_by;
	
	public ArrayQueue() {
		queue = new Object[10];
		size = 0;
		capacity = 10;
		grow_by = 3;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void enqueue(T obj) {
		if(size == 0) {
			size += 1;
			queue[0] = obj;
		} else {
			shiftRight();
			queue[0] = obj;
			size += 1;
		}
	}
	
	@SuppressWarnings("unchecked")
	public T dequeue() {
		T to_return = (T) queue[size - 1];
		queue[size - 1] = null;
		size -= 1;
		return to_return;
	}
	
	private void grow() {
		Object[] new_queue = new Object[capacity + grow_by];
		for(int i = 0; i < size; i++) {
			new_queue[i] = queue[i];
		}
		capacity += grow_by;
		queue = new_queue;
	}
	
	private void shiftRight() {
		if(size + 1 == queue.length) {
			grow();
		}
		
		for(int i = 0; i < size; i++) {
			queue[i + 1] = queue[i];
		}
	}	
}