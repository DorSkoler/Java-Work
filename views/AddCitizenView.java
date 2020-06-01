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

public class AddCitizenView implements ElectionViewable {

	private Vector<ElectionUiListenable> allListenables;
	private Button exitButton = new Button("submit");
	private Label nameLabel = new Label("Name:");
	private Label idLabel = new Label("ID:");
	private Label dateLabel = new Label("Year of birth:");
	private Label bidudLabel = new Label("Are you in bidud?");
	private Label daysLabel = new Label("How many days?");
	private TextField nameField = new TextField();
	private TextField idField = new TextField();
	private ComboBox<Integer> ageBox;
	private Button bidud1Button = new Button("YES");
	private Button bidud2Button = new Button("NO");
	private TextField daysField = new TextField();
	private Label errorLabel = new Label();
	private boolean isInBidud;

	public AddCitizenView(Stage primaryStage) {
		primaryStage.setTitle("Add Citizen");
		allListenables = new Vector<ElectionUiListenable>();
		ageBox = new ComboBox<Integer>();
		GridPane gpMainGridPane = new GridPane();
		errorLabel.setTextFill(Color.RED);
		errorLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		errorLabel.setVisible(false);
		daysField.setVisible(false);
		daysLabel.setVisible(false);
		exitButton.setVisible(false);
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
				if (isInBidud && daysField.getText().isEmpty()) {
					errorLabel.setText("Please enter days");
					errorLabel.setVisible(true);
					return;
				}
				try {
					if (isInBidud) {
						if (allListenables.get(0).viewAddedCitizen(nameField.getText(), idField.getText(),
								ageBox.getValue().intValue(), Integer.parseInt(daysField.getText()))) {
							allListenables.get(0).viewChoose(0);
							primaryStage.close();
						}
					} else {
						if (allListenables.get(0).viewAddedCitizen(nameField.getText(), idField.getText(),
								ageBox.getValue().intValue(), 0)) {
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
		});
		for (int i = 0; i < 100; i++) {
			ageBox.getItems().add(LocalDate.now().getYear() - 18 - i);
		}
		nameLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		idLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		dateLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		ageBox.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		bidudLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		bidud1Button.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		bidud2Button.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		daysLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		exitButton.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		nameField.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		idField.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		daysField.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		gpMainGridPane.setHgap(7);
		gpMainGridPane.setVgap(7);
		gpMainGridPane.setPadding(new Insets(10));
		gpMainGridPane.add(nameLabel, 0, 0);
		gpMainGridPane.add(nameField, 2, 0);
		gpMainGridPane.add(idLabel, 0, 1);
		gpMainGridPane.add(idField, 2, 1);
		gpMainGridPane.add(dateLabel, 0, 2);
		gpMainGridPane.add(ageBox, 2, 2);
		gpMainGridPane.add(bidudLabel, 0, 3);
		gpMainGridPane.add(bidud1Button, 1, 3);
		gpMainGridPane.add(bidud2Button, 2, 3);
		gpMainGridPane.add(daysLabel, 0, 4);
		gpMainGridPane.add(daysField, 2, 4);
		gpMainGridPane.add(exitButton, 2, 5);
		gpMainGridPane.add(errorLabel, 2, 6);
		gpMainGridPane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gpMainGridPane, 450, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void registerListener(ElectionUiListenable l) {
		allListenables.add(l);
	}
}
