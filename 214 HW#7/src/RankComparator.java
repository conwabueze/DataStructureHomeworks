import java.util.Comparator;

/*
 * helper method class used to help sorting for table printing
 */
public class RankComparator implements Comparator<WebPage> {
	
	/**
	 * 
	 * @param o1, item A
	 * @param o2, item B
	 * @return int, comparsion
	 */
	@Override
	public int compare(WebPage o1, WebPage o2) {
		///used for sorting in descending order RANK
		return o2.getRank()-o1.getRank();
	}
	
}
