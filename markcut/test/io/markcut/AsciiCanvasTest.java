package io.markcut;

import static org.junit.Assert.*;
import io.markcut.AsciiCanvas;
import io.markcut.Direction;
import io.markcut.Point;

import org.junit.Test;

public class AsciiCanvasTest {

	@Test
	public void isEndOfLine() {
		final AsciiCanvas canvas = new AsciiCanvas("+-+ ", "+-+");
		assertFalse(canvas.isEndOfLine(Point.ZERO, Direction.WEST));
		assertFalse(canvas.isEndOfLine(new Point(1, 0), Direction.WEST));
		assertTrue(canvas.isEndOfLine(new Point(2, 0), Direction.WEST));

		assertFalse(canvas.isEndOfLine(new Point(2, 0), Direction.DOWN));
		assertTrue(canvas.isEndOfLine(new Point(2, 1), Direction.DOWN));

		assertFalse(canvas.isEndOfLine(new Point(1, 0), Direction.EAST));
		assertTrue(canvas.isEndOfLine(new Point(0, 0), Direction.EAST));

		assertFalse(canvas.isEndOfLine(new Point(0, 1), Direction.UP));
		assertTrue(canvas.isEndOfLine(new Point(0, 0), Direction.UP));
		assertTrue(canvas.isEndOfLine(new Point(1, 0), Direction.UP));
	}

	@Test
	public void nextDir() {
		final AsciiCanvas line = new AsciiCanvas("++ ");
		assertEquals(Direction.WEST, line.nextDirection(Point.ZERO, Direction.WEST));
		assertNull(line.nextDirection(new Point(1, 0), Direction.WEST));
	}

	@Test
	public void isCorner() {
		final AsciiCanvas canvas = new AsciiCanvas("+-+ ", "+-+");
		assertTrue(canvas.isCorner(Point.ZERO));
		assertFalse(canvas.isCorner(new Point(1, 0)));
		assertTrue(canvas.isCorner(new Point(2, 0)));
	}

	@Test
	public void find() {
		final AsciiCanvas canvas = new AsciiCanvas("         ", "  +-+ ", "      ", "    <  >");
		assertEquals(new Point(4, 3), canvas.find('<'));
	}

	@Test
	public void find_first_corner() {
		String line1 = "  +----+";
		String line2 = "+-+    |";
		assertEquals(new Point(2, 0), new AsciiCanvas(line1, line2).find('+'));
	}

	@Test
	public void transposedColumn() {
		final AsciiCanvas canvas = new AsciiCanvas(" ^", "", "      ", " v  ");
		assertEquals("^  v", canvas.transposedColumn(1));
	}

	@Test
	public void subSection() {
		final AsciiCanvas canvas = new AsciiCanvas(" blabla", "", "SectionDelimiter      ", " next content");
		assertEquals(new AsciiCanvas(" next content"), canvas.subSection("SectionDelimiter"));
	}

}
