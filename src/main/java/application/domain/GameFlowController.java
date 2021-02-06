package application.domain;

import application.domain.FigureModels.Figure;
import application.domain.FigureModels.Figure_Bishop;
import application.domain.FigureModels.Figure_King;
import application.domain.FigureModels.Figure_Knight;
import application.domain.FigureModels.Figure_Pawn;
import application.domain.FigureModels.Figure_Queen;
import application.domain.FigureModels.Figure_Rook;
import application.presentation.FigureVisual;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GameFlowController {

	public GameFlowController() {
		this.board = new Board(60);
		this.checkModel = new CheckModel();
		start();
	}

	private Figure[] figures;
	private Board board;
	private TurnModel turnModel;
	private CheckModel checkModel;
	
	public void start() {

		turnModel = new TurnModel();
		generateFigures();
		updateOccupation();

	}
	
	public void paintTiles(Figure figure) {
		showPath(figure);
		showPrey(figure);
	}
	private void showPath(Figure figure) {
		for(int i = 0; i<64; i++) {
			if(figure.movementOption(i%8, i/8, this.board.getOccupation())) {
				this.board.getTile(i%8, i/8).setYellow(true);
			}
			else {
				this.board.getTile(i%8, i/8).setYellow(false);
			}
		}
	}
	private void showPrey(Figure hunter) {
		for(Figure figure : this.figures) {
			if(figure.isAlive() && hunter.canEat(figure, this.board.getOccupation())) {
				this.board.getTile(figure.getX(), figure.getY()).setRed(true);
			}
			else {
				this.board.getTile(figure.getX(), figure.getY()).setRed(false);
			}
		}
	}
	
	public void handling(Object source) {
		
	}

	public void turn(Figure figure, int x, int y) {

		checkModel.checkIfCheck(!turnModel.isWhiteTurn(), figures, board.getOccupation());
		figure.move(x, y);
		checkModel.checkIfCheck(!turnModel.isWhiteTurn(), figures, board.getOccupation());
		turnModel.updateOne();
		updateOccupation();

	}
	
	public void updateOccupation() {
		for(int i = 0; i<64; i++) {
			board.getTile(i%8, i/8).setOccupied(false);
		}
		for(Figure figure : figures) {
			if(figure.isAlive()) {
				board.getTile(figure.getX(), figure.getY()).setOccupied(true);
			}
		}
	}
	public void generateFigures() {
		figures = new Figure[32];
		int number = 0;

		figures[number++] = new Figure_Rook("Rook", "00", true, 0, 0, true);
		figures[number++] = new Figure_Knight("Knight", "00", true, 1, 0, true);
		figures[number++] = new Figure_Bishop("Bishop", "00", true, 2, 0, true);
		figures[number++] = new Figure_King("King", "00", true, 3, 0, true);
		figures[number++] = new Figure_Queen("Queen", "00", true, 4, 0, true);
		figures[number++] = new Figure_Bishop("Bishop", "00", true, 5, 0, true);
		figures[number++] = new Figure_Knight("Knight", "00", true, 6, 0, true);
		figures[number++] = new Figure_Rook("Rook", "00", true, 7, 0, true);


		figures[number++] = new Figure_Rook("Rook", "00", true, 0, 7, false);
		figures[number++] = new Figure_Knight("Knight", "00", true, 1, 7, false);
		figures[number++] = new Figure_Bishop("Bishop", "00", true, 2, 7, false);
		figures[number++] = new Figure_King("King", "00", true, 3, 7, false);
		figures[number++] = new Figure_Queen("Queen", "00", true, 4, 7, false);
		figures[number++] = new Figure_Bishop("Bishop", "00", true, 5, 7, false);
		figures[number++] = new Figure_Knight("Knight", "00", true, 6, 7, false);
		figures[number++] = new Figure_Rook("Rook", "00", true, 7, 7, false);

		for(int i = 0; i<8; i++) {
			figures[number++] = new Figure_Pawn("Pawn", "00", true, i, 1, true);
			figures[number++] = new Figure_Pawn("Pawn", "00", true, i, 6, false);
		}

	}


	public Figure[] getFigures() {
		return figures;
	}
	public void setFigures(Figure[] figures) {
		this.figures = figures;
	}
	public Board getBoard() {
		this.updateOccupation();
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public TurnModel getTurnModel() {
		return turnModel;
	}
	public void setTurnModel(TurnModel turnModel) {
		this.turnModel = turnModel;
	}

}
