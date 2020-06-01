package interfaces;

import java.util.Vector;
import model.Miflaga;

public interface ElectionUiListenable {

	void viewChoose(int choice);
	boolean viewAddedKalfi(int selection, String address);
	boolean viewAddedCitizen(String name, String id, int yearOfBirth, int days);
	boolean viewAddedMiflaga(String name, int standPoint);
	boolean viewAddedCandidate(String name, String id, int yearOfBirth,int days, String miflaga);
	boolean viewAddedCandidateAlreadyExist(String id, String miflaga);
	Vector<Miflaga> viewAsksMiflagot();
	
}
