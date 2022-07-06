package FbExceptions;

public class NotFoundException extends Exception{
	
	public NotFoundException() {
		super("User not found");
		
	}
}
