package ui;

import java.util.Scanner;
import interfaces.Messageable;

public class ConsoleUI implements Messageable {
	private Scanner s = new Scanner(System.in);

	public void showMessage(String msg) {
		System.out.println("\n" + msg);
	}
	
	public String getString(String msg) {
		if (msg != null)
			System.out.println("\n" + msg);
		return s.next();
	}
	
	public int getInteger(String msg) {
		if (msg != null)
			System.out.println("\n" + msg);
		return s.nextInt();
	}
	
	public String getNextLine(String msg) {
		s.nextLine();
		if (msg != null)
			System.out.println("\n" + msg);
		return s.nextLine();
	}
}
