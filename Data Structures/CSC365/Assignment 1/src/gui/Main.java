package gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import net.DataFetch;
import data.Data;
import tree.BinaryTree;
import data.Category;
import org.jsoup.HttpStatusException;

public class Main {
	Data data;
    GUI gui;

	public Main() {
		data = new Data();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Main mai = new Main();
			//mai.fetchData();
            mai.setupGUI();
	}
	
	public void setupGUI() {
		gui = new GUI(data);
            gui.createAndShowGUI();
	}
	
	public void fetchData() throws FileNotFoundException, IOException {
        Category.ignoreWord("the");
        Category.ignoreWord("and");
        Category.ignoreWord("but");
        Category.ignoreWord("or");
        Category.ignoreWord("a");
        DataFetch fet = new DataFetch();
        try {
        	fet.setData(data);
			fet.fetch("urllist.txt");
        } catch (HttpStatusException e) {
        	System.err.println("Failed on: " + fet.getLastURLProcessed());
        }
	}
	
	public void testTree() {
		BinaryTree<Integer> test = new BinaryTree<Integer>();
            test.add(10);
            test.add(5);
            test.add(3);
            test.add(4);
            test.add(15);
            test.add(13);
            test.add(17);
            test.add(16);

	}
}