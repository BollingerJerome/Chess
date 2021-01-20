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
		
		if(thisX -x == thisY - y) { 			// \-axe
			if(x>thisX && y>thisY) {			// figure top left, xy bottom right
				for(int i = 1; i<x-thisX+1; i++) {
					if(occupation[thisX+i][thisY+i]) {
						return false;
					}
				}
				return true;
			}
			
			else if(x<thisX && y<thisY) {		//figure bottom right
				for(int i = 1; i<thisY-y+1; i++) {
					if(occupation[thisX-i][thisY-i]) {
						return false;
					}
				}
				return true;
			}
			
		}
		else if(thisX -x == -(thisY - y)) {
			if(x<thisX && y>thisY) {			//figure top right 
				for(int i = 1; i<y-thisY+1; i++) {
					if(occupation[thisX-i][thisY+i]) {
						return false;
					}
				}
				return true;
			}
			
			else if(x>thisX && y<thisY) {		//figure bottom left, xy top right
				for(int i = 1; i<x-thisX+1; i++) {
					if(occupation[thisX+i][thisY-i]) {
						return false;
					}
				}
				return true;
			}
			
			
		}
		
		

		return false;
	}

}
