package application.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Figure_KingTest {

	@Test
	public void testMovementOption() {
		
		//setting a King on position 4,4
		int[] position = new int[2];
		position[0] = 4;
		position[1] = 4;
		Figure_King king = new Figure_King("king", "0", true, position, true);
		
		//one around the king
		assertTrue(king.movementOption(3, 3));
		assertTrue(king.movementOption(3, 5));
		assertTrue(king.movementOption(5, 3));
		assertTrue(king.movementOption(5, 5));
		assertTrue(king.movementOption(4, 5));
		assertTrue(king.movementOption(4, 3));
		assertTrue(king.movementOption(3, 4));
		assertTrue(king.movementOption(5, 4));
		
		//the kings position
		assertFalse(king.movementOption(4, 4));
		
		//somewhere remote
		assertFalse(king.movementOption(8, 3));
		assertFalse(king.movementOption(3, 8));
		
		//the edge
		position[0] = 6;
		position[1] = 6;
		king = new Figure_King("king", "0", true, position, true);
		
		assertTrue(king.movementOption(7, 7));
		
	}

}
