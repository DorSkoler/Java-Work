package mainAndModel;

import java.time.LocalDate;
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
import views.MainView;

public class ProgramElection extends Application {
	private Vector<ElectionListenable> allElectionListenables;
	private Messageable ui;
	private ElectionRound round;
	private MainView mainView;

	public static void main(String[] args) {
		launch(args);
	}

	public ProgramElection() {
		allElectionListenables = new Vector<ElectionListenable>();
		ui = new GraphicalUI();
		round = new ElectionRound();
		mainView = new MainView();
		new Controller(mainView, this);
		addHardCoded();
	}

	public void addListener(ElectionListenable l) {
		allElectionListenables.add(l);
	}

	public String showAllCitizen() {
		return round.printCitizen();
	}

	public void setElectionDate(int month, int year) {
		round.setMonth(month);
		round.setYear(year);
	}

	public String showAllKalfis() {
		return round.printKalfis();
	}

	public String showAllMiflagot() {
		return round.printMiflagot();
	}

	public String showResults() {
		return round.electionResult();
	}

	public boolean addCitizen(String name, String id, int yearOfBirth, int days) {
		int result = 0;
		Citizen tempCitizen;
		try {
			tempCitizen = new Citizen(name, id, yearOfBirth);
			if (days < -1)
				tempCitizen = new CoronaCitizen(tempCitizen, days);
			result = round.addCitizen(tempCitizen, null);
		} catch (CitizenIdException e) {
			ui.showMessage(e.getMessage());
			return false;
		} catch (Exception e) {
			ui.showMessage(e.getMessage());
			return false;
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
			round.addMiflaga("KacholLavan", 1, LocalDate.of(1973, 12, 13));
			round.addMiflaga("Likud", 2, LocalDate.of(2019, 2, 21));
			round.addMiflaga("HaaretzShelanu", 3, LocalDate.of(2005, 7, 5));
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
			for (ElectionListenable l : allElectionListenables) {
				l.modelUpdatedMiflagot(new Miflaga("Likud", 2, LocalDate.of(1973, 12, 13)));
				l.modelUpdatedMiflagot(new Miflaga("KacholLavan", 1, LocalDate.of(2019, 2, 21)));
				l.modelUpdatedMiflagot(new Miflaga("HaaretzShelanu", 3, LocalDate.of(2005, 7, 5)));
			}
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

	public boolean addMiflaga(String name, int standPoint, LocalDate dateOfCreation) {
		try {
			if (round.addMiflaga(name, standPoint, dateOfCreation)) {
				ui.showMessage("Added successfully");
				for (ElectionListenable l : allElectionListenables)
					l.modelUpdatedMiflagot(new Miflaga(name, standPoint, dateOfCreation));
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
			mainView.showMe();
			break;
		case 1:
			round.doPrimaries();
			round.doElection();
			ui.showMessage("Election done!");
			break;
		case 2:
			ui.showMessage("See you in a few months");
			break;
		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		electionMenu(0);
	}
}