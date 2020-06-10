package views;

import java.util.Vector;
import interfaces.ElectionUiListenable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowAllCitizensView {

	private Vector<ElectionUiListenable> allListenables;
	private ScrollPane citizensPane;
	private Text allCitizens = new Text();
	private ToolBar toolBar = new ToolBar();
	private Button exitButton = new Button("Back To Main Menu");
	private VBox vBox = new VBox();
	private Stage primaryStage;
	
	public ShowAllCitizensView(ElectionUiListenable l) {
		primaryStage = new Stage();
		primaryStage.setTitle("All Citizens");
		allListenables = new Vector<ElectionUiListenable>();
		allListenables.add(l);
		citizensPane = new ScrollPane();
		vBox.getChildren().add(toolBar);
		vBox.setSpacing(10);
		exitButton.setStyle("-fx-font: 12px \"MS Reference Sans Serif\"");
		toolBar.getItems().add(exitButton);
		allCitizens.setStyle("-fx-font: 12px \"MS Reference Sans Serif\"");
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				allListenables.get(0).viewChoose(0);
				primaryStage.close();
			}
		});
		citizensPane.setContent(allCitizens);
		vBox.getChildren().add(citizensPane);
		citizensPane.fitToWidthProperty().set(true);
		citizensPane.setPadding(new Insets(20));
		Scene scene = new Scene(vBox, 510, 700);
		primaryStage.setScene(scene);
	}

	public void showMe() {
		allCitizens.setText(allListenables.get(0).viewAsksForAllCitizens());
		primaryStage.show();
	}
	
}
