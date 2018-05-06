package com.jsplay.hello.utils.utils;

public class StringUtil {

	public static boolean isEmpty( String content ) {
		return content == null || "".equals(content.trim());
	}
}
