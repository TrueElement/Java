package Queue;

import java.util.ArrayList;

/**
 * Simple implementation of a generic Queue using an
 * ArrayList.
 * @author Tim Fleece
 *@param <T>
 */
public class Queue<T> {
	ArrayList<T> queue;
	
	public Queue() {
		queue = new ArrayList<T>();
	}
	
	public void enqueue(T obj) {
		queue.add(obj);
	}
	
	public T dequeue() {
		return queue.remove(0);
	}
}