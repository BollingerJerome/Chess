package application.presentation;

import application.domain.DomainController;

public class Controller {

	
	
	public Controller(ChessBoardView chessBoardView, DomainController domainController) {

		this.chessBoardView = chessBoardView;
		this.domainController = domainController;
	}

	private ChessBoardView chessBoardView;
	private DomainController domainController;

	public DomainController getDomainController() {
		return domainController;
	}

	public void setDomainController(DomainController domainController) {
		this.domainController = domainController;
	}

	public ChessBoardView getChessBoardView() {
		return chessBoardView;
	}

	public void setChessBoardView(ChessBoardView chessBoardView) {
		this.chessBoardView = chessBoardView;
	}

}
