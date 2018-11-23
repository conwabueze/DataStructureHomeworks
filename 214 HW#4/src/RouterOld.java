

public class RouterOld {
	private int size;
	private Node head;
	private Node tail;
	
	public void enqueue(Packet p) throws Exception {
		Node packet = new Node(p);
		if(head==null) {
			head=packet;
			tail=packet;
			size++;
		}
		else {
			tail.setNext(packet);
			packet.setPrevious(tail);
			tail = packet;
		} 
	}
	
	public Packet dequeue() throws Exception {
		if(head==null)
			throw new Exception("List is Empty");
		Node removedNode = head;
		if(head.getNext()==null) {
			head=null;
			tail = null;
			size--;
		}
		else {
			head = head.getNext();
			removedNode.getNext().setPrevious(null);
			removedNode.setNext(null);
			
		}
		return removedNode.getPacket();
		
	}
	
	public boolean isEmpty() {
		return head==null;
	}
	
	public Packet peek() throws Exception {
		if(head==null)
			throw new Exception("List is Empty");
		return head.getPacket();
	}
	
	
	
	///NODE INNER CLASS
	private class Node{
		//Instance variables
				private Packet packet;
				private Node next;
				private Node previous;
				
				//CONSTRUCTORS
				public Node() {
					super();
				}
				
				public Node(Packet packet) {
					this.packet = packet;
				}
				
				//GETTERS AND SETTERS
				public Packet getPacket() {
					return packet;
				}

				public void setPacket(Packet packet) {
					this.packet = packet;
				}

				public Node getNext() {
					return next;
				}

				public void setNext(Node next) {
					this.next = next;
				}

				public Node getPrevious() {
					return previous;
				}

				public void setPrevious(Node previous) {
					this.previous = previous;
				}
				
				
				
	}
}
