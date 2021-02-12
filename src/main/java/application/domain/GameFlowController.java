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

		this.last = null;
		this.turnModel = new TurnModel();
		generateFigures();
		updateOccupation();
		this.checkModel = new CheckModel((Figure_King) figures[3],(Figure_King) figures[11]);
	}

	private Figure[] figures;
	private Board board;
	private TurnModel turnModel;
	private CheckModel checkModel;
	private Figure last;


	public void psetTilesColor(Figure figure) {
		updateOccupation();
		showPath(figure);
		showPrey(figure);
	}
	private void showPath(Figure figure) {
		for(int i = 0; i<64; i++) {
			int ix = i%8;
			int iy = i/8;
			if(figure.movementOption(ix, iy, this.board.getOccupation())) {
				if(!wouldBeCheck(figure, ix, iy)) {
					this.board.getTile(ix, iy).setYellow(true);
				}
				else {
					this.board.getTile(ix, iy).setYellow(false);
				}
			}
			else {
				this.board.getTile(ix, iy).setYellow(false);
			}
		}
	}
	private void showPrey(Figure hunter) {
		for(Figure victim : this.figures) {
			if(victim.isAlive()) {
				if(hunter.canEat(victim, this.board.getOccupation())) {
					if(!wouldBeCheckIfEaten(hunter, victim, victim.getX(), victim.getY())) {
						this.board.getTile(victim.getX(), victim.getY()).setRed(true);
					}
					else{
						this.board.getTile(victim.getX(), victim.getY()).setRed(false);
					}
				}
				else {
					this.board.getTile(victim.getX(), victim.getY()).setRed(false);
				}
			}
		}
	}
	private void eraseVioletTile() {
		for (int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				board.getTile(i, j).setViolet(false);
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
	private boolean wouldBeCheck(Figure figure, int x,int y ){
		int preX = figure.getX();
		int preY = figure.getY();
		if(figure instanceof Figure_Pawn) {
			((Figure_Pawn) figure).imaginedMove(x, y);
		}
		else {
			figure.move(x, y);
		}
		boolean out = false;
		updateOccupation();
		if(checkModel.canEatKing(figure.isWhite(), figures, board.getOccupation())) {
			out = true;
		}
		else {
			out = false;
		}

		if(figure instanceof Figure_Pawn) {
			((Figure_Pawn) figure).imaginedMove(preX, preY);
		}
		else {
			figure.move(preX, preY);
		}
		updateOccupation();
		return out;
	}
	private boolean wouldBeCheckIfEaten(Figure figure, Figure victim, int x,int y ){
		int preX = figure.getX();
		int preY = figure.getY();
		victim.setAlive(false);
		if(figure instanceof Figure_Pawn) {
			((Figure_Pawn) figure).imaginedMove(x, y);
		}
		else {
			figure.move(x, y);
		}
		boolean out = false;
		updateOccupation();
		if(checkModel.canEatKing(figure.isWhite(), figures, board.getOccupation())) {
			out = true;
		}
		else {
			out = false;
		}
		if(figure instanceof Figure_Pawn) {
			((Figure_Pawn) figure).imaginedMove(preX, preY);
		}
		else {
			figure.move(preX, preY);
		}
		victim.setAlive(true);
		updateOccupation();
		return out;
	}
	public void clickOnFigure(Figure source) {
		if(turnModel.isWhiteTurn() == source.isWhite()) {
			psetTilesColor(source);
			this.last = source;
		}
		else {
			if(board.getTile(source.getX(), source.getY()).isRed()) {
				source.setAlive(false);
				updateOccupation();
				last.move(source.getX(), source.getY());
				if(checkModel.canEatKing(!turnModel.isWhiteTurn(), figures, board.getOccupation())) {
					board.getTile(checkModel.getKing(!turnModel.isWhiteTurn()).getX(), 
							checkModel.getKing(!turnModel.isWhiteTurn()).getY()).setViolet(true);
				}
				else {
					eraseVioletTile();
				}
				//update occupation, add one to turn and remove all yellow and red tiles
				updateOccupation();
				if(!availableMoveOption(!turnModel.isWhiteTurn())) {
					System.out.println("someone won!");
				}
				turnModel.updateOne();
				eraseTileColor();
			}
			return;
		}
	}
	public void clickOnTile(Tile tile) {
		//clicked on yellow Tile
		if(tile.isYellow()) {
			last.move(tile.getX(), tile.getY());
		}
		//clicked on red tile
		else if(tile.isRed()) {
			for(Figure victim: this.figures) {
				if(victim.isAlive()) {
					if(victim.getX()==tile.getX() && victim.getY() == tile.getY()) {
						victim.setAlive(false);
						updateOccupation();
						last.move(tile.getX(), tile.getY());
						break;
					}
				}
			}
		}
		//clicked on every other tile, stop wait for another input
		else {
			return;
		}
		//set the opponent check
		if(checkModel.canEatKing(!turnModel.isWhiteTurn(), figures, board.getOccupation())) {
			board.getTile(checkModel.getKing(!turnModel.isWhiteTurn()).getX(), 
					checkModel.getKing(!turnModel.isWhiteTurn()).getY()).setViolet(true);
		}
		else {
			eraseVioletTile();
		}
		//update occupation, add one to turn and remove all yellow and red tiles
		updateOccupation();
		if(!availableMoveOption(!turnModel.isWhiteTurn())) {
			System.out.println("someone won!");
		}
		turnModel.updateOne();
		eraseTileColor();
	}

	public boolean availableMoveOption(boolean white) {
		Tile[][] preTile = board.getTileField();
		boolean out = false;
		for(Figure figure : figures) {
			if(figure.isWhite() == white && figure.isAlive()) {
				psetTilesColor(figure);	
			}
		}
		for(int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				if(board.getTile(i, j).isRed() || board.getTile(i, j).isYellow()) {
					out = true;
				}
			}
		}
		board.setTileField(preTile);
		return out;
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
