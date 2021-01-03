package application.domain;

public class Figure_King extends Figure implements Movement {



	public Figure_King(String name, String id, boolean alive, int[] position, boolean white) {
		super(name, id, alive, position, white);
	}

	

	public boolean movementOption(int x, int y) {
		
		int thisX = this.getPosition()[0]; 
		int thisY = this.getPosition()[1];
		
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
