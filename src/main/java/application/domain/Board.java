package application.domain;


public class Board {

	
	public Board(double sideLength) {
		this.tileField = new Tile[8][8];
		this.sideLength = sideLength;
		initializeTiles();
	}

	private Tile[][] tileField;
	private double sideLength;
	
	
	public boolean[][] getOccupation(){
		boolean[][] occupation = new boolean[8][8];
		for(int k = 0; k<8; k++) {
			for(int j = 0; j<8; j++) {
				occupation[k][j] = getTile(k, j).isOccupied();
			}
		}
		return occupation;
	}
	
	public void initializeTiles() {
		for(int i = 0; i<8; i++) {
			for(int j = 0; j<8; j++) {
				if((i+j)%2 == 0) {
					this.tileField[i][j] = new Tile(i, j, false, false, sideLength);
				}
				else {
					this.tileField[i][j] = new Tile(i, j, false, true, sideLength);
				}
			}
		}
	}

	public Tile getTile(int x, int y) {
		return tileField[x][y];
	}
	public void setTileField(Tile[][] tileField) {
		this.tileField = tileField;
	}
	public double getSideLength() {
		return sideLength;
	}
	public void setSideLength(double sideLength) {
		this.sideLength = sideLength;
	}
	public Tile[][] getTileField() {
		return tileField;
	}	
}
