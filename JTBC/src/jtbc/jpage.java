/*    */ package jtbc;
/*    */ 
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
/*    */   public void Init(Object paramObject1, Object paramObject2)
/*    */   {
/* 15 */     Object localObject1 = paramObject1;
/* 16 */     Object localObject2 = paramObject2;
/* 17 */     this.conf = new conf();
/* 18 */     this.conf.Init(localObject1, localObject2);
/*    */   }
/*    */ 
/*    */   public void PageInit()
/*    */   {
/* 23 */     this.pageStartTimeMillis = Long.valueOf(System.currentTimeMillis());
/* 24 */     this.conf.ntitle = this.conf.jt.itake("global.default.web_title", "lng");
/* 25 */     this.conf.navSpStr = this.conf.jt.itake("global.config.navspstr", "cfg");
/* 26 */     this.conf.ntitleSpStr = this.conf.jt.itake("global.config.ntitlespstr", "cfg");
/*    */   }
/*    */ 
/*    */   public void PageNoCache()
/*    */   {
/* 31 */     this.conf.response.setDateHeader("Expires", 0L);
/* 32 */     this.conf.response.setHeader("Cache-Control", "no-cache");
/*    */   }
/*    */ 
/*    */   public void PageClose()
/*    */   {
/* 37 */     this.pageEndTimeMillis = Long.valueOf(System.currentTimeMillis());
/* 38 */     this.pageRunTimeMillis = Long.valueOf(this.pageEndTimeMillis.longValue() - this.pageStartTimeMillis.longValue());
/*    */   }
/*    */ }