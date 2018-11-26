import java.util.Comparator;

public class IndexComparator implements Comparator<WebPage>{

	@Override
	public int compare(WebPage arg0, WebPage arg1) {
		///used for sorting in ascending order Index
		return arg0.getIndex() - arg1.getIndex();
	}

	

}
