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
import views.ShowAllCitizensView;
import views.ShowAllKalfisView;
import views.ShowResultsView;
import views.ShowAllMiflagotView;

public class ProgramElection extends Application {
	private Vector<ElectionListenable> allElectionListenables;
	private ElectionRound round = new ElectionRound(01, 2020);
	private Messageable ui;
	private Stage s0;
	private Stage s1;
	private Stage s2;
	private Stage s3;
	private Stage s4;
	private Stage s5;
	private Stage s6;
	private Stage s7;
	private Stage s9;
	private ShowAllKalfisView view5;
	private ShowAllCitizensView view6;
	private ShowAllMiflagotView view7;
	private ShowResultsView view9;

	public static void main(String[] args) {
		launch(args);
	}

	public ProgramElection() {
		allElectionListenables = new Vector<ElectionListenable>();
		viewOpen();
		ui = new GraphicalUI();
		addHardCoded();
	}
	
	private void viewOpen() {
		s0 = new Stage();
		s1 = new Stage();
		s2 = new Stage();
		s3 = new Stage();
		s4 = new Stage();
		s5 = new Stage();
		s6 = new Stage();
		s7 = new Stage();
		s9 = new Stage();
		MainView view = new MainView(s0);
		Controller c0 = new Controller(view, this);
		AddKalfiView view1 = new AddKalfiView(s1);
		Controller c1 = new Controller(view1, this);
		AddCitizenView view2 = new AddCitizenView(s2);
		Controller c2 = new Controller(view2, this);
		AddMiflagaView view3 = new AddMiflagaView(s3);
		Controller c3 = new Controller(view3, this);
		AddCandidateView view4 = new AddCandidateView(s4);
		Controller c4 = new Controller(view4, this);
		view5 = new ShowAllKalfisView(s5);
		Controller c5 = new Controller(view5, this);
		view6 = new ShowAllCitizensView(s6);
		Controller c6 = new Controller(view6, this);
		view7 = new ShowAllMiflagotView(s7);
		Controller c7 = new Controller(view7, this);
		view9 = new ShowResultsView(s9);
		Controller c9 = new Controller(view9, this);
	}

	public void addListener(ElectionListenable l) {
		allElectionListenables.add(l);
	}

	public String showAllCitizen() {
		return round.printCitizen();
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
			for (ElectionListenable l : allElectionListenables) {
				l.modelUpdatedMiflagot(new Miflaga("Likud", 2));
				l.modelUpdatedMiflagot(new Miflaga("KacholLavan", 1));
				l.modelUpdatedMiflagot(new Miflaga("HaaretzShelanu", 3));
			}
			for (int i = 0; i < 1000; i++) {
				String str = "208942342";
				int d = Integer.parseInt(str) + i;
				str = Integer.toString(d);
				round.addCitizen(new Citizen("Dor", str, 1990), null);
			}
			for (int i = 0; i < 1000; i++) {
				String str = "208954542";
				int d = Integer.parseInt(str) + i;
				str = Integer.toString(d);
				round.addCitizen(new Citizen("Dor", str, 2000), null);
			}
			for (int i = 0; i < 1000; i++) {
				String str = "208965652";
				int d = Integer.parseInt(str) + i;
				str = Integer.toString(d);
				round.addCitizen(new CoronaCitizen(new Citizen("Dor", str, 1990), 3), null);
			}
			for (int i = 0; i < 1000; i++) {
				String str = "208977762";
				int d = Integer.parseInt(str) + i;
				str = Integer.toString(d);
				round.addCitizen(new CoronaCitizen(new Citizen("Dor", str, 2000), 3), null);
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

	public boolean addMiflaga(String name, int standPoint) {
		try {
			if (round.addMiflaga(name, standPoint)) {
				ui.showMessage("Added successfully");
				for (ElectionListenable l : allElectionListenables) 
					l.modelUpdatedMiflagot(new Miflaga(name, standPoint));
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
			s0.show();
			break;
		case 1:
			s1.show();
			break;
		case 2:
			s2.show();
			break;
		case 3:
			s3.show();
			break;
		case 4:
			s4.show();
			break;
		case 5:
			view5.setKalfis();
			s5.show();
			break;
		case 6:
			view6.setCitizens();
			s6.show();
			break;
		case 7:
			view7.setMiflagot();
			s7.show();
			break;
		case 8:
			round.doPrimaries();
			round.doElection();
			ui.showMessage("Election done!");
			break;
		case 9:
			view9.updateChart();
			s9.show();
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