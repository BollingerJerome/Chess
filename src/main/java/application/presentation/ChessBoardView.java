package application.presentation;

import application.domain.Board;
import application.domain.Tile;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChessBoardView {

	public ChessBoardView(Controller controller) {
		this.controller = controller;
	}

	
	private BorderPane borderPane;
	private Controller controller;
	private Rectangle[][] chessField;
	
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
	
	public Scene showScene() {
		
		
		
		
		return new Scene(borderPane);
	}
	
	
}
