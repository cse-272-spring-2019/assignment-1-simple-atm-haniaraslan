package atm;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage stage) {
		GUI gui = new GUI();
		gui.start(new Stage());
	}

	public static void main(String[] args) {
		launch(args);

	}
}
