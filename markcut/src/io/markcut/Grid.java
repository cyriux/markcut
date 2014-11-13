package io.markcut;

import java.util.ArrayList;
import java.util.List;

public class Grid {

	private final DimensionLine h;
	private final DimensionLine v;

	public Grid(DimensionLine h, DimensionLine v) {
		this.h = h;
		this.v = v;
	}

	public Shape warp(Shape shape) {
		final List<Point> scaled = new ArrayList<Point>();
		for (Point p : shape) {
			scaled.add(new Point((int) h.sizeAt(p.x()), (int) v.sizeAt(p.y())));
		}
		return new Shape(scaled);
	}

	public DimensionLine getHorizontalDimensionLine() {
		return h;
	}

	public DimensionLine getVerticalDimensionLine() {
		return v;
	}

	@Override
	public int hashCode() {
		return h.hashCode() ^ v.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Grid)) {
			return false;
		}
		Grid other = (Grid) obj;
		return h.equals(other.h) && v.equals(other.v);
	}

}
