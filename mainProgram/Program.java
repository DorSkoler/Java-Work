package mainProgram;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import mainModel.ProgramElection;
import views.MainView;

public class Program extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ProgramElection election = new ProgramElection();
		MainView view = new MainView(primaryStage);
		Controller controller = new Controller(view, election);
		
	}

}
