package application.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import application.domain.FigureModels.Figure_Pawn;

public class Figure_PawnTest {

	@Test
	public void testMovementOption() {

		Figure_Pawn pawnWhite = new Figure_Pawn("pawnWhite", "0", true, 4, 4, true);
		
		assertTrue(pawnWhite.movementOption(4,5));
		assertFalse(pawnWhite.movementOption(4, 3));
		
		
		Figure_Pawn pawnBlack = new Figure_Pawn("pawnWhite", "0", true, 4, 4, false);
		
		assertTrue(pawnBlack.movementOption(4,3));
		assertFalse(pawnBlack.movementOption(4, 5));
		
	}

}
