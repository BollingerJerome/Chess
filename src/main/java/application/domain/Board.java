package application.domain;

public class Board {

	
	public Board() {
		this.tileField = new Tile[8][8];
		this.initializeBoard();
		this.figures = new Figure[32];
		this.setFiguresToStartPosition();
		this.updateOccupation();
	}

	private Tile[][] tileField;
	private Figure[] figures;
	
	public void setFiguresToStartPosition() {
		int number = 0;
		//white setup
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
		}
		
		for(int i = 0; i<8; i++) {
			figures[number++] = new Figure_Pawn("King_white", "00", true, i, 6, false);
		}
	}
	
	public void updateOccupation() {
		for(int i = 0; i<64; i++) {
			tileField[i%8][i/8].setOccupied(false);
		}
		for(int i = 0; i<32; i++) {
			if(figures[i] != null) {
				if(figures[i].isAlive()) {
					this.tileField[figures[i].getX()][figures[i].getY()].setOccupied(true);
				}
			}
		}
	}
	
	public void initializeBoard() {
		for(int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				this.tileField[i][j] = new Tile(i, j, false);
			}
		}
	}

	public Tile[][] getBoard() {
		return tileField;
	}

	public void setBoard(Tile[][] board) {
		this.tileField = board;
	}

	public Figure[] getFigures() {
		return figures;
	}

	public void setFigures(Figure[] figures) {
		this.figures = figures;
	}
	
	
	
	
}
