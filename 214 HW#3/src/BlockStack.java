

/*
 * This class represents a stack for a code block
 */
public class BlockStack {
	private Node head;
	private int size;
	//saves the end of stack which tells the complexity;
	private Complexity blockComplexity;
	
	//GETTERS
	public Complexity getBlockComplexity() {
		return blockComplexity;
	}

	public Node getHead() {
		return head;
	}
	
	/**
	 * this method places a node CodeBlock on top of the stack
	 * 
	 * 
	 * @param block, CodeBlock want placed on top of the stack
	 * 
	 * 
	 */
	public void push(CodeBlock block) {
		Node newNode = new Node(block);
		if(head==null)
			head = newNode;
		else {
		newNode.setNext(head);
		head = newNode;
		}
		size++;
		
	}
	
	/**
	 * this method removes a CodeBlock from the top of stack
	 * 
	 * @return valueReturn, CodeBlock removed from the stack
	 */
	public CodeBlock pop() throws Exception {
		if(head==null)
			throw  new Exception("The stack is empty no list to pop");
		
		CodeBlock valueReturn = head.getBlock();
		head = head.getNext();
		size--;
		return valueReturn;
	}
	
	/**
	 * this method looks at the CodeBlock from the top of stack
	 * 
	 * 
	 */
	public CodeBlock peek() throws Exception{
		if(head == null)
			throw new Exception("The stack is empty nothing to peak at");
		return head.getBlock();
	}
	
	/**
	 * this method return the size of the stack
	 * @return size, size of the stack
	 */
	public int size() {
		return size;
	}
	
	/**
	 * this method checks if the stack is empty
	 * @return boolean, if stack is empty or not
	 */
	public boolean isEmpty() {
		return head==null;
	}
	
	/**
	 * prints the content of stack
	 * 
	 */
	public void printStack() {
		Node current = head;
		while(current != null) {
			
			System.out.println(current.getBlock()+" ");
			current=current.getNext();
		}
	}
	
	/**
	 * This methods gets the returns the worst complexity of the stack
	 * 
	 */
	public void getComplexity() {
		Node current = head;
		if(current.getNext()==null) {
			blockComplexity = current.getBlock().getHighestSubComplexity();
			//set bottom of stack back to default
			current.getBlock().setBlockComplexity(new Complexity(0,0));
			current.getBlock().setHighestSubComplexity(new Complexity(0,0));
		}
		else {
		while(current.getNext() != null) {
			//data on top complexity and complexity below that
			/*
			 * What we need to do is add the total complexity of the top stack node and then add it and replace it with the high sub complexity
			 * of the node below it
			 * 
			 */
			int[] topBlock = current.getBlock().getBlockComplexity().getComplexityValues();
			int[] topSubHigh = current.getBlock().getHighestSubComplexity().getComplexityValues();
			Complexity topTotal = new Complexity(topBlock[0]+topSubHigh[0],topBlock[1]+topSubHigh[1]);
			int[] topTotalValues = topTotal.getComplexityValues();
			int[] bottomSubHigh = current.getNext().getBlock().getHighestSubComplexity().getComplexityValues();
			Complexity topBottomAdd = new Complexity(topTotalValues[0]+bottomSubHigh[0],topTotalValues[1]+bottomSubHigh[1]);
			
			current.getNext().getBlock().setHighestSubComplexity(topBottomAdd);	
			System.out.println("Leaving block, updating parent block");
			System.out.println(current.getNext().getBlock()+"\n");
			
			//if where at the end save the top complexity if the complexity of the current block/nested block is bigger then the block of the previous
			
				
				if(blockComplexity==null)
				blockComplexity = current.getBlock().getHighestSubComplexity();
				else if(current.getNext().getNext()==null) {
					///getting array and calculating the sum of current greatest block/nested block complpexity
					int[] blockComplexityArr = blockComplexity.getComplexityValues();
					int complexityTotal = blockComplexityArr[0]+blockComplexityArr[1];
					
					///getting array of current block and getting sum
					int[] topBottomArr = topBottomAdd.getComplexityValues();
					int topBottomTotal = topBottomArr[0]+topBottomArr[1];
					///comparing if our current block complexity is greater than the complexity of the entire function
					//if greater replace 
					if(topBottomTotal>complexityTotal)
						blockComplexity=current.getNext().getBlock().getHighestSubComplexity();
					//once you got the high complexity of the block/nested block reset the bottom of the stack to get ready for 
					//getting the complexity of the new stack
						current.getNext().getBlock().setBlockComplexity(new Complexity(0,0));
						current.getNext().getBlock().setHighestSubComplexity(new Complexity(0,0));
					
				}
				
				
				
			
			current=current.getNext();
	}
	
	}
	}
	
	
	//Node inner class
	class Node{
		//Instance variables
		private CodeBlock block;
		private Node next;
		
		//CONSTRUCTORS
		public Node() {
			super();
		}
		
		public Node(CodeBlock block) {
			this.block = block;
			next=null;
		}
		
		//GETTERS AND SETTERS
		public CodeBlock getBlock() {
			return block;
		}

		public void setBlock(CodeBlock block) {
			this.block = block;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
		
		
			
	}
}
