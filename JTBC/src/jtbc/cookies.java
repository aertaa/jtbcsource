package jtbc;

import javax.servlet.http.Cookie;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

/**
 * 该类为对cookies的属性的操作,包括得到、设置、移除某个cookie
 * 
 * @author Administrator
 * 
 */
public class cookies {
	/* 缓存的默认路径,在同一应用服务器内共享cookie
	 * 若想设置为当前项目，则设置成"/ProjectName/"
	*/
	private static String cookiesPath = "/";

	/**
	 * 该方法根据传入的页面属性封装类conf得到页面当前的请求request
	 * 从而获得页面内所有的cookie，再根据参数得到cookie名称从而得到cookie值
	 * 
	 * @param paramconf
	 *            含有属性request和charset
	 * @param paramString
	 *            cookie名字
	 * @return String
	 */
	public static String getAttribute(conf paramconf, String paramString) {
		String str1 = "";
		conf localconf = paramconf;
		String str2 = paramString;
		Cookie[] arrayOfCookie = localconf.request.getCookies();
		if (arrayOfCookie != null) {
			for (int i = 0; i < arrayOfCookie.length; ++i) {
				Cookie localCookie = arrayOfCookie[i];
				if (!(str2.equals(localCookie.getName())))
					continue;
				try {
					// 将cookie值按页面当前配置的编码转码
					str1 = new String(encode.base64decode(localCookie
							.getValue()), localconf.charset);
				} catch (Exception localException) {
				}
			}
		}
		return str1;
	}

	/**
	 * 该方法根据传入的页面属性封装类conf得到页面当前的请求request
	 * 从而获得页面内所有的cookie，再根据参数cookie名称获得对象，从而移除。
	 * 
	 * @param paramconf
	 *            含有属性request和charset
	 * @param paramString
	 *            cookie名字
	 * @return String
	 */
	public static void removeAttribute(conf paramconf, String paramString) {
		conf localconf = paramconf;
		String str = paramString;
		Cookie[] arrayOfCookie = localconf.request.getCookies();
		if (arrayOfCookie == null)
			return;
		for (int i = 0; i < arrayOfCookie.length; ++i) {
			Cookie localCookie = arrayOfCookie[i];
			if (!(str.equals(localCookie.getName())))
				continue;
			localCookie.setMaxAge(0);
			localCookie.setPath(cookiesPath);
			localconf.response.addCookie(localCookie);
		}
	}

	/**
	 * 该方法根据传入的页面属性封装类conf得到页面当前的请求request
	 * 从而获得页面内所有的cookie，再根据参数cookie名称获得对象，从而移除。
	 * 
	 * @param paramconf
	 *            含有属性request和charset
	 * @param paramString
	 *            cookie名字
	 * @return String
	 */
	public static void setAttribute(conf paramconf, String paramString1,
			String paramString2) {
		conf localconf = paramconf;
		String str1 = paramString1;
		String str2 = paramString2;
		setAttribute(localconf, str1, str2, Integer.valueOf(-1));
	}

	/**
	 * 该方法根据传入的页面属性封装类conf得到页面当前的请求request
	 * 从而获得页面内所有的cookie，再根据参数cookie名称获得对象，从而移除。
	 * 
	 * @param paramconf
	 * @param paramString1
	 *            cookie 名称
	 * @param paramString2
	 *            cookie 值
	 * @param paramInteger
	 *            cookie的超时时间，单位为秒，设为0表示删除cookie，
	 *            负值则表示cookie非持久的，关闭浏览器时会删除
	 */
	public static void setAttribute(conf paramconf, String paramString1,
			String paramString2, Integer paramInteger) {
		conf localconf = paramconf;
		String str2 = paramString1;
		String str3 = paramString2;
		Integer localInteger = paramInteger;
		try {
			Cookie localCookie = new Cookie(str2, encode.base64encode(str3
					.getBytes(localconf.charset)));
			localCookie.setMaxAge(localInteger.intValue());
			localCookie.setPath(cookiesPath);
			localconf.response.addCookie(localCookie);
		} catch (Exception localException) {
		}
	}
}