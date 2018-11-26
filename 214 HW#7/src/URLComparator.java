import java.util.Comparator;

public class URLComparator implements Comparator<WebPage>{

	@Override
	public int compare(WebPage o1, WebPage o2) {
		///used for sorting in ascending order URL 
		return o1.getURL().compareTo(o2.getURL());
	}


}
