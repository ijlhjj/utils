package com.sweetmanor.utils;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * 数学计算工具类
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class MathUtil {

	/**
	 * 返回数组中最大元素
	 */
	public static int max(int... a) {
		return NumberUtils.max(a);
	}

	/**
	 * 素数判断方法
	 */
	public static boolean isPrime(int n) {
		if (n < 2) // 小于2的数直接返回false
			return false;
		double last = Math.sqrt(n);
		for (int i = 2; i <= last; i++)
			if (n % i == 0)
				return false;
		return true;
	}

}
