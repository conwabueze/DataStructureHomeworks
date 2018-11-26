import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SearchEngine {
	public static final String PAGES_FILE = "pages.txt";
	public static final String LINKS_FILE = "links.txt";
	private WebGraph web;
	
	public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException {
		String menu = "Menu:\n"
				+ "\t(AP) - Add a new page to the graph.\n"
				+ "\t(RP) - Remove a page from the graph.\n"
				+ "\t(AL) - Add a link between pages in the graph.\n"
				+ "\t(RL) - Remove a link between pages in the graph.\n"
				+ "\t(P) - Print the graph.\n"
				+ "\t\t(I) Sort based on index (ASC)\n"
				+ "\t\t(U) Sort based on URL (ASC)\n"
				+ "\t\t(R) Sort based on rank (DSC)\n"
				+ "\t(S) - Search for pages with a keyword.\n"
				+ "\t(Q) - Quit.\n";
		WebGraph graph = new WebGraph();
		graph=graph.buildFromFiles(PAGES_FILE, LINKS_FILE);
		System.out.println("Loading WebGraph data...\n"
				+ "Success!\n");
		
		while(true) {
			Scanner input =new Scanner(System.in);
			System.out.println(menu);
			System.out.println("Please select an option: ");
			String option = input.nextLine().toUpperCase();
			
			//add page
			if(option.equals("AP")) {
				System.out.println("Enter a URL:");
				String url = input.nextLine();
				System.out.println("Enter keywords (space-separated): ");
				String keywords = input.nextLine();
				
				//split keywords
				String[] keywordSplit = keywords.split(" ");
				//convert array to arraylist
				ArrayList<String> keywordsList = new ArrayList<>();
				for(String keyword: keywordSplit) {
					keywordsList.add(keyword);
				}
				
				graph.addPage(url,keywordsList);
				
			}
			//remove page
			///////////////////FIX METHOD///////////////////////
			else if(option.equals("RP")) {
				System.out.println("Enter a URL: ");
				String url = input.nextLine();
				graph.removePage(url);
			}
			//print table
			else if(option.equals("P")) {
				graph.printTable();
			}
			
			System.out.println();
		}

	}

}
