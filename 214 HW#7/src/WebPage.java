


import java.util.ArrayList;
import java.util.Collection;

public class WebPage {
	/////////////MEMBER VARIABLES//////////////
	private String URL;
	private int index;
	private int rank;
	private ArrayList<String> keywords;
	
	//////////////////CONSTRUCTORS////////////////////
	public WebPage() {}

	public WebPage(String uRL, int index, int rank, ArrayList<String> keywords) {
		super();
		URL = uRL;
		this.index = index;
		this.rank = rank;
		this.keywords = keywords;
	}
	
	//////////GETTERS AND SETTERS///////////////
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public ArrayList<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}

	/////////////TO STRING//////////////////////
	@Override
	public String toString() {
		return "Webpage [URL=" + URL + ", index=" + index + ", rank=" + rank + ", keywords=" + keywords + "]";
	}
	
	
}
