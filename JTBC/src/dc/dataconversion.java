package dc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jtbc.*;
import jtbc.dbc.*;

public class dataconversion {
	public String tfpre; 
	public String fromsql;
	public String fromdbtype;
	public String fromconnstr;
	public String inesrttpl;
	public String todbtype;
	public String toconnstr;
	
	public dataconversion() {
		 this.fromsql="select lbid = case CategoryID when 4 then 8 when 5 then 7 when 26 then 3 when 82 then 9 when 97 then 4 when 104 then 43 when 105 then 6 end  ,* from V_Information where CategoryID in(SELECT ID FROM Category WHERE (ParentID = '41'))";
		 this.fromdbtype="12";
		 this.fromconnstr="jdbc:sqlserver://192.168.1.101:1660;databasename=WJMZJ#username=sa;password=sa";
		 this.inesrttpl="Insert Into jtbc_news ([a_topic],[a_class],[a_content],[a_content_images],[a_commendatory],[a_hidden],[a_count],[a_time],[a_update],[a_lng]) values('{$Title}',{$lbid},'{$-content}','{$-imgcontent}',0,0,{$Hits},'{$CreateTime}',0,0)";
		 this.todbtype="2";
		 this.toconnstr="jdbc:sqlite:C:/Documents and Settings/Administrator/桌面/wjmz/WEB-INF/db/database.db";
	}
	public void createsql(){
		String inserttpl ="";
		conf fromconf = new conf();
		fromconf.dbtype = this.fromdbtype;
		fromconf.connStr = this.fromconnstr;
		conf toconf = new conf();
		toconf.dbtype = this.todbtype;
		toconf.connStr = this.toconnstr;
		dbc dbd= db.newInstance(toconf);
		
		String[]imgcontent;

		dbc dbc= db.newInstance(fromconf);
		Object[] tArys = dbc.getDataAry(this.fromsql);

		if (tArys != null) {
			for (int tis = 0; tis < tArys.length; tis++) {
				inserttpl = this.inesrttpl;
				Object[][] tAry = (Object[][])tArys[tis];
				for (int ti = 0; ti < tAry.length; ti++) {
					//encode.htmlencode(
					inserttpl= inserttpl.replace("{$" + cls.toString(tAry[ti][0]) + "}", cls.toString(tAry[ti][1]));	
				}
				imgcontent = getcontent((String)dbc.getValue(tAry, "Content"));
				inserttpl= inserttpl.replace("{$-content}",imgcontent[1]);
				inserttpl= inserttpl.replace("{$-imgcontent}",imgcontent[0]);
				System.out.println(imgcontent[0]);
				//dbd.Execute(inserttpl);
			}
		} 
	}
	public String[] getcontent(String paramString){
		conf toconf = new conf();
		toconf.dbtype = this.todbtype;
		toconf.connStr = this.toconnstr;
		dbc dbd= db.newInstance(toconf);
	     String str1 = paramString;
		 String str2 = "";
		 String str3 = str1;
		 String[] str4= new String[2];
	     Pattern localPattern = Pattern.compile("<img\\s*.*\\s*src=[\"|\'](\\S*)[\"|\']\\s*.*/>");
	     Matcher localMatcher = localPattern.matcher(str1);
	     while (localMatcher.find())
	     {
	       String str5 = localMatcher.group(1).replace("/Admin/Upload/Info/Image/", "common/upload/").replace("公共目录","old");
	       str2= str2+str5+"|";
	       str3= str3.replace(localMatcher.group(1), "{$->>repath}news/"+str5); 
	       dbd.Execute("Insert Into jtbc_sys_upload([up_genre],[up_filename],[up_field],[up_fid],[up_time],[up_foreback],[up_username],[up_valid],[up_vlreason]) values"+
	    		   "('news','"+ cls.getLRStr(str5, "|","leftr")+"','content_images','','2010-12-01','0','simple','1','2')");
	     }
	     str4[0] = cls.getLRStr(str2, "|", "leftr");
	     str4[1] = str3;
	     return str4;	
	}
	public void upuploadid() {
		String sql = "select b.up_fid,a.a_id ,b.up_id from jtbc_news a inner join  [jtbc_sys_upload] b on  replace(a.a_content_images,b.up_filename,'')!= a.a_content_images";
		conf toconf = new conf();
		toconf.dbtype = this.todbtype;
		toconf.connStr = this.toconnstr;
		dbc dbd = db.newInstance(toconf);
		String upsql = "update jtbc_sys_upload set up_fid = '{$a_id}' where up_id = {$up_id}";
		Object[] tArys = dbd.getDataAry(sql);
		if (tArys != null) {
			for (int tis = 0; tis < tArys.length; tis++) {
				String str1 = upsql;
				Object[][] tAry = (Object[][])tArys[tis];
				str1= str1.replace("{$a_id}",(Integer)dbd.getValue(tAry, "a_id")+"");
				str1= str1.replace("{$up_id}",(Integer)dbd.getValue(tAry, "up_id")+"");
				dbd.Execute(str1);
				System.out.println(str1);
			}
		}

	}
	public static void main(String[] args) {
		dataconversion dc = new dataconversion();
//		dc.fromsql="select lbid = case CategoryID when 4 then 8 when 5 then 7 when 26 then 3 when 82 then 9 when 97 then 4 when 104 then 43 when 105 then 6 end  ,* from V_Information where CategoryID in(SELECT ID FROM Category WHERE (ParentID = '41'))";
//		dc.fromdbtype="12";
//		dc.fromconnstr="jdbc:sqlserver://192.168.1.101:1660;databasename=WJMZJ#username=sa;password=sa";
//		dc.inesrttpl="Insert Into jtbc_news ([a_topic],[a_class],[a_content],[a_content_images],[a_commendatory],[a_hidden],[a_count],[a_time],[a_update],[a_lng]) values('{$Title}',{$lbid},'{$-content}','{$-imgcontent}',0,0,{$Hits},'{$CreateTime}',0,0)";
//		dc.todbtype="2";
//		dc.toconnstr="jdbc:sqlite:C:/Documents and Settings/Administrator/桌面/wjmz/WEB-INF/db/database.db";
//		dc.createsql();
//		dc.fromsql="select * from (select lbid = case CategoryID when 83 then 26 when 84 then 14 when 85 then 14 when 86 then 27 when 87 then 24 when 88 then 16 when 89 then 17 when 90 then 29 when 91 then 25 when 93 then 25 when 94 then 19 when 95 then 41 when 96 then 28 end ,* from V_Information where CategoryID not in(SELECT ID FROM Category WHERE (ParentID = '41'))) b where lbid is not null and lbid !=''";
//		dc.fromdbtype="12";
//		dc.fromconnstr="jdbc:sqlserver://192.168.1.101:1660;databasename=WJMZJ#username=sa;password=sa";
//		dc.inesrttpl="Insert Into jtbc_mzfw ([m_topic],[m_class],[m_content],[m_content_images],[m_commendatory],[m_hidden],[m_count],[m_time],[m_update],[m_lng],[m_hclass]) values('{$Title}',{$lbid},'{$-content}','{$-imgcontent}',0,0,{$Hits},'{$CreateTime}',0,0,35)";
//		dc.todbtype="2";
//		dc.toconnstr="jdbc:sqlite:C:/Documents and Settings/Administrator/桌面/wjmz/WEB-INF/db/database.db";
//		dc.createsql();
		dc.upuploadid();
	}
}
