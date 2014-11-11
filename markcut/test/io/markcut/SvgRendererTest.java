package io.markcut;

import static org.junit.Assert.assertEquals;
import io.markcut.Point;
import io.markcut.Shape;
import io.markcut.SvgRenderer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.junit.Test;

public class SvgRendererTest {

	@Test
	public void render_path() {
		final String expected = readFile("sample.svg");
		final Shape shape = points(pt(3, 3), pt(53, 3), pt(53, 13), pt(63, 13), pt(63, 33), pt(53, 33), pt(53, 43),
				pt(23, 43), pt(23, 23), pt(3, 23));
		final String rendering = new SvgRenderer().render(shape);
		System.out.println(rendering);
		assertEquals(expected, rendering);
	}

	private static Shape points(Point... points) {
		return new Shape(Arrays.asList(points));
	}

	private static Point pt(int x, int y) {
		return new Point(x, y);
	}

	/**
	 * @return A String that represents the content of the file
	 */
	public static String readFile(final String filename) {
		final Class<? extends ParserTest> clazz = ParserTest.class;
		final StringBuilder buffer = new StringBuilder();
		try {
			final BufferedReader in = new BufferedReader(new InputStreamReader(clazz.getResourceAsStream(filename)));
			String str = null;
			while ((str = in.readLine()) != null) {
				buffer.append(str);
				buffer.append("\n");
			}
			in.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

}
