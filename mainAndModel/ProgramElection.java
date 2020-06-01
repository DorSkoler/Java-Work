package mainAndModel;

import java.util.Vector;

import controller.Controller;
import exceptions.CitizenIdException;
import interfaces.ElectionListenable;
import interfaces.Messageable;
import javafx.application.Application;
import javafx.stage.Stage;
import manager.ElectionRound;
import model.Citizen;
import model.CoronaCitizen;
import model.Miflaga;
import ui.GraphicalUI;
import views.AddCandidateView;
import views.AddCitizenView;
import views.AddKalfiView;
import views.AddMiflagaView;
import views.MainView;

public class ProgramElection extends Application {
	private Vector<ElectionListenable> allElectionListenables;
	private ElectionRound round = new ElectionRound(01, 2020);
	private Messageable ui;

	public static void main(String[] args) {
		launch(args);
	}

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

	public boolean addCandidate(String name, String id, int yearOfBirth, int days, String miflaga) {
		if (!addCitizen(name, id, yearOfBirth, days))
			return false;
		int k = 0;
		try {
			k = round.changeCitizenToCandidate(id, miflaga);
		} catch (CitizenIdException e) {
			ui.showMessage(e.getMessage());
		}
		if (k == 1)
			ui.showMessage("Added successfully");
		if (k == 2) {
			ui.showMessage("Not added, alredy exist citizen with this id");
			System.out.println("23");
			return false;
		}
		if (k == 3) {
			ui.showMessage("Not added, there's no such miflaga");
			return false;
		}
		return true;
	}

	public boolean makeCandidateAlredyExist(String id, String miflaga) {
		int k = 1;
		try {
			k = round.changeCitizenToCandidate(id, miflaga);
		} catch (CitizenIdException e) {
			ui.showMessage(e.getMessage());
		}
		if (k == 0) {
			ui.showMessage("Added successfully");
			return false;
		}
		if (k == -1) {
			ui.showMessage("Not added, there's no such citizen");
			return false;
		}
		if (k == -2) {
			ui.showMessage("Soldier can not be a Candidate");
			return false;
		}
		if (k == -3) {
			ui.showMessage("Not added, there's no such miflaga");
			return false;
		}
		return true;
	}

	public Vector<Miflaga> allMiflagot() {
		return round.getMiflagot();
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

	public boolean addMiflaga(String name, int standPoint) {
		try {
			if (round.addMiflaga(name, standPoint)) {
				ui.showMessage("Added successfully");
				return true;
			}
		} catch (CitizenIdException e) {
			ui.showMessage(e.getMessage());
		}
		ui.showMessage("Not added, alredy exist miflaga with that name");
		return false;
	}

	public void electionMenu(int choice) {
		switch (choice) {
		case 0:
			MainView view = new MainView(new Stage());
			Controller c0 = new Controller(view, this);
			break;
		case 1:
			AddKalfiView view1 = new AddKalfiView(new Stage());
			Controller c1 = new Controller(view1, this);
			break;
		case 2:
			AddCitizenView view2 = new AddCitizenView(new Stage());
			Controller c2 = new Controller(view2, this);
			break;
		case 3:
			AddMiflagaView view3 = new AddMiflagaView(new Stage());
			Controller c3 = new Controller(view3, this);
			break;
		case 4:
			AddCandidateView view4 = new AddCandidateView(new Stage());
			Controller c4 = new Controller(view4, this);
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

	@Override
	public void start(Stage primaryStage) throws Exception {
		electionMenu(0);
	}
}