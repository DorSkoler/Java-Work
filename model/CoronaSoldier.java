package model;

import java.util.Random;
import exceptions.CitizenIdException;

public class CoronaSoldier extends Soldier {
	
	private int days;
	private boolean suit;
	
	public CoronaSoldier(CoronaSoldier other) {
		super(other);
		days = other.days;
		suit = other.suit;
	}

	public CoronaSoldier(Citizen other,int days2) throws CitizenIdException {
		super(other);
		days = days2;
		Random r = new Random();
		suit = r.nextBoolean();
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
