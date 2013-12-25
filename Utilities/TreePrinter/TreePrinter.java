package TreePrinter;
//Import Statements
//Local Imports
import AbstractTree.Tree;
import AbstractTree.Node;
//External Imports
import java.util.ArrayList;
import java.util.Collections;

public class TreePrinter<T extends Comparable<? super T>> {
	private String separator;
	private String space;
	Tree<T, ? extends Node<?,?>> tree;
	
	public TreePrinter(Tree<T, ? extends Node<?,?>> tree) {
		this.tree = tree;
		separator = System.getProperty("line.separator");
		space = " ";
	}
	
	public String treeString() {
		return null;
	}
	
	public String treeString(int max_level) {
		ArrayList<? extends Node<?, ?>> nodes = tree.inOrderArray(max_level);
		StringBuilder tree = new StringBuilder();

		int index = nodes.size() - 1;
		int longest_line = 0;
		int space_needed = 0;
		StringBuilder line = new StringBuilder();
		Node<?,?> current;
		for(int i = max_level; i >= 0; i--) {
			while(true) {
				if(index < 0) {
					break;
				}
				current = nodes.get(index);
				if(current.level() == i) {
					line.insert(0,current.toString());
					index -= 1;
				} else {
					break;
				}
			}
			
			if(index < 0 && line.length() == 0) {
				break;
			}
			if(line.length() > longest_line) {
				longest_line = line.length();
			} else {
				space_needed = longest_line / ((max_level - i) * 2);
				space_needed += line.length() / 2;
				line.insert(0, getSpace(space_needed));				
			}
			tree.insert(0, line.toString());
			tree.insert(0, separator);
			line = new StringBuilder();
			
			if(index < 0) {
				break;
			}
		}

		return tree.toString();
	}
	
	public void printTree() {
		
	}
	
	public void printTree(int max_level) {
		System.out.println(treeString(max_level));
	}
	
	public String getSpace(int amt) {
		String to_return = "";
		StringBuffer ss = new StringBuffer();
		for(int i = 0; i < amt; i++) {
			ss.append(space);
		}
		to_return = ss.toString();
		return to_return;
	}
}