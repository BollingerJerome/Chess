package application.presentation;

import application.domain.Board;
import application.domain.Tile;
import application.domain.FigureModels.Figure;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChessBoardView {

	public ChessBoardView(Controller controller) {
		this.controller = controller;
		this.borderPane = new BorderPane();
		this.eventHandler = getEventHandler();
	}

	
	private BorderPane borderPane;
	private Controller controller;
	private Rectangle[][] chessField;
	private FigureVisual[] figureVisual;
	private EventHandler<MouseEvent> eventHandler;
	
	public void addBoardTiles() {
		chessField = new Rectangle[8][8];
		Board board = controller.getDomainController().getGameFlowController().getBoard();
		Tile[][] tile = board.getTileField();
		double sideLength = tile[0][0].getSideLength();
		for(int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				chessField[i][j] = new Rectangle(tile[i][j].getX()*sideLength, tile[i][j].getY()*sideLength, sideLength, sideLength);
				if(tile[i][j].isBlack()) {
					chessField[i][j].setFill(Color.BLACK);
				}
				else {
					chessField[i][j].setFill(Color.WHITE);
				}
			}
		}
		
		for(int i = 0; i<64; i++) {
			borderPane.getChildren().add(chessField[i%8][i/8]);
		}
		
	}
	
	public void figureSetup() {
		figureVisual = new FigureVisual[32];
		int counter = 0;
		double sideLength = controller.getDomainController().getGameFlowController().getBoard().getSideLength();
		for(Figure figures : controller.getDomainController().getGameFlowController().getFigures()) {
			figureVisual[counter] = new FigureVisual(figures.getX(), figures.getY(), sideLength, figures);
			figureVisual[counter].setFill(Color.BLUE);
			figureVisual[counter].setArcHeight(sideLength);
			figureVisual[counter].setArcWidth(sideLength);
			figureVisual[counter].addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
			borderPane.getChildren().add(figureVisual[counter]);
			counter++;
		}
	}
	
	private EventHandler<MouseEvent> getEventHandler (){ 	//adding Eventhandler
		return new EventHandler<MouseEvent>() {		
			//@Override
			public void handle(MouseEvent event) {
				FigureVisual figureVisual = (FigureVisual) event.getSource();
				updateFigures();
			}
		};
	};
	
	
	public void updateFigures() {
		double sideLength = controller.getDomainController().getGameFlowController().getBoard().getSideLength();
		for(FigureVisual figures : figureVisual) {
			figures.setX(figures.getFigure().getX()*sideLength);
			figures.setY(figures.getFigure().getY()*sideLength);
		}
	}
	
	public Scene showScene() {
		this.addBoardTiles();
		figureSetup();
		return new Scene(this.borderPane);
	}
	
	
}
