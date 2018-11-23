

/*
 * This class represents a Packet that will be interested and removed in other parts of the program
 */
public class Packet {
	
	//INSTANCE VARIABLES
	private static int packetCount=0;
	private int id;
	private int packetSize;
	private int timeArrive;
	private int itemToDest;
	private int orginalItemToDest;
	
	//CONSTRUCTORS
	public Packet() {
		this.id = packetCount;
		packetCount++;
	}
	public Packet(int packetSize, int timeArrive, int itemToDest) {
		super();
		this.id = packetCount;
		packetCount++;
		this.packetSize = packetSize;
		this.timeArrive = timeArrive;
		this.itemToDest = itemToDest;
		orginalItemToDest = itemToDest;
	}
	
	//GETTERS AND SETTERS
	public static int getPacketCount() {
		return packetCount;
	}
	public static void setPacketCount(int packetCount) {
		Packet.packetCount = packetCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPacketSize() {
		return packetSize;
	}
	public void setPacketSize(int packetSize) {
		this.packetSize = packetSize;
	}
	public int getTimeArrive() {
		return timeArrive;
	}
	public void setTimeArrive(int timeArrive) {
		this.timeArrive = timeArrive;
	}
	public int getItemToDest() {
		return itemToDest;
	}
	public void setItemToDest(int itemToDest) {
		this.itemToDest = itemToDest;
	}

	public int getOrginalItemToDest() {
		return orginalItemToDest;
	}
	
	//TOSTRING
	public String toString() {
		return "["+id +","+timeArrive+","+itemToDest+"]";
	} 
	
	
}
