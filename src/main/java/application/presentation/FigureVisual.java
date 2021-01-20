package application.presentation;

import com.sun.glass.events.MouseEvent;

import application.domain.FigureModels.Figure;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class FigureVisual extends Rectangle {

	
	public FigureVisual(double x, double y, double sideLength, Figure figure) {
		super(x*sideLength, y*sideLength, sideLength, sideLength);
		this.figure = figure;
		this.name = new Text(x*sideLength+sideLength*0.25, y*sideLength+sideLength*0.5, figure.getName());
	}

	
	
	private Figure figure;
	private Text name;


	public Figure getFigure() {
		return figure;
	}
	public void setFigure(Figure figure) {
		this.figure = figure;
	}
	public Text getName() {
		return name;
	}
	public void setName(Text name) {
		this.name = name;
	}
	
	
	
}
