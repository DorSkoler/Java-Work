package views;

import java.time.LocalDate;
import java.util.Vector;
import interfaces.ElectionUiListenable;
import interfaces.ElectionViewable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Miflaga;

public class AddCandidateView implements ElectionViewable {
	private Vector<ElectionUiListenable> allListenables;
	private Button exitButton = new Button("submit");
	private Label existCitizenLabel = new Label("Citizen already exist?");
	private Button existButton = new Button("YES");
	private Button notExistButton = new Button("NO");
	private Label nameLabel = new Label("Name:");
	private TextField nameField = new TextField();
	private Label idLabel = new Label("ID:");
	private TextField idField = new TextField();
	private Label dateLabel = new Label("Year of birth:");
	private ComboBox<Integer> ageBox;
	private Label bidudLabel = new Label("Are you in bidud?");
	private Button bidud1Button = new Button("YES");
	private Button bidud2Button = new Button("NO");
	private Label daysLabel = new Label("How many days?");
	private TextField daysField = new TextField();
	private Label miflagaLabel = new Label("Miflaga");
	private ComboBox<String> miflagaBox;
	private Label errorLabel = new Label();
	private boolean isInBidud;
	private boolean isExist;
	
	public AddCandidateView(Stage primaryStage) {
		allListenables = new Vector<ElectionUiListenable>();
		primaryStage.setTitle("Add Candidate");
		ageBox = new ComboBox<Integer>();
		miflagaBox = new ComboBox<String>();
		GridPane gpMainGridPane = new GridPane();
		errorLabel.setTextFill(Color.RED);
		errorLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		errorLabel.setVisible(false);
		nameLabel.setVisible(false);
		nameField.setVisible(false);
		idLabel.setVisible(false);
		idField.setVisible(false);
		bidudLabel.setVisible(false);
		bidud1Button.setVisible(false);
		bidud2Button.setVisible(false);
		daysField.setVisible(false);
		daysLabel.setVisible(false);
		miflagaLabel.setVisible(false);
		miflagaBox.setVisible(false);
		exitButton.setVisible(false);
		dateLabel.setVisible(false);
		ageBox.setVisible(false);
		
		existButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				isExist = true;
				idLabel.setVisible(true);
				idField.setVisible(true);
				miflagaLabel.setVisible(true);
				miflagaBox.setVisible(true);
				bidudLabel.setVisible(false);
				bidud1Button.setVisible(false);
				bidud2Button.setVisible(false);
				nameLabel.setVisible(false);
				nameField.setVisible(false);
				exitButton.setVisible(true);
			}
		});
		notExistButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				isExist = false;
				exitButton.setVisible(false);
				nameLabel.setVisible(true);
				nameField.setVisible(true);
				idLabel.setVisible(true);
				idField.setVisible(true);
				bidudLabel.setVisible(true);
				bidud1Button.setVisible(true);
				bidud2Button.setVisible(true);
				miflagaLabel.setVisible(true);
				miflagaBox.setVisible(true);
				dateLabel.setVisible(true);
				ageBox.setVisible(true);
			}
		});
		bidud1Button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				isInBidud = true;
				exitButton.setVisible(true);
				daysField.setVisible(true);
				daysLabel.setVisible(true);
			}
		});
		bidud2Button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				isInBidud = false;
				exitButton.setVisible(true);
				daysField.setVisible(false);
				daysLabel.setVisible(false);
			}
		});
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (isExist) {
					if (idField.getText().isEmpty()) {
						errorLabel.setText("Please enter id");
						errorLabel.setVisible(true);
						return;
					}
					if (miflagaBox.getValue() == null) {
						errorLabel.setText("Please select your miflaga");
						errorLabel.setVisible(true);
						return;
					}
					if (allListenables.get(0).viewAddedCandidateAlreadyExist(idField.getText(),
							miflagaBox.getValue())) {
						allListenables.get(0).viewChoose(0);
						primaryStage.close();
					}
				} else {
					if (nameField.getText().isEmpty()) {
						errorLabel.setText("Please enter name");
						errorLabel.setVisible(true);
						return;
					}
					if (idField.getText().isEmpty()) {
						errorLabel.setText("Please enter id");
						errorLabel.setVisible(true);
						return;
					}
					if (ageBox.getValue() == null) {
						errorLabel.setText("Please select year of birth");
						errorLabel.setVisible(true);
						return;
					}
					if (miflagaBox.getValue() == null) {
						errorLabel.setText("Please select your miflaga");
						errorLabel.setVisible(true);
						return;
					}
					if (isInBidud && daysField.getText().isEmpty()) {
						errorLabel.setText("Please enter days");
						errorLabel.setVisible(true);
						return;
					}
					try {
						if (isInBidud) {
							if (allListenables.get(0).viewAddedCandidate(nameField.getText(), idField.getText(),
									ageBox.getValue().intValue(), Integer.parseInt(daysField.getText()),
									miflagaBox.getValue())) {
								allListenables.get(0).viewChoose(0);
								primaryStage.close();
							}
						} else {
							if (allListenables.get(0).viewAddedCandidate(nameField.getText(), idField.getText(),
									ageBox.getValue().intValue(), 0, miflagaBox.getValue())) {
								allListenables.get(0).viewChoose(0);
								primaryStage.close();
							}
						}
					} catch (NumberFormatException e) {
						errorLabel.setText("Please enter only numbers in days");
						errorLabel.setVisible(true);
						return;
					}
				}
			}
		});
		for (int i = 0; i < 100; i++) {
			ageBox.getItems().add(LocalDate.now().getYear() - 22 - i);
		}
		existCitizenLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		nameLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		nameField.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		idLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		idField.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		dateLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		ageBox.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		bidudLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		bidud1Button.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		bidud2Button.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		existButton.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		notExistButton.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		daysLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		daysField.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		miflagaLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		miflagaBox.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		exitButton.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		errorLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		gpMainGridPane.setHgap(7);
		gpMainGridPane.setVgap(7);
		gpMainGridPane.setPadding(new Insets(10));
		gpMainGridPane.add(existCitizenLabel, 0, 0);
		gpMainGridPane.add(existButton, 1, 0);
		gpMainGridPane.add(notExistButton, 2, 0);
		gpMainGridPane.add(idLabel, 0, 1);
		gpMainGridPane.add(idField, 2, 1);
		gpMainGridPane.add(miflagaLabel, 0, 2);
		gpMainGridPane.add(miflagaBox, 2, 2);
		gpMainGridPane.add(nameLabel, 0, 3);
		gpMainGridPane.add(nameField, 2, 3);
		gpMainGridPane.add(bidudLabel, 0, 6);
		gpMainGridPane.add(bidud1Button, 1, 6);
		gpMainGridPane.add(bidud2Button, 2, 6);
		gpMainGridPane.add(dateLabel, 0, 5);
		gpMainGridPane.add(ageBox, 2, 5);
		gpMainGridPane.add(daysLabel, 0, 7);
		gpMainGridPane.add(daysField, 2, 7);
		gpMainGridPane.add(exitButton, 2, 8);
		gpMainGridPane.add(errorLabel, 2, 9);
		gpMainGridPane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gpMainGridPane, 500, 350);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void registerListener(ElectionUiListenable l) {
		allListenables.add(l);
		for (int i = 0; i < allListenables.get(0).viewAsksMiflagot().size(); i++) {
			miflagaBox.getItems().add(allListenables.get(0).viewAsksMiflagot().get(i).getName());
		}
	}

}
