package io.markcut;

import static org.junit.Assert.*;
import io.markcut.Grid;
import io.markcut.Point;
import io.markcut.Shape;

import org.junit.Test;

public class GridTest {

	@Test
	public void grid_warp() {
		final Shape shape = new Shape(new Point(0, 0), new Point(3, 0), new Point(3, 1), new Point(0, 1));
		final Shape expected = new Shape(new Point(13, 10), new Point(40, 10), new Point(40, 15), new Point(13, 15));
		assertEquals(expected, new Grid(new int[] { 13, 20, 30, 40 }, new int[] { 10, 15 }).warp(shape));
	}

}
