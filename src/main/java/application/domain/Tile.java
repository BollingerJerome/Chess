package application.domain;

public class Tile {

	public Tile(int x, int y, boolean occupied, boolean isBlack, double sideLength) {
		this.x = x;
		this.y = y;
		this.occupied = occupied;
		this.isBlack = isBlack;
		this.sideLength = sideLength;

	}
	
	private int x,y;
	private boolean occupied;
	private boolean isBlack;
	private double sideLength;
	
	
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
	public boolean isBlack() {
		return isBlack;
	}
	public void setBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}
	public double getSideLength() {
		return sideLength;
	}
	public void setSideLength(double sideLength) {
		this.sideLength = sideLength;
	}

	
	
}
