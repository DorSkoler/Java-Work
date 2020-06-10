package views;

import java.time.LocalDate;
import java.util.Vector;
import interfaces.ElectionUiListenable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Miflaga;

public class AddCandidateView {
	
	private Vector<ElectionUiListenable> allListenables;
	private Button submitButton = new Button("submit");
	private Label existCitizenLabel = new Label("Citizen already exist?");
	private ToggleButton existButton = new ToggleButton("YES");
	private ToggleButton notExistButton = new ToggleButton("NO");
	private Label nameLabel = new Label("Name:");
	private TextField nameField = new TextField();
	private Label idLabel = new Label("ID:");
	private TextField idField = new TextField();
	private Label dateLabel = new Label("Year of birth:");
	private ComboBox<Integer> ageBox;
	private Label bidudLabel = new Label("Are you in bidud?");
	private ToggleButton bidud1Button = new ToggleButton("YES");
	private ToggleButton bidud2Button = new ToggleButton("NO");
	private Label daysLabel = new Label("How many days?");
	private TextField daysField = new TextField();
	private Label miflagaLabel = new Label("Miflaga");
	private ComboBox<String> miflagaBox;
	private Label errorLabel = new Label();
	private boolean isInBidud;
	private boolean isExist;
	private ToolBar toolBar = new ToolBar();
	private Button exitButton = new Button("Back To Main Menu");
	private VBox vBox = new VBox();
	private Stage primaryStage;
	
	public AddCandidateView(ElectionUiListenable l) {
		primaryStage = new Stage();
		allListenables = new Vector<ElectionUiListenable>();
		allListenables.add(l);
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
		submitButton.setVisible(false);
		dateLabel.setVisible(false);
		ageBox.setVisible(false);
		ToggleGroup group = new ToggleGroup();
		bidud1Button.setToggleGroup(group);
		bidud2Button.setToggleGroup(group);
		ToggleGroup group1 = new ToggleGroup();
		existButton.setToggleGroup(group1);
		notExistButton.setToggleGroup(group1);
		
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
				submitButton.setVisible(true);
				dateLabel.setVisible(false);
				ageBox.setVisible(false);
				daysField.setVisible(false);
				daysLabel.setVisible(false);
			}
		});
		notExistButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				isExist = false;
				submitButton.setVisible(false);
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
				submitButton.setVisible(true);
				daysField.setVisible(true);
				daysLabel.setVisible(true);
			}
		});
		bidud2Button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				isInBidud = false;
				submitButton.setVisible(true);
				daysField.setVisible(false);
				daysLabel.setVisible(false);
			}
		});
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
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
						clearView();
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
								clearView();
								group.selectToggle(null);
								group1.selectToggle(null);
								primaryStage.close();
							}
						} else {
							if (allListenables.get(0).viewAddedCandidate(nameField.getText(), idField.getText(),
									ageBox.getValue().intValue(), -1, miflagaBox.getValue())) {
								allListenables.get(0).viewChoose(0);
								clearView();
								group.selectToggle(null);
								group1.selectToggle(null);
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
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				clearView();
				allListenables.get(0).viewChoose(0);
				group.selectToggle(null);
				group1.selectToggle(null);
				primaryStage.close();
			}
			
		});
		vBox.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		exitButton.setStyle("-fx-font: 12px \"MS Reference Sans Serif\"");
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
		gpMainGridPane.add(submitButton, 2, 8);
		gpMainGridPane.add(errorLabel, 2, 9);
		gpMainGridPane.setAlignment(Pos.CENTER);
		vBox.getChildren().add(toolBar);
		toolBar.getItems().add(exitButton);
		vBox.getChildren().add(gpMainGridPane);
		Scene scene = new Scene(vBox, 500, 350);
		primaryStage.setScene(scene);
	}
	
	private void clearView() {
		nameField.clear();
		idField.clear();
		ageBox.getSelectionModel().clearSelection();
		miflagaBox.getSelectionModel().clearSelection();
		daysField.clear();
		daysField.setVisible(false);
		daysLabel.setVisible(false);
		submitButton.setVisible(false);
		idLabel.setVisible(false);
		idField.setVisible(false);
		miflagaLabel.setVisible(false);
		miflagaBox.setVisible(false);
		errorLabel.setVisible(false);
		submitButton.setVisible(false);
		dateLabel.setVisible(false);
		ageBox.setVisible(false);
		bidudLabel.setVisible(false);
		bidud1Button.setVisible(false);
		bidud2Button.setVisible(false);
		nameLabel.setVisible(false);
		nameField.setVisible(false);	
	}

	public void updateMiflagot(Miflaga miflaga) {
		miflagaBox.getItems().add(miflaga.getName());
	}

	public void showMe() {
		primaryStage.show();
	}

}
