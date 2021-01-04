package application.presentation;

import application.domain.DomainController;

public class Controller {



	public Controller(DomainController domainController, GameField gameField) {
		super();
		this.domainController = domainController;
		this.gameField = gameField;
	}

	private DomainController domainController;
	private GameField gameField;

	public GameField getGameField() {
		return gameField;
	}

	public void setGameField(GameField gameField) {
		this.gameField = gameField;
	}

	public DomainController getDomainController() {
		return domainController;
	}

	public void setDomainController(DomainController domainController) {
		this.domainController = domainController;
	}
	
	

}
