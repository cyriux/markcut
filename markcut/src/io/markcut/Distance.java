package io.markcut;

public class Distance {

	private final int from;
	private final int to;
	private final String featureSize;
	private final Double size;

	public Distance(int from, int to, String featureSize) {
		this.from = from;
		this.to = to;
		this.featureSize = featureSize.trim();
		this.size = tryParse(featureSize);
		if (size != null && size <= 0) {
			throw new IllegalArgumentException("Size must be strictly positive");
		}
	}

	public int from() {
		return from;
	}

	public int to() {
		return to;
	}

	public double size() {
		return size;
	}

	public String featureSize() {
		return featureSize;
	}

	private static final Double tryParse(String featureSize) {
		try {
			return Double.parseDouble(featureSize);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public boolean hasVariable() {
		return size == null;
	}

	@Override
	public int hashCode() {
		return (int) (31 * size.hashCode() + to);
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
		return from == other.from && to == other.to && size.equals(other.size);
	}

	@Override
	public String toString() {
		return "Distance from: " + from + " to: " + to + " of size: " + size;
	}

}
