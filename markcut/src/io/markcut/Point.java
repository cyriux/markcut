package io.markcut;

public final class Point {

	private final int x;
	private final int y;

	public final static Point ZERO = new Point(0, 0);

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public Point scale(double xScale, double yScale) {
		return new Point((int) (x * xScale), (int) (y * yScale));
	}

	public Point translate(int dx, int dy) {
		return new Point(x + dx, y + dy);
	}

	@Override
	public int hashCode() {
		return x ^ y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		final Point other = (Point) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}