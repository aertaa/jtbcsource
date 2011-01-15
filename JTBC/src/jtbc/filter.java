/*    */ package jtbc;
/*    */ 
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ //过滤器
/*    */ public class filter
/*    */ {
			/**
			 * 将字符串与"<(.[^>]*)>"正则表达式比对进行过滤
			 * @param paramString 字符参数
			 * @return	过滤后字符值
			 */
/*    */   public static String striptags(String paramString)
/*    */   {
/*  9 */     Boolean localBoolean = Boolean.valueOf(true);
/* 10 */     String str1 = paramString;
/* 11 */     String str2 = "<(.[^>]*)>";
/* 12 */     while (localBoolean.booleanValue())
/*    */     {
/* 14 */       localBoolean = Boolean.valueOf(false);
/*    */ 		//将给定的正则表达式编译到模式中
/* 16 */       Pattern localPattern = Pattern.compile(str2);
				//创建匹配给定输入与此模式的匹配器
/* 17 */       Matcher localMatcher = localPattern.matcher(str1);
				
/* 18 */       while (localMatcher.find())//尝试查找与该模式匹配的输入序列的下一个子序列
/*    */       {
/* 20 */         localBoolean = Boolean.valueOf(true);
				//由以前匹配操作所匹配的输入子序列
/* 21 */         String str3 = localMatcher.group();
/* 22 */         String str4 = "";
/* 23 */         str1 = str1.replace(str3, str4);
/*    */       }
/*    */     }
/* 26 */     return str1;
/*    */   }
/*    */ }