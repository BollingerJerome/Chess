package application.domain.FigureModels;

public class Figure_King extends Figure implements Movement {





	public Figure_King(String name, String id, boolean alive, int x, int y, boolean white) {
		super(name, id, alive, x, y, white);
		this.firstMove = true;
	}

	private boolean firstMove;
	
	public boolean movementOption(int x, int y) {

		int thisX = this.getX(); 
		int thisY = this.getY();

		if(x-thisX == 1 || x-thisX == -1) {
			if(y-thisY == 1 || y-thisY == -1 || y-thisY == 0) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(x-thisX == 0){
			if(y-thisY == 1 || y-thisY == -1) {
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

	public boolean movementOption(int x, int y, boolean[][] occupation) {
		int thisX = this.getX();
		int thisY = this.getY();

		if(!occupation[x][y]) {
			if(x-thisX == 1 || x-thisX == -1) {
				if(y-thisY == 1 || y-thisY == -1 || y-thisY == 0) {
					return true;
				}
				else {
					return false;
				}
			}
			else if(x-thisX == 0){
				if(y-thisY == 1 || y-thisY == -1) {
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
		int thisY = this.getY();
		int thisX = this.getX();
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
	
	@Override
	public void move(int posX, int posY) {
		this.setX(posX);
		this.setY(posY);
		this.firstMove = false;
	}

}
