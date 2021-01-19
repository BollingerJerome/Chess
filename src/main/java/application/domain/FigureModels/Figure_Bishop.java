package application.domain.FigureModels;

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
	
	public boolean movementOption(int x, int y, boolean[][] occupation) {
		int thisX = this.getX();
		int thisY = this.getY();

		if(thisY == y && thisX != x) {
			if(thisX<x) {
				for(int i = thisX+1; i<=x; i++ ) {
					if(occupation[i][thisY]) {
						return false;
					}
				}
				return true;
			}
			else if(thisX>x){
				for(int i = thisX-1; i>=x; i-- ) {
					if(occupation[i][thisY]) {
						return false;
					}
				}
				return true;
			}
		}
		else if(thisY != y && thisX == x) {
			if(thisY<y) {
				for(int i = thisY+1; i<=y; i++ ) {
					if(occupation[thisX][i]) {
						return false;
					}
				}
				return true;
			}
			else if(thisY>y){
				for(int i = thisY-1; i>=y; i-- ) {
					if(occupation[thisX][i]) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

}
