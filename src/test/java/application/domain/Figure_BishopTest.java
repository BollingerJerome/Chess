package application.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.domain.FigureModels.Figure_Bishop;

public class Figure_BishopTest {

	@Test
	public void testMovementOption() {

		Figure_Bishop bishop = new Figure_Bishop("bishop", "0", true, 4, 4, true);
		
		assertTrue(bishop.movementOption(3, 3));
		assertTrue(bishop.movementOption(3, 5));
		assertTrue(bishop.movementOption(5, 3));
		assertTrue(bishop.movementOption(5, 5));
		
		assertTrue(bishop.movementOption(2, 2));
		assertTrue(bishop.movementOption(2, 6));
		assertTrue(bishop.movementOption(6, 2));
		assertTrue(bishop.movementOption(6, 6));
		
		assertFalse(bishop.movementOption(4, 4));
		assertFalse(bishop.movementOption(1, 2));
	}

}
