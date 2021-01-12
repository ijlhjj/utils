package com.sweetmanor.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.math3.primes.Primes;
import org.junit.jupiter.api.Test;

public class MathUtilTest {

	/**
	 * 测试素数判断方法
	 */
	@Test
	public void testIsPrime() {
		for (int i = 0; i < 10000; i++) {
			assertEquals(Primes.isPrime(i), MathUtil.isPrime(i));
		}
	}

}
