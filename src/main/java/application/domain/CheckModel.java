package application.domain;

import java.util.Vector;

import application.domain.FigureModels.Figure;
import application.domain.FigureModels.Figure_King;

public class CheckModel {

	public CheckModel(Figure_King white, Figure_King black) {
		this.check = false;
		this.forWhite = false;
		this.kingWhite = white;
		this.kingBlack = black;
	}
	private boolean check;
	private boolean forWhite;
	private Figure_King kingWhite;
	private Figure_King kingBlack;
	
	public void checkIfCheck(boolean white, Figure[] figures, boolean[][] occupation) {
		
	}
	
	public boolean canEatKing(boolean white, Figure[] figures, boolean[][] occupation) {
		for (Figure figure: figures) {
			if(figure.isAlive() && figure.isWhite() != white) {
				if(figure.canEat(getKing(white), occupation)) {
					return true;
				}
			}
		}
		return false;
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

	public Figure_King getKingWhite() {
		return kingWhite;
	}

	public void setKingWhite(Figure_King kingWhite) {
		this.kingWhite = kingWhite;
	}

	public Figure_King getKingBlack() {
		return kingBlack;
	}

	public void setKingBlack(Figure_King kingBlack) {
		this.kingBlack = kingBlack;
	}
	public Figure_King getKing(boolean white) {
		if(white) {
			return kingWhite;
		}
		else {
			return kingBlack;
		}
	}
	

}
