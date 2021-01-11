package com.sweetmanor.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

public class IDCardUtilTest {
	private String idCard = "110102210605150319";// 此处使用了虚构的身份证号码，避免出现雷同
	private String idCard15 = "110102060515031";
	private String wrongCard = "11010221060515031x ";

	/**
	 * 测试简单验证机制，因为不判断校验位等，所以此处将非法的身份证也会判断为正确
	 */
	@Test
	public void testSimpleCheck() {
		assertTrue(IDCardUtil.simpleCheck(idCard));
		assertTrue(IDCardUtil.simpleCheck(wrongCard));
	}

	/**
	 * 测试获取相应的省份
	 */
	@Test
	public void testGetProvince() {
		assertEquals("北京", IDCardUtil.getProvince(idCard));
	}

	/**
	 * 测试获取性别
	 */
	@Test
	public void testGetGender() {
		assertEquals("男", IDCardUtil.getGender(idCard));
	}

	/**
	 * 测试获取出生日期
	 */
	@Test
	public void testGetBirthday() {
		try {
			Date birthday = DateUtils.parseDate("21060515", "yyyyMMdd");
			assertEquals(birthday, IDCardUtil.getBirthday(idCard));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试标准身份证校验，此处进行了校验位验证，基本满足不太严格的验证过程
	 */
	@Test
	public void testCheck() {
		assertTrue(IDCardUtil.check(idCard));
		assertFalse(IDCardUtil.check(wrongCard));
	}

	/**
	 * 测试把15位身份证号码转换为18位
	 */
	@Test
	public void testConvertIdcarBy15bit() {
		assertEquals("11010220060515031X", IDCardUtil.convertIdcarBy15bit(idCard15));
	}

	/**
	 * 测试对身份证号码进行一些标准化处理
	 */
	@Test
	public void testStandardizing() {
		assertEquals("11010221060515031X", IDCardUtil.standardizing(wrongCard));
	}

}
