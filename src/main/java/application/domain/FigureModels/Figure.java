package application.domain.FigureModels;

public abstract class Figure implements Movement{
	
	
	
	public Figure(String name, String id, boolean alive, int x, int y, boolean white) {
		super();
		this.name = name;
		this.id = id;
		this.alive = alive;
		this.x = x;
		this.y = y;
		this.white = white;
		this.firstMove=false;
	}


	private String name;
	private String id;
	private boolean alive;
	private int x;
	private int y;
	private boolean white;
	private boolean firstMove;
	
	public void move(int posX, int posY) {
		this.setX(posX);
		this.setY(posY);
		this.firstMove=true;
	}
	public void imaginedMove(int posX, int posY) {
		this.setX(posX);
		this.setY(posY);
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public boolean isWhite() {
		return white;
	}


	public void setWhite(boolean white) {
		this.white = white;
	}


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
	public boolean isFirstMove() {
		return firstMove;
	}
	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}

}
