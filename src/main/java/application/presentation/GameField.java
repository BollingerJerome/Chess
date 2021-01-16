package application.presentation;


import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameField {

	public GameField(Controller controller, float sideLength) {
		this.borderPane = new BorderPane();
		this.sideLength = sideLength;
		this.controller = controller;
		this.chessGrid = new ChessGrid(controller.getDomainController().getBoard(), sideLength);
		this.drawTiles();
		this.drawFigures();
		this.eventHandler = getEventHandler();
		this.addMovementOption();
		
	}

	private BorderPane borderPane;
	private Controller controller;
	private ChessGrid chessGrid;
	private FigureVisual[] FigureObjects;
	private float sideLength;
	private EventHandler<MouseEvent> eventHandler;
	

	
	private EventHandler<MouseEvent> getEventHandler (){ 	//adding Eventhandler
		return new EventHandler<MouseEvent>() {				
			public void handle(MouseEvent event) {
				chessGrid.colorBoardNormal();
				if(event.getSource() instanceof FigureVisual) {
					colorMovementRectangles(event.getSource());
				}
				else if(event.getSource() instanceof Rectangle){
					
				}
			}
		};
	};
	
	
	/*public void moveFigure(Object figure) {
		FigureVisual source = (FigureVisual) figure;
		source.getFigure().move(posX, posY);
	}*/
	
	public void colorMovementRectangles(Object figure) {
		
		FigureVisual source = (FigureVisual) figure;
		
		for(int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				if(source.getFigure().movementOption(i, j) && !chessGrid.getBoard().getBoard()[i][j].isOccupied()) {
					chessGrid.getRectangles(i, j).setFill(Color.YELLOW);
					chessGrid.getRectangles(i, j).addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
				}
			}
		}
		
	}
	
	public void addMovementOption() {
		for(int i = 0; i<16; i++) {
			FigureObjects[i].addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
		}
	}
	
	public void drawFigures() {
		FigureObjects = new FigureVisual[16];
		for(int i = 0; i<16; i++) {

			FigureObjects[i] = new FigureVisual(60*chessGrid.getBoard().getFigures()[i].getX(), 60*chessGrid.getBoard().getFigures()[i].getY(), 60, 60, chessGrid.getBoard().getFigures()[i]);
			FigureObjects[i].setArcHeight(80);
			FigureObjects[i].setArcWidth(80);
			FigureObjects[i].setFill(Color.BLUE);
			borderPane.getChildren().add(FigureObjects[i]);
		}
	}
	


	public void drawTiles() {
		chessGrid.colorBoardNormal();
		System.out.println("drawing");
		for(int i = 0; i < 64; i++) {
			borderPane.getChildren().add(chessGrid.getRectangles(i%8, i/8));
		}
		Rectangle test = new Rectangle(0, 0, 100, 100);
		test.setFill(Color.YELLOW);
		borderPane.getChildren().add(test);
	}
	
	

	public Scene getScene() {
		return new Scene(borderPane);
	}

	public Controller getController() {
		return controller;
	}


	public void setController(Controller controller) {
		this.controller = controller;
	}


	public ChessGrid getChessGrid() {
		return chessGrid;
	}


	public void setChessGrid(ChessGrid chessGrid) {
		this.chessGrid = chessGrid;
	}


	public float getSideLength() {
		return sideLength;
	}


	public void setSideLength(float sideLength) {
		this.sideLength = sideLength;
	}



}
