package data;

import java.util.ArrayList;
import java.util.Collections;
import tree.Node;

public class Data {
	ArrayList<Category> categories;

	public Data() {
		categories = new ArrayList<Category>();
	}
	
	public Category getCategory(String name) {
		Category to_return = null;
		return to_return;
	}
	
	public void addCategory(Category cat) {
		categories.add(cat);
	}

    public void addCategory(String category, String url, String[] words) {
    	if(hasCategory(category)) {
    		Category temp = getCategoryByName(category);
    			temp.addPage(url, words);
    	} else {
            Category temp = new Category(category);
            	temp.addPage(url, words);
            categories.add(temp);
    	}
    }
    
    public boolean hasCategory(String category) {
    	Category temp;
    	boolean to_return = false;
    	for(int i = 0; i < categories.size(); i++) {
    		temp = categories.get(i);
    		if(temp.getName().equals(category)) {
    			to_return = true;
    			break;
    		}
    	}
    	return to_return;
    }
	
	public String findCategory(Page page) {
		String to_return = null;
        Category temp;
        for(int i = 0; i < categories.size(); i++) {
            temp = categories.get(i);
            if(temp.contains(page)) {
                to_return = temp.getName();
                break;
            }
        }
		return to_return;
	}

    public String findCategory(String url) {
        String to_return = null;
        Category temp;
        for(int i = 0; i < categories.size(); i++) {
            temp = categories.get(i);
            if(temp.contains(url)) {
                to_return = temp.getName();
                break;
            }
        }
        return to_return;
    }

    public String matchCategory(Page page) {
        String to_return = null;
        Category temp;
        ArrayList<Node<String>> page_nodes, category_nodes;
        page_nodes = page.getNodes();
        page_nodes = sortByCount(page_nodes);
        for(int i = 0; i < categories.size(); i++) {
            temp = categories.get(i);
            category_nodes = temp.getNodes();
            category_nodes = sortByCount(category_nodes);

        }
        return to_return;
    }
    
    public Category getCategoryByName(String name) {
    	Category to_return = null;
    	for(int i = 0; i < categories.size(); i++) {
    		to_return = categories.get(i);
    		if(to_return.getName().equals(name)) {
    			break;
    		}
    	}
    	return to_return;
    }

    public int compareWords(ArrayList<Node<String>> category_nodes, ArrayList<Node<String>> page_ndoes) {
    	int to_return = 0;
    	
        return to_return;
    }

    public String getSimilar(Page page) {
        return null;
    }
    
    public String getCategories() {
    	Category temp;
    	StringBuilder sb = new StringBuilder();
    		sb.append("Known Categories: ");
    		sb.append("\n");
    	for(int i = 0; i < categories.size(); i++) {
    		sb.append(i + ". ");
    		temp = categories.get(i);
    		sb.append(temp.getName());
    		sb.append("\n");
    	}
    	return sb.toString();
    }

    public ArrayList<Node<String>> sortByCount(ArrayList<Node<String>> nodes) {
        Collections.sort(nodes, new NodeCountComparator<String>());
        return nodes;
    }
}