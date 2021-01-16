package application.domain;

public class DomainController {

	
	
	public DomainController(GameFlowController gameFlowController) {
		this.gameFlowController = gameFlowController;
	}

	private GameFlowController gameFlowController;

	public GameFlowController getGameFlowController() {
		return gameFlowController;
	}

	public void setGameFlowController(GameFlowController gameFlowController) {
		this.gameFlowController = gameFlowController;
	}


	
	

}
