
public class ProductLoad {
		//INSTANCE VARIBALES
		private String name;
		private double weight, value, totalLoadWeight, totalLoadValue;
		private boolean isDangerous;
		
		//CONSTRUCTOR
		public ProductLoad() {
			name = "";
			weight = 0;
			value = 0;
			isDangerous = false;  
		}
		
		//CONSTRUCTOR 
		public ProductLoad(String name, double weight, double value, boolean isDangerous) {
			this.name = name;
			this.weight = weight;
			this.value = value;
			this.isDangerous = isDangerous;
			totalLoadValue+=value;
			totalLoadWeight+=weight;
		}
		
		
		//GETTERS AND SETTERS
		
		public String getName() {
			return name;
		}

		public void setTotalLoadWeight(double totalLoadWeight) {
			this.totalLoadWeight = totalLoadWeight;
		}

		public void setTotalLoadValue(double totalLoadValue) {
			this.totalLoadValue = totalLoadValue;
		}

		public double getTotalLoadWeight() {
			return totalLoadWeight;
		}

		public double getTotalLoadValue() {
			return totalLoadValue;
		}

		public void setName(String name) {
			this.name = name;
		}

		public double getWeight() {
			return weight;
		}

		public void setWeight(double weight) {
			this.weight = weight;
		}

		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}

		public boolean isDangerous() {
			return isDangerous;
		}

		public void setDangerous(boolean isDangerous) {
			this.isDangerous = isDangerous;
		}
		
		// TO STRING
		@Override
		public String toString() {
			String info = String.format("%-12s%-15.2f%-15.2f%-10s", name, weight, value, isDangerous);
			return info; 
//			return "name:" + name + " weight: " + weight + " value: " + value + " isDangerous: "
//					+ isDangerous;
		}
		
		
		
		
}
