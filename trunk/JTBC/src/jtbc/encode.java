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
/*  14 */     str = str.replace("\\", "\\\\");
/*  15 */     return str;
/*     */   }
/*     */ 
/*     */   public static String base64encode(byte[] paramArrayOfByte)
/*     */   {
/*  20 */     String str = "";
/*  21 */     byte[] arrayOfByte = paramArrayOfByte;
/*  22 */     char[] arrayOfChar = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
/*  23 */     StringBuffer localStringBuffer = new StringBuffer();
/*  24 */     int i = arrayOfByte.length;
/*  25 */     int j = 0;
/*     */ 
/*  27 */     while (j < i)
/*     */     {
/*  29 */       int k = arrayOfByte[(j++)] & 0xFF;
/*  30 */       if (j == i)
/*     */       {
/*  32 */         localStringBuffer.append(arrayOfChar[(k >>> 2)]);
/*  33 */         localStringBuffer.append(arrayOfChar[((k & 0x3) << 4)]);
/*  34 */         localStringBuffer.append("==");
/*  35 */         break;
/*     */       }
/*  37 */       int l = arrayOfByte[(j++)] & 0xFF;
/*  38 */       if (j == i)
/*     */       {
/*  40 */         localStringBuffer.append(arrayOfChar[(k >>> 2)]);
/*  41 */         localStringBuffer.append(arrayOfChar[((k & 0x3) << 4 | (l & 0xF0) >>> 4)]);
/*  42 */         localStringBuffer.append(arrayOfChar[((l & 0xF) << 2)]);
/*  43 */         localStringBuffer.append("=");
/*  44 */         break;
/*     */       }
/*  46 */       int i1 = arrayOfByte[(j++)] & 0xFF;
/*  47 */       localStringBuffer.append(arrayOfChar[(k >>> 2)]);
/*  48 */       localStringBuffer.append(arrayOfChar[((k & 0x3) << 4 | (l & 0xF0) >>> 4)]);
/*  49 */       localStringBuffer.append(arrayOfChar[((l & 0xF) << 2 | (i1 & 0xC0) >>> 6)]);
/*  50 */       localStringBuffer.append(arrayOfChar[(i1 & 0x3F)]);
/*     */     }
/*  52 */     str = localStringBuffer.toString();
/*  53 */     return str;
/*     */   }
/*     */ 
/*     */   public static byte[] base64decode(String paramString) throws UnsupportedEncodingException
/*     */   {
/*  58 */     byte[] arrayOfByte1 = null;
/*  59 */     String str = paramString;
/*  60 */     byte[] arrayOfByte2 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };
/*  61 */     StringBuffer localStringBuffer = new StringBuffer();
/*  62 */     byte[] arrayOfByte3 = str.getBytes("US-ASCII");
/*  63 */     int i = arrayOfByte3.length;
/*  64 */     int j = 0;
/*     */ 
/*  66 */     while (j < i) { int k;
/*     */       int l;
/*     */       int i1;
/*     */       int i2;
/*     */       do k = arrayOfByte2[arrayOfByte3[(j++)]];
/*  71 */       while ((j < i) && (k == -1));
/*  72 */       if (k == -1)
/*     */         break;
/*     */       do
/*  75 */         l = arrayOfByte2[arrayOfByte3[(j++)]];
/*  76 */       while ((j < i) && (l == -1));
/*  77 */       if (l == -1) break;
/*  78 */       localStringBuffer.append((char)(k << 2 | (l & 0x30) >>> 4));
/*     */       do
/*     */       {
/*  81 */         i1 = arrayOfByte3[(j++)];
/*  82 */         if (i1 == 61)
/*     */         {
/*  84 */           arrayOfByte1 = localStringBuffer.toString().getBytes("ISO-8859-1");
/*  85 */           return arrayOfByte1;
/*     */         }
/*  87 */         i1 = arrayOfByte2[i1]; }
/*  88 */       while ((j < i) && (i1 == -1));
/*  89 */       if (i1 == -1) break;
/*  90 */       localStringBuffer.append((char)((l & 0xF) << 4 | (i1 & 0x3C) >>> 2));
/*     */       do
/*     */       {
/*  93 */         i2 = arrayOfByte3[(j++)];
/*  94 */         if (i2 == 61)
/*     */         {
/*  96 */           arrayOfByte1 = localStringBuffer.toString().getBytes("ISO-8859-1");
/*  97 */           return arrayOfByte1;
/*     */         }
/*  99 */         i2 = arrayOfByte2[i2]; }
/* 100 */       while ((j < i) && (i2 == -1));
/* 101 */       if (i2 == -1) break;
/* 102 */       localStringBuffer.append((char)((i1 & 0x3) << 6 | i2));
/*     */     }
/* 104 */     arrayOfByte1 = localStringBuffer.toString().getBytes("ISO-8859-1");
/* 105 */     return arrayOfByte1;
/*     */   }
/*     */ 
/*     */   public static String cachenameencode(String paramString)
/*     */   {
/* 110 */     String str = paramString;
/* 111 */     str = str.replace("/", "_");
/* 112 */     return str;
/*     */   }
/*     */ 
/*     */   public static String cdatadecode(String paramString)
/*     */   {
/* 117 */     String str = paramString;
/* 118 */     if (!(cls.isEmpty(str).booleanValue()))
/*     */     {
/* 120 */       str = str.replace("$CDATA#", "<![CDATA[");
/* 121 */       str = str.replace("#CDATA$", "]]>");
/*     */     }
/* 123 */     return str;
/*     */   }
/*     */ 
/*     */   public static String encodeNewline(String paramString)
/*     */   {
/* 128 */     String str = paramString;
/* 129 */     str = str.replace(String.valueOf('\r') + String.valueOf('\n'), String.valueOf('\n'));
/* 130 */     str = str.replace(String.valueOf('\n'), String.valueOf('\r') + String.valueOf('\n'));
/* 131 */     return str;
/*     */   }
/*     */ 
/*     */   public static String encodeArticle(String paramString)
/*     */   {
/* 136 */     String str = paramString;
/* 137 */     str = encodeNewline(str);
/* 138 */     str = str.replace(String.valueOf('\''), "&#39;");
/* 139 */     str = str.replace(String.valueOf(' ') + String.valueOf(' '), "&nbsp;&nbsp;");
/* 140 */     str = str.replace(String.valueOf('\r') + String.valueOf('\n'), "<br />");
/* 141 */     return str;
/*     */   }
/*     */ 
/*     */   public static String encodeChar2String(String paramString)
/*     */   {
/* 146 */     String str1 = paramString;
/* 147 */     String str2 = "";
/* 148 */     if (!(cls.isEmpty(str1).booleanValue()))
/*     */     {
/* 150 */       String[] arrayOfString = str1.split(Pattern.quote(","));
/* 151 */       for (int i = 0; i < arrayOfString.length; ++i)
/*     */       {
/* 153 */         int j = cls.getNum(arrayOfString[i], Integer.valueOf(0)).intValue();
/* 154 */         str2 = str2 + String.valueOf((char)j);
/*     */       }
/*     */     }
/* 157 */     return str2;
/*     */   }
/*     */ 
/*     */   public static String encodeHtml(String paramString)
/*     */   {
/* 162 */     String str = paramString;
/* 163 */     str = str.replace("&", "&amp;");
/* 164 */     str = str.replace(">", "&gt;");
/* 165 */     str = str.replace("<", "&lt;");
/* 166 */     str = str.replace("\"", "&quot;");
/* 167 */     return str;
/*     */   }
/*     */ 
/*     */   public static String encodeText(String paramString)
/*     */   {
/* 172 */     String str = paramString;
/* 173 */     str = str.replace("$", "&#36;");
/* 174 */     str = str.replace("@", "&#64;");
/* 175 */     return str;
/*     */   }
/*     */ 
/*     */   public static String htmlencode(String paramString)
/*     */   {
/* 180 */     String str1 = "";
/* 181 */     String str2 = paramString;
/* 182 */     str1 = htmlencode(str2, "0");
/* 183 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String htmlencode(String paramString1, String paramString2)
/*     */   {
/* 188 */     String str1 = paramString2;
/* 189 */     String str2 = paramString1;
/* 190 */     str2 = cls.getString(str2);
/* 191 */     str2 = encodeHtml(str2);
/* 192 */     str2 = encodeText(str2);
/* 193 */     if (str1.equals("1"))
/*     */     {
/* 195 */       str2 = str2.replace("|", "&#5;");
/* 196 */       str2 = str2.replace("=", "&#61;");
/*     */     }
/* 198 */     return str2;
/*     */   }
/*     */ 
/*     */   public static String keywordencode(String paramString)
/*     */   {
/* 203 */     String str1 = "";
/* 204 */     String str2 = paramString;
/* 205 */     if (!(cls.isEmpty(str2).booleanValue()))
/*     */     {
/* 207 */       String[] arrayOfString = str2.split(Pattern.quote(","));
/* 208 */       for (int i = 0; i < arrayOfString.length; ++i) str1 = str1 + "|:|" + arrayOfString[i] + "|:|,";
/* 209 */       if (!(cls.isEmpty(str1).booleanValue())) str1 = cls.getLRStr(str1, ",", "leftr");
/*     */     }
/* 211 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String keyworddecode(String paramString)
/*     */   {
/* 216 */     String str1 = "";
/* 217 */     String str2 = paramString;
/* 218 */     if (!(cls.isEmpty(str2).booleanValue()))
/*     */     {
/* 220 */       String[] arrayOfString = str2.split(Pattern.quote(","));
/* 221 */       for (int i = 0; i < arrayOfString.length; ++i) str1 = str1 + cls.getLRStr(cls.getLRStr(arrayOfString[i], "|:|", "leftr"), "|:|", "rightr") + ",";
/* 222 */       if (!(cls.isEmpty(str1).booleanValue())) str1 = cls.getLRStr(str1, ",", "leftr");
/*     */     }
/* 224 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String md5(byte[] paramArrayOfByte)
/*     */   {
/* 229 */     String str = "";
/* 230 */     byte[] arrayOfByte1 = paramArrayOfByte;
/* 231 */     char[] arrayOfChar1 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*     */     try
/*     */     {
/* 234 */       MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
/* 235 */       localMessageDigest.update(arrayOfByte1);
/* 236 */       byte[] arrayOfByte2 = localMessageDigest.digest();
/* 237 */       char[] arrayOfChar2 = new char[32];
/* 238 */       int i = 0;
/* 239 */       for (int j = 0; j < 16; ++j)
/*     */       {
/* 241 */         int k = arrayOfByte2[j];
/* 242 */         arrayOfChar2[(i++)] = arrayOfChar1[(k >>> 4 & 0xF)];
/* 243 */         arrayOfChar2[(i++)] = arrayOfChar1[(k & 0xF)];
/*     */       }
/* 245 */       str = new String(arrayOfChar2);
/*     */     } catch (Exception localException) {
/*     */     }
/* 248 */     return str;
/*     */   }
/*     */ 
/*     */   public static String scriptencode(String paramString)
/*     */   {
/* 253 */     String str = paramString;
/* 254 */     str = str.replace("\\", "\\\\");
/* 255 */     str = str.replace("'", "\\'");
/* 256 */     str = str.replace("\"", "\\\"");
/* 257 */     str = encodeText(str);
/* 258 */     return str;
/*     */   }
/*     */ 
/*     */   public static String unescape(String paramString)
/*     */   {
/* 263 */     String str1 = "";
/* 264 */     String str2 = paramString;
/* 265 */     if (!(cls.isEmpty(str2).booleanValue()))
/*     */     {
/* 267 */       byte[] arrayOfByte = { 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 63, 63, 63, 63, 63, 63, 63, 10, 11, 12, 13, 14, 15, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 10, 11, 12, 13, 14, 15, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63 };
/* 268 */       StringBuffer localStringBuffer = new StringBuffer();
/* 269 */       int i = 0;
/* 270 */       int j = str2.length();
/* 271 */       while (i < j)
/*     */       {
/* 273 */         int k = str2.charAt(i);
/* 274 */         if ((65 <= k) && (k <= 90))
/*     */         {
/* 276 */           localStringBuffer.append((char)k);
/*     */         }
/* 278 */         else if ((97 <= k) && (k <= 122))
/*     */         {
/* 280 */           localStringBuffer.append((char)k);
/*     */         }
/* 282 */         else if ((48 <= k) && (k <= 57))
/*     */         {
/* 284 */           localStringBuffer.append((char)k);
/*     */         }
/* 286 */         else if ((k == 45) || (k == 95) || (k == 46) || (k == 33) || (k == 126) || (k == 42) || (k == 39) || (k == 40) || (k == 41))
/*     */         {
/* 288 */           localStringBuffer.append((char)k);
/*     */         }
/* 290 */         else if (k == 37)
/*     */         {
/* 292 */           int l = 0;
/* 293 */           if ('u' != str2.charAt(i + 1))
/*     */           {
/* 295 */             l = l << 4 | arrayOfByte[str2.charAt(i + 1)];
/* 296 */             l = l << 4 | arrayOfByte[str2.charAt(i + 2)];
/* 297 */             i += 2;
/*     */           }
/*     */           else
/*     */           {
/* 301 */             l = l << 4 | arrayOfByte[str2.charAt(i + 2)];
/* 302 */             l = l << 4 | arrayOfByte[str2.charAt(i + 3)];
/* 303 */             l = l << 4 | arrayOfByte[str2.charAt(i + 4)];
/* 304 */             l = l << 4 | arrayOfByte[str2.charAt(i + 5)];
/* 305 */             i += 5;
/*     */           }
/* 307 */           localStringBuffer.append((char)l);
/*     */         }
/*     */         else
/*     */         {
/* 311 */           localStringBuffer.append((char)k);
/*     */         }
/* 313 */         ++i;
/*     */       }
/* 315 */       str1 = localStringBuffer.toString();
/*     */     }
/* 317 */     return str1;
/*     */   }
/*     */ 
/*     */   public static String ubb2html(String paramString)
/*     */   {
/* 322 */     String str1 = paramString;
/* 323 */     if (!(cls.isEmpty(str1).booleanValue()))
/*     */     {
/* 325 */       Boolean localBoolean = Boolean.valueOf(true);
/* 326 */       str1 = htmlencode(str1);
/* 327 */       str1 = str1.replace(" ", "&nbsp;");
/* 328 */       str1 = str1.replace("&nbsp;&nbsp;", "[&nbsp-nbsp;]");
/* 329 */       str1 = str1.replace("&nbsp;", " ");
/* 330 */       str1 = str1.replace("[&nbsp-nbsp;]", "&nbsp; ");
/* 331 */       str1 = str1.replaceAll("\\[br\\]", "<br />");
/* 332 */       String[][] arrayOfString = { { "\\[p\\](.*?)\\[\\/p\\]", "$1<br />" }, { "\\[b\\](.*?)\\[\\/b\\]", "<b>$1</b>" }, { "\\[i\\](.*?)\\[\\/i\\]", "<i>$1</i>" }, { "\\[u\\](.*?)\\[\\/u\\]", "<u>$1</u>" }, { "\\[ol\\]([\\s\\S]*)\\[\\/ol\\]", "<ol>$1</ol>" }, { "\\[ul\\]([\\s\\S]*)\\[\\/ul\\]", "<ul>$1</ul>" }, { "\\[li\\](.*?)\\[\\/li\\]", "<li>$1</li>" }, { "\\[code\\]([\\s\\S]+?)\\[\\/code\\]", "<div class=\"ubb_code\">$1</div>" }, { "\\[quote\\]([\\s\\S]+?)\\[\\/quote\\]", "<div class=\"ubb_quote\">$1</div>" }, { "\\[color=([^\\]]*)\\](.*?)\\[\\/color\\]", "<font style=\"color: $1\">$2</font>" }, { "\\[hilitecolor=([^\\]]*)\\](.*?)\\[\\/hilitecolor\\]", "<font style=\"background-color: $1\">$2</font>" }, { "\\[align=([^\\]]*)\\](.*?)\\[\\/align\\]", "<div style=\"text-align: $1\">$2</div>" }, { "\\[url=([^\\]]*)\\](.*?)\\[\\/url\\]", "<a href=\"$1\" target=\"_blank\">$2</a>" }, { "\\[img\\]([^\\[]*?)\\[\\/img\\]", "<a href=\"$1\" target=\"_blank\"><img src=\"$1\" onload=\"cls.img.tResize(this, 600, 1800);\" /></a>" } };
/*     */ 
/* 348 */       while (localBoolean.booleanValue())
/*     */       {
/* 350 */         localBoolean = Boolean.valueOf(false);
/* 351 */         for (int i = 0; i < arrayOfString.length; ++i)
/*     */         {
/* 354 */           Pattern localPattern = Pattern.compile(arrayOfString[i][0]);
/* 355 */           Matcher localMatcher = localPattern.matcher(str1);
/* 356 */           while (localMatcher.find())
/*     */           {
/* 358 */             localBoolean = Boolean.valueOf(true);
/* 359 */             String str2 = localMatcher.group();
/* 360 */             String str3 = arrayOfString[i][1];
/* 361 */             for (int j = 1; j < localMatcher.groupCount() + 1; ++j) str3 = str3.replace("$" + j, localMatcher.group(j));
/* 362 */             str1 = str1.replace(str2, str3);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 367 */     return str1;
/*     */   }
/*     */ }