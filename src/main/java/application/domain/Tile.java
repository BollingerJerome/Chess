package application.domain;

public class Tile {

	

	public Tile(int x, int y, boolean occupied) {
		super();
		this.x = x;
		this.y = y;
		this.occupied = occupied;
	}
	
	private int x,y;
	private boolean occupied;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	
}