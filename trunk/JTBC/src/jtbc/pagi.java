/*    */ package jtbc;
/*    */ 
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import jtbc.dbc.dbc;
/*    */ 
/*    */ public class pagi
/*    */ {
/*    */   public conf conf;
/* 10 */   public Long rslimit = Long.valueOf(0L);
/* 11 */   public Long rscount = Long.valueOf(0L);
/* 12 */   public Long pagenum = Long.valueOf(0L);
/* 13 */   public Long pagenums = Long.valueOf(0L);
/* 14 */   public Long pagesize = Long.valueOf(0L);
/* 15 */   public String sqlstr = "";
/*    */ 
/*    */   private Long getRsCount()
/*    */   {
/* 19 */     Long localLong = Long.valueOf(0L);
/* 20 */     String str = "select count(*) from " + cls.getLRStr(cls.getLRStr(this.sqlstr, "from", "rightr"), "order by", "leftr");
/* 21 */     str = str.trim();
/* 22 */     dbc localdbc = db.newInstance(this.conf);
/* 23 */     Object[] arrayOfObject = localdbc.getDataAry(str);
/* 24 */     if (arrayOfObject != null)
/*    */     {
/* 26 */       Object[][] arrayOfObject1 = (Object[][])(Object[][])arrayOfObject[0];
/* 27 */       localLong = cls.getNum64(cls.toString(localdbc.getValue(arrayOfObject1, 0)));
/*    */     }
/* 29 */     return localLong;
/*    */   }
/*    */ 
/*    */   private String getOrderByString1(String paramString)
/*    */   {
/* 35 */     String str2 = paramString;
/* 36 */     Pattern localPattern = Pattern.compile("( .[^ ]*\\.)");
/* 37 */     Matcher localMatcher = localPattern.matcher(str2);
/* 38 */     while (localMatcher.find())
/*    */     {
/* 40 */       String str1 = localMatcher.group(1);
/* 41 */       str2 = str2.replace(str1, " ");
/*    */     }
/* 43 */     return str2;
/*    */   }
/*    */ 
/*    */   private String getOrderByString2(String paramString)
/*    */   {
/* 48 */     String str = paramString;
/* 49 */     str = str.replace("desc", "_desc");
/* 50 */     str = str.replace("asc", "desc");
/* 51 */     str = str.replace("_desc", "asc");
/* 52 */     return str;
/*    */   }
/*    */ 
/*    */   public void Init()
/*    */   {
/* 57 */     this.rscount = getRsCount();
/*    */ 
/* 59 */     if (this.pagesize.longValue() == 0L) this.pagesize = Long.valueOf(20L);
/*    */ 
/* 61 */     if (this.rslimit.longValue() == 0L) this.rslimit = this.rscount;
/* 64 */     else if (this.rscount.longValue() < this.rslimit.longValue()) this.rslimit = this.rscount;
/*    */ 
/* 66 */     if (this.rslimit.longValue() % this.pagesize.longValue() == 0L) this.pagenums = Long.valueOf(this.rslimit.longValue() / this.pagesize.longValue());
/*    */     else this.pagenums = Long.valueOf(this.rslimit.longValue() / this.pagesize.longValue() + 1L);
/*    */   }
/*    */ 
/*    */   public Object[] getDataAry()
/*    */   {
/* 72 */     Object[] arrayOfObject = null;
/* 73 */     if (this.pagenum.longValue() == 0L) this.pagenum = Long.valueOf(1L);
/* 74 */     Long localLong1 = this.pagesize;
/* 75 */     Long localLong2 = Long.valueOf(this.pagesize.longValue() * this.pagenum.longValue());
/* 76 */     Integer localInteger = cls.getNum(this.conf.dbtype, Integer.valueOf(0));
/* 77 */     if (localLong2.longValue() > this.rslimit.longValue())
/*    */     {
/* 79 */       localLong2 = this.rslimit;
/* 80 */       localLong1 = Long.valueOf(localLong2.longValue() - (this.pagesize.longValue() * (this.pagenum.longValue() - 1L)));
/*    */     }
/* 82 */     if ((localLong2.longValue() > 0L) && (localLong1.longValue() > 0L))
/*    */     {
/* 84 */       String str = "";
/* 85 */       if ((localInteger.intValue() >= 0) && (localInteger.intValue() < 10)) str = "select * from (select * from (" + this.sqlstr + " limit 0," + localLong2 + ") t1 order by " + getOrderByString2(getOrderByString1(cls.getLRStr(this.sqlstr, "order by ", "rightr"))) + " limit 0," + localLong1 + ") t2 order by " + getOrderByString1(cls.getLRStr(this.sqlstr, "order by ", "rightr"));
/* 86 */       if ((localInteger.intValue() >= 10) && (localInteger.intValue() < 20)) str = "select * from (select top " + localLong1 + " * from (select top " + localLong2 + cls.getLRStr(this.sqlstr, "select", "rightr") + ") t1 order by " + getOrderByString2(getOrderByString1(cls.getLRStr(this.sqlstr, "order by ", "rightr"))) + ") t2 order by " + getOrderByString1(cls.getLRStr(this.sqlstr, "order by ", "rightr"));
/* 87 */       if ((localInteger.intValue() >= 20) && (localInteger.intValue() < 30)) str = "select * from (select * from (" + this.sqlstr + " limit " + localLong2 + ") t1 order by " + getOrderByString2(getOrderByString1(cls.getLRStr(this.sqlstr, "order by ", "rightr"))) + " limit " + localLong1 + ") t2 order by " + getOrderByString1(cls.getLRStr(this.sqlstr, "order by ", "rightr"));
/* 88 */       if (!(cls.isEmpty(str).booleanValue()))
/*    */       {
/* 90 */         dbc localdbc = db.newInstance(this.conf);
/* 91 */         arrayOfObject = localdbc.getDataAry(str);
/*    */       }
/*    */     }
/* 94 */     return arrayOfObject;
/*    */   }
/*    */ 
/*    */   public pagi(conf paramconf)
/*    */   {
/* 99 */     this.conf = paramconf;
/*    */   }
/*    */ }