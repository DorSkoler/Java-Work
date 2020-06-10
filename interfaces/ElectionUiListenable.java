package interfaces;

import java.time.LocalDate;
import java.util.Vector;
import model.Miflaga;

public interface ElectionUiListenable {

	void viewChoose(int choice);
	boolean viewAddedKalfi(int selection, String address);
	boolean viewAddedCitizen(String name, String id, int yearOfBirth, int days);
	boolean viewAddedMiflaga(String name, int standPoint, LocalDate dateOfCreation);
	boolean viewAddedCandidate(String name, String id, int yearOfBirth,int days, String miflaga);
	boolean viewAddedCandidateAlreadyExist(String id, String miflaga);
	String viewAsksForAllCitizens();
	String viewAsksForAllKalfis();
	String viewAsksForAllMiflagot();
	String viewAsksForResults();
	void viewSetElectionDate(int month, int year);
	Vector<Miflaga> viewAsksMiflagot();
	
}
