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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Miflaga;

public class AddMiflagaView {

	private Vector<ElectionUiListenable> allListenables;
	private Button submitButton = new Button("submit");
	private Label nameLabel = new Label("Name:");
	private Label standPointLabel = new Label("Stand point:");
	private TextField nameField = new TextField();
	private ComboBox<Miflaga.StandPoint> standPointBox;
	private Label errorLabel = new Label();
	private VBox vBox = new VBox();
	private ToolBar toolBar = new ToolBar();
	private Button exitButton = new Button("Back To Main Menu");
	private Stage primaryStage;
	private DatePicker datePicker = new DatePicker();

	public AddMiflagaView(ElectionUiListenable l) {
		primaryStage = new Stage();
		primaryStage.setTitle("Add Miflaga");
		allListenables = new Vector<ElectionUiListenable>();
		allListenables.add(l);
		standPointBox = new ComboBox<Miflaga.StandPoint>();
		GridPane gpMainGridPane = new GridPane();
		errorLabel.setTextFill(Color.RED);
		errorLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		errorLabel.setVisible(false);
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (nameField.getText().isEmpty()) {
					errorLabel.setText("Please enter name");
					errorLabel.setVisible(true);
					return;
				}
				if (standPointBox.getValue() == null) {
					errorLabel.setText("Please select your stand point");
					errorLabel.setVisible(true);
					return;
				}
				if (datePicker.getValue() == null || (datePicker.getValue().compareTo(LocalDate.now()) > -1
						&& !LocalDate.now().equals(datePicker.getValue()))) {
					errorLabel.setText("Please select valid date");
					errorLabel.setVisible(true);
					return;
				}
				for (int i = 0; i < 3; i++) {
					if (Miflaga.StandPoint.values()[i].equals(standPointBox.getValue()))
						if (allListenables.get(0).viewAddedMiflaga(nameField.getText(), i + 1, datePicker.getValue())) {
							allListenables.get(0).viewChoose(0);
							nameField.clear();
							standPointBox.getSelectionModel().clearSelection();
							errorLabel.setVisible(false);
							datePicker.setValue(null);
							primaryStage.close();
						}
				}
			}
		});
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				nameField.clear();
				standPointBox.getSelectionModel().clearSelection();
				errorLabel.setVisible(false);
				datePicker.setValue(null);
				allListenables.get(0).viewChoose(0);
				primaryStage.close();
			}

		});
		for (int i = 0; i < 3; i++) {
			standPointBox.getItems().add(Miflaga.StandPoint.values()[i]);
		}
		vBox.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		exitButton.setStyle("-fx-font: 12px \"MS Reference Sans Serif\"");
		gpMainGridPane.setHgap(7);
		gpMainGridPane.setVgap(7);
		gpMainGridPane.setPadding(new Insets(10));
		gpMainGridPane.add(nameLabel, 0, 0);
		gpMainGridPane.add(nameField, 2, 0);
		gpMainGridPane.add(standPointLabel, 0, 1);
		gpMainGridPane.add(standPointBox, 2, 1);
		gpMainGridPane.add(submitButton, 2, 4);
		gpMainGridPane.add(errorLabel, 2, 5);
		gpMainGridPane.add(new Label("Date of creation:"), 0, 3);
		gpMainGridPane.add(datePicker, 2, 3);
		gpMainGridPane.setAlignment(Pos.CENTER);
		vBox.getChildren().add(toolBar);
		toolBar.getItems().add(exitButton);
		vBox.getChildren().add(gpMainGridPane);
		Scene scene = new Scene(vBox, 450, 250);
		primaryStage.setScene(scene);
	}

	public void showMe() {
		primaryStage.show();
	}

}
