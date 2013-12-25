package RandomNumberListGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RandomNumberListGenerator {
	String file_name;
	
	public RandomNumberListGenerator() {
		file_name = null;
	}
	
	public void setFile(String file_name) {
		this.file_name = file_name;
	}
	
	public void generateFile(int lower_bound, int upper_bound) {
		try {
			ArrayList<Integer> arr = generateArray(lower_bound, upper_bound);
			File file = new File(file_name);
			PrintWriter pw = new PrintWriter(file);
			for(int i = 0; i < arr.size(); i++) {
				System.out.println(i + " Writing: " + arr.get(i));
				pw.println(arr.get(i));
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> generateArray(int lower_bound, int upper_bound) {
		ArrayList<Integer> random_list = new ArrayList<Integer>();
		
		for(int i = 0, j = lower_bound; j <= upper_bound; i++, j++) {
			random_list.add(i,j);
		}
		
		for(int i = random_list.size() - 1; i > 0; i--) {
			int j = randInt(0, i);
			int temp = random_list.get(i);
			random_list.set(i, random_list.get(j));
			random_list.set(j, temp);
		}
		return random_list;
	}
	
	private int randInt(int lower_bound, int upper_bound) {
		double rand_dub = lower_bound + Math.floor(Math.random() * ((upper_bound + 1) - lower_bound));
		return (int) rand_dub;
	}
}