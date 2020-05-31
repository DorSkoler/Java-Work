package model;

import java.util.Random;
import exceptions.CitizenIdException;
import interfaces.Sickable;

public class CoronaCitizen extends Citizen implements Sickable {
	
	protected int days;
	protected boolean suit;

	public CoronaCitizen(Citizen other, int days2) throws CitizenIdException {
		super(other);
		days = days2;
		Random r = new Random();
		suit = r.nextBoolean();
	}
	
	public CoronaCitizen(CoronaCitizen other) throws CitizenIdException {
		super(other);
		days = other.days;
		suit = other.suit;
	}
	
	public int getDays() {
		return days;
	}

	public boolean isSuit() {
		return suit;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
 	} 

	@Override
	public String toString() {
		return super.toString() + ", days in Bidud " + days; 
	}
}
