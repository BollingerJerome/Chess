package application.domain.FigureModels;

public class Figure_Knight extends Figure implements Movement {




	public Figure_Knight(String name, String id, boolean alive, int x, int y, boolean white) {
		super(name, id, alive, x, y, white);
	}




	public boolean movementOption(int x, int y, boolean[][] occupation) {

		int thisX = this.getX();
		int thisY = this.getY();

		if(!occupation[x][y]) {
			if(x - thisX == 2 || thisX - x == 2) {
				if(y-thisY == 1 || thisY -y == 1) {
					return true;
				}
				else {
					return false;
				}
			}
			else if(y-thisY == 2 || thisY -y == 2) {
				if(x - thisX == 1 || thisX - x == 1) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	public boolean canEat(Figure figure, boolean[][] occupation) {
		int figureX = figure.getX();
		int figureY = figure.getY();
		if(this.isWhite() != figure.isWhite()) {

			boolean[][] occup = occupation;
			occup[figureX][figureY] = false;
			if(movementOption(figureX,figureY,occup)) {
				return true;
			}
		}

		return false;
	}
}
