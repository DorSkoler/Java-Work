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
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import manager.ElectionRound;
import model.Citizen;
import model.Miflaga;

public class MainView implements ElectionViewable {
	private Vector<ElectionUiListenable> allListenables;
	private Vector<Button> allButtons;
	
	public MainView(Stage primaryStage) {
		allListenables = new Vector<ElectionUiListenable>();
		allButtons = new Vector<Button>();
		addButtons();
		primaryStage.setTitle("Main Menu");
		allButtons.get(8).setDisable(true);
		GridPane gpMainGridPane = new GridPane();
		gpMainGridPane.add(allButtons.get(0), 0, 0);
		gpMainGridPane.add(allButtons.get(1), 0, 1);
		gpMainGridPane.add(allButtons.get(2), 0, 2);
		gpMainGridPane.add(allButtons.get(3), 0, 3);
		gpMainGridPane.add(allButtons.get(4), 2, 0);
		gpMainGridPane.add(allButtons.get(5), 2, 1);
		gpMainGridPane.add(allButtons.get(6), 2, 2);
		gpMainGridPane.add(allButtons.get(7), 2, 3);
		gpMainGridPane.add(allButtons.get(8), 0, 4);
		gpMainGridPane.add(allButtons.get(9), 2, 4);

		allButtons.get(0).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				allListenables.get(0).viewChoose(1);
				primaryStage.close();
			}
		});
		allButtons.get(1).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				allListenables.get(0).viewChoose(2);
				primaryStage.close();
			}
		});
		allButtons.get(2).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				allListenables.get(0).viewChoose(3);
				primaryStage.close();
			}
		});
		allButtons.get(3).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				allListenables.get(0).viewChoose(4);
				primaryStage.close();
			}
		});
		allButtons.get(4).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				allListenables.get(0).viewChoose(5);
				primaryStage.close();
			}
		});
		allButtons.get(5).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				allListenables.get(0).viewChoose(6);
				primaryStage.close();
			}
		});
		allButtons.get(6).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				allListenables.get(0).viewChoose(7);
				primaryStage.close();
			}
		});
		allButtons.get(7).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				allListenables.get(0).viewChoose(8);
				allButtons.get(8).setDisable(false);
			}
		});
		allButtons.get(8).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				allListenables.get(0).viewChoose(9);
				primaryStage.close();
			}
		});
		allButtons.get(9).setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				allListenables.get(0).viewChoose(10);
				primaryStage.close();
			}
		});

		gpMainGridPane.setHgap(7);
		gpMainGridPane.setVgap(7);
		gpMainGridPane.setPadding(new Insets(7));
		gpMainGridPane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(gpMainGridPane, 400, 350);
		primaryStage.setScene(scene);
	}

	@Override
	public void registerListener(ElectionUiListenable l) {
		allListenables.add(l);
	}

	private void addButtons() {
		allButtons.add(new Button("Add Kalfi"));
		allButtons.add(new Button("Add Citizen"));
		allButtons.add(new Button("Add Miflaga"));
		allButtons.add(new Button("Add Candidate"));
		allButtons.add(new Button("Show all Kalfis"));
		allButtons.add(new Button("Show all Citizens"));
		allButtons.add(new Button("Show all Miflagot"));
		allButtons.add(new Button("Start Elections"));
		allButtons.add(new Button("Show Results"));
		allButtons.add(new Button("EXIT"));
		allButtons.get(9).setTextFill(Color.RED);
		for (int i = 0; i < allButtons.size(); i++) {
			allButtons.get(i).setPrefSize(160, 50);
			allButtons.get(i).setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		}
	}

	@Override
	public void updateMiflagot(Miflaga miflaga) {
		return;
	}

}
