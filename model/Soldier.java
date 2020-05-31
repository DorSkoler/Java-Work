package model;

import exceptions.CitizenIdException;

public class Soldier extends Citizen {
	
	protected boolean carryWeapon;
	
	public Soldier(Citizen other) throws CitizenIdException {
		super(other);
		if ((int)(Math.random()*2) == 0)
			carryWeapon = true;
		else
			carryWeapon = false;
	}
	
	public Soldier(Soldier other) {
		super(other.name, other.id, other.yearOfBirth, other.kalfi);
		carryWeapon = other.carryWeapon;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
 	} 

	@Override
	public String toString() {
		if (carryWeapon)
			return super.toString() + ", is carring a weapon beware";
		return super.toString() + ", Jobnik";
	}
}
