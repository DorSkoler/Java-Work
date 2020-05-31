package controller;

import interfaces.ElectionListenable;
import interfaces.ElectionUiListenable;
import interfaces.ElectionViewable;
import mainModel.ProgramElection;

public class Controller implements ElectionListenable, ElectionUiListenable {
	
	private ProgramElection theModElection;
	private ElectionViewable theView;

	public Controller(ElectionViewable view, ProgramElection election) {
		theModElection = election;
		theView = view;
		theModElection.addListener(this);
		theView.registerListener(this);
	}

	@Override
	public void viewChoose(int choice) {
		theModElection.electionMenu(choice);
		
	}

	@Override
	public boolean viewAddedKalfi(int selection, String address) {
		return theModElection.addKalfi(selection, address);
	}

	@Override
	public boolean viewAddedCitizen(String name, String id, int yearOfBirth, int days) {
		return theModElection.addCitizen(name, id, yearOfBirth, days);
	}
	
}
