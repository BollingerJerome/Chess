package application.presentation;

import application.domain.Board;
import application.domain.Tile;
import application.domain.FigureModels.Figure;
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
		this.eventHandler = getEventHandler();
		this.tileEeventHandler = getTileEventHandler();
	}


	private BorderPane borderPane;
	private Controller controller;
	private Rectangle[][] chessField;
	private FigureVisual[] figureVisual;
	private EventHandler<MouseEvent> eventHandler;
	private EventHandler<MouseEvent> tileEeventHandler;
	private FigureVisual last;

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
			chessField[i%8][i/8].addEventHandler(MouseEvent.MOUSE_CLICKED, tileEeventHandler);
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
				colorBoardTilesToNormal();
				FigureVisual figureVisual = (FigureVisual) event.getSource();
				last = figureVisual;
				showPossibleTurns(figureVisual.getFigure());
				updateFigures();

			}
		};
	};

	private EventHandler<MouseEvent> getTileEventHandler (){ 	//adding Eventhandler
		return new EventHandler<MouseEvent>() {		
			//@Override
			public void handle(MouseEvent event) {
				Rectangle tile = (Rectangle) event.getSource();
				for(int i = 0; i<64; i++) {
					if(tile.equals(chessField[i%8][i/8])){
						if(last != null) {
							if(last.getFigure().movementOption(i%8, i/8) && !controller.getDomainController().getGameFlowController().getBoard().getTile(i%8, i/8).isOccupied()) {
								controller.getDomainController().getGameFlowController().turn(last.getFigure(), i%8, i/8);
								colorBoardTilesToNormal();
								updateFigures();
								last = null;
							}

						}

					}
				}
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

	public void showPossibleTurns(Figure figure) {




		if(figure instanceof Figure_Rook) {
			boolean[][] occupation = new boolean[8][8];

			Figure_Rook rook = (Figure_Rook) figure;

			for(int i = 0; i<64; i++) {
				int x = i%8;
				int y = i/8;
				if(rook.movementOption(x, y, controller.getDomainController().getGameFlowController().getBoard().getOccupation())) {
					chessField[x][y].setFill(Color.YELLOW);
				}
			}


		}
		else  {
			for(int i = 0; i<64; i++) {
				if(figure.movementOption(i%8, i/8) && !controller.getDomainController().getGameFlowController().getBoard().getTile(i%8, i/8).isOccupied()) {
				chessField[i%8][i/8].setFill(Color.YELLOW);
			
				}
			}
		}


	}


	public Scene showScene() {
		this.addBoardTiles();
		figureSetup();
		return new Scene(this.borderPane);
	}


}
