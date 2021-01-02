package application.domain;

public class Figure {
	
	public Figure(String name, String id, boolean alive, int[] position) {
		super();
		this.name = name;
		this.id = id;
		this.alive = alive;
		this.position = position;
	}
	private String name;
	private String id;
	private boolean alive;
	private int[] position;
	
	
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
	public int[] getPosition() {
		return position;
	}
	public void setPosition(int[] position) {
		this.position = position;
	}

}
