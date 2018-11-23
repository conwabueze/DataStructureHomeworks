
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

import big.data.*;


/*
 * This class represents the Auction System where the user can import, create, bid, and remove form different auctions
 * 
 * */
public class AuctionSystem implements Serializable{
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, ClosedAuctionException{
		
		
		Scanner input = new Scanner(System.in);
		String menu = "menu:\n"
				+ "\t(D) - Import Data from URL\n"
				+ "\t(A) - Create a New Auction\n"
				+ "\t(B) - Bid on an Item\n"
				+ "\t(I) - Get Info on Auction\n"
				+ "\t(P) - Print All Auctions\n"
				+ "\t(R) - Remove Expired Auctions\n"
				+ "\t(T) - Let Time Pass\n"
				+ "\t(Q) - Quit";
		
		System.out.println("Please select a username:");
		String username = input.nextLine();
		HashMap<String, Auction> auctionMap = new HashMap<>();
		//AuctionTable table = new AuctionTable(username, auctionMap);
		AuctionTable table = new AuctionTable();
		//create File
				FileOutputStream outputStream = null;
				File file=new File("auction.obj");;
				//file.createNewFile();
				try { 
					
					if(file.exists()) {
						FileInputStream fileInput = new FileInputStream("auction.obj");
						ObjectInputStream inStream = new ObjectInputStream(fileInput);

						table = (AuctionTable) inStream.readObject();
					}
					else {
						table = new AuctionTable(username, auctionMap);

					}
					
				}catch(IOException e) {
					System.out.println(e);
				}
				
		System.out.println(menu);
		System.out.println("Please select an option:");
		String select = input.nextLine();
		while(true) {
			
			if(select.equals("D")) {
				System.out.println("Please Enter a URL");
				String url = input.nextLine();
				table = table.buildFromURL(url);
			}
			else if(select.equals("A")) {
				System.out.println("Creating new Auction as " + table.getUsername());
				System.out.println("Please enter an Auction ID:");
				String auctionId = input.nextLine();
				System.out.println("Please enter an Auction time (hours): ");
				int auctionTime = input.nextInt();
				System.out.println("Please enter some Item Info: ");
				input.nextLine();
				String desc = input.nextLine();
				Auction newAuction = new Auction(table.getUsername(), 0, auctionTime, "", desc, auctionId);
				table.putAuction(auctionId, newAuction);
			}
			else if(select.equals("B")) {
				System.out.println("Please enter an Auction ID: ");
				String auctionId = input.nextLine();
				if(table.getAuctionTableMap().containsKey(auctionId)) {
					System.out.println("Auction " + auctionId + " is OPEN");
					System.out.println("\tCurrent Bid: $ "+ table.getAuctionTableMap().get(auctionId).getCurrentBid());
				}
				
				System.out.println("What would you like to bid?: ");
				double bid = input.nextDouble();
				table.getAuctionTableMap().get(auctionId).newBid(username, bid);
			}
			else if(select.equals("I")) {
				System.out.println("Please enter an Auction ID:");
				String auctionId = input.nextLine();
				System.out.println("Auction " +auctionId+":");
				Auction auction = table.getAuctionTableMap().get(auctionId);
				System.out.println("\tSeller: "+auction.getSellersName()+"\n"
						+ "\tBuyer: "+auction.getBuyersName()+"\n"
						+ "\tTime: "+auction.getTimeRemaining()+"\n"
						+ "\tInfo: "+String.format("%.50s", auction.getItemInfo())+"\n");
			}
			
			else if(select.equals("P")) {
				table.printTable();
			}
			else if(select.equals("R")) {
				System.out.println("Removing expired auctions...");
				table.removeExpiredAuctions();
				System.out.println("All expired auctions removed.");
			}
			else if(select.equals("T")) {
				System.out.println("How many hours should pass: ");
				int hoursPassed = input.nextInt();
				table.letTimePass(hoursPassed);
				System.out.println("Time passing...");
				System.out.println("Auction times updated.");
			}
			else if(select.equals("Q")) {
				FileOutputStream fileOutput = new FileOutputStream("auction.obj");
				ObjectOutputStream outStream = new ObjectOutputStream(fileOutput);
				outStream.writeObject(table);
				System.out.println("File saved");
				break;
			}
			
			
			System.out.println();
			System.out.println(menu);
			System.out.println("Please select an option:");
			select = input.nextLine();
			
		}
		
		
		
		
		
		
		
		
		
	
	}
	
}
