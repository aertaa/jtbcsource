/*     */ package jtbc;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import javax.xml.xpath.XPath;
/*     */ import javax.xml.xpath.XPathConstants;
/*     */ import javax.xml.xpath.XPathFactory;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ 
/*     */ public class jt
/*     */ {
/*     */   public conf conf;
/*     */ 
/*     */   public String cparameter(String paramString)
/*     */   {
/*  18 */     String str1 = "";
/*  19 */     String str2 = paramString;
/*  20 */     str2 = str2.trim();
/*  21 */     if (str2.substring(0, 1).equals("\""))
/*     */     {
/*  23 */       str1 = cls.getLRStr(cls.getLRStr(str2, "\"", "rightr"), "\"", "leftr");
/*     */     }
/*     */     else
/*     */     {
/*  27 */       str2 = str2.replace("'", "\"");
/*  28 */       str1 = cvalue(str2);
/*     */     }
/*  30 */     return str1;
/*     */   }
/*     */ 
/*     */   public String cvalue(String paramString)
/*     */   {
/*  35 */     String str1 = paramString;
/*     */ 
/*  37 */     if (str1 != "")
/*     */     {
/*  39 */       String str2 = cls.getLRStr(str1, "(", "left");
/*  40 */       String str3 = cls.getLRStr(cls.getLRStr(str1, "(", "rightr"), ")", "leftr");
/*  41 */       String[] arrayOfString = fixParameterAry(str3.split(Pattern.quote(",")));
/*  42 */       Integer localInteger = Integer.valueOf(arrayOfString.length);
/*  43 */       if (str2.equals("$admin.theme")) { str1 = this.conf.common.getAdminTheme();
/*  44 */       } else if (str2.equals("$adminFolder")) { str1 = this.conf.adminFolder;
/*  45 */       } else if (str2.equals("$charset")) { str1 = this.conf.charset;
/*  46 */       } else if (str2.equals("$images")) { str1 = this.conf.imagesRoute;
/*  47 */       } else if (str2.equals("$global.images")) { str1 = this.conf.getActualRoute(this.conf.imagesRoute);
/*  48 */       } else if (str2.equals("$ngenre")) { str1 = this.conf.getNGenre();
/*  49 */       } else if (str2.equals("$nlng")) { str1 = this.conf.getNLng();
/*  50 */       } else if (str2.equals("$ntitle")) { str1 = this.conf.ntitle;
/*  51 */       } else if (str2.equals("$navSpStr")) { str1 = this.conf.navSpStr;
/*  52 */       } else if (str2.equals("$now")) { str1 = cls.getDate();
/*  53 */       } else if (str2.equals("$nurs")) { str1 = this.conf.getNURS();
/*  54 */       } else if (str2.equals("$nuri")) { str1 = this.conf.getNURI();
/*  55 */       } else if (str2.equals("$nurl")) { str1 = this.conf.getNURL();
/*  56 */       } else if (str2.equals("$nurlpre")) { str1 = this.conf.getNURLPre();
/*  57 */       } else if (str2.equals("$nuserip")) { str1 = this.conf.getRemortIP();
/*  58 */       } else if (str2.equals("$sysName")) { str1 = this.conf.sysName;
/*  59 */       } else if (str2.equals("base64encode")) { str1 = encode.base64encode(cparameter(str3).getBytes());
/*  60 */       } else if (str2.equals("concat"))
/*     */       {
/*  62 */         if (localInteger.intValue() == 2) str1 = cls.concat(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
/*     */       }
/*  64 */       else if (str2.equals("curl"))
/*     */       {
/*  66 */         if (localInteger.intValue() == 2) str1 = this.conf.common.curl(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
/*     */       }
/*  68 */       else if (str2.equals("cdatadecode")) { str1 = encode.cdatadecode(cparameter(str3));
/*  69 */       } else if (str2.equals("crValcodeTpl")) { str1 = this.conf.common.crValcodeTpl(cparameter(str3));
/*  70 */       } else if (str2.equals("encodeArticle")) { str1 = encode.encodeArticle(cparameter(str3));
/*  71 */       } else if (str2.equals("formatByte")) { str1 = cls.formatByte(cparameter(str3));
/*  72 */       } else if (str2.equals("formatDate"))
/*     */       {
/*  74 */         if (localInteger.intValue() == 1) str1 = cls.formatDate(cparameter(str3));
/*  75 */         if (localInteger.intValue() == 2) str1 = cls.formatDate(cparameter(arrayOfString[0]), cls.getNum(cparameter(arrayOfString[1])));
/*     */       }
/*  77 */       else if (str2.equals("formatText"))
/*     */       {
/*  79 */         if (localInteger.intValue() == 3) str1 = cls.formatText(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]));
/*     */       }
/*  81 */       else if (str2.equals("formatTextLine"))
/*     */       {
/*  83 */         if (localInteger.intValue() == 2) str1 = cls.formatTextLine(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
/*     */       }
/*  85 */       else if (str2.equals("formatUnixStampDate")) { str1 = cls.formatUnixStampDate(cls.getNum64(cparameter(str3), Long.valueOf(0L)));
/*  86 */       } else if (str2.equals("getActualRoute")) { str1 = this.conf.getActualRoute(cparameter(str3));
/*  87 */       } else if (str2.equals("getActualRouteB")) { str1 = this.conf.getActualRouteB(cparameter(str3));
/*  88 */       } else if (str2.equals("getActiveThings")) { str1 = this.conf.getActiveThings(cparameter(str3));
/*  89 */       } else if (str2.equals("getClassText"))
/*     */       {
/*  91 */         if (localInteger.intValue() == 3)
/*     */         {
/*  93 */           category localcategory = new category(this.conf);
/*  94 */           str1 = localcategory.getClassText(cparameter(arrayOfString[0]), cls.getNum(cparameter(arrayOfString[1]), Integer.valueOf(0)), cls.getNum(cparameter(arrayOfString[2]), Integer.valueOf(0)));
/*     */         }
/*     */       }
/*  97 */       else if (str2.equals("getCuteContent"))
/*     */       {
/*  99 */         if (localInteger.intValue() == 2) str1 = this.conf.common.getCuteContent(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
/*     */       }
/* 101 */       else if (str2.equals("getCuteContentCount")) { str1 = this.conf.common.getCuteContentCount(cparameter(str3));
/* 102 */       } else if (str2.equals("getLeft"))
/*     */       {
/* 104 */         if (localInteger.intValue() == 2) str1 = cls.getLeft(cparameter(arrayOfString[0]), cls.getNum(cparameter(arrayOfString[1]), Integer.valueOf(0)));
/* 105 */         if (localInteger.intValue() == 3) str1 = cls.getLeft(cparameter(arrayOfString[0]), cls.getNum(cparameter(arrayOfString[1]), Integer.valueOf(0)), cparameter(arrayOfString[2]));
/*     */       }
/* 107 */       else if (str2.equals("getLeftB"))
/*     */       {
/* 109 */         if (localInteger.intValue() == 2) str1 = cls.getLeftB(cparameter(arrayOfString[0]), cls.getNum(cparameter(arrayOfString[1]), Integer.valueOf(0)));
/* 110 */         if (localInteger.intValue() == 3) str1 = cls.getLeftB(cparameter(arrayOfString[0]), cls.getNum(cparameter(arrayOfString[1]), Integer.valueOf(0)), cparameter(arrayOfString[2]));
/*     */       }
/* 112 */       else if (str2.equals("getNum"))
/*     */       {
/* 114 */         if (localInteger.intValue() == 2) str1 = cls.toString(cls.getNum(cparameter(arrayOfString[0]), cls.getNum(cparameter(arrayOfString[1]), Integer.valueOf(0))));
/*     */       }
/* 116 */       else if (str2.equals("getLRStr"))
/*     */       {
/* 118 */         if (localInteger.intValue() == 3) str1 = cls.getLRStr(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]));
/*     */       }
/* 120 */       else if (str2.equals("getRsValue"))
/*     */       {
/* 122 */         if (localInteger.intValue() == 2) str1 = this.conf.common.getRsValue(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
/*     */       }
/* 124 */       else if (str2.equals("getString")) { str1 = cls.getString(cparameter(str3));
/* 125 */       } else if (str2.equals("getSafeString")) { str1 = cls.getSafeString(cparameter(str3));
/* 126 */       } else if (str2.equals("getRequestParameter")) { str1 = this.conf.getRequestParameter(cparameter(str3));
/* 127 */       } else if (str2.equals("getRequestParameters")) { str1 = this.conf.getRequestParameters(cparameter(str3));
/* 128 */       } else if (str2.equals("getRequestUsParameter")) { str1 = this.conf.getRequestUsParameter(cparameter(str3));
/* 129 */       } else if (str2.equals("getRequestUsParameters")) { str1 = this.conf.getRequestUsParameters(cparameter(str3));
/* 130 */       } else if (str2.equals("getSearchOptions")) { str1 = this.conf.common.getSearchOptions(cparameter(str3));
/* 131 */       } else if (str2.equals("getSwitchOptions")) { str1 = this.conf.common.getSwitchOptions(cparameter(str3));
/* 132 */       } else if (str2.equals("htmlencode"))
/*     */       {
/* 134 */         if (localInteger.intValue() == 1) str1 = encode.htmlencode(cparameter(str3));
/* 135 */         if (localInteger.intValue() == 2) str1 = encode.htmlencode(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
/*     */       }
/* 137 */       else if (str2.equals("itransfer")) { str1 = this.conf.common.itransfer(cparameter(str3));
/* 138 */       } else if (str2.equals("isort")) { str1 = this.conf.common.isort(cparameter(str3));
/* 139 */       } else if (str2.equals("inavigation")) { str1 = this.conf.common.inavigation(cparameter(str3));
/* 140 */       } else if (str2.equals("iurl")) { str1 = this.conf.common.iurl(cparameter(str3));
/* 141 */       } else if (str2.equals("itake"))
/*     */       {
/* 143 */         if (localInteger.intValue() == 2) str1 = itake(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
/* 144 */         if (localInteger.intValue() == 3) str1 = itake(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]));
/* 145 */         if (localInteger.intValue() == 4) str1 = itake(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]), cparameter(arrayOfString[3]));
/*     */       }
/* 147 */       else if (str2.equals("ireplace"))
/*     */       {
/* 149 */         if (localInteger.intValue() == 2) str1 = ireplace(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
/* 150 */         if (localInteger.intValue() == 3) str1 = ireplace(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]));
/*     */       }
/* 152 */       else if (str2.equals("keywordencode")) { str1 = encode.keywordencode(cparameter(str3));
/* 153 */       } else if (str2.equals("keyworddecode")) { str1 = encode.keyworddecode(cparameter(str3));
/* 154 */       } else if (str2.equals("loadEditor"))
/*     */       {
/* 156 */         if (localInteger.intValue() == 2) str1 = this.conf.common.loadEditor(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
/* 157 */         if (localInteger.intValue() == 3) str1 = this.conf.common.loadEditor(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]));
/* 158 */         if (localInteger.intValue() == 4) str1 = this.conf.common.loadEditor(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]), cparameter(arrayOfString[3]));
/*     */       }
/* 160 */       else if (str2.equals("pagi"))
/*     */       {
/* 162 */         if (localInteger.intValue() == 4) str1 = this.conf.common.pagi(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]), cparameter(arrayOfString[3]));
/* 163 */         if (localInteger.intValue() == 5) str1 = this.conf.common.pagi(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]), cparameter(arrayOfString[3]), cparameter(arrayOfString[4]));
/*     */       }
/* 165 */       else if (str2.equals("md5")) { str1 = encode.md5(cparameter(str3).getBytes());
/* 166 */       } else if (str2.equals("replace"))
/*     */       {
/* 168 */         if (localInteger.intValue() == 3) str1 = cls.replace(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]));
/*     */       }
/* 170 */       else if (str2.equals("repathdecode")) { str1 = this.conf.common.repathdecode(cparameter(str3));
/* 171 */       } else if (str2.equals("striptags")) { str1 = filter.striptags(cparameter(str3));
/* 172 */       } else if (str2.equals("selClass"))
/*     */       {
/* 174 */         if (localInteger.intValue() == 1) str1 = this.conf.common.selClass(cparameter(str3));
/* 175 */         if (localInteger.intValue() == 2) str1 = this.conf.common.selClass(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
/*     */       }
/* 177 */       else if (str2.equals("urlencode")) { str1 = this.conf.urlencode(cparameter(str3));
/* 178 */       } else if (str2.equals("ubb2html")) { str1 = encode.ubb2html(cparameter(str3));
/* 179 */       } else if (str2.equals("webBase")) { str1 = this.conf.common.webBase(cparameter(str3));
/* 180 */       } else if (str2.equals("webHead")) { str1 = this.conf.common.webHead(cparameter(str3));
/* 181 */       } else if (str2.equals("webFoot")) { str1 = this.conf.common.webFoot(cparameter(str3));
/* 182 */       } else if (str2.equals("xmlSelect"))
/*     */       {
/* 184 */         if (localInteger.intValue() == 3) str1 = this.conf.common.xmlSelect(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]));
/* 185 */         if (localInteger.intValue() == 4) str1 = this.conf.common.xmlSelect(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]), cparameter(arrayOfString[3]));
/*     */       }
/*     */     }
/* 188 */     return str1;
/*     */   }
/*     */ 
/*     */   public String creplace(String paramString)
/*     */   {
/*     */     Object localObject1;
			  Object localObject2;
			  Object localObject3;
			  Object localObject4;
/* 193 */     String str1 = paramString;
/* 194 */     if (str1.indexOf("</jtbc>") >= 0)
/*     */     {
/* 196 */       localObject1 = str1.split(Pattern.quote("</jtbc>"));
/* 197 */       for (int i = 0; i < ((String[])localObject1).length - 1; ++i)
/*     */       {
/*     */         
/* 199 */         localObject2 = ((String[])localObject1)[i];
/* 200 */         if ((cls.isEmpty(localObject2).booleanValue()) || 
/* 202 */           (((String)localObject2).indexOf("<jtbc") < 0))
/*     */           continue;
/* 204 */         localObject2 = cls.getLRStr((String)localObject2, "<jtbc", "right");
/* 205 */         localObject3 = "";
/* 206 */         String str3 = "";
/* 207 */         String str4 = cls.getLRStr((String)localObject2, ">", "left");
/* 208 */         String str5 = cls.getLRStr((String)localObject2, ">", "rightr");
/* 209 */         String str6 = "<jtbc" + ((String)localObject2) + "</jtbc>";
/* 210 */         if (!(cls.isEmpty(str4).booleanValue()))
/*     */         {
/* 212 */           localObject4 = str4.split(Pattern.quote(" "));
/* 213 */           for (int j = 0; j < ((String[])localObject4).length; ++j)
/*     */           {
/* 215 */             String str7 = ((String[])localObject4)[j].trim();
/* 216 */             if (cls.isEmpty(str7).booleanValue())
/*     */               continue;
/* 218 */             str7 = str7.replace("\"", "");
/* 219 */             String[] arrayOfString = str7.split(Pattern.quote("="));
/* 220 */             if (arrayOfString.length != 2)
/*     */               continue;
/* 222 */             if (arrayOfString[0].equals("id")) localObject3 = arrayOfString[1];
/* 223 */             str3 = str3 + str7 + ";";
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 228 */         if (!(cls.isEmpty(str3).booleanValue())) str3 = cls.getLRStr(str3, ";", "leftr");
/* 229 */         if (!(cls.isEmpty(localObject3).booleanValue()))
/*     */         {
/* 231 */           localObject4 = new String[1][2];
/* 232 */           ((String[][])localObject4)[0][0] = str3;
/* 233 */           ((String[][])localObject4)[0][1] = str5;
/* 234 */           this.conf.njtbcelement = cls.mergeAry(this.conf.njtbcelement, ((String[][])localObject4));
/*     */         }
/* 236 */         str1 = str1.replace(str6, "");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 242 */    localObject2 = Pattern.compile("(\\{\\$=(.[^\\}]*)\\})");
/* 243 */     localObject3 = ((Pattern)localObject2).matcher(str1);
/* 244 */     while (((Matcher)localObject3).find())
/*     */     {
/* 246 */       localObject1 = ((Matcher)localObject3).group(1);
/* 247 */       String str2 = ((Matcher)localObject3).group(2);
/* 248 */       str1 = str1.replace((CharSequence)localObject1, cvalue(str2));
/*     */     }
/* 250 */     return ((String)(String)(String)(String)str1);
/*     */   }
/*     */ 
/*     */   public String[] fixParameterAry(String[] paramArrayOfString)
/*     */   {
/* 255 */     String[] arrayOfString1 = paramArrayOfString;
/* 256 */     String str1 = "";
/* 257 */     String str2 = "";
/* 258 */     String str3 = "";
/* 259 */     String str4 = "{@}a{@}b{@}c{@}";
/* 260 */     for (int i = 0; i < arrayOfString1.length; ++i)
/*     */     {
/* 262 */       str2 = arrayOfString1[i];
/* 263 */       str2 = str2.trim();
/* 264 */       if (!(cls.isEmpty(str2).booleanValue()))
/*     */       {
/* 266 */         if ((!(str2.equals("\""))) && (str2.substring(0, 1).equals("\"")) && (str2.substring(str2.length() - 1, str2.length()).equals("\"")))
/*     */         {
/* 268 */           if (!(cls.isEmpty(str3).booleanValue()))
/*     */           {
/* 270 */             str1 = str1 + cls.getLRStr(str3, ",", "leftr") + str4;
/* 271 */             str3 = "";
/*     */           }
/* 273 */           str1 = str1 + str2 + str4;
/*     */         } else {
/* 275 */           str3 = str3 + str2 + ","; }
/*     */       }
/*     */       else str3 = str3 + ",";
/*     */     }
/* 279 */     if (!(cls.isEmpty(str3).booleanValue())) str1 = str1 + cls.getLRStr(str3, ",", "leftr") + str4;
/* 280 */     str1 = cls.getLRStr(str1, str4, "leftr");
/* 281 */     String[] arrayOfString2 = str1.split(Pattern.quote(str4));
/* 282 */     return arrayOfString2;
/*     */   }
/*     */ 
/*     */   public String getAppString(String paramString1, String paramString2)
/*     */   {
/* 287 */     String str1 = paramString1;
/* 288 */     String str2 = paramString2;
/* 289 */     if (str1.indexOf("../") == -1)
/*     */     {
/* 291 */       String str3 = this.conf.getNGenre();
/* 292 */       if (!(cls.isEmpty(str3).booleanValue())) str1 = str3 + "/" + str1;
/*     */     }
/* 294 */     str1 = str1.replace("../", "");
/* 295 */     str1 = str1.replace(this.conf.xmlsfx, "");
/* 296 */     str1 = str1.replace("/", "_");
/* 297 */     str1 = str1 + "_" + str2;
/* 298 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getReturn(String[][] paramArrayOfString, String paramString)
/*     */   {
/* 303 */     String[][] arrayOfString = paramArrayOfString;
/* 304 */     String str1 = paramString;
/* 305 */     String str2 = "";
/* 306 */     if (arrayOfString != null)
/*     */     {
/* 308 */       for (int i = 0; i < arrayOfString.length; ++i)
/*     */       {
/* 310 */         if (!(arrayOfString[i][0].equals(str1)))
/*     */           continue;
/* 312 */         str2 = arrayOfString[i][1];
/* 313 */         break;
/*     */       }
/*     */     }
/*     */ 
/* 317 */     return str2;
/*     */   }
/*     */ 
/*     */   public String[][] getXInfo(String paramString1, String paramString2)
/*     */   {
/* 322 */     String str1 = paramString1;
/* 323 */     String str2 = paramString2;
/* 324 */     String[][] arrayOfString = getXInfo(str1, str2, "0");
/* 325 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   public String[][] getXInfo(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 330 */     String str1 = paramString1;
/* 331 */     String str2 = paramString1;
/* 332 */     String str3 = paramString2;
/* 333 */     String str4 = paramString3;
/* 334 */     String str5 = getAppString(str1, str3);
/* 335 */     String[][] arrayOfString1 = (String[][])(String[][])this.conf.application.getAttribute(this.conf.getAppKey(str5));
/* 336 */     Object localObject1 = arrayOfString1;
/* 337 */     if (localObject1 == null)
/*     */     {
/*     */       try
/*     */       {
/* 341 */         File localFile = new File(this.conf.application.getRealPath(this.conf.getMapPath(str2)).toString());
/* 342 */         DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
/* 343 */         DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
/* 344 */         Document localDocument = localDocumentBuilder.parse(localFile);
/* 345 */         XPath localXPath = XPathFactory.newInstance().newXPath();
/* 346 */         Node localNode1 = (Node)localXPath.evaluate("/xml/configure/node", localDocument, XPathConstants.NODE);
/* 347 */         Node localNode2 = (Node)localXPath.evaluate("/xml/configure/field", localDocument, XPathConstants.NODE);
/* 348 */         Node localNode3 = (Node)localXPath.evaluate("/xml/configure/base", localDocument, XPathConstants.NODE);
/* 349 */         String str6 = localNode1.getFirstChild().getNodeValue();
/* 350 */         String str7 = localNode2.getFirstChild().getNodeValue();
/* 351 */         String str8 = localNode3.getFirstChild().getNodeValue();
/* 352 */         if (str7.indexOf(",") >= 0)
/*     */         {
/*     */           Object localObject3;
					Object localObject2;
/*     */           Integer localInteger4;
/* 354 */           Integer localInteger1 = Integer.valueOf(0);
/* 355 */           Integer localInteger2 = Integer.valueOf(1);
					Integer localInteger3;
/* 356 */           String[] arrayOfString = str7.split(Pattern.quote(","));
/* 357 */           for (localInteger1 = Integer.valueOf(0); localInteger1.intValue() < arrayOfString.length; localInteger3 = localInteger1 = Integer.valueOf(localInteger1.intValue() + 1))
/*     */           {
/* 359 */             if (arrayOfString[localInteger1.intValue()].equals(str3)) localInteger2 = localInteger1;
/* 357 */             localObject2 = localInteger1;
/*     */           }
/*     */ 
/* 361 */           if (localInteger2.intValue() == 0) localInteger2 = Integer.valueOf(1);
/* 362 */           localInteger2 = Integer.valueOf(localInteger2.intValue() * 2 + 1);
/* 363 */           localObject2 = (NodeList)localXPath.evaluate("/xml/" + str8 + "/" + str6, localDocument, XPathConstants.NODESET);
/* 364 */           localInteger3 = Integer.valueOf(((NodeList)localObject2).getLength());
/* 365 */           String[][] arrayOfString2 = new String[localInteger3.intValue()][2];
/* 366 */           for (localInteger1 = Integer.valueOf(0); localInteger1.intValue() < localInteger3.intValue(); localInteger4 = localInteger1 = Integer.valueOf(localInteger1.intValue() + 1))
/*     */           {
/* 368 */             localObject3 = ((NodeList)localObject2).item(localInteger1.intValue()).getChildNodes();
/* 369 */             arrayOfString2[localInteger1.intValue()][0] = ((NodeList)localObject3).item(1).getFirstChild().getNodeValue();
/* 370 */             arrayOfString2[localInteger1.intValue()][1] = ((NodeList)localObject3).item(localInteger2.intValue()).getFirstChild().getNodeValue();
/*     */ 
/* 366 */             localObject3 = localInteger1;
/*     */           }
/*     */ 
/* 372 */           if (this.conf.isApp.equals("1")) this.conf.application.setAttribute(this.conf.getAppKey(str5), arrayOfString2);
/* 373 */           localObject1 = arrayOfString2;
/*     */         }
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/* 378 */         this.conf.application.removeAttribute(this.conf.getAppKey(str5));
/* 379 */         if (str4.equals("0")) localObject1 = getXInfo(str1, str3, "1");
/*     */       }
/*     */     }
/* 382 */     return ((String[][])(String[][])(String[][])localObject1);
/*     */   }
/*     */ 
/*     */   public String[][] getXInfoAry(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 387 */     String str1 = paramString1;
/* 388 */     String str2 = paramString2;
/* 389 */     String str3 = paramString3;
/* 390 */     String[] arrayOfString = getXRouteInfo(str1, str2);
/* 391 */     if (cls.isEmpty(str3).booleanValue()) str3 = this.conf.getActiveThings(str2);
/* 392 */     return getXInfo(arrayOfString[0], str3);
/*     */   }
/*     */ 
/*     */   public String[] getXRouteInfo(String paramString1, String paramString2)
/*     */   {
/* 397 */     String str1 = "";
/* 398 */     String str2 = paramString1.toLowerCase();
/* 399 */     String str3 = paramString2;
/* 400 */     if (str3.equals("cfg")) str1 = "common";
/* 401 */     else if (str3.equals("lng")) str1 = "common/language";
/* 402 */     else if (str3.equals("sel")) str1 = "common/language";
/* 403 */     else if (str3.equals("tpl")) str1 = "common/template";
/*     */     else str1 = "common";
/* 405 */     if ((str2.length() >= 7) && 
/* 407 */       (str2.substring(0, 7).equals("global.")))
/*     */     {
/* 409 */       str2 = cls.getLRStr(str2, ".", "rightr");
/* 410 */       if (str2.indexOf(":") >= 0)
/*     */       {
/* 412 */         str1 = cls.getLRStr(str2, ":", "left") + "/" + str1;
/* 413 */         str2 = cls.getLRStr(str2, ":", "right");
/*     */       }
/* 415 */       str1 = this.conf.getActualRouteB(str1.replace(".", "/"));
/*     */     }
/*     */ 
/* 418 */     String[] arrayOfString = new String[2];
/* 419 */     arrayOfString[0] = str1 + "/" + cls.getLRStr(str2, ".", "leftr").replace(".", "/") + this.conf.xmlsfx;
/* 420 */     arrayOfString[1] = cls.getLRStr(str2, ".", "right");
/* 421 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   public String getXRootAtt(String paramString1, String paramString2)
/*     */   {
/* 426 */     String str1 = "";
/* 427 */     String str2 = paramString2;
/* 428 */     String str3 = paramString1;
/*     */     try
/*     */     {
/* 431 */       File localFile = new File(this.conf.application.getRealPath(this.conf.getMapPath(str3)).toString());
/* 432 */       DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
/* 433 */       DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
/* 434 */       Document localDocument = localDocumentBuilder.parse(localFile);
/* 435 */       str1 = localDocument.getDocumentElement().getAttribute(str2);
/*     */     } catch (Exception localException) {
/*     */     }
/* 438 */     return str1;
/*     */   }
/*     */ 
/*     */   public String itake(String paramString1, String paramString2)
/*     */   {
/* 443 */     String str1 = "";
/* 444 */     String str2 = paramString1;
/* 445 */     String str3 = paramString2;
/* 446 */     str1 = itake(str2, str3, "", "");
/* 447 */     return str1;
/*     */   }
/*     */ 
/*     */   public String itake(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 452 */     String str1 = "";
/* 453 */     String str2 = paramString1;
/* 454 */     String str3 = paramString2;
/* 455 */     String str4 = paramString3;
/* 456 */     str1 = itake(str2, str3, str4, "");
/* 457 */     return str1;
/*     */   }
/*     */ 
/*     */   public String itake(String paramString1, String paramString2, String paramString3, String paramString4)
/*     */   {
/* 462 */     String str1 = paramString1;
/* 463 */     String str2 = paramString2;
/* 464 */     String str3 = paramString3;
/* 465 */     String str4 = paramString4;
/* 466 */     String[] arrayOfString1 = getXRouteInfo(str1, str2);
/* 467 */     String[][] arrayOfString = getXInfoAry(str1, str2, str4);
/* 468 */     String str5 = getReturn(arrayOfString, arrayOfString1[1]);
/* 469 */     String str6 = cls.getLRStr(str1, ".", "leftr");
/* 470 */     String str7 = cls.getLRStr(cls.getLRStr(str1, ":", "leftr"), "global.", "right");
/* 471 */     if ((cls.isEmpty(str7).booleanValue()) || (str7.equals(str1))) str7 = this.conf.getNGenre();
/* 472 */     str5 = str5.replace("{$>this}", str6);
/* 473 */     str5 = str5.replace("{$>this.genre}", str7);
/*     */ 
/* 475 */     String[] arrayOfString2 = str7.split(Pattern.quote("/"));
/* 476 */     int i = arrayOfString2.length;
/* 477 */     if (i == 2) str5 = str5.replace("{$>this.genre.parents.1}", arrayOfString2[0]);
/* 478 */     if (i == 3)
/*     */     {
/* 480 */       str5 = str5.replace("{$>this.genre.parents.1}", arrayOfString2[0] + "/" + arrayOfString2[1]);
/* 481 */       str5 = str5.replace("{$>this.genre.parents.2}", arrayOfString2[0]);
/*     */     }
/* 483 */     if (i == 4)
/*     */     {
/* 485 */       str5 = str5.replace("{$>this.genre.parents.1}", arrayOfString2[0] + "/" + arrayOfString2[1] + "/" + arrayOfString2[2]);
/* 486 */       str5 = str5.replace("{$>this.genre.parents.2}", arrayOfString2[0] + "/" + arrayOfString2[1]);
/* 487 */       str5 = str5.replace("{$>this.genre.parents.3}", arrayOfString2[0]);
/*     */     }
/*     */ 
/* 490 */     if (!(cls.isEmpty(str3).booleanValue()))
/*     */     {
/* 492 */       String[] arrayOfString3 = str3.split(Pattern.quote("|"));
/* 493 */       for (int j = 0; j < arrayOfString3.length; ++j)
/*     */       {
/* 495 */         String str8 = arrayOfString3[j];
/* 496 */         if (cls.isEmpty(str8).booleanValue())
/*     */           continue;
/* 498 */         String[] arrayOfString4 = str8.split(Pattern.quote("="));
/* 499 */         if (arrayOfString4.length != 2) continue; str5 = str5.replace("{$" + arrayOfString4[0] + "}", arrayOfString4[1]);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 504 */     str5 = str5.replace("{$>now}", cls.getDate());
/* 505 */     return str5;
/*     */   }
/*     */ 
/*     */   public String[][] itakes(String paramString1, String paramString2)
/*     */   {
/* 510 */     String[][] arrayOfString = (String[][])null;
/* 511 */     String str1 = paramString1;
/* 512 */     String str2 = paramString2;
/* 513 */     arrayOfString = getXInfoAry(str1, str2, "");
/* 514 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   public String[][] itakes(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 519 */     String[][] arrayOfString = (String[][])null;
/* 520 */     String str1 = paramString1;
/* 521 */     String str2 = paramString2;
/* 522 */     String str3 = paramString3;
/* 523 */     arrayOfString = getXInfoAry(str1, str2, str3);
/* 524 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   public String ireplace(String paramString1, String paramString2)
/*     */   {
/* 529 */     String str1 = "";
/* 530 */     String str2 = paramString1;
/* 531 */     String str3 = paramString2;
/* 532 */     str1 = ireplace(str2, str3, "");
/* 533 */     return str1;
/*     */   }
/*     */ 
/*     */   public String ireplace(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 538 */     String str1 = "";
/* 539 */     String str2 = paramString1;
/* 540 */     String str3 = paramString2;
/* 541 */     String str4 = paramString3;
/* 542 */     str1 = itake(str2, str3, str4);
/* 543 */     str1 = creplace(str1);
/* 544 */     return str1;
/*     */   }
/*     */ 
/*     */   public Boolean iset(String paramString1, String paramString2, String paramString3, String paramString4)
/*     */   {
/* 549 */     Boolean localBoolean = Boolean.valueOf(false);
/* 550 */     String str1 = paramString1;
/* 551 */     String str2 = paramString2;
/* 552 */     String str3 = paramString3;
/* 553 */     String str4 = paramString4;
/* 554 */     String[] arrayOfString1 = getXRouteInfo(str1, str2);
/* 555 */     String str5 = arrayOfString1[0];
/* 556 */     String str6 = arrayOfString1[1];
/* 557 */     if (!(cls.isEmpty(str5).booleanValue()))
/*     */     {
/*     */       try
/*     */       {
/* 561 */         File localFile = new File(this.conf.application.getRealPath(this.conf.getMapPath(str5)).toString());
/* 562 */         DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
/* 563 */         DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
/* 564 */         Document localDocument = localDocumentBuilder.parse(localFile);
/* 565 */         XPath localXPath = XPathFactory.newInstance().newXPath();
/* 566 */         Node localNode1 = (Node)localXPath.evaluate("/xml/configure/node", localDocument, XPathConstants.NODE);
/* 567 */         Node localNode2 = (Node)localXPath.evaluate("/xml/configure/field", localDocument, XPathConstants.NODE);
/* 568 */         Node localNode3 = (Node)localXPath.evaluate("/xml/configure/base", localDocument, XPathConstants.NODE);
/* 569 */         String str8 = localNode1.getFirstChild().getNodeValue();
/* 570 */         String str9 = localNode2.getFirstChild().getNodeValue();
/* 571 */         String str10 = localNode3.getFirstChild().getNodeValue();
/* 572 */         if (str9.indexOf(",") >= 0)
/*     */         {
/* 574 */           int i = 1;
/* 575 */           String[] arrayOfString2 = str9.split(Pattern.quote(","));
/* 576 */           for (int j = 0; j < arrayOfString2.length; ++j)
/*     */           {
/* 578 */             if (!(arrayOfString2[j].equals(str3))) continue; i = j;
/*     */           }
/* 580 */           if (i == 0) i = 1;
/* 581 */           i = i * 2 + 1;
/* 582 */           NodeList localNodeList1 = (NodeList)localXPath.evaluate("/xml/" + str10 + "/" + str8 + "[" + cls.getLRStr(str9, ",", "left") + "='" + str6 + "']", localDocument, XPathConstants.NODESET);
/* 583 */           if (localNodeList1 != null)
/*     */           {
/* 585 */             NodeList localNodeList2 = localNodeList1.item(0).getChildNodes();
/* 586 */             localNodeList2.item(i).getFirstChild().setNodeValue(str4);
/* 587 */             TransformerFactory localTransformerFactory = TransformerFactory.newInstance();
/* 588 */             Transformer localTransformer = localTransformerFactory.newTransformer();
/* 589 */             DOMSource localDOMSource = new DOMSource(localDocument);
/* 590 */             StreamResult localStreamResult = new StreamResult(new File(this.conf.application.getRealPath(this.conf.getMapPath(str5)).toString()));
/* 591 */             localTransformer.transform(localDOMSource, localStreamResult);
/* 592 */             localBoolean = Boolean.valueOf(true);
/*     */           }
/*     */         }
/*     */       } catch (Exception localException) {
/*     */       }
/*     */     }
/* 598 */     if (localBoolean.booleanValue() == true)
/*     */     {
/* 600 */       String str7 = getAppString(str5, str3);
/* 601 */       this.conf.application.removeAttribute(this.conf.getAppKey(str7));
/*     */     }
/* 603 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   public jt(conf paramconf)
/*     */   {
/* 608 */     this.conf = paramconf;
/*     */   }
/*     */ }