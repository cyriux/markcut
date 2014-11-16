package io.markcut;

import static io.markcut.ParserTest.*;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

/**
 * See https://github.com/mses-bly/2D-Bin-Packing
 * 
 */
public class MarkCutTest {

	@Test
	public void test_main() {
		final List<String> lines = readAllLines("sample.txt");

		final AsciiCanvas canvas = new AsciiCanvas(lines);
		final Parser parser = new Parser();

		final Point origin = canvas.find('+');
		final Shape contour = parser.parseShape(canvas, origin);

		final Grid grid = parser.parseGrid(canvas);
		final Shape shape = grid.warp(contour);
		
		final int margin = 3;
		final String svg = new SvgRenderer().render(shape.translate(margin, margin));

		final String expected = readFile("sample.svg");
		assertEquals(expected, svg);
	}

}
