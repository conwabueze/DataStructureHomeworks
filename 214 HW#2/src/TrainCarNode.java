
public class TrainCarNode {
	
	//instance variables
	private TrainCarNode previous;
	private TrainCarNode next;
	private TrainCar car;
	
	//CONSTRUCTOR
	public TrainCarNode() {
		car = null;
		previous=null;
		next=null;
	}
	
	//CONSTRUCTOR
	public TrainCarNode(TrainCar car) {
		super();
		this.car = car;
	}
	
	
	//GETTERS AND SETTERS
	public TrainCarNode getPrevious() {
		return previous;
	}

	public void setPrevious(TrainCarNode previous) {
		this.previous = previous;
	}

	public TrainCarNode getNext() {
		return next;
	}

	public void setNext(TrainCarNode next) {
		this.next = next;
	}

	public TrainCar getCar() {
		return car;
	}

	public void setCar(TrainCar car) {
		this.car = car;
	}
	
	//TO STRING
	public String toString() {
		return car.toString();
	}
	
	

}
