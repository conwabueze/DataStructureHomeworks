import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/*
 * This class simulates a network
 */
public class Simulator {
	
	/**
	 * this method generates a random number in a specific range
	 * @param minVal, minimum value of range
	 * @param maxVal,  maximum value of range
	 * @return int, random integer within range
	 */
	private int randInt(int minVal, int maxVal) throws Exception {
		if(maxVal<=minVal)
			throw new Exception("maxVal has to greater than minVal");
		return (int)(Math.random()*((maxVal-minVal)+1)+minVal);
	}
	
	/**
	 * this method simulates a networks and outputs its results
	 * @param dispatcher, Level 1 router
	 * @param numIntRouters,  the number of Intermediate routers in the network.
	 * @param routers, Level 2 routers
	 * @param arrivalProb, the probability of a new packet arriving at the Dispatcher.
	 * @param maxBufferSize, the maximum number of Packets a Router can accommodate for.
	 * @param duration,  the number of simulation units
	 * @param minPacketSize, the minimum size of a Packet
	 * @param maxPacketSize,  the maximum size of a Packet
	 * @return double, router size
	 */
	public double simulate(Router dispatcher, int numIntRouters, List<Router> routers, double arrivalProb, int maxBufferSize, int duration, int minPacketSize, int maxPacketSize) throws Exception{
		
		//keep track
		int packetsDropped=0;
		int totalPacketsArrived=0;
		int totalServiceTime = 0;
		
		if(arrivalProb<0 || arrivalProb>1)
			throw new Exception("proability has to be betweeb 0 and 1.0");
		
		//intialize varable that need to be initialized
		dispatcher = new Router();
		routers = new ArrayList<>();
		
		//create number of level 2 routers for simulation
		for(int i=0;i<numIntRouters;i++) {
			routers.add(new Router());
		}
		
		//create number of time simulation occurs
		for(int x = 0; x<duration;x++) {

			//time print out
			System.out.println("time: "+x);
			//String ArrayList holding Packet sent to Strings
			List<String> sentToOutputList = new ArrayList<>();
			//get arrival probabilty and use math.random() to only put on the packets that at or are below that probabilty
			final int MAX_PACKETS =3;
			for(int i = 0; i<MAX_PACKETS; i++) {
				//if packet if packet is within the probabilty addits to the next avaible level 2 router
				if(Math.random()<arrivalProb) {
					//create random size within bounds
					int packetSize = randInt(minPacketSize, maxPacketSize);
					Packet packet = new Packet(packetSize,x,packetSize/100);
					//print out id and packet size
					System.out.println("Packet "+ packet.getId() + " arrives at dispatcher with size " +packet.getPacketSize()+".");
					
					//enqueue index
					int enqueueIndex = sendPacketTo(routers, maxBufferSize);
					if(enqueueIndex!=-1) {
					//enqueue packet
					routers.get(enqueueIndex).enqueue(packet);
					//add Sting output to list
					String sentToOutput = "Packet "+packet.getId()+" sent to Router "+ enqueueIndex+".";
					sentToOutputList.add(sentToOutput);
					}else {
					String sentToOutput = "Network is congested. Packet " + packet.getId()+ " is dropped."; 
					sentToOutputList.add(sentToOutput);
					packetsDropped++;
					}
				}
			}
			
			//print out sent out strings
			for(String sentTo:sentToOutputList)
				System.out.println(sentTo);
			
			
			//print out router values
			for(int i =0;i<routers.size();i++) {
				if(x!=0) {
					int timeArrived =routers.get(i).timeDestDecrement();
					if(timeArrived!=-1)
						totalServiceTime+=x-timeArrived;
				}

			}
			//successfully removed printout
			
			for(int i =0;i<routers.size();i++) {
				List<String> successfullyReachedList = routers.get(i).getSuccessfullyReachedList();
				for(int y =0; y<successfullyReachedList.size();y++) {
					System.out.println(successfullyReachedList.get(y));
					totalPacketsArrived++;
					
				}
				routers.get(i).setSuccessfullyReachedList(new ArrayList<>());
				
			}
			
			//print routers
			for(int i =0;i<routers.size();i++) {
				System.out.println("R"+i+": "+routers.get(i).toString());
			}
			System.out.println();
			
			
			
			
		}
		
		//final calculations
		System.out.println("Simulation ending...");
		System.out.println("Total service time: "+totalServiceTime);
		System.out.println("Total packets served: "+totalPacketsArrived);
		System.out.println("Average service time per packet: "+(((double)totalServiceTime/totalPacketsArrived)));
		System.out.println("Total packets dropped: "+packetsDropped);
		//CHANGE LATER
		return routers.size();
	}
	
	/**
	 * this method simulates a networks and outputs its results
	 * @param routers, router list to traverse
	 * @param maxBufferSize,  determines if router has met its capacity
	 * @return int, index of smallest size router if alll routers full returns -1
	 */

	public static int sendPacketTo(List<Router> routers, int maxBufferSize) throws Exception {
		if(routers == null)
			throw new Exception("List is empty");
		//holds index of smallest none empty list
		int smallest = 0;
		boolean allFull = false;
		//the the smallest size that doesnt go above the maxBufferSize gets returned
		for(int i = 1;i<routers.size();i++) {
			if(routers.get(i).size()<routers.get(smallest).size()) {
				if(routers.get(i).size()<maxBufferSize) {
				smallest = i;	
				allFull =false;
				}
			}
			else if(routers.get(i).size()==routers.get(smallest).size()) {
				if(routers.get(i).size()==maxBufferSize)
				allFull = true;
			}
		}
		if(allFull==true)
			return -1;
		
		return smallest;
	}
	
	//MAIN METHOD
	public static void main(String[] args) throws Exception {
		String decision = "";
		while(decision!="n") {
			List<Router> routers = new ArrayList<>();
			Router dispatcher = new Router();
			
			System.out.println("Starting simulator...");
			Scanner input = new Scanner(System.in);
			System.out.println("Enter the number of Intermediate routers: ");
			int numIntRouters = input.nextInt();
			System.out.println("Enter the arrival probability of a packet: ");
			double arrivalProb = input.nextDouble();
			System.out.println("Enter the maximum buffer size of a router: ");
			int maxBufferSize = input.nextInt();
			System.out.println("Enter the minimum size of a packet: ");
			int minPacketSize=input.nextInt();
			System.out.println("Enter the maximum size of a packet: ");
			int maxPacketSize=input.nextInt();
			System.out.println("Enter the simulation duration: ");
			int duration = input.nextInt();
			
			
			//run simulation
			Simulator sim = new Simulator();
			sim.simulate(dispatcher,numIntRouters, routers, arrivalProb, maxBufferSize, duration,minPacketSize,maxPacketSize);
			
			System.out.println();
			System.out.println("Do you want to try another simulation? (y/n): ");
			decision = input.next().toLowerCase();
			if(decision.equals("n"))
				System.exit(1);
		}
		
		
	}
	

}
