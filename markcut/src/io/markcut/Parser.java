package io.markcut;

import static io.markcut.Direction.WEST;
import io.markcut.DimensionLine.Axis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Parser {

	public Shape parseShape(final AsciiCanvas canvas, Point origin) {
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

	public DimensionLine parseHDistances(AsciiCanvas canvas) {
		final List<Distance> distances = new ArrayList<Distance>();
		final Point hDimensions = canvas.find('<');
		if (hDimensions != null) {
			final String line = canvas.line(hDimensions.y());
			final int begin = hDimensions.x();
			final int end = line.indexOf('>');
			distances.addAll(splitDistances(line, begin, end));
		}
		return new DimensionLine(Axis.HORIZONTAL, distances);
	}

	public DimensionLine parseVDistances(AsciiCanvas canvas) {
		final List<Distance> distances = new ArrayList<Distance>();
		final Point vDimensions = canvas.find('^');
		if (vDimensions != null) {
			final String line = canvas.transposedColumn(vDimensions.x());
			final int begin = vDimensions.y();
			final int end = line.indexOf('v');
			distances.addAll(splitDistances(line, begin, end));
		}
		return new DimensionLine(Axis.VERTICAL, distances);
	}

	private final static List<Distance> splitDistances(final String line, final int begin, final int end) {
		final List<Distance> distances = new ArrayList<Distance>();
		int from = begin;
		int to = begin;
		String size = "";
		for (int i = begin; i <= end; i++) {
			if (i == end || line.charAt(i) == '+') {
				to = i;
				size = line.substring(from + 1, to).trim();
				distances.add(new Distance(from, to, size));
				from = i;
			}
		}
		return distances;
	}

	public Grid parseGrid(AsciiCanvas canvas) {
		final DimensionLine h = parseHDistances(canvas);
		final DimensionLine v = parseVDistances(canvas);
		return new Grid(h, v);
	}

	public Parameters parseParameters(AsciiCanvas canvas) {
		final AsciiCanvas paramSection = canvas.subSection("parameters:");
		final Map<String, Double> map = new HashMap<String, Double>();
		for (String line : paramSection) {
			String[] keyValue = line.split("=");
			if (keyValue.length == 2) {
				final String key = keyValue[0].trim();
				final String value = keyValue[1].trim();
				map.put(key, Double.valueOf(value));
			}
		}
		return new Parameters(map);
	}
}
