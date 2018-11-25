import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class WebGraph {

	private static final int MAX_PAGES = 40;
	private ArrayList<WebPage> pages;
	private ArrayList<ArrayList<Integer>> edges;
	
	public WebGraph() {
		
	}
	
	public static WebGraph buildFromFiles(String pagesFile, String linksFile) throws IllegalArgumentException, FileNotFoundException{
		//Lists needed to return WebGraph Object
		ArrayList<WebPage> pages = new ArrayList<>();
		ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
		
		//Initialize nest array list;
		for(int i = 0; i<pages.size(); i++) {
			edges.add(new ArrayList<Integer>());
		}
		
		//Hashmap for each look for indices 
		HashMap<String, Integer> urlIndexLookUp = new HashMap<>();
		
		File pagesList = new File("pages.txt");
		Scanner readPages =  new Scanner(pagesList);
		
		
		int index = 0;
		while(readPages.hasNextLine()) {
			//get line
			String line = readPages.nextLine();
			//cut off space in beginning of file
			line = line.substring(4);

			//split line by space to get each word into array indice
			String[] lineSplit = line.split(" ");
			//keyword bank needed to create webpage object
			ArrayList<String> keywords = new ArrayList<>();
			//loop to add key words
			for(int i = 1; i<lineSplit.length; i++) {
				keywords.add(lineSplit[i]);
			}
			//object creation
			WebPage newPage = new WebPage(lineSplit[0],index,0,keywords);
			
			//Add look up to hashmap 
			urlIndexLookUp.put(lineSplit[0], index);
			
			//add webPage to pages ArrayList
			pages.add(newPage);
			
			//increment index for next webpage
			index++;
			System.out.println(newPage.toString());
			
		}
		
		System.out.println();
		
		File links = new File("links.txt");
		Scanner readLinks = new Scanner(links);
		while(readLinks.hasNextLine()) {
			//read line
			String line = readLinks.nextLine();
			//cut off spaces
			line = line.substring(4);
			
			System.out.println(line);
			
			//split to get words
			String[] splitLines = line.split(" ");
			String from = splitLines[0];
			String to = splitLines[1];
			System.out.println("from "+from + " lookup" + to);
			
			edges.get(urlIndexLookUp.get(from)).add(urlIndexLookUp.get(to));
			
		}
		
		
		return new WebGraph();
	}
}
