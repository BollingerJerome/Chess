package application.domain;

import application.domain.FigureModels.Figure;
import application.domain.FigureModels.Figure_Bishop;
import application.domain.FigureModels.Figure_King;
import application.domain.FigureModels.Figure_Knight;
import application.domain.FigureModels.Figure_Pawn;
import application.domain.FigureModels.Figure_Queen;
import application.domain.FigureModels.Figure_Rook;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class GameFlowController {

	public GameFlowController() {
		board = new Board(60);
		start();
	}
	
	private Figure[] figures;
	private Board board;

	private int round;
	private int state;
	
	public void start() {
		round = 0;
		state = 0;
		
		generateFigures();
		updateOccupation();
		
	}
	
	public void turn(Figure figure, int x, int y) {
		if(!board.getTile(x, y).isOccupied()) {
			figure.move(x, y);
			updateOccupation();
		}
	}
		
	
	public void updateOccupation() {
		
		for(int i = 0; i<64; i++) {
			board.getTile(i%8, i/8).setOccupied(false);
		}
		
		for(Figure figure : figures) {
			board.getTile(figure.getX(), figure.getY()).setOccupied(true);
		}
	}
	
	public void generateFigures() {
		figures = new Figure[32];
		int number = 0;
		
		figures[number++] = new Figure_Rook("Rook_white", "00", true, 0, 0, true);
		figures[number++] = new Figure_Knight("King_white", "00", true, 1, 0, true);
		figures[number++] = new Figure_Bishop("King_white", "00", true, 2, 0, true);
		figures[number++] = new Figure_King("King_white", "00", true, 3, 0, true);
		figures[number++] = new Figure_Queen("King_white", "00", true, 4, 0, true);
		figures[number++] = new Figure_Bishop("King_white", "00", true, 5, 0, true);
		figures[number++] = new Figure_Knight("King_white", "00", true, 6, 0, true);
		figures[number++] = new Figure_Rook("King_white", "00", true, 7, 0, true);
		
		
		figures[number++] = new Figure_Rook("Rook_white", "00", true, 0, 7, false);
		figures[number++] = new Figure_Knight("King_white", "00", true, 1, 7, false);
		figures[number++] = new Figure_Bishop("King_white", "00", true, 2, 7, false);
		figures[number++] = new Figure_King("King_white", "00", true, 3, 7, false);
		figures[number++] = new Figure_Queen("King_white", "00", true, 4, 7, false);
		figures[number++] = new Figure_Bishop("King_white", "00", true, 5, 7, false);
		figures[number++] = new Figure_Knight("King_white", "00", true, 6, 7, false);
		figures[number++] = new Figure_Rook("King_white", "00", true, 7, 7, false);
		
		for(int i = 0; i<8; i++) {
			figures[number++] = new Figure_Pawn("King_white", "00", true, i, 1, true);
			figures[number++] = new Figure_Pawn("King_white", "00", true, i, 6, false);
		}
		
	}


	public Figure[] getFigures() {
		return figures;
	}


	public void setFigures(Figure[] figures) {
		this.figures = figures;
	}


	public Board getBoard() {
		return board;
	}


	public void setBoard(Board board) {
		this.board = board;
	}


	public int getRound() {
		return round;
	}


	public void setRound(int round) {
		this.round = round;
	}


	public int getState() {
		return state;
	}


	public void setState(int state) {
		this.state = state;
	}

}
