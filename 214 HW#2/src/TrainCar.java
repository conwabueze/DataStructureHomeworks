
public class TrainCar {
	//INSTANCE VARIABLES
	private double carLength;
	private double carWeight;
	private ProductLoad load;
	
	//CONSTRUCTOR
	public TrainCar(double carLength, double carWeight, ProductLoad load) {
		this.carLength = carLength;
		this.carWeight = carWeight;
		this.load = load;
	}
	
	//GETTERS AND SETTERS
	public ProductLoad getLoad() {
		return load;
	}

	public void setLoad(ProductLoad load) {
		this.load = load;
	}

	public double getCarLength() {
		return carLength;
	}

	public double getCarWeight() {
		return carWeight;
	}
	
	public boolean isEmpty() {
		return (load==null && carLength==0 && carWeight==0);
	}
	
	//TO STRING
	public String toString() {
		String info = String.format("%-15f%-14f%25s", carLength, carWeight,load.toString());
		return info;
//		return "Length: " + carLength + " Weight: " + carWeight + " " + load.toString(); 
	}
	

	

}
