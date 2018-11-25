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
	private HashMap<String, Integer> urlIndexLookUp;
	
	public WebGraph() {
		
	}
	
	
	public WebGraph(ArrayList<WebPage> pages, ArrayList<ArrayList<Integer>> edges) {
		super();
		this.pages = pages;
		this.edges = edges;
	}

	public WebGraph(ArrayList<WebPage> pages, ArrayList<ArrayList<Integer>> edges,
			HashMap<String, Integer> urlIndexLookUp) {
		this.pages = pages;
		this.edges = edges;
		this.urlIndexLookUp = urlIndexLookUp;
	}


	public static WebGraph buildFromFiles(String pagesFile, String linksFile) throws IllegalArgumentException, FileNotFoundException{
		//Lists needed to return WebGraph Object
		ArrayList<WebPage> pages = new ArrayList<>();
		ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
		
		//Hashmap for each look for indices 
		HashMap<String, Integer> urlIndexLookUp = new HashMap<>();
		
		File pagesList = new File(pagesFile);
		if(!pagesList.exists()) {
			throw new IllegalArgumentException();
		}
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
			
		}
		
		//Initialize nest array list;
				for(int i = 0; i<pages.size(); i++) {
					edges.add(new ArrayList<Integer>());
				}
				
		File links = new File(linksFile);
		if(!links.exists()) {
			throw new IllegalArgumentException();
		}
		Scanner readLinks = new Scanner(links);
		while(readLinks.hasNextLine()) {
			//read line
			String line = readLinks.nextLine();
			//cut off spaces
			line = line.substring(4);
			
			
			//split to get words
			String[] splitLines = line.split(" ");
			String from = splitLines[0];
			String to = splitLines[1];
			
			edges.get(urlIndexLookUp.get(from)).add(urlIndexLookUp.get(to));
			
			
			
			
		}
		//printout arraycontent
		for(int i = 0; i < edges.size(); i++) {
			for(int x = 0; x< edges.get(i).size();x++) {
				System.out.print(edges.get(i).get(x) + " ");
			}
			System.out.println();
		}
		return new WebGraph(pages,edges,urlIndexLookUp);
	}
	
	public void addPage(String url, ArrayList<String> keywords) throws IllegalArgumentException{
		if(urlIndexLookUp.containsKey(url)) {
			throw new IllegalArgumentException("This page already exist");
		}
		WebPage page = new WebPage(url,pages.size()-1,0,keywords);
		pages.add(page);
		urlIndexLookUp.put(url, pages.size()-1);
	}
	
	public void addLink(String source, String destination) throws IllegalArgumentException{
		if(!(urlIndexLookUp.containsKey(source) || urlIndexLookUp.containsKey(destination))) {
			throw new IllegalArgumentException("One or Both of these URL's does not exist");
		}
		edges.get(urlIndexLookUp.get(source)).add(urlIndexLookUp.get(destination));
	}
	
	public void removePage(String url) {
		for(String urls: urlIndexLookUp.keySet()) {
			System.out.println(urls);
		}
		//get index of page we want to remove
		int removalIndex = urlIndexLookUp.get(url);
		
		//decrement index of all indices higher than the removalIndex
		for(int i = removalIndex+1; i<pages.size();i++) {
			pages.get(i).setIndex(pages.get(i).getIndex()-1);
		}
		
		//go thought all edges and removal any integers = to removalIndex
		for(int i = 0; i < edges.size(); i++) {
			if(edges.get(i).contains(removalIndex)) {
				edges.get(i).remove(edges.get(i).indexOf(removalIndex));	
			}
		}
		
		//look through all edges and decrements value in any list containing values higher than the removalIndex
		for(int i = 0; i < edges.size(); i++) {
			for(int x = 0; x< edges.get(i).size();x++) {
				if(edges.get(i).get(x)>removalIndex) {
					edges.get(i).set(x, edges.get(i).get(x)-1);
				}
			}
		}
		
		//remove index from page list
		pages.remove(removalIndex);
		
		//printout arraycontent
				for(int i = 0; i < edges.size(); i++) {
					for(int x = 0; x< edges.get(i).size();x++) {
						System.out.print(edges.get(i).get(x) + " ");
					}
					System.out.println();
				}
	}
}
