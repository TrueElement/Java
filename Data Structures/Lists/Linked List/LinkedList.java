package LinkedList;
import java.util.Comparator;
import java.util.Iterator;

public class LinkedList<E extends Comparable<E>> implements Iterable<E> {
	int size;
	ListNode<E> root;
	ListNode<E> last;
	Comparator<E> cmp;
	public LinkedList() {
		cmp = new NaturalComparator<E>();
		root = new ListNode<E>(cmp);
		last = root;
		size = 0;
	}
	
	public LinkedList(Comparator<E> cmp) {
		this.cmp = cmp;
		root = new ListNode<E>(cmp);
		last = root;
		size = 0;
	}
	
	public void add(E element) {
		last = last.add(element);
		size += 1;
	}
	
	public boolean remove(E element) {
		ListNode<E> previous = null, 
				    current = root;
		boolean remove_status = false;
		while(true) {
			if(cmp.compare(element, current.get()) == 0) {
				if(previous != null) {
					previous.setNext(current.getNext());
					remove_status = true;
					size -= 1;
					break;
				} else {
					remove_status = true;
					current.set(null);
					size -= 1;
				}
			} else {
				previous = current;
				current = current.getNext();
				if(current == null) {
					break;
				}
			}
		}
		return remove_status;
	}
	
	public boolean remove() {
		ListNode<E> last_neighbor = root;
		boolean return_status = false;
		if(root == null) {
			return return_status;
		}
		if(last.equals(root)) {
			root = null;
			last = null;
			size -= 1;
		} else {
			while(true) {
				if(last_neighbor.getNext().equals(last)) {
					last = last_neighbor;
					break;
				} else {
					last_neighbor = last_neighbor.getNext();
				}
			}
		}
		return return_status;
	}
	
	public int size() {
		return size;
	}
		
	@Override
	@SuppressWarnings("unchecked")
	public Iterator<E> iterator() {
		return (Iterator<E>) new LinkedListIterator<E>(root, this);
	}
}

class ListNode<E extends Comparable<E>> {
	E element;
	ListNode<E> next;
	Comparator<E> cmp;
	public ListNode() {
		element = null;
		next = null;
	}
	
	public ListNode(Comparator<E> cmp) {
		element = null;
		next = null;
	}
	
	public ListNode(E element) {
		this.element = element;
		this.next = null;
	}
	
	public ListNode(E element, Comparator<E> cmp) {
		this.cmp = cmp;
		this.element = element;
	}
	
	public ListNode(E element, ListNode<E> next) {
		this.element = element;
		this.next = next;
	}
	
	public void set(E element) {
		this.element = element;
	}
	
	public ListNode<E> add(E element) {
		this.element = element;
		next = new ListNode<E>(cmp);
		return next;
	}
	
	public E get() {
		return element;
	}
	
	public void setNext(ListNode<E> next) {
		this.next = next;
	}
	
	public ListNode<E> getNext() {
		return next;
	}
	
	public boolean hasNext() {
		return next != null;
	}
	
	public boolean equals(ListNode<E> node) {
		return cmp.compare(element, node.get()) == 0;
	}
}

class LinkedListIterator<E extends Comparable<E>> implements Iterator<ListNode<E>> {
	ListNode<E> current;
	LinkedList<E> list;
	public LinkedListIterator() {
		current = null;
	}
	
	public LinkedListIterator(ListNode<E> current, LinkedList<E> list) {
		this.current = current;
	}
	
	public void setStart(ListNode<E> node) {
		this.current = node;
	}
	
	@Override
	public boolean hasNext() {
		return current.hasNext();
	}

	@Override
	public ListNode<E> next() {
		ListNode<E> next = current;
		current = current.getNext();
		return next;
	}

	@Override
	public void remove() {
		list.remove(current.get());
	}
}

class NaturalComparator<E extends Comparable<E>> implements Comparator<E> {

	@Override
	public int compare(E o1, E o2) {
		return o1.compareTo(o2);
	}
	
}

