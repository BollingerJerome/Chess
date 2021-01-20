package application.domain;

public class DomainController {

	
	
	public DomainController(GameFlowController gameFlowController) {
		this.gameFlowController = gameFlowController;
		this.turnModel = gameFlowController.getTurnModel();
	}

	private GameFlowController gameFlowController;
	private TurnModel turnModel;
	
	public GameFlowController getGameFlowController() {
		return gameFlowController;
	}

	public void setGameFlowController(GameFlowController gameFlowController) {
		this.gameFlowController = gameFlowController;
	}

	public TurnModel getTurnModel() {
		return turnModel;
	}

	public void setTurnModel(TurnModel turnModel) {
		this.turnModel = turnModel;
	}


	
	

}
