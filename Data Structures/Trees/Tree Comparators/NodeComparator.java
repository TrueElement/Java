package TreeComparators;
//Import Statements
//Local Imports
import AbstractTree.Node;

//External Imports
import java.util.Comparator;



public class NodeComparator<T extends Comparable<? super T>, E extends Node<?,?>> implements Comparator<Node<T,E>> {
	protected Comparator<T> cmp;
	
	public NodeComparator(Comparator<T> cmp) {
		this.cmp = cmp;
	}

	@Override
	public int compare(Node<T, E> o1, Node<T, E> o2) {
		return 0;
	}
}