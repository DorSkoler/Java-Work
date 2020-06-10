package views;

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
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Kalfi;

public class AddKalfiView extends AbstractView {

	private Vector<ElectionUiListenable> allListenables;
	private Button submitButton = new Button("submit");
	private ComboBox<String> kalfisBox;
	private TextField addressField = new TextField();
	private Label errorLabel = new Label();
	private Label typeLabel = new Label("Type of Kalfi:");
	private Label addressLabel = new Label("Address:");
	
	public AddKalfiView(ElectionUiListenable l) {
		super();
		primaryStage.setTitle("Add Kalfi");
		kalfisBox = new ComboBox<String>();
		allListenables = new Vector<ElectionUiListenable>();
		allListenables.add(l);
		GridPane gpMainGridPane = new GridPane();
		for (int i = 0; i < Kalfi.KalfiType.values().length; i++) {
			kalfisBox.getItems().add(Kalfi.KalfiType.values()[i].toString());
		}
		gpMainGridPane.add(errorLabel, 1, 7);
		errorLabel.setTextFill(Color.RED);
		errorLabel.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		errorLabel.setVisible(false);
		submitButton.setOnAction(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent arg0) {
				if (kalfisBox.getValue() == null) {
					errorLabel.setText("Please select type");
					errorLabel.setVisible(true);
					return;
				}
				if (addressField.getText().isEmpty()) {
					errorLabel.setText("Please enter address");
					errorLabel.setVisible(true);
					return;
				}
				if (allListenables.get(0).viewAddedKalfi(getIntegerType(), addressField.getText())) {
					allListenables.get(0).viewChoose(0);
					errorLabel.setVisible(false);
					addressField.clear();
					kalfisBox.getSelectionModel().clearSelection();
					primaryStage.close();
				}
			}
		});
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addressField.clear();
				kalfisBox.getSelectionModel().clearSelection();
				errorLabel.setVisible(false);
				allListenables.get(0).viewChoose(0);
				primaryStage.close();
			}
			
		});
		kalfisBox.setPromptText("Type");
		gpMainGridPane.add(kalfisBox, 1, 0);
		gpMainGridPane.add(typeLabel, 0, 0);
		gpMainGridPane.add(addressLabel, 0, 1);
		gpMainGridPane.add(addressField, 1, 1);
		gpMainGridPane.add(submitButton, 1, 2);
		gpMainGridPane.setVgap(7);
		gpMainGridPane.setHgap(7);
		gpMainGridPane.setPadding(new Insets(7));
		gpMainGridPane.setAlignment(Pos.CENTER);
		vBox.getChildren().add(gpMainGridPane);
	}

	private int getIntegerType() {
		if (kalfisBox.getValue().equals(Kalfi.KalfiType.Regular.toString()))
			return 1;
		if (kalfisBox.getValue().equals(Kalfi.KalfiType.Corona.toString()))
			return 2;
		if (kalfisBox.getValue().equals(Kalfi.KalfiType.Soldier.toString()))
			return 3;
		return 4;
	}

	public void showMe() {
		primaryStage.show();
	}
	
}
