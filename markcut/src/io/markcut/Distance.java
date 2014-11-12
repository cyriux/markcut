package io.markcut;

import java.util.Arrays;
import java.util.List;

public class Distance {

	public enum Axis {
		HORIZONTAL, VERTICAL;
	}

	private final Axis axis;
	private final int from;
	private final int to;
	private final String size;

	public final static List<Distance> distances(Distance... distances) {
		return Arrays.asList(distances);
	}

	public Distance(Axis axis, int from, int to, String size) {
		this.axis = axis;
		this.from = from;
		this.to = to;
		this.size = size;
	}

	public Axis axis() {
		return axis;
	}

	public int from() {
		return from;
	}

	public int to() {
		return to;
	}

	public String size() {
		return size;
	}

	@Override
	public int hashCode() {
		return (int) (axis.hashCode() + axis.hashCode() + 31 * size.hashCode() + to);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Distance)) {
			return false;
		}
		final Distance other = (Distance) obj;
		return axis == other.axis && from == other.from && to == other.to && size.equals(other.size);
	}

	@Override
	public String toString() {
		return axis.name().substring(0, 1) + "[" + from + " - " + to + "]=" + size;
	}
}
