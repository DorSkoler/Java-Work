package model;

import java.time.LocalDate;
import exceptions.CitizenIdException;

public class Citizen {

	protected String name;
	protected String id;
	protected int yearOfBirth;
	protected Kalfi <?> kalfi;

	public Citizen(String name, String id, int yearOfBirth) throws CitizenIdException, NumberFormatException {
		this.name = name;
		setId(id);
		setYearOfBirth(yearOfBirth);
	}
	
	private void setYearOfBirth(int yearOfBirth2) {
		yearOfBirth = yearOfBirth2;
	}

	private void setId(String id2) throws CitizenIdException, NumberFormatException {
		if (id2.length() != 9) 			
			throw new CitizenIdException();
		Integer.parseInt(id2);	
		id = id2;	
	}

	public Citizen(String name, String id, int yearOfBirth, Kalfi<?> kalfi) {
		this.name = name;
		this.id = id;
		this.yearOfBirth = yearOfBirth;
		this.kalfi = kalfi;
	}
	
	public Citizen(Citizen other) throws CitizenIdException {
		this(other.name, other.id, other.yearOfBirth);
		kalfi = other.kalfi;
	}
	
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}


	public boolean setKalfi(Kalfi<?> kalfi) {
		this.kalfi = kalfi;
		return true;
	}
	
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	
	public int getAge() {
		return (LocalDate.now().getYear() - yearOfBirth);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Citizen)) {
			return false;
		}
		Citizen c = (Citizen)obj;
		return  c.id.equals(id);
 	} 

	@Override
	public String toString() {
		String str = this.getClass().getSimpleName() + "\nName: " + name + ", Id: " + id + ", Age: " + getAge();
		return str + "\nVoting in kalfi number: " + kalfi.getNumOfKalfi();
	}
}
