package application.presentation;

import com.sun.glass.events.MouseEvent;

import application.domain.FigureModels.Figure;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;

public class FigureVisual extends Rectangle {

	
	public FigureVisual(double x, double y, double sideLength, Figure figure) {
		super(x*sideLength, y*sideLength, sideLength, sideLength);
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
