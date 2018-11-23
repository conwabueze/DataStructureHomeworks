


/*
 * This class throws an exception if the auction is closed (TimeRemaining = 0)
 * 
 * */
public class ClosedAuctionException extends Exception {
	
	public ClosedAuctionException(){
		super("This auction is closed buddy");
	}
}
