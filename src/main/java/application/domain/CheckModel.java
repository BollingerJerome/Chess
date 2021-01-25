package application.domain;

import java.util.Vector;

import application.domain.FigureModels.Figure;
import application.domain.FigureModels.Figure_King;

public class CheckModel {

	public CheckModel() {
		this.check = false;
		this.forWhite = false;
	}
	private boolean check;
	private boolean forWhite;
	
	public void checkIfCheck(boolean white, Figure[] figures, boolean[][] occupation) {
		
	}
	
	public Vector<Figure> canEatKing(boolean white, Figure[] figures, boolean[][] occupation) {
		Figure king = null;
		Vector<Figure> threats = new Vector<Figure>();
		for (Figure figure: figures) {
			if(figure instanceof Figure_King && figure.isWhite() != white) {
				king = figure;
			}
		}
		for (Figure figure: figures) {
			if(figure.isAlive() && figure.isWhite() == white) {
				if(figure.canEat(king, occupation)) {
					threats.add(figure);
				}
			}
		}
		return threats;
	}
	
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check, boolean white) {
		this.check = check;
		this.forWhite = white;
	}
	private boolean isForWhite() {
		return forWhite;
	}
	public void setForWhite(boolean forWhite) {
		this.forWhite = forWhite;
	}
	

}
