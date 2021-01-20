package application.domain.FigureModels;

public interface Movement {

	
	public boolean movementOption(int x, int y);
	public boolean movementOption(int x, int y, boolean[][] occupation);
	
}
