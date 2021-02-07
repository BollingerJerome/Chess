package application.domain;

import application.domain.FigureModels.Figure;
import application.domain.FigureModels.Figure_Bishop;
import application.domain.FigureModels.Figure_King;
import application.domain.FigureModels.Figure_Knight;
import application.domain.FigureModels.Figure_Pawn;
import application.domain.FigureModels.Figure_Queen;
import application.domain.FigureModels.Figure_Rook;


public class GameFlowController {

	public GameFlowController() {
		this.board = new Board(60);
	//	this.checkModel = new CheckModel();
		this.last = null;
		this.turnModel = new TurnModel();
		generateFigures();
		updateOccupation();
	}

	private Figure[] figures;
	private Board board;
	private TurnModel turnModel;
	//private CheckModel checkModel;
	private Figure last;
	
	
	public void paintTiles(Figure figure) {
		updateOccupation();
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
		for(Figure victim : this.figures) {
			if(victim.isAlive()) {
				if(hunter.canEat(victim, this.board.getOccupation())) {
					this.board.getTile(victim.getX(), victim.getY()).setRed(true);
				}
				else {
					this.board.getTile(victim.getX(), victim.getY()).setRed(false);
				}
			}
		}
	}
	private void eraseTileColor() {
		for (int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				board.getTile(i, j).setRed(false);
				board.getTile(i, j).setYellow(false);
			}
		}
	}
	
	
	public void clickOnFigure(Figure source) {
		if(turnModel.isWhiteTurn() == source.isWhite()) {
			paintTiles(source);
			this.last = source;
		}
	}
	public void clickOnTile(Tile tile) {
		if(tile.isYellow()) {
			last.move(tile.getX(), tile.getY());
			updateOccupation();
			turnModel.updateOne();
			eraseTileColor();
		}
		else if(tile.isRed()) {
			for(Figure victim: this.figures) {
				if(victim.isAlive()) {
					if(victim.getX()==tile.getX() && victim.getY() == tile.getY()) {
						victim.setAlive(false);
						updateOccupation();
						last.move(tile.getX(), tile.getY());
						updateOccupation();
						turnModel.updateOne();
						eraseTileColor();
						return;
					}
				}
			}
		}
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
