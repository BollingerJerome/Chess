package application.presentation;

import application.domain.Board;
import application.domain.Tile;
import application.domain.FigureModels.Figure;
import application.domain.FigureModels.Figure_Bishop;
import application.domain.FigureModels.Figure_Rook;
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
		this.figureClick = getFigureEventHandler();
		this.tileClick = getTileEventHandler();
	}


	private BorderPane borderPane;
	private Controller controller;
	private Rectangle[][] chessField;
	private FigureVisual[] figureVisual;
	private EventHandler<MouseEvent> figureClick;
	private EventHandler<MouseEvent> tileClick;
	private FigureVisual last;


	public void updateFigureEvents() {
		boolean isWhiteTurn = controller.getDomainController().getTurnModel().isWhiteTurn();
		for(FigureVisual figures : figureVisual) {
			if(figures.getFigure().isWhite()) {
				if(isWhiteTurn) {
					figures.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
				}
				else {
					figures.removeEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
				}
			}
			else {
				if(isWhiteTurn) {
					figures.removeEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
				}
				else {
					figures.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
				}
			}
		}
	}


	public void colorBoardTilesToNormal() {
		Board board = controller.getDomainController().getGameFlowController().getBoard();
		Tile[][] tile = board.getTileField();
		for(int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				if(tile[i][j].isBlack()) {
					chessField[i][j].setFill(Color.BLACK);
				}
				else {
					chessField[i][j].setFill(Color.WHITE);
				}
				chessField[i][j].removeEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
			}
		}
	}

	public void addBoardTiles() {
		chessField = new Rectangle[8][8];
		Board board = controller.getDomainController().getGameFlowController().getBoard();
		Tile[][] tile = board.getTileField();
		double sideLength = tile[0][0].getSideLength();
		for(int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				chessField[i][j] = new Rectangle(tile[i][j].getX()*sideLength, tile[i][j].getY()*sideLength, sideLength, sideLength);
			}
		}
		colorBoardTilesToNormal();		
		for(int i = 0; i<64; i++) {
			chessField[i%8][i/8].addEventHandler(MouseEvent.MOUSE_CLICKED, tileClick);
			borderPane.getChildren().add(chessField[i%8][i/8]);
		}

	}

	public void figureSetup() {
		figureVisual = new FigureVisual[32];
		int counter = 0;
		double sideLength = controller.getDomainController().getGameFlowController().getBoard().getSideLength();
		for(Figure figures : controller.getDomainController().getGameFlowController().getFigures()) {
			figureVisual[counter] = new FigureVisual(figures.getX(), figures.getY(), sideLength, figures);

			if(figureVisual[counter].getFigure().isWhite()) {
				figureVisual[counter].setFill(Color.LIGHTGREEN);
			}
			else {
				figureVisual[counter].setFill(Color.DARKGREEN);
			}
			figureVisual[counter].setArcHeight(sideLength);
			figureVisual[counter].setArcWidth(sideLength);

			borderPane.getChildren().add(figureVisual[counter]);
			figureVisual[counter].getName().setMouseTransparent(true);
			borderPane.getChildren().add(figureVisual[counter].getName());
			counter++;
		}
		updateFigureEvents();
	}

	

	private EventHandler<MouseEvent> getFigureEventHandler (){ 	//adding Eventhandler clicking on figure
		return new EventHandler<MouseEvent>() {		
			//@Override
			public void handle(MouseEvent event) {
				controller.getDomainController().getGameFlowController().handling(event.getSource());	
			}
		};
	};

	private EventHandler<MouseEvent> getTileEventHandler (){ 	//adding Eventhandler clicking on options
		return new EventHandler<MouseEvent>() {		
			//@Override
			public void handle(MouseEvent event) {
				
			}
		};
	};



	public void updateFigures() {
		double sideLength = controller.getDomainController().getGameFlowController().getBoard().getSideLength();
		for(FigureVisual figures : figureVisual) {
			if(figures.getFigure().isAlive()) {
				figures.setX(figures.getFigure().getX()*sideLength);
				figures.setY(figures.getFigure().getY()*sideLength);
				figures.getName().setX(figures.getFigure().getX()*sideLength+sideLength*0.25);
				figures.getName().setY(figures.getFigure().getY()*sideLength+sideLength*0.5);
			}
			else {
				borderPane.getChildren().remove(figures);
				borderPane.getChildren().remove(figures.getName());
			}
		}
	}



	public void showPossiblePrey(Figure hunter) {
		for(FigureVisual figures : figureVisual) {
			if(figures.getFigure().isAlive() && hunter.canEat(figures.getFigure(), controller.getDomainController().getGameFlowController().getBoard().getOccupation())) {
				chessField[figures.getFigure().getX()][figures.getFigure().getY()].setFill(Color.RED);
				figures.addEventHandler(MouseEvent.MOUSE_CLICKED, getKilled);
			}
		}
	}
	
	public void removeEatingEventHandler() {
		for(FigureVisual figures : figureVisual) {
				figures.removeEventHandler(MouseEvent.MOUSE_CLICKED, getKilled);
			}
	}


	public Scene showScene() {
		this.addBoardTiles();
		figureSetup();
		return new Scene(this.borderPane);
	}


}
