package io.markcut;

import static org.junit.Assert.assertEquals;
import io.markcut.DimensionLine.Axis;

import java.util.Collections;

import org.junit.Test;

public class ParametersTest {

	private final Parameters parameters = new Parameters(Collections.singletonMap("abc", 27.0));
	private final static Distance D1 = new Distance(10, 15, " abc  ");
	private final static Distance D2 = new Distance(10, 15, "27");
	private final static Distance D3 = new Distance(15, 20, "23");
	private final static Distance D4 = new Distance(0, 30, "60");

	@Test
	public void evaluate_distance() {
		assertEquals(D2, parameters.evaluate(D1));
	}

	@Test
	public void evaluate_dimension_line() {
		final DimensionLine dimensionLine = new DimensionLine(Axis.HORIZONTAL, D1, D3);
		assertEquals(new DimensionLine(Axis.HORIZONTAL, D2, D3), parameters.evaluate(dimensionLine));
	}

	@Test
	public void evaluate_grid() {
		final DimensionLine h = new DimensionLine(Axis.HORIZONTAL, D1, D3);
		final DimensionLine h2 = new DimensionLine(Axis.HORIZONTAL, D2, D3);
		final DimensionLine v = new DimensionLine(Axis.HORIZONTAL, D4);
		assertEquals(new Grid(h2, v), parameters.evaluate(new Grid(h, v)));
	}
}
