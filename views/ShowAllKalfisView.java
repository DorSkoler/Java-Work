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

public class ShowAllKalfisView {

	private Vector<ElectionUiListenable> allListenables;
	private ScrollPane kalfisPane;
	private Text allKalfis = new Text();
	private ToolBar toolBar = new ToolBar();
	private Button exitButton = new Button("Back To Main Menu");
	private VBox vBox = new VBox();
	private Stage primaryStage;
	
	public ShowAllKalfisView(ElectionUiListenable l) {
		primaryStage = new Stage();
		primaryStage.setTitle("All Kalfis");
		allListenables = new Vector<ElectionUiListenable>();
		allListenables.add(l);
		kalfisPane = new ScrollPane();
		vBox.getChildren().add(toolBar);
		vBox.setSpacing(10);
		exitButton.setStyle("-fx-font: 12px \"MS Reference Sans Serif\"");
		toolBar.getItems().add(exitButton);
		allKalfis.setStyle("-fx-font: 12px \"MS Reference Sans Serif\"");
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				allListenables.get(0).viewChoose(0);
				primaryStage.close();
			}	
		});
		kalfisPane.setContent(allKalfis);
		vBox.getChildren().add(kalfisPane);
		kalfisPane.fitToWidthProperty().set(true);
		kalfisPane.setPadding(new Insets(20));
		Scene scene = new Scene(vBox, 500, 600);
		primaryStage.setScene(scene);
	}

	public void showMe() {
		allKalfis.setText(allListenables.get(0).viewAsksForAllKalfis());
		primaryStage.show();
	}

}
