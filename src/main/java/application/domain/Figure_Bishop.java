package application.domain;

public class Figure_Bishop extends Figure implements Movement {

	public Figure_Bishop(String name, String id, boolean alive, int[] position, boolean white) {
		super(name, id, alive, position, white);
	}

	public int[][] movementOption() {

		int x = this.getPosition()[0];
		int y = this.getPosition()[1];
		int[][] optionArray = new int[16][2];
		int counter = 0;

		for(int j = 0; j<8; j++) {

			if(x+j < 8 && y+j < 8) {
				optionArray[counter][0] = x+j;
				optionArray[counter][1] = y+j;
				counter++;
			}
			
			if(x-j >= 0 && y+j < 8) {
				optionArray[counter][0] = x-j;
				optionArray[counter][1] = y+j;
				counter++;
			}
			
			if(x+j < 8 && y+j >= 0) {
				optionArray[counter][0] = x+j;
				optionArray[counter][1] = y-j;
				counter++;
			}
			
			if(x-j >= 0 && y+j >= 0) {
				optionArray[counter][0] = x-j;
				optionArray[counter][1] = y-j;
				counter++;
			}

		}



		return optionArray;
	}

}
