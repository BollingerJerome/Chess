package application.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class Figure_PawnTest {

	@Test
	public void testMovementOption() {
		
		int[] position = new int[2];
		position[0] = 4;
		position[1] = 4;
		Figure_Pawn pawnWhite = new Figure_Pawn("pawnWhite", "0", true, position, true);
		
		assertTrue(pawnWhite.movementOption(4,5));
		assertFalse(pawnWhite.movementOption(4, 3));
		
		
		Figure_Pawn pawnBlack = new Figure_Pawn("pawnWhite", "0", true, position, false);
		
		assertTrue(pawnBlack.movementOption(4,3));
		assertFalse(pawnBlack.movementOption(4, 5));
		
	}

}
