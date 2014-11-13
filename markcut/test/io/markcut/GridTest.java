package io.markcut;

import static org.junit.Assert.assertEquals;
import io.markcut.DimensionLine.Axis;

import org.junit.Test;

public class GridTest {

	@Test
	public void grid_warp() {
		final DimensionLine h = new DimensionLine(Axis.HORIZONTAL, new Distance(10, 15, "11"));
		final DimensionLine v = new DimensionLine(Axis.VERTICAL, new Distance(1, 9, "21"));
		final Grid grid = new Grid(h, v);

		final Shape shape = new Shape(new Point(10, 1), new Point(15, 1), new Point(15, 9), new Point(10, 9));
		final Shape expected = new Shape(new Point(0, 0), new Point(11, 0), new Point(11, 21), new Point(0, 21));
		assertEquals(expected, grid.warp(shape));
	}

}
