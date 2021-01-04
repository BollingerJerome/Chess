package application.domain;

public class Figure_Bishop extends Figure implements Movement {

	public Figure_Bishop(String name, String id, boolean alive, int x, int y, boolean white) {
		super(name, id, alive, x, y, white);
	}

	public boolean movementOption(int x, int y) {
		int thisX = this.getX();
		int thisY = this.getY();
		
		if((thisX -x == thisY - y || thisX -x == -(thisY - y)) && thisY != y && thisX != x) {
			return true;
		}
		else {
			return false;
		}

	}

}
