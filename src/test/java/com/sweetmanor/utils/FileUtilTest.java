package com.sweetmanor.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 此测试类只在本机可以正确运行，在其他机器上运行前应该修改指定的测试目录等参数。
 */
public class FileUtilTest {
	private final String existDir = "E:\\参考资料\\lib\\apache\\commons-io\\commons-io-2.6";// 应该存在的文件夹
	private final String notExistFile = "E:\\参考资料\\lib\\apache\\commons-io\\commons-io-2.5";// 不存在的文件夹
	private final int dirCount = 18;// 测试目录包含的子目录数（递归）
	private final int fileCount = 280;// 测试目录包含的文件数（递归）
	private final int currFileCount = 8;// 测试目录包含的文件数（不递归）
	private final int htmlCount = 269;// 测试目录包含的html文件数（递归）

	/**
	 * 测试文件是否存在
	 */
	@BeforeEach
	public void testIsExist() {
		assumeTrue(FileUtil.isExist(existDir));
	}

	/**
	 * 测试文件是否存在
	 */
	@Test
	public void testIsNotExist() {
		assumeFalse(FileUtil.isExist(notExistFile));
	}

	/**
	 * 测试统计文件总数
	 */
	@Test
	public void testCountFiles() {
		int count = FileUtil.countFiles(new File(existDir));
		assertEquals(fileCount, count);
	}

	/**
	 * 测试统计目录总数
	 */
	@Test
	public void testCountDirs() {
		int count = FileUtil.countDirs(new File(existDir));
		assertEquals(dirCount, count);
	}

	/**
	 * 测试统计目录和文件总数
	 */
	@Test
	public void testCountDirAndFiles() {
		int count = FileUtil.countDirAndFiles(new File(existDir));
		assertEquals(dirCount + fileCount, count);
	}

	/**
	 * 测试获取指定类型文件
	 */
	@Test
	public void testGetFiles() {
		File[] files = FileUtil.getFiles(new File(existDir), new String[] { "html" }, true);
		assertEquals(htmlCount, files.length);
		files = FileUtil.getFiles(new File(existDir), null, false);
		assertEquals(currFileCount, files.length);
	}

}
