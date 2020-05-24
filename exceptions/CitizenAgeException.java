package exceptions;

public class CitizenAgeException extends Exception {

	public CitizenAgeException() {
		super("You are too young to vote");
	}
}
