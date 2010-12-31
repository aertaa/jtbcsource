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
/* 165 */       else if (str2.equals("md5")) { str1 = encode.base64encode(cparameter(str3).getBytes());
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
//System.out.println("cvalue:"+paramString+":"+str1);
/* 188 */     return str1;
/*     */   }
/*     */ 
/*     */   public String creplace(String paramString)
/*     */   {
/* 193 */     String str1 = paramString;
/*     */ 
/* 195 */     Pattern localPattern = Pattern.compile("(\\{\\$=(.[^\\}]*)\\})");
/* 196 */     Matcher localMatcher = localPattern.matcher(str1);
/* 197 */     while (localMatcher.find())
/*     */     {
/* 199 */       String str2 = localMatcher.group(1);
/* 200 */       String str3 = localMatcher.group(2);
/* 201 */       str1 = str1.replace(str2, cvalue(str3));
/*     */     }
System.out.println("creplace:"+paramString+":"+str1);
/* 203 */     return str1;
/*     */   }
/*     */ 
/*     */   public String[] fixParameterAry(String[] paramArrayOfString)
/*     */   {
/* 208 */     String[] arrayOfString1 = paramArrayOfString;
/* 209 */     String str1 = "";
/* 210 */     String str2 = "";
/* 211 */     String str3 = "";
/* 212 */     String str4 = "{@}a{@}b{@}c{@}";
/* 213 */     for (int i = 0; i < arrayOfString1.length; ++i)
/*     */     {
/* 215 */       str2 = arrayOfString1[i];
/* 216 */       str2 = str2.trim();
/* 217 */       if (!(cls.isEmpty(str2).booleanValue()))
/*     */       {
/* 219 */         if ((!(str2.equals("\""))) && (str2.substring(0, 1).equals("\"")) && (str2.substring(str2.length() - 1, str2.length()).equals("\"")))
/*     */         {
/* 221 */           if (!(cls.isEmpty(str3).booleanValue()))
/*     */           {
/* 223 */             str1 = str1 + cls.getLRStr(str3, ",", "leftr") + str4;
/* 224 */             str3 = "";
/*     */           }
/* 226 */           str1 = str1 + str2 + str4;
/*     */         } else {
/* 228 */           str3 = str3 + str2 + ","; }
/*     */       }
/*     */       else str3 = str3 + ",";
/*     */     }
/* 232 */     if (!(cls.isEmpty(str3).booleanValue())) str1 = str1 + cls.getLRStr(str3, ",", "leftr") + str4;
/* 233 */     str1 = cls.getLRStr(str1, str4, "leftr");
/* 234 */     String[] arrayOfString2 = str1.split(Pattern.quote(str4));
/* 235 */     return arrayOfString2;
/*     */   }
/*     */ 
/*     */   public String getAppString(String paramString1, String paramString2)
/*     */   {
/* 240 */     String str1 = paramString1;
/* 241 */     String str2 = paramString2;
/* 242 */     if (str1.indexOf("../") == -1)
/*     */     {
/* 244 */       String str3 = this.conf.getNGenre();
/* 245 */       if (!(cls.isEmpty(str3).booleanValue())) str1 = str3 + "/" + str1;
/*     */     }
/* 247 */     str1 = str1.replace("../", "");
/* 248 */     str1 = str1.replace(this.conf.xmlsfx, "");
/* 249 */     str1 = str1.replace("/", "_");
/* 250 */     str1 = str1 + "_" + str2;
/* 251 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getReturn(String[][] paramArrayOfString, String paramString)
/*     */   {
/* 256 */     String[][] arrayOfString = paramArrayOfString;
/* 257 */     String str1 = paramString;
/* 258 */     String str2 = "";
/* 259 */     if (arrayOfString != null)
/*     */     {
/* 261 */       for (int i = 0; i < arrayOfString.length; ++i)
/*     */       {
/* 263 */         if (!(arrayOfString[i][0].equals(str1)))
/*     */           continue;
/* 265 */         str2 = arrayOfString[i][1];
/* 266 */         break;
/*     */       }
/*     */     }
/*     */ 
/* 270 */     return str2;
/*     */   }
/*     */ 
/*     */   public String[][] getXInfo(String paramString1, String paramString2)
/*     */   {
/* 275 */     String str1 = paramString1;
/* 276 */     String str2 = paramString2;
/* 277 */     String[][] arrayOfString = getXInfo(str1, str2, "0");
/* 278 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   public String[][] getXInfo(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 283 */     String str1 = paramString1;
/* 284 */     String str2 = paramString1;
/* 285 */     String str3 = paramString2;
/* 286 */     String str4 = paramString3;
/* 287 */     String str5 = getAppString(str1, str3);
/* 288 */     String[][] arrayOfString1 = (String[][])(String[][])this.conf.application.getAttribute(this.conf.getAppKey(str5));
/* 289 */     Object localObject1 = arrayOfString1;
/* 290 */     if (localObject1 == null)
/*     */     {
/*     */       try
/*     */       {
/* 294 */         File localFile = new File(this.conf.application.getRealPath(this.conf.getMapPath(str2)).toString());
/* 295 */         DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
/* 296 */         DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
/* 297 */         Document localDocument = localDocumentBuilder.parse(localFile);
/* 298 */         XPath localXPath = XPathFactory.newInstance().newXPath();
/* 299 */         Node localNode1 = (Node)localXPath.evaluate("/xml/configure/node", localDocument, XPathConstants.NODE);
/* 300 */         Node localNode2 = (Node)localXPath.evaluate("/xml/configure/field", localDocument, XPathConstants.NODE);
/* 301 */         Node localNode3 = (Node)localXPath.evaluate("/xml/configure/base", localDocument, XPathConstants.NODE);
/* 302 */         String str6 = localNode1.getFirstChild().getNodeValue();
/* 303 */         String str7 = localNode2.getFirstChild().getNodeValue();
/* 304 */         String str8 = localNode3.getFirstChild().getNodeValue();
/* 305 */         if (str7.indexOf(",") >= 0)
/*     */         {
/*     */           Object localObject3;
/*     */           Integer localInteger4;
/* 307 */           Integer localInteger1 = Integer.valueOf(0);
/* 308 */           Integer localInteger2 = Integer.valueOf(1);
					Integer localInteger3;
					Object localObject2;
/* 309 */           String[] arrayOfString = str7.split(Pattern.quote(","));
/* 310 */           for (localInteger1 = Integer.valueOf(0); localInteger1.intValue() < arrayOfString.length;localInteger3 = localInteger1 = Integer.valueOf(localInteger1.intValue() + 1))
/*     */           {
/* 312 */             if (arrayOfString[localInteger1.intValue()].equals(str3)) localInteger2 = localInteger1;
/* 310 */             localObject2 = localInteger1;
/*     */           }
/*     */ 
/* 314 */           if (localInteger2.intValue() == 0) localInteger2 = Integer.valueOf(1);
/* 315 */           localInteger2 = Integer.valueOf(localInteger2.intValue() * 2 + 1);
/* 316 */           localObject2 = (NodeList)localXPath.evaluate("/xml/" + str8 + "/" + str6, localDocument, XPathConstants.NODESET);
/* 317 */           localInteger3 = Integer.valueOf(((NodeList)localObject2).getLength());
/* 318 */           String[][] arrayOfString2 = new String[localInteger3.intValue()][2];
/* 319 */           for (localInteger1 = Integer.valueOf(0); localInteger1.intValue() < localInteger3.intValue(); localInteger4 = localInteger1 = Integer.valueOf(localInteger1.intValue() + 1))
/*     */           {
/* 321 */             localObject3 = ((NodeList)localObject2).item(localInteger1.intValue()).getChildNodes();
/* 322 */             arrayOfString2[localInteger1.intValue()][0] = ((NodeList)localObject3).item(1).getFirstChild().getNodeValue();
/* 323 */             arrayOfString2[localInteger1.intValue()][1] = ((NodeList)localObject3).item(localInteger2.intValue()).getFirstChild().getNodeValue();
/*     */ 
/* 319 */             localObject3 = localInteger1;
/*     */           }
/*     */ 
/* 325 */           if (this.conf.isApp.equals("1")) this.conf.application.setAttribute(this.conf.getAppKey(str5), arrayOfString2);
/* 326 */           localObject1 = arrayOfString2;
/*     */         }
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/* 331 */         this.conf.application.removeAttribute(this.conf.getAppKey(str5));
/* 332 */         if (str4.equals("0")) localObject1 = getXInfo(str1, str3, "1");
/*     */       }
/*     */     }
/* 335 */     return ((String[][])localObject1);
/*     */   }
/*     */ 
/*     */   public String[][] getXInfoAry(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 340 */     String str1 = paramString1;
/* 341 */     String str2 = paramString2;
/* 342 */     String str3 = paramString3;
/* 343 */     String[] arrayOfString = getXRouteInfo(str1, str2);
/* 344 */     if (cls.isEmpty(str3).booleanValue()) str3 = this.conf.getActiveThings(str2);
/* 345 */     return getXInfo(arrayOfString[0], str3);
/*     */   }
/*     */ 
/*     */   public String[] getXRouteInfo(String paramString1, String paramString2)
/*     */   {
/* 350 */     String str1 = "";
/* 351 */     String str2 = paramString1.toLowerCase();
/* 352 */     String str3 = paramString2;
/* 353 */     if (str3.equals("cfg")) str1 = "common";
/* 354 */     else if (str3.equals("lng")) str1 = "common/language";
/* 355 */     else if (str3.equals("sel")) str1 = "common/language";
/* 356 */     else if (str3.equals("tpl")) str1 = "common/template";
/*     */     else str1 = "common";
/* 358 */     if ((str2.length() >= 7) && 
/* 360 */       (str2.substring(0, 7).equals("global.")))
/*     */     {
/* 362 */       str2 = cls.getLRStr(str2, ".", "rightr");
/* 363 */       if (str2.indexOf(":") >= 0)
/*     */       {
/* 365 */         str1 = cls.getLRStr(str2, ":", "left") + "/" + str1;
/* 366 */         str2 = cls.getLRStr(str2, ":", "right");
/*     */       }
/* 368 */       str1 = this.conf.getActualRouteB(str1.replace(".", "/"));
/*     */     }
/*     */ 
/* 371 */     String[] arrayOfString = new String[2];
/* 372 */     arrayOfString[0] = str1 + "/" + cls.getLRStr(str2, ".", "leftr").replace(".", "/") + this.conf.xmlsfx;
/* 373 */     arrayOfString[1] = cls.getLRStr(str2, ".", "right");
/* 374 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   public String getXRootAtt(String paramString1, String paramString2)
/*     */   {
/* 379 */     String str1 = "";
/* 380 */     String str2 = paramString2;
/* 381 */     String str3 = paramString1;
/*     */     try
/*     */     {
/* 384 */       File localFile = new File(this.conf.application.getRealPath(this.conf.getMapPath(str3)).toString());
/* 385 */       DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
/* 386 */       DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
/* 387 */       Document localDocument = localDocumentBuilder.parse(localFile);
/* 388 */       str1 = localDocument.getDocumentElement().getAttribute(str2);
/*     */     } catch (Exception localException) {
/*     */     }
/* 391 */     return str1;
/*     */   }
/*     */ 
/*     */   public String itake(String paramString1, String paramString2)
/*     */   {
/* 396 */     String str1 = "";
/* 397 */     String str2 = paramString1;
/* 398 */     String str3 = paramString2;
/* 399 */     str1 = itake(str2, str3, "", "");
/* 400 */     return str1;
/*     */   }
/*     */ 
/*     */   public String itake(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 405 */     String str1 = "";
/* 406 */     String str2 = paramString1;
/* 407 */     String str3 = paramString2;
/* 408 */     String str4 = paramString3;
/* 409 */     str1 = itake(str2, str3, str4, "");
/* 410 */     return str1;
/*     */   }
/*     */ 
/*     */   public String itake(String paramString1, String paramString2, String paramString3, String paramString4)
/*     */   {
/* 415 */     String str1 = paramString1;
/* 416 */     String str2 = paramString2;
/* 417 */     String str3 = paramString3;
/* 418 */     String str4 = paramString4;
/* 419 */     String[] arrayOfString1 = getXRouteInfo(str1, str2);
/* 420 */     String[][] arrayOfString = getXInfoAry(str1, str2, str4);
/* 421 */     String str5 = getReturn(arrayOfString, arrayOfString1[1]);
/* 422 */     String str6 = cls.getLRStr(str1, ".", "leftr");
/* 423 */     String str7 = cls.getLRStr(cls.getLRStr(str1, ":", "leftr"), "global.", "right");
/* 424 */     if ((cls.isEmpty(str7).booleanValue()) || (str7.equals(str1))) str7 = this.conf.getNGenre();
/* 425 */     str5 = str5.replace("{$>this}", str6);
/* 426 */     str5 = str5.replace("{$>this.genre}", str7);
/*     */ 
/* 428 */     String[] arrayOfString2 = str7.split(Pattern.quote("/"));
/* 429 */     int i = arrayOfString2.length;
/* 430 */     if (i == 2) str5 = str5.replace("{$>this.genre.parents.1}", arrayOfString2[0]);
/* 431 */     if (i == 3)
/*     */     {
/* 433 */       str5 = str5.replace("{$>this.genre.parents.1}", arrayOfString2[0] + "/" + arrayOfString2[1]);
/* 434 */       str5 = str5.replace("{$>this.genre.parents.2}", arrayOfString2[0]);
/*     */     }
/* 436 */     if (i == 4)
/*     */     {
/* 438 */       str5 = str5.replace("{$>this.genre.parents.1}", arrayOfString2[0] + "/" + arrayOfString2[1] + "/" + arrayOfString2[2]);
/* 439 */       str5 = str5.replace("{$>this.genre.parents.2}", arrayOfString2[0] + "/" + arrayOfString2[1]);
/* 440 */       str5 = str5.replace("{$>this.genre.parents.3}", arrayOfString2[0]);
/*     */     }
/*     */ 
/* 443 */     if (!(cls.isEmpty(str3).booleanValue()))
/*     */     {
/* 445 */       String[] arrayOfString3 = str3.split(Pattern.quote("|"));
/* 446 */       for (int j = 0; j < arrayOfString3.length; ++j)
/*     */       {
/* 448 */         String str8 = arrayOfString3[j];
/* 449 */         if (cls.isEmpty(str8).booleanValue())
/*     */           continue;
/* 451 */         String[] arrayOfString4 = str8.split(Pattern.quote("="));
/* 452 */         if (arrayOfString4.length != 2) continue; str5 = str5.replace("{$" + arrayOfString4[0] + "}", arrayOfString4[1]);
/*     */       }
/*     */ 
/*     */     }
/*     */ 		//System.out.println("itakeStr:"+str5);
/* 457 */     return str5;
/*     */   }
/*     */ 
/*     */   public String[][] itakes(String paramString1, String paramString2)
/*     */   {
/* 462 */     String[][] arrayOfString = (String[][])null;
/* 463 */     String str1 = paramString1;
/* 464 */     String str2 = paramString2;
/* 465 */     arrayOfString = getXInfoAry(str1, str2, "");
/* 466 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   public String[][] itakes(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 471 */     String[][] arrayOfString = (String[][])null;
/* 472 */     String str1 = paramString1;
/* 473 */     String str2 = paramString2;
/* 474 */     String str3 = paramString3;
/* 475 */     arrayOfString = getXInfoAry(str1, str2, str3);
/* 476 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   public String ireplace(String paramString1, String paramString2)
/*     */   {
/* 481 */     String str1 = "";
/* 482 */     String str2 = paramString1;
/* 483 */     String str3 = paramString2;
/* 484 */     str1 = ireplace(str2, str3, "");
/* 485 */     return str1;
/*     */   }
/*     */ 
/*     */   public String ireplace(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 490 */     String str1 = "";
/* 491 */     String str2 = paramString1;
/* 492 */     String str3 = paramString2;
/* 493 */     String str4 = paramString3;
/* 494 */     str1 = itake(str2, str3, str4);
/* 495 */     str1 = creplace(str1);
/* 496 */     return str1;
/*     */   }
/*     */ 
/*     */   public Boolean iset(String paramString1, String paramString2, String paramString3, String paramString4)
/*     */   {
/* 501 */     Boolean localBoolean = Boolean.valueOf(false);
/* 502 */     String str1 = paramString1;
/* 503 */     String str2 = paramString2;
/* 504 */     String str3 = paramString3;
/* 505 */     String str4 = paramString4;
/* 506 */     String[] arrayOfString1 = getXRouteInfo(str1, str2);
/* 507 */     String str5 = arrayOfString1[0];
/* 508 */     String str6 = arrayOfString1[1];
/* 509 */     if (!(cls.isEmpty(str5).booleanValue()))
/*     */     {
/*     */       try
/*     */       {
/* 513 */         File localFile = new File(this.conf.application.getRealPath(this.conf.getMapPath(str5)).toString());
/* 514 */         DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
/* 515 */         DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
/* 516 */         Document localDocument = localDocumentBuilder.parse(localFile);
/* 517 */         XPath localXPath = XPathFactory.newInstance().newXPath();
/* 518 */         Node localNode1 = (Node)localXPath.evaluate("/xml/configure/node", localDocument, XPathConstants.NODE);
/* 519 */         Node localNode2 = (Node)localXPath.evaluate("/xml/configure/field", localDocument, XPathConstants.NODE);
/* 520 */         Node localNode3 = (Node)localXPath.evaluate("/xml/configure/base", localDocument, XPathConstants.NODE);
/* 521 */         String str8 = localNode1.getFirstChild().getNodeValue();
/* 522 */         String str9 = localNode2.getFirstChild().getNodeValue();
/* 523 */         String str10 = localNode3.getFirstChild().getNodeValue();
/* 524 */         if (str9.indexOf(",") >= 0)
/*     */         {
/* 526 */           int i = 1;
/* 527 */           String[] arrayOfString2 = str9.split(Pattern.quote(","));
/* 528 */           for (int j = 0; j < arrayOfString2.length; ++j)
/*     */           {
/* 530 */             if (!(arrayOfString2[j].equals(str3))) continue; i = j;
/*     */           }
/* 532 */           if (i == 0) i = 1;
/* 533 */           i = i * 2 + 1;
/* 534 */           NodeList localNodeList1 = (NodeList)localXPath.evaluate("/xml/" + str10 + "/" + str8 + "[" + cls.getLRStr(str9, ",", "left") + "='" + str6 + "']", localDocument, XPathConstants.NODESET);
/* 535 */           if (localNodeList1 != null)
/*     */           {
/* 537 */             NodeList localNodeList2 = localNodeList1.item(0).getChildNodes();
/* 538 */             localNodeList2.item(i).getFirstChild().setNodeValue(str4);
/* 539 */             TransformerFactory localTransformerFactory = TransformerFactory.newInstance();
/* 540 */             Transformer localTransformer = localTransformerFactory.newTransformer();
/* 541 */             DOMSource localDOMSource = new DOMSource(localDocument);
/* 542 */             StreamResult localStreamResult = new StreamResult(new File(this.conf.application.getRealPath(this.conf.getMapPath(str5)).toString()));
/* 543 */             localTransformer.transform(localDOMSource, localStreamResult);
/* 544 */             localBoolean = Boolean.valueOf(true);
/*     */           }
/*     */         }
/*     */       } catch (Exception localException) {
/*     */       }
/*     */     }
/* 550 */     if (localBoolean.booleanValue() == true)
/*     */     {
/* 552 */       String str7 = getAppString(str5, str3);
/* 553 */       this.conf.application.removeAttribute(this.conf.getAppKey(str7));
/*     */     }
/* 555 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   public jt(conf paramconf)
/*     */   {
/* 560 */     this.conf = paramconf;
/*     */   }
/*     */ }