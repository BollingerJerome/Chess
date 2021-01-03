package application.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class Figure_QueenTest {

	@Test
	public void testMovementOption() {
		
		
		int[] position = new int[2];
		position[0] = 4;
		position[1] = 4;
		Figure_Queen queen = new Figure_Queen("queen", "0", true, position, true);
		
		assertTrue(queen.movementOption(4, 7));
		assertTrue(queen.movementOption(4, 1));
		assertTrue(queen.movementOption(7, 4));
		assertTrue(queen.movementOption(1, 4));
		
		assertTrue(queen.movementOption(3, 3));
		assertTrue(queen.movementOption(3, 5));
		assertTrue(queen.movementOption(5, 3));
		assertTrue(queen.movementOption(5, 5));
		
		assertTrue(queen.movementOption(2, 2));
		assertTrue(queen.movementOption(2, 6));
		assertTrue(queen.movementOption(6, 2));
		assertTrue(queen.movementOption(6, 6));
		
		assertFalse(queen.movementOption(4, 4));
		assertFalse(queen.movementOption(7, 6));
		
		
	}

}
