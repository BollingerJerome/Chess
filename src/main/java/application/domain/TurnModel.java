package application.domain;

public class TurnModel {

	public TurnModel() {
		this.turn = 0;
		this.whiteTurn = true;
		this.state = 0;
	}

	private int state;
	private int turn;
	private boolean whiteTurn;
	
	public void updateOne() {
		this.setTurn(this.getTurn()+1);
		if(turn%2 == 0) {
			setWhiteTurn(true);
		}
		else {
			setWhiteTurn(false);
		}
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public boolean isWhiteTurn() {
		return whiteTurn;
	}

	public void setWhiteTurn(boolean whiteTurn) {
		this.whiteTurn = whiteTurn;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
