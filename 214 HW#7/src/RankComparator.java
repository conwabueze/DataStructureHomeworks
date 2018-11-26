import java.util.Comparator;

public class RankComparator implements Comparator<WebPage> {

	@Override
	public int compare(WebPage o1, WebPage o2) {
		///used for sorting in descending order RANK
		return o2.getRank()-o1.getRank();
	}
	
}
