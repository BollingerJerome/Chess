package application.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import application.domain.FigureModels.Figure_Rook;

public class Figure_RookTest {

	@Test
	public void testMovementOption() {

		Figure_Rook rook = new Figure_Rook("rook", "0", true, 4, 4, true);
		
		assertTrue(rook.movementOption(4, 7));
		assertTrue(rook.movementOption(4, 1));
		assertTrue(rook.movementOption(7, 4));
		assertTrue(rook.movementOption(1, 4));
		
		assertFalse(rook.movementOption(4, 4));
		assertFalse(rook.movementOption(5, 5));
		
		
		
		boolean[][] feld = new boolean[8][8];
		for(int i = 0; i<64; i++) {
			feld[i%8][i/8] = false;
		}
		
		feld[2][0] = true;
		
		rook.setX(5);
		rook.setY(0);
		assertFalse(rook.movementOption(0, 0, feld));
		assertFalse(rook.movementOption(1, 0, feld));
		assertFalse(rook.movementOption(2, 0, feld));
		assertTrue(rook.movementOption(3, 0, feld));
	}

}
