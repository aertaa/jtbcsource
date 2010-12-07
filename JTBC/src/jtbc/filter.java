/*    */ package jtbc;
/*    */ 
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ 
/*    */ public class filter
/*    */ {
/*    */   public static String striptags(String paramString)
/*    */   {
/*  9 */     Boolean localBoolean = Boolean.valueOf(true);
/* 10 */     String str1 = paramString;
/* 11 */     String str2 = "<(.[^>]*)>";
/* 12 */     while (localBoolean.booleanValue())
/*    */     {
/* 14 */       localBoolean = Boolean.valueOf(false);
/*    */ 
/* 16 */       Pattern localPattern = Pattern.compile(str2);
/* 17 */       Matcher localMatcher = localPattern.matcher(str1);
/* 18 */       while (localMatcher.find())
/*    */       {
/* 20 */         localBoolean = Boolean.valueOf(true);
/* 21 */         String str3 = localMatcher.group();
/* 22 */         String str4 = "";
/* 23 */         str1 = str1.replace(str3, str4);
/*    */       }
/*    */     }
/* 26 */     return str1;
/*    */   }
/*    */ }