package com.sweetmanor.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonTest {
	private Person p1;
	private Person p2;

	@BeforeEach
	void setUp() throws Exception {
		p1 = new Person("孙悟空", 500);
		p2 = new Person("孙悟空", 500);
	}

	/**
	 * 测试equals方法和hashCode方法的实现是否符合预期
	 */
	@Test
	void testEquals() {
		assertNotSame(p1, p2);
		assertEquals(p1, p2);
		assertEquals(p1.hashCode(), p2.hashCode());
	}

}
