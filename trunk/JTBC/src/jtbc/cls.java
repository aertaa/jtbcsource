package jtbc;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.applet.Main;

public class cls {

	/**
	 * * 将字符串参数1和字符串参数2做连接操作，参数1在前，参2在后
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @return
	 */
	public static String cfnames(String paramString1, String paramString2) {
		String str1 = "";
		String str2 = paramString1;
		String str3 = paramString2;
		str1 = concat(str2, str3);
		return str1;
	}

	/**
	 * 将字符串参数1和字符串参数2做连接操作，参数1在前，参2在后
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @return
	 */
	public static String concat(String paramString1, String paramString2) {
		String str1 = "";
		String str2 = paramString1;
		String str3 = paramString2;
		str2 = getString(str2);
		str3 = getString(str3);
		str1 = str2 + str3;
		return str1;
	}

	/**
	 * 返回整形参数1整除参数2的结果
	 * 
	 * @param paramInteger1
	 * @param paramInteger2
	 * @return
	 */
	public static String cper(Integer paramInteger1, Integer paramInteger2) {
		String str1 = "0";
		Integer localInteger1 = paramInteger1;
		Integer localInteger2 = paramInteger2;
		String str2 = toString(localInteger1);
		String str3 = toString(localInteger2);
		if ((!(localInteger1.equals(Integer.valueOf(0))))
				&& (!(localInteger2.equals(Integer.valueOf(0)))))
			str1 = toString(Double.valueOf(getDouble(str2).doubleValue()
					/ getDouble(str3).doubleValue() * 100.0D));
		str1 = getLRStr(str1, ".", "left");
		return str1;
	}
	/**
	 * 将paramString1两个paramString2之间的字符串返回
	 * @param paramString1
	 * @param paramString2
	 * @return
	 */
	public static String ctemplate(String paramString1, String paramString2) {
		String str1 = "";
		String str2 = paramString1;
		String str3 = paramString2;
		// 如果str2不为空并且str2中包含有str3
		if ((!(isEmpty(str2).booleanValue())) && (str2.indexOf(str3) >= 0)) {
			String str4 = "<!--fixed-->" + str2 + "<!--fixed-->";
			String[] arrayOfString = str4.split(Pattern.quote(str3));
			if (arrayOfString.length == 3)
				str1 = arrayOfString[1];
		}
		return str1;
	}

	/**
	 * 将paramString1中，两个paramString2及其之间的字符替换成paramString3
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @param paramString3
	 * @return
	 */
	public static String ctemplates(String paramString1, String paramString2,
			String paramString3) {
		String str1 = "";
		String str2 = paramString1;
		String str3 = paramString2;
		String str4 = paramString3;
		if ((!(isEmpty(str2).booleanValue())) && (str2.indexOf(str3) >= 0)) {
			String str5 = "<!--fixed-->" + str2 + "<!--fixed-->";
			String[] arrayOfString = str5.split(Pattern.quote(str3));
			if (arrayOfString.length == 3)
				str1 = str2.replace(str3 + arrayOfString[1] + str3, str4);
		}
		return str1;
	}

	/**
	 * 如果参数按逗号分隔后的字符串数组每个元素都能转成Integer对象，则返回true，否则返回false
	 * 
	 * @param paramString
	 * @return
	 */
	public static Boolean cidary(String paramString) {
		// 初始化false
		Boolean localBoolean = Boolean.valueOf(false);
		String str = getString(paramString);
		// 如果不为??
		if (!(isEmpty(str).booleanValue())) {
			localBoolean = Boolean.valueOf(true);
			String[] arrayOfString = str.split(Pattern.quote(","));
			for (int i = 0; i < arrayOfString.length; ++i) {
				if (getNum(arrayOfString[i], Integer.valueOf(-1)).intValue() != -1)
					continue;
				localBoolean = Boolean.valueOf(false);
				break;
			}
		}

		return localBoolean;
	}

	/**
	 * 参数1和参数2满足某种的格式就返回true
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @param paramString3
	 * @return
	 */
	public static Boolean cinstr(String paramString1, String paramString2,
			String paramString3) {
		Boolean localBoolean = Boolean.valueOf(false);
		String str1 = paramString1;
		String str2 = paramString2;
		String str3 = paramString3;
		// 参数1和参??值相等，返回true
		if (str1.equals(str2))
			localBoolean = Boolean.valueOf(true);
		// 参数1中包含有参数3+参数2+参数3，返回true
		else if (str1.indexOf(str3 + str2 + str3) >= 0)
			localBoolean = Boolean.valueOf(true);
		// str1中str3左边和str2相等，返回true
		else if (getLRStr(str1, str3, "left").equals(str2))
			localBoolean = Boolean.valueOf(true);
		// str1中str3右边的和str2想等，返回true
		else if (getLRStr(str1, str3, "right").equals(str2))
			localBoolean = Boolean.valueOf(true);

		return localBoolean;
	}

	/**
	 * 参数1和参数2按参数3分隔之后形成的数组中的每个元素满足某种的条件，则返回true
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @param paramString3
	 * @return
	 */
	public static Boolean cinstrs(String paramString1, String paramString2,
			String paramString3) {
		Boolean localBoolean = Boolean.valueOf(false);
		String str1 = paramString1;
		String str2 = paramString2;
		String str3 = paramString3;
		// 参数1和参??相等，返回true
		if (str1.equals(str2)) {
			localBoolean = Boolean.valueOf(true);
		} else {
			localBoolean = Boolean.valueOf(true);
			// 参数2按参??分隔
			String[] arrayOfString = str2.split(Pattern.quote(str3));
			for (int i = 0; i < arrayOfString.length; ++i) {
				// 如果发现参数1和数组中的某个元素不满足一定的条件，返回false
				if (cinstr(str1, arrayOfString[i], str3).booleanValue())
					continue;
				localBoolean = Boolean.valueOf(false);
			}
		}
		return localBoolean;
	}

	/**
	 * 先把传入的String参数1转成Long形的，然后将这个Long形数据标示的字节数转换成KB、MG或??GB对应得字符串
	 * 
	 * @param paramString
	 * @return
	 */
	public static String formatByte(String paramString) {
		String str1 = paramString;
		String str2 = formatByte(getNum64(str1, Long.valueOf(0L)));
		return str2;
	}

	/**
	 * 对传入的标示多少B的Long形参数转成KB,MB,GB的String对象,如传入2048 返回2KB
	 * 
	 * @param paramLong
	 * @return
	 */
	public static String formatByte(Long paramLong) {
		Long localLong = paramLong;
		String str = "";
		if (localLong.longValue() > 1073741824L)
			str = toString(Double
					.valueOf(Math
							.round(localLong.longValue() / 1073741824.0D * 1000.0D) / 1000.0D))
					+ " GB";
		else if (localLong.longValue() > 1048576L)
			str = toString(Double
					.valueOf(Math
							.round(localLong.longValue() / 1048576.0D * 1000.0D) / 1000.0D))
					+ " MB";
		else if (localLong.longValue() > 1024L)
			str = toString(Double
					.valueOf(Math
							.round(localLong.longValue() / 1024.0D * 1000.0D) / 1000.0D))
					+ " KB";
		else
			str = toString(localLong) + " B";

		return str;
	}

	/**
	 * 将一个时间格式的字符串转成2010-12-12 12:12:12这种格式的字符串
	 * 
	 * @param paramString
	 * @return
	 */
	public static String formatDate(String paramString) {
		String str1 = "";
		String str2 = paramString;
		str1 = formatDate(str2, Integer.valueOf(100));
		return str1;
	}

	/**
	 * 返回2010-12-12 12:12:12格式的字符串
	 * 
	 * @param paramDate
	 * @return
	 */
	public static String formatDate(Date paramDate) {
		String str = "";
		Date localDate = paramDate;
		str = formatDate(localDate, Integer.valueOf(100));
		return str;
	}

	/**
	 * 根据参数2按照一定的格式将参数一表示的时间格式的串转换成另外一个格式
	 * 
	 * @param paramString
	 * @param paramInteger
	 * @return
	 */
	public static String formatDate(String paramString, Integer paramInteger) {
		Integer localInteger = paramInteger;
		String str1 = paramString;
		String str2 = str1;
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			Date localDate = localSimpleDateFormat.parse(str1);
			str2 = formatDate(localDate, localInteger);
		} catch (Exception localException) {
		}
		return str2;
	}

	/**
	 * 根据paramInteger，获得格式化后的时间串
	 * 
	 * @param paramDate
	 * @param paramInteger
	 * @return
	 */
	public static String formatDate(Date paramDate, Integer paramInteger) {
		String str = "";
		Integer localInteger = paramInteger;
		Date localDate = paramDate;
		try {
			Calendar localCalendar = Calendar.getInstance();
			localCalendar.setTime(localDate);
			switch (localInteger.intValue()) {
			case -3:
				str = toString(Integer.valueOf(localCalendar.get(5)));
				break;
			case -2:
				str = toString(Integer.valueOf(localCalendar.get(2) + 1));
				break;
			case -1:
				str = toString(Integer.valueOf(localCalendar.get(1)));
				break;
			case 0:
				str = toString(Integer.valueOf(localCalendar.get(1)))
						+ toString(Integer.valueOf(localCalendar.get(2) + 1))
						+ toString(Integer.valueOf(localCalendar.get(5)))
						+ toString(Integer.valueOf(localCalendar.get(11)))
						+ toString(Integer.valueOf(localCalendar.get(12)))
						+ toString(Integer.valueOf(localCalendar.get(13)));
				break;
			case 1:
				str = toString(Integer.valueOf(localCalendar.get(1))) + "-"
						+ toString(Integer.valueOf(localCalendar.get(2) + 1))
						+ "-" + toString(Integer.valueOf(localCalendar.get(5)));
				break;
			case 2:
				str = toString(Integer.valueOf(localCalendar.get(1))) + "/"
						+ toString(Integer.valueOf(localCalendar.get(2) + 1))
						+ "/" + toString(Integer.valueOf(localCalendar.get(5)));
				break;
			case 3:
				str = toString(Integer.valueOf(localCalendar.get(1))) + "."
						+ toString(Integer.valueOf(localCalendar.get(2) + 1))
						+ "." + toString(Integer.valueOf(localCalendar.get(5)));
				break;
			case 4:
				str = toString(Integer.valueOf(localCalendar.get(1))) + "-"
						+ formatTime(Integer.valueOf(localCalendar.get(2) + 1))
						+ "-"
						+ formatTime(Integer.valueOf(localCalendar.get(5)));
				break;
			case 5:
				str = toString(Integer.valueOf(localCalendar.get(1))) + "/"
						+ formatTime(Integer.valueOf(localCalendar.get(2) + 1))
						+ "/"
						+ formatTime(Integer.valueOf(localCalendar.get(5)));
				break;
			case 6:
				str = toString(Integer.valueOf(localCalendar.get(1))) + "."
						+ formatTime(Integer.valueOf(localCalendar.get(2) + 1))
						+ "."
						+ formatTime(Integer.valueOf(localCalendar.get(5)));
				break;
			case 7:
				str = toString(Integer.valueOf(localCalendar.get(1)))
						+ formatTime(Integer.valueOf(localCalendar.get(2) + 1))
						+ formatTime(Integer.valueOf(localCalendar.get(5)));
				break;
			case 10:
				str = toString(Integer.valueOf(localCalendar.get(2) + 1))
						+ toString(Integer.valueOf(localCalendar.get(5)))
						+ toString(Integer.valueOf(localCalendar.get(11)))
						+ toString(Integer.valueOf(localCalendar.get(12)));
				break;
			case 11:
				str = toString(Integer.valueOf(localCalendar.get(2) + 1)) + "-"
						+ toString(Integer.valueOf(localCalendar.get(5))) + " "
						+ toString(Integer.valueOf(localCalendar.get(11)))
						+ ":"
						+ toString(Integer.valueOf(localCalendar.get(12)));
				break;
			case 12:
				str = toString(Integer.valueOf(localCalendar.get(2) + 1)) + "/"
						+ toString(Integer.valueOf(localCalendar.get(5))) + " "
						+ toString(Integer.valueOf(localCalendar.get(11)))
						+ ":"
						+ toString(Integer.valueOf(localCalendar.get(12)));
				break;
			case 13:
				str = toString(Integer.valueOf(localCalendar.get(2) + 1)) + "."
						+ toString(Integer.valueOf(localCalendar.get(5))) + " "
						+ toString(Integer.valueOf(localCalendar.get(11)))
						+ ":"
						+ toString(Integer.valueOf(localCalendar.get(12)));
				break;
			case 14:
				str = formatTime(Integer.valueOf(localCalendar.get(2) + 1))
						+ "-"
						+ formatTime(Integer.valueOf(localCalendar.get(5)))
						+ " "
						+ formatTime(Integer.valueOf(localCalendar.get(11)))
						+ ":"
						+ formatTime(Integer.valueOf(localCalendar.get(12)));
				break;
			case 15:
				str = formatTime(Integer.valueOf(localCalendar.get(2) + 1))
						+ "/"
						+ formatTime(Integer.valueOf(localCalendar.get(5)))
						+ " "
						+ formatTime(Integer.valueOf(localCalendar.get(11)))
						+ ":"
						+ formatTime(Integer.valueOf(localCalendar.get(12)));
				break;
			case 16:
				str = formatTime(Integer.valueOf(localCalendar.get(2) + 1))
						+ "."
						+ formatTime(Integer.valueOf(localCalendar.get(5)))
						+ " "
						+ formatTime(Integer.valueOf(localCalendar.get(11)))
						+ ":"
						+ formatTime(Integer.valueOf(localCalendar.get(12)));
				break;
			case 20:
				str = toString(Integer.valueOf(localCalendar.get(11)))
						+ toString(Integer.valueOf(localCalendar.get(12)))
						+ toString(Integer.valueOf(localCalendar.get(13)));
				break;
			case 21:
				str = toString(Integer.valueOf(localCalendar.get(11))) + ":"
						+ toString(Integer.valueOf(localCalendar.get(12)))
						+ ":"
						+ toString(Integer.valueOf(localCalendar.get(13)));
				break;
			case 30:
				str = toString(Integer.valueOf(localCalendar.get(1)))
						+ formatTime(Integer.valueOf(localCalendar.get(2) + 1))
						+ formatTime(Integer.valueOf(localCalendar.get(5)))
						+ formatTime(Integer.valueOf(localCalendar.get(11)))
						+ formatTime(Integer.valueOf(localCalendar.get(12)))
						+ formatTime(Integer.valueOf(localCalendar.get(13)));
				break;
			case 100:
				str = toString(Integer.valueOf(localCalendar.get(1))) + "-"
						+ formatTime(Integer.valueOf(localCalendar.get(2) + 1))
						+ "-"
						+ formatTime(Integer.valueOf(localCalendar.get(5)))
						+ " "
						+ formatTime(Integer.valueOf(localCalendar.get(11)))
						+ ":"
						+ formatTime(Integer.valueOf(localCalendar.get(12)))
						+ ":"
						+ formatTime(Integer.valueOf(localCalendar.get(13)));
			}
		} catch (Exception localException) {
		}
		return str;
	}

	/**
	 * 按参数2指定的模式去格式化参数1
	 * 
	 * @param paramDouble
	 * @param paramString
	 * @return
	 */
	public static String formatDouble(Double paramDouble, String paramString) {
		String str1 = "";
		Double localDouble = paramDouble;
		String str2 = paramString;
		DecimalFormat localDecimalFormat = new DecimalFormat(str2);
		str1 = localDecimalFormat.format(localDouble);
		return str1;
	}

	/**
	 * paramInteger<10 返回这个数前面加0，否则直接返回
	 * 
	 * @param paramInteger
	 * @return
	 */
	public static String formatTime(Integer paramInteger) {
		String str = "";
		Integer localInteger = paramInteger;
		if (localInteger.intValue() < 10)
			str = "0";
		str = str + toString(localInteger);
		return str;
	}

	/**
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @param paramString3
	 *            分隔??
	 * @return
	 */
	public static String formatText(String paramString1, String paramString2,
			String paramString3) {
		String str1 = "";
		String str2 = paramString1;
		String str3 = paramString2;
		String str4 = paramString3;
		// 如果str2不为空
		if (!(isEmpty(str2).booleanValue())) {
			// 将str2按str4分隔
			String[] arrayOfString = str2.split(Pattern.quote(str4));
			for (int i = 0; i < arrayOfString.length; ++i) {
				str1 = str1 + str3;
				String str5 = getString(arrayOfString[i]);
				str1 = str1.replace("[text]", str5);
				str1 = str1.replace("[i]", toString(Integer.valueOf(i)));
				str1 = str1.replace("[text-htmlencode]", encode
						.htmlencode(str5));
				str1 = str1.replace("[text-base64encode]", encode
						.base64encode(str5.getBytes()));
			}
		}
		return str1;
	}

	/**
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @return
	 */
	public static String formatTextLine(String paramString1, String paramString2) {
		String str1 = "";
		String str2 = paramString1;
		String str3 = paramString2;
		// 将str2中所有的'\r\n'替换为\n','\n\r'替换为\n'
		str2 = str2.replace(String.valueOf('\r') + String.valueOf('\n'), String
				.valueOf('\n'));
		str2 = str2.replace(String.valueOf('\n'), String.valueOf('\r')
				+ String.valueOf('\n'));
		String str4 = String.valueOf('\r') + String.valueOf('\n');
		// 如果str2不为空
		if (!(isEmpty(str2).booleanValue())) {
			String[] arrayOfString = str2.split(Pattern.quote(str4));
			for (int i = 0; i < arrayOfString.length; ++i) {
				str1 = str1 + str3;
				String str5 = getString(arrayOfString[i]);
				str1 = str1.replace("[text]", str5);
				str1 = str1.replace("[i]", toString(Integer.valueOf(i)));
				str1 = str1.replace("[text-htmlencode]", encode
						.htmlencode(str5));
				str1 = str1.replace("[text-base64encode]", encode
						.base64encode(str5.getBytes()));
			}
		}
		return str1;
	}

	/**
	 * 根据Long形paramLong*1000所对应的毫秒数所对应的日期格式为yyyy-MM-dd HH:mm:ss的字符串
	 * 
	 * @param paramLong
	 * @return
	 */
	public static String formatUnixStampDate(Long paramLong) {
		String str1 = "";
		Long localLong = paramLong;
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		try {
			TimeZone localTimeZone = TimeZone.getTimeZone("UTC");
			Calendar localCalendar = Calendar.getInstance(localTimeZone);
			localCalendar.setTimeInMillis(localLong.longValue() * 1000L);
			String str2 = toString(Integer.valueOf(localCalendar.get(1))) + "-"
					+ toString(Integer.valueOf(localCalendar.get(2) + 1)) + "-"
					+ toString(Integer.valueOf(localCalendar.get(5))) + " "
					+ toString(Integer.valueOf(localCalendar.get(11))) + ":"
					+ toString(Integer.valueOf(localCalendar.get(12))) + ":"
					+ toString(Integer.valueOf(localCalendar.get(13)));
			str1 = formatDate(str2);
		} catch (Exception localException) {
		}
		return str1;
	}

	/**
	 * 如果某一行的第0列的字符串和参数paramString相等，则返回该行第一列对象的toString()串
	 * 
	 * @param paramArrayOfObject
	 *            对象二维数组
	 * @param paramString
	 * @return
	 */
	public static Object getAryValue(Object[][] paramArrayOfObject,
			String paramString) {
		Object localObject = null;
		Object[][] arrayOfObject = paramArrayOfObject;
		String str = paramString;
		for (int i = 0; i < arrayOfObject.length; ++i) {
			if (!(str.equals(toString(arrayOfObject[i][0]))))
				continue;
			localObject = arrayOfObject[i][1];
			break;
		}

		return localObject;
	}

	/**
	 * 获得东八区当前系统时间的yyyy-MM-dd HH:mm:ss格式的字符串
	 * 
	 * @return
	 */
	public static String getDate() {
		String str1 = "";
		TimeZone localTimeZone = TimeZone.getTimeZone("GMT+8");
		Calendar localCalendar = Calendar.getInstance(localTimeZone);
		String str2 = toString(Integer.valueOf(localCalendar.get(1))) + "-"
				+ toString(Integer.valueOf(localCalendar.get(2) + 1)) + "-"
				+ toString(Integer.valueOf(localCalendar.get(5))) + " "
				+ toString(Integer.valueOf(localCalendar.get(11))) + ":"
				+ toString(Integer.valueOf(localCalendar.get(12))) + ":"
				+ toString(Integer.valueOf(localCalendar.get(13)));
		str1 = formatDate(str2);
		return str1;
	}

	/**
	 * 将传入的时间格式的参数转为yyyy-MM-dd HH:mm:ss"格式的时间字符串 如果转换失败，则返回系统当前时间"yyyy-MM-dd
	 * HH:mm:ss"格式的字符串
	 * 
	 * @param paramString
	 * @return
	 */
	public static String getDate(String paramString) {
		String str1 = paramString;
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			Date localDate = localSimpleDateFormat.parse(str1);
			Calendar localCalendar = Calendar.getInstance();
			localCalendar.setTime(localDate);
			String str2 = toString(Integer.valueOf(localCalendar.get(1))) + "-"
					+ toString(Integer.valueOf(localCalendar.get(2) + 1)) + "-"
					+ toString(Integer.valueOf(localCalendar.get(5))) + " "
					+ toString(Integer.valueOf(localCalendar.get(11))) + ":"
					+ toString(Integer.valueOf(localCalendar.get(12))) + ":"
					+ toString(Integer.valueOf(localCalendar.get(13)));
			str1 = formatDate(str2);
		} catch (Exception localException) {
			str1 = getDate();
		}
		return str1;
	}

	/**
	 * 返回paramString转成Double，如果失败，返回0d
	 * 
	 * @param paramString
	 * @return
	 */
	public static Double getDouble(String paramString) {
		Double localDouble = Double.valueOf(0.0D);
		String str = paramString;
		localDouble = getDouble(str, Double.valueOf(0.0D));
		return localDouble;
	}

	/**
	 * 返回paramString转成Double，如果失败，返回paramDouble
	 * 
	 * @param paramString
	 * @param paramDouble
	 * @return
	 */
	public static Double getDouble(String paramString, Double paramDouble) {
		String str = paramString;
		Double localDouble1 = paramDouble;
		Double localDouble2 = localDouble1;
		try {
			localDouble2 = Double.valueOf(Double.parseDouble(str));
		} catch (Exception localException) {
		}
		return localDouble2;
	}

	/**
	 * 返回长度为paramInt的随机数字和小写字母的字符串
	 * 
	 * @param paramInt
	 * @return
	 */
	public static String getRandomString(int paramInt) {
		String str1 = "";
		int i = paramInt;
		String str2 = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
		String[] arrayOfString = str2.split(Pattern.quote(","));
		Random localRandom = new Random();
		for (int j = 0; j < i; ++j) {
			int k = localRandom.nextInt(arrayOfString.length);
			str1 = str1 + arrayOfString[k];
		}
		return str1;
	}

	/**
	 * 当前系统时间和1970-1-1 0:00:00 之间的毫秒数
	 * 
	 * @return
	 */
	public static Long getUnixStamp() {
		String str = getDate();
		Long localLong = getUnixStamp(str);
		return localLong;
	}

	/**
	 * 传入一个日期格式的字符串，返回该字符串所对应的日期和1970-1-1 0:00:00 之间的毫秒数
	 * 
	 * @param paramString
	 * @return
	 */
	public static Long getUnixStamp(String paramString) {
		Long localLong = Long.valueOf(0L);
		String str1 = paramString;
		String str2 = getDate(str1);
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			Date localDate1 = localSimpleDateFormat.parse(str2);
			Date localDate2 = localSimpleDateFormat.parse("1970-1-1 0:00:00");
			localLong = Long.valueOf((localDate1.getTime() - localDate2
					.getTime()) / 1000L);
		} catch (Exception localException) {
		}
		return localLong;
	}

	/**
	 * 如果paramString1不为空，返回paramString1，否则返回paramString12
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @return
	 */
	public static String getHstr(String paramString1, String paramString2) {
		Object localObject = "";
		String str1 = getString(paramString1);
		String str2 = getString(paramString2);
		localObject = str1;
		if (isEmpty(localObject).booleanValue())
			localObject = str2;
		return ((String) localObject);
	}

	/**
	 * 获得paramString1左边paramInteger位字符形成的字符串中文算两个
	 * 
	 * @param paramString1
	 * @param paramInteger
	 * @return
	 */
	public static String getLeft(String paramString, Integer paramInteger) {
		String str1 = "";
		String str2 = paramString;
		Integer localInteger = paramInteger;
		str1 = getLeft(str2, localInteger, "");
		return str1;
	}

	/**
	 * 返回paramString1的左边paramInteger位字符连接上paramString2形成的字符串
	 * 如果paramInteger大于paramString1的长度，直接返回paramString1 中文算两个
	 * 
	 * @param paramString1
	 * @param paramInteger
	 * @param paramString2
	 * @return
	 */
	public static String getLeft(String paramString1, Integer paramInteger,
			String paramString2) {
		String str1 = "";
		Integer localInteger = paramInteger;
		String str2 = getString(paramString1);
		String str3 = getString(paramString2);
		if (!(isEmpty(str2).booleanValue())) {
			if (localInteger.intValue() > str2.length())
				localInteger = Integer.valueOf(str2.length());
			str1 = str2.substring(0, localInteger.intValue());
		}
		if (!(str1.equals(str2)))
			str1 = str1 + str3;
		return str1;
	}

	/**
	 * 获得paramString1左边paramInteger位字符形成的字符串中文算一个
	 * 
	 * @param paramString
	 * @param paramInteger
	 * @return
	 */
	public static String getLeftB(String paramString, Integer paramInteger) {
		String str1 = "";
		String str2 = paramString;
		Integer localInteger = paramInteger;
		str1 = getLeftB(str2, localInteger, "");
		return str1;
	}

	/**
	 * 返回paramString1的左边paramInteger位字符连接上paramString2形成的字符串
	 * 如果paramInteger大于paramString1的长度，直接返回paramString1 中文算一位
	 * 
	 * @param paramString1
	 * @param paramInteger
	 * @param paramString2
	 * @return
	 */
	public static String getLeftB(String paramString1, Integer paramInteger,
			String paramString2) {
		String str1 = "";
		Integer localInteger1 = paramInteger;
		String str2 = getString(paramString1);
		String str3 = getString(paramString2);
		if (!(isEmpty(str2).booleanValue())) {
			Integer localInteger2 = Integer.valueOf(0);
			for (int i = 0; i < str2.length(); ++i) {
				String str4 = str2.substring(i, i + 1);
				if (validator.isChinese(str4).booleanValue()) {
					localInteger2 = Integer
							.valueOf(localInteger2.intValue() + 2);
				} else {
					localInteger2 = Integer
							.valueOf(localInteger2.intValue() + 1);
				}
				if (localInteger2.intValue() > localInteger1.intValue())
					break;
				str1 = str1 + str4;
			}
		}

		if (!(str1.equals(str2)))
			str1 = str1 + str3;
		return str1;
	}

	/**
	 * 将传入参数转成Integer对象，若转不了，则返回
	 * 
	 * @param paramString
	 * @return
	 */
	public static Integer getNum(String paramString) {
		String str = paramString;
		Integer localInteger = getNum(str, Integer.valueOf(0));
		return localInteger;
	}

	/**
	 * 如果String参数能转换成Integer对象，则返回转换后的对象，否则返回Integer参数2
	 * 
	 * @param paramString
	 * @param paramInteger
	 * @return
	 */
	public static Integer getNum(String paramString, Integer paramInteger) {
		String str = paramString;
		Integer localInteger1 = paramInteger;
		Integer localInteger2 = localInteger1;
		try {
			localInteger2 = Integer.valueOf(Integer.parseInt(str));
		} catch (Exception localException) {
		}
		return localInteger2;
	}

	/**
	 * 返回paramString对应的Long值，否则返回0
	 * 
	 * @param paramString
	 * @return
	 */
	public static Long getNum64(String paramString) {
		String str = paramString;
		Long localLong = getNum64(str, Long.valueOf(0L));
		return localLong;
	}

	/**
	 * 如果参数1能够转成Long，则返回该Long，否则返回参数
	 * 
	 * @param paramString
	 * @param paramLong
	 * @return
	 */
	public static Long getNum64(String paramString, Long paramLong) {
		String str = paramString;
		Long localLong1 = paramLong;
		Long localLong2 = localLong1;
		try {
			localLong2 = Long.valueOf(Long.parseLong(str));
		} catch (Exception localException) {
		}
		return localLong2;
	}

	/**
	 * 返回paramString右边paramInteger位的字符串
	 * ，如果paramInteger大于paramString的长度，返回paramString本身
	 * 
	 * @param paramString
	 * @param paramInteger
	 * @return
	 */
	public static String getRight(String paramString, Integer paramInteger) {
		String str1 = "";
		Integer localInteger1 = paramInteger;
		String str2 = getString(paramString);
		if (!(isEmpty(str2).booleanValue())) {
			Integer localInteger2 = Integer.valueOf(str2.length());
			Integer localInteger3 = Integer.valueOf(localInteger2.intValue()
					- localInteger1.intValue());
			if (localInteger3.intValue() < 0)
				localInteger3 = Integer.valueOf(0);
			str1 = str2.substring(localInteger3.intValue(), localInteger2
					.intValue());
		}
		return str1;
	}

	/**
	 * 返回paramInteger-1个paramString组成的字符串
	 * 
	 * @param paramString
	 * @param paramInteger
	 * @return
	 */
	public static String getRepeatedString(String paramString,
			Integer paramInteger) {
		String str1 = paramString;
		Integer localInteger = paramInteger;
		String str2 = "";
		for (int i = 1; i < localInteger.intValue(); ++i)
			str2 = str2 + str1;
		return str2;
	}

	/**
	 * 传入字符串对象，null返回空字符串，否则直接返回
	 * 
	 * @param paramString
	 * @return
	 */
	public static String getString(String paramString) {
		String str = paramString;
		if (str == null)
			str = "";
		return str;
	}

	/**
	 * 将paramString中的'、;、--替换为空字符串
	 * 
	 * @param paramString
	 * @return
	 */
	public static String getSafeString(String paramString) {
		String str = paramString;
		str = getString(str);
		str = str.replace("'", "");
		str = str.replace(";", "");
		str = str.replace("--", "");
		return str;
	}

	/**
	 * 根据参数3，返回参数中的某段字符串，left：首字母到第一次出现参数（不包括参数2) right:
	 * 最后一次出现参数2到最后（不包括参2) lefttr 首字母到最后一次出现参数2（不包括参数2) rightr
	 * 第一次出现参数2至末尾（不包括参2)
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @param paramString3
	 *            left 或者right 或leftr 或 rightr
	 * @return
	 */
	public static String getLRStr(String paramString1, String paramString2,
			String paramString3) {
		Object localObject = "";
		String str1 = paramString1;
		String str2 = paramString2;
		String str3 = paramString3;
		if ((isEmpty(str2).booleanValue()) || (str1.indexOf(str2) == -1))
			localObject = str1;
		else if (str3.equals("left"))
			localObject = str1.substring(0, str1.indexOf(str2));
		else if (str3.equals("leftr"))
			localObject = str1.substring(0, str1.lastIndexOf(str2));
		else if (str3.equals("right"))
			localObject = str1.substring(
					str1.lastIndexOf(str2) + str2.length(), str1.length());
		else if (str3.equals("rightr"))
			localObject = str1.substring(str1.indexOf(str2) + str2.length(),
					str1.length());
		else
			localObject = str1;

		return ((String) localObject);
	}

	public static String getParameter(String paramString1, String paramString2) {
		String str1 = "";
		String str2 = paramString1;
		String str3 = paramString2;
		str1 = getParameter(str2, str3, ";");
		return str1;
	}

	public static String getParameter(String paramString1, String paramString2,
			String paramString3) {
		String str1 = "";
		String str2 = paramString1;
		String str3 = paramString2;
		String str4 = paramString3;
		Pattern localPattern = Pattern.compile("((?:^|" + str4 + ")" + str3
				+ "=(.[^" + str4 + "]*))");
		Matcher localMatcher = localPattern.matcher(str2);
		if (localMatcher.find())
			str1 = localMatcher.group(2);
		return str1;
	}

	/**
	 * 传入对象为null 返回true，传入对象的toString方法为空字符，返回true， 否则返回false
	 * 
	 * @param paramObject
	 * @return
	 */
	public static Boolean isEmpty(Object paramObject) {
		Boolean localBoolean = Boolean.valueOf(false);
		Object localObject = paramObject;
		String str = toString(localObject);
		if (str.equals(""))
			localBoolean = Boolean.valueOf(true);
		return localBoolean;
	}

	/**
	 * 合并数组 paramArrayOfObject1 放到 paramArrayOfObject最后一个元素后
	 * 
	 * @param paramArrayOfObject
	 * @param paramArrayOfObject1
	 * @return
	 */
	public static Object[] mergeAry(Object[] paramArrayOfObject,
			Object[][] paramArrayOfObject1) {
		Object[] arrayOfObject1 = null;
		Object[] arrayOfObject2 = paramArrayOfObject;
		Object[][] arrayOfObject = paramArrayOfObject1;
		if (arrayOfObject2 == null) {
			arrayOfObject1 = new Object[1];
			arrayOfObject1[0] = arrayOfObject;
		} else {
			int i = arrayOfObject2.length;
			arrayOfObject1 = new Object[i + 1];
			for (int j = 0; j < i; ++j) {
				arrayOfObject1[j] = arrayOfObject2[j];
			}
			arrayOfObject1[(arrayOfObject1.length - 1)] = arrayOfObject;
		}
		return arrayOfObject1;
	}

	/**
	 * 将paramArrayOfString2合并到paramArrayOfString1下面 维数不一致返回null
	 * 
	 * @param paramArrayOfString1
	 * @param paramArrayOfString2
	 * @return
	 */
	public static String[][] mergeAry(String[][] paramArrayOfString1,
			String[][] paramArrayOfString2) {
		Object localObject = (String[][]) null;
		String[][] arrayOfString1 = paramArrayOfString1;
		String[][] arrayOfString2 = paramArrayOfString2;
		if (arrayOfString1 == null)
			localObject = arrayOfString2;
		if (arrayOfString2 == null)
			localObject = arrayOfString1;
		if (localObject == null) {
			Integer localInteger1 = Integer.valueOf(arrayOfString1.length);
			Integer localInteger2 = Integer.valueOf(arrayOfString1[0].length);
			Integer localInteger3 = Integer.valueOf(arrayOfString2.length);
			Integer localInteger4 = Integer.valueOf(arrayOfString2[0].length);
			if (localInteger2.equals(localInteger4)) {
				int i, j;
				localObject = new String[localInteger1.intValue()
						+ localInteger3.intValue()][localInteger2.intValue()];
				for (i = 0; i < localInteger1.intValue(); ++i) {
					for (j = 0; j < localInteger2.intValue(); ++j) {
						((String[][]) localObject)[i][j] = arrayOfString1[i][j];
					}
				}
				for (i = 0; i < localInteger3.intValue(); ++i) {
					for (j = 0; j < localInteger4.intValue(); ++j) {
						((String[][]) localObject)[(i + localInteger1
								.intValue())][j] = arrayOfString2[i][j];
					}
				}
			}
		}
		return ((String[][]) localObject);
	}

	/**
	 * 返回将paramString1中paramString2替换为paramString3
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @param paramString3
	 * @return
	 */
	public static String replace(String paramString1, String paramString2,
			String paramString3) {
		String str1 = getString(paramString1);
		String str2 = getString(paramString2);
		String str3 = getString(paramString3);
		if (!(isEmpty(str1).booleanValue()))
			str1 = str1.replace(str2, str3);
		return str1;
	}

	/**
	 * 返回 paramString1&paramString2=paramString3 ，
	 * paramString1:http:www.xxx.com?type=1
	 * ,paramString2:name,paramString3:zhangsan 返回
	 * http:www.xxx.com?type=1&name=zhangsan
	 * ,如果paramString1为http:www.xxx.com?type=1&name=lisi则返回http:www.xxx.com?type=1&name=lisi
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @param paramString3
	 * @return
	 */
	public static String reparameter(String paramString1, String paramString2,
			String paramString3) {
		String str1 = "";
		String str2 = paramString1;
		String str3 = paramString2;
		String str4 = paramString3;
		if (isEmpty(str2).booleanValue()) {
			str1 = str3 + "=" + str4;
		} else {
			String str5 = "&" + str2;
			if (str5.indexOf("&" + str3 + "=") == -1) {
				str1 = str2 + "&" + str3 + "=" + str4;
			} else {
				String str6 = getLRStr(str5, "&" + str3 + "=", "rightr");
				str6 = getLRStr(str6, "&", "left");
				str1 = str5.replace("&" + str3 + "=" + str6, "&" + str3 + "="
						+ str4);
				str1 = getLRStr(str1, "&", "rightr");
			}
		}
		return str1;
	}

	/**
	 * 返回传入对象的toString方法,null返回空字符串
	 * 
	 * @param paramObject
	 * @return
	 */
	public static String toString(Object paramObject) {
		String str = "";
		Object localObject = paramObject;
		if (localObject != null)
			str = localObject.toString();
		return str;
	}
}
