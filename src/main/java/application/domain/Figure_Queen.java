package application.domain;

public class Figure_Queen extends Figure implements Movement {

	public Figure_Queen(String name, String id, boolean alive, int[] position, boolean white) {
		super(name, id, alive, position, white);
		// TODO Auto-generated constructor stub
	}


	public boolean movementOption(int x, int y) {
		int thisX = this.getPosition()[0];
		int thisY = this.getPosition()[1];

		if(thisY == y && thisX != x) {
			return true;
		}
		else if(thisX == x && thisY != y) {
			return true;
		}
		else {		
			if((thisX -x == thisY - y || thisX -x == -(thisY - y)) && thisY != y && thisX != x) {
				return true;
			}
			else {
				return false;
			}
		}

	}
}