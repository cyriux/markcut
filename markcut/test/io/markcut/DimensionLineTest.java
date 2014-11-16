package io.markcut;

import static io.markcut.DimensionLine.UNDEFINED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.markcut.DimensionLine.Axis;

import org.junit.Test;

public class DimensionLineTest {

	@Test
	public void dimension_line_with_2_segments() {
		final DimensionLine dimensionLine = new DimensionLine(Axis.HORIZONTAL, new Distance(10, 15, "11"),
				new Distance(15, 20, "23"));
		assertEquals(2, dimensionLine.count());
		assertFalse(dimensionLine.hasVariable());
		assertFalse(dimensionLine.isDefault());

		assertEquals(0, dimensionLine.sizeAt(10), 0.);
		assertEquals(11, dimensionLine.sizeAt(15), 0.);
		assertEquals(11 + 23, dimensionLine.sizeAt(20), 0.);

		assertEquals(UNDEFINED, dimensionLine.sizeAt(0), 0.);
		assertEquals(UNDEFINED, dimensionLine.sizeAt(14), 0.);
		assertEquals(UNDEFINED, dimensionLine.sizeAt(16), 0.);
		assertEquals(UNDEFINED, dimensionLine.sizeAt(21), 0.);
	}

	@Test
	public void default_dimension_line_with_2_segments() {
		final DimensionLine dimensionLine = new DimensionLine(Axis.HORIZONTAL);
		assertEquals(0, dimensionLine.count());
		assertFalse(dimensionLine.hasVariable());
		assertTrue(dimensionLine.isDefault());

		assertEquals(10, dimensionLine.sizeAt(10), 0.);
		assertEquals(17, dimensionLine.sizeAt(17), 0.);
		assertEquals(50, dimensionLine.sizeAt(50), 0.);
	}

}
