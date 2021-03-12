package application.domain.FigureModels;


public class Figure_Pawn extends Figure implements Movement {

	public Figure_Pawn(String name, String id, boolean alive, int x, int y, boolean white) {
		super(name, id, alive, x, y, white);
		this.mutationable = false;
	}
	private boolean mutationable;

	public boolean movementOption(int x, int y) {
		int thisY = this.getY();
		int thisX = this.getX();
		
		
		if(this.isWhite()) {
			if(y-thisY == 1 && x == thisX) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(thisY-y == 1  && x == thisX) {
				return true;
			}
			else {
				return false;
			}
		}

	}

	public boolean movementOption(int x, int y, boolean[][] occupation) {
		int thisY = this.getY();
		int thisX = this.getX();

		if(!occupation[x][y]) {
			if(this.isWhite()) {
				if(y-thisY == 1 && x == thisX) {
					return true;
				}
				else if(this.isFirstMove() && y-thisY == 2 && x == thisX && !occupation[x][y-1]) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if(thisY-y == 1  && x == thisX) {
					return true;
				}
				else if(this.isFirstMove() && thisY-y == 2 && x == thisX && !occupation[x][y+1]) {
					return true;
				}
				else {
					return false;
				}
			}

		}
		else {
			return false;
		}
	}

	public boolean canEat(Figure figure, boolean[][] notNeeded) {
		int thisY = this.getY();
		int thisX = this.getX();
		int figureX = figure.getX();
		int figureY = figure.getY();
		if(this.isWhite() && !figure.isWhite()) {

			if((figureX == thisX+1 || figureX == thisX-1) && figureY == thisY+1) {
				return true;
			}
		}
		else if(!this.isWhite() && figure.isWhite()){
			if((figureX == thisX+1 || figureX == thisX-1) && figureY == thisY-1) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void move(int posX, int posY) {
		this.setX(posX);
		this.setY(posY);
		this.setFirstMove(false);;
		if((this.isWhite() && this.getY() == 7) || (!this.isWhite() && this.getY() == 0)) {
			this.mutationable = true;
		}
	}

	public boolean isMutationable() {
		return mutationable;
	}

	public void setMutationable(boolean mutationable) {
		this.mutationable = mutationable;
	}
	
}