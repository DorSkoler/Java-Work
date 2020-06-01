package controller;

import java.util.Vector;

import interfaces.ElectionListenable;
import interfaces.ElectionUiListenable;
import interfaces.ElectionViewable;
import mainAndModel.ProgramElection;
import model.Miflaga;

public class Controller implements ElectionListenable, ElectionUiListenable {

	private ProgramElection theModElection;
	private ElectionViewable theView;

	public Controller() {
		
	}
	
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

	@Override
	public boolean viewAddedMiflaga(String name, int standPoint) {
		return theModElection.addMiflaga(name, standPoint);
	}

	@Override
	public boolean viewAddedCandidate(String name, String id, int yearOfBirth, int days, String miflaga) {
		return theModElection.addCandidate(name, id, yearOfBirth, days, miflaga);
	}

	@Override
	public boolean viewAddedCandidateAlreadyExist(String id, String miflaga) {
		return theModElection.makeCandidateAlredyExist(id, miflaga);
	}
	
	@Override
	public Vector<Miflaga> viewAsksMiflagot() {
		return theModElection.allMiflagot();
	}

}
