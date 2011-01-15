package jtbc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 该类为验证类，包括是否中文、是否email地址、是否手机号码、是否常用字符(即字母和数字)、是否合法用户名(即字符串)、是否邮编
 * 
 * @author Administrator
 * 
 */
public class validator {
	/**
	 * 是否中文汉字，是返回true，否返回false 空值返回false
	 * 
	 * @param paramString
	 * @return Boolean
	 */
	public static Boolean isChinese(String paramString) {
		Boolean localBoolean = Boolean.valueOf(false);
		String str = paramString;
		if (!(cls.isEmpty(str).booleanValue())) {
			Pattern localPattern = Pattern.compile("^[\\u0391-\\uFFE5]+$");
			Matcher localMatcher = localPattern.matcher(str);
			localBoolean = Boolean.valueOf(localMatcher.matches());
		}
		return localBoolean;
	}

	/**
	 * 是否邮件地址格式，是返回true，否返回false 空值返回false
	 * 
	 * @param paramString
	 * @return Boolean
	 */
	public static Boolean isEmail(String paramString) {
		Boolean localBoolean = Boolean.valueOf(false);
		String str = paramString;
		if (!(cls.isEmpty(str).booleanValue())) {
			Pattern localPattern = Pattern
					.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
			Matcher localMatcher = localPattern.matcher(str);
			localBoolean = Boolean.valueOf(localMatcher.matches());
		}
		return localBoolean;
	}

	/**
	 * 是否11位手机号码格式，是返回true，否返回false 空值返回false
	 * 
	 * @param paramString
	 * @return Boolean
	 */
	public static Boolean isMobile(String paramString) {
		Boolean localBoolean = Boolean.valueOf(false);
		String str = paramString;
		if (!(cls.isEmpty(str).booleanValue())) {
			Pattern localPattern = Pattern.compile("^1\\d{10}$");
			Matcher localMatcher = localPattern.matcher(str);
			localBoolean = Boolean.valueOf(localMatcher.matches());
		}
		return localBoolean;
	}

	/**
	 * 是否是通常使用的字符(26个大小写字母和10个数字)，是返回true，否返回false 空值返回false
	 * 
	 * @param paramString
	 * @return Boolean
	 */
	public static Boolean isNatural(String paramString) {
		Boolean localBoolean = Boolean.valueOf(false);
		String str = paramString;
		if (!(cls.isEmpty(str).booleanValue())) {
			Pattern localPattern = Pattern.compile("^[A-Za-z0-9]+$");
			Matcher localMatcher = localPattern.matcher(str);
			localBoolean = Boolean.valueOf(localMatcher.matches());
		}
		return localBoolean;
	}

	/**
	 * 用户名有效性验证，可以为中文、字母和数字，是返回true，否返回false 空值返回false
	 * 
	 * @param paramString
	 * @return Boolean
	 */
	public static Boolean isUsername(String paramString) {
		Boolean localBoolean = Boolean.valueOf(false);
		String str = paramString;
		if (!(cls.isEmpty(str).booleanValue())) {
			Pattern localPattern = Pattern
					.compile("^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$");
			Matcher localMatcher = localPattern.matcher(str);
			localBoolean = Boolean.valueOf(localMatcher.matches());
		}
		return localBoolean;
	}

	/**
	 * 6位数字,可能是邮编号码，是返回true，否返回false 空值返回false
	 * 
	 * @param paramString
	 * @return Boolean
	 */
	public static Boolean isZip(String paramString) {
		Boolean localBoolean = Boolean.valueOf(false);
		String str = paramString;
		if (!(cls.isEmpty(str).booleanValue())) {
			Pattern localPattern = Pattern.compile("^[0-9]\\d{5}$");
			Matcher localMatcher = localPattern.matcher(str);
			localBoolean = Boolean.valueOf(localMatcher.matches());
		}
		return localBoolean;
	}
}