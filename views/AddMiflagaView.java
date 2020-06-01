package views;

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

public class AddMiflagaView implements ElectionViewable {
	private Vector<ElectionUiListenable> allListenables;
	private Button exitButton = new Button("submit");
	private Label nameLabel = new Label("Name:");
	private Label standPointLabel = new Label("Stand point:");
	private TextField nameField = new TextField();
	private ComboBox<Miflaga.StandPoint> standPointBox;
	private Label errorLabel = new Label();

	public AddMiflagaView(Stage primaryStage) {
		primaryStage.setTitle("Add Miflaga");
		allListenables = new Vector<ElectionUiListenable>();
		standPointBox = new ComboBox<Miflaga.StandPoint>();
		GridPane gpMainGridPane = new GridPane();
		errorLabel.setTextFill(Color.RED);
		errorLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		errorLabel.setVisible(false);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (nameField.getText().isEmpty()) {
					errorLabel.setText("Please enter name");
					errorLabel.setVisible(true);
					return;
				}
				if (standPointBox.getValue() == null) {
					errorLabel.setText("please select your stand point");
					errorLabel.setVisible(true);
					return;
				}
				for (int i = 0; i < 3; i++) {
					if (Miflaga.StandPoint.values()[i].equals(standPointBox.getValue()))
						if (allListenables.get(0).viewAddedMiflaga(nameField.getText(), i + 1)) {
							allListenables.get(0).viewChoose(0);
							primaryStage.close();
						}
				}
			}
		});
		for (int i = 0; i < 3; i++) {
			standPointBox.getItems().add(Miflaga.StandPoint.values()[i]);
		}
		nameLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		nameField.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		standPointLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		standPointBox.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		gpMainGridPane.setHgap(7);
		gpMainGridPane.setVgap(7);
		gpMainGridPane.setPadding(new Insets(10));
		gpMainGridPane.add(nameLabel, 0, 0);
		gpMainGridPane.add(nameField, 2, 0);
		gpMainGridPane.add(standPointLabel, 0, 1);
		gpMainGridPane.add(standPointBox, 2, 1);
		gpMainGridPane.add(exitButton, 2, 3);
		gpMainGridPane.add(errorLabel, 2, 4);
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
