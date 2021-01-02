package application.domain;

public class Figure_King extends Figure implements Movement {



	public Figure_King(String name, String id, boolean alive, int[] position) {
		super(name, id, alive, position);
	}

	public void move(int posX, int posY) {
		int[] newPosition = new int[2];
		newPosition[0] = posX;
		newPosition[1] = posY;
		this.setPosition(newPosition);
	}

	public int[][] movementOption() {
		
		int x = this.getPosition()[0];
		int y = this.getPosition()[1];
		int[][] optionArray = new int[8][2];
		int counter = 0;
		
		boolean left = x>0;
		boolean right = x<7;
		boolean front = y>0;
		boolean back = y<7;
		
		if(left) {
			optionArray[counter][0] = x--;
			optionArray[counter][1] = y;
			counter++;
			
			if(back) {
				optionArray[counter][0] = x--;
				optionArray[counter][1] = y--;
				counter++;
			}
			else if(front) {
				optionArray[counter][0] = x--;
				optionArray[counter][1] = y++;
				counter++;
			}
			
			
		}
		else if(right) {
			optionArray[counter][0] = x++;
			optionArray[counter][1] = y;
			
			if(back) {
				optionArray[counter][0] = x++;
				optionArray[counter][1] = y--;
				counter++;
			}
			else if(front) {
				optionArray[counter][0] = x++;
				optionArray[counter][1] = y++;
				counter++;
			}
			
		}
		
		if(back) {
			optionArray[counter][0] = x;
			optionArray[counter][1] = y--;
			counter++;
		}
		
		if(front) {
			optionArray[counter][0] = x;
			optionArray[counter][1] = y++;
			counter++;
		}
		
		return optionArray;

	}

}
