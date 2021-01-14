package com.sweetmanor.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import com.sweetmanor.common.Const;
import com.sweetmanor.common.Person;

/**
 * 全部测试方法使用一个测试实例，测试必须按指定顺序执行
 */
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class ObjectAccessUtilTest {
	private File file;
	private Person p;

	/**
	 * 只有在使用 @TestInstance(Lifecycle.PER_CLASS) 时，@BeforeAll 才可以不使用 static 修饰
	 */
	@BeforeAll
	void setUp() throws Exception {
		file = new File(Const.classPath, "p.dat");
		file.delete();
		assertFalse(file.exists());
	}

	/**
	 * 只有在使用 @TestInstance(Lifecycle.PER_CLASS) 时，@AfterAll 才可以不使用 static 修饰
	 */
	@AfterAll
	void tearDown() throws Exception {
		file.delete();
		assertFalse(file.exists());
	}

	/**
	 * 测试写入序列化对象到文件中
	 */
	@Test
	@Order(1)
	void testWriteToFile() {
		p = new Person("孙悟空", 500, "男");
		ObjectAccessUtil.writeToFile(p, file);
		assertTrue(file.exists());
	}

	/**
	 * 测试从文件中读取序列化对象，必须在对象写入文件后执行
	 */
	@Test
	@Order(2)
	void testReadFromFile() {
		Person p2 = (Person) ObjectAccessUtil.readFromFile(file);
		assertNotNull(p2);
		assertEquals(p, p2);// 反序列化对象和原对象相等
		assertNull(p2.getSex());// 序列化时不存储 transient 修饰的字段
	}

}
