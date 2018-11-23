

/*
 * This class represents a directory tree 
 */
public class DirectoryTree {
	
	///MEMBER VARIABLES
	private DirectoryNode root;
	private DirectoryNode cursor;
	private boolean foundCursor;
	
	//CONSTRUCTOR
	public DirectoryTree() {
		root = new DirectoryNode("root",false);
		cursor = root;
		foundCursor = false;
	}
	
	/**
	 * This method sets the cursor to the root
	 * 
	 */
	public void resetCursor() {
		cursor=root;
		foundCursor = false;
	}
	

	/**
	 * This method creates and assign a directory node to a open child spot in the cursor
	 * @param name, names the DirectoryNode that will be made
	 */
	public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException{
		DirectoryNode newChild = new DirectoryNode(name,false);
		if(root==null) {
			root = newChild;
			cursor = root;
		}
		else {
			//if cursor is a file throw exception
			if(cursor.isFile()) {
				throw new NotADirectoryException("This is not a Directory");
			}
			cursor.addChild(newChild);
		}
			
	}
	
	/**
	 * This method creates and assign a file node to a open child spot in the cursor
	 * @param name, names the file that will be made
	 */
	public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException{
		if(name.contains(" ") || name.contains("/"))
			throw new IllegalArgumentException("Name cannot contain a space or forward slash");
		
		if((cursor==root && root == null)||cursor.isFile())
			throw new NotADirectoryException("This is not a directory");
		
		DirectoryNode newChild = new DirectoryNode(name,true);
		
		
		
		cursor.addChild(newChild);
	}
	
	/**
	 * This method creates and assign a file node to a open child spot in the cursor
	 * @param name, names the file that will be made
	 */
	public void changeDirectory(String name) throws NotADirectoryException{
		if(cursor.getLeft().getName().equals(name) && cursor.getLeft().isFile()==false) {
			cursor = cursor.getLeft();
		}
		else if(cursor.getMiddle().getName().equals(name) && cursor.getMiddle().isFile()==false) {
			cursor = cursor.getMiddle();
		}
		else if(cursor.getRight().getName().equals(name) && cursor.getRight().isFile()==false) {
			cursor = cursor.getRight();
		}
		else {
			throw new NotADirectoryException("entered name is either a file or not found");
		}
		
		
	}
	
	/**
	 * This creates cookie crumbs from the root to the cursor
	 * @return String, the cookiecrumb
	 */
	public String presentWorkingDirectory() {
		StringBuilder str = new StringBuilder();
		//reset
		foundCursor=false;
		return presentWorkingDirectory(root, str).toString();
	}
	
	/**
	 * This method is a helper method for pwd that does all the work for getting a cookie crumb from the root to the cursor
	 * @param current, current not being worked on
	 * @param str, keeps track of final value
	 * @return StringBuilder, the cookiecrumb
	 */
	public StringBuilder presentWorkingDirectory(DirectoryNode current, StringBuilder str) {
		if(current==cursor) {
			foundCursor = true;
		}
		if(current!=cursor) {
			
				if(current.getLeft() != null) {
					presentWorkingDirectory(current.getLeft(),str);
				}
				
				if(foundCursor==true)
					return str.insert(0, current.getName() + "/");
				
				if(current.getMiddle() != null) {
					presentWorkingDirectory(current.getMiddle(),str);
				}
				
				if(foundCursor==true)
					return str.insert(0, current.getName() + "/");
				
				if(current.getRight() != null) {
					presentWorkingDirectory(current.getRight(),str);
				}
			
				
		}
		
		if(foundCursor==true) {
//			if(current==root) {
//				foundCursor = false;
//				return str.insert(0, current.getName() + "/");
//			}
			return str.insert(0, current.getName() + "/");
			//return str.append(current.getName() + "/");
			
		}
		
		return str.append("");
	}
	
	/**
	 * This method returns a String of all children of the cursor 
	 * @return String, the child list
	 */
	public String listDirectory() {
		String str = "";
		if(cursor.getLeft()!=null)
			str+=cursor.getLeft().getName()+" ";
		if(cursor.getMiddle()!=null)
			str+=cursor.getMiddle().getName()+" ";
		if(cursor.getRight()!=null)
			str+=cursor.getRight().getName();
		else
			return "Empty";
		
		return str;
	}
	
	/**
	 * This method prints a visual of the directory tree
	 */
	
	public void printDirectoryTree() {
		printDirectoryTree(cursor,"");
	}
	
	/**
	 * This method is a helper method for PDT where all the work is being done
	 * @param node, keeps track of node to be worked on
	 * @param indent, creates indent length 
	 */
	public void printDirectoryTree(DirectoryNode node, String indent) {
		if(node.isFile()) 
			System.out.println(indent+"- "+node.getName());
		
		else
			System.out.println(indent+"|- "+node.getName());
		
		if(node.getLeft()!=null)
			printDirectoryTree(node.getLeft(),indent+"  ");
		if(node.getMiddle()!=null)
			printDirectoryTree(node.getMiddle(),indent+"  ");
		if(node.getRight()!=null)
			printDirectoryTree(node.getRight(), indent + "  ");
	}
}
