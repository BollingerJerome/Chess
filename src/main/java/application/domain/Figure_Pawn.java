package application.domain;

public class Figure_Pawn extends Figure implements Movement {

	public Figure_Pawn(String name, String id, boolean alive, int[] position, boolean white) {
		super(name, id, alive, position, white);
		// TODO Auto-generated constructor stub
	}




	public boolean movementOption(int x, int y) {
		int thisY = this.getPosition()[1];
		
		if(this.isWhite()) {
			if(y-thisY == 1) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(thisY-y == 1) {
				return true;
			}
			else {
				return false;
			}
		}

	}

}
