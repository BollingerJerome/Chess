package application;

import application.domain.Board;
import application.domain.DomainController;
import application.presentation.Controller;
import application.presentation.GameField;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Chess_Application extends Application{

	public static void main(String[] args) {
		launch(args);
		
	}
	
	private Board board;
	private DomainController domainController;
	private Controller controller;
	private GameField gameField;

	
	
	@Override	
	public void start(Stage primaryStage) {
		
		this.board = new Board();
		this.domainController = new DomainController(board);
		this.controller = new Controller(domainController, gameField);
		this.gameField = new GameField(controller, 60);
		
		this.controller.setGameField(gameField);
		
		
		
		
		try {
			
			primaryStage.setScene(controller.getGameField().getScene());
			primaryStage.setTitle("Chess");
			primaryStage.show();


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
