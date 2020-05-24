package model;

import java.time.LocalDate;

import exceptions.CitizenAgeException;
import exceptions.CitizenIdException;
import interfaces.Sickable;

public class CoronaCandidate extends Candidate implements Sickable {
	
	private int days;
	private boolean suit;

	public CoronaCandidate(CoronaCandidate other) throws CitizenIdException, CitizenAgeException {
		super(other);
		days = other.days;
		suit = other.suit;
	}
	
	public CoronaCandidate(CoronaCitizen c, Miflaga miflaga) throws CitizenIdException, CitizenAgeException {
		super(c, miflaga);
		days = c.days;
		suit = c.suit;
	}
	
	public boolean isSuit() {
		return suit;
	}

	public Miflaga getMiflaga() {
		return miflaga;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	} 

	@Override
	public String toString() {
		String str = this.getClass().getSimpleName() + "\nName: " + name + "\nId: " + id + ", Age: " + (LocalDate.now().getYear() - yearOfBirth);
		return str + "\nName of miflaga: " + miflaga.getName() + "\nVoting in kalfi number: " + kalfi.getNumOfKalfi() + ", days in Bidud" + days ;
	}

	@Override
	public int getDays() {
		return days;
	}
}
