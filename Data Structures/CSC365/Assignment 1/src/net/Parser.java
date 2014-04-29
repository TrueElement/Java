package net;
//Import Statements
//External Imports
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Parser {
	
	public Parser() {
		
	}
	
	public String[] parse(String url) throws IOException {
		System.out.println("Fetching Words in: " + url);
		Document doc = Jsoup.connect(url)
							.data("query","java")
							.userAgent("Mozilla")
							.cookie("auth", "token")
							.timeout(3000)
							.get();
		String body = doc.body().text();
		String[] split_body = body.split("\\W+");
		return split_body;
	}
	
}

