package application.domain;

public class Figure_King extends Figure implements Movement {





	public Figure_King(String name, String id, boolean alive, int x, int y, boolean white) {
		super(name, id, alive, x, y, white);
	}

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


}
