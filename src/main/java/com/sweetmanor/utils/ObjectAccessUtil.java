package com.sweetmanor.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.io.FileUtils;

/**
 * 对象序列化工具类：只作为一个代码示例，具体应用时应该使用成熟的第三方序列化库。
 * 
 * @version 1.0 2016-11-22
 * @author ijlhjj
 */
public class ObjectAccessUtil {

	/**
	 * 写入序列化对象到文件中
	 * 
	 * @param object 待写入的可序列化对象
	 * @param file   写入文件名称
	 */
	public static void writeToFile(Serializable object, String file) {
		writeToFile(object, new File(file));
	}

	/**
	 * 写入序列化对象到文件中
	 * 
	 * @param object 待写入的可序列化对象
	 * @param file   写入文件对象
	 */
	public static void writeToFile(Serializable object, File file) {
		try { // 写入对象前父目录必须已经存在
			FileUtils.forceMkdirParent(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (FileOutputStream fos = new FileOutputStream(file); //
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(object);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * 从文件读取对象
	 * 
	 * @param file 读取文件的名称
	 * @return 反序列化读取到的对象，文件或对象不存在将返回null
	 */
	public static Object readFromFile(String file) {
		return readFromFile(new File(file));
	}

	/**
	 * 从文件读取对象
	 * 
	 * @param file 读取的文件对象
	 * @return 反序列化读取到的对象，文件或对象不存在将返回null
	 */
	public static Object readFromFile(File file) {
		if (file == null || !file.exists())
			return null;

		try (FileInputStream fis = new FileInputStream(file); //
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
