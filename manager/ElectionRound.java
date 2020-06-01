package manager;

import java.util.Vector;
import exceptions.CitizenIdException;
import interfaces.Sickable;
import model.Candidate;
import model.Citizen;
import model.CoronaCandidate;
import model.CoronaCitizen;
import model.CoronaSoldier;
import model.Kalfi;
import model.Miflaga;
import model.SetClass;
import model.Soldier;

public class ElectionRound {

	private int month;
	private int year;
	private SetClass<Citizen> voters;
	private Vector<Miflaga> miflagot;
	private Vector<Kalfi<Citizen>> regularKalfis;
	private Vector<Kalfi<Sickable>> coronaKalfis;
	private Vector<Kalfi<Soldier>> soldierKalfis;
	private Vector<Kalfi<CoronaSoldier>> coronaSoldierKalfis;

	public ElectionRound(int month, int year) {
		voters = new SetClass<Citizen>();
		miflagot = new Vector<Miflaga>();
		regularKalfis = new Vector<Kalfi<Citizen>>();
		coronaKalfis = new Vector<Kalfi<Sickable>>();
		soldierKalfis = new Vector<Kalfi<Soldier>>();
		coronaSoldierKalfis = new Vector<Kalfi<CoronaSoldier>>();
		this.month = month;
		this.year = year;
	}

	public boolean addKalfi(String adress, int selection) {
		if (selection == 1) {
			regularKalfis.add(new Kalfi<Citizen>(adress, 1));
			return true;
		}
		if (selection == 2) {
			coronaKalfis.add(new Kalfi<Sickable>(adress, 2));
			return true;
		}
		if (selection == 3) {
			soldierKalfis.add(new Kalfi<Soldier>(adress, 3));
			return true;
		}
		if (selection == 4) {
			coronaSoldierKalfis.add(new Kalfi<CoronaSoldier>(adress, 4));
			return true;
		}
		return false;
	}

	public int changeCitizenToCandidate(String id, String name) throws CitizenIdException {
		String message = alredyExist(id);
		if (message.equals("NotExist"))
			return -1;
		if (message.equals("Soldier"))
			return -2;
		Miflaga m = getMiflagaByName(name);
		if (m == null)
			return -3;
		if (message.equals("AlreadyCandiate") && m != null) {
			removeCandidateFromMiflaga(id);
			message = alredyExist(id);
		}
		int index = Integer.parseInt(message);
		if (index > -1) {
			if (voters.get(index) instanceof CoronaCitizen) {
				voters.set(index, new CoronaCandidate((CoronaCitizen) voters.get(index), m));
				m.addCandidate((CoronaCandidate) voters.get(index));
				return 0;
			} else {
				voters.set(index, new Candidate(voters.get(index), m));
				m.addCandidate((Candidate) voters.get(index));
				return 0;
			}
		}
		return -3;
	}

	private void removeCandidateFromMiflaga(String id) throws CitizenIdException {
		int index = voters.getIndexOfObject(new Citizen(null, id, 0));
		if (index > -1) {
			if (voters.get(index) instanceof CoronaCandidate) {
				((CoronaCandidate) voters.get(index)).getMiflaga().removeCandidate(id);
				voters.set(index, new CoronaCitizen((CoronaCitizen) voters.get(index)));
			} else {
				((Candidate) voters.get(index)).getMiflaga().removeCandidate(id);
				voters.set(index, new Citizen((Citizen) voters.get(index)));
			}
		}
	}

	public int addCitizen(Citizen citizen, String name) throws CitizenIdException {
		if (citizen.getAge() <= 21) {
			if (citizen instanceof CoronaCitizen) {
				CoronaCitizen d = (CoronaCitizen) citizen;
				if (!voters.add(new CoronaSoldier(citizen, d.getDays())))
					return 2;
			} else if (!voters.add(new Soldier(citizen)))
				return 2;
		} else if (name == null) {
			if (citizen instanceof CoronaCitizen) {
				if (!voters.add(new CoronaCitizen((CoronaCitizen) citizen)))
					return 2;
			} else {
				if (!voters.add(citizen))
					return 2;
			}
		} else {
			Miflaga g = getMiflagaByName(name);
			if (g == null) {
				return 3;
			} else if (citizen instanceof CoronaCitizen) {
				if (!voters.add(new CoronaCandidate((CoronaCitizen) citizen, g)))
					return 2;
				g.addCandidate((CoronaCandidate) voters.getTop());
			} else {
				if (!voters.add(new Candidate(citizen, g)))
					return 2;
				g.addCandidate((Candidate) voters.getTop());
			}
		}
		putCitizenInKalfi();
		return 1;
	}

	private void putCitizenInKalfi() {
		Citizen d = voters.getTop();
		if (d instanceof CoronaSoldier) {
			putCitizenInKalfiHelper(d, coronaSoldierKalfis);
		} else if (d instanceof Soldier) {
			putCitizenInKalfiHelper(d, soldierKalfis);
		} else if (d instanceof Sickable) {
			putCitizenInKalfiHelper(d, coronaKalfis);
		} else {
			putCitizenInKalfiHelper(d, regularKalfis);
		}
	}
	
	private <T> void putCitizenInKalfiHelper(Citizen c, Vector<Kalfi<T>> kalfi) {
		int index = (int) (Math.random() * kalfi.size());
		kalfi.get(index).addCitizenToKalfi((T)c);
		c.setKalfi(kalfi.get(index));
	}

	public boolean addMiflaga(String name, int standpoint) throws CitizenIdException {
		for (int i = 0; i < miflagot.size(); i++) {
			if (miflagot.get(i).getName().equalsIgnoreCase(name))
				return false;
		}
		miflagot.add(new Miflaga(name, standpoint));
		return true;
	}

	public void doElection() {
		for (int i = 0; i < miflagot.size(); i++) {
			miflagot.get(i).setNumOfVotes(0);
		}
		doElectionHelper(regularKalfis);
		doElectionHelper(coronaKalfis);
		doElectionHelper(soldierKalfis);
		doElectionHelper(coronaSoldierKalfis);
	}

	private <T> void doElectionHelper(Vector<Kalfi<T>> kalfi) {
		for (int i = 0; i < kalfi.size(); i++) {
			kalfi.get(i).doElection(miflagot.size());
			for (int j = 0; j < miflagot.size(); j++) {
				int num = kalfi.get(i).numVotesToMiflaga(j) + miflagot.get(j).getNumOfVotes();
				miflagot.get(j).setNumOfVotes(num);
			}
		}
	}

	private Miflaga getMiflagaByName(String name) {
		for (int i = 0; i < miflagot.size(); i++) {
			if (miflagot.get(i).getName().equalsIgnoreCase(name)) {
				return miflagot.get(i);
			}
		}
		return null;
	}

	private <T> String printKalfisHelper(Vector<Kalfi<T>> kalfi) {
		String print = "";
		for (int i = 0; i < kalfi.size(); i++) {
			print += kalfi.get(i).toString() + "\n\n";
		}
		return print;
	}

	public String printKalfis() {
		return printKalfisHelper(regularKalfis) + printKalfisHelper(coronaKalfis) + printKalfisHelper(soldierKalfis)
				+ printKalfisHelper(coronaSoldierKalfis);
	}

	public String printCitizen() {
		return voters.toString() + "Number of voters: " + voters.size() + "\n";
	}

	public String printMiflagot() {
		String str = "";
		for (int i = 0; i < miflagot.size(); i++) {
			str += "\n" + miflagot.get(i).toString();
		}
		return str;
	}
	
	public Vector<Miflaga> getMiflagot(){
		return miflagot;
	}

	private String alredyExist(String id) {
		for (int i = 0; i < voters.size(); i++) {
			if (voters.get(i).getId().equals(id))
				if (voters.get(i) instanceof Candidate || voters.get(i) instanceof CoronaCandidate)
					return "AlreadyCandiate";
				else if (voters.get(i) instanceof Soldier)
					return "Soldier";
				else
					return Integer.toString(i);
		}
		return "NotExist";
	}

	private <T> String electionResultHelper(Vector<Kalfi<T>> kalfi, int i) {
		String print = "";
		for (int j = 0; j < kalfi.size(); j++) {
			print += "Votes from kalfi " + kalfi.get(j).getNumOfKalfi() + " - "
					+ kalfi.get(j).numVotesToMiflaga(i) + "\n";
		}
		return print;
	}

	public String electionResult()  {
		String print = "The Results of the elections on the date, " + month + "/" + year + " are:\n";
		for (int i = 0; i < miflagot.size(); i++) {
			print += "\n" + miflagot.get(i).getName() + ":\n";
			print += electionResultHelper(regularKalfis, i) + electionResultHelper(coronaKalfis, i)
					+ electionResultHelper(soldierKalfis, i) + electionResultHelper(coronaSoldierKalfis, i);
		}
		miflagot.sort(null);
		for (int i = 0; i < miflagot.size(); i++) {
			print += "\n" + miflagot.get(i).getName() + ": sum of votes - " + miflagot.get(i).getNumOfVotes() + "\n";
		}
		return print;
	}

	public void doPrimaries() {
		for (int i = 0; i < miflagot.size(); i++) {
			miflagot.get(i).doPrimaries();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ElectionRound))
			return false;
		ElectionRound d = (ElectionRound) obj;
		return month == d.month & year == d.year;
	}

}
