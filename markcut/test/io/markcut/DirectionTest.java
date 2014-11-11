package io.markcut;

import static io.markcut.Direction.*;
import static org.junit.Assert.*;
import io.markcut.Point;

import org.junit.Test;

public class DirectionTest {

	@Test
	public void clockwise() throws Exception {
		assertEquals(DOWN, WEST.clockwise());
		assertEquals(EAST, DOWN.clockwise());
		assertEquals(UP, EAST.clockwise());
		assertEquals(WEST, UP.clockwise());
	}

	@Test
	public void opposite() throws Exception {
		assertEquals(DOWN, UP.opposite());
		assertEquals(UP, DOWN.opposite());
		assertEquals(EAST, WEST.opposite());
		assertEquals(WEST, EAST.opposite());
	}

	@Test
	public void coordinate() throws Exception {
		Point p = new Point(3, 8);
		assertEquals(3, WEST.coordinate(p));
		assertEquals(3, EAST.coordinate(p));
		assertEquals(8, UP.coordinate(p));
		assertEquals(8, DOWN.coordinate(p));
	}
}
