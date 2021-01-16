package application.domain.FigureModels;

public class Figure_Queen extends Figure implements Movement {

	public Figure_Queen(String name, String id, boolean alive, int x, int y, boolean white) {
		super(name, id, alive, x, y, white);
	}

	public boolean movementOption(int x, int y) {
		int thisX = this.getX();
		int thisY = this.getY();

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