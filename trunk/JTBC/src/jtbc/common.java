package jtbc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import jtbc.dbc.dbc;

public class common {
	public conf conf;

	/**
	 * 当地址为绝对地址时返回地址，否则返回基地址与地址的组合地址。	 * 
	 * @param paramString1
	 * @param paramString2
	 * @return
	 */
	public String curl(String paramString1, String paramString2) {
		Object localObject = "";
		String str1 = paramString1;
		String str2 = paramString2;
		// 如果第二个参数不为空
		if (!(cls.isEmpty(str2).booleanValue())) {
			// 如果第二个参数以"/"开头，就赋值给localObject
			if (cls.getLeft(str2, Integer.valueOf(1)).equals("/"))
				localObject = str2;
			// 如果第一个参数为空或者第一个参数以"/"结尾
			else if ((cls.isEmpty(str1).booleanValue())
					|| (cls.getRight(str1, Integer.valueOf(1)).equals("/")))
				// 把两个字符串参数连接，赋值给localObject
				localObject = str1 + str2;
			else
				// 两个参数用"/"隔开
				localObject = str1 + "/" + str2;
		}
		// 返回localObject
		return ((String) localObject);
	}

	/**
	 * 当模板代码中带有验证码时可以使用此函数来处理代码使之是否显示验证码。
	 * @param paramString
	 * @return
	 */
	public String crValcodeTpl(String paramString) {
		String str1 = "";
		String str2 = paramString;
		String str3 = this.conf.jt.itake("global.config.nvalidate", "cfg");
		str1 = crValHtml(str2, str3, "{@valcode@}");
		return str1;
	}

	public String crValHtml(String paramString1, String paramString2,
			String paramString3) {
		Object localObject1 = "";
		String str2 = paramString1;
		String str3 = paramString2;
		String str4 = paramString3;
		localObject1 = str2;
		Object localObject2 = "";
		String str1 = cls.ctemplate((String) localObject1, str4);
		if (str3.equals("1"))
			localObject2 = str1;
		localObject1 = cls.ctemplates((String) localObject1, str4,
				(String) localObject2);
		return ((String) (String) localObject1);
	}

	
	public Boolean ckValcode(String paramString) {
		Boolean localBoolean = Boolean.valueOf(false);
		String str1 = paramString;
		String str2 = (String) this.conf.session.getAttribute("valcode");
		if (!(cls.isEmpty(str2).booleanValue())) {
			str1 = cls.getString(str1);
			str2 = cls.getString(str2);
			str1 = str1.toLowerCase();
			str2 = str2.toLowerCase();
			if (str1.equals(str2))
				localBoolean = Boolean.valueOf(true);
		}
		return localBoolean;
	}

	
	public Boolean ckValcodes(String paramString) {
		Boolean localBoolean = Boolean.valueOf(true);
		String str1 = paramString;
		String str2 = this.conf.jt.itake("global.config.nvalidate", "cfg");
		if (str2.equals("1"))
			localBoolean = ckValcode(str1);
		return localBoolean;
	}

	/**
	 * 不带其他条件的删除，就带in（）中的条件
	 * 
	 * @param paramString1
	 *            表名
	 * @param paramString2
	 *            列名条件，如s1主键
	 * @param paramString3
	 *            in( )中的数据，
	 * @return 删除的行数
	 */
	public Integer dataDelete(String paramString1, String paramString2,
			String paramString3) {
		Integer localInteger = Integer.valueOf(0);
		String str1 = paramString1;
		String str2 = paramString2;
		String str3 = paramString3;
		localInteger = dataDelete(str1, str2, str3, "");
		return localInteger;
	}

	/**
	 * 删除符合条件的数据
	 * 
	 * @param paramString1
	 *            表名
	 * @param paramString2
	 *            列名条件，如s1主键
	 * @param paramString3
	 *            in( )中的数据，
	 * @param paramString4
	 *            另外的附加添加，如and name='a'
	 * @return 删除的行数
	 */
	public Integer dataDelete(String paramString1, String paramString2,
			String paramString3, String paramString4) {
		String str1 = cls.getString(paramString1);
		String str2 = cls.getString(paramString2);
		String str3 = cls.getString(paramString3);
		String str4 = cls.getString(paramString4);
		Integer localInteger = Integer.valueOf(-101);
		if (cls.cidary(str3).booleanValue()) {// 是否str3全为数字，其中用逗号隔开
			String str5 = "delete from " + str1 + " where " + str2 + " in ("
					+ str3 + ")";
			if (!(cls.isEmpty(str4).booleanValue()))// 如果str4不为空，就作为条件与str5的sql语句拼接
				str5 = str5 + str4;
			dbc localdbc = db.newInstance(this.conf);
			localInteger = Integer.valueOf(localdbc.Executes(str5));// 执行sql语句，返回受影响行数
		}
		return localInteger;
	}

	/**
	 * 修改数据
	 * 
	 * @param paramString1
	 *            表名
	 * @param paramString2
	 *            要修改的列名
	 * @param paramString3
	 *            in前面的条件列
	 * @param paramString4
	 *            in（）中的条件，必须为数字
	 * @return 修改的行数
	 */
	public Integer dataSwitch(String paramString1, String paramString2,
			String paramString3, String paramString4) {
		Integer localInteger = Integer.valueOf(0);
		String str1 = paramString1;
		String str2 = paramString2;
		String str3 = paramString3;
		String str4 = paramString4;
		localInteger = dataSwitch(str1, str2, str3, str4, "");
		return localInteger;
	}

	/**
	 * 修改数据
	 * 
	 * @param paramString1
	 *            表名
	 * @param paramString2
	 *            要修改的列名
	 * @param paramString3
	 *            in前面的条件列
	 * @param paramString4
	 *            in（）中的条件，必须为数字
	 * @param paramString5
	 *            附加条件
	 * @return 修改的行数
	 */
	public Integer dataSwitch(String paramString1, String paramString2,
			String paramString3, String paramString4, String paramString5) {
		String str1 = cls.getString(paramString1);
		String str2 = cls.getString(paramString2);
		String str3 = cls.getString(paramString3);
		String str4 = cls.getString(paramString4);
		String str5 = cls.getString(paramString5);
		Integer localInteger = Integer.valueOf(-101);
		if (cls.cidary(str4).booleanValue()) {// 是否str4全为数字，其中用逗号隔开
			String str6 = "update " + str1 + " set " + str2 + "=abs(" + str2
					+ "-1) where " + str3 + " in (" + str4 + ")";
			if (!(cls.isEmpty(str5).booleanValue()))// 如果str5不为空，就作为条件与str6的sql语句拼接
				str6 = str6 + str5;
			dbc localdbc = db.newInstance(this.conf);
			localInteger = Integer.valueOf(localdbc.Executes(str6));// 执行sql语句，返回受影响行数
		}
		return localInteger;
	}

	/**
	 * 删除文件
	 * 
	 * @param paramString
	 *            文件路径
	 * @return
	 */
	public Boolean directoryDelete(String paramString) {
		Boolean localBoolean = Boolean.valueOf(true);
		String str = paramString;
		File localFile = new File(str);
		if ((localFile.exists()) && (localFile.isDirectory())) {
			File[] arrayOfFile = localFile.listFiles();
			for (int i = 0; i < arrayOfFile.length; ++i) {
				if ((arrayOfFile[i].isFile()) && (!(arrayOfFile[i].delete())))
					localBoolean = Boolean.valueOf(false);

				if ((!(arrayOfFile[i].isDirectory()))
						|| (directoryDelete(arrayOfFile[i].toString())
								.booleanValue()))
					continue;
				localBoolean = Boolean.valueOf(false);
			}

			if (!(localFile.delete()))
				localBoolean = Boolean.valueOf(false);
		}
		return localBoolean;
	}

	/**
	 * 创建文件
	 * 
	 * @param paramString
	 *            文件路径
	 * @return
	 */
	public Boolean directoryCreate(String paramString) {
		Boolean localBoolean = Boolean.valueOf(false);
		String str = paramString;
		File localFile = new File(str);
		if ((localFile.exists()) && (localFile.isDirectory()))
			localBoolean = Boolean.valueOf(true);
		else {
			try {
				localFile.mkdirs();
				localBoolean = Boolean.valueOf(true);
			} catch (Exception localException) {
			}
		}
		return localBoolean;
	}

	/**
	 * 创建文件
	 * 
	 * @param paramString
	 *            文件路径
	 * @return
	 */
	public Boolean directoryCreateNew(String paramString) {
		Boolean localBoolean = Boolean.valueOf(false);
		String str = paramString;
		File localFile = new File(str);
		if ((!(localFile.exists())) || (!(localFile.isDirectory()))) {
			try {
				localFile.mkdirs();
				localBoolean = Boolean.valueOf(true);
			} catch (Exception localException) {
			}
		}
		return localBoolean;
	}

	/**
	 * 在该路径下，该文件是否存在
	 * 
	 * @param paramString
	 *            文件路径
	 * @return
	 */
	public Boolean directoryExists(String paramString) {
		Boolean localBoolean = Boolean.valueOf(false);
		String str = paramString;
		File localFile = new File(str);
		if ((localFile.exists()) && (localFile.isDirectory()))
			localBoolean = Boolean.valueOf(true);
		return localBoolean;
	}

	/**
	 * 把源文件移动到目标文件
	 * 
	 * @param paramString1
	 *            源文件路径和文件名称
	 * @param paramString2
	 *            目标文件路径和文件名
	 * @return
	 */
	public Boolean directoryMove(String paramString1, String paramString2) {
		Boolean localBoolean = Boolean.valueOf(false);
		String str1 = paramString1;
		String str2 = paramString2;
		File localFile1 = new File(str1);
		File localFile2 = new File(str2);
		if ((localFile1.exists())
				&& (localFile1.isDirectory())
				&& (((!(localFile2.exists())) || (!(localFile2.isDirectory()))))
				&& (localFile1.renameTo(localFile2)))// 源文件存在，目标文件不存在，移动源文件
			localBoolean = Boolean.valueOf(true);

		return localBoolean;
	}

	/**
	 * 把字节数组写入文件
	 * 
	 * @param paramString
	 *            文件名，带路径
	 * @param paramArrayOfByte
	 *            字节数组
	 * @return
	 */
	public Boolean fileCreate(String paramString, byte[] paramArrayOfByte) {
		Boolean localBoolean = Boolean.valueOf(true);
		String str = paramString;
		byte[] arrayOfByte = paramArrayOfByte;
		try {
			FileOutputStream localFileOutputStream = new FileOutputStream(str);
			localFileOutputStream.write(arrayOfByte);
			localFileOutputStream.close();
		} catch (Exception localException) {
			localBoolean = Boolean.valueOf(false);
		}
		return localBoolean;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param paramString
	 *            文件路径
	 * @return
	 */
	public Boolean fileExists(String paramString) {
		Boolean localBoolean = Boolean.valueOf(false);
		String str = paramString;
		File localFile = new File(str);
		if ((localFile.exists()) && (localFile.isFile()))
			localBoolean = Boolean.valueOf(true);
		return localBoolean;
	}

	/**
	 * 删除文件
	 * 
	 * @param paramString
	 *            文件路径
	 * @return
	 */
	public Boolean fileDelete(String paramString) {
		Boolean localBoolean = Boolean.valueOf(true);
		String str = paramString;
		File localFile = new File(str);
		if ((localFile.exists()) && (localFile.isFile())
				&& (!(localFile.delete())))
			localBoolean = Boolean.valueOf(false);

		return localBoolean;
	}

	/**
	 * 读取文件
	 * 
	 * @param paramString
	 *            文件路径
	 * @return
	 */
	public String fileGetContents(String paramString) {
		String str1 = "";
		String str2 = paramString;
		try {
			FileInputStream localFileInputStream = new FileInputStream(str2);
			InputStreamReader localInputStreamReader = new InputStreamReader(
					localFileInputStream, this.conf.charset);
			BufferedReader localBufferedReader = new BufferedReader(
					localInputStreamReader);
			while (true) {
				String str3 = localBufferedReader.readLine();
				if (str3 == null)
					break;
				str1 = str1 + str3 + "\r\n";
			}
			if (!(cls.isEmpty(str1).booleanValue()))
				str1 = cls.getLRStr(str1, "\r\n", "leftr");
		} catch (Exception localException) {
			str1 = null;
		}
		return str1;
	}

	/**
	 * 读取远程文件内容到目标文件
	 * 
	 * @param paramString1
	 *            url
	 * @param paramString2
	 *            字符编码
	 * @return
	 */
	public String fileGetHttpContents(String paramString1, String paramString2) {
		String str1 = "";
		String str2 = paramString1;
		String str3 = paramString2;
		try {
			URL localURL = new URL(str2);
			URLConnection localURLConnection = localURL.openConnection();
			InputStreamReader localInputStreamReader = new InputStreamReader(
					localURLConnection.getInputStream(), str3);
			BufferedReader localBufferedReader = new BufferedReader(
					localInputStreamReader);
			while (true) {
				String str4 = localBufferedReader.readLine();
				if (str4 == null)
					break;
				str1 = str1 + str4 + "\r\n";
			}
			if (!(cls.isEmpty(str1).booleanValue()))
				str1 = cls.getLRStr(str1, "\r\n", "leftr");
		} catch (Exception localException) {
			str1 = null;
		}
		return str1;
	}

	/**
	 * 把paramString2内容写入paramString1文件
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @return
	 */
	public Boolean filePutContents(String paramString1, String paramString2) {
		Boolean localBoolean = Boolean.valueOf(true);
		String str1 = paramString1;
		String str2 = paramString2;
		try {
			FileOutputStream localFileOutputStream = new FileOutputStream(str1);
			localFileOutputStream.write(str2.getBytes(this.conf.charset));
			localFileOutputStream.close();
		} catch (Exception localException) {
			localBoolean = Boolean.valueOf(false);
		}
		return localBoolean;
	}

	/**
	 * 获得后台管理的主题路径
	 * 
	 * @return
	 */
	public String getAdminTheme() {
		String str = "";
		str = this.conf.getActualRoute(this.conf.adminFolder);
		str = str + "/" + this.conf.imagesRoute + "/theme/";
		str = str
				+ this.conf.jt.itake(new StringBuilder().append("global.")
						.append(this.conf.adminFolder).append(":config.theme")
						.toString(), "cfg") + "/";
		return str;
	}

	/**
	 * 按照设置的分页数显示内容中该分页的内容。
	 * @param paramString1 内容字符串
	 * @param paramString2 分页数
	 * @return
	 */
	public String getCuteContent(String paramString1, String paramString2) {
		Object localObject;
		String str1 = "";
		String str2 = paramString1;
		Integer localInteger = cls.getNum(paramString2, Integer.valueOf(-1));
		if (localInteger.intValue() <= 0)
			localInteger = Integer.valueOf(1);
		String str3 = this.conf.jt
				.itake("global.tpl_common.ct-cutepage", "tpl");
		if (str2.indexOf(str3) != -1) {
			localObject = str2.split(Pattern.quote(str3));
			if (localObject != null) {
				if (localInteger.intValue() > ((String[]) localObject).length)
					localInteger = Integer
							.valueOf(((Object[]) localObject).length);
				str1 = ((String[]) localObject)[(localInteger.intValue() - 1)];
			}
		} else {
			localObject = this.conf.jt.itake("global.tpl_common.ct-cutepage-b",
					"tpl");
			String[] arrayOfString = str2.split(Pattern
					.quote((String) localObject));
			if (arrayOfString != null) {
				if (localInteger.intValue() > arrayOfString.length)
					localInteger = Integer.valueOf(arrayOfString.length);
				str1 = arrayOfString[(localInteger.intValue() - 1)];
			}
		}
		return ((String) str1);
	}

	/**
	 * 返回内容字符串中含有分页的总数
	 * @param paramString 内容字符串
	 * @return
	 */
	public String getCuteContentCount(String paramString) {
		Object localObject;
		String str1 = "1";
		String str2 = paramString;
		String str3 = this.conf.jt
				.itake("global.tpl_common.ct-cutepage", "tpl");
		if (str2.indexOf(str3) != -1) {
			localObject = str2.split(Pattern.quote(str3));
			if (localObject != null)
				str1 = cls.toString(Integer
						.valueOf(((Object[]) localObject).length));
		} else {
			localObject = this.conf.jt.itake("global.tpl_common.ct-cutepage-b",
					"tpl");
			String[] arrayOfString = str2.split(Pattern
					.quote((String) localObject));
			if (arrayOfString != null)
				str1 = cls.toString(Integer.valueOf(arrayOfString.length));
		}
		return ((String) str1);
	}

	/**
	 * 获得文件列表数组，包括文件名称，文件长度，最后修改时间
	 * 
	 * @param paramString
	 * @return
	 */
	public String[][] getFileList(String paramString) {
		String str1 = paramString;
		String[][] arrayOfString1 = (String[][]) null;
		File localFile = new File(this.conf.application.getRealPath(
				this.conf.getMapPath(str1)).toString());
		File[] arrayOfFile = localFile.listFiles();
		for (int i = 0; i < arrayOfFile.length; ++i) {
			if (!(arrayOfFile[i].isFile())) {
				continue;
			}
			String str2 = arrayOfFile[i].getName();
			String str3 = cls.toString(Long.valueOf(arrayOfFile[i].length()));
			Date localDate = new Date(arrayOfFile[i].lastModified());
			String str4 = cls.formatDate(localDate);
			String[][] arrayOfString2 = new String[1][3];
			arrayOfString2[0][0] = str2;
			arrayOfString2[0][1] = str3;
			arrayOfString2[0][2] = str4;
			// 合并数组
			arrayOfString1 = cls.mergeAry(arrayOfString1, arrayOfString2);
		}

		return arrayOfString1;
	}

	/**
	 * 获得文件大小
	 * 
	 * @param paramString
	 * @return
	 */
	public Long getFolderSize(String paramString) {
		Long localLong = Long.valueOf(0L);
		String str = paramString;
		File localFile = new File(this.conf.application.getRealPath(
				this.conf.getMapPath(str)).toString());
		File[] arrayOfFile = localFile.listFiles();
		for (int i = 0; i < arrayOfFile.length; ++i) {
			if (arrayOfFile[i].isFile())
				localLong = Long.valueOf(localLong.longValue()
						+ arrayOfFile[i].length());
			if (!(arrayOfFile[i].isDirectory()))
				continue;
			localLong = Long.valueOf(localLong.longValue()
					+ getFolderSize(str + arrayOfFile[i].getName() + "/")
							.longValue());
		}
		return localLong;
	}

	/**
	 * 获得文件列表数组，包括文件名称，文件长度，最后修改时间
	 * 
	 * @param paramString
	 * @return
	 */
	public String[][] getFolderList(String paramString) {
		String str = paramString;
		String[][] arrayOfString = (String[][]) null;
		arrayOfString = getFolderList(str, Integer.valueOf(1));
		return arrayOfString;
	}

	/**
	 * 获得文件列表数组，包括文件名称，文件长度，最后修改时间
	 * 
	 * @param paramString
	 * @param paramInteger
	 * @return
	 */
	public String[][] getFolderList(String paramString, Integer paramInteger) {
		String str1 = paramString;
		Integer localInteger = paramInteger;
		String[][] arrayOfString1 = (String[][]) null;
		File localFile = new File(this.conf.application.getRealPath(
				this.conf.getMapPath(str1)).toString());
		File[] arrayOfFile = localFile.listFiles();
		for (int i = 0; i < arrayOfFile.length; ++i) {
			if (!(arrayOfFile[i].isDirectory()))
				continue;
			String str2 = "-1";
			String str3 = arrayOfFile[i].getName();
			Date localDate = new Date(arrayOfFile[i].lastModified());
			String str4 = cls.formatDate(localDate);
			if (localInteger.intValue() == 1)
				str2 = cls.toString(getFolderSize(str1 + str3 + "/"));
			String[][] arrayOfString2 = new String[1][3];
			arrayOfString2[0][0] = str3;
			arrayOfString2[0][1] = str2;
			arrayOfString2[0][2] = str4;
			arrayOfString1 = cls.mergeAry(arrayOfString1, arrayOfString2);
		}

		return arrayOfString1;
	}

	public String[][] getJtbcElement(String paramString) {
		String str1 = paramString;
		String[][] arrayOfString1 = (String[][]) null;
		String[][] arrayOfString2 = this.conf.njtbcelement;
		if (arrayOfString2 != null) {
			Integer localInteger1 = Integer.valueOf(arrayOfString2.length);
			Integer localInteger2 = Integer.valueOf(arrayOfString2[0].length);
			if ((localInteger1.intValue() >= 1)
					&& (localInteger2.intValue() == 2)) {
				for (int i = 0; i < localInteger1.intValue(); ++i) {
					String str2 = arrayOfString2[i][0];
					String str3 = arrayOfString2[i][1];
					if (!(cls.getParameter(str2, "id").equals(str1)))
						continue;
					arrayOfString1 = new String[1][2];
					arrayOfString1[0][0] = str2;
					arrayOfString1[0][1] = str3;
					break;
				}
			}
		}

		return arrayOfString1;
	}

	/**
	 * 获取rs的值
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @return
	 */
	public String getRsValue(String paramString1, String paramString2) {
		String str1 = paramString1;
		String str2 = paramString2;
		String str3 = "";
		Object[][] arrayOfObject = (Object[][]) null;
		if (str1.equals("rs"))
			arrayOfObject = this.conf.rsAry;
		else if (str1.equals("rsb"))
			arrayOfObject = this.conf.rsbAry;
		else if (str1.equals("rsc"))
			arrayOfObject = this.conf.rscAry;
		else if (str1.equals("rst"))
			arrayOfObject = this.conf.rstAry;
		if (arrayOfObject != null) {
			for (int i = 0; i < arrayOfObject.length; ++i) {
				if (!(cls.toString(arrayOfObject[i][0]).equals(str2)))
					continue;
				str3 = cls.toString(arrayOfObject[i][1]);
				break;
			}
		}

		return str3;
	}

	public String getSearchOptions(String paramString) {
		String str1 = paramString;
		String str2 = "";
		if (!(cls.isEmpty(str1).booleanValue())) {
			Object localObject = "";
			String[] arrayOfString = str1.split(Pattern.quote(","));
			String str3 = this.conf.jt.itake(
					"global.tpl_config.option_unselect", "tpl");
			for (int i = 0; i < arrayOfString.length; ++i) {
				localObject = str3;
				localObject = ((String) localObject).replace("{$explain}",
						this.conf.jt.itake("global.sel_search."
								+ encode.htmlencode(arrayOfString[i]), "sel"));
				localObject = ((String) localObject).replace("{$value}", encode
						.htmlencode(arrayOfString[i]));
				str2 = str2 + ((String) localObject);
			}
		}
		return ((String) str2);
	}

	public String getSwitchOptions(String paramString) {
		String str1 = paramString;
		String str2 = "";
		if (!(cls.isEmpty(str1).booleanValue())) {
			Object localObject = "";
			String[] arrayOfString = str1.split(Pattern.quote(","));
			String str3 = this.conf.jt.itake(
					"global.tpl_config.option_unselect", "tpl");
			for (int i = 0; i < arrayOfString.length; ++i) {
				localObject = str3;
				localObject = ((String) localObject).replace("{$explain}",
						this.conf.jt.itake("global.sel_switch."
								+ encode.htmlencode(arrayOfString[i]), "sel"));
				localObject = ((String) localObject).replace("{$value}", encode
						.htmlencode(arrayOfString[i]));
				str2 = str2 + ((String) localObject);
			}
		}
		return ((String) str2);
	}

	/**
	 * 获取数据库中最大的ID
	 * 
	 * @param paramString1
	 *            表名
	 * @param paramString2
	 *            id列
	 * @return
	 */
	public Integer getTopID(String paramString1, String paramString2) {
		Integer localInteger = Integer.valueOf(-1);
		String str1 = paramString1;
		String str2 = paramString2;
		dbc localdbc = db.newInstance(this.conf);
		String str3 = "select max(" + str2 + ") from " + str1;
		Object[] arrayOfObject = localdbc.getDataAry(str3);
		if (arrayOfObject != null)
			localInteger = cls.getNum(cls.toString(localdbc.getValue(
					(Object[][]) (Object[][]) arrayOfObject[0], 0)), Integer
					.valueOf(0));
		return localInteger;
	}

	/**
	 * 根据Chinese找到sel_lng.jtbc中的chinese：0中的0
	 * @param paramString
	 * @return
	 */
	public String getLngID(String paramString) {
		String str1 = paramString;
		String str2 = "";
		String[][] arrayOfString = this.conf.jt.itakes("global.sel_lng.all",
				"sel");
		for (int i = 0; i < arrayOfString.length; ++i) {
			String str3 = arrayOfString[i][0];
			if (str3.indexOf(":") == -1)
				continue;
			String[] arrayOfString1 = str3.split(Pattern.quote(":"));
			if ((arrayOfString1.length != 2)
					|| (!(arrayOfString1[0].equals(str1))))
				continue;
			str2 = arrayOfString1[1];
			break;
		}

		return str2;
	}
	
	/**
	 * 根据0找到sel_lng.jtbc中的chinese：0中的chinese
	 * @param paramString
	 * @return
	 */
	public String getLngText(String paramString) {
		String str1 = paramString;
		String str2 = "";
		String[][] arrayOfString = this.conf.jt.itakes("global.sel_lng.all",
				"sel");
		for (int i = 0; i < arrayOfString.length; ++i) {
			String str3 = arrayOfString[i][0];
			if (str3.indexOf(":") == -1)
				continue;
			String[] arrayOfString1 = str3.split(Pattern.quote(":"));
			if ((arrayOfString1.length != 2)
					|| (!(arrayOfString1[1].equals(str1))))
				continue;
			str2 = arrayOfString1[0];
			break;
		}

		return str2;
	}

	/**
	 * 按照指定的条件将数据库中的数据按照指定的模板格式化输出
	 * @param paramString 复合参数型字符串
	 * {$=itransfer("genre=products;tpl=1;type=new;topx=10")} = 调用产品(products)模块下的前10条最新的记录，按照预设的调用模板为1的模板样式格式化输出
	 */
	public String itransfer(String paramString) {
		String str1 = "";
		String str2 = paramString;
		String str3 = cls.getParameter(str2, "method");
		if (cls.isEmpty(str3).booleanValue())
			str1 = itransferStandard(str2);
		else if (str3.equals("sql"))
			str1 = itransferSQL(str2);
		else if (str3.equals("itakes"))
			str1 = itransferITakes(str2);
		else if (str3.equals("multigenre"))
			str1 = itransferMultiGenre(str2);

		return str1;
	}

	/**
	 * 按照指定的条件将数据库中的数据按照指定的模板格式化输出
	 * @param paramString
	 * @return
	 */
	public String itransferStandard(String paramString) {
		Object localObject1 = "";

		String str4 = paramString;
		//tpl: 模板节点名或者模板路径(如 1 表示模板取自 global.tpl_transfer.1，module.1 表示模板取自 module.1)
		String str5 = cls.getParameter(str4, "tpl");
		String str6 = cls.getParameter(str4, "tplid");
		//tplstr: 模板字符串，当设置此值时，模板直接使用这个字符串作为模板，如果设置tpl参数将不再有效
		String str7 = cls.getParameter(str4, "tplstr");
		//type: 调用数据的类型，预设的有 new(按时间排序最新)、top(按ID号排序最新)、commendatory(推荐的)、up(上面的记录)、down(下面的记录)等
		String str8 = cls.getParameter(str4, "type");
		//genre: 设定需要调用数据所属的模块名
		Object localObject2 = cls.getParameter(str4, "genre");
		//ndatabase: 单独设置调用的数据库表名
		String str9 = cls.getParameter(str4, "ndatabase");
		//nfpre: 单独设置调用的数据库表中的字段前缀
		String str10 = cls.getParameter(str4, "nfpre");
		//osql: 附加SQL语句
		String str11 = cls.getParameter(str4, "osql");
		//osqlorder: 附加SQL排序语句(设定此值后预设的排序方式失效，以此设置为准)。
		String str12 = cls.getParameter(str4, "osqlorder");
		String str13 = cls.getParameter(str4, "ocname");
		String str14 = cls.getParameter(str4, "ocmode");
		//baseurl: 基地址
		String str15 = cls.getParameter(str4, "baseurl");
		//vars: 预设变量，如果设置此值，在模板中将按照这些规则替换标签(如 vars=a=1|b=2 在模板中将可以使用 {$a}代表1 {$b}代表2)
		String str16 = cls.getParameter(str4, "vars");
		//topx: 调用数据的前X条记录
		Integer localInteger1 = cls.getNum(cls.getParameter(str4, "topx"),
				Integer.valueOf(-1));
		//cls: 模糊类别编号，调用这个类别下的所有记录（包括子类别）
		Integer localInteger2 = cls.getNum(cls.getParameter(str4, "cls"),
				Integer.valueOf(-1));
		// class: 精确类别编号，调用这个类别下的所有记录（不包括子类别）
		Integer localInteger3 = cls.getNum(cls.getParameter(str4, "class"),
				Integer.valueOf(-1));
		//lng: 语言ID，调用这个语言下的记录
		Integer localInteger4 = cls.getNum(cls.getParameter(str4, "lng"),
				Integer.valueOf(-1));
		//bid: 基准ID，按照这个ID计算上与下(与type=up或者type=down配合使用)
		Integer localInteger5 = cls.getNum(cls.getParameter(str4, "bid"),
				Integer.valueOf(-1));
		Integer localInteger6 = cls
				.getNum(this.conf.dbtype, Integer.valueOf(0));
		if (localInteger4.intValue() == -1)
			localInteger4 = cls
					.getNum(this.conf.getNLng(), Integer.valueOf(-1));
		if (localInteger1.intValue() > 0) {
			String str17 = this.conf.getNGenre();
			if ((cls.isEmpty(str15).booleanValue())
					&& (!(cls.isEmpty(localObject2).booleanValue()))
					&& (!(((String) localObject2).equals(str17)))) {
				str15 = this.conf.getActualRoute((String) localObject2);
				if (!(cls.getRight(str15, Integer.valueOf(1)).equals("/")))
					str15 = str15 + "/";
			}

			if (cls.isEmpty(localObject2).booleanValue())
				localObject2 = str17;
			String str18 = "";
			String str19 = "";
			String str20 = "";
			if (!(cls.isEmpty(str9).booleanValue())) {
				str18 = cls.getString(str9);
				str19 = cls.getString(str10);
			} else if (cls.isEmpty(str13).booleanValue()) {
				str18 = cls
						.getString(this.conf.jt.itake(
								"global." + ((String) localObject2)
										+ ":config.ndatabase", "cfg"));
				str19 = cls.getString(this.conf.jt.itake("global."
						+ ((String) localObject2) + ":config.nfpre", "cfg"));
			} else {
				str18 = cls.getString(this.conf.jt.itake("global."
						+ ((String) localObject2) + ":config.ndatabase-"
						+ str13, "cfg"));
				str19 = cls.getString(this.conf.jt.itake("global."
						+ ((String) localObject2) + ":config.nfpre-" + str13,
						"cfg"));
			}

			str20 = cls.cfnames(str19, "id");
			if (!(cls.isEmpty(str18).booleanValue())) {
				Object localObject5;
				String str21 = "";
				String str22 = "";
				if (str8.equals("new")) {
					str21 = "select * from " + str18 + " where "
							+ cls.cfnames(str19, "hidden") + "=0";
					str22 = " order by " + cls.cfnames(str19, "time") + " desc";
				} else if (str8.equals("-new")) {
					str21 = "select * from " + str18 + " where "
							+ cls.cfnames(str19, "hidden") + "=0";
					str22 = " order by " + cls.cfnames(str19, "time") + " asc";
				} else if (str8.equals("@new")) {
					str21 = "select * from " + str18 + " where 1=1";
					str22 = " order by " + cls.cfnames(str19, "time") + " desc";
				} else if (str8.equals("top")) {
					str21 = "select * from " + str18 + " where "
							+ cls.cfnames(str19, "hidden") + "=0";
					str22 = " order by " + str20 + " desc";
				} else if (str8.equals("-top")) {
					str21 = "select * from " + str18 + " where "
							+ cls.cfnames(str19, "hidden") + "=0";
					str22 = " order by " + str20 + " asc";
				} else if (str8.equals("@top")) {
					str21 = "select * from " + str18 + " where 1=1";
					str22 = " order by " + str20 + " desc";
				} else if (str8.equals("commendatory")) {
					str21 = "select * from " + str18 + " where "
							+ cls.cfnames(str19, "hidden") + "=0 and "
							+ cls.cfnames(str19, "commendatory") + "=1";
					str22 = " order by " + cls.cfnames(str19, "time") + " desc";
				} else if (str8.equals("-commendatory")) {
					str21 = "select * from " + str18 + " where "
							+ cls.cfnames(str19, "hidden") + "=0 and "
							+ cls.cfnames(str19, "commendatory") + "=1";
					str22 = " order by " + cls.cfnames(str19, "time") + " asc";
				} else if (str8.equals("@commendatory")) {
					str21 = "select * from " + str18 + " where "
							+ cls.cfnames(str19, "commendatory") + "=1";
					str22 = " order by " + cls.cfnames(str19, "time") + " desc";
				} else if (str8.equals("up")) {
					str21 = "select * from " + str18 + " where "
							+ cls.cfnames(str19, "hidden") + "=0 and " + str20
							+ ">" + localInteger5;
					str22 = " order by " + str20 + " asc";
				} else if (str8.equals("down")) {
					str21 = "select * from " + str18 + " where "
							+ cls.cfnames(str19, "hidden") + "=0 and " + str20
							+ "<" + localInteger5;
					str22 = " order by " + str20 + " desc";
				}
				Object localObject3;
				if (localInteger2.intValue() != -1) {
					localObject3 = new category(this.conf);
					String str23 = ((category) localObject3).getClassChildIds(
							(String) localObject2, localInteger4, cls
									.toString(localInteger2));
					if (!(cls.isEmpty(str23).booleanValue()))
						str21 = str21 + " and " + cls.cfnames(str19, "class")
								+ " in (" + str23 + ")";
				}
				if (localInteger3.intValue() != -1)
					str21 = str21 + " and " + cls.cfnames(str19, "class") + "="
							+ localInteger3;
				if ((localInteger4.intValue() != -1)
						&& (localInteger4.intValue() != -100))
					str21 = str21 + " and " + cls.cfnames(str19, "lng") + "="
							+ localInteger4;
				if (!(cls.isEmpty(str11).booleanValue()))
					str21 = str21 + str11;
				if (!(cls.isEmpty(str12).booleanValue()))
					str22 = str12;
				str21 = str21 + str22;
				if ((localInteger6.intValue() >= 0)
						&& (localInteger6.intValue() < 10))
					str21 = str21 + " limit 0," + localInteger1;
				if ((localInteger6.intValue() >= 10)
						&& (localInteger6.intValue() < 20))
					str21 = "select top " + localInteger1 + " *"
							+ cls.getLRStr(str21, "select *", "rightr");
				if ((localInteger6.intValue() >= 20)
						&& (localInteger6.intValue() < 30))
					str21 = str21 + " limit " + localInteger1;
				if (!(cls.isEmpty(str7).booleanValue())) {
					localObject1 = str7;
				} else if (!(cls.isEmpty(str6).booleanValue())) {
					localObject3 = getJtbcElement(str6);
					if ((localObject3 != null)
							&& (((Object[][]) localObject3)[0].length == 2))
						localObject1 = ((Object[][]) localObject3)[0][1];

				} else if (str5.indexOf(".") != -1) {
					localObject1 = this.conf.jt.itake(str5, "tpl");
				} else {
					localObject1 = this.conf.jt.itake("global.tpl_transfer."
							+ str5, "tpl");
				}

				if (!(cls.isEmpty(str16).booleanValue())) {
					localObject3 = str16.split(Pattern.quote("|"));
					for (int i = 0; i < ((Object[]) localObject3).length; ++i) {
						Object localObject4 = ((Object[]) localObject3)[i];
						if (cls.isEmpty(localObject4).booleanValue())
							continue;
						localObject5 = ((String) localObject4).split(Pattern
								.quote("="));
						if (((Object[]) localObject5).length != 2)
							continue;
						localObject1 = ((String) localObject1).replace("{$"
								+ ((String[]) localObject5)[0] + "}",
								((String[]) localObject5)[1]);
					}
				}

				localObject3 = db.newInstance(this.conf);
				Object[] arrayOfObject = ((dbc) localObject3).getDataAry(str21);
				if (arrayOfObject != null) {
					String str2 = "";
					String str1 = cls.ctemplate((String) localObject1, "{@}");
					for (int j = 0; j < arrayOfObject.length; ++j) {
						String str3 = str1;
						localObject5 = (Object[][]) (Object[][]) arrayOfObject[j];
						for (int k = 0; k < ((Object[][]) (Object[][]) localObject5).length; ++k) {
							((Object[][]) (Object[][]) localObject5)[k][0] = cls
									.getLRStr(
											(String) ((Object[][]) (Object[][]) localObject5)[k][0],
											str19, "rightr");
							str3 = str3
									.replace(
											"{$"
													+ cls
															.toString(((Object[][]) (Object[][]) localObject5)[k][0])
													+ "}",
											encode
													.htmlencode(
															cls
																	.toString(((Object[][]) (Object[][]) localObject5)[k][1]),
															"1"));
						}
						this.conf.rstAry = ((Object[][]) (Object[][]) localObject5);
						str3 = str3.replace("{$-i}", cls.toString(Integer
								.valueOf(j)));
						str3 = str3.replace("{$-genre}",
								(CharSequence) localObject2);
						str3 = str3.replace("{$-baseurl}", str15);

						for (int k = 2; k < 7; ++k) {
							int l = j % k + 1;
							str3 = str3.replace("{$-!mod" + k + "}", cls
									.toString(Integer.valueOf(l)));
							if (l != k)
								str3 = str3.replace("{$-!mod" + k + "-string}",
										"");
							else
								str3 = str3.replace("{$-!mod" + k + "-string}",
										cls.toString(str14));
						}

						str3 = this.conf.jt.creplace(str3);
						str2 = str2 + str3;
					}
					localObject1 = cls.ctemplates((String) localObject1, "{@}",
							str2);
				} else {
					localObject1 = "";
				}
				localObject1 = this.conf.jt.creplace((String) localObject1);
			}
		}
		return ((String) (String) (String) (String) localObject1);
	}

	/**
	 * 按照指定的SQL语句将数据库中的数据按照指定的模板格式化输出
	 * @param paramString
	 * @return
	 */
	public String itransferSQL(String paramString) {
		Object localObject1 = "";

		String str4 = paramString;
		//sql: 需要查询数据的SQL语句
		String str5 = cls.getParameter(str4, "sql");
		//tpl: 模板节点名或者模板路径(如 1 表示模板取自 global.tpl_transfer.1，module.1 表示模板取自 module.1)
		String str6 = cls.getParameter(str4, "tpl");
		String str7 = cls.getParameter(str4, "tplid");
		//tplstr: 模板字符串，当设置此值时，模板直接使用这个字符串作为模板，如果设置tpl参数将不再有效
		String str8 = cls.getParameter(str4, "tplstr");
		//genre: 设定需要调用数据所属的模块名
		Object localObject2 = cls.getParameter(str4, "genre");
		String str9 = cls.getParameter(str4, "ocmode");
		// baseurl: 基地址
		String str10 = cls.getParameter(str4, "baseurl");
		//vars: 预设变量，如果设置此值，在模板中将按照这些规则替换标签(如 vars=a=1|b=2 在模板中将可以使用 {$a}代表1 {$b}代表2)
		String str11 = cls.getParameter(str4, "vars");
		if (!(cls.isEmpty(str5).booleanValue())) {
			String str12 = cls.getLRStr(str5, " ", "left").toLowerCase();
			if (str12.equals("select")) {
				Object localObject5;
				String str13 = str5;
				String str14 = this.conf.getNGenre();
				if ((cls.isEmpty(str10).booleanValue())
						&& (!(cls.isEmpty(localObject2).booleanValue()))
						&& (!(((String) localObject2).equals(str14)))) {
					str10 = this.conf.getActualRoute((String) localObject2);
					if (!(cls.getRight(str10, Integer.valueOf(1)).equals("/")))
						str10 = str10 + "/";
				}

				if (cls.isEmpty(localObject2).booleanValue())
					localObject2 = str14;

				if (!(cls.isEmpty(str8).booleanValue())) {
					localObject1 = str8;
				} else if (!(cls.isEmpty(str7).booleanValue())) {
					String[][] localObject3 = getJtbcElement(str7);
					if ((localObject3 != null) && (localObject3[0].length == 2))
						localObject1 = localObject3[0][1];

				} else if (str6.indexOf(".") != -1) {
					localObject1 = this.conf.jt.itake(str6, "tpl");
				} else {
					localObject1 = this.conf.jt.itake("global.tpl_transfer."
							+ str6, "tpl");
				}

				if (!(cls.isEmpty(str11).booleanValue())) {
					Object[] localObject3 = str11.split(Pattern.quote("|"));
					for (int i = 0; i < localObject3.length; ++i) {
						Object localObject4 = localObject3[i];
						if (cls.isEmpty(localObject4).booleanValue())
							continue;
						localObject5 = ((String) localObject4).split(Pattern
								.quote("="));
						if (((String[]) localObject5).length != 2)
							continue;
						localObject1 = ((String) localObject1).replace("{$"
								+ ((String[]) localObject5)[0] + "}",
								((String[]) localObject5)[1]);
					}

				}

				Object localObject3 = db.newInstance(this.conf);
				Object[] arrayOfObject = ((dbc) localObject3).getDataAry(str13);
				if (arrayOfObject != null) {
					String str2 = "";
					String str1 = cls.ctemplate((String) localObject1, "{@}");
					for (int j = 0; j < arrayOfObject.length; ++j) {
						String str3 = str1;
						localObject5 = (Object[][]) (Object[][]) arrayOfObject[j];
						for (int k = 0; k < ((Object[][]) (Object[][]) localObject5).length; ++k) {
							str3 = str3
									.replace(
											"{$"
													+ cls
															.toString(((Object[][]) (Object[][]) localObject5)[k][0])
													+ "}",
											encode
													.htmlencode(
															cls
																	.toString(((Object[][]) (Object[][]) localObject5)[k][1]),
															"1"));
						}
						this.conf.rstAry = ((Object[][]) (Object[][]) localObject5);
						str3 = str3.replace("{$-i}", cls.toString(Integer
								.valueOf(j)));
						str3 = str3.replace("{$-genre}",
								(CharSequence) localObject2);
						str3 = str3.replace("{$-baseurl}", str10);

						for (int k = 2; k < 7; ++k) {
							int l = j % k + 1;
							str3 = str3.replace("{$-!mod" + k + "}", cls
									.toString(Integer.valueOf(l)));
							if (l != k)
								str3 = str3.replace("{$-!mod" + k + "-string}",
										"");
							else
								str3 = str3.replace("{$-!mod" + k + "-string}",
										cls.toString(str9));
						}

						str3 = this.conf.jt.creplace(str3);
						str2 = str2 + str3;
					}
					localObject1 = cls.ctemplates((String) localObject1, "{@}",
							str2);
				} else {
					localObject1 = "";
				}
				localObject1 = this.conf.jt.creplace((String) localObject1);
			}
		}
		return ((String) (String) (String) (String) localObject1);
	}

	/**
	 * 按照指定的参数将某个JTBC文件中的数据按照指定的模板格式化输出
	 * @param paramString
	 * @return
	 */
	public String itransferITakes(String paramString) {
		Object localObject1 = "";

		String str4 = paramString;
		//xinfostr: 文件代号
		String str5 = cls.getParameter(str4, "xinfostr");
		//xinfotype: 文件类型(cfg/lng/tpl)。
		String str6 = cls.getParameter(str4, "xinfotype");
		//xinfolimit: 限制调用的名称范围以“,”间隔
		String str7 = cls.getParameter(str4, "xinfolimit");
		//tpl: 模板节点名或者模板路径(如 1 表示模板取自 global.tpl_transfer.1，module.1 表示模板取自 module.1)。
		String str8 = cls.getParameter(str4, "tpl");
		String str9 = cls.getParameter(str4, "tplid");
		//tplstr: 模板字符串，当设置此值时，模板直接使用这个字符串作为模板，如果设置tpl参数将不再有效
		String str10 = cls.getParameter(str4, "tplstr");
		//genre: 设定需要调用数据所属的模块名
		Object localObject2 = cls.getParameter(str4, "genre");
		String str11 = cls.getParameter(str4, "ocmode");
		//baseurl: 基地址
		String str12 = cls.getParameter(str4, "baseurl");
		//vars: 预设变量，如果设置此值，在模板中将按照这些规则替换标签(如 vars=a=1|b=2 在模板中将可以使用 {$a}代表1 {$b}代表2)
		String str13 = cls.getParameter(str4, "vars");
		if (!(cls.isEmpty(str5).booleanValue())) {
			Object localObject3;
			Object localObject5;
			String str14 = this.conf.getNGenre();
			if ((cls.isEmpty(str12).booleanValue())
					&& (!(cls.isEmpty(localObject2).booleanValue()))
					&& (!(((String) localObject2).equals(str14)))) {
				str12 = this.conf.getActualRoute((String) localObject2);
				if (!(cls.getRight(str12, Integer.valueOf(1)).equals("/")))
					str12 = str12 + "/";
			}

			if (cls.isEmpty(localObject2).booleanValue())
				localObject2 = str14;

			if (!(cls.isEmpty(str10).booleanValue())) {
				localObject1 = str10;
			} else if (!(cls.isEmpty(str9).booleanValue())) {
				localObject3 = getJtbcElement(str9);
				if ((localObject3 != null)
						&& (((String[][]) localObject3)[0].length == 2))
					localObject1 = ((String[][]) localObject3)[0][1];

			} else if (str8.indexOf(".") != -1) {
				localObject1 = this.conf.jt.itake(str8, "tpl");
			} else {
				localObject1 = this.conf.jt.itake(
						"global.tpl_transfer." + str8, "tpl");
			}

			if (!(cls.isEmpty(str13).booleanValue())) {
				localObject3 = str13.split(Pattern.quote("|"));
				for (int j = 0; j < ((Object[]) localObject3).length; ++j) {
					Object localObject4 = ((Object[]) localObject3)[j];
					if (cls.isEmpty(localObject4).booleanValue())
						continue;
					localObject5 = ((String) localObject4).split(Pattern
							.quote("="));
					if (((String[]) localObject5).length != 2)
						continue;
					localObject1 = ((String) localObject1).replace("{$"
							+ ((String[]) localObject5)[0] + "}",
							((String[]) localObject5)[1]);
				}

			}

			int i = 0;
			if (cls.isEmpty(str6).booleanValue())
				str6 = "lng";
			String[][] arrayOfString = this.conf.jt.itakes(str5, str6);
			if (arrayOfString != null) {
				String str2 = "";
				String str1 = cls.ctemplate((String) localObject1, "{@}");
				for (int k = 0; k < arrayOfString.length; ++k) {
					localObject5 = arrayOfString[k][0];
					String str15 = arrayOfString[k][1];
					if ((!(cls.isEmpty(str7).booleanValue()))
							&& (!(cls.cinstr(str7, (String) localObject5, ",")
									.booleanValue())))
						continue;
					String str3 = str1;
					str3 = str3.replace("{$namestring}",
							(CharSequence) localObject5);
					str3 = str3.replace("{$valuestring}", str15);
					str3 = str3.replace("{$name}", encode
							.htmlencode((String) localObject5));
					str3 = str3.replace("{$value}", encode.htmlencode(str15));
					str3 = str3.replace("{$-i}", cls.toString(Integer
							.valueOf(i)));
					str3 = str3.replace("{$-genre}",
							(CharSequence) localObject2);
					str3 = str3.replace("{$-baseurl}", str12);

					for (int l = 2; l < 7; ++l) {
						int i1 = i % l + 1;
						str3 = str3.replace("{$-!mod" + l + "}", cls
								.toString(Integer.valueOf(i1)));
						if (i1 != l)
							str3 = str3.replace("{$-!mod" + l + "-string}", "");
						else
							str3 = str3.replace("{$-!mod" + l + "-string}", cls
									.toString(str11));
					}

					str2 = str2 + str3;
					++i;
				}

				localObject1 = cls.ctemplates((String) localObject1, "{@}",
						str2);
			} else {
				localObject1 = "";
			}
			localObject1 = this.conf.jt.creplace((String) localObject1);
		}
		return ((String) (String) (String) (String) localObject1);
	}

	/**
	 * 按照指定的多个模块的参数将其所有的数据按照指定的模板格式化输出
	 * @param paramString
	 * @return
	 */
	public String itransferMultiGenre(String paramString) {
		Object localObject1 = "";

		String str4 = paramString;
		//tpl: 模板节点名或者模板路径(如 1 表示模板取自 global.tpl_transfer.1，module.1 表示模板取自 module.1)
		String str5 = cls.getParameter(str4, "tpl");
		String str6 = cls.getParameter(str4, "tplid");
		//tplstr: 模板字符串，当设置此值时，模板直接使用这个字符串作为模板，如果设置tpl参数将不再有效
		String str7 = cls.getParameter(str4, "tplstr");
		//type: 调用数据的类型，预设的有 new(按时间排序最新)、commendatory(推荐的)等
		String str8 = cls.getParameter(str4, "type");
		//genre: 设定需要调用数据所属的模块名
		String str9 = cls.getParameter(str4, "genre");
		// field: 字段列表(id,time默认选择，其他字段需枚举)。
		String str10 = cls.getParameter(str4, "field");
		// osql: 附加SQL语句(此处附加的SQL语句字段前缀为“un_”)。
		String str11 = cls.getParameter(str4, "osql");
		//osqlorder: 附加SQL排序语句(此处附加的SQL语句字段前缀为“un_”，设定此值后预设的排序方式失效，以此设置为准)。
		String str12 = cls.getParameter(str4, "osqlorder");
		String str13 = cls.getParameter(str4, "ocmode");
		String str14 = cls.getParameter(str4, "baseurl");
		// vars: 预设变量，如果设置此值，在模板中将按照这些规则替换标签(如 vars=a=1|b=2 在模板中将可以使用 {$a}代表1 {$b}代表2)
		String str15 = cls.getParameter(str4, "vars");
		Integer localInteger1 = cls.getNum(cls.getParameter(str4, "topx"),
				Integer.valueOf(-1));
		//lng: 语言ID，调用这个语言下的记录
		Integer localInteger2 = cls.getNum(cls.getParameter(str4, "lng"),
				Integer.valueOf(-1));
		Integer localInteger3 = cls
				.getNum(this.conf.dbtype, Integer.valueOf(0));
		if (localInteger2.intValue() == -1)
			localInteger2 = cls
					.getNum(this.conf.getNLng(), Integer.valueOf(-1));
		if (localInteger1.intValue() > 0) {
			String str16 = this.conf.getNGenre();
			if (!(cls.isEmpty(str9).booleanValue())) {
				String str20;
				Object localObject3;
				String str17 = "";
				String str18 = "";
				str17 = str17 + "select * from (";
				String[] arrayOfString1 = str9.split(Pattern.quote("&"));
				for (int i = 0; i < arrayOfString1.length; ++i) {
					String str19 = arrayOfString1[i];
					str20 = cls.getString(this.conf.jt.itake("global." + str19
							+ ":config.ndatabase", "cfg"));
					localObject3 = cls.getString(this.conf.jt.itake("global."
							+ str19 + ":config.nfpre", "cfg"));
					if (cls.isEmpty(str20).booleanValue())
						continue;
					str17 = str17 + "select "
							+ cls.cfnames((String) localObject3, "id")
							+ " as un_id,";
					String[] arrayOfString2 = str10.split(Pattern.quote("&"));
					for (int i1 = 0; i1 < arrayOfString2.length; ++i1) {
						String str23 = arrayOfString2[i1];
						str17 = str17
								+ cls.cfnames((String) localObject3, str23)
								+ " as un_" + str23 + ",";
					}
					str17 = str17 + cls.cfnames((String) localObject3, "time")
							+ " as un_time, '" + str19 + "' as un_genre from "
							+ str20;
					if (str8.equals("new")) {
						str17 = str17 + " where "
								+ cls.cfnames((String) localObject3, "hidden")
								+ "=0";
						str18 = " order by un_time desc";
					} else if (str8.equals("-new")) {
						str17 = str17 + " where "
								+ cls.cfnames((String) localObject3, "hidden")
								+ "=0";
						str18 = " order by un_time desc";
					} else if (str8.equals("@new")) {
						str17 = str17 + " where 1=1";
						str18 = " order by un_time desc";
					} else if (str8.equals("commendatory")) {
						str17 = str17
								+ " where "
								+ cls.cfnames((String) localObject3, "hidden")
								+ "=0 and "
								+ cls.cfnames((String) localObject3,
										"commendatory") + "=1";
						str18 = " order by un_time desc";
					} else if (str8.equals("-commendatory")) {
						str17 = str17
								+ " where "
								+ cls.cfnames((String) localObject3, "hidden")
								+ "=0 and "
								+ cls.cfnames((String) localObject3,
										"commendatory") + "=1";
						str18 = " order by un_time desc";
					} else if (str8.equals("@commendatory")) {
						str17 = str17
								+ " where "
								+ cls.cfnames((String) localObject3,
										"commendatory") + "=1";
						str18 = " order by un_time desc";
					} else {
						str17 = str17 + " where "
								+ cls.cfnames((String) localObject3, "hidden")
								+ "=0";
						str18 = " order by un_time desc";
					}
					if ((localInteger2.intValue() != -1)
							&& (localInteger2.intValue() != -100))
						str17 = str17 + " and "
								+ cls.cfnames((String) localObject3, "lng")
								+ "=" + localInteger2;
					str17 = str17 + " union all ";
				}

				if (str17.indexOf(" union all ") != -1)
					str17 = cls.getLRStr(str17, " union all ", "leftr");
				str17 = str17 + ") t1 where 1=1";
				if (!(cls.isEmpty(str11).booleanValue()))
					str17 = str17 + str11;
				if (!(cls.isEmpty(str12).booleanValue()))
					str18 = str12;
				str17 = str17 + str18;
				if ((localInteger3.intValue() >= 0)
						&& (localInteger3.intValue() < 10))
					str17 = str17 + " limit 0," + localInteger1;
				if ((localInteger3.intValue() >= 10)
						&& (localInteger3.intValue() < 20))
					str17 = "select top " + localInteger1 + " *"
							+ cls.getLRStr(str17, "select *", "rightr");
				if ((localInteger3.intValue() >= 20)
						&& (localInteger3.intValue() < 30))
					str17 = str17 + " limit " + localInteger1;

				if (!(cls.isEmpty(str7).booleanValue())) {
					localObject1 = str7;
				} else if (!(cls.isEmpty(str6).booleanValue())) {
					String[][] localObject2 = getJtbcElement(str6);
					if ((localObject2 != null) && (localObject2[0].length == 2))
						localObject1 = localObject2[0][1];

				} else if (str5.indexOf(".") != -1) {
					localObject1 = this.conf.jt.itake(str5, "tpl");
				} else {
					localObject1 = this.conf.jt.itake("global.tpl_transfer."
							+ str5, "tpl");
				}

				if (!(cls.isEmpty(str15).booleanValue())) {
					String[] localObject2 = str15.split(Pattern.quote("|"));
					for (int j = 0; j < localObject2.length; ++j) {
						str20 = localObject2[j];
						if (cls.isEmpty(str20).booleanValue())
							continue;
						localObject3 = str20.split(Pattern.quote("="));
						if (((String[]) localObject3).length != 2)
							continue;
						localObject1 = ((String) localObject1).replace("{$"
								+ ((String[]) localObject3)[0] + "}",
								((String[]) localObject3)[1]);
					}

				}

				Object localObject2 = db.newInstance(this.conf);
				Object[] arrayOfObject = ((dbc) localObject2).getDataAry(str17);
				if (arrayOfObject != null) {
					String str2 = "";
					String str1 = cls.ctemplate((String) localObject1, "{@}");
					for (int k = 0; k < arrayOfObject.length; ++k) {
						String str3 = str1;
						localObject3 = (Object[][]) (Object[][]) arrayOfObject[k];
						for (int l = 0; l < ((Object[][]) (Object[][]) localObject3).length; ++l) {
							((Object[][]) (Object[][]) localObject3)[l][0] = cls
									.getLRStr(
											(String) ((Object[][]) (Object[][]) localObject3)[l][0],
											"un_", "rightr");
							str3 = str3
									.replace(
											"{$"
													+ cls
															.toString(((Object[][]) (Object[][]) localObject3)[l][0])
													+ "}",
											encode
													.htmlencode(
															cls
																	.toString(((Object[][]) (Object[][]) localObject3)[l][1]),
															"1"));
						}
						this.conf.rstAry = ((Object[][]) (Object[][]) localObject3);
						String str21 = cls
								.toString(((dbc) localObject2)
										.getValue(
												((Object[][]) (Object[][]) localObject3),
												"genre"));
						String str22 = "";
						if (!(cls.isEmpty(str14).booleanValue())) {
							str22 = str14;
						} else if (!(str21.equals(str16))) {
							str22 = this.conf.getActualRoute(str21);
							if (!(cls.getRight(str22, Integer.valueOf(1))
									.equals("/")))
								str22 = str22 + "/";
						}

						str3 = str3.replace("{$-i}", cls.toString(Integer
								.valueOf(k)));
						str3 = str3.replace("{$-genre}", encode
								.htmlencode(str21));
						str3 = str3.replace("{$-baseurl}", str22);

						for (int i2 = 2; i2 < 7; ++i2) {
							int i3 = k % i2 + 1;
							str3 = str3.replace("{$-!mod" + i2 + "}", cls
									.toString(Integer.valueOf(i3)));
							if (i3 != i2)
								str3 = str3.replace(
										"{$-!mod" + i2 + "-string}", "");
							else
								str3 = str3.replace(
										"{$-!mod" + i2 + "-string}", cls
												.toString(str13));
						}

						str3 = this.conf.jt.creplace(str3);
						str2 = str2 + str3;
					}
					localObject1 = cls.ctemplates((String) localObject1, "{@}",
							str2);
				} else {
					localObject1 = "";
				}
				localObject1 = this.conf.jt.creplace((String) localObject1);
			}
		}
		return ((String) (String) (String) localObject1);
	}

	
	/**
	 * 按照指定的条件将分类数据按照指定的模板格式化输出
	 * @param paramString 复合参数型字符串(格式: "tpl=1;genre=articles")
	 * {$=isort("genre=articles;tpl=s1")} = 调用文章(articles)第一级别的分类，按照预设的调用模板为s1的模板样式格式化输出
	 * @return
	 */
	public String isort(String paramString) {
		String str1 = "";

		String str5 = paramString;
		String str6 = cls.getParameter(str5, "tpl");
		Object localObject1 = cls.getParameter(str5, "genre");
		String str7 = cls.getParameter(str5, "lng");
		String str8 = cls.getParameter(str5, "fid");
		String str9 = cls.getParameter(str5, "vars");
		String str10 = cls.getParameter(str5, "valids");
		String str11 = "";
		String str12 = this.conf.getNGenre();
		if (cls.isEmpty(localObject1).booleanValue())
			localObject1 = str12;
		if (cls.isEmpty(str7).booleanValue())
			str7 = this.conf.getNLng();
		if (str10.equals("-1"))
			str10 = "";
		if (!(((String) localObject1).equals(str12))) {
			str11 = this.conf.getActualRoute((String) localObject1);
			if (cls.getRight(str11, Integer.valueOf(1)) != "/")
				str11 = str11 + "/";
		}
		if (str6.indexOf(".") != -1)
			str1 = this.conf.jt.itake(str6, "tpl");
		else
			str1 = this.conf.jt.itake("global.tpl_transfer." + str6, "tpl");
		if (!(cls.isEmpty(str9).booleanValue())) {
			Object[] localObject2 = str9.split(Pattern.quote("|"));
			for (int i = 0; i < localObject2.length; ++i) {
				Object localObject3 = localObject2[i];
				if (cls.isEmpty(localObject3).booleanValue())
					continue;
				String[] arrayOfString1 = ((String) localObject3).split(Pattern
						.quote("="));
				if (arrayOfString1.length != 2)
					continue;
				str1 = str1.replace("{$" + arrayOfString1[0] + "}",
						arrayOfString1[1]);
			}
		}

		String str3 = "";
		String str2 = cls.ctemplate(str1, "{@}");
		Object localObject2 = new category(this.conf);
		String[][] arrayOfString = ((category) localObject2).getCatAry(
				(String) localObject1, cls.getNum(str7, Integer.valueOf(0)));
		if (arrayOfString != null) {
			for (int j = 0; j < arrayOfString.length; ++j) {
				if ((!(cls.getNum(arrayOfString[j][2], Integer.valueOf(0))
						.equals(cls.getNum(str8, Integer.valueOf(0)))))
						|| ((!(cls.isEmpty(str10).booleanValue())) && (!(cls
								.cinstr(str10, cls.toString(cls
										.getNum(arrayOfString[j][0], Integer
												.valueOf(0))), ",")
								.booleanValue()))))
					continue;
				String str4 = str2;
				str4 = str4.replace("{$topic}", encode
						.htmlencode(arrayOfString[j][1]));
				str4 = str4.replace("{$id}", encode
						.htmlencode(arrayOfString[j][0]));
				str4 = str4.replace("{$-genre}", (CharSequence) localObject1);
				str4 = str4.replace("{$-baseurl}", str11);
				str3 = str3 + str4;
			}

		}

		str1 = cls.ctemplates(str1, "{@}", str3);
		str1 = this.conf.jt.creplace(str1);
		return ((String) (String) str1);
	}

	 /**
	 * 按照指定的条件输出导航。
	 * 示例：{$=inavigation("class={$-class}")} = 常用的有分类的默认导航
	 * @param argStrings, 复合参数型字符串(格式: "genre=articles;class=1" ...)
	 * ⒈ genre: 设定需要调用数据所属的模块名(设置成&hidden时隐藏)
	 * ⒉ lng: 语言ID，调用这个语言下的记录
	 * ⒊ class: 类别的ID
	 * ⒋ genrelink: 模块链接(设置成&hidden时隐藏链接)
	 * @return
	 */
	public String inavigation(String paramString) {
		Object localObject1 = "";
		String str1 = paramString;
		Object localObject2 = cls.getParameter(str1, "genre");
		String str2 = cls.getParameter(str1, "lng");
		String str3 = cls.getParameter(str1, "class");
		String str4 = cls.getParameter(str1, "genrelink");
		String str5 = "";
		String str6 = this.conf.getNGenre();
		if (cls.isEmpty(localObject2).booleanValue())
			localObject2 = str6;
		if (cls.isEmpty(str2).booleanValue())
			str2 = this.conf.getNLng();
		if (!(((String) localObject2).equals(str6))) {
			str5 = this.conf.getActualRoute((String) localObject2);
			if (!(cls.getRight(str5, Integer.valueOf(1)).equals("/")))
				str5 = str5 + "/";
		}
		String str7 = this.conf.jt
				.itake("global.tpl_config.a_href_self", "tpl");
		String str8 = this.conf.jt.itake("global.default.channel_title", "lng");
		localObject1 = str7;
		localObject1 = ((String) localObject1).replace("{$explain}", str8);
		localObject1 = ((String) localObject1).replace("{$value}", this.conf
				.getActualRoute("./"));
		if (localObject2 != "&hidden") {
			String str9 = this.conf.jt
					.itake("global." + ((String) localObject2)
							+ ":default.channel_title", "lng");
			if (!(str4.equals("&hidden"))) {
				localObject1 = ((String) localObject1) + this.conf.navSpStr
						+ str7;
				if (cls.isEmpty(str4).booleanValue())
					str4 = this.conf.getActualRoute((String) localObject2);
				localObject1 = ((String) localObject1).replace("{$explain}",
						str9);
				localObject1 = ((String) localObject1)
						.replace("{$value}", str4);
			} else {
				localObject1 = ((String) localObject1) + this.conf.navSpStr
						+ str9;
			}
		}
		String str9 = "<!--fixed-->{@}" + this.conf.navSpStr + str7
				+ "{@}<!--fixed-->";
		str9 = str9.replace("{$explain}", "{$topic}");
		str9 = str9.replace("{$value}", curl(str5, iurl("genre="
				+ ((String) localObject2) + ";type=list;key={$id}")));
		if (!(cls.isEmpty(str3).booleanValue())) {
			category localcategory = new category(this.conf);
			localObject1 = ((String) localObject1)
					+ localcategory.getFaCatHtml(str9, (String) localObject2,
							cls.getNum(str2, Integer.valueOf(0)), cls.getNum(
									str3, Integer.valueOf(0)));
		}
		return ((String) (String) localObject1);
	}

	/**
	 * 按照指定的条件与配置输出地址
	 * @param argStrings, 复合参数型字符串(格式: "genre=articles;type=detail;key=1;time=2008-10-10 10:10:10" ...)
	 * ⒈ type: 类型(list,detail,page,ctpage)
	 * ⒉ genre: 设定需要调用数据所属的模块名
	 * ⒊ key: 关键字，一般为ID号，分类号等
	 * ⒋ time: 时间，用于生成地址
	 * ⒌ page: 分页数
	 * ⒍ ctpage: 内容分页数
	 * @return
	 */
	public String iurl(String argStrings) {
		String str1 = "";
		String str2 = "";
		String str3 = argStrings;
		String str4 = cls.getParameter(str3, "type");
		String str5 = cls.getParameter(str3, "genre");
		String str6 = cls.getParameter(str3, "key");
		String str7 = cls.getParameter(str3, "time");
		String str8 = cls.getParameter(str3, "page");
		String str9 = cls.getParameter(str3, "ctpage");
		str2 = cls.getSafeString(str6);
		if (cls.isEmpty(str2).booleanValue())
			str2 = "0";
		str8 = cls.getSafeString(str8);
		if (cls.isEmpty(str8).booleanValue())
			str8 = "0";
		str9 = cls.getSafeString(str9);
		if (cls.isEmpty(str9).booleanValue())
			str9 = "0";
		if (cls.isEmpty(str5).booleanValue())
			str5 = this.conf.getNGenre();
		Integer localInteger = cls.getNum(this.conf.jt.itake("global." + str5
				+ ":config.nurltype", "cfg"), Integer.valueOf(0));
		String str10 = this.conf.jt.itake("global." + str5
				+ ":config.ncreatefolder", "cfg");
		String str11 = this.conf.jt.itake("global." + str5
				+ ":config.ncreatefiletype", "cfg");
		label1480: switch (localInteger.intValue()) {
		case 0:
			if (str4.equals("list")) {
				str1 = "?type=list&amp;class=" + str2;
				break label1480;
			}
			if (str4.equals("detail")) {
				str1 = "?type=detail&amp;id=" + str2;
				break label1480;
			}
			if (str4.equals("page")) {
				str1 = "?"
						+ encode.htmlencode(cls.reparameter(
								this.conf.getNURS(), "page", str8));
				break label1480;
			}
			if (!(str4.equals("ctpage")))
				break label1480;
			str1 = "?"
					+ encode.htmlencode(cls.reparameter(this.conf.getNURS(),
							"ctpage", str9));
			break;
		case 1:
			if (str4.equals("list")) {
				str1 = str10 + "/list/" + str2 + "/1" + str11;
				break label1480;
			}
			if (str4.equals("detail")) {
				str1 = str10 + "/detail/"
						+ cls.formatDate(str7, Integer.valueOf(5)) + "/" + str2
						+ str11;
				break label1480;
			}
			if (str4.equals("page")) {
				str1 = str10 + "/list/" + str2 + "/" + str8 + str11;
				break label1480;
			}
			if (!(str4.equals("ctpage")))
				break label1480;
			str1 = str10 + "/detail/"
					+ cls.formatDate(str7, Integer.valueOf(5)) + "/" + str2
					+ "_" + str9 + str11;
			break;
		case 2:
			if (str4.equals("list")) {
				str1 = "list-" + str2 + "-1.jsp";
				break label1480;
			}
			if (str4.equals("detail")) {
				str1 = "detail-" + str2 + ".jsp";
				break label1480;
			}
			if (str4.equals("page")) {
				str1 = "list-" + str2 + "-" + str8 + ".jsp";
				break label1480;
			}
			if (!(str4.equals("ctpage")))
				break label1480;
			str1 = "detail-" + str2 + "-" + str9 + ".jsp";
			break;
		case 3:
			if (str4.equals("list")) {
				str1 = "list-" + str2 + "-1.htm";
				break label1480;
			}
			if (str4.equals("detail")) {
				str1 = "detail-" + str2 + ".htm";
				break label1480;
			}
			if (str4.equals("page")) {
				str1 = "list-" + str2 + "-" + str8 + ".htm";
				break label1480;
			}
			if (!(str4.equals("ctpage")))
				break label1480;
			str1 = "detail-" + str2 + "-" + str9 + ".htm";
			break;
		case 4:
			if (str4.equals("list")) {
				str1 = "list-" + str2 + "-1.xhtml";
				break label1480;
			}
			if (str4.equals("detail")) {
				str1 = "detail-" + str2 + ".xhtml";
				break label1480;
			}
			if (str4.equals("page")) {
				str1 = "list-" + str2 + "-" + str8 + ".xhtml";
				break label1480;
			}
			if (!(str4.equals("ctpage")))
				break label1480;
			str1 = "detail-" + str2 + "-" + str9 + ".xhtml";
			break;
		case 5:
			if (str4.equals("list")) {
				str1 = "list-" + str2 + "-1.html";
				break label1480;
			}
			if (str4.equals("detail")) {
				str1 = "detail-" + str2 + ".html";
				break label1480;
			}
			if (str4.equals("page")) {
				str1 = "list-" + str2 + "-" + str8 + ".html";
				break label1480;
			}
			if (!(str4.equals("ctpage")))
				break label1480;
			str1 = "detail-" + str2 + "-" + str9 + ".html";
		}

		label1480: return str1;
	}

	/**
	 * 按照设置的名字与初始值等参数输出编辑器窗口的代码
	 * @param paramString1
	 * @param paramString2
	 * @return
	 */
	public String loadEditor(String paramString1, String paramString2) {
		String str1 = "";
		String str2 = paramString1;
		String str3 = paramString2;
		str1 = loadEditor(str2, str3, "1");
		return str1;
	}

	/**
	 * 按照设置的名字与初始值等参数输出编辑器窗口的代码
	 * @param paramString1
	 * @param paramString2
	 * @param paramString3
	 * @return
	 */
	public String loadEditor(String paramString1, String paramString2,
			String paramString3) {
		String str1 = "";
		String str2 = paramString1;
		String str3 = paramString2;
		String str4 = paramString3;
		str1 = loadEditor(str2, str3, str4, "300");
		return str1;
	}

	/**
	 * 示例:{$=loadEditor("content", "")} = 输出一个名称为 content 的编辑器窗口，初始值为空
	 * @param argName, 名称
	 * @param argValue, 初始值
	 * @param argStyle(可选), 样式, 默认为1
	 * @param argHeight(可选), 高度, 默认为300px
	 * @return
	 */
	public String loadEditor(String argName, String argValue, String argStyle,
			String argHeight) {
		String str1 = "";
		String str2 = argName;
		String str3 = argValue;
		String str4 = argStyle;
		String str5 = argHeight;
		if (cls.getNum(str4, Integer.valueOf(-1)).intValue() != -1)
			str4 = "Style" + str4;
		if (str2.substring(0, 1).equals("~"))
			str2 = cls.getLRStr(str2, "~", "rightr");
		else
			str1 = str1
					+ this.conf.jt.itake("global.tpl_common.editor_script",
							"tpl");
		str1 = str1
				+ this.conf.jt.itake("global.tpl_common.editor_content", "tpl");
		str1 = str1.replace("{$name}", encode.htmlencode(str2));
		str1 = str1.replace("{$value}", encode.htmlencode(str3));
		str1 = str1.replace("{$-style}", encode.htmlencode(str4));
		str1 = str1.replace("{$-height}", encode.htmlencode(str5));
		str1 = this.conf.jt.creplace(str1);
		return str1;
	}

	/**
	 * 按照设置的参数输出分页代码
	 * @param paramString1
	 * @param paramString2
	 * @param paramString3
	 * @param paramString4
	 * @return
	 */
	public String pagi(String paramString1, String paramString2,
			String paramString3, String paramString4) {
		String str1 = "";
		String str2 = paramString1;
		String str3 = paramString2;
		String str4 = paramString3;
		String str5 = paramString4;
		str1 = pagi(str2, str3, str4, str5, "");
		return str1;
	}

	/**
	 * 示例：{$=pagi("{$pagi.pagenum}", "{$pagi.pagenums}", iurl("type=page;key={$-class};page=[~page]"), "cutepage")} = 模块列表中常见的输出分页的代码
	 * @param argNum1, 当前页数
	 * @param argNum2, 总的分页数
	 * @param argBaseLink, 基地址
	 * @param argTid, 标识类型
	 * @param argTpl(可选), 模板的节点名, 默认为pagi-1
	 * @return
	 */
	public String pagi(String paramString1, String paramString2,
			String paramString3, String paramString4, String paramString5) {
		String str1 = "";

		Integer localInteger1 = Integer.valueOf(10);
		Integer localInteger2 = localInteger1;
		Object localObject = cls.getNum(paramString1, Integer.valueOf(0));
		Integer localInteger3 = cls.getNum(paramString2, Integer.valueOf(0));
		String str5 = cls.getString(paramString3);
		String str6 = cls.getString(paramString4);
		String str7 = cls.getString(paramString5);
		if (cls.isEmpty(str7).booleanValue())
			str7 = "pagi-1";
		Integer localInteger4 = Integer.valueOf(0);
		if (str6.equals("ct-cutepage"))
			localInteger4 = Integer.valueOf(1);
		if (localInteger3.intValue() > localInteger4.intValue()) {
			str1 = this.conf.jt.itake("global.tpl_common." + str7, "tpl");
			String str3 = "";
			String str2 = cls.ctemplate(str1, "{@}");
			if (((Integer) localObject).intValue() < 1)
				localObject = Integer.valueOf(1);
			if (((Integer) localObject).intValue() > localInteger3.intValue())
				localObject = localInteger3;
			Integer localInteger5 = cls.getNum(cls.getLRStr(cls.toString(Double
					.valueOf(((Integer) localObject).intValue()
							- Math.floor(localInteger1.intValue() / 2))), ".",
					"left"), Integer.valueOf(0));
			if (localInteger5.intValue() < 1)
				localInteger5 = Integer.valueOf(1);
			Integer localInteger6 = Integer.valueOf(localInteger5.intValue()
					+ localInteger2.intValue() - 1);
			if (localInteger6.intValue() > localInteger3.intValue())
				localInteger6 = localInteger3;
			if (localInteger5.intValue() <= localInteger6.intValue()) {
				if (localInteger6.intValue() - localInteger5.intValue() < localInteger2
						.intValue() - 1) {
					localInteger5 = Integer.valueOf(localInteger5.intValue()
							- (localInteger2.intValue() - 1 - (localInteger6
									.intValue() - localInteger5.intValue())));
					if (localInteger5.intValue() < 1)
						localInteger5 = Integer.valueOf(1);
				}
				for (int i = localInteger5.intValue(); i <= localInteger6
						.intValue(); ++i) {
					String str4 = str2;
					str4 = str4.replace("{$-num}", cls.toString(Integer
							.valueOf(i)));
					str4 = str4.replace("{$-link}", str5.replace("[~page]", cls
							.toString(Integer.valueOf(i))));
					if (i != ((Integer) localObject).intValue())
						str4 = str4.replace("{$-class}", "");
					else
						str4 = str4.replace("{$-class}", "selected");
					str3 = str3 + str4;
				}
			}
			str1 = cls.ctemplates(str1, "{@}", str3);
			str1 = str1.replace("{$-page1}", cls.toString(localObject));
			str1 = str1.replace("{$-page2}", cls.toString(localInteger3));
			str1 = str1.replace("{$-firstpagelink}", str5.replace("[~page]",
					"1"));
			str1 = str1.replace("{$-lastpagelink}", str5.replace("[~page]", cls
					.toString(localInteger3)));
			str1 = str1.replace("{$-tid}", encode.htmlencode(str6));
			str1 = str1.replace("{$-value1}", cls
					.toString(Integer.valueOf((((Integer) localObject)
							.equals(localInteger6)) ? ((Integer) localObject)
							.intValue()
							: ((Integer) localObject).intValue() + 1)));
			str1 = str1.replace("{$-baselink}", encode.scriptencode(encode
					.htmlencode(str5)));
			str1 = this.conf.jt.creplace(str1);
		}
		return ((String) str1);
	}

	/**
	 * 将当前网站路径地址 替换成 {$->>repath}，是对编辑器中生成的绝对路径的替换操作，是为方便网站地址的迁移定制的函数。
	 * @param paramString
	 * @return String
	 */
	public String repathencode(String paramString) {
		String str1 = paramString;
		if ((!(cls.isEmpty(str1).booleanValue()))
				&& (this.conf.repath.equals("1"))) {
			String str2 = this.conf.getNGenre();
			String str3 = this.conf.getNURLPre() + this.conf.getNURL();
			String str4 = str2 + "/";
			String str5 = cls.getLRStr(str3, str2, "leftr");
			str1 = str1.replace(str5, "{$->>repath}");
		}
		return str1;
	}

	/**
	 * 将字符串中的 {$->>repath} 替换成当前网站的路径地址，是对编辑器中生成的绝对路径的反向替换操作，是为方便网站地址的迁移定制的函数。
	 * @param paramString
	 * @return String
	 */
	public String repathdecode(String paramString) {
		String str1 = paramString;
		if (!(cls.isEmpty(str1).booleanValue())) {
			String str2 = this.conf.getNGenre();
			String str3 = this.conf.getNURLPre() + this.conf.getNURL();
			String str4 = str2 + "/";
			String str5 = cls.getLRStr(str3, str2, "leftr");
			str1 = str1.replace("{$->>repath}", str5);
		}
		return str1;
	}

	/**
	 * 按照指定的条件输出类别的选项
	 * 
	 * @param paramString
	 * @return String
	 */
	public String selClass(String paramString) {
		String str1 = "";
		String str2 = paramString;
		str1 = selClass(str2, "-1");
		return str1;
	}

	/**
	 * 示例：{$=selClass("genre={$-genre};lng={$-lng}", "{$-myclass}")} = 在模板中常见的分类选项生成函数
	 * @param argStrings, 复合参数型字符串(格式: "genre=articles;fid=1;class=2" ...)
	 * @param argValIDString(可选), 有效的ID序列，当设置此值时只输出在这序列中存在的值
	 * @return String
	 */
	public String selClass(String argStrings, String argValIDString) {
		String str1 = "";

		String str5 = argStrings;
		String str6 = argValIDString;

		String str7 = cls.getParameter(str5, "genre");

		String str8 = cls.getParameter(str5, "lng");

		String str9 = cls.getParameter(str5, "fid");
		Integer localInteger1 = cls.getNum(cls.getParameter(str5, "class"),
				Integer.valueOf(0));
		Integer localInteger2 = cls.getNum(cls.getParameter(str5, "inum"),
				Integer.valueOf(0));
		str1 = this.conf.jt.itake("global.tpl_common.sel-class", "tpl");
		String str3 = "";
		String str2 = cls.ctemplate(str1, "{@}");
		category localcategory = new category(this.conf);
		String[][] arrayOfString = localcategory.getCatAry(str7, cls.getNum(
				str8, Integer.valueOf(0)));
		if (arrayOfString != null) {
			localInteger2 = Integer.valueOf(localInteger2.intValue() + 1);
			for (int i = 0; i < arrayOfString.length; ++i) {
				if ((!(cls.getNum(arrayOfString[i][2], Integer.valueOf(0))
						.equals(cls.getNum(str9, Integer.valueOf(0)))))
						|| ((!(str6.equals("-1"))) && (!(cls.cinstr(str6,
								arrayOfString[i][0], ",").booleanValue()))))
					continue;
				String str4 = str2;
				str4 = str4.replace("{$topic}", encode
						.htmlencode(arrayOfString[i][1]));
				str4 = str4.replace("{$id}", encode
						.htmlencode(arrayOfString[i][0]));
				if (!(cls.getNum(arrayOfString[i][0], Integer.valueOf(0))
						.equals(localInteger1)))
					str4 = str4.replace("{$-selected}", "");
				else
					str4 = str4
							.replace("{$-selected}", "selected=\"selected\"");
				str4 = str4.replace("{$-prestr}", cls.getRepeatedString(
						this.conf.jt.itake("global.lng_common.sys-spsort",
								"lng"), localInteger2));
				str4 = str4.replace("{$-child}", selClass("genre=" + str7
						+ ";lng=" + str8 + ";class=" + localInteger1 + ";inum="
						+ localInteger2 + ";fid="
						+ cls.getNum(arrayOfString[i][0], Integer.valueOf(0)),
						str6));
				str3 = str3 + str4;
			}

		}

		str1 = cls.ctemplates(str1, "{@}", str3);
		str1 = this.conf.jt.creplace(str1);
		return str1;
	}

	/**
	 * 该方法为发送邮件，需要配置接收地址、邮件主题和邮件内容参数，其它参数在配置文件中
	 * @param paramString1 接收邮件人地址
	 * @param paramString2 邮件主题
	 * @param paramString3 邮件内容
	 * @return Boolean
	 */
	public Boolean sendMail(String paramString1, String paramString2,
			String paramString3) {
		Boolean localBoolean = Boolean.valueOf(false);
		String str1 = paramString1;
		String str2 = paramString2;
		String str3 = paramString3;

		String str4 = this.conf.application
				.getInitParameter("mail_smtpusername");
		if (cls.isEmpty(str4).booleanValue())
			str4 = this.conf.jt.itake("global.config.mail-smtpusername", "cfg");

		String str5 = this.conf.application
				.getInitParameter("mail_smtppassword");
		if (cls.isEmpty(str5).booleanValue())
			str5 = this.conf.jt.itake("global.config.mail-smtppassword", "cfg");

		String str6 = this.conf.application.getInitParameter("mail_smtpfrom");
		if (cls.isEmpty(str6).booleanValue())
			str6 = this.conf.jt.itake("global.config.mail-smtpfrom", "cfg");
		if (cls.isEmpty(str6).booleanValue())
			str6 = str4;

		String str7 = this.conf.application
				.getInitParameter("mail_smtpcharset");
		if (cls.isEmpty(str7).booleanValue())
			str7 = this.conf.jt.itake("global.config.mail-smtpcharset", "cfg");

		String str8 = this.conf.application.getInitParameter("mail_smtpserver");
		if (cls.isEmpty(str8).booleanValue())
			str8 = this.conf.jt.itake("global.config.mail-smtpserver", "cfg");

		try {
			String str9 = "";
			Socket localSocket = new Socket(str8, 25);
			BufferedReader localBufferedReader = new BufferedReader(
					new InputStreamReader(localSocket.getInputStream()));
			BufferedWriter localBufferedWriter = new BufferedWriter(
					new OutputStreamWriter(localSocket.getOutputStream()));
			str9 = localBufferedReader.readLine();
			if ((str9.substring(0, 3).equals("220"))
					|| (str9.substring(0, 3).equals("250"))) {
				localBufferedWriter.write("HELO JTBC\n");
				localBufferedWriter.flush();
				str9 = localBufferedReader.readLine();
				if (str9.substring(0, 3).equals("250")) {
					localBufferedWriter.write("AUTH LOGIN\n");
					localBufferedWriter.flush();
					str9 = localBufferedReader.readLine();
					if (str9.substring(0, 3).equals("334")) {
						localBufferedWriter.write(encode.base64encode(str4
								.getBytes())
								+ "\n");
						localBufferedWriter.flush();
						str9 = localBufferedReader.readLine();
						if (str9.substring(0, 3).equals("334")) {
							localBufferedWriter.write(encode.base64encode(str5
									.getBytes())
									+ "\n");
							localBufferedWriter.flush();
							str9 = localBufferedReader.readLine();
							if (str9.substring(0, 3).equals("235")) {
								localBufferedWriter.write("MAIL FROM: <" + str6
										+ ">\n");
								localBufferedWriter.flush();
								str9 = localBufferedReader.readLine();
								if (str9.substring(0, 3).equals("250")) {
									localBufferedWriter.write("RCPT TO: <"
											+ str1 + ">\n");
									localBufferedWriter.flush();
									str9 = localBufferedReader.readLine();
									if (str9.substring(0, 3).equals("250")) {
										localBufferedWriter.write("DATA\n");
										localBufferedWriter.flush();
										str9 = localBufferedReader.readLine();
										if (str9.substring(0, 3).equals("354")) {
											localBufferedWriter
													.write("MIME-Version: 1.0\nContent-type: text/html; charset="
															+ str7
															+ "\nTo: <"
															+ str1
															+ ">\nFrom: <"
															+ str6
															+ ">\nSubject: "
															+ str2
															+ "\n\n"
															+ str3 + "\n.\n");
											localBufferedWriter.flush();
											localBufferedWriter.write("QUIT\n");
											localBufferedWriter.flush();
											localBoolean = Boolean
													.valueOf(true);
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception localException) {
		}
		return localBoolean;
	}

	/**
	 * 按照指定的模块名下面的配置判断是否输出HTML代码中的 base href 代码 
	 * (base href代码一般用于生成html长路径文件后解决页面中的路径问题)。
	 * @param argGenre, 模块名
	 * @return String
	 */
	public String webBase(String argGenre) {
		String str1 = "";
		String str2 = argGenre;
		Integer localInteger = cls.getNum(this.conf.jt.itake(
				"global.config.nbasehref", "cfg"), Integer.valueOf(0));
		if (!(cls.isEmpty(str2).booleanValue()))
			localInteger = cls.getNum(this.conf.jt.itake("global." + str2
					+ ":config.nbasehref", "cfg"), Integer.valueOf(0));
		if (localInteger.intValue() == 1) {
			str1 = this.conf.jt.itake("global.tpl_public.base", "tpl");
			str1 = str1.replace("{$-base}", cls.getLRStr(new StringBuilder()
					.append(this.conf.getNURLPre()).append(this.conf.getNURI())
					.toString(), "/", "leftr")
					+ "/");
			str1 = this.conf.jt.creplace(str1);
		}
		return str1;
	}

	/**
	 * 按照设置的关键字取出网站中共享的头部代码
	 * @param argKey, 关键字
	 * @return String
	 */
	public String webHead(String argKey) {
		String str1 = "";
		String str2 = argKey;
		str1 = this.conf.jt.itake("global.tpl_public." + str2, "tpl");
		str1 = this.conf.jt.creplace(str1);
		return str1;
	}

	/**
	 * 按照设置的关键字取出网站中共享的尾部代码。
	 * @param argKey, 关键字
	 * @return String
	 */
	public String webFoot(String argKey) {
		String str1 = "";
		String str2 = argKey;
		str1 = this.conf.jt.itake("global.tpl_public." + str2, "tpl");
		str1 = this.conf.jt.creplace(str1);
		return str1;
	}

	/**
	 * 该方法为根据传入的参数生成一个jtbc信息节点并返回字符串
	 * 
	 * @param paramString
	 * @return
	 */
	public String webMessage(String paramString) {
		String str1 = paramString;
		String str2 = this.conf.jt.itake("global.tpl_common.wfront-message",
				"tpl");
		str2 = str2.replace("{$message}", encode.htmlencode(str1));
		str2 = this.conf.jt.creplace(str2);
		return str2;
	}

	/**
	 * 该方法为根据传入的参数生成多个jtbc信息节点并返回字符串
	 * 
	 * @param paramString
	 * @return
	 */
	public String webMessages(String paramString1, String paramString2) {
		String str1 = paramString1;
		String str2 = paramString2;
		String str3 = this.conf.jt.itake("global.tpl_common.wfront-messages",
				"tpl");
		str3 = str3.replace("{$message}", encode.htmlencode(str1));
		str3 = str3.replace("{$backurl}", encode.htmlencode(str2));
		str3 = this.conf.jt.creplace(str3);
		return str3;
	}

	/**
	 * 该方法为按照设置的路径与节点以及选中项生成一个jtbc节点并返回字符串
	 * 
	 * @param paramString1
	 * @param paramString2
	 * @param paramString3
	 * @return String
	 */
	public String xmlSelect(String paramString1, String paramString2,
			String paramString3) {
		String str1 = "";
		String str2 = paramString1;
		String str3 = paramString2;
		String str4 = paramString3;
		str1 = xmlSelect(str2, str3, str4, "");
		return str1;
	}

	/**
	 * 
	 * 示例：{$=xmlSelect("global.sel_yesno.all", "0", "radio", "hidden")} 
	 * @param argXInfostr 路径与节点字符串。关于形如 global.sel_yesno.all 的字符串对应的文件可以参见 itake 函数
	 * @param argValue 选中的值
	 * @param argTemplate 使用的模板名
	 * @param argName (可选), 名称, 当输出 radio, checkbox 等类型的选项时需要设置此值
	 * @return String
	 */
	public String xmlSelect(String argXInfostr, String argValue,
			String argTemplate, String argName) {
		String str1 = argXInfostr;
		String str2 = argValue;
		String str3 = argTemplate;
		String str4 = argName;
		String str5 = "";
		String str6 = this.conf.jt.itake("global.tpl_config.xmlselect_" + str3,
				"tpl");
		String str7 = this.conf.jt.itake("global.tpl_config.xmlselect_un"
				+ str3, "tpl");
		if ((!(cls.isEmpty(str6).booleanValue()))
				&& (!(cls.isEmpty(str7).booleanValue()))) {
			String[][] arrayOfString = this.conf.jt.itakes(str1, "sel");
			for (int i = 0; i < arrayOfString.length; ++i) {
				if (cls.cinstr(str2, arrayOfString[i][0], ",").booleanValue())
					str5 = str5 + str6;
				else
					str5 = str5 + str7;
				str5 = str5.replace("{$value}", encode
						.htmlencode(arrayOfString[i][0]));
				str5 = str5.replace("{$explain}", encode
						.htmlencode(arrayOfString[i][1]));
			}
			if (!(cls.isEmpty(str4).booleanValue()))
				str5 = str5.replace("{$name}", encode.htmlencode(str4));
			str5 = this.conf.jt.creplace(str5);
		}
		return str5;
	}
	 public common(conf paramconf)
	   {
	     this.conf = paramconf;
	   }
	 
	public common() {
		// TODO Auto-generated constructor stub
	}
}