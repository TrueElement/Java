package Stack;

public class ArrayStack<T> {
	private Object[] stack;
	private int size;
	private int capacity;
	private int grow_by;
	
	public ArrayStack() {
		stack = new Object[10];
		size = 0;
		capacity = 10;
		grow_by = 3;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void push(T obj) {
		if(size + 1 > stack.length) {
			grow();
		}
		stack[size] = obj;
		size += 1;
	}
	
	@SuppressWarnings("unchecked")
	public T pop() {
		if(size <= 0) {
			throw new IndexOutOfBoundsException("Stack is empty.");
		}
		T to_return = (T) stack[size - 1];
		stack[size - 1] = null;
		size -= 1;
		return to_return;
	}
	
	@SuppressWarnings("unchecked")
	public T peek() {
		if(size <= 0) {
			throw new IndexOutOfBoundsException("Stack is empty.");
		}
		T to_return = (T) stack[size - 1];
		return to_return;
	}
	
	private void grow() {
		capacity += grow_by;
		Object[] new_stack = new Object[capacity];
		for(int i = 0; i < size; i++) {
			new_stack[i] = stack[i];
		}
		stack = new_stack;
	}
}