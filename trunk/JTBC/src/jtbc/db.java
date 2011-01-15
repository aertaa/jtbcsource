package jtbc;

/* import javax.servlet.ServletContext; */
import jtbc.dbc.access;
import jtbc.dbc.dbc;
import jtbc.dbc.mysql;
import jtbc.dbc.postgresql;
import jtbc.dbc.sqlite;
import jtbc.dbc.sqlserver;

/**
 * 该类为数据库连接类，根据传入的数据库连接配置信息返回一个数据库的连接实例
 * 
 * @author Administrator
 * 
 */
public class db {
	/**
	 * 该方法传入参数conf类,conf中包含两个属性(dbtype,connStr) 分别对应所连接的数据库类型、需要的连接驱动地址
	 * 根据数据库类型和驱动连接创建出一个数据库实例连接dbc并返回该实例
	 * 
	 * @param paramconf
	 * @return dbc
	 */
	public static dbc newInstance(conf paramconf) {
		Object localObject = null;
		conf localconf = paramconf;
		String str1 = localconf.dbtype;
		String str2 = localconf.connStr;
		// 如果conf对象中的dbtype值为1,表示所连接的数据库为mysql数据库
		if (cls.getNum(str1, Integer.valueOf(0)).intValue() == 1) {
			localObject = new mysql();
		} else {
			String str3;
			String str4;
			/*
			 * 如果conf对象中的dbtype值为2,表示所连接的数据库为SharpPlus sqlite数据库
			 * SQLite，是一款轻型的数据库同时能够跟很多程序语言相结合， 比如 Tcl、C#、PHP、Java等，还有ODBC接口
			 */
			if (cls.getNum(str1, Integer.valueOf(0)).intValue() == 2) {
				localObject = new sqlite();
				str3 = cls.getLRStr(str2, "sqlite:", "rightr");
				str4 = localconf.application.getRealPath(str3).toString();
				str2 = str2.replace(str3, str4);
			}
			// 如果conf对象中的dbtype值为11,表示所连接的数据库为access数据库
			else if (cls.getNum(str1, Integer.valueOf(0)).intValue() == 11) {
				localObject = new access();
				str3 = cls.getLRStr(str2, "DBQ=", "rightr");
				str3 = cls.getLRStr(str3, "#", "leftr");
				str4 = localconf.application.getRealPath(str3).toString();
				str2 = str2.replace(str3, str4);
			}
			// 如果conf对象中的dbtype值为12,表示所连接的数据库为sqlserver数据库
			else if (cls.getNum(str1, Integer.valueOf(0)).intValue() == 12) {
				localObject = new sqlserver();
			}
			/*
			 * 如果conf对象中的dbtype值为21,表示所连接的数据库为PostgreSQL数据库
			 * PostgreSQL是一种特性非常齐全的自由软件的对象-关系型数据库管理系统（ORDBMS）
			 */
			else if (cls.getNum(str1, Integer.valueOf(0)).intValue() == 21) {
				localObject = new postgresql();
			}
		}
		((dbc) localObject).setConnStr(str2);
		return ((dbc) localObject);
	}

}