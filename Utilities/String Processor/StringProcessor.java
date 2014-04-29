package StringProcessor;

import java.util.ArrayList;


public class StringProcessor {
	public StringProcessor() {
		
	}
	
	public ArrayList<String> tokenize(String to_process, String delimeter) {
		ArrayList<String> to_return = new ArrayList<String>();
		StringBuilder buffer = new StringBuilder();
		int str_len = to_process.length();
		char current;
		for(int i = 0; i < str_len; i++) {
			current = to_process.charAt(i);
			
			
		}
		return to_return;
	}
}