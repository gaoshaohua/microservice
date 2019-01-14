package com.micro.tools.generate.utils;


import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @Description 字符串处理工具类
 * @author 龚亮
 * @date 2017年3月25日 上午10:41:07
 */
public class StrUtil {


	/**
	 * 获取随机uuid文件名
	 */
	public static String generateRandonFileName(String filename) {
		return UUID.randomUUID().toString() + "." + subFileName(filename);
	}

	/**
	 * 截取文件后缀
	 */
	public static String subFileName(String fileName) {
		int index = fileName.lastIndexOf(".");
		return -1 == index ? fileName : fileName.substring(index + 1);
	}

	/**
	 * 获得hashcode生成二级目录
	 */
	public static String generateRandomDir(String uuidFileName) {
		int hashCode = uuidFileName.hashCode();
		// 一级目录
		int d1 = hashCode & 0xf;
		// 二级目录
		int d2 = (hashCode >> 4) & 0xf;
		return "/" + d1 + "/" + d2;
	}

	/**
	 * 首字母小写
	 */
	public static String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if ((firstChar >= 'A') && (firstChar <= 'Z')) {
			char[] arr = str.toCharArray();
			int tmp25_24 = 0;
			char[] tmp25_23 = arr;
			tmp25_23[tmp25_24] = (char) (tmp25_23[tmp25_24] + ' ');
			return new String(arr);
		}
		return str;
	}

	/**
	 * 首字母大写
	 */
	public static String firstCharToUpperCase(String str) {
		char firstChar = str.charAt(0);
		if ((firstChar >= 'a') && (firstChar <= 'z')) {
			char[] arr = str.toCharArray();
			int tmp25_24 = 0;
			char[] tmp25_23 = arr;
			tmp25_23[tmp25_24] = (char) (tmp25_23[tmp25_24] - ' ');
			return new String(arr);
		}
		return str;
	}

	/**
	 * 是否全为大写字母
	 */
	public static boolean isAllUpperCase(String str) {
		boolean flag = true;
		for (int i = 0; i < str.length(); i++) {
			char firstChar = str.charAt(i);
			if ((firstChar >= 'a') && (firstChar <= 'z')) {
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * str是否为null/""
	 */
	public static boolean isBlank(String str) {
		return ((str == null) || ("".equals(str.trim())));
	}

	/**
	 * str是不为null/""
	 */
	public static boolean notBlank(String str) {
		return ((str != null) && (!("".equals(str.trim()))));
	}

	/**
	 * str[]是不为null/""
	 */
	public static boolean notBlank(String[] strings) {
		if (strings == null)
			return false;
		for (String str : strings)
			if ((str == null) || ("".equals(str.trim())))
				return false;
		return true;
	}

	/**
	 * object[]不为null
	 */
	public static boolean notNull(Object... paras) {
		if (paras == null)
			return false;
		for (Object obj : paras)
			if (obj == null)
				return false;
		return true;
	}

	/**
	 * List不为null
	 */
	public static boolean notNull(List<?> paras) {
		if (paras == null || paras.size() == 0)
			return false;
		for (Object obj : paras)
			if (obj == null)
				return false;
		return true;
	}

	/**
	 * 空
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 非空
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 将驼峰风格替换为下划线风格
	 */
	public static String camelhumpToUnderline(String str) {
		final int size;
		final char[] chars;
		final StringBuilder sb = new StringBuilder((size = (chars = str.toCharArray()).length) * 3 / 2 + 1);
		char c;
		for (int i = 0; i < size; i++) {
			c = chars[i];
			if (isUppercaseAlpha(c)) {
				sb.append('_').append(toLowerAscii(c));
			} else {
				sb.append(toUpperAscii(c));
			}
		}
		return sb.charAt(0) == '_' ? sb.substring(1) : sb.toString();
	}

	/**
	 * 将下划线风格替换为驼峰风格
	 */
	public static String underlineToCamelhump(String str) {
		Matcher matcher = Pattern.compile("_[a-z]").matcher(str);
		StringBuilder builder = new StringBuilder(str);
		for (int i = 0; matcher.find(); i++) {
			builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
		}
		if (Character.isUpperCase(builder.charAt(0))) {
			builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
		}
		return builder.toString();
	}

	public static boolean isUppercaseAlpha(char c) {
		return (c >= 'A') && (c <= 'Z');
	}

	public static boolean isLowercaseAlpha(char c) {
		return (c >= 'a') && (c <= 'z');
	}

	public static char toUpperAscii(char c) {
		if (isUppercaseAlpha(c)) {
			c -= (char) 0x20;
		}
		return c;
	}

	public static char toLowerAscii(char c) {
		if (isUppercaseAlpha(c)) {
			c += (char) 0x20;
		}
		return c;
	}

	/**
	 * 判断字符串是否全为中文
	 */
	public static boolean isChinese(String str) {
		return str.matches("[\\u4E00-\\u9FA5]+");
	}
	
	public static String sqlType2JavaType(String columnName, String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        }else if (sqlType.equalsIgnoreCase("double")) {
            return "java.math.BigDecimal";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") || sqlType.equalsIgnoreCase("real")
                || sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")) {
            return "java.math.BigDecimal";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar")
                || sqlType.equalsIgnoreCase("nchar") || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("timestamp")) {
            return "java.util.Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "java.sql.Blod";
        } else if (sqlType.equalsIgnoreCase("boolean")) {
            return "Boolean";
        }

        return null;
    }
}
