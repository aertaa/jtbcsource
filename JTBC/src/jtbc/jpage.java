/*    */ package jtbc;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public abstract class jpage
/*    */ {
/*    */   public conf conf;
/*    */   public String eMessage;
/*    */   public Long pageStartTimeMillis;
/*    */   public Long pageEndTimeMillis;
/*    */   public Long pageRunTimeMillis;
/*    */ 
/*    */   public void PageAutoRedirect()
/*    */   {
/* 17 */     String str1 = this.conf.getNURLPre();
/* 18 */     String str2 = cls.getLRStr(str1, "/", "right");
/* 19 */     Integer localInteger = cls.getNum(this.conf.jt.itake("global.config.autoredirect", "cfg"), Integer.valueOf(0));
/* 20 */     if ((localInteger.intValue() != 1) || 
/* 22 */       (cls.isEmpty(str2).booleanValue()))
/*    */       return;
/* 24 */     str2 = str2.toLowerCase();
/* 25 */     if (cls.getLRStr(str2, ".", "left") == "www")
/*    */       return;
/* 27 */     String str3 = "www." + str2;
/* 28 */     str1 = str1.toLowerCase();
/* 29 */     str1 = str1.replace(str2, str3);
/* 30 */     String str4 = str1 + this.conf.getNURI();
/* 31 */     if (cls.getLRStr(str4, "/", "right").equals("default.jsp")) str4 = cls.getLRStr(str4, "/", "leftr") + "/";
/* 32 */     if (!(cls.isEmpty(this.conf.getNURS()).booleanValue())) str4 = str4 + "?" + this.conf.getNURS();
/* 33 */     this.conf.response.setStatus(301);
/* 34 */     this.conf.response.setHeader("Location", str4);
/*    */   }
/*    */ 
/*    */   public void Init(Object paramObject1, Object paramObject2)
/*    */   {
/* 42 */     Object localObject1 = paramObject1;
/* 43 */     Object localObject2 = paramObject2;
/* 44 */     this.conf = new conf();
/* 45 */     this.conf.Init(localObject1, localObject2);
/*    */   }
/*    */ 
/*    */   public void PageInit()
/*    */   {
/* 50 */     PageAutoRedirect();
/* 51 */     this.pageStartTimeMillis = Long.valueOf(System.currentTimeMillis());
/* 52 */     this.conf.ntitle = this.conf.jt.itake("global.default.web_title", "lng");
/* 53 */     this.conf.navSpStr = this.conf.jt.itake("global.config.navspstr", "cfg");
/* 54 */     this.conf.ntitleSpStr = this.conf.jt.itake("global.config.ntitlespstr", "cfg");
/*    */   }
/*    */ 
/*    */   public void PageNoCache()
/*    */   {
/* 59 */     this.conf.response.setDateHeader("Expires", 0L);
/* 60 */     this.conf.response.setHeader("Cache-Control", "no-cache");
/*    */   }
/*    */ 
/*    */   public void PagePrint(String paramString)
/*    */   {
/* 65 */     Boolean localBoolean = Boolean.valueOf(true);
/* 66 */     String str = paramString;
/* 67 */     if (!(cls.isEmpty(str).booleanValue()))
/*    */     {
/* 69 */       if (str.length() < this.conf.ajaxPreContent.length()) localBoolean = Boolean.valueOf(false);
/* 72 */       else if (str.substring(0, this.conf.ajaxPreContent.length()).equals(this.conf.ajaxPreContent)) localBoolean = Boolean.valueOf(false);
/*    */     }
/*    */ 
/* 75 */     if (localBoolean.booleanValue())
/*    */     {
/* 77 */       this.pageEndTimeMillis = Long.valueOf(System.currentTimeMillis());
/* 78 */       this.pageRunTimeMillis = Long.valueOf(this.pageEndTimeMillis.longValue() - this.pageStartTimeMillis.longValue());
/* 79 */       str = str + "\r\n<!--JTBC(2.0), Processed in " + this.pageRunTimeMillis + " ms-->";
/*    */     }
/*    */     try
/*    */     {
/* 83 */       PrintWriter localPrintWriter = this.conf.response.getWriter();
/* 84 */       localPrintWriter.print(str);
/* 85 */       localPrintWriter.close();
/*    */     }
/*    */     catch (Exception localException) {
/*    */     }
/*    */   }
/*    */ 
/*    */   public void PageClose() {
/* 92 */     this.pageEndTimeMillis = Long.valueOf(System.currentTimeMillis());
/* 93 */     this.pageRunTimeMillis = Long.valueOf(this.pageEndTimeMillis.longValue() - this.pageStartTimeMillis.longValue());
/*    */   }
/*    */ }