

/*
 * This method throws an exception if the directory is full
 * */
public class FullDirectoryException extends Exception {
	
	public FullDirectoryException(String message) {
		super(message);
	}
}
