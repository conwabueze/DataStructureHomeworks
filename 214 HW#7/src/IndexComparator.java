


import java.io.FileNotFoundException;
import java.util.Comparator;

/*
 * helper method class used to help sorting for table printing
 */
public class IndexComparator implements Comparator<WebPage>{
	
	/**
	 * 
	 * @param arg0, item A
	 * @param arg1, item B
	 * @return int, comparsion
	 */
	@Override
	public int compare(WebPage arg0, WebPage arg1) {
		///used for sorting in ascending order Index
		return arg0.getIndex() - arg1.getIndex();
	}

	

}
