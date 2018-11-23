

/*
 * This class represents a complexity
 */
public class Complexity {
	private int nPower;
	private int  logPower;
	private int[] complexityValues;
	
	//CONSTRUCTORS
	public Complexity() {
		
	}
	public Complexity(int nPower, int logPower) {
		super();
		this.nPower = nPower;
		this.logPower = logPower;
		complexityValues= new int[]{nPower,logPower};		
	}
	
	//GETTERS AND SETTERS
	public int[] getComplexityValues() {
		return complexityValues;
	}
	
	public int getnPower() {
		return nPower;
	}

	public void setnPower(int nPower) {
		this.nPower = nPower;
	}

	public int getLogPower() {
		return logPower;
	}

	public void setLogPower(int logPower) {
		this.logPower = logPower;
	}
	
	//TO STRING
	@Override
	public String toString() {
		if(nPower==0 && logPower==0)
			return "O(1)";
		
		else if(nPower>0 && logPower==0){
			if(nPower==1)
				return "O(n)";
			else
				return "O(n^"+nPower+")";
		}
		
		else if(logPower>0 && nPower==0) {
			if(logPower == 1)
				return "O(log(n))";
			else
				return "O(log(n)^" + logPower+")";
		}
		
		else {
			if(logPower>0 && nPower>0) {
				if(logPower>1 && nPower==1)
					return "O(n * log(n^"+logPower+"))";
				else if(logPower==1 && nPower>1)
					return "O(n^"+nPower+ "* log(n))";
				else if(logPower==1 && nPower==1)
					return "O(n* log(n))";
				else
					return "O(n^"+nPower+"* log(n^"+logPower+"))";
			}
		}
			
			
		return "";
	}
	
	
	
	
	
	
}
