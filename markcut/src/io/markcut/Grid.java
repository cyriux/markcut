package io.markcut;

import java.util.ArrayList;
import java.util.List;

public class Grid {

	private final int[] xs;
	private final int[] ys;

	public Grid(int[] xs, int[] ys) {
		this.xs = xs;
		this.ys = ys;
	}

	public Shape warp(Shape shape) {
		final List<Point> scaled = new ArrayList<Point>();
		for (Point p : shape) {
			scaled.add(new Point(xs[p.x()], ys[p.y()]));
		}
		return new Shape(scaled);
	}

}
