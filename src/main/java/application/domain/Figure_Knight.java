package application.domain;

public class Figure_Knight extends Figure implements Movement {

	public Figure_Knight(String name, String id, boolean alive, int[] position, boolean white) {
		super(name, id, alive, position, white);
		// TODO Auto-generated constructor stub
	}

	public int[][] movementOption() {


		int x = this.getPosition()[0];
		int y = this.getPosition()[1];
		int[][] optionArray = new int[8][2];
		int counter = 0;
		
		if(x+2<8) {
			if(y+1<8) {
				optionArray[counter][0] = x+1;
				optionArray[counter][1] = y;
				counter++;
			}
			else if(y-1>=0){
				
			}
		}
		
		
		
		return optionArray;
	}

}
