package application.domain;

public class Figure_Pawn extends Figure implements Movement {

	public Figure_Pawn(String name, String id, boolean alive, int[] position) {
		super(name, id, alive, position);
		// TODO Auto-generated constructor stub
	}


	public int[][] movementOption() {
		
		int x = this.getPosition()[0];
		int y = this.getPosition()[1];
		int[][] movementOption = new int[1][2];
		
		if(y < 7) {
			movementOption[0][0] = x;
			movementOption[0][0] = y+1;
		}
		
		return movementOption;
	}

}
