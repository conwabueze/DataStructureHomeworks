
public class dummyMain {

	public static void main(String[] args) {
		AuctionTable table = new AuctionTable();
		table.buildFromURL("http://tinyurl.com/nbf5g2h");
		table.printTable();
//		System.out.println();
//		table.letTimePass(100);
//		table.printTable();
//		Auction auction = new Auction("mark", 45, 40,"chatty chad", "yerrr", "jrknfkj");
//		table.putAuction("9303903", auction);
//		System.out.println();
//		table.printTable();
//		table.removeExpiredAuctions();
//		System.out.println();
//		table.printTable();
	}

}
