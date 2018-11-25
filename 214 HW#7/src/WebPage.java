import java.util.ArrayList;
import java.util.Collection;

public class WebPage {
	private String URL;
	private int index;
	private int rank;
	private ArrayList<String> keywords;
	
	public WebPage() {}

	public WebPage(String uRL, int index, int rank, ArrayList<String> keywords) {
		super();
		URL = uRL;
		this.index = index;
		this.rank = rank;
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		return "Webpage [URL=" + URL + ", index=" + index + ", rank=" + rank + ", keywords=" + keywords + "]";
	}
	
	
}
