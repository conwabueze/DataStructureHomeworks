

/*
 * This class represents A Directory or File that will be represented in the tree
 */
public class DirectoryNode {
	
	//MEMBER VARIABLES
	private DirectoryNode left;
	private DirectoryNode middle;
	private DirectoryNode right;
	private String name;
	private boolean isFile;
	private boolean foundCursor;
	
	//CONSTRUCTORS
	public DirectoryNode() {
		
	}
	
	public DirectoryNode(String name) {
		this.name = name;
	}
	
	public DirectoryNode(String name, boolean isFile) {
		this.name = name;
		this.isFile = isFile;
	}

	//METHODS
	public void addChild(DirectoryNode newChild) throws FullDirectoryException, NotADirectoryException{
		

			//if directory is full(All children are occupied)
			if(left!=null && middle!=null && right!=null)
				throw new FullDirectoryException("Directory is full");
			//THROW OTHER DIRECTORY HERE
			if(left==null)
				left = newChild;
			else if(middle==null)
				middle = newChild;
			else if(right==null)
				right = newChild;
		
		
	}
//	public void addDirectoryNode(DirectoryNode newChild) throws FullDirectoryException{
//		//if directory is full(All children are occupied)
//		if(left!=null && middle!=null && right!=null)
//			throw new FullDirectoryException("Directory is full");
//		//THROW OTHER DIRECTORY HERE
//		if(left==null)
//			left = newChild;
//		else if(middle==null)
//			middle = newChild;
//		else if(right==null)
//			right = newChild;
//		
//	}
	
//	public DirectoryNode changeDirectory(String name, DirectoryNode node) throws NotADirectoryException{
//		
////		if(node.getName().equals(name)) {
////			if(node.isFile()==true) {
////				throw new NotADirectoryException("This is not a directory");
////			}
////			return node;
////		}
////			
////		
////		if(left!=null)
////			node.getLeft().changeDirectory(name, node.getLeft());
////		
////		else if(middle!=null)
////			node.getMiddle().changeDirectory(name, node.getMiddle());
////		
////		else if(right!=null)
////			node.getRight().changeDirectory(name, node.getRight());
//		
//		return null;
//		
//				
//	}
	
	
	///GETTERS AND SETTERS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DirectoryNode getLeft() {
		return left;
	}

	public DirectoryNode getMiddle() {
		return middle;
	}

	public DirectoryNode getRight() {
		return right;
	}

	public boolean isFile() {
		return isFile;
	}

	
//	public boolean isFile() {
//		//if node has no children return true cuz is a file
//		if(left==null && middle==null && right==null) {
//			return true;
//		}
//		//else return false  cuz directory
//		return false;
//	}
//	
	
	
	
	
}
