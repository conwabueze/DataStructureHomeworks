import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



/*
 * This class represents a router
 */

public class Router extends LinkedList<Packet>{
	
	//Router
	Queue<Packet> router = new LinkedList<>();
	ArrayList<Packet> tempList = new ArrayList<>();
	List<String> successfullyReachedList = new ArrayList<>();
	
	//CONSTRUCTOR
	public Router() {
		
	}
	public Router(Queue<Packet> router) {
		this.router = router;
	}
	
	/**
	 * inserts packet at rear of
	 * @param p, packet to insert
	 * 
	 */
	public void enqueue(Packet p) {
		router.add(p);
	}
	
	
	/**
	 * removes packet from top
	 * @return Packet, removed packet
	 */
	public Packet dequeue() {
		return router.remove();
	}
	
	/**
	 * looks at packet from top
	 * @return Packet, top packet
	 */
	public Packet peek() {
		return router.peek();
	}
	
	/*
	 * checks if routers isempty
	 * @return boolean
	 */
	public boolean isEmpty() {
		return router.size()==0;
	}
	
	/*
	 * checks size of list
	 * @return int,list size
	 */
	public int size() {
		return router.size();
	}
	
	/**
	 * this method decrements the timeDest of every top router in the routers queue
	 * @return int, time the removed packet arrived
	 */
	public int timeDestDecrement() {
		
		int queueSize = router.size();
		//put queue into arraylist
		//traverse arrayList decrement first packet time dest value by one
		int packetTimeArrived = -1;
		for(int i =0; i<queueSize;i++) {	
			tempList.add(router.remove());
			if(i==0)
			tempList.get(i).setItemToDest(tempList.get(i).getItemToDest()-1);
			if(tempList.get(0).getItemToDest()==0) {
				Packet packet = tempList.remove(0);
				packetTimeArrived = packet.getTimeArrive();
				String successToDest="Packet "+packet.getId()+ " has successfully reached its destination: +" +packet.getOrginalItemToDest();
				successfullyReachedList.add(successToDest);
				
			}
			
			//if item des is 0 remove from list
		}
		//put everything back in list
				for(int i =0; i<tempList.size();i++) {
					
					router.add(tempList.get(i));
				}
				
				//empty temp
				tempList = new ArrayList<>();
		
		//if value is 0 out it has reached its desitions
		return packetTimeArrived;
	}
	
	

	//GETTER/SETTER
	public List<String> getSuccessfullyReachedList() {
		return successfullyReachedList;
	}
	
	
	public void setSuccessfullyReachedList(List<String> successfullyReachedList) {
		this.successfullyReachedList = successfullyReachedList;
	}
	
	///TO STRING
	public String toString() {
		int queueSize = router.size();
		String rep = "{ ";
		//store all contents into arraylist and print out
		for(int i =0; i<queueSize;i++) {
			
			tempList.add(router.remove());
			rep += tempList.get(tempList.size()-1)+", ";
		}
		//put everything back in list
		for(int i =0; i<tempList.size();i++) {
			
			router.add(tempList.get(i));
		}
		
		//empty temp
		tempList = new ArrayList<>();
		return rep +"}";
		
		
	}
	
}
