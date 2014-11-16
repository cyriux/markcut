package io.markcut;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Test;

public class DistanceTest {

	@Test(expected = IllegalArgumentException.class)
	public void distance_zero() {
		new Distance(0, 10, "0");
	}

	@Test(expected = IllegalArgumentException.class)
	public void distance_negative() {
		new Distance(0, 10, "-1");
	}

	@Test
	public void distance_is_number() {
		final Distance distance = new Distance(0, 10, " 13  ");
		assertEquals("13", distance.featureSize());
		assertEquals(13., distance.size(), 0);
		assertFalse(distance.hasVariable());
	}

	@Test
	public void distance_is_not_a_number() {
		final Distance distance = new Distance(0, 10, " abc  ");
		assertEquals("abc", distance.featureSize());
		assertTrue(distance.hasVariable());
	}

}
