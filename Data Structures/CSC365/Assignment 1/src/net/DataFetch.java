package net;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import data.Category;
import data.Data;


public class DataFetch {
	Parser parse;
	Data data;
	String current_url;
	
	public DataFetch() {
		parse = new Parser();
	}
	
	public void setData(Data data) {
		this.data = data;
	}
	
	public void fetch(String url_file) throws IOException, FileNotFoundException {
		File file = new File(url_file);
		if(file.exists()) {
			Scanner sc = new Scanner(file);
			Category cat = null;
			while(sc.hasNext()) {
				current_url = sc.next();
				if(current_url.matches("\\d")) {
					current_url = sc.next();
					if(cat != null) {
						data.addCategory(cat);
					}
					cat = new Category(current_url);
				} else {
					String[] p_words = parse.parse(current_url);
					cat.addPage(current_url, p_words);
				}
			}
			sc.close();
		} else {
			System.out.println("File doesn't exist.");
		}
	}
	
	public String getLastURLProcessed() {
		return current_url;
	}
}