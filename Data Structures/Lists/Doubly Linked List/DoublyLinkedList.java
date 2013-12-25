package DoublyLinkedList;

import java.util.Comparator;
import java.util.Iterator;

public class DoublyLinkedList<E extends Comparable<E>> implements Iterable<E> {
	Comparator<E> cmp;
	ListNode<E> root;
	ListNode<E> last;
	public DoublyLinkedList() {
		root = new ListNode<E>(cmp);
		last = root;
	}
	
	public void add(E element) {
		last = last.add(element);
	}
	
	public boolean remove(E element) {
		return true;
	}
	
	public boolean remove() {
		return true;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Iterator<E> iterator() {
		return (Iterator<E>) new LinkedListIterator<E>(root, this);
	}
}

class ListNode<E extends Comparable<E>> {
	E element;
	Comparator<E> cmp;
	ListNode<E> next, previous;
	
	public ListNode() {
		element = null;
		next = null;
		previous = null;
	}
	
	public ListNode(Comparator<E> cmp) {
		this.cmp = cmp;
		element = null;
		next = null;
		previous = null;
	}
	
	public ListNode(E element) {
		this.element = element;
		this.next = null;
	}
	
	public ListNode(E element, ListNode<E> next) {
		this.element = element;
		this.next = next;
	}
	
	public ListNode(Comparator<E> cmp, ListNode<E> previous) {
		this.previous = previous;
		this.cmp = cmp;
	}
	
	public ListNode(E element, ListNode<E> next, ListNode<E> previous) {
		this.element = element;
		this.next = next;
		this.previous = previous;
	}
	
	public void set(E element) {
		this.element = element;
	}
	
	public ListNode<E> add(E element) {
		this.element = element;
		next = new ListNode<E>();
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
		return element.compareTo(node.get()) == 0;
	}
}

class LinkedListIterator<E extends Comparable<E>> implements Iterator<ListNode<E>> {
	ListNode<E> current;
	DoublyLinkedList<E> list;
	
	public LinkedListIterator() {
		current = null;
	}
	
	public LinkedListIterator(ListNode<E> current, DoublyLinkedList<E> list) {
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