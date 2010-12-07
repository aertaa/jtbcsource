/*     */ package jtbc;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.URLDecoder;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.servlet.http.HttpSession;
/*     */ 
/*     */ public class conf
/*     */ {
/*     */   public jt jt;
/*     */   public common common;
/*     */   public HttpSession session;
/*     */   public ServletContext application;
/*     */   public HttpServletRequest request;
/*     */   public HttpServletResponse response;
/*     */   public String ajaxPreContent;
/*     */   public String appName;
/*     */   public String adminFolder;
/*     */   public String charset;
/*     */   public String convert;
/*     */   public String connStr;
/*     */   public String dbtype;
/*     */   public String default_language;
/*     */   public String default_template;
/*     */   public String default_theme;
/*     */   public String imagesRoute;
/*     */   public String isApp;
/*     */   public String jtbccinfo;
/*     */   public String linkMode;
/*     */   public String navSpStr;
/*     */   public String ntitle;
/*     */   public String ntitleSpStr;
/*     */   public String repath;
/*     */   public Object[][] rsAry;
/*     */   public Object[][] rsbAry;
/*     */   public Object[][] rscAry;
/*     */   public Object[][] rstAry;
/*     */   public String sysName;
/*     */   public String xmlsfx;
/*     */ 
/*     */   public conf()
/*     */   {
/*  19 */     this.ajaxPreContent = "<!--jtbc-->";
/*  20 */     this.appName = "";
/*  21 */     this.adminFolder = "";
/*  22 */     this.charset = "";
/*  23 */     this.convert = "";
/*  24 */     this.connStr = "";
/*  25 */     this.dbtype = "";
/*  26 */     this.default_language = "";
/*  27 */     this.default_template = "";
/*  28 */     this.default_theme = "";
/*  29 */     this.imagesRoute = "";
/*  30 */     this.isApp = "";
/*  31 */     this.jtbccinfo = "<!--jtbccinfo-->";
/*  32 */     this.linkMode = "";
/*  33 */     this.navSpStr = "";
/*  34 */     this.ntitle = "";
/*  35 */     this.ntitleSpStr = "";
/*  36 */     this.repath = "";
/*  37 */     this.rsAry = ((Object[][])null);
/*  38 */     this.rsbAry = ((Object[][])null);
/*  39 */     this.rscAry = ((Object[][])null);
/*  40 */     this.rstAry = ((Object[][])null);
/*  41 */     this.sysName = "jtbc";
/*  42 */     this.xmlsfx = "";
/*     */   }
/*     */ 
/*     */   public void cntitle(String paramString) {
/*  46 */     String str = paramString;
/*  47 */     if (cls.isEmpty(this.ntitle).booleanValue()) this.ntitle = encode.htmlencode(str);
/*     */     else this.ntitle = encode.htmlencode(str) + this.ntitleSpStr + this.ntitle;
/*     */   }
/*     */ 
/*     */   public String decodeParameter(String paramString)
/*     */   {
/*  53 */     String str = paramString;
/*  54 */     if ((!(cls.isEmpty(str).booleanValue())) && 
/*  56 */       (this.convert.equals("1")))
/*     */     {
/*     */       try
/*     */       {
/*  60 */         str = new String(str.getBytes("iso-8859-1"), this.charset);
/*     */       }
/*     */       catch (Exception localException) {
/*     */       }
/*     */     }
/*  65 */     return str;
/*     */   }
/*     */ 
/*     */   public String getAppKey(String paramString)
/*     */   {
/*  70 */     String str1 = "";
/*  71 */     String str2 = paramString;
/*  72 */     str1 = this.appName + str2;
/*  73 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getActiveThings(String paramString)
/*     */   {
/*  78 */     String str1 = "";
/*  79 */     String str2 = "";
/*  80 */     String str3 = "";
/*  81 */     String str4 = paramString;
/*  82 */     if (str4.equals("lng"))
/*     */     {
/*  84 */       str2 = "language";
/*  85 */       str3 = this.default_language;
/*     */     }
/*  87 */     else if (str4.equals("sel"))
/*     */     {
/*  89 */       str2 = "language";
/*  90 */       str3 = this.default_language;
/*     */     }
/*  92 */     else if (str4.equals("tpl"))
/*     */     {
/*  94 */       str2 = "template";
/*  95 */       str3 = this.default_template;
/*     */     }
/*  97 */     else if (str4.equals("theme"))
/*     */     {
/*  99 */       str2 = "theme";
/* 100 */       str3 = this.default_theme;
/*     */     }
/*     */     else
/*     */     {
/* 104 */       str2 = "language";
/* 105 */       str3 = this.default_language;
/*     */     }
/* 107 */     String str5 = cookies.getAttribute(this, getAppKey("config-" + str2));
/* 108 */     str5 = cls.getString(str5);
/* 109 */     if (!(cls.isEmpty(str5).booleanValue())) str1 = encode.htmlencode(str5);
/*     */     else str1 = encode.htmlencode(str3);
/* 111 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getActualRoute(String paramString)
/*     */   {
/* 116 */     String str1 = "";
/* 117 */     String str2 = paramString;
/* 118 */     str1 = getActualRoute(str2, getNroute());
/* 119 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getActualRouteB(String paramString)
/*     */   {
/* 124 */     String str1 = "";
/* 125 */     String str2 = paramString;
/* 126 */     str1 = getActualRoute(str2, getNroute(), "0");
/* 127 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getActualRoute(String paramString1, String paramString2)
/*     */   {
/* 132 */     String str1 = "";
/* 133 */     String str2 = paramString1;
/* 134 */     String str3 = paramString2;
/* 135 */     str1 = getActualRoute(str2, str3, "1");
/* 136 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getActualRoute(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 141 */     Object localObject = "";
/* 142 */     String str1 = paramString1;
/* 143 */     String str2 = paramString2;
/* 144 */     String str3 = paramString3;
/* 145 */     String str4 = this.linkMode;
/* 146 */     if ((str3.equals("1")) && (!(cls.isEmpty(str4).booleanValue()))) localObject = str4 + str1;
/* 149 */     else if (str2.equals("greatgrandchild")) localObject = "../../../../" + str1;
/* 150 */     else if (str2.equals("grandchild")) localObject = "../../../" + str1;
/* 151 */     else if (str2.equals("child")) localObject = "../../" + str1;
/* 152 */     else if (str2.equals("node")) localObject = "../" + str1;
/*     */     else localObject = str1;
/*     */ 
/* 155 */     return ((String)localObject);
/*     */   }
/*     */ 
/*     */   public String getActiveGenre(String paramString1, String paramString2)
/*     */   {
/* 160 */     String str1 = paramString1;
/* 161 */     String str2 = paramString2;
/* 162 */     Object localObject = "";
/* 163 */     String str3 = "active_genre_" + str1;
/* 164 */     String str4 = (String)this.application.getAttribute(getAppKey(str3));
/* 165 */     if (!(cls.isEmpty(str4).booleanValue())) { localObject = str4;
/*     */     }
/*     */     else {
/* 168 */       localObject = getActiveGenre(paramString1, paramString2, null);
/* 169 */       if (this.isApp.equals("1")) this.application.setAttribute(getAppKey(str3), localObject);
/*     */     }
/* 171 */     return ((String)localObject);
/*     */   }
/*     */ 
/*     */   public String getActiveGenre(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 176 */     String str1 = paramString1;
/* 177 */     String str2 = paramString2;
/* 178 */     String str3 = paramString3;
/* 179 */     String str4 = str1;
/* 180 */     String str5 = "";
/* 181 */     String str6 = "";
/* 182 */     String str7 = "";
/* 183 */     String str8 = "";
/* 184 */     String str9 = "";
/* 185 */     File localFile = new File(this.application.getRealPath(getMapPath(str2)).toString());
/* 186 */     File[] arrayOfFile = localFile.listFiles();
/* 187 */     for (int i = 0; i < arrayOfFile.length; ++i)
/*     */     {
/* 189 */       if (!(arrayOfFile[i].isDirectory()))
/*     */         continue;
/* 191 */       str8 = arrayOfFile[i].getName();
/* 192 */       if ((str8.indexOf("=") != -1) || (str8.indexOf("&") != -1) || (str8.indexOf("'") != -1))
/*     */         continue;
/* 194 */       str5 = this.jt.getXRootAtt(str2 + str8 + "/common/" + str4 + this.xmlsfx, "mode");
/* 195 */       if (cls.isEmpty(str5).booleanValue())
/*     */         continue;
/* 197 */       str9 = str8;
/* 198 */       if (!(cls.isEmpty(str3).booleanValue())) str9 = str3 + "/" + str8;
/* 199 */       str6 = str6 + str9 + "|";
/* 200 */       str7 = getActiveGenre(str1, str2 + str8 + "/", str9);
/* 201 */       if (cls.isEmpty(str7).booleanValue()) continue; str6 = str6 + str7 + "|";
/*     */     }
/*     */ 
/* 206 */     str6 = cls.getLRStr(str6, "|", "leftr");
/* 207 */     str6 = getActiveGenreOrdered(str3, str6);
/* 208 */     return str6;
/*     */   }
/*     */ 
/*     */   public String getActiveGenreOrdered(String paramString1, String paramString2)
/*     */   {
/* 213 */     String str1 = paramString1;
/* 214 */     String str2 = paramString2;
/* 215 */     Object localObject = "";
/* 216 */     if (!(cls.isEmpty(str2).booleanValue()))
/*     */     {
/* 218 */       String str3 = "";
/* 219 */       if (cls.isEmpty(str1).booleanValue()) str3 = this.jt.itake("global.config.genre-order", "cfg");
/*     */       else str3 = this.jt.itake("global." + str1 + ":config.genre-order", "cfg");
/* 221 */       if (!(cls.isEmpty(str3).booleanValue()))
/*     */       {
/* 223 */         String str4 = "";
/* 224 */         String str5 = "";
/* 225 */         String str6 = "";
/* 226 */         String[] arrayOfString1 = str3.split(Pattern.quote(","));
/* 227 */         String[] arrayOfString2 = str2.split(Pattern.quote("|"));
/* 228 */         for (int i = 0; i < arrayOfString1.length; ++i)
/*     */         {
/* 230 */           str5 = arrayOfString1[i];
/* 231 */           if (!(cls.isEmpty(str1).booleanValue())) str5 = str1 + "/" + str5;
/* 232 */           if (!(cls.cinstr(str2, str5, "|").booleanValue()))
/*     */             continue;
/* 234 */           for (int j = 0; j < arrayOfString2.length; ++j)
/*     */           {
/* 236 */             str4 = arrayOfString2[j];
/* 237 */             if (!(str4.equals(str5))) continue; str6 = str6 + str4 + "|";
/*     */           }
/*     */         }
/*     */ 
/* 241 */         for (int i = 0; i < arrayOfString2.length; ++i)
/*     */         {
/* 243 */           if (cls.cinstr(str6, arrayOfString2[i], "|").booleanValue()) continue; str6 = str6 + arrayOfString2[i] + "|";
/*     */         }
/* 245 */         str6 = cls.getLRStr(str6, "|", "leftr");
/* 246 */         localObject = str6;
/*     */       } else {
/* 248 */         localObject = str2; }
/*     */     }
/* 250 */     return ((String)localObject);
/*     */   }
/*     */ 
/*     */   public String getMapPath(String paramString)
/*     */   {
/* 255 */     String str1 = "";
/* 256 */     String str2 = getNURI();
/* 257 */     String str3 = paramString;
/* 258 */     Integer localInteger = Integer.valueOf(0);
/* 259 */     str2 = cls.getLRStr(str2, "/", "leftr");
/*     */     do
/*     */     {
/* 262 */       if (str3.length() < 3) { localInteger = Integer.valueOf(1);
/*     */       }
/*     */       else {
/* 265 */         String str4 = str3.substring(0, 3);
/* 266 */         if (str4.equals("../"))
/*     */         {
/* 268 */           str3 = cls.getLRStr(str3, "../", "rightr");
/* 269 */           str2 = cls.getLRStr(str2, "/", "leftr");
/*     */         } else {
/* 271 */           localInteger = Integer.valueOf(1); } }
/*     */     }
/* 273 */     while (localInteger.intValue() == 0);
/* 274 */     str1 = str2 + "/" + str3;
/* 275 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getNURI()
/*     */   {
/* 280 */     String str = "";
/* 281 */     str = this.request.getServletPath();
/* 282 */     return str;
/*     */   }
/*     */ 
/*     */   public String getNURS()
/*     */   {
/* 287 */     String str = "";
/* 288 */     str = this.request.getQueryString();
/* 289 */     if (str == null) str = "";
/* 290 */     return str;
/*     */   }
/*     */ 
/*     */   public String getNURL()
/*     */   {
/* 295 */     String str1 = "";
/* 296 */     String str2 = getNURS();
/* 297 */     str1 = getNURI();
/* 298 */     if (!(cls.isEmpty(str2).booleanValue())) str1 = str1 + "?" + str2;
/* 299 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getNURLPre()
/*     */   {
/* 304 */     String str1 = "";
/* 305 */     String str2 = this.request.getRequestURL().toString();
/* 306 */     String str3 = cls.getLRStr(getNURI(), "/", "leftr");
/* 307 */     str1 = cls.getLRStr(str2, "/", "leftr");
/* 308 */     if (!(cls.isEmpty(str3).booleanValue())) str1 = cls.getLRStr(str1, str3, "leftr");
/* 309 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getNLng()
/*     */   {
/* 314 */     String str = "";
/* 315 */     str = this.common.getLngID(getActiveThings("lng"));
/* 316 */     if (cls.getNum(str, Integer.valueOf(-1)).intValue() == -1) str = "0";
/* 317 */     return str;
/*     */   }
/*     */ 
/*     */   public String getNroute()
/*     */   {
/* 322 */     String str1 = "";
/* 323 */     String str2 = getNURI();
/* 324 */     String str3 = encode.base64encode(cls.getLRStr(str2, "/", "leftr").getBytes());
/* 325 */     String str4 = cls.getLRStr(cls.getLRStr(str2, "/", "leftr"), "/", "right");
/* 326 */     if (cls.isEmpty(str4).booleanValue()) str4 = ":root";
/* 327 */     str3 = str3 + encode.base64encode(str4.getBytes());
/* 328 */     str1 = getNroute(str3);
/* 329 */     if (cls.isEmpty(str1).booleanValue())
/*     */     {
/* 331 */       File localFile1 = new File(this.application.getRealPath(getMapPath("common/jtbc.guide")).toString());
/* 332 */       if (localFile1.exists()) { str1 = "root";
/*     */       }
/*     */       else {
/* 335 */         File localObject = new File(this.application.getRealPath(getMapPath("../common/jtbc.guide")).toString());
/* 336 */         if (((File)localObject).exists()) { str1 = "node";
/*     */         }
/*     */         else {
/* 339 */           File localFile2 = new File(this.application.getRealPath(getMapPath("../../common/jtbc.guide")).toString());
/* 340 */           if (localFile2.exists()) { str1 = "child";
/*     */           }
/*     */           else {
/* 343 */             File localFile3 = new File(this.application.getRealPath(getMapPath("../../../common/jtbc.guide")).toString());
/* 344 */             if (localFile3.exists()) { str1 = "grandchild";
/*     */             }
/*     */             else {
/* 347 */               File localFile4 = new File(this.application.getRealPath(getMapPath("../../../../common/jtbc.guide")).toString());
/* 348 */               if (localFile4.exists()) str1 = "greatgrandchild";
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/* 353 */       String[][] localObject = new String[1][2];
/* 354 */       localObject[0][0] = str3;
/* 355 */       localObject[0][1] = str1;
/* 356 */       if (this.isApp.equals("1")) this.application.setAttribute(getAppKey("route"), cls.mergeAry((String[][])(String[][])this.application.getAttribute(getAppKey("route")), localObject));
/*     */     }
/* 358 */     return ((String)str1);
/*     */   }
/*     */ 
/*     */   public String getNroute(String paramString)
/*     */   {
/* 363 */     String[][] arrayOfString = (String[][])(String[][])this.application.getAttribute(getAppKey("route"));
/* 364 */     String str1 = "";
/* 365 */     if (arrayOfString != null)
/*     */     {
/* 367 */       String str2 = cls.getString(paramString);
/* 368 */       for (int i = 0; i < arrayOfString.length; ++i)
/*     */       {
/* 370 */         if (!(str2.equals(arrayOfString[i][0])))
/*     */           continue;
/* 372 */         str1 = arrayOfString[i][1];
/* 373 */         break;
/*     */       }
/*     */     }
/*     */ 
/* 377 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getNGenre()
/*     */   {
/* 382 */     String str1 = "";
/* 383 */     String str2 = getNURI();
/* 384 */     str2 = cls.getLRStr(str2.toLowerCase(), "/", "leftr");
/* 385 */     String[] arrayOfString = str2.split(Pattern.quote("/"));
/* 386 */     int i = arrayOfString.length;
/* 387 */     String str3 = getNroute();
/* 388 */     if (str3.equals("greatgrandchild"))
/*     */     {
/* 390 */       if (i >= 4) str1 = arrayOfString[(i - 4)] + "/" + arrayOfString[(i - 3)] + "/" + arrayOfString[(i - 2)] + "/" + arrayOfString[(i - 1)];
/*     */     }
/* 392 */     else if (str3.equals("grandchild"))
/*     */     {
/* 394 */       if (i >= 3) str1 = arrayOfString[(i - 3)] + "/" + arrayOfString[(i - 2)] + "/" + arrayOfString[(i - 1)];
/*     */     }
/* 396 */     else if (str3.equals("child"))
/*     */     {
/* 398 */       if (i >= 2) str1 = arrayOfString[(i - 2)] + "/" + arrayOfString[(i - 1)];
/*     */     }
/* 400 */     else if ((str3.equals("node")) && 
/* 402 */       (i >= 1)) str1 = arrayOfString[(i - 1)];
/*     */ 
/* 404 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getRemortIP()
/*     */   {
/* 409 */     String str = "";
/* 410 */     str = this.request.getHeader("x-forwarded-for");
/* 411 */     if (str == null) str = this.request.getRemoteAddr();
/* 412 */     return str;
/*     */   }
/*     */ 
/*     */   public String getRequestURL()
/*     */   {
/* 417 */     String str1 = "";
/* 418 */     String str2 = getNURS();
/* 419 */     str1 = this.request.getRequestURL().toString();
/* 420 */     if (!(cls.isEmpty(str2).booleanValue())) str1 = str1 + "?" + str2;
/* 421 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getRequestParameter(String paramString)
/*     */   {
/* 426 */     String str = paramString;
/* 427 */     str = decodeParameter(this.request.getParameter(str));
/* 428 */     return str;
/*     */   }
/*     */ 
/*     */   public String getRequestParameters(String paramString)
/*     */   {
/* 433 */     String str1 = "";
/* 434 */     String str2 = paramString;
/* 435 */     String[] arrayOfString = this.request.getParameterValues(str2);
/* 436 */     if (arrayOfString != null)
/*     */     {
/* 438 */       for (int i = 0; i < arrayOfString.length; ++i) str1 = str1 + arrayOfString[i] + ",";
/* 439 */       str1 = cls.getLRStr(str1, ",", "leftr");
/*     */     }
/* 441 */     str1 = decodeParameter(str1);
/* 442 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getRequestUsParameter(String paramString)
/*     */   {
/* 447 */     String str = paramString;
/* 448 */     str = getRequestParameter(str);
/*     */     try
/*     */     {
/* 451 */       str = URLDecoder.decode(str, this.charset);
/*     */     } catch (Exception localException) {
/* 453 */       str = ""; }
/* 454 */     return str;
/*     */   }
/*     */ 
/*     */   public String getRequestUsParameters(String paramString)
/*     */   {
/* 459 */     String str = paramString;
/* 460 */     str = getRequestParameters(str);
/*     */     try
/*     */     {
/* 463 */       str = URLDecoder.decode(str, this.charset);
/*     */     } catch (Exception localException) {
/* 465 */       str = ""; }
/* 466 */     return str;
/*     */   }
/*     */ 
/*     */   public String urlencode(String paramString)
/*     */   {
/* 471 */     String str = paramString;
/*     */     try
/*     */     {
/* 474 */       str = URLEncoder.encode(str, this.charset);
/*     */     } catch (Exception localException) {
/* 476 */       str = ""; }
/* 477 */     return str;
/*     */   }
/*     */ 
/*     */   public void Init(Object paramObject1, Object paramObject2)
/*     */   {
/* 482 */     Object localObject1 = paramObject1;
/* 483 */     Object localObject2 = paramObject2;
/* 484 */     this.request = ((HttpServletRequest)localObject1);
/* 485 */     this.response = ((HttpServletResponse)localObject2);
/* 486 */     this.session = this.request.getSession();
/* 487 */     this.application = this.session.getServletContext();
/*     */ 
/* 489 */     this.appName = this.application.getInitParameter("appName");
/* 490 */     this.adminFolder = this.application.getInitParameter("adminFolder");
/* 491 */     this.charset = this.application.getInitParameter("charset");
/* 492 */     this.convert = this.application.getInitParameter("convert");
/* 493 */     this.connStr = this.application.getInitParameter("connStr");
/* 494 */     this.dbtype = this.application.getInitParameter("dbtype");
/* 495 */     this.default_language = this.application.getInitParameter("default_language");
/* 496 */     this.default_template = this.application.getInitParameter("default_template");
/* 497 */     this.default_theme = this.application.getInitParameter("default_theme");
/* 498 */     this.imagesRoute = this.application.getInitParameter("imagesRoute");
/* 499 */     this.isApp = this.application.getInitParameter("isApp");
/* 500 */     this.linkMode = this.application.getInitParameter("linkMode");
/* 501 */     this.repath = this.application.getInitParameter("repath");
/* 502 */     this.xmlsfx = this.application.getInitParameter("xmlsfx");
/*     */ 
/* 504 */     this.jt = new jt(this);
/* 505 */     this.common = new common(this);
/*     */   }
/*     */ }