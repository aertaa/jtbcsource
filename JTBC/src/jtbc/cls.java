/*     */ package jtbc;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Random;
/*     */ import java.util.TimeZone;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class cls
/*     */ {
/*     */   /*测试主函数*/
			public static void main(String[] args)
			{
				System.out.println(cls.concat("ab","c"));
			}
			/*连接两个字符串*/
			public static String cfnames(String paramString1, String paramString2)
/*     */   {
/*  15 */     String str1 = "";
/*  16 */     String str2 = paramString1;
/*  17 */     String str3 = paramString2;
/*  18 */     str1 = concat(str2, str3);
/*  19 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String concat(String paramString1, String paramString2)
/*     */   {
/*  24 */     String str1 = "";
/*  25 */     String str2 = paramString1;
/*  26 */     String str3 = paramString2;
/*  27 */     str2 = getString(str2);
/*  28 */     str3 = getString(str3);
/*  29 */     str1 = str2 + str3;
/*  30 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String cper(Integer paramInteger1, Integer paramInteger2)
/*     */   {
/*  35 */     String str1 = "0";
/*  36 */     Integer localInteger1 = paramInteger1;
/*  37 */     Integer localInteger2 = paramInteger2;
/*  38 */     String str2 = toString(localInteger1);
/*  39 */     String str3 = toString(localInteger2);
/*  40 */     if ((!(localInteger1.equals(Integer.valueOf(0)))) || (!(localInteger2.equals(Integer.valueOf(0))))) str1 = toString(Double.valueOf(getDouble(str2).doubleValue() / getDouble(str3).doubleValue() * 100.0D));
/*  41 */     str1 = getLRStr(str1, ".", "left");
/*  42 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String ctemplate(String paramString1, String paramString2)
/*     */   {
/*  47 */     String str1 = "";
/*  48 */     String str2 = paramString1;
/*  49 */     String str3 = paramString2;
/*  50 */     if ((!(isEmpty(str2).booleanValue())) && (str2.indexOf(str3) >= 0))
/*     */     {
/*  52 */       String str4 = "<!--fixed-->" + str2 + "<!--fixed-->";
/*  53 */       String[] arrayOfString = str4.split(Pattern.quote(str3));
/*  54 */       if (arrayOfString.length == 3) str1 = arrayOfString[1];
/*     */     }
/*  56 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String ctemplates(String paramString1, String paramString2, String paramString3)
/*     */   {
/*  61 */     String str1 = "";
/*  62 */     String str2 = paramString1;
/*  63 */     String str3 = paramString2;
/*  64 */     String str4 = paramString3;
/*  65 */     if ((!(isEmpty(str2).booleanValue())) && (str2.indexOf(str3) >= 0))
/*     */     {
/*  67 */       String str5 = "<!--fixed-->" + str2 + "<!--fixed-->";
/*  68 */       String[] arrayOfString = str5.split(Pattern.quote(str3));
/*  69 */       if (arrayOfString.length == 3) str1 = str2.replace(str3 + arrayOfString[1] + str3, str4);
/*     */     }
/*  71 */     return str1;
/*     */   }
/*     */ 
/*     */   public static Boolean cidary(String paramString)
/*     */   {
/*  76 */     Boolean localBoolean = Boolean.valueOf(false);
/*  77 */     String str = getString(paramString);
/*  78 */     if (!(isEmpty(str).booleanValue()))
/*     */     {
/*  80 */       localBoolean = Boolean.valueOf(true);
/*  81 */       String[] arrayOfString = str.split(Pattern.quote(","));
/*  82 */       for (int i = 0; i < arrayOfString.length; ++i)
/*     */       {
/*  84 */         if (getNum(arrayOfString[i], Integer.valueOf(-1)).intValue() != -1)
/*     */           continue;
/*  86 */         localBoolean = Boolean.valueOf(false);
/*  87 */         break;
/*     */       }
/*     */     }
/*     */ 
/*  91 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   public static Boolean cinstr(String paramString1, String paramString2, String paramString3)
/*     */   {
/*  96 */     Boolean localBoolean = Boolean.valueOf(false);
/*  97 */     String str1 = paramString1;
/*  98 */     String str2 = paramString2;
/*  99 */     String str3 = paramString3;
/* 100 */     if (str1.equals(str2)) localBoolean = Boolean.valueOf(true);
/* 103 */     else if (str1.indexOf(str3 + str2 + str3) > 0) localBoolean = Boolean.valueOf(true);
/* 106 */     else if (getLRStr(str1, str3, "left").equals(str2)) localBoolean = Boolean.valueOf(true);
/* 109 */     else if (getLRStr(str1, str3, "right").equals(str2)) localBoolean = Boolean.valueOf(true);
/*     */ 
/* 113 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   public static Boolean cinstrs(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 118 */     Boolean localBoolean = Boolean.valueOf(false);
/* 119 */     String str1 = paramString1;
/* 120 */     String str2 = paramString2;
/* 121 */     String str3 = paramString3;
/* 122 */     if (str1.equals(str2)) { localBoolean = Boolean.valueOf(true);
/*     */     }
/*     */     else {
/* 125 */       localBoolean = Boolean.valueOf(true);
/* 126 */       String[] arrayOfString = str2.split(Pattern.quote(str3));
/* 127 */       for (int i = 0; i < arrayOfString.length; ++i)
/*     */       {
/* 129 */         if (cinstr(str1, arrayOfString[i], str3).booleanValue()) continue; localBoolean = Boolean.valueOf(false);
/*     */       }
/*     */     }
/* 132 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   public static String formatByte(String paramString)
/*     */   {
/* 137 */     String str1 = paramString;
/* 138 */     String str2 = formatByte(getNum64(str1, Long.valueOf(0L)));
/* 139 */     return str2;
/*     */   }
/*     */ 
/*     */   public static String formatByte(Long paramLong)
/*     */   {
/* 144 */     Long localLong = paramLong;
/* 145 */     String str = "";
/* 146 */     if (localLong.longValue() > 1073741824L) str = toString(Double.valueOf(Math.round(localLong.longValue() / 1073741824.0D * 1000.0D) / 1000.0D)) + " GB";
/* 149 */     else if (localLong.longValue() > 1048576L) str = toString(Double.valueOf(Math.round(localLong.longValue() / 1048576.0D * 1000.0D) / 1000.0D)) + " MB";
/* 152 */     else if (localLong.longValue() > 1024L) str = toString(Double.valueOf(Math.round(localLong.longValue() / 1024.0D * 1000.0D) / 1000.0D)) + " KB";
/*     */     else str = toString(localLong) + " B";
/*     */ 
/* 156 */     return str;
/*     */   }
/*     */ 
/*     */   public static String formatDate(String paramString)
/*     */   {
/* 161 */     String str1 = "";
/* 162 */     String str2 = paramString;
/* 163 */     str1 = formatDate(str2, Integer.valueOf(100));
/* 164 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String formatDate(Date paramDate)
/*     */   {
/* 169 */     String str = "";
/* 170 */     Date localDate = paramDate;
/* 171 */     str = formatDate(localDate, Integer.valueOf(100));
/* 172 */     return str;
/*     */   }
/*     */ 
/*     */   public static String formatDate(String paramString, Integer paramInteger)
/*     */   {
/* 177 */     Integer localInteger = paramInteger;
/* 178 */     String str1 = paramString;
/* 179 */     String str2 = str1;
/* 180 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */     try
/*     */     {
/* 183 */       Date localDate = localSimpleDateFormat.parse(str1);
/* 184 */       str2 = formatDate(localDate, localInteger);
/*     */     } catch (Exception localException) {
/*     */     }
/* 187 */     return str2;
/*     */   }
/*     */ 
/*     */   public static String formatDate(Date paramDate, Integer paramInteger)
/*     */   {
/* 192 */     String str = "";
/* 193 */     Integer localInteger = paramInteger;
/* 194 */     Date localDate = paramDate;
/*     */     try
/*     */     {
/* 197 */       Calendar localCalendar = Calendar.getInstance();
/* 198 */       localCalendar.setTime(localDate);
/* 199 */       switch (localInteger.intValue())
/*     */       {
/*     */       case -3:
/* 202 */         str = toString(Integer.valueOf(localCalendar.get(5)));
/* 203 */         break;
/*     */       case -2:
/* 205 */         str = toString(Integer.valueOf(localCalendar.get(2) + 1));
/* 206 */         break;
/*     */       case -1:
/* 208 */         str = toString(Integer.valueOf(localCalendar.get(1)));
/* 209 */         break;
/*     */       case 0:
/* 211 */         str = toString(Integer.valueOf(localCalendar.get(1))) + toString(Integer.valueOf(localCalendar.get(2) + 1)) + toString(Integer.valueOf(localCalendar.get(5))) + toString(Integer.valueOf(localCalendar.get(11))) + toString(Integer.valueOf(localCalendar.get(12))) + toString(Integer.valueOf(localCalendar.get(13)));
/* 212 */         break;
/*     */       case 1:
/* 214 */         str = toString(Integer.valueOf(localCalendar.get(1))) + "-" + toString(Integer.valueOf(localCalendar.get(2) + 1)) + "-" + toString(Integer.valueOf(localCalendar.get(5)));
/* 215 */         break;
/*     */       case 2:
/* 217 */         str = toString(Integer.valueOf(localCalendar.get(1))) + "/" + toString(Integer.valueOf(localCalendar.get(2) + 1)) + "/" + toString(Integer.valueOf(localCalendar.get(5)));
/* 218 */         break;
/*     */       case 3:
/* 220 */         str = toString(Integer.valueOf(localCalendar.get(1))) + "." + toString(Integer.valueOf(localCalendar.get(2) + 1)) + "." + toString(Integer.valueOf(localCalendar.get(5)));
/* 221 */         break;
/*     */       case 4:
/* 223 */         str = toString(Integer.valueOf(localCalendar.get(1))) + "-" + formatTime(Integer.valueOf(localCalendar.get(2) + 1)) + "-" + formatTime(Integer.valueOf(localCalendar.get(5)));
/* 224 */         break;
/*     */       case 5:
/* 226 */         str = toString(Integer.valueOf(localCalendar.get(1))) + "/" + formatTime(Integer.valueOf(localCalendar.get(2) + 1)) + "/" + formatTime(Integer.valueOf(localCalendar.get(5)));
/* 227 */         break;
/*     */       case 6:
/* 229 */         str = toString(Integer.valueOf(localCalendar.get(1))) + "." + formatTime(Integer.valueOf(localCalendar.get(2) + 1)) + "." + formatTime(Integer.valueOf(localCalendar.get(5)));
/* 230 */         break;
/*     */       case 7:
/* 232 */         str = toString(Integer.valueOf(localCalendar.get(1))) + formatTime(Integer.valueOf(localCalendar.get(2) + 1)) + formatTime(Integer.valueOf(localCalendar.get(5)));
/* 233 */         break;
/*     */       case 10:
/* 235 */         str = toString(Integer.valueOf(localCalendar.get(2) + 1)) + toString(Integer.valueOf(localCalendar.get(5))) + toString(Integer.valueOf(localCalendar.get(11))) + toString(Integer.valueOf(localCalendar.get(12)));
/* 236 */         break;
/*     */       case 11:
/* 238 */         str = toString(Integer.valueOf(localCalendar.get(2) + 1)) + "-" + toString(Integer.valueOf(localCalendar.get(5))) + " " + toString(Integer.valueOf(localCalendar.get(11))) + ":" + toString(Integer.valueOf(localCalendar.get(12)));
/* 239 */         break;
/*     */       case 12:
/* 241 */         str = toString(Integer.valueOf(localCalendar.get(2) + 1)) + "/" + toString(Integer.valueOf(localCalendar.get(5))) + " " + toString(Integer.valueOf(localCalendar.get(11))) + ":" + toString(Integer.valueOf(localCalendar.get(12)));
/* 242 */         break;
/*     */       case 13:
/* 244 */         str = toString(Integer.valueOf(localCalendar.get(2) + 1)) + "." + toString(Integer.valueOf(localCalendar.get(5))) + " " + toString(Integer.valueOf(localCalendar.get(11))) + ":" + toString(Integer.valueOf(localCalendar.get(12)));
/* 245 */         break;
/*     */       case 14:
/* 247 */         str = formatTime(Integer.valueOf(localCalendar.get(2) + 1)) + "-" + formatTime(Integer.valueOf(localCalendar.get(5))) + " " + formatTime(Integer.valueOf(localCalendar.get(11))) + ":" + formatTime(Integer.valueOf(localCalendar.get(12)));
/* 248 */         break;
/*     */       case 15:
/* 250 */         str = formatTime(Integer.valueOf(localCalendar.get(2) + 1)) + "/" + formatTime(Integer.valueOf(localCalendar.get(5))) + " " + formatTime(Integer.valueOf(localCalendar.get(11))) + ":" + formatTime(Integer.valueOf(localCalendar.get(12)));
/* 251 */         break;
/*     */       case 16:
/* 253 */         str = formatTime(Integer.valueOf(localCalendar.get(2) + 1)) + "." + formatTime(Integer.valueOf(localCalendar.get(5))) + " " + formatTime(Integer.valueOf(localCalendar.get(11))) + ":" + formatTime(Integer.valueOf(localCalendar.get(12)));
/* 254 */         break;
/*     */       case 20:
/* 256 */         str = toString(Integer.valueOf(localCalendar.get(11))) + toString(Integer.valueOf(localCalendar.get(12))) + toString(Integer.valueOf(localCalendar.get(13)));
/* 257 */         break;
/*     */       case 21:
/* 259 */         str = toString(Integer.valueOf(localCalendar.get(11))) + ":" + toString(Integer.valueOf(localCalendar.get(12))) + ":" + toString(Integer.valueOf(localCalendar.get(13)));
/* 260 */         break;
/*     */       case 30:
/* 262 */         str = toString(Integer.valueOf(localCalendar.get(1))) + formatTime(Integer.valueOf(localCalendar.get(2) + 1)) + formatTime(Integer.valueOf(localCalendar.get(5))) + formatTime(Integer.valueOf(localCalendar.get(11))) + formatTime(Integer.valueOf(localCalendar.get(12))) + formatTime(Integer.valueOf(localCalendar.get(13)));
/* 263 */         break;
/*     */       case 100:
/* 265 */         str = toString(Integer.valueOf(localCalendar.get(1))) + "-" + formatTime(Integer.valueOf(localCalendar.get(2) + 1)) + "-" + formatTime(Integer.valueOf(localCalendar.get(5))) + " " + formatTime(Integer.valueOf(localCalendar.get(11))) + ":" + formatTime(Integer.valueOf(localCalendar.get(12))) + ":" + formatTime(Integer.valueOf(localCalendar.get(13)));
/*     */       }
/*     */     }
/*     */     catch (Exception localException) {
/*     */     }
/* 270 */     return str;
/*     */   }
/*     */ 
/*     */   public static String formatDouble(Double paramDouble, String paramString)
/*     */   {
/* 275 */     String str1 = "";
/* 276 */     Double localDouble = paramDouble;
/* 277 */     String str2 = paramString;
/* 278 */     DecimalFormat localDecimalFormat = new DecimalFormat(str2);
/* 279 */     str1 = localDecimalFormat.format(localDouble);
/* 280 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String formatTime(Integer paramInteger)
/*     */   {
/* 285 */     String str = "";
/* 286 */     Integer localInteger = paramInteger;
/* 287 */     if (localInteger.intValue() < 10) str = "0";
/* 288 */     str = str + toString(localInteger);
/* 289 */     return str;
/*     */   }
/*     */ 
/*     */   public static String formatText(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 294 */     String str1 = "";
/* 295 */     String str2 = paramString1;
/* 296 */     String str3 = paramString2;
/* 297 */     String str4 = paramString3;
/* 298 */     if (!(isEmpty(str2).booleanValue()))
/*     */     {
/* 300 */       String[] arrayOfString = str2.split(Pattern.quote(str4));
/* 301 */       for (int i = 0; i < arrayOfString.length; ++i)
/*     */       {
/* 303 */         str1 = str1 + str3;
/* 304 */         String str5 = getString(arrayOfString[i]);
/* 305 */         str1 = str1.replace("[text]", str5);
/* 306 */         str1 = str1.replace("[i]", toString(Integer.valueOf(i)));
/* 307 */         str1 = str1.replace("[text-htmlencode]", encode.htmlencode(str5));
/* 308 */         str1 = str1.replace("[text-base64encode]", encode.base64encode(str5.getBytes()));
/*     */       }
/*     */     }
/* 311 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String formatTextLine(String paramString1, String paramString2)
/*     */   {
/* 316 */     String str1 = "";
/* 317 */     String str2 = paramString1;
/* 318 */     String str3 = paramString2;
/* 319 */     str2 = str2.replace(String.valueOf('\r') + String.valueOf('\n'), String.valueOf('\n'));
/* 320 */     str2 = str2.replace(String.valueOf('\n'), String.valueOf('\r') + String.valueOf('\n'));
/* 321 */     String str4 = String.valueOf('\r') + String.valueOf('\n');
/* 322 */     if (!(isEmpty(str2).booleanValue()))
/*     */     {
/* 324 */       String[] arrayOfString = str2.split(Pattern.quote(str4));
/* 325 */       for (int i = 0; i < arrayOfString.length; ++i)
/*     */       {
/* 327 */         str1 = str1 + str3;
/* 328 */         String str5 = getString(arrayOfString[i]);
/* 329 */         str1 = str1.replace("[text]", str5);
/* 330 */         str1 = str1.replace("[i]", toString(Integer.valueOf(i)));
/* 331 */         str1 = str1.replace("[text-htmlencode]", encode.htmlencode(str5));
/* 332 */         str1 = str1.replace("[text-base64encode]", encode.base64encode(str5.getBytes()));
/*     */       }
/*     */     }
/* 335 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String formatUnixStampDate(Long paramLong)
/*     */   {
/* 340 */     String str1 = "";
/* 341 */     Long localLong = paramLong;
/* 342 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
/*     */     try
/*     */     {
/* 345 */       TimeZone localTimeZone = TimeZone.getTimeZone("UTC");
/* 346 */       Calendar localCalendar = Calendar.getInstance(localTimeZone);
/* 347 */       localCalendar.setTimeInMillis(localLong.longValue() * 1000L);
/* 348 */       String str2 = toString(Integer.valueOf(localCalendar.get(1))) + "-" + toString(Integer.valueOf(localCalendar.get(2) + 1)) + "-" + toString(Integer.valueOf(localCalendar.get(5))) + " " + toString(Integer.valueOf(localCalendar.get(11))) + ":" + toString(Integer.valueOf(localCalendar.get(12))) + ":" + toString(Integer.valueOf(localCalendar.get(13)));
/* 349 */       str1 = formatDate(str2);
/*     */     } catch (Exception localException) {
/*     */     }
/* 352 */     return str1;
/*     */   }
/*     */ 
/*     */   public static Object getAryValue(Object[][] paramArrayOfObject, String paramString)
/*     */   {
/* 357 */     Object localObject = null;
/* 358 */     Object[][] arrayOfObject = paramArrayOfObject;
/* 359 */     String str = paramString;
/* 360 */     for (int i = 0; i < arrayOfObject.length; ++i)
/*     */     {
/* 362 */       if (!(str.equals(toString(arrayOfObject[i][0]))))
/*     */         continue;
/* 364 */       localObject = arrayOfObject[i][1];
/* 365 */       break;
/*     */     }
/*     */ 
/* 368 */     return localObject;
/*     */   }
/*     */ 
/*     */   public static String getDate()
/*     */   {
/* 373 */     String str1 = "";
/* 374 */     TimeZone localTimeZone = TimeZone.getTimeZone("GMT+8");
/* 375 */     Calendar localCalendar = Calendar.getInstance(localTimeZone);
/* 376 */     String str2 = toString(Integer.valueOf(localCalendar.get(1))) + "-" + toString(Integer.valueOf(localCalendar.get(2) + 1)) + "-" + toString(Integer.valueOf(localCalendar.get(5))) + " " + toString(Integer.valueOf(localCalendar.get(11))) + ":" + toString(Integer.valueOf(localCalendar.get(12))) + ":" + toString(Integer.valueOf(localCalendar.get(13)));
/* 377 */     str1 = formatDate(str2);
/* 378 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String getDate(String paramString)
/*     */   {
/* 383 */     String str1 = paramString;
/* 384 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */     try
/*     */     {
/* 387 */       Date localDate = localSimpleDateFormat.parse(str1);
/* 388 */       Calendar localCalendar = Calendar.getInstance();
/* 389 */       localCalendar.setTime(localDate);
/* 390 */       String str2 = toString(Integer.valueOf(localCalendar.get(1))) + "-" + toString(Integer.valueOf(localCalendar.get(2) + 1)) + "-" + toString(Integer.valueOf(localCalendar.get(5))) + " " + toString(Integer.valueOf(localCalendar.get(11))) + ":" + toString(Integer.valueOf(localCalendar.get(12))) + ":" + toString(Integer.valueOf(localCalendar.get(13)));
/* 391 */       str1 = formatDate(str2);
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 395 */       str1 = getDate();
/*     */     }
/* 397 */     return str1;
/*     */   }
/*     */ 
/*     */   public static Double getDouble(String paramString)
/*     */   {
/* 402 */     Double localDouble = Double.valueOf(0.0D);
/* 403 */     String str = paramString;
/* 404 */     localDouble = getDouble(str, Double.valueOf(0.0D));
/* 405 */     return localDouble;
/*     */   }
/*     */ 
/*     */   public static Double getDouble(String paramString, Double paramDouble)
/*     */   {
/* 410 */     String str = paramString;
/* 411 */     Double localDouble1 = paramDouble;
/* 412 */     Double localDouble2 = localDouble1;
/*     */     try
/*     */     {
/* 415 */       localDouble2 = Double.valueOf(Double.parseDouble(str));
/*     */     } catch (Exception localException) {
/*     */     }
/* 418 */     return localDouble2;
/*     */   }
/*     */ 
/*     */   public static String getRandomString(int paramInt)
/*     */   {
/* 423 */     String str1 = "";
/* 424 */     int i = paramInt;
/* 425 */     String str2 = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
/* 426 */     String[] arrayOfString = str2.split(Pattern.quote(","));
/* 427 */     Random localRandom = new Random();
/* 428 */     for (int j = 0; j < i; ++j)
/*     */     {
/* 430 */       int k = localRandom.nextInt(arrayOfString.length);
/* 431 */       str1 = str1 + arrayOfString[k];
/*     */     }
/* 433 */     return str1;
/*     */   }
/*     */ 
/*     */   public static Long getUnixStamp()
/*     */   {
/* 438 */     String str = getDate();
/* 439 */     Long localLong = getUnixStamp(str);
/* 440 */     return localLong;
/*     */   }
/*     */ 
/*     */   public static Long getUnixStamp(String paramString)
/*     */   {
/* 445 */     Long localLong = Long.valueOf(0L);
/* 446 */     String str1 = paramString;
/* 447 */     String str2 = getDate(str1);
/* 448 */     SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */     try
/*     */     {
/* 451 */       Date localDate1 = localSimpleDateFormat.parse(str2);
/* 452 */       Date localDate2 = localSimpleDateFormat.parse("1970-1-1 0:00:00");
/* 453 */       localLong = Long.valueOf((localDate1.getTime() - localDate2.getTime()) / 1000L);
/*     */     } catch (Exception localException) {
/*     */     }
/* 456 */     return localLong;
/*     */   }
/*     */ 
/*     */   public static String getHstr(String paramString1, String paramString2)
/*     */   {
/* 461 */     Object localObject = "";
/* 462 */     String str1 = getString(paramString1);
/* 463 */     String str2 = getString(paramString2);
/* 464 */     localObject = str1;
/* 465 */     if (isEmpty(localObject).booleanValue()) localObject = str2;
/* 466 */     return ((String)localObject);
/*     */   }
/*     */ 
/*     */   public static String getLeft(String paramString, Integer paramInteger)
/*     */   {
/* 471 */     String str1 = "";
/* 472 */     String str2 = paramString;
/* 473 */     Integer localInteger = paramInteger;
/* 474 */     str1 = getLeft(str2, localInteger, "");
/* 475 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String getLeft(String paramString1, Integer paramInteger, String paramString2)
/*     */   {
/* 480 */     String str1 = "";
/* 481 */     Integer localInteger = paramInteger;
/* 482 */     String str2 = getString(paramString1);
/* 483 */     String str3 = getString(paramString2);
/* 484 */     if (!(isEmpty(str2).booleanValue()))
/*     */     {
/* 486 */       if (localInteger.intValue() > str2.length()) localInteger = Integer.valueOf(str2.length());
/* 487 */       str1 = str2.substring(0, localInteger.intValue());
/*     */     }
/* 489 */     if (!(str1.equals(str2))) str1 = str1 + str3;
/* 490 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String getLeftB(String paramString, Integer paramInteger)
/*     */   {
/* 495 */     String str1 = "";
/* 496 */     String str2 = paramString;
/* 497 */     Integer localInteger = paramInteger;
/* 498 */     str1 = getLeftB(str2, localInteger, "");
/* 499 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String getLeftB(String paramString1, Integer paramInteger, String paramString2)
/*     */   {
/* 504 */     String str1 = "";
/* 505 */     Integer localInteger1 = paramInteger;
/* 506 */     String str2 = getString(paramString1);
/* 507 */     String str3 = getString(paramString2);
/* 508 */     if (!(isEmpty(str2).booleanValue()))
/*     */     {
/* 510 */       Integer localInteger2 = Integer.valueOf(0);
/* 511 */       for (int i = 0; i < str2.length(); ++i)
/*     */       {
/* 513 */         String str4 = str2.substring(i, i + 1);
/* 514 */         if (validator.isChinese(str4).booleanValue())
/*     */         {
/* 516 */           localInteger2 = Integer.valueOf(localInteger2.intValue() + 2);
/*     */         }
/*     */         else
/*     */         {
/* 520 */           localInteger2 = Integer.valueOf(localInteger2.intValue() + 1);
/*     */         }
/* 522 */         if (localInteger2.intValue() > localInteger1.intValue()) break; str1 = str1 + str4;
/*     */       }
/*     */     }
/*     */ 
/* 526 */     if (!(str1.equals(str2))) str1 = str1 + str3;
/* 527 */     return str1;
/*     */   }
/*     */ 
/*     */   public static Integer getNum(String paramString)
/*     */   {
/* 532 */     String str = paramString;
/* 533 */     Integer localInteger = getNum(str, Integer.valueOf(0));
/* 534 */     return localInteger;
/*     */   }
/*     */ 
/*     */   public static Integer getNum(String paramString, Integer paramInteger)
/*     */   {
/* 539 */     String str = paramString;
/* 540 */     Integer localInteger1 = paramInteger;
/* 541 */     Integer localInteger2 = localInteger1;
/*     */     try
/*     */     {
/* 544 */       localInteger2 = Integer.valueOf(Integer.parseInt(str));
/*     */     } catch (Exception localException) {
/*     */     }
/* 547 */     return localInteger2;
/*     */   }
/*     */ 
/*     */   public static Long getNum64(String paramString)
/*     */   {
/* 552 */     String str = paramString;
/* 553 */     Long localLong = getNum64(str, Long.valueOf(0L));
/* 554 */     return localLong;
/*     */   }
/*     */ 
/*     */   public static Long getNum64(String paramString, Long paramLong)
/*     */   {
/* 559 */     String str = paramString;
/* 560 */     Long localLong1 = paramLong;
/* 561 */     Long localLong2 = localLong1;
/*     */     try
/*     */     {
/* 564 */       localLong2 = Long.valueOf(Long.parseLong(str));
/*     */     } catch (Exception localException) {
/*     */     }
/* 567 */     return localLong2;
/*     */   }
/*     */ 
/*     */   public static String getRight(String paramString, Integer paramInteger)
/*     */   {
/* 572 */     String str1 = "";
/* 573 */     Integer localInteger1 = paramInteger;
/* 574 */     String str2 = getString(paramString);
/* 575 */     if (!(isEmpty(str2).booleanValue()))
/*     */     {
/* 577 */       Integer localInteger2 = Integer.valueOf(str2.length());
/* 578 */       Integer localInteger3 = Integer.valueOf(localInteger2.intValue() - localInteger1.intValue());
/* 579 */       if (localInteger3.intValue() < 0) localInteger3 = Integer.valueOf(0);
/* 580 */       str1 = str2.substring(localInteger3.intValue(), localInteger2.intValue());
/*     */     }
/* 582 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String getRepeatedString(String paramString, Integer paramInteger)
/*     */   {
/* 587 */     String str1 = paramString;
/* 588 */     Integer localInteger = paramInteger;
/* 589 */     String str2 = "";
/* 590 */     for (int i = 1; i < localInteger.intValue(); ++i) str2 = str2 + str1;
/* 591 */     return str2;
/*     */   }
/*     */ 	/*
 * 			把字符串转成非null值，方便程序开发的转化。
 * 			*/
/*     */   public static String getString(String paramString)
/*     */   {
/* 596 */     String str = paramString;
/* 597 */     if (str == null) str = "";
/* 598 */     return str;
/*     */   }
/*     */ 
/*     */   public static String getSafeString(String paramString)
/*     */   {
/* 603 */     String str = paramString;
/* 604 */     str = getString(str);
/* 605 */     str = str.replace("'", "");
/* 606 */     str = str.replace(";", "");
/* 607 */     str = str.replace("--", "");
/* 608 */     return str;
/*     */   }
/*     */ 
/*     */   public static String getLRStr(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 613 */     Object localObject = "";
/* 614 */     String str1 = paramString1;
/* 615 */     String str2 = paramString2;
/* 616 */     String str3 = paramString3;
/* 617 */     if ((isEmpty(str2).booleanValue()) || (str1.indexOf(str2) == -1)) localObject = str1;
/* 620 */     else if (str3.equals("left")) localObject = str1.substring(0, str1.indexOf(str2));
/* 621 */     else if (str3.equals("leftr")) localObject = str1.substring(0, str1.lastIndexOf(str2));
/* 622 */     else if (str3.equals("right")) localObject = str1.substring(str1.lastIndexOf(str2) + str2.length(), str1.length());
/* 623 */     else if (str3.equals("rightr")) localObject = str1.substring(str1.indexOf(str2) + str2.length(), str1.length());
/*     */     else localObject = str1;
/*     */ 
/* 626 */     return ((String)localObject);
/*     */   }
/*     */ 
/*     */   public static String getParameter(String paramString1, String paramString2)
/*     */   {
/* 631 */     String str1 = "";
/* 632 */     String str2 = paramString1;
/* 633 */     String str3 = paramString2;
/* 634 */     str1 = getParameter(str2, str3, ";");
/* 635 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String getParameter(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 640 */     String str1 = "";
/* 641 */     String str2 = paramString1;
/* 642 */     String str3 = paramString2;
/* 643 */     String str4 = paramString3;
/* 644 */     Pattern localPattern = Pattern.compile("((?:^|" + str4 + ")" + str3 + "=(.[^" + str4 + "]*))");
/* 645 */     Matcher localMatcher = localPattern.matcher(str2);
/* 646 */     if (localMatcher.find()) str1 = localMatcher.group(2);
/* 647 */     return str1;
/*     */   }
/*     */ 
/*     */   public static Boolean isEmpty(Object paramObject)
/*     */   {
/* 652 */     Boolean localBoolean = Boolean.valueOf(false);
/* 653 */     Object localObject = paramObject;
/* 654 */     String str = toString(localObject);
/* 655 */     if (str.equals("")) localBoolean = Boolean.valueOf(true);
/* 656 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   public static Object[] mergeAry(Object[] paramArrayOfObject, Object[][] paramArrayOfObject1)
/*     */   {
/* 661 */     Object[] arrayOfObject1 = null;
/* 662 */     Object[] arrayOfObject2 = paramArrayOfObject;
/* 663 */     Object[][] arrayOfObject = paramArrayOfObject1;
/* 664 */     if (arrayOfObject2 == null)
/*     */     {
/* 666 */       arrayOfObject1 = new Object[1];
/* 667 */       arrayOfObject1[0] = arrayOfObject;
/*     */     }
/*     */     else
/*     */     {
/* 671 */       int i = arrayOfObject2.length;
/* 672 */       arrayOfObject1 = new Object[i + 1];
/* 673 */       for (int j = 0; j < i; ++j)
/*     */       {
/* 675 */         arrayOfObject1[j] = arrayOfObject2[j];
/*     */       }
/* 677 */       arrayOfObject1[(arrayOfObject1.length - 1)] = arrayOfObject;
/*     */     }
/* 679 */     return arrayOfObject1;
/*     */   }
/*     */ 
/*     */   public static String[][] mergeAry(String[][] paramArrayOfString1, String[][] paramArrayOfString2)
/*     */   {
/* 684 */     Object localObject = (String[][])null;
/* 685 */     String[][] arrayOfString1 = paramArrayOfString1;
/* 686 */     String[][] arrayOfString2 = paramArrayOfString2;
/* 687 */     if (arrayOfString1 == null) localObject = arrayOfString2;
/* 688 */     if (arrayOfString2 == null) localObject = arrayOfString1;
/* 689 */     if (localObject == null)
/*     */     {
/* 691 */       Integer localInteger1 = Integer.valueOf(arrayOfString1.length);
/* 692 */       Integer localInteger2 = Integer.valueOf(arrayOfString1[0].length);
/* 693 */       Integer localInteger3 = Integer.valueOf(arrayOfString2.length);
/* 694 */       Integer localInteger4 = Integer.valueOf(arrayOfString2[0].length);
/* 695 */       if (localInteger2.equals(localInteger4))
/*     */       {
/*     */         int j,i;
/* 697 */         localObject = new String[localInteger1.intValue() + localInteger3.intValue()][localInteger2.intValue()];
/* 698 */         for (i = 0; i < localInteger1.intValue(); ++i)
/*     */         {
/* 700 */           for (j = 0; j < localInteger2.intValue(); ++j)
/*     */           {
/* 702 */            ((String[][])localObject)[i][j] = arrayOfString1[i][j];
/*     */           }
/*     */         }
/* 705 */         for (i = 0; i < localInteger3.intValue(); ++i)
/*     */         {
/* 707 */           for (j = 0; j < localInteger4.intValue(); ++j)
/*     */           {
/* 709 */             ((String[][])localObject)[(i + localInteger1.intValue())][j] = arrayOfString2[i][j];
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 714 */     return ((String[][])localObject);
/*     */   }
/*     */ 
/*     */   public static String replace(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 719 */     String str1 = getString(paramString1);
/* 720 */     String str2 = getString(paramString2);
/* 721 */     String str3 = getString(paramString3);
/* 722 */     if (!(isEmpty(str1).booleanValue())) str1 = str1.replace(str2, str3);
/* 723 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String reparameter(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 728 */     String str1 = "";
/* 729 */     String str2 = paramString1;
/* 730 */     String str3 = paramString2;
/* 731 */     String str4 = paramString3;
/* 732 */     if (isEmpty(str2).booleanValue()) { str1 = str3 + "=" + str4;
/*     */     }
/*     */     else {
/* 735 */       String str5 = "&" + str2;
/* 736 */       if (str5.indexOf("&" + str3 + "=") == -1) { str1 = str2 + "&" + str3 + "=" + str4;
/*     */       }
/*     */       else {
/* 739 */         String str6 = getLRStr(str5, "&" + str3 + "=", "rightr");
/* 740 */         str6 = getLRStr(str6, "&", "left");
/* 741 */         str1 = str5.replace("&" + str3 + "=" + str6, "&" + str3 + "=" + str4);
/* 742 */         str1 = getLRStr(str1, "&", "rightr");
/*     */       }
/*     */     }
/* 745 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String toString(Object paramObject)
/*     */   {
/* 750 */     String str = "";
/* 751 */     Object localObject = paramObject;
/* 752 */     if (localObject != null) str = localObject.toString();
/* 753 */     return str;
/*     */   }
/*     */ }