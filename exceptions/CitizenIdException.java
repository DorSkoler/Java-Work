package exceptions;

public class CitizenIdException extends Exception {
	
	public CitizenIdException() {
		super("Id must be 9 digits");
	}

}
