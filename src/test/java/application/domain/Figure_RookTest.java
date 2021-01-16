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
		
	}

}
