package application.presentation;

import application.domain.Board;
import application.domain.Figure;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameField {

	public GameField(Controller controller, float sideLength) {
		this.sideLength = sideLength;
		this.scene = drawScene();
		this.controller = controller;
		this.drawFigures();
		this.eventHandler = getEventHandler();
		this.addMovementOption();
	}

	private BorderPane borderPane;
	private Controller controller;
	private Scene scene;
	private Rectangle[][] rectangle;
	private FigureVisual[] FigureObjects;
	private float sideLength;
	private EventHandler<MouseEvent> eventHandler;

	
	private EventHandler<MouseEvent> getEventHandler (){ 	//adding Eventhandler
		return new EventHandler<MouseEvent>() {				
			public void handle(MouseEvent event) {
				colorBoardNormal();
				colorMovementRectangles(event.getSource());									//updating the field 
			}
		};
	};
	
	
	
	public void colorMovementRectangles(Object figure) {
		
		FigureVisual source = (FigureVisual) figure;
		
		for(int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				if(source.getFigure().movementOption(i, j) && !controller.getDomainController().getBoard().getBoard()[i][j].isOccupied()) {
					rectangle[i][j].setFill(Color.YELLOW);
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
		Board board = controller.getDomainController().getBoard();
		for(int i = 0; i<16; i++) {

			FigureObjects[i] = new FigureVisual(60*board.getFigures()[i].getX(), 60*board.getFigures()[i].getY(), 60, 60, board.getFigures()[i]);
			FigureObjects[i].setArcHeight(80);
			FigureObjects[i].setArcWidth(80);
			FigureObjects[i].setFill(Color.BLUE);
			borderPane.getChildren().add(FigureObjects[i]);
		}
	}


	public void colorBoardNormal() {
		for(int i = 0; i<64; i++) {
			int x = i%8;
			int y = i/8;

			if((x + y)%2 == 0) {
				rectangle[x][y].setFill(Color.BLACK);
			}
			else {
				rectangle[x][y].setFill(Color.WHITE);
			
		}}
	}
	
	public Scene drawScene() {

		this.borderPane = new BorderPane();
		this.rectangle = new Rectangle[8][8];

		for(int i = 0; i<64; i++) {
			int x = i%8;
			int y = i/8;

			rectangle[x][y] = new Rectangle(sideLength * x, sideLength * y, sideLength, sideLength);

			if((x + y)%2 == 0) {
				rectangle[x][y].setFill(Color.BLACK);
			}
			else {
				rectangle[x][y].setFill(Color.WHITE);
			}

			borderPane.getChildren().add(rectangle[x][y]);
		}
		return new Scene(borderPane);
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}



}
