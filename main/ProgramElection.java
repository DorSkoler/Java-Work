package main;

import javax.swing.JOptionPane;
import exceptions.CitizenAgeException;
import exceptions.CitizenIdException;
import exceptions.MismatchCharTypeException;
import exceptions.SelectionIntegerRangeException;
import interfaces.Messageable;
import manager.ElectionRound;
import model.Citizen;
import model.CoronaCitizen;
import ui.ConsoleUI;
import ui.GraphicalUI;

public class ProgramElection {

	public static void main(String[] args) throws CitizenIdException, CitizenAgeException {
		String answer = JOptionPane.showInputDialog("Enter choice:\n1 - GraphicalUI\n2 - ConsuleUI");
		Messageable ui;
		if (answer.equals("1")) {
			ui = new GraphicalUI();
		}
		else {
			ui = new ConsoleUI();
		}
		ElectionRound round1 = new ElectionRound(01, 2020);
		addHardCoded(round1);
		electionMenu(round1, ui);
	}

	public static Citizen makeCitizen(Messageable ui) throws CitizenIdException, CitizenAgeException {
		boolean done = false;
		Citizen motek = new Citizen(ui.getString("Enter name"),ui.getString("Enter id"), ui.getInteger("Enter year of birth"));
		while (!done) {
			try {
				String answer = ui.getString("Are you in bidud? (enter 'Y' or 'N')");
				if (!answer.equalsIgnoreCase("y") && !answer.equalsIgnoreCase("n"))
					throw new MismatchCharTypeException();
				if (answer.equalsIgnoreCase("y")) {
					motek = new CoronaCitizen(motek, ui.getInteger("How many days?"));
				}
				done = true;
			} catch (MismatchCharTypeException e) {
				ui.showMessage(e.getMessage());
			}
		}
		return motek;
	}

	public static void makeCandidate(ElectionRound round, Messageable ui) throws CitizenIdException, CitizenAgeException {
		String miflaga = ui.getString("Name of miflaga?");
		int k = round.addCitizen(makeCitizen(ui), miflaga);
		if (k == 1)
			ui.showMessage("Added successfully");
		if (k == 2)
			ui.showMessage("Not added, alredy exist citizen with this id");
		if (k == 3)
			ui.showMessage("Not added, there's no such miflaga");
	}

	private static void makeCandidateAlredyExist(ElectionRound round, String id, Messageable ui)
			throws CitizenIdException, CitizenAgeException {
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

	public static void addHardCoded(ElectionRound round) {
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
			for (int i = 0; i < 100; i++) {
				String str = "208942342";
				int d = Integer.parseInt(str) + i;
				str = Integer.toString(d);
				round.addCitizen(new Citizen("Dor", str, 1990), null);
			}
			for (int i = 0; i < 100; i++) {
				String str = "208942542";
				int d = Integer.parseInt(str) + i;
				str = Integer.toString(d);
				round.addCitizen(new Citizen("Dor", str, 2000), null);
			}
			for (int i = 0; i < 100; i++) {
				String str = "208942652";
				int d = Integer.parseInt(str) + i;
				str = Integer.toString(d);
				round.addCitizen(new CoronaCitizen(new Citizen("Dor", str, 1990), 3), null);
			}
			for (int i = 0; i < 100; i++) {
				String str = "208942762";
				int d = Integer.parseInt(str) + i;
				str = Integer.toString(d);
				round.addCitizen(new CoronaCitizen(new Citizen("Dor", str, 2000), 3), null);
			}
		} catch (CitizenIdException e) {
			System.out.println("error hard coded");
		} catch (CitizenAgeException e) {
			System.out.println("error hard coded");
		}
	}

	public static void electionMenu(ElectionRound round, Messageable ui) {
		int num = 0;
		boolean done = false;
		String str = "1  -  Add kalfi\n2  -  Add citizen\n3  -  Add miflaga\n4  -  Add candidate\n5  -  Show all kalfis\n6  -  Show all citizens\n7  -  Show all miflagot\n8  -  Start election\n9  -  Show results\n10 -  Exit program";
		while (num != 10) {
			num = ui.getInteger("Choose what to do:\n" + str);
			switch (num) {
			case 1:
				done = false;
				while (!done) {
					int selection = ui.getInteger(
							"enter which kalfi\n1 - Regular\n2 - Corona kalfi\n3 - Soldier kalfi\n4 - Corona Soldier kalfi");
					try {
						if (selection > 4 || selection < 1)
							throw new SelectionIntegerRangeException();
						if (round.addKalfi(ui.getNextLine("Enter adress of kalfi"), selection))
							ui.showMessage("Added successfully");
						else
							ui.showMessage("Not added");
						done = true;
					} catch (SelectionIntegerRangeException e) {
						ui.showMessage("\n" + e.getMessage() + "\n");
					}
				}
				break;
			case 2:
				done = false;
				while (!done) {
					try {
						if (round.addCitizen(makeCitizen(ui), null) == 1)
							ui.showMessage("Added successfully");
						else
							ui.showMessage("Not added, alredy exist citizen with this id");
						done = true;
					} catch (CitizenIdException e) {
						ui.showMessage(e.getMessage());
					} catch (CitizenAgeException e) {
						ui.showMessage(e.getMessage());
					} catch (NumberFormatException e) {
						ui.showMessage(e.getMessage() + ", enter 9 digits");
					}
				}
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
					} catch (CitizenAgeException e) {
						ui.showMessage(e.getMessage());
					}
				}
				break;
			case 4:
				done = false;
				while (!done) {
					String answer = ui
							.getString("Candidate alredy exist as citizen?\nIf Yes enter id\nIf not enter 'N'");
					try {
						if (answer.equalsIgnoreCase("n"))
							makeCandidate(round, ui);
						else
							makeCandidateAlredyExist(round, answer, ui);
						done = true;
					} catch (CitizenIdException e) {
						ui.showMessage(e.getMessage());
					} catch (CitizenAgeException e) {
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
}