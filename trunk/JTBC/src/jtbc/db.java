/*    */ package jtbc;
/*    */ 
/*    */ import javax.servlet.ServletContext;
/*    */ import jtbc.dbc.access;
/*    */ import jtbc.dbc.dbc;
/*    */ import jtbc.dbc.mysql;
/*    */ import jtbc.dbc.postgresql;
/*    */ import jtbc.dbc.sqlite;
/*    */ import jtbc.dbc.sqlserver;
/*    */ 
/*    */ public class db
/*    */ {
/*    */   public static dbc newInstance(conf paramconf)
/*    */   {
/*  9 */     Object localObject = null;
/* 10 */     conf localconf = paramconf;
/* 11 */     String str1 = localconf.dbtype;
/* 12 */     String str2 = localconf.connStr;
/* 13 */     if (cls.getNum(str1, Integer.valueOf(0)).intValue() == 1) { localObject = new mysql();
/*    */     }
/*    */     else
/*    */     {
/*    */       String str3;
/*    */       String str4;
/* 14 */       if (cls.getNum(str1, Integer.valueOf(0)).intValue() == 2)
/*    */       {
/* 16 */         localObject = new sqlite();
/* 17 */         str3 = cls.getLRStr(str2, "sqlite:", "rightr");
/* 18 */         str4 =str3; 
				//localconf.application.getRealPath(str3).toString();
				//System.out.println(str4);
/* 19 */         str2 = str2.replace(str3, str4);
/*    */       }
/* 21 */       else if (cls.getNum(str1, Integer.valueOf(0)).intValue() == 11)
/*    */       {
/* 23 */         localObject = new access();
/* 24 */         str3 = cls.getLRStr(str2, "DBQ=", "rightr");
/* 25 */         str3 = cls.getLRStr(str3, "#", "leftr");
/* 26 */         str4 = localconf.application.getRealPath(str3).toString();
/* 27 */         str2 = str2.replace(str3, str4);
/*    */       }
/* 29 */       else if (cls.getNum(str1, Integer.valueOf(0)).intValue() == 12) { localObject = new sqlserver();
/* 30 */       } else if (cls.getNum(str1, Integer.valueOf(0)).intValue() == 21) { localObject = new postgresql(); } }
/* 31 */     ((dbc)localObject).setConnStr(str2);
/* 32 */     return ((dbc)localObject);
/*    */   }
/*    */ }