

/*
 * This method throws an exception if the directory is not a directory
 * */
public class NotADirectoryException extends Exception {
	
	public NotADirectoryException(String message){
		super(message);
	}
}
