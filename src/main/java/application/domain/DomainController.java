package application.domain;

public class DomainController {

	
	
	public DomainController(Board board) {
		this.board = board;
	}

	private Board board;

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	
	

}
