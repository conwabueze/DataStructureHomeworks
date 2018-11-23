
public class DummyBlockStack {
	private Node head;
	private int size;
	
	public void push(int data) {
		Node newNode = new Node(data);
		if(head==null)
			head = newNode;
		else {
		newNode.setNext(head);
		head = newNode;
		}
		size++;
		
	}
	
	public int pop() throws Exception {
		if(head==null)
			throw  new Exception("The stack is empty no list to pop");
		
		int valueReturn = head.getData();
		head = head.getNext();
		size--;
		return valueReturn;
	}
	
	public int peek() throws Exception{
		if(head == null)
			throw new Exception("The stack is empty nothing to peak at");
		return head.getData();
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return head==null;
	}
	
	public void printStack() {
		Node current = head;
		while(current != null) {
			
			System.out.print(current.getData()+" ");
			current=current.getNext();
		}
	}
	
	
	
	
	
	//Node inner class
	private class Node{
		private int data;
		private Complexity complex;
		private Node next;
		
		public Node() {
			super();
		}

		public Node(Complexity complex) {
			super();
			this.complex = complex;
		}

		public Node(int data) {
			super();
			this.data = data;
			next=null;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Complexity getComplex() {
			return complex;
		}

		public void setComplex(Complexity complex) {
			this.complex = complex;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		
		
		
		
		
		
		
		
	}

}
