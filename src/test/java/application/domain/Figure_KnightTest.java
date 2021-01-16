package application.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import application.domain.FigureModels.Figure_Knight;

public class Figure_KnightTest {

	@Test
	public void testMovementOption() {
		
		int[] position = new int[2];
		position[0] = 4;
		position[1] = 4;
		Figure_Knight knight = new Figure_Knight("knight", "0", true, 4, 4, true);
		
		assertTrue(knight.movementOption(5, 6));
		assertTrue(knight.movementOption(5, 2));
		assertTrue(knight.movementOption(3, 6));
		assertTrue(knight.movementOption(3, 2));
		assertTrue(knight.movementOption(6, 5));
		assertTrue(knight.movementOption(2, 5));
		assertTrue(knight.movementOption(6, 3));
		assertTrue(knight.movementOption(3, 2));
		
		assertFalse(knight.movementOption(4, 4));
		assertFalse(knight.movementOption(3, 3));
		
	}

}
