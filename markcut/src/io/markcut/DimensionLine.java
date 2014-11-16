package io.markcut;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DimensionLine implements Iterable<Distance> {

	public enum Axis {
		HORIZONTAL, VERTICAL;
	}

	public final static double UNDEFINED = -1;

	private final Axis axis;
	private final List<Distance> distances;

	public DimensionLine(Axis axis, List<Distance> distances) {
		this.axis = axis;
		this.distances = distances;
	}

	public DimensionLine(Axis axis, Distance... distances) {
		this(axis, Arrays.asList(distances));
	}

	@Override
	public Iterator<Distance> iterator() {
		return distances.iterator();
	}

	public int count() {
		return distances.size();
	}

	public Axis getAxis() {
		return axis;
	}

	public boolean hasVariable() {
		for (Distance distance : distances) {
			if (distance.hasVariable()) {
				return true;
			}
		}
		return false;
	}

	public boolean isDefault() {
		return distances.isEmpty();
	}

	public double sizeAt(int index) {
		if (isDefault()) {
			return index;
		}
		if (index == distances.get(0).from()) {
			return 0.;
		}
		double total = 0;
		for (Distance d : distances) {
			total += d.size();
			if (index == d.to()) {
				return total;
			}
		}
		return UNDEFINED;
	}

	@Override
	public int hashCode() {
		return 43 * axis.hashCode() + distances.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DimensionLine)) {
			return false;
		}
		final DimensionLine other = (DimensionLine) obj;
		return axis == other.axis && distances.equals(other.distances);
	}

	@Override
	public String toString() {
		return distances.toString();
	}

}
