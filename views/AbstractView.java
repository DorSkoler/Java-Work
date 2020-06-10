package views;

import interfaces.ElectionUiListenable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AbstractView {

	protected VBox vBox = new VBox();
	private ToolBar toolBar = new ToolBar();
	protected Button exitButton = new Button("Back To Main Menu");
	protected Stage primaryStage;
	
	public AbstractView() {
		primaryStage = new Stage();
		primaryStage.initStyle(StageStyle.UNDECORATED);
		vBox.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		exitButton.setStyle("-fx-font: 12px \"MS Reference Sans Serif\"");
		vBox.getChildren().add(toolBar);
		toolBar.getItems().add(exitButton);
		vBox.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, null, null)));;
		Scene scene = new Scene(vBox, 450, 250);
		primaryStage.setScene(scene);
	}

	public void showMe() {
		primaryStage.show();
	}
	
}
