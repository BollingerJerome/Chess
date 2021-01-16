package application.domain.FigureModels;
import application.domain.FigureModels.Movement;

public class Figure_Rook extends Figure implements Movement {

	

	public Figure_Rook(String name, String id, boolean alive, int x, int y, boolean white) {
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
			return false;
		}
	}

}
