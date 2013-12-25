package Stack;

//Import Statements
//External Imports
import java.util.ArrayList;

/**
 * Simple implementation of a generic stack.
 * @author Tim Fleece
 * @param <T>
 */
public class Stack<T> {
	ArrayList<T> stack;
	
	public Stack() {
		stack = new ArrayList<T>();
	}
	
	public void push(T obj) {
		stack.add(0,obj);
	}
	
	public T pop() {
		return stack.remove(0);
	}
	
	public T peek() {
		return stack.get(0);
	}
}