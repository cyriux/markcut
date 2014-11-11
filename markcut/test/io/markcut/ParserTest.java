package io.markcut;

import static io.markcut.Direction.DOWN;
import static io.markcut.Direction.WEST;
import static org.junit.Assert.assertEquals;
import io.markcut.AsciiCanvas;
import io.markcut.Direction;
import io.markcut.Distance;
import io.markcut.Parser;
import io.markcut.Point;
import io.markcut.Shape;
import io.markcut.Distance.Axis;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;

/**
 * First attempt to parse ascii diagrams was by just text parsing line by line,
 * but it quickly highlighted the concept of direction of the trait.
 * 
 * Refactoring to incorporate the direction approach proved more difficult than
 * expected, because of the missing concept of end of line, and from then the
 * also missing concept of finding the next direction.
 * 
 * In this new perspective, previously simple baby-steps scenarios like the
 * straight line became more difficult than rectangles. However once solved,
 * more complicated shapes were easy to parse, as long as they start at (0, 0).
 * To generalize, there was the need to include the concept of origin point.
 * 
 * Along this journey, the code suggested refactorings to concepts like the
 * ascii canvas and the recognized shape. Everything is immutable and value
 * object so far.
 * 
 * @see http://www.keppel.demon.co.uk/111000/111000.html
 */
public class ParserTest {

	@Test
	public void line() {
		String text = "++";
		assertEquals(points(pt(0, 0), pt(1, 0)), parse(text));
	}

	@Test
	public void rectangle1x1() {
		String text = "++";
		assertEquals(points(pt(0, 0), pt(1, 0), pt(1, 1), pt(0, 1)), parse(text, text));
	}

	@Test
	public void rectangle2x1() {
		String text = "+-+";
		assertEquals(points(pt(0, 0), pt(2, 0), pt(2, 1), pt(0, 1)), parse(text, text));
	}

	@Test
	public void rectangle2x1_with_space_after() {
		String text = "+-+  ";
		assertEquals(points(pt(0, 0), pt(2, 0), pt(2, 1), pt(0, 1)), parse(text, text));
	}

	@Test
	public void rectange5x2() {
		String line = "+----+";
		String mid = "|    |";
		assertEquals(points(pt(0, 0), pt(5, 0), pt(5, 2), pt(0, 2)), parse(line, mid, line));
	}

	@Test
	public void rectange5x3_top_right_hole() {
		String top = "+----+";
		String mid = "|    ++";
		String mid2 = "|     |";
		String bottom = "+-----+";
		assertEquals(points(pt(0, 0), pt(5, 0), pt(5, 1), pt(6, 1), pt(6, 3), pt(0, 3)), parse(top, mid, mid2, bottom));
	}

	@Test
	public void rectange5x3_top_left_hole() {
		String line1 = "  +----+";
		String line2 = "+-+    |";
		String line3 = "|      |";
		String line4 = "+------+";
		assertEquals(points(pt(2, 0), pt(7, 0), pt(7, 3), pt(0, 3), pt(0, 1), pt(2, 1)),
				parse(new Point(2, 0), line1, line2, line3, line4));
	}

	@Test
	public void random_closed_shape() {
		String line1 = "+----+";
		String line2 = "|    ++";
		String line3 = "+-+   |";
		String line4 = "  |  ++";
		String line5 = "  +--+";
		assertEquals(
				points(pt(0, 0), pt(5, 0), pt(5, 1), pt(6, 1), pt(6, 3), pt(5, 3), pt(5, 4), pt(2, 4), pt(2, 2),
						pt(0, 2)), parse(line1, line2, line3, line4, line5));
	}

	@Test
	@Ignore("need to check space OR non expected character")
	public void rectange5x2_top_right_hole() {
		String line1 = "+----+";
		String line2 = "|    ++";
		String line3 = "+-----+";
		assertEquals(points(pt(0, 0), pt(5, 0), pt(5, 1), pt(6, 1), pt(6, 2), pt(0, 2)), parse(line1, line2, line3));
	}

	@Test
	public void dimension_horizontal_line() {
		String line1 = "< 2 >";
		assertEquals(new Distance(Axis.HORIZONTAL, 0, 4, "2"), parseDistance(line1));
	}

	@Test
	public void dimension_horizontal_line_bis() {
		String line1 = "< 12.50 >  ";
		assertEquals(new Distance(Axis.HORIZONTAL, 0, 8, "12.50"), parseDistance(line1));
	}

	@Test
	public void dimension_vertical_line() {
		String line1 = "^";
		String line2 = " ";
		String line3 = "5";
		String line4 = " ";
		String line5 = "v";

		assertEquals(new Distance(Axis.VERTICAL, 0, 4, "5"), parseDistance(line1, line2, line3, line4, line5));
	}

	private Distance parseDistance(String... lines) {
		final AsciiCanvas canvas = new AsciiCanvas(lines);
		final Point origin = Point.ZERO;

		final Axis axis = canvas.isChar(origin, '<') ? Axis.HORIZONTAL : Axis.VERTICAL;
		final Direction dir = axis == Axis.HORIZONTAL ? WEST : DOWN;
		final char closingChar = axis == Axis.HORIZONTAL ? '>' : 'v';

		int from = 0;
		int to = 0;
		Point p = origin;
		from = dir.coordinate(p);
		while (!canvas.isChar(p, closingChar)) {
			p = dir.move(p);
		}
		to = dir.coordinate(p);
		if (dir == WEST) {
			final String size = lines[dir.coordinate(origin)].substring(from + 1, to - 1).trim();
			return new Distance(Axis.HORIZONTAL, from, to, size);
		}
		final String size = lines[lines.length / 2].substring(0, 1);
		return new Distance(Axis.VERTICAL, from, to, size);
	}

	private static Shape points(Point... points) {
		return new Shape(Arrays.asList(points));
	}

	private static Point pt(int x, int y) {
		return new Point(x, y);
	}

	private Shape parse(String... lines) {
		return parse(Point.ZERO, lines);
	}

	private Shape parse(Point origin, String... lines) {
		final AsciiCanvas canvas = new AsciiCanvas(lines);
		return new Parser().parse(canvas, origin);
	}

}
