package io.markcut;

import static io.markcut.ParserTest.readFile;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class SvgRendererTest {

	@Test
	public void render_path() {
		final String expected = readFile("renderer.svg");
		final Shape shape = points(pt(3, 3), pt(53, 3), pt(53, 13), pt(63, 13), pt(63, 33), pt(53, 33), pt(53, 43),
				pt(23, 43), pt(23, 23), pt(3, 23));
		final String rendering = new SvgRenderer().render(shape);
		assertEquals(expected, rendering);
	}

	private static Shape points(Point... points) {
		return new Shape(Arrays.asList(points));
	}

	private static Point pt(int x, int y) {
		return new Point(x, y);
	}

}
