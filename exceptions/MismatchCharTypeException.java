package exceptions;

public class MismatchCharTypeException extends Exception {
	
	public MismatchCharTypeException() {
		super("Please enter 'Y' or 'N'");
	}

}
