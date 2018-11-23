import java.util.Arrays;
import java.util.List;


/*
 * This class represents a block a code block from a file
 */
public class CodeBlock {
	//INSTANCE VARIABLES
	
	//Array holding all block types keywords
	public final String[] BLOCK_TYPE = {
			"def",
			"for",
			"while",
			"if",
			"elif",
			"else"
	};
	
	//array holding only O(1) keywords
	public final String[] BLOCK_TYPE_O1 = {
			"def",
			"if",
			"elif",
			"else"
	};
	
	//array list holding all O(1) keywords
	public final List<String> O1_LIST= Arrays.asList(BLOCK_TYPE_O1);
	
	//array holding all keyword indices
	public final int[] BLOCK_INDICES = {0,1,2,3,4,5,};
	
	//MEMBER VARIBALES
	private String loopVariable;
	private String name;
	private Complexity blockComplexity;
	private Complexity highestSubComplexity;
	
	//CONSTRUCTOR
	public CodeBlock() {
		
	}
	public CodeBlock(String loopVariable, String name, Complexity blockComplexity, Complexity highestSubComplexity) {
		super();
		this.loopVariable = null;
		this.name = name;
		this.blockComplexity = blockComplexity;
		this.highestSubComplexity = highestSubComplexity;
	}
	
	//GETTERS AND SETTERS
	public String getLoopVariable() {
		return loopVariable;
	}
	
	public void setLoopVariable(String loopVariable) {
		this.loopVariable = loopVariable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Complexity getBlockComplexity() {
		return blockComplexity;
	}
	public void setBlockComplexity(Complexity blockComplexity) {
		this.blockComplexity = blockComplexity;
	}
	public Complexity getHighestSubComplexity() {
		return highestSubComplexity;
	}
	public void setHighestSubComplexity(Complexity highestSubComplexity) {
		this.highestSubComplexity = highestSubComplexity;
	}
	
	//TO STRING
	public String toString() {
		return "Block Complexity = " + blockComplexity.toString()  
				+ "    Highest Sub Complexity = " + highestSubComplexity.toString();
	}
}
