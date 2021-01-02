package com.sweetmanor.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

class RandomIntTest {

	/**
	 * random方法返回数组大小和最大值符合预期
	 */
	@Test
	void testRandom() {
		int[] array = RandomInt.random();
		assertEquals(RandomInt.COUNT, array.length);
		int max = NumberUtils.max(array);
		assertTrue(max < RandomInt.MAX);
	}

	/**
	 * random(int)方法返回数组大小和最大值符合预期
	 */
	@Test
	void testRandomInt() {
		int count = 100;
		int[] array = RandomInt.random(count);
		assertEquals(count, array.length);
		int max = NumberUtils.max(array);
		assertTrue(max < RandomInt.MAX);
	}

	/**
	 * getLittleArray方法返回数组大小符合预期
	 */
	@Test
	void testGetLittleArray() {
		int[] array = RandomInt.getLittleArray();
		assertEquals(RandomInt.LITTLE_ARRAY, array.length);
	}

	/**
	 * getMediumArray方法返回数组大小符合预期
	 */
	@Test
	void testGetMediumArray() {
		int[] array = RandomInt.getMediumArray();
		assertEquals(RandomInt.MEDIUM_ARRAY, array.length);
	}

	/**
	 * getLargeArray方法返回数组大小符合预期
	 */
	@Test
	void testGetLargeArray() {
		int[] array = RandomInt.getLargeArray();
		assertEquals(RandomInt.LARGE_ARRAY, array.length);
	}

	/**
	 * random(int, int)方法返回数组大小和最大值符合预期
	 */
	@Test
	void testRandomTwoArgs() {
		int count = 10000;
		int limit = 1000;
		int[] array = RandomInt.random(count, limit);
		assertEquals(count, array.length);
		int max = NumberUtils.max(array);
		assertTrue(max < limit);
	}

	/**
	 * random(long, int, int)方法返回数组大小和上下限符合预期
	 */
	@Test
	void testRandomThreeArgs() {
		long count = 10000L;
		int lower = 100;
		int upper = 100_000_000;
		int[] array = RandomInt.random(count, lower, upper);
		assertEquals(count, array.length);

		int min = NumberUtils.min(array);
		assertTrue(lower < min);
		int max = NumberUtils.max(array);
		assertTrue(max < upper);
	}

}
