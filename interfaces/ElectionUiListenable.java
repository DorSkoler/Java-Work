package interfaces;

public interface ElectionUiListenable {

	void viewChoose(int choice);
	boolean viewAddedKalfi(int selection, String address);
	boolean viewAddedCitizen(String name, String id, int yearOfBirth, int days);
}
