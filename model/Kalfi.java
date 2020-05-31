package model;

import java.util.Random;
import java.util.Vector;
import interfaces.Sickable;

public class Kalfi<T> {

	public enum KalfiType {Regular, Corona, Soldier, CoronaSoldier};

	public static int counter;
	private int numOfKalfi;
	private String adressOfKalfi;
	private Vector<T> voters;
	private Vector<Integer> numVotesEachMiflaga;
	private KalfiType type;

	public Kalfi(String adressOfKalfi, int type2) {
		numOfKalfi = ++counter;
		this.adressOfKalfi = adressOfKalfi;
		voters = new Vector<T>();
		numVotesEachMiflaga = new Vector<Integer>();
		type = KalfiType.values()[type2 - 1];
	}

	public Kalfi(Kalfi<T> other) {
		type = other.type;
		numOfKalfi = other.numOfKalfi;
		adressOfKalfi = other.adressOfKalfi;
		voters = new Vector<T>();
		voters.addAll(other.voters);
		numVotesEachMiflaga = new Vector<Integer>();
		numVotesEachMiflaga.addAll(other.numVotesEachMiflaga);
	}

	public String getType() {
		return type.toString();
	}

	private int sumVotes() {
		int sum = 0;
		for (int i = 0; i < numVotesEachMiflaga.size(); i++) {
			sum += numVotesEachMiflaga.get(i);
		}
		return sum;
	}

	protected double votingPercent() {
		double scale = Math.pow(10, 2);
		if (voters.size() != 0)
			return (Math.round((sumVotes() / (double) voters.size() * 100) * scale) / scale);
		return 0;
	}

	public int getNumOfKalfi() {
		return numOfKalfi;
	}

	public void doElection(int size) {
		numVotesEachMiflaga = new Vector<Integer>(size);
		for (int i = 0; i < numVotesEachMiflaga.capacity(); i++) {
			numVotesEachMiflaga.add(0);
		}
		for (int i = 0; i < voters.size(); i++) {
			Random random = new Random();
			boolean choise = random.nextBoolean();
			if (choise) {
				if (type.toString().equals(Kalfi.KalfiType.Corona.toString())) {
					if (((Sickable) voters.get(i)).isSuit()) {
						int num = (int) (Math.random() * size);
						numVotesEachMiflaga.set(num, numVotesEachMiflaga.get(num) + 1);
					}
				} else if (type.toString().equals(Kalfi.KalfiType.CoronaSoldier.toString())) {
					if (((CoronaSoldier) voters.get(i)).isSuit()) {
						int num = (int) (Math.random() * size);
						numVotesEachMiflaga.set(num, numVotesEachMiflaga.get(num) + 1);
					}
				} else {
					int num = (int) (Math.random() * size);
					numVotesEachMiflaga.set(num, numVotesEachMiflaga.get(num) + 1);
				}
			}
		}
	}

	public void addCitizenToKalfi(T citizen) {
		voters.add(citizen);
	}

	public int numVotesToMiflaga(int index) {
		if (index < 0 || index > numVotesEachMiflaga.size())
			return 0;
		return numVotesEachMiflaga.get(index);
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Kalfi)) {
			return false;
		}
		Kalfi<?> k = (Kalfi<?>) obj;
		return k.numOfKalfi == numOfKalfi;
	}

	@Override
	public String toString() {
		return type.name() + " Kalfi:\nNumber: " + numOfKalfi + ", adress: " + adressOfKalfi + ", number of voters: "
				+ voters.size() + ",\nPercentage vote: " + votingPercent() + "%, sum of votes: " + sumVotes();
	}
}
