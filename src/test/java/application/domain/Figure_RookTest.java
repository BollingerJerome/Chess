package application.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class Figure_RookTest {

	@Test
	public void testMovementOption() {
		int[] position = new int[2];
		position[0] = 4;
		position[1] = 4;
		Figure_Rook rook = new Figure_Rook("rook", "0", true, position, true);
		
		assertTrue(rook.movementOption(4, 7));
		assertTrue(rook.movementOption(4, 1));
		assertTrue(rook.movementOption(7, 4));
		assertTrue(rook.movementOption(1, 4));
		
		assertFalse(rook.movementOption(4, 4));
		assertFalse(rook.movementOption(5, 5));
		
	}

}
