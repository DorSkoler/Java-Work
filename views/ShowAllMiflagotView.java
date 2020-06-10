package views;

import java.util.Vector;
import interfaces.ElectionUiListenable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Miflaga;

public class ShowAllMiflagotView {

	private Vector<ElectionUiListenable> allListenables;
	private ScrollPane miflagotPane;
	private Text allMiflagot = new Text();
	private ToolBar toolBar = new ToolBar();
	private Button exitButton = new Button("Back To Main Menu");
	private VBox vBox = new VBox();
	private Accordion accordion = new Accordion();
	private Stage primaryStage;

	public ShowAllMiflagotView(ElectionUiListenable l) {
		primaryStage = new Stage();
		primaryStage.setTitle("All Miflagot");
		allListenables = new Vector<ElectionUiListenable>();
		allListenables.add(l);
		miflagotPane = new ScrollPane();
		vBox.getChildren().add(toolBar);
		vBox.setSpacing(10);
		exitButton.setStyle("-fx-font: 12px \"MS Reference Sans Serif\"");
		toolBar.getItems().add(exitButton);
		allMiflagot.setStyle("-fx-font: 12px \"MS Reference Sans Serif\"");
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				allListenables.get(0).viewChoose(0);
				primaryStage.close();
			}

		});
		miflagotPane.setContent(allMiflagot);
		vBox.getChildren().add(miflagotPane);
		miflagotPane.setPadding(new Insets(20));
		accordion.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		Scene scene = new Scene(vBox, 450, 300);
		primaryStage.setScene(scene);
	}

	private void setMiflagot() {
		accordion = new Accordion();
		Vector<Miflaga> temp = allListenables.get(0).viewAsksMiflagot();
		for (int i = 0; i < temp.size(); i++) {
			accordion.getPanes().add(new TitledPane(temp.get(i).getName(), new Text(temp.get(i).toString())));
		}
		miflagotPane.setContent(accordion);
		accordion.setStyle("-fx-font: 14px \"MS Reference Sans Serif\"");
		accordion.setPadding(new Insets(7));
		miflagotPane.setPadding(new Insets(7));
	}

	public void showMe() {
		setMiflagot();
		primaryStage.show();
	}
}