package com.sweetmanor.utils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

/**
 * 文件工具类：调用 commons-io 实现的方法更多的是一个使用示例，具体使用时尽量直接用原方法，这样可以减少封装不好而引入的不必要错误。
 * 
 * @version 1.0 2014-08-26
 * @author ijlhjj
 */
public class FileUtil {

	/**
	 * 判断指定文件是否存在
	 */
	public static boolean isExist(String filename) {
		if (filename == null)
			return false;
		File file = new File(filename);
		return file.exists();
	}

	/**
	 * 获取文件名，不包括扩展名
	 */
	public static String getBaseName(String fileName) {
		return FilenameUtils.getBaseName(fileName);
	}

	/**
	 * 统计文件（夹）包含文件个数（递归所有子目录）
	 */
	public static int countFiles(File dir) {
		Collection<File> files = FileUtils.listFiles(dir, null, true);
		return files.size();
	}

	/**
	 * 统计文件（夹）包含目录个数（递归所有子目录）
	 */
	public static int countDirs(File dir) {
		Collection<File> files = FileUtils.listFilesAndDirs(dir, DirectoryFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		return files.size() - 1;// 遍历结果包含了当前目录，此处减1与Windows的统计结果保持一致
	}

	/**
	 * 统计文件（夹）包含目录和文件个数（递归所有子目录）
	 */
	public static int countDirAndFiles(File dir) {
		Collection<File> files = FileUtils.listFilesAndDirs(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		return files.size() - 1;// 遍历结果包含了当前目录，此处减1与Windows的统计结果保持一致
	}

	/**
	 * 查找目录下指定类型文件
	 * 
	 * @param dir        查找的目录
	 * @param extensions 扩展名数组
	 * @param recursive  是否递归遍历子目录，True遍历子目录，False只获取当前目录（不包括子目录）
	 * @return 返回结果文件数组
	 */
	public static File[] getFiles(File dir, String[] extensions, boolean recursive) {
		Collection<File> files = FileUtils.listFiles(dir, extensions, recursive); // 获取指定扩展名文件集合
		return FileUtils.convertFileCollectionToFileArray(files);// 将文件集合转换为数组
	}

	/**
	 * 文件拷贝
	 * <p>
	 * <strong>注意：</strong> 不能将目录拷贝到文件；拷贝过程将强制覆盖。
	 * 
	 * @throws IOException
	 */
	public static void copyFile(String sourceName, String targetName) throws IOException {
		copyFile(new File(sourceName), new File(targetName));
	}

	/**
	 * 文件拷贝
	 * <p>
	 * <strong>注意：</strong> 不能将目录拷贝到文件；拷贝过程将强制覆盖。
	 * 
	 * @throws IOException
	 */
	public static void copyFile(File source, File target) throws IOException {
		if (source.isDirectory() && target.isFile())
			throw new IOException("不能将目录拷贝到文件！");

		if (source.isDirectory() && target.isDirectory())
			FileUtils.copyDirectory(source, target);
		else if (source.isFile() && target.isDirectory())
			FileUtils.copyFileToDirectory(source, target);
		else if (source.isFile() && target.isFile())
			FileUtils.copyFile(source, target);
	}

}
