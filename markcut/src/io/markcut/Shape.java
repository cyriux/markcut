package io.markcut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Shape implements Iterable<Point> {

	private final List<Point> points;

	public Shape(Point... points) {
		this(Arrays.asList(points));
	}

	public Shape(List<Point> points) {
		this.points = Collections.unmodifiableList(points);
	}

	@Override
	public Iterator<Point> iterator() {
		return points.iterator();
	}

	public Shape scale(double xScale, double yScale) {
		final List<Point> scaled = new ArrayList<Point>();
		for (Point p : points) {
			scaled.add(p.scale(xScale, yScale));
		}
		return new Shape(scaled);
	}

	public Shape translate(int dx, int dy) {
		final List<Point> shifted = new ArrayList<Point>();
		for (Point p : points) {
			shifted.add(p.translate(dx, dy));
		}
		return new Shape(shifted);
	}

	@Override
	public int hashCode() {
		return points.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Shape)) {
			return false;
		}
		final Shape other = (Shape) obj;
		return points.equals(other.points);
	}

	@Override
	public String toString() {
		return points.toString();
	}

}
