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

public class AddCitizenView {

	private Vector<ElectionUiListenable> allListenables;
	private Button submitButton = new Button("submit");
	private Label nameLabel = new Label("Name:");
	private Label idLabel = new Label("ID:");
	private Label dateLabel = new Label("Year of birth:");
	private Label bidudLabel = new Label("Are you in bidud?");
	private Label daysLabel = new Label("How many days?");
	private TextField nameField = new TextField();
	private TextField idField = new TextField();
	private ComboBox<Integer> ageBox;
	private ToggleButton bidud1Button = new ToggleButton("YES");
	private ToggleButton bidud2Button = new ToggleButton("NO");
	private TextField daysField = new TextField();
	private Label errorLabel = new Label();
	private boolean isInBidud;
	private ToolBar toolBar = new ToolBar();
	private Button exitButton = new Button("Back To Main Menu");
	private VBox vBox = new VBox();
	private Stage primaryStage;

	public AddCitizenView(ElectionUiListenable l) {
		primaryStage = new Stage();
		primaryStage.setTitle("Add Citizen");
		exitButton.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		toolBar.getItems().add(exitButton);
		allListenables = new Vector<ElectionUiListenable>();
		allListenables.add(l);
		ageBox = new ComboBox<Integer>();
		GridPane gpMainGridPane = new GridPane();
		errorLabel.setTextFill(Color.RED);
		errorLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		errorLabel.setVisible(false);
		daysField.setVisible(false);
		daysLabel.setVisible(false);
		submitButton.setVisible(false);
		ToggleGroup group = new ToggleGroup();
		bidud1Button.setToggleGroup(group);
		bidud2Button.setToggleGroup(group);
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
							clearView();
							primaryStage.close();
						}
					} else {
						if (allListenables.get(0).viewAddedCitizen(nameField.getText(), idField.getText(),
								ageBox.getValue().intValue(), -1)) {
							allListenables.get(0).viewChoose(0);
							clearView();
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
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				clearView();
				errorLabel.setVisible(false);
				group.selectToggle(null);
				allListenables.get(0).viewChoose(0);
				primaryStage.close();
			}
			
		});
		for (int i = 0; i < 100; i++) {
			ageBox.getItems().add(LocalDate.now().getYear() - 18 - i);
		}
		vBox.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		exitButton.setStyle("-fx-font: 12px \"MS Reference Sans Serif\"");
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
		gpMainGridPane.add(submitButton, 2, 5);
		gpMainGridPane.add(errorLabel, 2, 6);
		vBox.getChildren().add(toolBar);
		vBox.getChildren().add(gpMainGridPane);
		gpMainGridPane.setAlignment(Pos.CENTER);
		vBox.setSpacing(7);
		Scene scene = new Scene(vBox, 450, 300);
		primaryStage.setScene(scene);
	}
	
	private void clearView() {
		nameField.clear();
		idField.clear();
		ageBox.getSelectionModel().clearSelection();
		daysField.clear();
		daysField.setVisible(false);
		daysLabel.setVisible(false);
		submitButton.setVisible(false);
		errorLabel.setVisible(false);
	}

	
	public void showMe() {
		primaryStage.show();
	}

}
