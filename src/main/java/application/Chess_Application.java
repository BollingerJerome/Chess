package application;

import application.domain.DomainController;
import application.domain.GameFlowController;
import application.presentation.ChessBoardView;
import application.presentation.Controller;
import javafx.application.Application;
import javafx.stage.Stage;


//github transfer test
public class Chess_Application extends Application{

	public static void main(String[] args) {
		launch(args);
		
	}
	
	private DomainController domainController;
	private Controller controller;
	private GameFlowController gameFlowController;
	private ChessBoardView chessBoardView;
	
	
	@Override	
	public void start(Stage primaryStage) {
		
		this.gameFlowController = new GameFlowController();
		this.domainController = new DomainController(gameFlowController);
		this.controller = new Controller(chessBoardView, domainController);
		this.chessBoardView = new ChessBoardView(controller);
		
		controller.setChessBoardView(chessBoardView);
		chessBoardView.addBoardTiles();
		chessBoardView.figureSetup();
		
		
		try {
			
			primaryStage.setScene(controller.getChessBoardView().showScene());
			primaryStage.setTitle("Chess");
			primaryStage.show();


		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
