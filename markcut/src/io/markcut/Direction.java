package io.markcut;

public enum Direction {

	WEST {
		Point move(Point p) {
			return new Point(p.x() + 1, p.y());
		}
	},
	DOWN {
		Point move(Point p) {
			return new Point(p.x(), p.y() + 1);
		}
	},
	EAST {
		Point move(Point p) {
			return new Point(p.x() - 1, p.y());
		}
	},
	UP {
		Point move(Point p) {
			return new Point(p.x(), p.y() - 1);
		}
	};

	Point move(Point p) {
		throw new IllegalArgumentException("Direction.next() should only be called on an enum constant");
	}

	int coordinate(Point p) {
		return ordinal() % 2 == 0 ? p.x() : p.y();
	}

	public Direction clockwise() {
		return roll(1);
	}

	public Direction opposite() {
		return roll(size() / 2);
	}

	private Direction roll(final int index) {
		return values()[(ordinal() + index) % size()];
	}

	private int size() {
		return values().length;
	}

}
