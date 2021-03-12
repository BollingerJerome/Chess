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
	private Figure_Rook rochadeRook; 
	private int Xrook_rochade_rook;
	private int Yrook_rochade_rook;
	private Figure_Pawn mutator;

	private void showRochade(Figure figure) {
		if(figure instanceof Figure_King) { //rochade
			if(figure.isFirstMove() ) {  //Kings first move
				if(figures[0].isFirstMove() && figure.isWhite()) { //left Rook first move and king is white
					if(!board.getOccupation()[1][0] && !board.getOccupation()[2][0]) { //Fields are empty
						if(!wouldBeCheck(figure,2,0) && !wouldBeCheck(figure, 1, 0)) { //King wont be checked
							this.board.getTile(1, 0).setGreen(true);
							rochadeRook = (Figure_Rook) figures[0];
							Xrook_rochade_rook = 2;
							Yrook_rochade_rook = 0;
						}
					}
				}
				if(figures[7].isFirstMove() && figure.isWhite()) { //right Rook first move and king is white
					if(!board.getOccupation()[4][0] && !board.getOccupation()[5][0] && !board.getOccupation()[6][0]) { //Fields are empty
						if(!wouldBeCheck(figure,4,0) && !wouldBeCheck(figure, 5, 0) && !wouldBeCheck(figure, 6, 0)) { //King wont be checked
							this.board.getTile(6, 0).setGreen(true);
							rochadeRook = (Figure_Rook) figures[7];
							Xrook_rochade_rook = 5;
							Yrook_rochade_rook = 0;
						}
					}
				}
				if(figures[8].isFirstMove() && !figure.isWhite()) { //left Rook first move and king is black
					if(!board.getOccupation()[1][7] && !board.getOccupation()[2][7]) { //Fields are empty
						if(!wouldBeCheck(figure,1,7) && !wouldBeCheck(figure, 2, 7)) { //King wont be checked
							this.board.getTile(1, 7).setGreen(true);
							rochadeRook = (Figure_Rook) figures[8];
							Xrook_rochade_rook = 2;
							Yrook_rochade_rook = 7;
						}
					}
				}
				if(figures[15].isFirstMove() && !figure.isWhite()) { //right Rook first move and king is white
					if(!board.getOccupation()[4][7] && !board.getOccupation()[5][7] && !board.getOccupation()[6][7]) { //Fields are empty
						if(!wouldBeCheck(figure,4,7) && !wouldBeCheck(figure, 5, 7) && !wouldBeCheck(figure, 6, 7)) { //King wont be checked
							this.board.getTile(6, 7).setGreen(true);
							rochadeRook = (Figure_Rook) figures[15];
							Xrook_rochade_rook = 5;
							Yrook_rochade_rook = 7;
						}
					}
				}
			}
		}
		else {
			this.board.getTile(1, 0).setGreen(false);
			this.board.getTile(6, 7).setGreen(false);
			this.board.getTile(6, 0).setGreen(false);
			this.board.getTile(1, 7).setGreen(false);
		}
	}
	public void psetTilesColor(Figure figure) {
		updateOccupation();
		showRochade(figure);
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
				board.getTile(i, j).setGreen(false);
			}
		}
	}
	private boolean wouldBeCheck(Figure figure, int x,int y ){
		int preX = figure.getX();
		int preY = figure.getY();
		if(figure instanceof Figure_Pawn) {
			((Figure_Pawn) figure).imaginedMove(x, y);
		}
		else if(figure instanceof Figure_King) {
			((Figure_King) figure).imaginedMove(x,y);
		}
		else if(figure instanceof Figure_Rook) {
			((Figure_Rook) figure).imaginedMove(x,y);
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
		else if(figure instanceof Figure_King) {
			((Figure_King) figure).imaginedMove(preX,preY);
		}
		else if(figure instanceof Figure_Rook) {
			((Figure_Rook) figure).imaginedMove(preX,preY);
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
			if(last instanceof Figure_Pawn) {
				Figure_Pawn paw = (Figure_Pawn) last;
				if(paw.isMutationable()) {
					this.mutator = paw;
				}
			}
		}
		//clicked on red tile
		else if(tile.isRed()) {
			for(Figure victim: this.figures) {
				if(victim.isAlive()) {
					if(victim.getX()==tile.getX() && victim.getY() == tile.getY()) {
						victim.setAlive(false);
						updateOccupation();
						last.move(tile.getX(), tile.getY());
						if(last instanceof Figure_Pawn) {
							Figure_Pawn paw = (Figure_Pawn) last;
							if(paw.isMutationable()) {
								this.mutator = paw;
							}
						}

						break;
					}
				}
			}
		}
		else if(tile.isGreen()) {
			last.move(tile.getX(), tile.getY());
			rochadeRook.move(Xrook_rochade_rook, Yrook_rochade_rook);
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
	public Figure_Pawn getMutator() {
		return mutator;
	}
	public void setMutator(Figure_Pawn mutator) {
		this.mutator = mutator;
	}

}
