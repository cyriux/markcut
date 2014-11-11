package io.markcut;

import java.util.List;

public class AsciiCanvas {

	private static final char SPACE = ' ';
	private static final char CORNER = '+';

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
}
