package application;

import application.presentation.Controller;
import application.presentation.GameField;
import javafx.application.Application;
import javafx.stage.Stage;

public class Chess_Application extends Application{

	public static void main(String[] args) {
		launch(args);
		
	}
	
	@Override	
	public void start(Stage primaryStage) {
		
		GameField gameField = new GameField(60);
		Controller controller = new Controller(gameField);
		
		
		
		
		try {
			
			primaryStage.setScene(controller.getGameField().getScene());
			primaryStage.setTitle("Chess");
			primaryStage.show();


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
