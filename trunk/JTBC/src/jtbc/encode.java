/*     */ package jtbc;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.MessageDigest;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class encode
/*     */ {
/*     */   public static String addslashes(String paramString)
/*     */   {
/*  12 */     String str = paramString;
/*  13 */     str = str.replace("'", "''");
/*  14 */     return str;
/*     */   }
/*     */ 
/*     */   public static String base64encode(byte[] paramArrayOfByte)
/*     */   {
/*  19 */     String str = "";
/*  20 */     byte[] arrayOfByte = paramArrayOfByte;
/*  21 */     char[] arrayOfChar = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
/*  22 */     StringBuffer localStringBuffer = new StringBuffer();
/*  23 */     int i = arrayOfByte.length;
/*  24 */     int j = 0;
/*     */ 
/*  26 */     while (j < i)
/*     */     {
/*  28 */       int k = arrayOfByte[(j++)] & 0xFF;
/*  29 */       if (j == i)
/*     */       {
/*  31 */         localStringBuffer.append(arrayOfChar[(k >>> 2)]);
/*  32 */         localStringBuffer.append(arrayOfChar[((k & 0x3) << 4)]);
/*  33 */         localStringBuffer.append("==");
/*  34 */         break;
/*     */       }
/*  36 */       int l = arrayOfByte[(j++)] & 0xFF;
/*  37 */       if (j == i)
/*     */       {
/*  39 */         localStringBuffer.append(arrayOfChar[(k >>> 2)]);
/*  40 */         localStringBuffer.append(arrayOfChar[((k & 0x3) << 4 | (l & 0xF0) >>> 4)]);
/*  41 */         localStringBuffer.append(arrayOfChar[((l & 0xF) << 2)]);
/*  42 */         localStringBuffer.append("=");
/*  43 */         break;
/*     */       }
/*  45 */       int i1 = arrayOfByte[(j++)] & 0xFF;
/*  46 */       localStringBuffer.append(arrayOfChar[(k >>> 2)]);
/*  47 */       localStringBuffer.append(arrayOfChar[((k & 0x3) << 4 | (l & 0xF0) >>> 4)]);
/*  48 */       localStringBuffer.append(arrayOfChar[((l & 0xF) << 2 | (i1 & 0xC0) >>> 6)]);
/*  49 */       localStringBuffer.append(arrayOfChar[(i1 & 0x3F)]);
/*     */     }
/*  51 */     str = localStringBuffer.toString();
/*  52 */     return str;
/*     */   }
/*     */ 
/*     */   public static byte[] base64decode(String paramString) throws UnsupportedEncodingException
/*     */   {
/*  57 */     byte[] arrayOfByte1 = null;
/*  58 */     String str = paramString;
/*  59 */     byte[] arrayOfByte2 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };
/*  60 */     StringBuffer localStringBuffer = new StringBuffer();
/*  61 */     byte[] arrayOfByte3 = str.getBytes("US-ASCII");
/*  62 */     int i = arrayOfByte3.length;
/*  63 */     int j = 0;
/*     */ 
/*  65 */     while (j < i) { int k;
/*     */       int l;
/*     */       int i1;
/*     */       int i2;
/*     */       do k = arrayOfByte2[arrayOfByte3[(j++)]];
/*  70 */       while ((j < i) && (k == -1));
/*  71 */       if (k == -1)
/*     */         break;
/*     */       do
/*  74 */         l = arrayOfByte2[arrayOfByte3[(j++)]];
/*  75 */       while ((j < i) && (l == -1));
/*  76 */       if (l == -1) break;
/*  77 */       localStringBuffer.append((char)(k << 2 | (l & 0x30) >>> 4));
/*     */       do
/*     */       {
/*  80 */         i1 = arrayOfByte3[(j++)];
/*  81 */         if (i1 == 61)
/*     */         {
/*  83 */           arrayOfByte1 = localStringBuffer.toString().getBytes("ISO-8859-1");
/*  84 */           return arrayOfByte1;
/*     */         }
/*  86 */         i1 = arrayOfByte2[i1]; }
/*  87 */       while ((j < i) && (i1 == -1));
/*  88 */       if (i1 == -1) break;
/*  89 */       localStringBuffer.append((char)((l & 0xF) << 4 | (i1 & 0x3C) >>> 2));
/*     */       do
/*     */       {
/*  92 */         i2 = arrayOfByte3[(j++)];
/*  93 */         if (i2 == 61)
/*     */         {
/*  95 */           arrayOfByte1 = localStringBuffer.toString().getBytes("ISO-8859-1");
/*  96 */           return arrayOfByte1;
/*     */         }
/*  98 */         i2 = arrayOfByte2[i2]; }
/*  99 */       while ((j < i) && (i2 == -1));
/* 100 */       if (i2 == -1) break;
/* 101 */       localStringBuffer.append((char)((i1 & 0x3) << 6 | i2));
/*     */     }
/* 103 */     arrayOfByte1 = localStringBuffer.toString().getBytes("ISO-8859-1");
/* 104 */     return arrayOfByte1;
/*     */   }
/*     */ 
/*     */   public static String cachenameencode(String paramString)
/*     */   {
/* 109 */     String str = paramString;
/* 110 */     str = str.replace("/", "_");
/* 111 */     return str;
/*     */   }
/*     */ 
/*     */   public static String cdatadecode(String paramString)
/*     */   {
/* 116 */     String str = paramString;
/* 117 */     if (!(cls.isEmpty(str).booleanValue()))
/*     */     {
/* 119 */       str = str.replace("$CDATA#", "<![CDATA[");
/* 120 */       str = str.replace("#CDATA$", "]]>");
/*     */     }
/* 122 */     return str;
/*     */   }
/*     */ 
/*     */   public static String encodeNewline(String paramString)
/*     */   {
/* 127 */     String str = paramString;
/* 128 */     str = str.replace(String.valueOf('\r') + String.valueOf('\n'), String.valueOf('\n'));
/* 129 */     str = str.replace(String.valueOf('\n'), String.valueOf('\r') + String.valueOf('\n'));
/* 130 */     return str;
/*     */   }
/*     */ 
/*     */   public static String encodeArticle(String paramString)
/*     */   {
/* 135 */     String str = paramString;
/* 136 */     str = encodeNewline(str);
/* 137 */     str = str.replace(String.valueOf('\''), "&#39;");
/* 138 */     str = str.replace(String.valueOf(' ') + String.valueOf(' '), "&nbsp;&nbsp;");
/* 139 */     str = str.replace(String.valueOf('\r') + String.valueOf('\n'), "<br />");
/* 140 */     return str;
/*     */   }
/*     */ 
/*     */   public static String encodeChar2String(String paramString)
/*     */   {
/* 145 */     String str1 = paramString;
/* 146 */     String str2 = "";
/* 147 */     if (!(cls.isEmpty(str1).booleanValue()))
/*     */     {
/* 149 */       String[] arrayOfString = str1.split(Pattern.quote(","));
/* 150 */       for (int i = 0; i < arrayOfString.length; ++i)
/*     */       {
/* 152 */         int j = cls.getNum(arrayOfString[i], Integer.valueOf(0)).intValue();
/* 153 */         str2 = str2 + String.valueOf((char)j);
/*     */       }
/*     */     }
/* 156 */     return str2;
/*     */   }
/*     */ 
/*     */   public static String encodeHtml(String paramString)
/*     */   {
/* 161 */     String str = paramString;
/* 162 */     str = str.replace("&", "&amp;");
/* 163 */     str = str.replace(">", "&gt;");
/* 164 */     str = str.replace("<", "&lt;");
/* 165 */     str = str.replace("\"", "&quot;");
/* 166 */     return str;
/*     */   }
/*     */ 
/*     */   public static String encodeText(String paramString)
/*     */   {
/* 171 */     String str = paramString;
/* 172 */     str = str.replace("$", "&#36;");
/* 173 */     return str;
/*     */   }
/*     */ 
/*     */   public static String htmlencode(String paramString)
/*     */   {
/* 178 */     String str1 = "";
/* 179 */     String str2 = paramString;
/* 180 */     str1 = htmlencode(str2, "0");
/* 181 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String htmlencode(String paramString1, String paramString2)
/*     */   {
/* 186 */     String str1 = paramString2;
/* 187 */     String str2 = paramString1;
/* 188 */     str2 = cls.getString(str2);
/* 189 */     str2 = encodeHtml(str2);
/* 190 */     str2 = encodeText(str2);
/* 191 */     if (str1.equals("1"))
/*     */     {
/* 193 */       str2 = str2.replace("|", "&#5;");
/* 194 */       str2 = str2.replace("=", "&#61;");
/*     */     }
/* 196 */     return str2;
/*     */   }
/*     */ 
/*     */   public static String keywordencode(String paramString)
/*     */   {
/* 201 */     String str1 = "";
/* 202 */     String str2 = paramString;
/* 203 */     if (!(cls.isEmpty(str2).booleanValue()))
/*     */     {
/* 205 */       String[] arrayOfString = str2.split(Pattern.quote(","));
/* 206 */       for (int i = 0; i < arrayOfString.length; ++i) str1 = str1 + "|:|" + arrayOfString[i] + "|:|,";
/* 207 */       if (!(cls.isEmpty(str1).booleanValue())) str1 = cls.getLRStr(str1, ",", "leftr");
/*     */     }
/* 209 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String keyworddecode(String paramString)
/*     */   {
/* 214 */     String str1 = "";
/* 215 */     String str2 = paramString;
/* 216 */     if (!(cls.isEmpty(str2).booleanValue()))
/*     */     {
/* 218 */       String[] arrayOfString = str2.split(Pattern.quote(","));
/* 219 */       for (int i = 0; i < arrayOfString.length; ++i) str1 = str1 + cls.getLRStr(cls.getLRStr(arrayOfString[i], "|:|", "leftr"), "|:|", "rightr") + ",";
/* 220 */       if (!(cls.isEmpty(str1).booleanValue())) str1 = cls.getLRStr(str1, ",", "leftr");
/*     */     }
/* 222 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String md5(byte[] paramArrayOfByte)
/*     */   {
/* 227 */     String str = "";
/* 228 */     byte[] arrayOfByte1 = paramArrayOfByte;
/* 229 */     char[] arrayOfChar1 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*     */     try
/*     */     {
/* 232 */       MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
/* 233 */       localMessageDigest.update(arrayOfByte1);
/* 234 */       byte[] arrayOfByte2 = localMessageDigest.digest();
/* 235 */       char[] arrayOfChar2 = new char[32];
/* 236 */       int i = 0;
/* 237 */       for (int j = 0; j < 16; ++j)
/*     */       {
/* 239 */         int k = arrayOfByte2[j];
/* 240 */         arrayOfChar2[(i++)] = arrayOfChar1[(k >>> 4 & 0xF)];
/* 241 */         arrayOfChar2[(i++)] = arrayOfChar1[(k & 0xF)];
/*     */       }
/* 243 */       str = new String(arrayOfChar2);
/*     */     } catch (Exception localException) {
/*     */     }
/* 246 */     return str;
/*     */   }
/*     */ 
/*     */   public static String scriptencode(String paramString)
/*     */   {
/* 251 */     String str = paramString;
/* 252 */     str = str.replace("\\", "\\\\");
/* 253 */     str = str.replace("'", "\\'");
/* 254 */     str = str.replace("\"", "\\\"");
/* 255 */     str = encodeText(str);
/* 256 */     return str;
/*     */   }
/*     */ 
/*     */   public static String unescape(String paramString)
/*     */   {
/* 261 */     String str1 = "";
/* 262 */     String str2 = paramString;
/* 263 */     if (!(cls.isEmpty(str2).booleanValue()))
/*     */     {
/* 265 */       byte[] arrayOfByte = { 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 63, 63, 63, 63, 63, 63, 63, 10, 11, 12, 13, 14, 15, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 10, 11, 12, 13, 14, 15, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63 };
/* 266 */       StringBuffer localStringBuffer = new StringBuffer();
/* 267 */       int i = 0;
/* 268 */       int j = str2.length();
/* 269 */       while (i < j)
/*     */       {
/* 271 */         int k = str2.charAt(i);
/* 272 */         if ((65 <= k) && (k <= 90))
/*     */         {
/* 274 */           localStringBuffer.append((char)k);
/*     */         }
/* 276 */         else if ((97 <= k) && (k <= 122))
/*     */         {
/* 278 */           localStringBuffer.append((char)k);
/*     */         }
/* 280 */         else if ((48 <= k) && (k <= 57))
/*     */         {
/* 282 */           localStringBuffer.append((char)k);
/*     */         }
/* 284 */         else if ((k == 45) || (k == 95) || (k == 46) || (k == 33) || (k == 126) || (k == 42) || (k == 39) || (k == 40) || (k == 41))
/*     */         {
/* 286 */           localStringBuffer.append((char)k);
/*     */         }
/* 288 */         else if (k == 37)
/*     */         {
/* 290 */           int l = 0;
/* 291 */           if ('u' != str2.charAt(i + 1))
/*     */           {
/* 293 */             l = l << 4 | arrayOfByte[str2.charAt(i + 1)];
/* 294 */             l = l << 4 | arrayOfByte[str2.charAt(i + 2)];
/* 295 */             i += 2;
/*     */           }
/*     */           else
/*     */           {
/* 299 */             l = l << 4 | arrayOfByte[str2.charAt(i + 2)];
/* 300 */             l = l << 4 | arrayOfByte[str2.charAt(i + 3)];
/* 301 */             l = l << 4 | arrayOfByte[str2.charAt(i + 4)];
/* 302 */             l = l << 4 | arrayOfByte[str2.charAt(i + 5)];
/* 303 */             i += 5;
/*     */           }
/* 305 */           localStringBuffer.append((char)l);
/*     */         }
/*     */         else
/*     */         {
/* 309 */           localStringBuffer.append((char)k);
/*     */         }
/* 311 */         ++i;
/*     */       }
/* 313 */       str1 = localStringBuffer.toString();
/*     */     }
/* 315 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String ubb2html(String paramString)
/*     */   {
/* 320 */     String str1 = paramString;
/* 321 */     if (!(cls.isEmpty(str1).booleanValue()))
/*     */     {
/* 323 */       Boolean localBoolean = Boolean.valueOf(true);
/* 324 */       str1 = htmlencode(str1);
/* 325 */       str1 = str1.replace(" ", "&nbsp;");
/* 326 */       str1 = str1.replace("&nbsp;&nbsp;", "[&nbsp-nbsp;]");
/* 327 */       str1 = str1.replace("&nbsp;", " ");
/* 328 */       str1 = str1.replace("[&nbsp-nbsp;]", "&nbsp; ");
/* 329 */       str1 = str1.replaceAll("\\[br\\]", "<br />");
/* 330 */       String[][] arrayOfString = { { "\\[p\\]([^\\[]*?)\\[\\/p\\]", "$1<br />" }, { "\\[b\\]([^\\[]*?)\\[\\/b\\]", "<b>$1</b>" }, { "\\[i\\]([^\\[]*?)\\[\\/i\\]", "<i>$1</i>" }, { "\\[u\\]([^\\[]*?)\\[\\/u\\]", "<u>$1</u>" }, { "\\[ol\\]([^\\[]*?)\\[\\/ol\\]", "<ol>$1</ol>" }, { "\\[ul\\]([^\\[]*?)\\[\\/ul\\]", "<ul>$1</ul>" }, { "\\[li\\]([^\\[]*?)\\[\\/li\\]", "<li>$1</li>" }, { "\\[code\\]([^\\[]*?)\\[\\/code\\]", "<div class=\"ubb_code\">$1</div>" }, { "\\[quote\\]([^\\[]*?)\\[\\/quote\\]", "<div class=\"ubb_quote\">$1</div>" }, { "\\[color=([^\\]]*)\\]([^\\[]*?)\\[\\/color\\]", "<font style=\"color: $1\">$2</font>" }, { "\\[hilitecolor=([^\\]]*)\\]([^\\[]*?)\\[\\/hilitecolor\\]", "<font style=\"background-color: $1\">$2</font>" }, { "\\[align=([^\\]]*)\\]([^\\[]*?)\\[\\/align\\]", "<div style=\"text-align: $1\">$2</div>" }, { "\\[url=([^\\]]*)\\]([^\\[]*?)\\[\\/url\\]", "<a href=\"$1\" target=\"_blank\">$2</a>" }, { "\\[img\\]([^\\[]*?)\\[\\/img\\]", "<img src=\"$1\" onload=\"cls.img.tResize(this, 600, 800);\" />" } };
/*     */ 
/* 346 */       while (localBoolean.booleanValue())
/*     */       {
/* 348 */         localBoolean = Boolean.valueOf(false);
/* 349 */         for (int i = 0; i < arrayOfString.length; ++i)
/*     */         {
/* 352 */           Pattern localPattern = Pattern.compile(arrayOfString[i][0]);
/* 353 */           Matcher localMatcher = localPattern.matcher(str1);
/* 354 */           while (localMatcher.find())
/*     */           {
/* 356 */             localBoolean = Boolean.valueOf(true);
/* 357 */             String str2 = localMatcher.group();
/* 358 */             String str3 = arrayOfString[i][1];
/* 359 */             for (int j = 1; j < localMatcher.groupCount() + 1; ++j) str3 = str3.replace("$" + j, localMatcher.group(j));
/* 360 */             str1 = str1.replace(str2, str3);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 365 */     return str1;
/*     */   }
/*     */ }