package io.markcut;

import static org.junit.Assert.*;
import io.markcut.AsciiCanvas;
import io.markcut.Parser;
import io.markcut.Point;
import io.markcut.Shape;
import io.markcut.SvgRenderer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * See https://github.com/mses-bly/2D-Bin-Packing
 *
 */
public class MarkCutTest {

	@Test
	public void test_main() {
		double xScale = 10;
		double yScale = 10;
		int margin = 3;
		final List<String> lines = readAllLines("sample.txt");
		
		final AsciiCanvas canvas = new AsciiCanvas(lines);
		final Shape shape = new Parser().parse(canvas, Point.ZERO);
		final String svg = new SvgRenderer().render(shape.scale(xScale, yScale).translate(margin, margin));

		final String expected = SvgRendererTest.readFile("sample.svg");
		assertEquals(expected, svg);
	}

	public static List<String> readAllLines(final String filename) {
		final Class<? extends ParserTest> clazz = ParserTest.class;
		final List<String> lines = new ArrayList<String>();
		try {
			final BufferedReader in = new BufferedReader(new InputStreamReader(clazz.getResourceAsStream(filename)));
			String line = null;
			while ((line = in.readLine()) != null) {
				lines.add(line);
			}
			in.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
