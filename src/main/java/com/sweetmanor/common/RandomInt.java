package com.sweetmanor.common;

import java.util.Random;

/**
 * 随机整型数组生成器
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class RandomInt {
	static final int MAX = 100; // 默认生成最大值（不包括）
	static final int COUNT = 10;// 默认生成元素个数
	public static final int LITTLE_ARRAY = 10000;
	public static final int MEDIUM_ARRAY = 1_000_000;
	public static final int LARGE_ARRAY = 100_000_000;

	/**
	 * 生成默认随机数组
	 */
	public static int[] random() {
		return random(COUNT);
	}

	/**
	 * 生成数组大小为count的随机数组
	 * 
	 * @param count 生成元素个数
	 * @return 返回生成的随机数组
	 */
	public static int[] random(int count) {
		return random(count, MAX);
	}

	/**
	 * 生成数组大小为 LITTLE_ARRAY 的随机数组
	 */
	public static int[] getLittleArray() {
		return random(LITTLE_ARRAY, Integer.MAX_VALUE);
	}

	/**
	 * 生成数组大小为 MEDIUM_ARRAY 的随机数组
	 */
	public static int[] getMediumArray() {
		return random(MEDIUM_ARRAY, Integer.MAX_VALUE);
	}

	/**
	 * 生成数组大小为 LARGE_ARRAY 的随机数组
	 */
	public static int[] getLargeArray() {
		return random(LARGE_ARRAY, Integer.MAX_VALUE);
	}

	/**
	 * 生成包含count个元素，最大值为max(不包括)的随机数组
	 * 
	 * @param count 生成元素个数，count小于1时返回null
	 * @param max   最大值(不包括)
	 * @return 返回生成的随机数组
	 */
	public static int[] random(int count, int max) {
		if (count < 1)
			return null;

		int[] intArray = new int[count];
		Random random = new Random();
		for (int i = 0; i < count; i++)
			intArray[i] = random.nextInt(max);// 对每个元素随机赋值

		return intArray;
	}

	/**
	 * 生成包含count个元素，下限为 lower(包括)，上限为 upper(不包括)的随机数组
	 * 
	 * @param count 生成元素个数
	 * @param lower 下限(包括)
	 * @param upper 上限(不包括)
	 * @return 返回生成的随机数组
	 * @throws IllegalArgumentException 元素个数小于0或者下限大于等于上限
	 * @since 1.8
	 */
	public static int[] random(long count, int lower, int upper) {
		Random random = new Random();
		return random.ints(count, lower, upper).toArray();
	}

}
