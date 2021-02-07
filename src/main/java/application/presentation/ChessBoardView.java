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





	private void colorBoardTilesToNormal() {
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
			figureVisual[counter].addEventHandler(MouseEvent.MOUSE_CLICKED, figureClick);

			borderPane.getChildren().add(figureVisual[counter]);
			figureVisual[counter].getName().setMouseTransparent(true);
			borderPane.getChildren().add(figureVisual[counter].getName());
			counter++;
		}
	}



	private EventHandler<MouseEvent> getFigureEventHandler (){ 	//adding Eventhandler clicking on figure
		return new EventHandler<MouseEvent>() {		
			//@Override
			public void handle(MouseEvent event) {
				FigureVisual source = (FigureVisual) event.getSource();
				controller.getDomainController().getGameFlowController().clickOnFigure(source.getFigure());	
				update();
			}
		};
	};

	private EventHandler<MouseEvent> getTileEventHandler (){ 	//adding Eventhandler clicking on options
		return new EventHandler<MouseEvent>() {		
			//@Override
			public void handle(MouseEvent event) {
				Rectangle source = (Rectangle) event.getSource();
				Tile tile = null;
				for(int i = 0; i<8;i++) {
					for(int j = 0; j<8; j++) {
						if(chessField[i][j].equals(source)) {
							tile = controller.getDomainController().getGameFlowController().getBoard().getTile(i, j);
							controller.getDomainController().getGameFlowController().clickOnTile(tile);
							update();
							return;
						}
					}
				}
			}
		};
	};

	private void update() {
		colorBoardTilesToNormal();
		updateField();
		updateFigures();
	}

	private void updateField() {
		Board board = controller.getDomainController().getGameFlowController().getBoard();
		for(int i = 0; i<8;i++) {
			for(int j = 0; j<8; j++) {
				if(board.getTile(i, j).isYellow()) {
					chessField[i][j].setFill(Color.YELLOW);
				}
				else if(board.getTile(i, j).isRed()) {
					chessField[i][j].setFill(Color.RED);
				}
			}
		}
	}


	private void updateFigures() {
		double sideLength = controller.getDomainController().getGameFlowController().getBoard().getSideLength();
		for(FigureVisual figures : figureVisual) {
			if(!figures.getFigure().isAlive()) {
				borderPane.getChildren().remove(figures);
				borderPane.getChildren().remove(figures.getName());
			}
			else {
				figures.setX(figures.getFigure().getX()*sideLength);
				figures.setY(figures.getFigure().getY()*sideLength);
				figures.getName().setX(figures.getFigure().getX()*sideLength+sideLength*0.25);
				figures.getName().setY(figures.getFigure().getY()*sideLength+sideLength*0.5);
			}
		}
	}



	public Scene showScene() {
		this.addBoardTiles();
		figureSetup();
		return new Scene(this.borderPane);
	}


}
