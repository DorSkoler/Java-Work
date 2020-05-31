package model;

import java.time.LocalDate;
import java.util.Vector;
import exceptions.CitizenIdException;

public class Miflaga implements Comparable<Miflaga> {

	private enum StandPoint {
		right, left, center
	};

	private String name;
	private StandPoint standPoint;
	private LocalDate dateOfCreation;
	private Vector<Citizen> candidates;
	private int numOfVotes;

	public Miflaga(String name, int standPoint) {
		this.name = name;
		this.standPoint = StandPoint.values()[standPoint - 1]; // main input are 1,2,3
		this.dateOfCreation = LocalDate.now();
		candidates = new Vector<Citizen>();
	}

	public Miflaga(Miflaga other) throws CitizenIdException {
		name = other.name;
		standPoint = other.standPoint;
		dateOfCreation = LocalDate.of(other.dateOfCreation.getYear(), other.dateOfCreation.getMonth(),
				other.dateOfCreation.getDayOfMonth());
		numOfVotes = other.numOfVotes;
		candidates = new Vector<Citizen>();
		candidates.addAll(other.candidates);
	}

	public void addCandidate(Citizen candidate) {
		candidates.add(candidate);
	}

	public String getName() {
		return name;
	}

	public void setNumOfVotes(int num) {
		numOfVotes = num;
	}

	public int getNumOfVotes() {
		return numOfVotes;
	}

	public void doPrimaries() {
		Citizen temp;
		for (int i = 0; i < candidates.size(); i++) {
			temp = candidates.get(i);
			int j = (int) (Math.random() * candidates.size());
			candidates.set(i, candidates.get(j));
			candidates.set(j, temp);
		}
	}

	public void removeCandidate(String id) throws NumberFormatException, CitizenIdException {
		candidates.remove(new Citizen(null, id, 0));
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Miflaga)) {
			return false;
		}
		Miflaga m = (Miflaga) obj;
		return m.name.equals(name);
	}

	public String toString() {
		String print = name + " was created in: " + dateOfCreation + " , stand point: " + standPoint.toString()
				+ "\nCandidates are:\n";
		for (int i = 0; i < candidates.size(); i++) {
			print += (i + 1) + ". " + candidates.get(i).getName() + "\n";
		}
		return print;
	}

	@Override
	public int compareTo(Miflaga m) {
		if (numOfVotes > m.numOfVotes)
			return -1;
		if (numOfVotes < m.numOfVotes)
			return 1;
		return 0;
	}
}
