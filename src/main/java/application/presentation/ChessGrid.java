package application.presentation;

import application.domain.Board;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChessGrid{

	
	
	public ChessGrid(Board board, float sideLength) {
		
		this.board = board;
		this.rectangles = new Rectangle[8][8];
		initializeRectangles();
		this.sideLength = sideLength;
	}
	
	private Board board;
	private Rectangle[][] rectangles;
	private float sideLength;
	
	public void initializeRectangles() {
		
		for(int i = 0; i<64; i++) {
			int x = i%8;
			int y = i/8;
			rectangles[x][y] = new Rectangle(sideLength * x, sideLength * y, sideLength, sideLength);
		}
		colorBoardNormal();
	}
	
	public void colorBoardNormal() {
		for(int i = 0; i<64; i++) {
			int x = i%8;
			int y = i/8;

			if((x + y)%2 == 0) {
				rectangles[x][y].setFill(Color.BLACK);
			}
			else {
				rectangles[x][y].setFill(Color.WHITE);
			}
		}
	}

	public Rectangle getRectangles(int x, int y) {
		return rectangles[x][y];
	}

	public void setRectangles(Rectangle[][] rectangles) {
		this.rectangles = rectangles;
	}

	public float getSideLength() {
		return sideLength;
	}

	public void setSideLength(float sideLength) {
		this.sideLength = sideLength;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	
	
	
}
