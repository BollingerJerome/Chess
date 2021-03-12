package application.domain;

public class Tile {

	public Tile(int x, int y, boolean occupied, boolean isBlack, double sideLength) {
		this.x = x;
		this.y = y;
		this.occupied = occupied;
		this.isBlack = isBlack;
		this.sideLength = sideLength;
		this.red = false;
		this.yellow = false;
		this.violet = false;
		this.green = false;

	}
	
	private int x,y;
	private boolean occupied;
	private boolean isBlack;
	private double sideLength;
	private boolean red;
	private boolean yellow;
	private boolean violet;
	private boolean green;
	
	
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
	public boolean isRed() {
		return red;
	}
	public void setRed(boolean red) {
		this.red = red;
	}
	public boolean isYellow() {
		return yellow;
	}
	public void setYellow(boolean yellow) {
		this.yellow = yellow;
	}
	public boolean isViolet() {
		return violet;
	}
	public void setViolet(boolean violet) {
		this.violet = violet;
	}
	public boolean isGreen() {
		return green;
	}
	public void setGreen(boolean green) {
		this.green = green;
	}

	
	
}
