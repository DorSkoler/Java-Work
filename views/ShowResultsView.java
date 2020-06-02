package views;

import java.util.Vector;

import interfaces.ElectionUiListenable;
import interfaces.ElectionViewable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Miflaga;

public class ShowResultsView implements ElectionViewable {

	private Vector<ElectionUiListenable> allListenables;
	private PieChart pieChart = new PieChart();
	private VBox vBox = new VBox();
	private ToolBar toolBar = new ToolBar();
	private Button exitButton = new Button("Back To Main Menu");
	private Text allResults = new Text();
	private ScrollPane scrollPane = new ScrollPane();
	private SplitPane splitPane = new SplitPane();
	
	public ShowResultsView(Stage primaryStage) {
		primaryStage.setTitle("Results");
		allListenables = new Vector<ElectionUiListenable>();
		vBox.getChildren().add(toolBar);
		toolBar.getItems().add(exitButton);
		vBox.getChildren().add(splitPane);
		splitPane.getItems().add(pieChart);
		splitPane.getItems().add(scrollPane);
		scrollPane.setContent(allResults);
		vBox.setPadding(new Insets(8));
		vBox.setSpacing(8);
		scrollPane.setPadding(new Insets(10));
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				allListenables.get(0).viewChoose(0);
				primaryStage.close();
			}
			
		});
		Scene scene = new Scene(vBox, 800, 450);
		primaryStage.setScene(scene);
	}

	@Override
	public void registerListener(ElectionUiListenable l) {
		allListenables.add(l);
	}

	@Override
	public void updateMiflagot(Miflaga miflaga) {
		pieChart.getData().add(new PieChart.Data(miflaga.getName() , miflaga.getNumOfVotes()));;
	}
	
	public void updateChart() {
		allResults.setText(allListenables.get(0).viewAsksForResults());
		Vector<Miflaga> temp = allListenables.get(0).viewAsksMiflagot();
		for (int i = 0; i < temp.size(); i++) {
			pieChart.getData().set(i , new PieChart.Data(temp.get(i).getName(),temp.get(i).getNumOfVotes()));
		}
	}

}
