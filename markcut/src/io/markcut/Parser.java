package io.markcut;

import static io.markcut.Direction.WEST;
import io.markcut.Distance.Axis;

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

	public List<Distance> parseDistances(AsciiCanvas canvas) {
		final List<Distance> distances = new ArrayList<Distance>();
		final Point hDimensions = canvas.find('<');
		if (hDimensions != null) {
			final String line = canvas.line(hDimensions.y());
			final int begin = hDimensions.x();
			final int end = line.indexOf('>');
			distances.addAll(splitDistances(line, begin, end, Axis.HORIZONTAL));
		}

		final Point vDimensions = canvas.find('^');
		if (vDimensions != null) {
			final String line = canvas.transposedColumn(vDimensions.x());
			final int begin = vDimensions.y();
			final int end = line.indexOf('v');
			distances.addAll(splitDistances(line, begin, end, Axis.VERTICAL));
		}

		return distances;
	}

	private final static List<Distance> splitDistances(final String line, final int begin, final int end, Axis axis) {
		final List<Distance> distances = new ArrayList<Distance>();
		int from = begin;
		int to = begin;
		String size = "";
		for (int i = begin; i <= end; i++) {
			if (i == end || line.charAt(i) == '+') {
				to = i;
				size = line.substring(from + 1, to).trim();
				distances.add(new Distance(axis, from, to, size));
				from = i;
			}
		}
		return distances;
	}
}
