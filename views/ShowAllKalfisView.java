package views;

import java.util.Vector;
import interfaces.ElectionUiListenable;
import interfaces.ElectionViewable;
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
import model.Miflaga;

public class ShowAllKalfisView implements ElectionViewable {

	private Vector<ElectionUiListenable> allListenables;
	private ScrollPane kalfisPane;
	private Text allKalfis = new Text();
	private ToolBar toolBar = new ToolBar();
	private Button exitButton = new Button("Back To Main Menu");
	private VBox vBox = new VBox();
	
	public ShowAllKalfisView(Stage primaryStage) {
		primaryStage.setTitle("All Kalfis");
		allListenables = new Vector<ElectionUiListenable>();
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
	
	@Override
	public void registerListener(ElectionUiListenable l) {
		allListenables.add(l);
	}

	@Override
	public void updateMiflagot(Miflaga miflaga) {
		return;
	}
	
	public void setKalfis() {
		allKalfis.setText(allListenables.get(0).viewAsksForAllKalfis());
	}


}
