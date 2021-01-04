package application.presentation;

import application.domain.Figure;


public class FigureVisual extends javafx.scene.shape.Rectangle {

	
	public FigureVisual(int x, int y, double width, double height, Figure figure) {
		super(x, y, width, height);
		this.figure = figure;
	}

	private Figure figure;
	
	public Figure getFigure() {
		return figure;
	}
	
	public void setFigure(Figure figure) {
		this.figure = figure;
	}
	
}
