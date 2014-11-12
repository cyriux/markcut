package io.markcut;

import java.util.List;

public class AsciiCanvas {

	private static final char SPACE = ' ';
	private static final char CORNER = '+';
	private static final char JOINT = '+';

	private final String[] lines;

	public AsciiCanvas(String... lines) {
		this.lines = lines;
	}

	public AsciiCanvas(List<String> lines) {
		this((String[]) lines.toArray(new String[lines.size()]));
	}

	public boolean isEndOfLine(Point p, Direction dir) {
		return nextChar(dir, p) == SPACE;
	}

	public boolean isCorner(Point p) {
		return charAt(p) == CORNER;
	}

	public boolean isDistanceJoint(Point p) {
		return charAt(p) == JOINT;
	}

	public boolean isChar(Point p, char c) {
		return charAt(p) == c;
	}

	public Direction nextDirection(Point p, Direction dir) {
		if (!isEndOfLine(p, dir)) {
			return dir;
		}
		final Direction opposite = dir.opposite();
		dir = opposite.clockwise();
		while (isEndOfLine(p, dir)) {
			dir = dir.clockwise();
			if (dir == opposite) {
				return null;
			}
		}
		return dir;
	}

	private char nextChar(Direction dir, Point p) {
		final Point nextMove = dir.move(p);
		return charAt(nextMove);
	}

	private char charAt(Point p) {
		final int y = p.y();
		if (y < 0 || y >= lines.length) {
			return SPACE;
		}
		final String line = lines[y];
		final int x = p.x();
		if (x < 0 || x >= line.length()) {
			return SPACE;
		}
		return line.charAt(x);
	}

	@Override
	public String toString() {
		return String.join("\n", lines);
	}

	public Point find(char ch) {
		for (int y = 0; y < lines.length; y++) {
			final int x = lines[y].indexOf(ch);
			if (x != -1) {
				return new Point(x, y);
			}
		}
		return null;
	}

	public String line(int y) {
		return lines[y];
	}

	public String transposedColumn(int x) {
		final StringBuilder sb = new StringBuilder();
		for (String line : lines) {
			sb.append(x < line.length() ? line.charAt(x) : ' ');
		}
		return sb.toString();
	}
}
