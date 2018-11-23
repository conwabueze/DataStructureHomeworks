
import java.io.Serializable;

import big.data.*;
public class Auction implements Serializable{

//MEMEBER VARIABLES
private String sellersName;
private double currentBid;
private int timeRemaining;
private String buyersName;
private String itemInfo;
private String auctionID;


/////CONSTRUCTORS
private Auction() {

}



public Auction(String sellersName, double currentBid, int timeRemaining, String buyersName, String itemInfo,
		String auctionID) {
	super();
	this.sellersName = sellersName;
	this.currentBid = currentBid;
	this.timeRemaining = timeRemaining;
	this.buyersName = buyersName;
	this.itemInfo = itemInfo;
	this.auctionID = auctionID;
}


/**
 * this method updates the bid of auction object if higher then the previous bid
 * 
 * @param bidderName, name of bidder
 * @param bidAmt, amount to bid 
 * @throws IllegalArgumentException, auction time 0
 *
 */
public void newBid(String bidderName, double bidAmt) throws ClosedAuctionException{
	if(timeRemaining>0) {
		//if the new bid is bigger then the current bid replace bidAmt and buyerName
		if(bidAmt>currentBid) {
			buyersName = bidderName;
			currentBid = bidAmt;
		}
	}
	else
		throw new ClosedAuctionException();
}

/**
 * this method decrements the time of the auction object
 * 
 * @param time, time to decrement
 *
 */
public void decrementTimeRemaining(int time) {
	if(timeRemaining-time<0) 
		timeRemaining = 0;
	
	else
		timeRemaining=timeRemaining-time;
}



//GETTERS
public String getSellersName() {
	return sellersName;
}

public double getCurrentBid() {
	return currentBid;
}

public int getTimeRemaining() {
	return timeRemaining;
}

public String getBuyersName() {
	return buyersName;
}

public String getItemInfo() {
	return itemInfo;
}

public String getAuctionID() {
	return auctionID;
}


//TO STRING 
@Override
public String toString() {
	
		String itemInfoParse = String.format("%.35s", itemInfo);
		String returnStr = String.format("%-5s%17s%30s%30s%20s%40s", auctionID+"|", currentBid+"|", sellersName+"|", buyersName+"|",timeRemaining+"|",itemInfoParse);

	
			//String.format("%-5s%17s%17s%10s%10s%10s", auctionID, currentBid, sellersName, buyersName,timeRemaining,itemInfoParse);
//	return "Auction [sellersName=" + sellersName + ", currentBid=" + currentBid + ", timeRemaining=" + timeRemaining
//			+ ", buyersName=" + buyersName + ", itemInfo=" + itemInfoParse + ", auctionID=" + auctionID + "]";
	return returnStr;
	
}




}
