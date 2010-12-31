/*    */ package jtbc;
/*    */ 
/*    */ import javax.servlet.http.Cookie;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class cookies
/*    */ {
/*  7 */   private static String cookiesPath = "/";
/*    */ 
/*    */   public static String getAttribute(conf paramconf, String paramString)
/*    */   {
/* 11 */     String str1 = "";
/* 12 */     conf localconf = paramconf;
/* 13 */     String str2 = paramString;
/* 14 */     Cookie[] arrayOfCookie = localconf.request.getCookies();
/* 15 */     if (arrayOfCookie != null)
/*    */     {
/* 17 */       for (int i = 0; i < arrayOfCookie.length; ++i)
/*    */       {
/* 19 */         Cookie localCookie = arrayOfCookie[i];
/* 20 */         if (!(str2.equals(localCookie.getName())))
/*    */           continue;
/*    */         try
/*    */         {
/* 24 */           str1 = new String(encode.base64decode(localCookie.getValue()), localconf.charset);
/*    */         }
/*    */         catch (Exception localException) {
/*    */         }
/*    */       }
/*    */     }
/* 30 */     return str1;
/*    */   }
/*    */ 
/*    */   public static void removeAttribute(conf paramconf, String paramString)
/*    */   {
/* 35 */     conf localconf = paramconf;
/* 36 */     String str = paramString;
/* 37 */     Cookie[] arrayOfCookie = localconf.request.getCookies();
/* 38 */     if (arrayOfCookie == null)
/*    */       return;
/* 40 */     for (int i = 0; i < arrayOfCookie.length; ++i)
/*    */     {
/* 42 */       Cookie localCookie = arrayOfCookie[i];
/* 43 */       if (!(str.equals(localCookie.getName())))
/*    */         continue;
/* 45 */       localCookie.setMaxAge(0);
/* 46 */       localCookie.setPath(cookiesPath);
/* 47 */       localconf.response.addCookie(localCookie);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void setAttribute(conf paramconf, String paramString1, String paramString2)
/*    */   {
/* 55 */     conf localconf = paramconf;
/* 56 */     String str1 = paramString1;
/* 57 */     String str2 = paramString2;
/* 58 */     setAttribute(localconf, str1, str2, Integer.valueOf(-1));
/*    */   }
/*    */ 
/*    */   public static void setAttribute(conf paramconf, String paramString1, String paramString2, Integer paramInteger)
/*    */   {
/* 63 */     String str1 = "";
/* 64 */     conf localconf = paramconf;
/* 65 */     String str2 = paramString1;
/* 66 */     String str3 = paramString2;
/* 67 */     Integer localInteger = paramInteger;
/*    */     try
/*    */     {
/* 70 */       Cookie localCookie = new Cookie(str2, encode.base64encode(str3.getBytes(localconf.charset)));
/* 71 */       localCookie.setMaxAge(localInteger.intValue());
/* 72 */       localCookie.setPath(cookiesPath);
/* 73 */       localconf.response.addCookie(localCookie);
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/*    */     }
/*    */   }
/*    */ }