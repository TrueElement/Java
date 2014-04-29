package data;
import java.util.ArrayList;
import tree.BinaryTree;
import tree.Node;

public class Page {
	String url;
	BinaryTree<String> tree;
	
	public Page() {
		tree = new BinaryTree<String>();
		url = null;
	}
	
	public void setURL(String url) {
		this.url = url;
	}
	
	public String getURL() {
		return url;
	}
	
	public void process(String[] words) {
		String current = null;
		for(int i = 0; i < words.length; i++) {
			current = words[i];
			if(Category.ignore_words.contains(current)) {
				continue;
			} else {
				tree.add(current);
			}
		}
	}

	public int getCount(String word) {
		Node<String> temp = tree.getNode(word);
		return temp.getCount();
	}

    public String getMinCount() {
        return tree.getMinCount();
    }

    public String getMaxCount() {
        return tree.getMaxCount();
    }


    public boolean equals(Page page) {
        return page.getURL().equals(url);
    }

    public ArrayList<Node<String>> getNodes() {
        return tree.getInOrderArray();
    }
}

