package dc;

import jtbc.*;
import jtbc.dbc.*;

public class TestJtbc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestJtbc.test1();
	}
	public static void test1(){
		conf fromconf = new conf();
		fromconf.dbtype = "2";
		fromconf.connStr = "jdbc:sqlite:WEB-INF/db/database.db";		
		dbc dbc= db.newInstance(fromconf);
		Object[] tArys = dbc.getDataAry("select * from jtbc_admin");
		if (tArys != null) {
			//System.out.println(tArys.length);
			for (int i = 0; i < tArys.length; i++) {
				//System.out.println(tArys[i]);
			}
		}
	}
	
	
}
