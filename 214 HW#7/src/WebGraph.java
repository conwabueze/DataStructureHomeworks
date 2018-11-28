import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class WebGraph {
	////////////MEMBER VARIABLES////////////
	private static final int MAX_PAGES = 40;
	private ArrayList<WebPage> pages;
	private ArrayList<ArrayList<Integer>> edges;
	private HashMap<String, Integer> urlIndexLookUp;
	
	//this member variable used for means on manipulation when printing the table
	private ArrayList<ArrayList<Integer>> edgesReorder;
	
	///////////////CONSTRUCTORS/////////////
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

	/**
	 * 
	 * @param pagesFile, name of page file
	 * @param linksFile, name of links file
	 * @throws IllegalArgumentException, File not found
	 * @throws FileNotFoundException, File not found
	 * @return WebGraph, webgraph built from files
	 */
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
		
		
		//update page ranks
		WebGraph graph = new WebGraph(pages,edges,urlIndexLookUp);
		graph.updatePageRanks();
	
		return graph;
	}
	
	/*
	 * 
	 * @param url, url of pages
	 * @param kehywords, keywords associated with page
	 * @throws IllegalArgumentException, url already exist
	 */
	public void addPage(String url, ArrayList<String> keywords) throws IllegalArgumentException{
		if(urlIndexLookUp.containsKey(url)) {
			throw new IllegalArgumentException("This page already exist");
		}
		WebPage page = new WebPage(url,pages.size(),0,keywords);
		pages.add(page);
		edges.add(new ArrayList<Integer>());
		urlIndexLookUp.put(url, pages.size()-1);
		updatePageRanks();
	}
	
	/**
	 * 
	 * @param source, source 
	 * @param destination, destination of link
	 * @throws IllegalArgumentException, invalid source or destination name
	 */
	public void addLink(String source, String destination) throws IllegalArgumentException{
		if(urlIndexLookUp.containsKey(source) || urlIndexLookUp.containsKey(destination)) {
			throw new IllegalArgumentException("One or Both of these URL's does not exist");
		}
		edges.get(urlIndexLookUp.get(source)).add(urlIndexLookUp.get(destination));
		updatePageRanks();
	}
	/**
	 * 
	 * @param url, url to remove 
	 */
	public void removePage(String url) {
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
		//remove edge from edgelist
		edges.remove(removalIndex);
		
		///update lookup table///
		
		//remove url from table
		urlIndexLookUp.remove(url);
		//now loop through pages starting from the removed index pull the hash by key and decrement the value of the hash
		for(int i = removalIndex; i<pages.size(); i++) {
			urlIndexLookUp.put(pages.get(i).getURL(),urlIndexLookUp.get(pages.get(i).getURL())-1);
		}
		
		//update page rank
		updatePageRanks();
	}
	
	/**
	 * 
	 * @param source, source 
	 * @param destination, destination of link
	 */
	public void removeLink(String source, String destination) {
		//get edge list of source and find index of destination
		int removalIndex = edges.get(urlIndexLookUp.get(source)).indexOf(urlIndexLookUp.get(destination));
		
		//remove edge from list
		edges.get(urlIndexLookUp.get(source)).remove(removalIndex);
	
		updatePageRanks();
	}
	
	/*
	 * this method will update all pages as once
	 */
	public void updatePageRanks() {
		//start by getting index you want to look for one by one
		for(int i = 0; i<pages.size(); i++) {
			//once you get the index you want to look through the entire list
			int rank = 0;
			//row traverse
			for(int x=0;x<edges.size();x++) {
				//col traversal
				
				//if index is not equal to row in other words as long as where not on the same site traverse edges 
				if(i!=x) {
					for(int y =0; y<edges.get(x).size();y++) {
						if(edges.get(x).get(y)==i)
							rank++;
					}
				}
			}
			
			//update page rank of particular site
			pages.get(i).setRank(rank);
		}
	}
	
	/**
	 * 
	 * @param keyword, keyword to search 
	 */
	public void searchKeyword(String keyword) {
		String header = String.format("%1s%15s%15s", "Rank","PageRank","URL");
		System.out.println(header);
		System.out.println("----------------------------------------------");
		ArrayList<WebPage> keywordSearchList = new ArrayList<>();
		for(int i = 0; i<pages.size(); i++) {
			if(pages.get(i).getKeywords().contains(keyword)) {
				keywordSearchList.add(pages.get(i));
				//String content = String.format("%1s%20s%20s", args);
			}
				
		}
		//sort Array by Rank
		Collections.sort(keywordSearchList, new RankComparator());
		
		//print out sorted array
		int rank = 1;
		for(int i = 0; i<keywordSearchList.size();i++) {
			WebPage page =keywordSearchList.get(i);
			String content = String.format("%1s%15s%20s", rank+"\t|",page.getRank()+"\t|",page.getURL()+"\t|");
			System.out.println(content);
			rank++;
		}
		
		//reset rank
		rank = 0;
		
	
		
		
		
		//String content = String.format("%1s%20s%20s", args);
		
	}
	
	/**
	 * method prints table
	 */
	public void printTable() {
		String tableHeader = String.format("%2s%20s%30s%20s%40s", "Index", "URL","PageRank","Links","Keywords");
		System.out.println(tableHeader);
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		for(int i =0; i<pages.size(); i++) {
			String keywords = "";
			//checks if keywords is empty
			if(pages.get(i).getKeywords().size()==0) {
				//if empty set to 0
				keywords = "";
			}
			else {
				keywords = pages.get(i).getKeywords().toString();
				keywords = keywords.substring(1, keywords.length()-1);
			}
			
			String links = "";
			if(edges.get(i).size()<=0) {
				links = "";
			}
			else {
				links = edges.get(i).toString();
				links = links.substring(1, links.length()-1);
			}

			//String content  = String.format("%2s%10s%30s%20s%30s", pages.get(i).getIndex(), pages.get(i).getURL(), pages.get(i).getRank(), links, keywords);
			String content  = String.format("%2s%30s%20s%25s%50s", pages.get(i).getIndex()+"\t|", pages.get(i).getURL()+"\t|", pages.get(i).getRank()+"\t|",links+"\t|",keywords);
			System.out.println(content);
		}
	}
	
	/**
	 * prints table but uses edgeReorder nested list instead
	 */
	public void printTableAlt() {
		String tableHeader = String.format("%2s%20s%30s%20s%40s", "Index", "URL","PageRank","Links","Keywords");
		System.out.println(tableHeader);
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		for(int i =0; i<pages.size(); i++) {
			String keywords = "";
			//checks if keywords is empty
			if(pages.get(i).getKeywords().size()==0) {
				//if empty set to 0
				keywords = "";
			}
			else {
				keywords = pages.get(i).getKeywords().toString();
				keywords = keywords.substring(1, keywords.length()-1);
			}
			
			String links = "";
			if(edgesReorder.get(i).size()<=0) {
				links = "";
			}
			else {
				links = edgesReorder.get(i).toString();
				links = links.substring(1, links.length()-1);
			}

			//String content  = String.format("%2s%10s%30s%20s%30s", pages.get(i).getIndex(), pages.get(i).getURL(), pages.get(i).getRank(), links, keywords);
			String content  = String.format("%2s%30s%20s%25s%50s", pages.get(i).getIndex()+"\t|", pages.get(i).getURL()+"\t|", pages.get(i).getRank()+"\t|",links+"\t|",keywords);
			System.out.println(content);
		}
	}
	/*
	 * this method reorder the edges list depending on the order
	 * method used mainly for print table in whatever order
	 */
	public void edgeListReorder() {
		//create order new 2D ArrayList and pull from old and put into new
		edgesReorder = new ArrayList<>();
		
		//traverse page list and pull edge list and insert for new order
		
		//Initialize nest array list;
		for(int i = 0; i<pages.size(); i++) {
			int listIndex = urlIndexLookUp.get(pages.get(i).getURL());
			edgesReorder.add(edges.get(listIndex));
		}
	}

	//////GETTERS AND SETTERS//////////
	public ArrayList<WebPage> getPages() {
		return pages;
	}


	public ArrayList<ArrayList<Integer>> getEdges() {
		return edges;
	}


	public HashMap<String, Integer> getUrlIndexLookUp() {
		return urlIndexLookUp;
	}
	
	
	
	
}
