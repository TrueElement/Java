package data;

import java.util.ArrayList;
import tree.BinaryTree;
import tree.Node;

public class Category {
	String name;
	ArrayList<Page> pages;
	BinaryTree<String> tree;
	public static ArrayList<String> ignore_words = new ArrayList<String>();
	public Category(String name) {
		this.name = name;
		pages = new ArrayList<Page>();
		tree = new BinaryTree<String>();
	}
	
	public Category(String name, ArrayList<Page> pages) {
		this.name = name;
		this.pages = pages;
		tree = new BinaryTree<String>();
	}
	
	public static void ignoreWord(String word) {
		Category.ignore_words.add(word);
	}

    public String getName() {
        return name;
    }
	
	public void addPage(String url, String[] words) {
		Page temp = new Page();
			temp.setURL(url);
			temp.process(words);
		pages.add(temp);
		
		String current;
		for(int i = 0; i < words.length; i++) {
			current = words[i];
			if(Category.ignore_words.contains(current)) {
				continue;
			} else {
				tree.add(current);
			}
		}
	}

    public boolean contains(String url) {
        boolean to_return = false;
        Page temp;
        for(int i = 0; i < pages.size(); i++) {
            temp = pages.get(i);
            if(temp.getURL().equals(url)) {
                to_return = true;
                break;
            }
        }
        return to_return;
    }

    public boolean contains(Page page) {
        boolean to_return = false;
        Page temp;
        for(int i = 0; i < pages.size(); i++) {
            temp = pages.get(i);
            if(temp.equals(page)) {
                to_return = true;
                break;
            }
        }
        return to_return;
    }

    public String getMinCount() {
        return tree.getMinCount();
    }

    public String getMaxCount() {
        return tree.getMaxCount();
    }

    public ArrayList<Node<String>> getNodes() {
        return tree.getInOrderArray();
    }




}
