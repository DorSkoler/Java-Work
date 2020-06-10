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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Miflaga;

public class MainView implements ElectionViewable {
	
	private Vector<ElectionUiListenable> allListenables;
	private Stage primaryStage;
	private AddKalfiView addKalfiView;
	private AddCitizenView addCitizenView;
	private AddMiflagaView addMiflagaView;
	private AddCandidateView addCandidateView;
	private ShowAllKalfisView showAllKalfisView;
	private ShowAllCitizensView showAllCitizensView;
	private ShowAllMiflagotView showAllMiflagotView;
	private ShowResultsView showResultsView;
	private VBox vBox = new VBox(); 
	
	public MainView() {
		primaryStage = new Stage();
		allListenables = new Vector<ElectionUiListenable>();
		Button addKalfiButton = new Button("Add Kalfi");
		Button addCitizenButton = new Button("Add Citizen");
		Button addMiflagaButton = new Button("Add Miflaga");
		Button addCandidateButton = new Button("Add Candidate");
		Button showAllKalfisButton = new Button("Show all Kalfis");
		Button showAllCitizensButton = new Button("Show all Citizens");
		Button showAllMiflagotButton = new Button("Show all Miflagot");
		Button startElectionButton = new Button("Start Elections");
		Button showResultsButton = new Button("Show Results");
		Button exitButton = new Button("EXIT");
		Label errorLabel = new Label();
		DatePicker electionDate = new DatePicker();
		GridPane gpMain = new GridPane();
		gpMain.setVisible(false);
		primaryStage.setTitle("Main Menu");
		showResultsButton.setDisable(true);
		vBox.getChildren().add(new Label("Date of Elections:"));
		vBox.getChildren().add(electionDate);
		vBox.getChildren().add(gpMain);
		vBox.getChildren().add(errorLabel);
		gpMain.add(addKalfiButton, 0, 0);
		gpMain.add(addCitizenButton, 0, 1);
		gpMain.add(addMiflagaButton, 0, 2);
		gpMain.add(addCandidateButton, 0, 3);
		gpMain.add(showAllKalfisButton, 2, 0);
		gpMain.add(showAllCitizensButton, 2, 1);
		gpMain.add(showAllMiflagotButton, 2, 2);
		gpMain.add(startElectionButton, 2, 3);
		gpMain.add(showResultsButton, 0, 4);
		gpMain.add(exitButton, 2, 4);

		addKalfiButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				addKalfiView.showMe();
				primaryStage.close();
			}
		});
		addCitizenButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				addCitizenView.showMe();
				primaryStage.close();
			}
		});
		addMiflagaButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				addMiflagaView.showMe();
				primaryStage.close();
			}
		});
		addCandidateButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				addCandidateView.showMe();
				primaryStage.close();
			}
		});
		showAllKalfisButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				showAllKalfisView.showMe();
				primaryStage.close();
			}
		});
		showAllCitizensButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				showAllCitizensView.showMe();
				primaryStage.close();
			}
		});
		showAllMiflagotButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				showAllMiflagotView.showMe();
				primaryStage.close();
			}
		});
		startElectionButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				allListenables.get(0).viewChoose(1);
				showResultsButton.setDisable(false);
			}
		});
		showResultsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				showResultsView.showMe();
				primaryStage.close();
			}
		});
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				allListenables.get(0).viewChoose(2);
				primaryStage.close();
			}
		});
		exitButton.setTextFill(Color.RED);
		gpMain.setHgap(7);
		gpMain.setVgap(7);
		vBox.setSpacing(7);
		vBox.setPadding(new Insets(7));
		gpMain.setAlignment(Pos.CENTER);
		vBox.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		addKalfiButton.setPrefSize(160, 50);
		addCitizenButton.setPrefSize(160, 50);
		addMiflagaButton.setPrefSize(160, 50);
		addCandidateButton.setPrefSize(160, 50);
		showAllKalfisButton.setPrefSize(160, 50);
		showAllCitizensButton.setPrefSize(160, 50);
		showAllMiflagotButton.setPrefSize(160, 50);
		exitButton.setPrefSize(160, 50);
		startElectionButton.setPrefSize(160, 50);
		showResultsButton.setPrefSize(160, 50);
		electionDate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (electionDate.getValue().compareTo(LocalDate.now()) >= 0
						&& !LocalDate.now().equals(electionDate.getValue())) {
					errorLabel.setText("Please select valid date");
					errorLabel.setTextFill(Color.RED);
					return;
				}
				gpMain.setVisible(true);
				allListenables.get(0).viewSetElectionDate(electionDate.getValue().getMonthValue(), electionDate.getValue().getYear());
				vBox.getChildren().remove(3);
				vBox.getChildren().remove(1);
				vBox.getChildren().remove(0);
			}
		});
		vBox.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vBox, 400, 350);
		primaryStage.setScene(scene);
	}

	@Override
	public void registerListener(ElectionUiListenable l) {
		allListenables.add(l);
		addKalfiView = new AddKalfiView(l);
		addCitizenView = new AddCitizenView(l);
		addMiflagaView = new AddMiflagaView(l);
		addCandidateView = new AddCandidateView(l);
		showAllKalfisView = new ShowAllKalfisView(l);
		showAllCitizensView = new ShowAllCitizensView(l);
		showAllMiflagotView = new ShowAllMiflagotView(l);
		showResultsView = new ShowResultsView(l);
	}

	@Override
	public void updateMiflagot(Miflaga miflaga) {
//		showAllMiflagotView.updateMiflagot(miflaga);
		addCandidateView.updateMiflagot(miflaga);
		showResultsView.updateMiflagot(miflaga);
	}
	
	public void showMe() {
		primaryStage.show();
	}

}
