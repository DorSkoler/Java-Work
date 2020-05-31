package model;

import java.time.LocalDate;
import exceptions.CitizenIdException;
import model.Citizen;

public class Candidate extends Citizen {

	protected Miflaga miflaga;

	public Candidate(Citizen c, Miflaga miflaga) {
		super(c.name, c.id, c.yearOfBirth, c.kalfi);
		this.miflaga = miflaga;
	}
	
	public Candidate(Candidate other) throws CitizenIdException {
		super(other);
		miflaga = other.miflaga;
	}
	
	public Candidate(String name, String id, int yearOfBirth, Kalfi<? extends Citizen> kalfi) {
		super(name, id, yearOfBirth, kalfi);
	}

	public boolean setMiflaga(Miflaga m) {
		miflaga = m;
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
 	} 
	
	public Miflaga getMiflaga() {
		return miflaga;
	}

	@Override
	public String toString() {
		String str = this.getClass().getSimpleName() + "\nName: " + name + ", Id: " + id + ", Age: " + (LocalDate.now().getYear() - yearOfBirth);
		return str + "\nName of miflaga: " + miflaga.getName() + "\nVoting in kalfi number: " + kalfi.getNumOfKalfi();
	}
}
	
	
	
