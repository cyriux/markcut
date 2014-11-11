package io.markcut;

import static io.markcut.Direction.WEST;

import java.util.ArrayList;
import java.util.List;

public class Parser {

	public Shape parse(final AsciiCanvas canvas, Point origin) {
		final List<Point> points = new ArrayList<Point>();
		Point p = origin;
		Direction dir = WEST;
		while (!points.contains(p)) {
			if (canvas.isCorner(p)) {
				points.add(p);
			}
			dir = canvas.nextDirection(p, dir);
			p = dir == null ? p : dir.move(p);
		}
		return new Shape(points);
	}
}
