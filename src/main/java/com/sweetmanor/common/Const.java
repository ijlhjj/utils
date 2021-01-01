package com.sweetmanor.common;

/**
 * 常用公共参数
 */
public class Const {
	public static final String classPath;// 类加载根目录

	static {
		String path = Const.class.getResource("/").getPath();
		if (path.startsWith("/"))// 路径在某些系统下会以“/”开头
			path = path.substring(1);
		classPath = path;
	}

}
