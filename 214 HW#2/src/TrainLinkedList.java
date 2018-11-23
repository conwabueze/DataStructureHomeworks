
public class TrainLinkedList {
	//INSTANCE VARIBALES
	private TrainCarNode head, tail, cursor;
	private int size, isDangerousCount;
	private double length, value, weight;
	private boolean isDangerous;
	
	//CONSTRUCTOR
	public TrainLinkedList() {
		head=null;
		tail=null;
		cursor = null;
		size=0;
		length=0;
		value=0;
		weight=0;
		isDangerous = false;
		isDangerousCount = 0;
	}
	
	/**
	 * this method calculates and keeps tracks of the trains size, length, weight, value,
	 * and finally if one of the carts in the train is dangerous
	 * 
	 * @param node, needed so we can add values of one car to the total value
	 * @param added, needed to know to add or deduct
	 * 
	 * 
	 */
	private void calculation(TrainCarNode node, boolean added){
		if(added) {
			size++;
			length += node.getCar().getCarLength();
			weight = weight + node.getCar().getCarWeight();
			value += node.getCar().getLoad().getValue();
			
			if(node.getCar().getLoad().isDangerous()) {
				isDangerous = true;
				isDangerousCount++;
			}
		}
		else {
			size--;
			length -= node.getCar().getCarLength();
			weight = weight - node.getCar().getCarWeight();
			value -= node.getCar().getLoad().getValue();
			
			if(node.getCar().getLoad().isDangerous()) {	
				isDangerousCount--;
				if(isDangerousCount==0) {
					isDangerous = false;
				}
			}
		}
		
	}
	
	
	/**
	 * returns trains size
	 * 
	 * @return int, size of car
	 */
	public int size() {
		return size;
	}

	/**
	 * returns trains length
	 * 
	 * @return int, length of car
	 */
	public double getLength() {
		return length;
	}

	/**
	 * returns trains value
	 * 
	 * @return int, value of car
	 */
	public double getValue() {
		TrainCarNode node= head;
		value = value + node.getCar().getLoad().getTotalLoadValue();
		return value;
	}
	
	/**
	 * returns trains weight
	 * 
	 * @return int, weight of car
	 */
	public double getWeight() {
		TrainCarNode node= head;
		weight = weight + node.getCar().getLoad().getTotalLoadWeight();
		return weight;
	}

	/**
	 * returns trains if car is dangerous or not
	 * 
	 * @return boolean, if car is dangerous or not
	 */
	public boolean isDangerous() {
		return isDangerous;
	}
	
	/**
	 * this method give the train car of the cursor allowing access to the cars data
	 
	 * @throws Exception, list is empty
	 * 
	 * @return TrainCar, return the TrainCar of the cursor
	 */
	public TrainCar getCursorData() throws Exception{
		if(head == null) {
			throw new Exception("List is Empty");
		}

			return cursor.getCar();
	}
	
	/**
	 * this method allows you to set car data to the cursor  
	 * @throws Exception, list is empty
	 * 
	 */
	public void setCursorData(TrainCar car) throws Exception {
		if(head == null) {
			throw new Exception("List is Empty");
		}
		else
		cursor.setCar(car);
	}
	
	/**
	 * this method moves the cursor forward to the next node
	 * @throws Exception, next node is null no way to move forward
	 * 
	 */
	public void cursorForward() throws Exception {
		if(head == null || tail == cursor) {
			throw new Exception("Cant move cursor forward");
		}
		else
			cursor = cursor.getNext();
	}
	
	/**
	 * this method moves the cursor backward to the previous node
	 * @throws Exception, previous node is null no way to move backwards
	 * 
	 */
	public void cursorBackward() throws Exception{
		if(head == null || head == cursor) {
			throw new Exception("Cant move cursor forward");
		}
		else
		cursor = cursor.getPrevious();
	}
	/**
	 * this method inserts a node after the cursor
	 * @param newCar, car that you want entered after the cursor
	 * @throws IllegalArgumentException, car you wanted entered is null
	 * 
	 */
	public void insertAfterCursor(TrainCar newCar) throws IllegalArgumentException {
		if(newCar == null)
			throw new IllegalArgumentException("TrainCar is null");
		
		TrainCarNode newNode = new TrainCarNode(newCar);
		calculation(newNode, true);
		//if list is empty
		if(head==null) {
			head = newNode;
			tail = newNode;
			cursor = newNode;
			
			
		}
		//only one node in list
		else if(head.getNext()==null) {
			cursor.setNext(newNode);
			newNode.setPrevious(cursor);
			tail = newNode;
			
			
		}
		//cursor is at tail
		else if(cursor==tail) {
			newNode.setPrevious(tail);
			cursor.setNext(newNode);
			tail = newNode;
			
		}
		//middle of list
		else { 
			newNode.setNext(cursor.getNext());
			cursor.getNext().setPrevious(newNode);
			newNode.setPrevious(cursor);
			cursor.setNext(newNode);
			
			
		}
	}
	
	/**
	 * this method removes the node at the cursor
	 * 
	 * @throws Exception, list is empty
	 * 
	 * @return TrainCar, removed trainCar
	 */

	public TrainCar removeCursor() throws Exception{
		
		
		//if list is empty 
		if(head==null) {
			throw new Exception("List is empty");
		}
		
		calculation(cursor, false);
		TrainCar car = cursor.getCar();
		
		//remove if there is only one node
		if(head.getNext()==null) {
			head = null;
			cursor = null;
			tail = null;
		}
		//deleting head
		else if(cursor==head) {
			TrainCarNode temp = cursor.getNext();
			cursor.getNext().setPrevious(null);
			cursor.setNext(null);
			cursor=temp;
			head=cursor;
		}
		//removing at end of the list
		else if(cursor == tail) {
			cursor = tail.getPrevious();
			tail.setPrevious(null);		
			cursor.setNext(null);
			tail = cursor;
		}
		
		//remove at middle of list
		else {
			TrainCarNode temp = cursor.getNext();
			cursor.getPrevious().setNext(cursor.getNext());
			cursor.getNext().setPrevious(cursor.getPrevious());
			cursor.setPrevious(null);
			cursor.setNext(null);
			cursor = temp;
		}
		
		return car;
		
	}
	/**
	 * this method find a product of a certain name
	 * 
	 * @param name, name to search for
	 * 
	 */
	
	public void findProduct(String name) {
		double weight = 0, value = 0;
		boolean isDangerous = false;
		TrainCarNode current = head;
		while(current != null) {
			if(current.getCar().getLoad().getName().equals(name)) {
				weight += current.getCar().getLoad().getWeight();
				value+=current.getCar().getLoad().getValue();
				if(isDangerous)
					isDangerous = true;
				
			}
			current = current.getNext();
		}
		ProductLoad load = new ProductLoad(name, weight, value, isDangerous);
		System.out.println(load);
	}
	
	/**
	 * this method removes all dangerous cars in the trian
	 * 
	 * @throws Exception, no node to move forward to
	 * 
	 */
	public void removeDangerousCars() throws Exception{
		if(head==null) {
			System.out.println("Train is safe");
		}
		else{
			cursor = head;
			while(cursor != null) {
				if(cursor.getCar().getLoad().isDangerous()==true) {
					removeCursor();				
					if(cursor.getNext() == null)
						break;
				}else {
					cursorForward();
				}
			}
			cursor = head;
			System.out.println("After removal(s) the cursor has return to the head");
		}
		
	}
	
	/**
	 * this method returns info on the train
	 * 
	 * @return String, train info
	 * 
	 */
	public String toString() {
		return "Size: " + size + " Length: " + length + " Weight: " + getWeight() + " Value: " + getValue() + " IsDangerous: " + isDangerous;
	}
	
	/**
	 * this method prints out all info on the train
	 * 
	 * 
	 */
	public void printManifest(){
		printHeader();
		TrainCarNode current = head;
		int index = 0;
		while(current != null) {
			System.out.println(index + "\t" + current);
			current = current.getNext();
			index++;
		}
	}
	/**
	 * this method prints out a header for the print manifest 
	 * 
	 * 
	 */
	private void printHeader() {
		String header1= String.format("%-37s%-21s", "CAR:", "LOAD: ");
		System.out.println(header1);
		String header2 = String.format("%-7s%-14s%-15s%-1s%-10s%-15s%-15s%-10s", "No.", "Length (m)", "Weight (t)", "|",
				"Name", "Weight (t)", "Value($)", "Dangerous");
		System.out.println(header2);
		String lineBreak = "====================================+======================================================";
		System.out.println(lineBreak);
		
	}
	
	
	

	
	
}
