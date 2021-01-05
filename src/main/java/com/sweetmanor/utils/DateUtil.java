package com.sweetmanor.utils;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期时间工具类：部分方法直接调用 commons-lang3 包的方法实现。 <br />
 * 调用 commons-lang3 实现的方法更多的是一个使用示例，具体使用时尽量直接用原方法，这样可以减少封装不好而引入的不必要错误。 <br />
 * 历史遗留代码，新代码应该使用 Java 8 新增的日期时间 API，此工具类将不再适用。
 * 
 * @version 1.0 2016-11-24
 * @author ijlhjj
 */
public class DateUtil {
	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss"; // 默认日期格式化模式

	/**
	 * @return DEFAULT_FORMAT格式的当前日期字符串
	 */
	public static String getDate() {
		return DateFormatUtils.format(new Date(), DEFAULT_FORMAT);
	}

	/**
	 * @param date 待格式化日期, not null
	 * @return DEFAULT_FORMAT格式的日期字符串
	 */
	public static String format(Date date) {
		return DateFormatUtils.format(date, DEFAULT_FORMAT);
	}

	/**
	 * @param date    待格式化日期, not null
	 * @param pattern 格式化模式字符串, not null
	 * @return pattern格式的日期字符串
	 */
	public static String format(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * 对输入日期增加指定天数，返回新的Data对象，不改变原对象
	 * 
	 * @param date   原日期对象, not null
	 * @param amount 增加的天数，可以为负值，负值即减少指定天数
	 * @return 计算后的日期对象
	 */
	public static Date addDays(Date date, int amount) {
		return DateUtils.addDays(date, amount);
	}

	/**
	 * 对输入日期增加指定月数，返回新的Data对象，不改变原对象
	 * 
	 * @param date   原日期对象, not null
	 * @param amount 增加的月数，可以为负值，负值即减少指定月数
	 * @return 计算后的日期对象
	 */
	public static Date addMonths(Date date, int amount) {
		return DateUtils.addMonths(date, amount);
	}

	/**
	 * 对输入日期增加指定星期数，返回新的Data对象，不改变原对象
	 * 
	 * @param date   原日期对象, not null
	 * @param amount 增加的星期数，可以为负值，负值即减少指定星期数
	 * @return 计算后的日期对象
	 */
	public static Date addWeeks(Date date, int amount) {
		return DateUtils.addWeeks(date, amount);
	}

	/**
	 * 设置日期的天数为指定值，不改变时间。例如要获取当月第一天，amount为1
	 * 
	 * @param date   原日期对象, not null
	 * @param amount 要设定的天
	 * @return 计算后的日期对象
	 */
	public static Date setDays(Date date, int amount) {
		return DateUtils.setDays(date, amount);
	}

	/**
	 * 设置日期的小时为指定值，其他字段不变
	 * 
	 * @param date   原日期对象, not null
	 * @param amount 要设定的小时
	 * @return 计算后的日期对象
	 */
	public static Date setHours(Date date, int amount) {
		return DateUtils.setHours(date, amount);
	}

	/**
	 * 设置日期的分钟为指定值，其他字段不变
	 * 
	 * @param date   原日期对象, not null
	 * @param amount 要设定的分钟
	 * @return 计算后的日期对象
	 */
	public static Date setMinutes(Date date, int amount) {
		return DateUtils.setMinutes(date, amount);
	}

	/**
	 * 设置日期的秒为指定值，其他字段不变
	 * 
	 * @param date   原日期对象, not null
	 * @param amount 要设定的秒
	 * @return 计算后的日期对象
	 */
	public static Date setSeconds(Date date, int amount) {
		return DateUtils.setSeconds(date, amount);
	}

	/**
	 * 设置为指定日期的 00:00:00
	 * 
	 * @param date 原日期对象, not null
	 * @return 计算后的日期对象
	 */
	public static Date setBeginTime(Date date) {
		return truncate(date, Calendar.DAY_OF_MONTH);
	}

	/**
	 * 设置为指定日期的 23:59:59
	 * 
	 * @param date 原日期对象, not null
	 * @return 计算后的日期对象
	 */
	public static Date setEndTime(Date date) {
		Date d = setHours(date, 23);
		d = setMinutes(d, 59);
		d = setSeconds(d, 59);
		return d;
	}

	/**
	 * 获取指定日期所在月份的最后一天，不改变时间
	 * 
	 * @param date 原日期对象, not null
	 * @return 计算后的日期对象
	 */
	public static Date getLastDayOfMonth(Date date) {
		Date d = setDays(date, 1);
		d = addMonths(d, 1);
		return addDays(d, -1);
	}

	/**
	 * 获取指定日期所在周的第一天（周日），不改变时间
	 * 
	 * @param date 原日期对象, not null
	 * @return 计算后的日期对象
	 */
	public static Date getFirstDayOfWeek(Date date) {
		return setDayOfWeek(date, Calendar.SUNDAY);
	}

	/**
	 * 获取指定日期所在周的最后一天（周六），不改变时间
	 * 
	 * @param date 原日期对象, not null
	 * @return 计算后的日期对象
	 */
	public static Date getLastDayOfWeek(Date date) {
		return setDayOfWeek(date, Calendar.SATURDAY);
	}

	/**
	 * 设置为指定的星期几，不改变时间
	 * 
	 * @param dayOfWeek 设置为指定星期几，可接受以下参数： Calendar.MONDAY - Calendar.SUNDAY
	 * @return 计算后的日期对象
	 */
	public static Date setDayOfWeek(Date date, int dayOfWeek) {
		Calendar cal = DateUtils.toCalendar(date);
		cal.set(Calendar.DAY_OF_WEEK, dayOfWeek); // 设置日为指定的星期
		return cal.getTime();
	}

	/**
	 * 对日期按指定字段进行截取
	 * 
	 * @param date  要截取的日期
	 * @param field 截取字段：使用Calendar的字段值。例如要获取当天的00:00:00，field为Calendar.
	 *              DAY_OF_MONTH
	 * @return 计算后的日期对象
	 */
	public static Date truncate(Date date, int field) {
		return DateUtils.truncate(date, field);
	}

	/**
	 * 转换毫秒值为中文字符串。月一律按30天计算。 <br />
	 * 使测试程序运行时间的结果可读性更高。
	 */
	public static String convertMillisToString(long millis) {
		int n = 7; // 最高日期单位的标志位
		long year = 0, month = 0, day = 0, hour = 0, minute = 0;

		long second = millis / 1000; // 计算秒
		long millisecond = millis % 1000;// 计算毫秒
		// 有秒值，计算分
		if (second > 0) {
			minute = second / 60;
			second = second % 60;
			n = 6;
		}
		// 有分值，计算小时
		if (minute > 0) {
			hour = minute / 60;
			minute = minute % 60;
			n = 5;
		}
		// 有小时，计算天
		if (hour > 0) {
			day = hour / 24;
			hour = hour % 24;
			n = 4;
		}
		// 有天值，计算月，月不分大小月，一律按30天计算
		if (day > 0) {
			month = day / 30;
			day = day % 30;
			n = 3;
		}
		// 有月值，计算年
		if (month > 0) {
			year = month / 12;
			month = month % 12;
			n = 2;
		}
		// 有年值
		if (year > 0)
			n = 1;

		StringBuilder result = new StringBuilder(); // 返回结果字符串
		// 组织字符串，此处没有break语句，从对应值后面的语句都会执行
		switch (n) {
		case 1:
			result.append(year + "年 ");
		case 2:
			result.append(month + "月 ");
		case 3:
			result.append(day + "日 ");
		case 4:
			result.append(hour + "时 ");
		case 5:
			result.append(minute + "分 ");
		case 6:
			result.append(second + "秒 ");
		default:
			result.append(millisecond + "毫秒");
		}
		return result.toString();
	}

}
