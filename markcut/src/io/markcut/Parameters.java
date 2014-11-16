package io.markcut;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parameters {

	private final Map<String, Double> params;

	public Parameters(Map<String, Double> map) {
		this.params = map;
	}

	protected Distance evaluate(Distance distance) {
		if (!distance.hasVariable()) {
			return distance;
		}
		return new Distance(distance.from(), distance.to(), "" + params.get(distance.featureSize()));
	}

	protected DimensionLine evaluate(DimensionLine dl) {
		if (!dl.hasVariable()) {
			return dl;
		}
		final List<Distance> evaluated = new ArrayList<Distance>();
		for (Distance distance : dl) {
			evaluated.add(evaluate(distance));
		}
		return new DimensionLine(dl.getAxis(), evaluated);
	}

	public Grid evaluate(Grid grid) {
		return new Grid(evaluate(grid.getHorizontalDimensionLine()), evaluate(grid.getVerticalDimensionLine()));
	}

}
