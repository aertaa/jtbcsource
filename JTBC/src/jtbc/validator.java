/*    */ package jtbc;
/*    */ 
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class validator
/*    */ {
/*    */   public static Boolean isChinese(String paramString)
/*    */   {
/*  9 */     Boolean localBoolean = Boolean.valueOf(false);
/* 10 */     String str = paramString;
/* 11 */     if (!(cls.isEmpty(str).booleanValue()))
/*    */     {
/* 13 */       Pattern localPattern = Pattern.compile("^[\\u0391-\\uFFE5]+$");
/* 14 */       Matcher localMatcher = localPattern.matcher(str);
/* 15 */       localBoolean = Boolean.valueOf(localMatcher.matches());
/*    */     }
/* 17 */     return localBoolean;
/*    */   }
/*    */ 
/*    */   public static Boolean isEmail(String paramString)
/*    */   {
/* 22 */     Boolean localBoolean = Boolean.valueOf(false);
/* 23 */     String str = paramString;
/* 24 */     if (!(cls.isEmpty(str).booleanValue()))
/*    */     {
/* 26 */       Pattern localPattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
/* 27 */       Matcher localMatcher = localPattern.matcher(str);
/* 28 */       localBoolean = Boolean.valueOf(localMatcher.matches());
/*    */     }
/* 30 */     return localBoolean;
/*    */   }
/*    */ 
/*    */   public static Boolean isMobile(String paramString)
/*    */   {
/* 35 */     Boolean localBoolean = Boolean.valueOf(false);
/* 36 */     String str = paramString;
/* 37 */     if (!(cls.isEmpty(str).booleanValue()))
/*    */     {
/* 39 */       Pattern localPattern = Pattern.compile("^1\\d{10}$");
/* 40 */       Matcher localMatcher = localPattern.matcher(str);
/* 41 */       localBoolean = Boolean.valueOf(localMatcher.matches());
/*    */     }
/* 43 */     return localBoolean;
/*    */   }
/*    */ 
/*    */   public static Boolean isNatural(String paramString)
/*    */   {
/* 48 */     Boolean localBoolean = Boolean.valueOf(false);
/* 49 */     String str = paramString;
/* 50 */     if (!(cls.isEmpty(str).booleanValue()))
/*    */     {
/* 52 */       Pattern localPattern = Pattern.compile("^[A-Za-z0-9]+$");
/* 53 */       Matcher localMatcher = localPattern.matcher(str);
/* 54 */       localBoolean = Boolean.valueOf(localMatcher.matches());
/*    */     }
/* 56 */     return localBoolean;
/*    */   }
/*    */ 
/*    */   public static Boolean isUsername(String paramString)
/*    */   {
/* 61 */     Boolean localBoolean = Boolean.valueOf(false);
/* 62 */     String str = paramString;
/* 63 */     if (!(cls.isEmpty(str).booleanValue()))
/*    */     {
/* 65 */       Pattern localPattern = Pattern.compile("^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$");
/* 66 */       Matcher localMatcher = localPattern.matcher(str);
/* 67 */       localBoolean = Boolean.valueOf(localMatcher.matches());
/*    */     }
/* 69 */     return localBoolean;
/*    */   }
/*    */ 
/*    */   public static Boolean isZip(String paramString)
/*    */   {
/* 74 */     Boolean localBoolean = Boolean.valueOf(false);
/* 75 */     String str = paramString;
/* 76 */     if (!(cls.isEmpty(str).booleanValue()))
/*    */     {
/* 78 */       Pattern localPattern = Pattern.compile("^[0-9]\\d{5}$");
/* 79 */       Matcher localMatcher = localPattern.matcher(str);
/* 80 */       localBoolean = Boolean.valueOf(localMatcher.matches());
/*    */     }
/* 82 */     return localBoolean;
/*    */   }
/*    */ }