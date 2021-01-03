package application.presentation;

public class Controller {

	public Controller(GameField gameField) {
		this.gameField = gameField;
	}

	private GameField gameField;

	public GameField getGameField() {
		return gameField;
	}

	public void setGameField(GameField gameField) {
		this.gameField = gameField;
	}
	
	

}
