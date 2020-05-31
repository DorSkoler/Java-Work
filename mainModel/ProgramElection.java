package mainModel;

import java.util.Vector;

import javax.swing.JOptionPane;

import controller.Controller;
import exceptions.CitizenIdException;
import exceptions.MismatchCharTypeException;
import exceptions.SelectionIntegerRangeException;
import interfaces.ElectionListenable;
import interfaces.Messageable;
import javafx.stage.Stage;
import manager.ElectionRound;
import model.Citizen;
import model.CoronaCitizen;
import ui.GraphicalUI;
import views.AddCitizenView;
import views.AddKalfiView;

public class ProgramElection {
	Vector<ElectionListenable> allElectionListenables;
	ElectionRound round = new ElectionRound(01, 2020);
	Messageable ui;

	public ProgramElection() {
		allElectionListenables = new Vector<ElectionListenable>();
		ui = new GraphicalUI();
		addHardCoded();
	}

	public void addListener(ElectionListenable l) {
		allElectionListenables.add(l);
	}

	public boolean addCitizen(String name, String id, int yearOfBirth, int days) {
		int result = 0;
		Citizen tempCitizen;
		try {
			tempCitizen = new Citizen(name, id, yearOfBirth);
			if (days != 0)
				tempCitizen = new CoronaCitizen(tempCitizen, days);
			result = round.addCitizen(tempCitizen, null);
		} catch (CitizenIdException e) {
			ui.showMessage(e.getMessage());
			return false;
		} catch (Exception e) {
			ui.showMessage(e.getMessage());
		}
		if (result == 1) {
			ui.showMessage("Added successfully");
			return true;
		}
		if (result == 2)
			ui.showMessage("Not added, alredy exist citizen with this id");
		return false;
	}

	private void makeCandidate() throws CitizenIdException {
		String miflaga = ui.getString("Name of miflaga?");
		int k = round.addCitizen(, miflaga);
		if (k == 1)
			ui.showMessage("Added successfully");
		if (k == 2)
			ui.showMessage("Not added, alredy exist citizen with this id");
		if (k == 3)
			ui.showMessage("Not added, there's no such miflaga");
	}

	private void makeCandidateAlredyExist(String id) throws CitizenIdException {
		String miflaga = ui.getString("Name of miflaga?");
		int k = round.changeCitizenToCandidate(id, miflaga);
		if (k == 0) {
			ui.showMessage("Added successfully");
			return;
		}
		if (k == -1) {
			ui.showMessage("Not added, there's no such citizen");
			return;
		}
		if (k == -2) {
			ui.showMessage("Soldier can not be a Candidate");
			return;
		}
		if (k == -3) {
			ui.showMessage("Not added, there's no such miflaga");
			return;
		}
	}

	private void addHardCoded() {
		try {
			round.addMiflaga("KacholLavan", 1);
			round.addMiflaga("Likud", 2);
			round.addMiflaga("HaaretzShelanu", 3);
			round.addKalfi("Halevi 2, Jerusalem", 1);
			round.addKalfi("Jualin 18, Raanana", 2);
			round.addKalfi("Segev 20, Tel Aviv", 3);
			round.addKalfi("shalom 56, Kiryat Ono", 4);
			round.addCitizen(new Citizen("Dor", "123456789", 1997), null);
			round.addCitizen(new Citizen("Ariel", "123456788", 1997), null);
			round.addCitizen(new Citizen("Dani", "123456787", 1990), null);
			round.addCitizen(new Citizen("Ziv", "123456767", 1994), null);
			round.addCitizen(new Citizen("Noa", "123456780", 2000), null);
			round.addCitizen(new Citizen("Benny", "012345678", 1970), "KacholLavan");
			round.addCitizen(new Citizen("Gabi", "123456785", 1967), "KacholLavan");
			round.addCitizen(new Citizen("Bibi", "123456567", 1966), "Likud");
			round.addCitizen(new Citizen("Miri", "987654321", 1968), "Likud");
			round.addCitizen(new Citizen("Erez", "987654322", 1958), "HaaretzShelanu");
			round.addCitizen(new Citizen("Israel", "987654323", 1960), "HaaretzShelanu");
//			for (int i = 0; i < 100; i++) {
//				String str = "208942342";
//				int d = Integer.parseInt(str) + i;
//				str = Integer.toString(d);
//				round.addCitizen(new Citizen("Dor", str, 1990), null);
//			}
//			for (int i = 0; i < 100; i++) {
//				String str = "208942542";
//				int d = Integer.parseInt(str) + i;
//				str = Integer.toString(d);
//				round.addCitizen(new Citizen("Dor", str, 2000), null);
//			}
//			for (int i = 0; i < 100; i++) {
//				String str = "208942652";
//				int d = Integer.parseInt(str) + i;
//				str = Integer.toString(d);
//				round.addCitizen(new CoronaCitizen(new Citizen("Dor", str, 1990), 3), null);
//			}
//			for (int i = 0; i < 100; i++) {
//				String str = "208942762";
//				int d = Integer.parseInt(str) + i;
//				str = Integer.toString(d);
//				round.addCitizen(new CoronaCitizen(new Citizen("Dor", str, 2000), 3), null);
//			}
		} catch (CitizenIdException e) {
			System.out.println("error hard coded");
		}
	}

	public boolean addKalfi(int selection, String address) {
		if (round.addKalfi(address, selection)) {
			ui.showMessage("Added successfully");
			return true;
		}
		ui.showMessage("Not added");
		return false;
	}

	public void electionMenu(int choice) {
		boolean done;
		switch (choice) {
		case 1:
			AddKalfiView view1 = new AddKalfiView(new Stage());
			ElectionListenable c1 = new Controller(view1, this);
			break;
		case 2:
			AddCitizenView view2 = new AddCitizenView(new Stage());
			ElectionListenable c2 = new Controller(view2, this);
			break;
		case 3:
			done = false;
			while (!done) {
				try {
					if (round.addMiflaga(ui.getString("Enter your miflaga details:\nname"),
							ui.getInteger("stand point:\n1-right\n2-left\n3-center")))
						ui.showMessage("Added successfully");
					else
						ui.showMessage("Not added, alredy exist miflaga with that name");
					done = true;
				} catch (CitizenIdException e) {
					ui.showMessage(e.getMessage()); // not expecting exception
				}
			}
			break;
		case 4:
			done = false;
			while (!done) {
				String answer = ui.getString("Candidate alredy exist as citizen?\nIf Yes enter id\nIf not enter 'N'");
				try {
					if (answer.equalsIgnoreCase("n"))
						makeCandidate();
					else
						makeCandidateAlredyExist(answer);
					done = true;
				} catch (CitizenIdException e) {
					ui.showMessage(e.getMessage());
				}
			}
			break;
		case 5:
			ui.showMessage("These are the kalfis:\n" + round.printKalfis());
			break;
		case 6:
			ui.showMessage("These are the Voters:\n" + round.printCitizen());
			break;
		case 7:
			ui.showMessage("These are the miflagot:\n" + round.printMiflagot());
			break;
		case 8:
			int choise = ui.getInteger("Choose what to start:\nPrimaries - 1\nElections - 2");
			if (choise == 1) {
				round.doPrimaries();
				ui.showMessage("Primaries done!");
			}
			if (choise == 2) {
				round.doElection();
				ui.showMessage("Election done!");
			}
			break;
		case 9:
			ui.showMessage(round.electionResult());
			break;
		case 10:
			ui.showMessage("See you in a few months");
			break;
		}
	}
}