package application.domain;

public class Figure_Bishop extends Figure implements Movement {

	public Figure_Bishop(String name, String id, boolean alive, int[] position, boolean white) {
		super(name, id, alive, position, white);
	}



	public boolean movementOption(int x, int y) {
		int thisX = this.getPosition()[0];
		int thisY = this.getPosition()[1];
		
		if((thisX -x == thisY - y || thisX -x == -(thisY - y)) && thisY != y && thisX != x) {
			return true;
		}
		else {
			return false;
		}

	}

}
