/*    */ package jtbc;
/*    */ 
/*    */ import javax.servlet.http.Cookie;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class cookies
/*    */ {
/*    */   public static String getAttribute(conf paramconf, String paramString)
/*    */   {
/*  9 */     String str1 = "";
/* 10 */     conf localconf = paramconf;
/* 11 */     String str2 = paramString;
/* 12 */     Cookie[] arrayOfCookie = localconf.request.getCookies();
/* 13 */     if (arrayOfCookie != null)
/*    */     {
/* 15 */       for (int i = 0; i < arrayOfCookie.length; ++i)
/*    */       {
/* 17 */         Cookie localCookie = arrayOfCookie[i];
/* 18 */         if (!(str2.equals(localCookie.getName())))
/*    */           continue;
/*    */         try
/*    */         {
/* 22 */           str1 = new String(encode.base64decode(localCookie.getValue()), localconf.charset);
/*    */         }
/*    */         catch (Exception localException) {
/*    */         }
/*    */       }
/*    */     }
/* 28 */     return str1;
/*    */   }
/*    */ 
/*    */   public static void removeAttribute(conf paramconf, String paramString)
/*    */   {
/* 33 */     conf localconf = paramconf;
/* 34 */     String str = paramString;
/* 35 */     Cookie[] arrayOfCookie = localconf.request.getCookies();
/* 36 */     if (arrayOfCookie == null)
/*    */       return;
/* 38 */     for (int i = 0; i < arrayOfCookie.length; ++i)
/*    */     {
/* 40 */       Cookie localCookie = arrayOfCookie[i];
/* 41 */       if (!(str.equals(localCookie.getName())))
/*    */         continue;
/* 43 */       localCookie.setMaxAge(0);
/* 44 */       localconf.response.addCookie(localCookie);
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void setAttribute(conf paramconf, String paramString1, String paramString2)
/*    */   {
/* 52 */     conf localconf = paramconf;
/* 53 */     String str1 = paramString1;
/* 54 */     String str2 = paramString2;
/* 55 */     setAttribute(localconf, str1, str2, Integer.valueOf(-1));
/*    */   }
/*    */ 
/*    */   public static void setAttribute(conf paramconf, String paramString1, String paramString2, Integer paramInteger)
/*    */   {
/* 60 */     String str1 = "";
/* 61 */     conf localconf = paramconf;
/* 62 */     String str2 = paramString1;
/* 63 */     String str3 = paramString2;
/* 64 */     Integer localInteger = paramInteger;
/*    */     try
/*    */     {
/* 67 */       Cookie localCookie = new Cookie(str2, encode.base64encode(str3.getBytes(localconf.charset)));
/* 68 */       localCookie.setMaxAge(localInteger.intValue());
/* 69 */       localconf.response.addCookie(localCookie);
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/*    */     }
/*    */   }
/*    */ }