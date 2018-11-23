
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


import big.data.*;

public class AuctionTable implements Serializable {
	////MEMBER VARIABLES
	private static HashMap<String,Auction> auctionTableMap = new HashMap<>();
	private static String username;
	
	//CONSTRUCTORS
	public AuctionTable() {
		//HashMap<String,Auction> auctionTableMap = new HashMap<>();
	}
	
	public AuctionTable(HashMap<String,Auction> auctionTableMap) {
		this.auctionTableMap = auctionTableMap;
	}
	public AuctionTable(String username, HashMap<String,Auction> auctionTableMap) {
		this.username = username;
		this.auctionTableMap = auctionTableMap;
	}
	
	public AuctionTable(String username) {
		//HashMap<String,Auction> auctionTableMap = new HashMap<>();
		this.username = username;
	}
	
	/**
	 * this method parse through big data to create a auction table of auctions from a certain URL
	 * 
	 * @param URL, url to parse from
	 * @throws IllegalArgumentException, invalid URL
	 * @return AuctionTable, AuctionTable of auctions created from URL
	 */
	public static AuctionTable buildFromURL(String URL) throws IllegalArgumentException{
		
		//load data 
		DataSource ds = DataSource.connect(URL);
		ds.load();
		
		//get data needed to create objects
		String[] sellersName = ds.fetchStringArray("listing/seller_info/seller_name");
		String[] currentBid = ds.fetchStringArray("listing/auction_info/current_bid");
		String[] timeRemaining = ds.fetchStringArray("listing/auction_info/time_left");
		String[] buyersName = ds.fetchStringArray("listing/auction_info/high_bidder/bidder_name");
		String[] itemInfo =  ds.fetchStringArray("listing/item_info/description");
		String[] auctionId = ds.fetchStringArray("listing/auction_info/id_num");
//		for(int y = 0; y<sellersName.length;y++) {
//			System.out.println(sellersName[y]+" "+currentBid[y] +" "+ timeRemaining[y]+buyersName[y]+auctionId[y]);
//		}
		
		//create auction to insert into hashtable
		for(int i = 0; i<sellersName.length; i++) {
			//convert currentBid to double
			double currentBidDouble = 0;
			if(currentBid[i].contains(",")) {
				String[] splitComma = currentBid[i].split(",");
				String parsedVal ="";
				for(int w = 0; w<splitComma.length; w++) {
					parsedVal+=splitComma[w];
				}
				currentBidDouble = Double.parseDouble(parsedVal.substring(1));
			}	
			else
				currentBidDouble = Double.parseDouble(currentBid[i].substring(1));
			
			
			//Time remaining 
			String timeRemainingString = timeRemaining[i];
			int trueTimeRemaining = 0;
			///days parse
			if(timeRemainingString.contains("days")) {
				int indexAfter = timeRemainingString.indexOf("days")-1;
				String parsedDays = "";
				for(int x =0; x<indexAfter;x++) {
					if(Character.isDigit(timeRemainingString.charAt(x))) {
						parsedDays += timeRemainingString.charAt(x);
					}
				}
				trueTimeRemaining = Integer.parseInt(parsedDays)*24;
				
				//update string with days for hours
				timeRemainingString = timeRemainingString.substring(timeRemainingString.indexOf("days")+3);
			}
			//day parse
			else if(timeRemainingString.contains("day")) {
				int indexAfter = timeRemainingString.indexOf("day")-1;
				String parsedDays = "";
				trueTimeRemaining = 24;
				
				//update string with days for hours
				timeRemainingString = timeRemainingString.substring(timeRemainingString.indexOf("day")+2);
			}
			
			///hours parse
			if(timeRemainingString.contains("hours")) {
				String parsedHours = "";
				int hoursIndex = timeRemainingString.indexOf("hours");
				for(int x =0; x<hoursIndex;x++) {
					if(Character.isDigit(timeRemainingString.charAt(x))) {
						parsedHours += timeRemainingString.charAt(x);
					}
				}
				trueTimeRemaining += Integer.parseInt(parsedHours);
			}
			else if(timeRemainingString.contains("hrs")) {
				String parsedHours = "";
				int hourIndex = timeRemainingString.indexOf("hrs");
				for(int x =0; x<hourIndex;x++) {
					if(Character.isDigit(timeRemainingString.charAt(x))) {
						parsedHours += timeRemainingString.charAt(x);
					}
				}
				trueTimeRemaining += Integer.parseInt(parsedHours);
			}
			Auction auction = new Auction(sellersName[i], currentBidDouble, trueTimeRemaining, buyersName[i], itemInfo[i], auctionId[i]);
			
			auctionTableMap.put(auction.getAuctionID(),auction);
			
			
		}
		//System.out.println(auctionTableMap);
		return new AuctionTable(username,auctionTableMap);
		
	}
	
	/**
	 * this method creates a new auction and inserts it into the HashMap
	 * 
	 * @param auctionID, key for HashMap
	 * @param auction, auction object to be inserted into hashmap
	 * @throws IllegalArgumentException, key already exist
	 */
	public void putAuction(String auctionID, Auction auction) throws IllegalArgumentException{
		if(auctionTableMap.containsKey(auctionID))
			throw new IllegalArgumentException();
		else
			auctionTableMap.put(auctionID, auction);
	}
	
	
	/**
	 * this method gets auction from HashMap
	 * 
	 * @param auctionID, key for HashMap
	 * @return Auction
	 */
	public Auction getAuction(String auctionID) {
		return auctionTableMap.get(auctionID);
	}
	/**
	 * this method decrements all auctions in Map
	 * 
	 * @param numHours, decrement amount
	 * @throws IllegalArgumentException, Map is null
	 */
	public void letTimePass(int numHours) throws IllegalArgumentException{
		if(auctionTableMap == null)
			throw new IllegalArgumentException();
		
		for(String key: auctionTableMap.keySet()) {
			auctionTableMap.get(key).decrementTimeRemaining(numHours);
		}
	}
	
	/**
	 * this method removes any auctions with the time remaining of 0
	 */
	public void removeExpiredAuctions() {
		ArrayList<String> keyDeletions = new ArrayList<>();
		for(String key: auctionTableMap.keySet()) {
			if(auctionTableMap.get(key).getTimeRemaining()==0)
				keyDeletions.add(key);	
		}
		for(int i = 0; i<keyDeletions.size(); i++) {
				auctionTableMap.remove(keyDeletions.get(i));	
		}
	}
	
	/**
	 * this method prints the toString of all auctions in the Map formatted
	 */
	public void printTable() {
		String tableHeader = String.format("%-5s%17s%30s%30s%20s%30s", "Auction ID|", "Bid|", "Seller|", "Buyer|","Time|","Item Info");
		System.out.println(tableHeader);
		System.out.println("============================================================================================================"
				+ "========================================");
		for(String key: auctionTableMap.keySet()) {
			System.out.println(auctionTableMap.get(key).toString());
					
		}
	}

	///GETTERS
	public static HashMap<String, Auction> getAuctionTableMap() {
		return auctionTableMap;
	}

	public static String getUsername() {
		return username;
	}
	
	
	
	
	
}
