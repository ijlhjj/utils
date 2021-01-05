package com.sweetmanor.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 只测试了少量的方法，其他方法大部分直接调用Apache的工具类，不再进行测试
 */
public class DateUtilTest {
	private Date now;

	@BeforeEach
	public void setUp() throws Exception {
		now = new Date();
	}

	/**
	 * 测试获取当天的 23:59:59
	 */
	@Test
	public void testSetEndTime() {
		Date date = DateUtil.setEndTime(now);
		assertEquals(23, date.getHours());
		assertEquals(59, date.getMinutes());
		assertEquals(59, date.getSeconds());
	}

	/**
	 * 测试获取当月的最后一天
	 */
	@Test
	public void testGetLastDayOfMonth() {
		Date lastDay = DateUtil.getLastDayOfMonth(now);
		Date firstDay = DateUtil.addDays(lastDay, 1);
		int m1 = (lastDay.getMonth() + 1) % 12; // getMonth方法获取值为0-11，12月加1后得到0值，所以先加再取模
		int m2 = firstDay.getMonth();
		assertEquals(m1, m2);
	}

	/**
	 * 测试获取本周的指定星期几
	 */
	@Test
	public void testSetDayOfWeek() {
		Date date = DateUtil.setDayOfWeek(now, Calendar.MONDAY);
		assertEquals(Calendar.MONDAY, date.getDay() + 1);// getDay方法取值为0-6，Calendar的星期取值为1-7，所以此处加1
	}

}
