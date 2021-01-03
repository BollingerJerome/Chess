package application.presentation;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameField {

	public GameField(float sideLength) {
		this.sideLength = sideLength;
		this.scene = drawScene();

	}

	private Scene scene;
	private Rectangle[][] rectangle;
	private float sideLength;

	public Scene drawScene() {

		BorderPane borderPane = new BorderPane();
		this.rectangle = new Rectangle[8][8];

		for(int i = 0; i<64; i++) {
			int x = i%8;
			int y = i/8;

			rectangle[x][y] = new Rectangle(sideLength * x, sideLength * y, sideLength, sideLength);
			
			System.out.println(x+y);
			if((x + y)%2 == 0) {
				rectangle[x][y].setFill(Color.BLACK);
			}
			else {
				rectangle[x][y].setFill(Color.WHITE);
			}

			borderPane.getChildren().add(rectangle[x][y]);
		}
		return new Scene(borderPane);
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}



}
