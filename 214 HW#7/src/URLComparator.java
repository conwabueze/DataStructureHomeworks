import java.util.Comparator;

/*
 * helper method class used to help sorting for table printing
 */
public class URLComparator implements Comparator<WebPage>{
	
	/**
	 * 
	 * @param o1, item A
	 * @param o2, item B
	 * @return int, comparsion
	 */
	@Override
	public int compare(WebPage o1, WebPage o2) {
		///used for sorting in ascending order URL 
		return o1.getURL().compareTo(o2.getURL());
	}


}
