/*      */ package jtbc;
/*      */ 
/*      */ import java.io.BufferedReader;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.net.Socket;
/*      */ import java.net.URL;
/*      */ import java.net.URLConnection;
/*      */ import java.util.Date;
/*      */ import java.util.regex.Pattern;
/*      */ import javax.servlet.ServletContext;
/*      */ import javax.servlet.http.HttpSession;
/*      */ import jtbc.dbc.dbc;
/*      */ 
/*      */ public class common
/*      */ {
/*      */   public conf conf;
/*      */ 
/*      */   public String curl(String paramString1, String paramString2)
/*      */   {
/*   17 */     Object localObject = "";
/*   18 */     String str1 = paramString1;
/*   19 */     String str2 = paramString2;
/*   20 */     if (!(cls.isEmpty(str2).booleanValue()))
/*      */     {
/*   22 */       if (cls.getLeft(str2, Integer.valueOf(1)).equals("/")) localObject = str2;
/*   25 */       else if ((cls.isEmpty(str1).booleanValue()) || (cls.getRight(str1, Integer.valueOf(1)).equals("/"))) localObject = str1 + str2;
/*      */       else localObject = str1 + "/" + str2;
/*      */     }
/*      */ 
/*   29 */     return ((String)localObject);
/*      */   }
/*      */ 
/*      */   public String crValcodeTpl(String paramString)
/*      */   {
/*   34 */     String str1 = "";
/*   35 */     String str2 = paramString;
/*   36 */     String str3 = this.conf.jt.itake("global.config.nvalidate", "cfg");
/*   37 */     str1 = crValHtml(str2, str3, "{@valcode@}");
/*   38 */     return str1;
/*      */   }
/*      */ 
/*      */   public String crValHtml(String paramString1, String paramString2, String paramString3)
/*      */   {
/*   43 */     Object localObject1 = "";
/*      */ 
/*   45 */     String str2 = paramString1;
/*   46 */     String str3 = paramString2;
/*   47 */     String str4 = paramString3;
/*   48 */     localObject1 = str2;
/*   49 */     Object localObject2 = "";
/*   50 */     String str1 = cls.ctemplate((String)localObject1, str4);
/*   51 */     if (str3.equals("1")) localObject2 = str1;
/*   52 */     localObject1 = cls.ctemplates((String)localObject1, str4, (String)localObject2);
/*   53 */     return ((String)(String)localObject1);
/*      */   }
/*      */ 
/*      */   public Boolean ckValcode(String paramString)
/*      */   {
/*   58 */     Boolean localBoolean = Boolean.valueOf(false);
/*   59 */     String str1 = paramString;
/*   60 */     String str2 = (String)this.conf.session.getAttribute("valcode");
/*   61 */     if (!(cls.isEmpty(str2).booleanValue()))
/*      */     {
/*   63 */       str1 = cls.getString(str1);
/*   64 */       str2 = cls.getString(str2);
/*   65 */       str1 = str1.toLowerCase();
/*   66 */       str2 = str2.toLowerCase();
/*   67 */       if (str1.equals(str2)) localBoolean = Boolean.valueOf(true);
/*      */     }
/*   69 */     return localBoolean;
/*      */   }
/*      */ 
/*      */   public Boolean ckValcodes(String paramString)
/*      */   {
/*   74 */     Boolean localBoolean = Boolean.valueOf(true);
/*   75 */     String str1 = paramString;
/*   76 */     String str2 = this.conf.jt.itake("global.config.nvalidate", "cfg");
/*   77 */     if (str2.equals("1")) localBoolean = ckValcode(str1);
/*   78 */     return localBoolean;
/*      */   }
/*      */ 
/*      */   public Integer dataDelete(String paramString1, String paramString2, String paramString3)
/*      */   {
/*   83 */     Integer localInteger = Integer.valueOf(0);
/*   84 */     String str1 = paramString1;
/*   85 */     String str2 = paramString2;
/*   86 */     String str3 = paramString3;
/*   87 */     localInteger = dataDelete(str1, str2, str3, "");
/*   88 */     return localInteger;
/*      */   }
/*      */ 
/*      */   public Integer dataDelete(String paramString1, String paramString2, String paramString3, String paramString4)
/*      */   {
/*   93 */     String str1 = cls.getString(paramString1);
/*   94 */     String str2 = cls.getString(paramString2);
/*   95 */     String str3 = cls.getString(paramString3);
/*   96 */     String str4 = cls.getString(paramString4);
/*   97 */     Integer localInteger = Integer.valueOf(-101);
/*   98 */     if (cls.cidary(str3).booleanValue())
/*      */     {
/*  100 */       String str5 = "delete from " + str1 + " where " + str2 + " in (" + str3 + ")";
/*  101 */       if (!(cls.isEmpty(str4).booleanValue())) str5 = str5 + str4;
/*  102 */       dbc localdbc = db.newInstance(this.conf);
/*  103 */       localInteger = Integer.valueOf(localdbc.Executes(str5));
/*      */     }
/*  105 */     return localInteger;
/*      */   }
/*      */ 
/*      */   public Integer dataSwitch(String paramString1, String paramString2, String paramString3, String paramString4)
/*      */   {
/*  110 */     Integer localInteger = Integer.valueOf(0);
/*  111 */     String str1 = paramString1;
/*  112 */     String str2 = paramString2;
/*  113 */     String str3 = paramString3;
/*  114 */     String str4 = paramString4;
/*  115 */     localInteger = dataSwitch(str1, str2, str3, str4, "");
/*  116 */     return localInteger;
/*      */   }
/*      */ 
/*      */   public Integer dataSwitch(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
/*      */   {
/*  121 */     String str1 = cls.getString(paramString1);
/*  122 */     String str2 = cls.getString(paramString2);
/*  123 */     String str3 = cls.getString(paramString3);
/*  124 */     String str4 = cls.getString(paramString4);
/*  125 */     String str5 = cls.getString(paramString5);
/*  126 */     Integer localInteger = Integer.valueOf(-101);
/*  127 */     if (cls.cidary(str4).booleanValue())
/*      */     {
/*  129 */       String str6 = "update " + str1 + " set " + str2 + "=abs(" + str2 + "-1) where " + str3 + " in (" + str4 + ")";
/*  130 */       if (!(cls.isEmpty(str5).booleanValue())) str6 = str6 + str5;
/*  131 */       dbc localdbc = db.newInstance(this.conf);
/*  132 */       localInteger = Integer.valueOf(localdbc.Executes(str6));
/*      */     }
/*  134 */     return localInteger;
/*      */   }
/*      */ 
/*      */   public Boolean directoryDelete(String paramString)
/*      */   {
/*  139 */     Boolean localBoolean = Boolean.valueOf(true);
/*  140 */     String str = paramString;
/*  141 */     File localFile = new File(str);
/*  142 */     if ((localFile.exists()) && (localFile.isDirectory()))
/*      */     {
/*  144 */       File[] arrayOfFile = localFile.listFiles();
/*  145 */       for (int i = 0; i < arrayOfFile.length; ++i)
/*      */       {
/*  147 */         if ((arrayOfFile[i].isFile()) && 
/*  149 */           (!(arrayOfFile[i].delete()))) localBoolean = Boolean.valueOf(false);
/*      */ 
/*  151 */         if ((!(arrayOfFile[i].isDirectory())) || 
/*  153 */           (directoryDelete(arrayOfFile[i].toString()).booleanValue())) continue; localBoolean = Boolean.valueOf(false);
/*      */       }
/*      */ 
/*  156 */       if (!(localFile.delete())) localBoolean = Boolean.valueOf(false);
/*      */     }
/*  158 */     return localBoolean;
/*      */   }
/*      */ 
/*      */   public Boolean directoryCreate(String paramString)
/*      */   {
/*  163 */     Boolean localBoolean = Boolean.valueOf(false);
/*  164 */     String str = paramString;
/*  165 */     File localFile = new File(str);
/*  166 */     if ((localFile.exists()) && (localFile.isDirectory())) localBoolean = Boolean.valueOf(true);
/*      */     else
/*      */     {
/*      */       try
/*      */       {
/*  171 */         localFile.mkdirs();
/*  172 */         localBoolean = Boolean.valueOf(true);
/*      */       } catch (Exception localException) {
/*      */       }
/*      */     }
/*  176 */     return localBoolean;
/*      */   }
/*      */ 
/*      */   public Boolean directoryCreateNew(String paramString)
/*      */   {
/*  181 */     Boolean localBoolean = Boolean.valueOf(false);
/*  182 */     String str = paramString;
/*  183 */     File localFile = new File(str);
/*  184 */     if ((!(localFile.exists())) || (!(localFile.isDirectory())))
/*      */     {
/*      */       try
/*      */       {
/*  188 */         localFile.mkdirs();
/*  189 */         localBoolean = Boolean.valueOf(true);
/*      */       } catch (Exception localException) {
/*      */       }
/*      */     }
/*  193 */     return localBoolean;
/*      */   }
/*      */ 
/*      */   public Boolean directoryExists(String paramString)
/*      */   {
/*  198 */     Boolean localBoolean = Boolean.valueOf(false);
/*  199 */     String str = paramString;
/*  200 */     File localFile = new File(str);
/*  201 */     if ((localFile.exists()) && (localFile.isDirectory())) localBoolean = Boolean.valueOf(true);
/*  202 */     return localBoolean;
/*      */   }
/*      */ 
/*      */   public Boolean directoryMove(String paramString1, String paramString2)
/*      */   {
/*  207 */     Boolean localBoolean = Boolean.valueOf(false);
/*  208 */     String str1 = paramString1;
/*  209 */     String str2 = paramString2;
/*  210 */     File localFile1 = new File(str1);
/*  211 */     File localFile2 = new File(str2);
/*  212 */     if ((localFile1.exists()) && (localFile1.isDirectory()) && (((!(localFile2.exists())) || (!(localFile2.isDirectory())))) && 
/*  214 */       (localFile1.renameTo(localFile2))) localBoolean = Boolean.valueOf(true);
/*      */ 
/*  216 */     return localBoolean;
/*      */   }
/*      */ 
/*      */   public Boolean fileCreate(String paramString, byte[] paramArrayOfByte)
/*      */   {
/*  221 */     Boolean localBoolean = Boolean.valueOf(true);
/*  222 */     String str = paramString;
/*  223 */     byte[] arrayOfByte = paramArrayOfByte;
/*      */     try
/*      */     {
/*  226 */       FileOutputStream localFileOutputStream = new FileOutputStream(str);
/*  227 */       localFileOutputStream.write(arrayOfByte);
/*  228 */       localFileOutputStream.close();
/*      */     } catch (Exception localException) {
/*  230 */       localBoolean = Boolean.valueOf(false); }
/*  231 */     return localBoolean;
/*      */   }
/*      */ 
/*      */   public Boolean fileExists(String paramString)
/*      */   {
/*  236 */     Boolean localBoolean = Boolean.valueOf(false);
/*  237 */     String str = paramString;
/*  238 */     File localFile = new File(str);
/*  239 */     if ((localFile.exists()) && (localFile.isFile())) localBoolean = Boolean.valueOf(true);
/*  240 */     return localBoolean;
/*      */   }
/*      */ 
/*      */   public Boolean fileDelete(String paramString)
/*      */   {
/*  245 */     Boolean localBoolean = Boolean.valueOf(true);
/*  246 */     String str = paramString;
/*  247 */     File localFile = new File(str);
/*  248 */     if ((localFile.exists()) && (localFile.isFile()) && 
/*  250 */       (!(localFile.delete()))) localBoolean = Boolean.valueOf(false);
/*      */ 
/*  252 */     return localBoolean;
/*      */   }
/*      */ 
/*      */   public String fileGetContents(String paramString)
/*      */   {
/*  257 */     String str1 = "";
/*  258 */     String str2 = paramString;
/*      */     try
/*      */     {
/*  261 */       FileInputStream localFileInputStream = new FileInputStream(str2);
/*  262 */       InputStreamReader localInputStreamReader = new InputStreamReader(localFileInputStream, this.conf.charset);
/*  263 */       BufferedReader localBufferedReader = new BufferedReader(localInputStreamReader);
/*      */       while (true)
/*      */       {
/*  266 */         String str3 = localBufferedReader.readLine();
/*  267 */         if (str3 == null) break;
/*  268 */         str1 = str1 + str3 + "\r\n";
/*      */       }
/*  270 */       if (!(cls.isEmpty(str1).booleanValue())) str1 = cls.getLRStr(str1, "\r\n", "leftr");
/*      */     } catch (Exception localException) {
/*  272 */       str1 = null; }
/*  273 */     return str1;
/*      */   }
/*      */ 
/*      */   public String fileGetHttpContents(String paramString1, String paramString2)
/*      */   {
/*  278 */     String str1 = "";
/*  279 */     String str2 = paramString1;
/*  280 */     String str3 = paramString2;
/*      */     try
/*      */     {
/*  283 */       URL localURL = new URL(str2);
/*  284 */       URLConnection localURLConnection = localURL.openConnection();
/*  285 */       InputStreamReader localInputStreamReader = new InputStreamReader(localURLConnection.getInputStream(), str3);
/*  286 */       BufferedReader localBufferedReader = new BufferedReader(localInputStreamReader);
/*      */       while (true)
/*      */       {
/*  289 */         String str4 = localBufferedReader.readLine();
/*  290 */         if (str4 == null) break;
/*  291 */         str1 = str1 + str4 + "\r\n";
/*      */       }
/*  293 */       if (!(cls.isEmpty(str1).booleanValue())) str1 = cls.getLRStr(str1, "\r\n", "leftr");
/*      */     } catch (Exception localException) {
/*  295 */       str1 = null; }
/*  296 */     return str1;
/*      */   }
/*      */ 
/*      */   public Boolean filePutContents(String paramString1, String paramString2)
/*      */   {
/*  301 */     Boolean localBoolean = Boolean.valueOf(true);
/*  302 */     String str1 = paramString1;
/*  303 */     String str2 = paramString2;
/*      */     try
/*      */     {
/*  306 */       FileOutputStream localFileOutputStream = new FileOutputStream(str1);
/*  307 */       localFileOutputStream.write(str2.getBytes(this.conf.charset));
/*  308 */       localFileOutputStream.close();
/*      */     } catch (Exception localException) {
/*  310 */       localBoolean = Boolean.valueOf(false); }
/*  311 */     return localBoolean;
/*      */   }
/*      */ 
/*      */   public String getAdminTheme()
/*      */   {
/*  316 */     String str = "";
/*  317 */     str = this.conf.getActualRoute(this.conf.adminFolder);
/*  318 */     str = str + "/" + this.conf.imagesRoute + "/theme/";
/*  319 */     str = str + this.conf.jt.itake(new StringBuilder().append("global.").append(this.conf.adminFolder).append(":config.theme").toString(), "cfg") + "/";
/*  320 */     return str;
/*      */   }
/*      */ 
/*      */   public String getCuteContent(String paramString1, String paramString2)
/*      */   {
/*      */     Object localObject;
/*  325 */     String str1 = "";
/*  326 */     String str2 = paramString1;
/*  327 */     Integer localInteger = cls.getNum(paramString2, Integer.valueOf(-1));
/*  328 */     if (localInteger.intValue() <= 0) localInteger = Integer.valueOf(1);
/*  329 */     String str3 = this.conf.jt.itake("global.tpl_common.ct-cutepage", "tpl");
/*  330 */     if (str2.indexOf(str3) != -1)
/*      */     {
/*  332 */       localObject = str2.split(Pattern.quote(str3));
/*  333 */       if (localObject != null)
/*      */       {
/*  335 */         if (localInteger.intValue() >  ((String[])localObject).length) localInteger = Integer.valueOf(((Object[])localObject).length);
/*  336 */         str1 = ((String[])localObject)[(localInteger.intValue() - 1)];
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  341 */       localObject = this.conf.jt.itake("global.tpl_common.ct-cutepage-b", "tpl");
/*  342 */       String[] arrayOfString = str2.split(Pattern.quote((String)localObject));
/*  343 */       if (arrayOfString != null)
/*      */       {
/*  345 */         if (localInteger.intValue() > arrayOfString.length) localInteger = Integer.valueOf(arrayOfString.length);
/*  346 */         str1 = arrayOfString[(localInteger.intValue() - 1)];
/*      */       }
/*      */     }
/*  349 */     return ((String)str1);
/*      */   }
/*      */ 
/*      */   public String getCuteContentCount(String paramString)
/*      */   {
/*      */     Object localObject;
/*  354 */     String str1 = "1";
/*  355 */     String str2 = paramString;
/*  356 */     String str3 = this.conf.jt.itake("global.tpl_common.ct-cutepage", "tpl");
/*  357 */     if (str2.indexOf(str3) != -1)
/*      */     {
/*  359 */       localObject = str2.split(Pattern.quote(str3));
/*  360 */       if (localObject != null) str1 = cls.toString(Integer.valueOf(((Object[])localObject).length));
/*      */     }
/*      */     else
/*      */     {
/*  364 */       localObject = this.conf.jt.itake("global.tpl_common.ct-cutepage-b", "tpl");
/*  365 */       String[] arrayOfString = str2.split(Pattern.quote((String)localObject));
/*  366 */       if (arrayOfString != null) str1 = cls.toString(Integer.valueOf(arrayOfString.length));
/*      */     }
/*  368 */     return ((String)str1);
/*      */   }
/*      */ 
/*      */   public String[][] getFileList(String paramString)
/*      */   {
/*  373 */     String str1 = paramString;
/*  374 */     String[][] arrayOfString1 = (String[][])null;
/*  375 */     File localFile = new File(this.conf.application.getRealPath(this.conf.getMapPath(str1)).toString());
/*  376 */     File[] arrayOfFile = localFile.listFiles();
/*  377 */     for (int i = 0; i < arrayOfFile.length; ++i)
/*      */     {
/*  379 */       if (!(arrayOfFile[i].isFile())) {
/*      */         continue;
/*      */       }
/*  382 */       String str2 = arrayOfFile[i].getName();
/*  383 */       String str3 = cls.toString(Long.valueOf(arrayOfFile[i].length()));
/*  384 */       Date localDate = new Date(arrayOfFile[i].lastModified());
/*  385 */       String str4 = cls.formatDate(localDate);
/*  386 */       String[][] arrayOfString2 = new String[1][3];
/*  387 */       arrayOfString2[0][0] = str2;
/*  388 */       arrayOfString2[0][1] = str3;
/*  389 */       arrayOfString2[0][2] = str4;
/*  390 */       arrayOfString1 = cls.mergeAry(arrayOfString1, arrayOfString2);
/*      */     }
/*      */ 
/*  393 */     return arrayOfString1;
/*      */   }
/*      */ 
/*      */   public Long getFolderSize(String paramString)
/*      */   {
/*  398 */     Long localLong = Long.valueOf(0L);
/*  399 */     String str = paramString;
/*  400 */     File localFile = new File(this.conf.application.getRealPath(this.conf.getMapPath(str)).toString());
/*  401 */     File[] arrayOfFile = localFile.listFiles();
/*  402 */     for (int i = 0; i < arrayOfFile.length; ++i)
/*      */     {
/*  404 */       if (arrayOfFile[i].isFile()) localLong = Long.valueOf(localLong.longValue() + arrayOfFile[i].length());
/*  405 */       if (!(arrayOfFile[i].isDirectory())) continue; localLong = Long.valueOf(localLong.longValue() + getFolderSize(str + arrayOfFile[i].getName() + "/").longValue());
/*      */     }
/*  407 */     return localLong;
/*      */   }
/*      */ 
/*      */   public String[][] getFolderList(String paramString)
/*      */   {
/*  412 */     String str = paramString;
/*  413 */     String[][] arrayOfString = (String[][])null;
/*  414 */     arrayOfString = getFolderList(str, Integer.valueOf(1));
/*  415 */     return arrayOfString;
/*      */   }
/*      */ 
/*      */   public String[][] getFolderList(String paramString, Integer paramInteger)
/*      */   {
/*  420 */     String str1 = paramString;
/*  421 */     Integer localInteger = paramInteger;
/*  422 */     String[][] arrayOfString1 = (String[][])null;
/*  423 */     File localFile = new File(this.conf.application.getRealPath(this.conf.getMapPath(str1)).toString());
/*  424 */     File[] arrayOfFile = localFile.listFiles();
/*  425 */     for (int i = 0; i < arrayOfFile.length; ++i)
/*      */     {
/*  427 */       if (!(arrayOfFile[i].isDirectory()))
/*      */         continue;
/*  429 */       String str2 = "-1";
/*  430 */       String str3 = arrayOfFile[i].getName();
/*  431 */       Date localDate = new Date(arrayOfFile[i].lastModified());
/*  432 */       String str4 = cls.formatDate(localDate);
/*  433 */       if (localInteger.intValue() == 1) str2 = cls.toString(getFolderSize(str1 + str3 + "/"));
/*  434 */       String[][] arrayOfString2 = new String[1][3];
/*  435 */       arrayOfString2[0][0] = str3;
/*  436 */       arrayOfString2[0][1] = str2;
/*  437 */       arrayOfString2[0][2] = str4;
/*  438 */       arrayOfString1 = cls.mergeAry(arrayOfString1, arrayOfString2);
/*      */     }
/*      */ 
/*  441 */     return arrayOfString1;
/*      */   }
/*      */ 
/*      */   public String[][] getJtbcElement(String paramString)
/*      */   {
/*  446 */     String str1 = paramString;
/*  447 */     String[][] arrayOfString1 = (String[][])null;
/*  448 */     String[][] arrayOfString2 = this.conf.njtbcelement;
/*  449 */     if (arrayOfString2 != null)
/*      */     {
/*  451 */       Integer localInteger1 = Integer.valueOf(arrayOfString2.length);
/*  452 */       Integer localInteger2 = Integer.valueOf(arrayOfString2[0].length);
/*  453 */       if ((localInteger1.intValue() >= 1) && (localInteger2.intValue() == 2))
/*      */       {
/*  455 */         for (int i = 0; i < localInteger1.intValue(); ++i)
/*      */         {
/*  457 */           String str2 = arrayOfString2[i][0];
/*  458 */           String str3 = arrayOfString2[i][1];
/*  459 */           if (!(cls.getParameter(str2, "id").equals(str1)))
/*      */             continue;
/*  461 */           arrayOfString1 = new String[1][2];
/*  462 */           arrayOfString1[0][0] = str2;
/*  463 */           arrayOfString1[0][1] = str3;
/*  464 */           break;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  469 */     return arrayOfString1;
/*      */   }
/*      */ 
/*      */   public String getRsValue(String paramString1, String paramString2)
/*      */   {
/*  474 */     String str1 = paramString1;
/*  475 */     String str2 = paramString2;
/*  476 */     String str3 = "";
/*  477 */     Object[][] arrayOfObject = (Object[][])null;
/*  478 */     if (str1.equals("rs")) arrayOfObject = this.conf.rsAry;
/*  479 */     else if (str1.equals("rsb")) arrayOfObject = this.conf.rsbAry;
/*  480 */     else if (str1.equals("rsc")) arrayOfObject = this.conf.rscAry;
/*  481 */     else if (str1.equals("rst")) arrayOfObject = this.conf.rstAry;
/*  482 */     if (arrayOfObject != null)
/*      */     {
/*  484 */       for (int i = 0; i < arrayOfObject.length; ++i)
/*      */       {
/*  486 */         if (!(cls.toString(arrayOfObject[i][0]).equals(str2)))
/*      */           continue;
/*  488 */         str3 = cls.toString(arrayOfObject[i][1]);
/*  489 */         break;
/*      */       }
/*      */     }
/*      */ 
/*  493 */     return str3;
/*      */   }
/*      */ 
/*      */   public String getSearchOptions(String paramString)
/*      */   {
/*  498 */     String str1 = paramString;
/*  499 */     String str2 = "";
/*  500 */     if (!(cls.isEmpty(str1).booleanValue()))
/*      */     {
/*  502 */       Object localObject = "";
/*  503 */       String[] arrayOfString = str1.split(Pattern.quote(","));
/*  504 */       String str3 = this.conf.jt.itake("global.tpl_config.option_unselect", "tpl");
/*  505 */       for (int i = 0; i < arrayOfString.length; ++i)
/*      */       {
/*  507 */         localObject = str3;
/*  508 */         localObject = ((String)localObject).replace("{$explain}", this.conf.jt.itake("global.sel_search." + encode.htmlencode(arrayOfString[i]), "sel"));
/*  509 */         localObject = ((String)localObject).replace("{$value}", encode.htmlencode(arrayOfString[i]));
/*  510 */         str2 = str2 + ((String)localObject);
/*      */       }
/*      */     }
/*  513 */     return ((String)str2);
/*      */   }
/*      */ 
/*      */   public String getSwitchOptions(String paramString)
/*      */   {
/*  518 */     String str1 = paramString;
/*  519 */     String str2 = "";
/*  520 */     if (!(cls.isEmpty(str1).booleanValue()))
/*      */     {
/*  522 */       Object localObject = "";
/*  523 */       String[] arrayOfString = str1.split(Pattern.quote(","));
/*  524 */       String str3 = this.conf.jt.itake("global.tpl_config.option_unselect", "tpl");
/*  525 */       for (int i = 0; i < arrayOfString.length; ++i)
/*      */       {
/*  527 */         localObject = str3;
/*  528 */         localObject = ((String)localObject).replace("{$explain}", this.conf.jt.itake("global.sel_switch." + encode.htmlencode(arrayOfString[i]), "sel"));
/*  529 */         localObject = ((String)localObject).replace("{$value}", encode.htmlencode(arrayOfString[i]));
/*  530 */         str2 = str2 + ((String)localObject);
/*      */       }
/*      */     }
/*  533 */     return ((String)str2);
/*      */   }
/*      */ 
/*      */   public Integer getTopID(String paramString1, String paramString2)
/*      */   {
/*  538 */     Integer localInteger = Integer.valueOf(-1);
/*  539 */     String str1 = paramString1;
/*  540 */     String str2 = paramString2;
/*  541 */     dbc localdbc = db.newInstance(this.conf);
/*  542 */     String str3 = "select max(" + str2 + ") from " + str1;
/*  543 */     Object[] arrayOfObject = localdbc.getDataAry(str3);
/*  544 */     if (arrayOfObject != null) localInteger = cls.getNum(cls.toString(localdbc.getValue((Object[][])(Object[][])arrayOfObject[0], 0)), Integer.valueOf(0));
/*  545 */     return localInteger;
/*      */   }
/*      */ 
/*      */   public String getLngID(String paramString)
/*      */   {
/*  550 */     String str1 = paramString;
/*  551 */     String str2 = "";
/*  552 */     String[][] arrayOfString = this.conf.jt.itakes("global.sel_lng.all", "sel");
/*  553 */     for (int i = 0; i < arrayOfString.length; ++i)
/*      */     {
/*  555 */       String str3 = arrayOfString[i][0];
/*  556 */       if (str3.indexOf(":") == -1)
/*      */         continue;
/*  558 */       String[] arrayOfString1 = str3.split(Pattern.quote(":"));
/*  559 */       if ((arrayOfString1.length != 2) || 
/*  561 */         (!(arrayOfString1[0].equals(str1))))
/*      */         continue;
/*  563 */       str2 = arrayOfString1[1];
/*  564 */       break;
/*      */     }
/*      */ 
/*  569 */     return str2;
/*      */   }
/*      */ 
/*      */   public String getLngText(String paramString)
/*      */   {
/*  574 */     String str1 = paramString;
/*  575 */     String str2 = "";
/*  576 */     String[][] arrayOfString = this.conf.jt.itakes("global.sel_lng.all", "sel");
/*  577 */     for (int i = 0; i < arrayOfString.length; ++i)
/*      */     {
/*  579 */       String str3 = arrayOfString[i][0];
/*  580 */       if (str3.indexOf(":") == -1)
/*      */         continue;
/*  582 */       String[] arrayOfString1 = str3.split(Pattern.quote(":"));
/*  583 */       if ((arrayOfString1.length != 2) || 
/*  585 */         (!(arrayOfString1[1].equals(str1))))
/*      */         continue;
/*  587 */       str2 = arrayOfString1[0];
/*  588 */       break;
/*      */     }
/*      */ 
/*  593 */     return str2;
/*      */   }
/*      */ 
/*      */   public String itransfer(String paramString)
/*      */   {
/*  598 */     String str1 = "";
/*  599 */     String str2 = paramString;
/*  600 */     String str3 = cls.getParameter(str2, "method");
/*  601 */     if (cls.isEmpty(str3).booleanValue()) str1 = itransferStandard(str2);
/*  604 */     else if (str3.equals("sql")) str1 = itransferSQL(str2);
/*  605 */     else if (str3.equals("itakes")) str1 = itransferITakes(str2);
/*  606 */     else if (str3.equals("multigenre")) str1 = itransferMultiGenre(str2);
/*      */ 
/*  608 */     return str1;
/*      */   }
/*      */ 
/*      */   public String itransferStandard(String paramString)
/*      */   {
/*  613 */     Object localObject1 = "";
/*      */ 
/*  615 */     String str4 = paramString;
/*  616 */     String str5 = cls.getParameter(str4, "tpl");
/*  617 */     String str6 = cls.getParameter(str4, "tplid");
/*  618 */     String str7 = cls.getParameter(str4, "tplstr");
/*  619 */     String str8 = cls.getParameter(str4, "type");
/*  620 */     Object localObject2 = cls.getParameter(str4, "genre");
/*  621 */     String str9 = cls.getParameter(str4, "ndatabase");
/*  622 */     String str10 = cls.getParameter(str4, "nfpre");
/*  623 */     String str11 = cls.getParameter(str4, "osql");
/*  624 */     String str12 = cls.getParameter(str4, "osqlorder");
/*  625 */     String str13 = cls.getParameter(str4, "ocname");
/*  626 */     String str14 = cls.getParameter(str4, "ocmode");
/*  627 */     String str15 = cls.getParameter(str4, "baseurl");
/*  628 */     String str16 = cls.getParameter(str4, "vars");
/*  629 */     Integer localInteger1 = cls.getNum(cls.getParameter(str4, "topx"), Integer.valueOf(-1));
/*  630 */     Integer localInteger2 = cls.getNum(cls.getParameter(str4, "cls"), Integer.valueOf(-1));
/*  631 */     Integer localInteger3 = cls.getNum(cls.getParameter(str4, "class"), Integer.valueOf(-1));
/*  632 */     Integer localInteger4 = cls.getNum(cls.getParameter(str4, "lng"), Integer.valueOf(-1));
/*  633 */     Integer localInteger5 = cls.getNum(cls.getParameter(str4, "bid"), Integer.valueOf(-1));
/*  634 */     Integer localInteger6 = cls.getNum(this.conf.dbtype, Integer.valueOf(0));
/*  635 */     if (localInteger4.intValue() == -1) localInteger4 = cls.getNum(this.conf.getNLng(), Integer.valueOf(-1));
/*  636 */     if (localInteger1.intValue() > 0)
/*      */     {
/*  638 */       String str17 = this.conf.getNGenre();
/*  639 */       if ((cls.isEmpty(str15).booleanValue()) && 
/*  641 */         (!(cls.isEmpty(localObject2).booleanValue())) && (!(((String)localObject2).equals(str17))))
/*      */       {
/*  643 */         str15 = this.conf.getActualRoute((String)localObject2);
/*  644 */         if (!(cls.getRight(str15, Integer.valueOf(1)).equals("/"))) str15 = str15 + "/";
/*      */       }
/*      */ 
/*  647 */       if (cls.isEmpty(localObject2).booleanValue()) localObject2 = str17;
/*  648 */       String str18 = "";
/*  649 */       String str19 = "";
/*  650 */       String str20 = "";
/*  651 */       if (!(cls.isEmpty(str9).booleanValue()))
/*      */       {
/*  653 */         str18 = cls.getString(str9);
/*  654 */         str19 = cls.getString(str10);
/*      */       }
/*  658 */       else if (cls.isEmpty(str13).booleanValue())
/*      */       {
/*  660 */         str18 = cls.getString(this.conf.jt.itake("global." + ((String)localObject2) + ":config.ndatabase", "cfg"));
/*  661 */         str19 = cls.getString(this.conf.jt.itake("global." + ((String)localObject2) + ":config.nfpre", "cfg"));
/*      */       }
/*      */       else
/*      */       {
/*  665 */         str18 = cls.getString(this.conf.jt.itake("global." + ((String)localObject2) + ":config.ndatabase-" + str13, "cfg"));
/*  666 */         str19 = cls.getString(this.conf.jt.itake("global." + ((String)localObject2) + ":config.nfpre-" + str13, "cfg"));
/*      */       }
/*      */ 
/*  669 */       str20 = cls.cfnames(str19, "id");
/*  670 */       if (!(cls.isEmpty(str18).booleanValue()))
/*      */       {
/*      */         Object localObject5;
/*  672 */         String str21 = "";
/*  673 */         String str22 = "";
/*  674 */         if (str8.equals("new"))
/*      */         {
/*  676 */           str21 = "select * from " + str18 + " where " + cls.cfnames(str19, "hidden") + "=0";
/*  677 */           str22 = " order by " + cls.cfnames(str19, "time") + " desc";
/*      */         }
/*  679 */         else if (str8.equals("-new"))
/*      */         {
/*  681 */           str21 = "select * from " + str18 + " where " + cls.cfnames(str19, "hidden") + "=0";
/*  682 */           str22 = " order by " + cls.cfnames(str19, "time") + " asc";
/*      */         }
/*  684 */         else if (str8.equals("@new"))
/*      */         {
/*  686 */           str21 = "select * from " + str18 + " where 1=1";
/*  687 */           str22 = " order by " + cls.cfnames(str19, "time") + " desc";
/*      */         }
/*  689 */         else if (str8.equals("top"))
/*      */         {
/*  691 */           str21 = "select * from " + str18 + " where " + cls.cfnames(str19, "hidden") + "=0";
/*  692 */           str22 = " order by " + str20 + " desc";
/*      */         }
/*  694 */         else if (str8.equals("-top"))
/*      */         {
/*  696 */           str21 = "select * from " + str18 + " where " + cls.cfnames(str19, "hidden") + "=0";
/*  697 */           str22 = " order by " + str20 + " asc";
/*      */         }
/*  699 */         else if (str8.equals("@top"))
/*      */         {
/*  701 */           str21 = "select * from " + str18 + " where 1=1";
/*  702 */           str22 = " order by " + str20 + " desc";
/*      */         }
/*  704 */         else if (str8.equals("commendatory"))
/*      */         {
/*  706 */           str21 = "select * from " + str18 + " where " + cls.cfnames(str19, "hidden") + "=0 and " + cls.cfnames(str19, "commendatory") + "=1";
/*  707 */           str22 = " order by " + cls.cfnames(str19, "time") + " desc";
/*      */         }
/*  709 */         else if (str8.equals("-commendatory"))
/*      */         {
/*  711 */           str21 = "select * from " + str18 + " where " + cls.cfnames(str19, "hidden") + "=0 and " + cls.cfnames(str19, "commendatory") + "=1";
/*  712 */           str22 = " order by " + cls.cfnames(str19, "time") + " asc";
/*      */         }
/*  714 */         else if (str8.equals("@commendatory"))
/*      */         {
/*  716 */           str21 = "select * from " + str18 + " where " + cls.cfnames(str19, "commendatory") + "=1";
/*  717 */           str22 = " order by " + cls.cfnames(str19, "time") + " desc";
/*      */         }
/*  719 */         else if (str8.equals("up"))
/*      */         {
/*  721 */           str21 = "select * from " + str18 + " where " + cls.cfnames(str19, "hidden") + "=0 and " + str20 + ">" + localInteger5;
/*  722 */           str22 = " order by " + str20 + " asc";
/*      */         }
/*  724 */         else if (str8.equals("down"))
/*      */         {
/*  726 */           str21 = "select * from " + str18 + " where " + cls.cfnames(str19, "hidden") + "=0 and " + str20 + "<" + localInteger5;
/*  727 */           str22 = " order by " + str20 + " desc";
/*      */         }
				   Object localObject3;
/*  729 */         if (localInteger2.intValue() != -1)
/*      */         {
/*  731 */           localObject3 = new category(this.conf);
/*  732 */           String str23 = ((category)localObject3).getClassChildIds((String)localObject2, localInteger4, cls.toString(localInteger2));
/*  733 */           if (!(cls.isEmpty(str23).booleanValue())) str21 = str21 + " and " + cls.cfnames(str19, "class") + " in (" + str23 + ")";
/*      */         }
/*  735 */         if (localInteger3.intValue() != -1) str21 = str21 + " and " + cls.cfnames(str19, "class") + "=" + localInteger3;
/*  736 */         if ((localInteger4.intValue() != -1) && (localInteger4.intValue() != -100)) str21 = str21 + " and " + cls.cfnames(str19, "lng") + "=" + localInteger4;
/*  737 */         if (!(cls.isEmpty(str11).booleanValue())) str21 = str21 + str11;
/*  738 */         if (!(cls.isEmpty(str12).booleanValue())) str22 = str12;
/*  739 */         str21 = str21 + str22;
/*  740 */         if ((localInteger6.intValue() >= 0) && (localInteger6.intValue() < 10)) str21 = str21 + " limit 0," + localInteger1;
/*  741 */         if ((localInteger6.intValue() >= 10) && (localInteger6.intValue() < 20)) str21 = "select top " + localInteger1 + " *" + cls.getLRStr(str21, "select *", "rightr");
/*  742 */         if ((localInteger6.intValue() >= 20) && (localInteger6.intValue() < 30)) str21 = str21 + " limit " + localInteger1;
/*  743 */         if (!(cls.isEmpty(str7).booleanValue())) { localObject1 = str7;
/*      */         }
/*  746 */         else if (!(cls.isEmpty(str6).booleanValue()))
/*      */         {
/*  748 */           localObject3 = getJtbcElement(str6);
/*  749 */           if ((localObject3 != null) && 
/*  751 */             (((Object[][])localObject3)[0].length == 2)) localObject1 = ((Object[][])localObject3)[0][1];
/*      */ 
/*      */         }
/*  756 */         else if (str5.indexOf(".") != -1) { localObject1 = this.conf.jt.itake(str5, "tpl"); } else {
/*  757 */           localObject1 = this.conf.jt.itake("global.tpl_transfer." + str5, "tpl");
/*      */         }
/*      */ 
/*  760 */         if (!(cls.isEmpty(str16).booleanValue()))
/*      */         {
/*  762 */           localObject3 = str16.split(Pattern.quote("|"));
/*  763 */           for (int i = 0; i < ((Object[])localObject3).length; ++i)
/*      */           {
/*  765 */             Object localObject4 = ((Object[])localObject3)[i];
/*  766 */             if (cls.isEmpty(localObject4).booleanValue())
/*      */               continue;
/*  768 */             localObject5 = ((String)localObject4).split(Pattern.quote("="));
/*  769 */             if (((Object[])localObject5).length != 2) continue; localObject1 = ((String)localObject1).replace("{$" + ((String[])localObject5)[0] + "}", ((String[])localObject5)[1]);
/*      */           }
/*      */         }
/*      */ 
/*  773 */         localObject3 = db.newInstance(this.conf);
/*  774 */         Object[] arrayOfObject = ((dbc)localObject3).getDataAry(str21);
/*  775 */         if (arrayOfObject != null)
/*      */         {
/*  777 */           String str2 = "";
/*  778 */           String str1 = cls.ctemplate((String)localObject1, "{@}");
/*  779 */           for (int j = 0; j < arrayOfObject.length; ++j)
/*      */           {
/*  781 */             String str3 = str1;
/*  782 */             localObject5 = (Object[][])(Object[][])arrayOfObject[j];
/*  783 */             for (int k = 0; k < ((Object[][])(Object[][])localObject5).length; ++k)
/*      */             {
/*  785 */               ((Object[][])(Object[][])localObject5)[k][0] = cls.getLRStr((String)((Object[][])(Object[][])localObject5)[k][0], str19, "rightr");
/*  786 */               str3 = str3.replace("{$" + cls.toString(((Object[][])(Object[][])localObject5)[k][0]) + "}", encode.htmlencode(cls.toString(((Object[][])(Object[][])localObject5)[k][1]), "1"));
/*      */             }
/*  788 */             this.conf.rstAry = ((Object[][])(Object[][])localObject5);
/*  789 */             str3 = str3.replace("{$-i}", cls.toString(Integer.valueOf(j)));
/*  790 */             str3 = str3.replace("{$-genre}", (CharSequence)localObject2);
/*  791 */             str3 = str3.replace("{$-baseurl}", str15);
/*      */ 
/*  793 */             for (int k = 2; k < 7; ++k)
/*      */             {
/*  795 */               int l = j % k + 1;
/*  796 */               str3 = str3.replace("{$-!mod" + k + "}", cls.toString(Integer.valueOf(l)));
/*  797 */               if (l != k) str3 = str3.replace("{$-!mod" + k + "-string}", "");
/*      */               else str3 = str3.replace("{$-!mod" + k + "-string}", cls.toString(str14));
/*      */             }
/*      */ 
/*  801 */             str3 = this.conf.jt.creplace(str3);
/*  802 */             str2 = str2 + str3;
/*      */           }
/*  804 */           localObject1 = cls.ctemplates((String)localObject1, "{@}", str2);
/*      */         } else {
/*  806 */           localObject1 = ""; }
/*  807 */         localObject1 = this.conf.jt.creplace((String)localObject1);
/*      */       }
/*      */     }
/*  810 */     return ((String)(String)(String)(String)localObject1);
/*      */   }
/*      */ 
/*      */   public String itransferSQL(String paramString)
/*      */   {
/*  815 */     Object localObject1 = "";
/*      */ 
/*  817 */     String str4 = paramString;
/*  818 */     String str5 = cls.getParameter(str4, "sql");
/*  819 */     String str6 = cls.getParameter(str4, "tpl");
/*  820 */     String str7 = cls.getParameter(str4, "tplid");
/*  821 */     String str8 = cls.getParameter(str4, "tplstr");
/*  822 */     Object localObject2 = cls.getParameter(str4, "genre");
/*  823 */     String str9 = cls.getParameter(str4, "ocmode");
/*  824 */     String str10 = cls.getParameter(str4, "baseurl");
/*  825 */     String str11 = cls.getParameter(str4, "vars");
/*  826 */     if (!(cls.isEmpty(str5).booleanValue()))
/*      */     {
/*  828 */       String str12 = cls.getLRStr(str5, " ", "left").toLowerCase();
/*  829 */       if (str12.equals("select"))
/*      */       {
/*      */         Object localObject5;
/*  831 */         String str13 = str5;
/*  832 */         String str14 = this.conf.getNGenre();
/*  833 */         if ((cls.isEmpty(str10).booleanValue()) && 
/*  835 */           (!(cls.isEmpty(localObject2).booleanValue())) && (!(((String)localObject2).equals(str14))))
/*      */         {
/*  837 */           str10 = this.conf.getActualRoute((String)localObject2);
/*  838 */           if (!(cls.getRight(str10, Integer.valueOf(1)).equals("/"))) str10 = str10 + "/";
/*      */         }
/*      */ 
/*  841 */         if (cls.isEmpty(localObject2).booleanValue()) localObject2 = str14;
/*      */ 
/*  843 */         if (!(cls.isEmpty(str8).booleanValue())) { localObject1 = str8;
/*      */         }
/*  846 */         else if (!(cls.isEmpty(str7).booleanValue()))
/*      */         {
/*  848 */           String[][]localObject3 = getJtbcElement(str7);
/*  849 */           if ((localObject3 != null) && 
/*  851 */             (localObject3[0].length == 2)) localObject1 = localObject3[0][1];
/*      */ 
/*      */         }
/*  856 */         else if (str6.indexOf(".") != -1) { localObject1 = this.conf.jt.itake(str6, "tpl"); } else {
/*  857 */           localObject1 = this.conf.jt.itake("global.tpl_transfer." + str6, "tpl");
/*      */         }
/*      */ 
/*  860 */         if (!(cls.isEmpty(str11).booleanValue()))
/*      */         {
/*  862 */           Object[] localObject3 = str11.split(Pattern.quote("|"));
/*  863 */           for (int i = 0; i < localObject3.length; ++i)
/*      */           {
/*  865 */             Object localObject4 = localObject3[i];
/*  866 */             if (cls.isEmpty(localObject4).booleanValue())
/*      */               continue;
/*  868 */            localObject5 = ((String)localObject4).split(Pattern.quote("="));
/*  869 */             if (((String[])localObject5).length != 2) continue; localObject1 = ((String)localObject1).replace("{$" + ((String[])localObject5)[0] + "}", ((String[])localObject5)[1]);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  874 */         Object localObject3 = db.newInstance(this.conf);
/*  875 */         Object[] arrayOfObject = ((dbc)localObject3).getDataAry(str13);
/*  876 */         if (arrayOfObject != null)
/*      */         {
/*  878 */           String str2 = "";
/*  879 */           String str1 = cls.ctemplate((String)localObject1, "{@}");
/*  880 */           for (int j = 0; j < arrayOfObject.length; ++j)
/*      */           {
/*  882 */             String str3 = str1;
/*  883 */             localObject5 = (Object[][])(Object[][])arrayOfObject[j];
/*  884 */             for (int k = 0; k < ((Object[][])(Object[][])localObject5).length; ++k)
/*      */             {
/*  886 */               str3 = str3.replace("{$" + cls.toString(((Object[][])(Object[][])localObject5)[k][0]) + "}", encode.htmlencode(cls.toString(((Object[][])(Object[][])localObject5)[k][1]), "1"));
/*      */             }
/*  888 */             this.conf.rstAry = ((Object[][])(Object[][])localObject5);
/*  889 */             str3 = str3.replace("{$-i}", cls.toString(Integer.valueOf(j)));
/*  890 */             str3 = str3.replace("{$-genre}", (CharSequence)localObject2);
/*  891 */             str3 = str3.replace("{$-baseurl}", str10);
/*      */ 
/*  893 */             for (int k = 2; k < 7; ++k)
/*      */             {
/*  895 */               int l = j % k + 1;
/*  896 */               str3 = str3.replace("{$-!mod" + k + "}", cls.toString(Integer.valueOf(l)));
/*  897 */               if (l != k) str3 = str3.replace("{$-!mod" + k + "-string}", "");
/*      */               else str3 = str3.replace("{$-!mod" + k + "-string}", cls.toString(str9));
/*      */             }
/*      */ 
/*  901 */             str3 = this.conf.jt.creplace(str3);
/*  902 */             str2 = str2 + str3;
/*      */           }
/*  904 */           localObject1 = cls.ctemplates((String)localObject1, "{@}", str2);
/*      */         } else {
/*  906 */           localObject1 = ""; }
/*  907 */         localObject1 = this.conf.jt.creplace((String)localObject1);
/*      */       }
/*      */     }
/*  910 */     return ((String)(String)(String)(String)localObject1);
/*      */   }
/*      */ 
/*      */   public String itransferITakes(String paramString)
/*      */   {
/*  915 */     Object localObject1 = "";
/*      */ 
/*  917 */     String str4 = paramString;
/*  918 */     String str5 = cls.getParameter(str4, "xinfostr");
/*  919 */     String str6 = cls.getParameter(str4, "xinfotype");
/*  920 */     String str7 = cls.getParameter(str4, "xinfolimit");
/*  921 */     String str8 = cls.getParameter(str4, "tpl");
/*  922 */     String str9 = cls.getParameter(str4, "tplid");
/*  923 */     String str10 = cls.getParameter(str4, "tplstr");
/*  924 */     Object localObject2 = cls.getParameter(str4, "genre");
/*  925 */     String str11 = cls.getParameter(str4, "ocmode");
/*  926 */     String str12 = cls.getParameter(str4, "baseurl");
/*  927 */     String str13 = cls.getParameter(str4, "vars");
/*  928 */     if (!(cls.isEmpty(str5).booleanValue()))
/*      */     {
/*      */       Object localObject3;
/*      */       Object localObject5;
/*  930 */       String str14 = this.conf.getNGenre();
/*  931 */       if ((cls.isEmpty(str12).booleanValue()) && 
/*  933 */         (!(cls.isEmpty(localObject2).booleanValue())) && (!(((String)localObject2).equals(str14))))
/*      */       {
/*  935 */         str12 = this.conf.getActualRoute((String)localObject2);
/*  936 */         if (!(cls.getRight(str12, Integer.valueOf(1)).equals("/"))) str12 = str12 + "/";
/*      */       }
/*      */ 
/*  939 */       if (cls.isEmpty(localObject2).booleanValue()) localObject2 = str14;
/*      */ 
/*  941 */       if (!(cls.isEmpty(str10).booleanValue())) { localObject1 = str10;
/*      */       }
/*  944 */       else if (!(cls.isEmpty(str9).booleanValue()))
/*      */       {
/*  946 */         localObject3 = getJtbcElement(str9);
/*  947 */         if ((localObject3 != null) && 
/*  949 */           (((String[][])localObject3)[0].length == 2)) localObject1 = ((String[][])localObject3)[0][1];
/*      */ 
/*      */       }
/*  954 */       else if (str8.indexOf(".") != -1) { localObject1 = this.conf.jt.itake(str8, "tpl"); } else {
/*  955 */         localObject1 = this.conf.jt.itake("global.tpl_transfer." + str8, "tpl");
/*      */       }
/*      */ 
/*  958 */       if (!(cls.isEmpty(str13).booleanValue()))
/*      */       {
/*  960 */         localObject3 = str13.split(Pattern.quote("|"));
/*  961 */         for (int j = 0; j < ((Object[])localObject3).length; ++j)
/*      */         {
/*  963 */           Object localObject4 = ((Object[])localObject3)[j];
/*  964 */           if (cls.isEmpty(localObject4).booleanValue())
/*      */             continue;
/*  966 */           localObject5 = ((String)localObject4).split(Pattern.quote("="));
/*  967 */           if (((String[])localObject5).length != 2) continue; localObject1 = ((String)localObject1).replace("{$" + ((String[])localObject5)[0] + "}", ((String[])localObject5)[1]);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  972 */       int i = 0;
/*  973 */       if (cls.isEmpty(str6).booleanValue()) str6 = "lng";
/*  974 */       String[][] arrayOfString = this.conf.jt.itakes(str5, str6);
/*  975 */       if (arrayOfString != null)
/*      */       {
/*  977 */         String str2 = "";
/*  978 */         String str1 = cls.ctemplate((String)localObject1, "{@}");
/*  979 */         for (int k = 0; k < arrayOfString.length; ++k)
/*      */         {
/*  981 */           localObject5 = arrayOfString[k][0];
/*  982 */           String str15 = arrayOfString[k][1];
/*  983 */           if ((!(cls.isEmpty(str7).booleanValue())) && (!(cls.cinstr(str7, (String)localObject5, ",").booleanValue())))
/*      */             continue;
/*  985 */           String str3 = str1;
/*  986 */           str3 = str3.replace("{$namestring}", (CharSequence)localObject5);
/*  987 */           str3 = str3.replace("{$valuestring}", str15);
/*  988 */           str3 = str3.replace("{$name}", encode.htmlencode((String)localObject5));
/*  989 */           str3 = str3.replace("{$value}", encode.htmlencode(str15));
/*  990 */           str3 = str3.replace("{$-i}", cls.toString(Integer.valueOf(i)));
/*  991 */           str3 = str3.replace("{$-genre}", (CharSequence)localObject2);
/*  992 */           str3 = str3.replace("{$-baseurl}", str12);
/*      */ 
/*  994 */           for (int l = 2; l < 7; ++l)
/*      */           {
/*  996 */             int i1 = i % l + 1;
/*  997 */             str3 = str3.replace("{$-!mod" + l + "}", cls.toString(Integer.valueOf(i1)));
/*  998 */             if (i1 != l) str3 = str3.replace("{$-!mod" + l + "-string}", "");
/*      */             else str3 = str3.replace("{$-!mod" + l + "-string}", cls.toString(str11));
/*      */           }
/*      */ 
/* 1002 */           str2 = str2 + str3;
/* 1003 */           ++i;
/*      */         }
/*      */ 
/* 1006 */         localObject1 = cls.ctemplates((String)localObject1, "{@}", str2);
/*      */       } else {
/* 1008 */         localObject1 = ""; }
/* 1009 */       localObject1 = this.conf.jt.creplace((String)localObject1);
/*      */     }
/* 1011 */     return ((String)(String)(String)(String)localObject1);
/*      */   }
/*      */ 
/*      */   public String itransferMultiGenre(String paramString)
/*      */   {
/* 1016 */     Object localObject1 = "";
/*      */ 
/* 1018 */     String str4 = paramString;
/* 1019 */     String str5 = cls.getParameter(str4, "tpl");
/* 1020 */     String str6 = cls.getParameter(str4, "tplid");
/* 1021 */     String str7 = cls.getParameter(str4, "tplstr");
/* 1022 */     String str8 = cls.getParameter(str4, "type");
/* 1023 */     String str9 = cls.getParameter(str4, "genre");
/* 1024 */     String str10 = cls.getParameter(str4, "field");
/* 1025 */     String str11 = cls.getParameter(str4, "osql");
/* 1026 */     String str12 = cls.getParameter(str4, "osqlorder");
/* 1027 */     String str13 = cls.getParameter(str4, "ocmode");
/* 1028 */     String str14 = cls.getParameter(str4, "baseurl");
/* 1029 */     String str15 = cls.getParameter(str4, "vars");
/* 1030 */     Integer localInteger1 = cls.getNum(cls.getParameter(str4, "topx"), Integer.valueOf(-1));
/* 1031 */     Integer localInteger2 = cls.getNum(cls.getParameter(str4, "lng"), Integer.valueOf(-1));
/* 1032 */     Integer localInteger3 = cls.getNum(this.conf.dbtype, Integer.valueOf(0));
/* 1033 */     if (localInteger2.intValue() == -1) localInteger2 = cls.getNum(this.conf.getNLng(), Integer.valueOf(-1));
/* 1034 */     if (localInteger1.intValue() > 0)
/*      */     {
/* 1036 */       String str16 = this.conf.getNGenre();
/* 1037 */       if (!(cls.isEmpty(str9).booleanValue()))
/*      */       {
/*      */         String str20;
/*      */         Object localObject3;
/* 1039 */         String str17 = "";
/* 1040 */         String str18 = "";
/* 1041 */         str17 = str17 + "select * from (";
/* 1042 */         String[] arrayOfString1 = str9.split(Pattern.quote("&"));
/* 1043 */         for (int i = 0; i < arrayOfString1.length; ++i)
/*      */         {
/* 1045 */           String str19 = arrayOfString1[i];
/* 1046 */           str20 = cls.getString(this.conf.jt.itake("global." + str19 + ":config.ndatabase", "cfg"));
/* 1047 */           localObject3 = cls.getString(this.conf.jt.itake("global." + str19 + ":config.nfpre", "cfg"));
/* 1048 */           if (cls.isEmpty(str20).booleanValue())
/*      */             continue;
/* 1050 */           str17 = str17 + "select " + cls.cfnames((String)localObject3, "id") + " as un_id,";
/* 1051 */           String[] arrayOfString2 = str10.split(Pattern.quote("&"));
/* 1052 */           for (int i1 = 0; i1 < arrayOfString2.length; ++i1)
/*      */           {
/* 1054 */             String str23 = arrayOfString2[i1];
/* 1055 */             str17 = str17 + cls.cfnames((String)localObject3, str23) + " as un_" + str23 + ",";
/*      */           }
/* 1057 */           str17 = str17 + cls.cfnames((String)localObject3, "time") + " as un_time, '" + str19 + "' as un_genre from " + str20;
/* 1058 */           if (str8.equals("new"))
/*      */           {
/* 1060 */             str17 = str17 + " where " + cls.cfnames((String)localObject3, "hidden") + "=0";
/* 1061 */             str18 = " order by un_time desc";
/*      */           }
/* 1063 */           else if (str8.equals("-new"))
/*      */           {
/* 1065 */             str17 = str17 + " where " + cls.cfnames((String)localObject3, "hidden") + "=0";
/* 1066 */             str18 = " order by un_time desc";
/*      */           }
/* 1068 */           else if (str8.equals("@new"))
/*      */           {
/* 1070 */             str17 = str17 + " where 1=1";
/* 1071 */             str18 = " order by un_time desc";
/*      */           }
/* 1073 */           else if (str8.equals("commendatory"))
/*      */           {
/* 1075 */             str17 = str17 + " where " + cls.cfnames((String)localObject3, "hidden") + "=0 and " + cls.cfnames((String)localObject3, "commendatory") + "=1";
/* 1076 */             str18 = " order by un_time desc";
/*      */           }
/* 1078 */           else if (str8.equals("-commendatory"))
/*      */           {
/* 1080 */             str17 = str17 + " where " + cls.cfnames((String)localObject3, "hidden") + "=0 and " + cls.cfnames((String)localObject3, "commendatory") + "=1";
/* 1081 */             str18 = " order by un_time desc";
/*      */           }
/* 1083 */           else if (str8.equals("@commendatory"))
/*      */           {
/* 1085 */             str17 = str17 + " where " + cls.cfnames((String)localObject3, "commendatory") + "=1";
/* 1086 */             str18 = " order by un_time desc";
/*      */           }
/*      */           else
/*      */           {
/* 1090 */             str17 = str17 + " where " + cls.cfnames((String)localObject3, "hidden") + "=0";
/* 1091 */             str18 = " order by un_time desc";
/*      */           }
/* 1093 */           if ((localInteger2.intValue() != -1) && (localInteger2.intValue() != -100)) str17 = str17 + " and " + cls.cfnames((String)localObject3, "lng") + "=" + localInteger2;
/* 1094 */           str17 = str17 + " union all ";
/*      */         }
/*      */ 
/* 1097 */         if (str17.indexOf(" union all ") != -1) str17 = cls.getLRStr(str17, " union all ", "leftr");
/* 1098 */         str17 = str17 + ") t1 where 1=1";
/* 1099 */         if (!(cls.isEmpty(str11).booleanValue())) str17 = str17 + str11;
/* 1100 */         if (!(cls.isEmpty(str12).booleanValue())) str18 = str12;
/* 1101 */         str17 = str17 + str18;
/* 1102 */         if ((localInteger3.intValue() >= 0) && (localInteger3.intValue() < 10)) str17 = str17 + " limit 0," + localInteger1;
/* 1103 */         if ((localInteger3.intValue() >= 10) && (localInteger3.intValue() < 20)) str17 = "select top " + localInteger1 + " *" + cls.getLRStr(str17, "select *", "rightr");
/* 1104 */         if ((localInteger3.intValue() >= 20) && (localInteger3.intValue() < 30)) str17 = str17 + " limit " + localInteger1;
/*      */ 
/* 1106 */         if (!(cls.isEmpty(str7).booleanValue())) { localObject1 = str7;
/*      */         }
/* 1109 */         else if (!(cls.isEmpty(str6).booleanValue()))
/*      */         {
/* 1111 */          String[][] localObject2 = getJtbcElement(str6);
/* 1112 */           if ((localObject2 != null) && 
/* 1114 */             (localObject2[0].length == 2)) localObject1 = localObject2[0][1];
/*      */ 
/*      */         }
/* 1119 */         else if (str5.indexOf(".") != -1) { localObject1 = this.conf.jt.itake(str5, "tpl"); } else {
/* 1120 */           localObject1 = this.conf.jt.itake("global.tpl_transfer." + str5, "tpl");
/*      */         }
/*      */ 
/* 1123 */         if (!(cls.isEmpty(str15).booleanValue()))
/*      */         {
/* 1125 */          String[] localObject2 = str15.split(Pattern.quote("|"));
/* 1126 */           for (int j = 0; j < localObject2.length; ++j)
/*      */           {
/* 1128 */             str20 = localObject2[j];
/* 1129 */             if (cls.isEmpty(str20).booleanValue())
/*      */               continue;
/* 1131 */             localObject3 = str20.split(Pattern.quote("="));
/* 1132 */             if (((String[])localObject3).length != 2) continue; localObject1 = ((String)localObject1).replace("{$" + ((String[])localObject3)[0] + "}", ((String[])localObject3)[1]);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/* 1137 */         Object localObject2 = db.newInstance(this.conf);
/* 1138 */         Object[] arrayOfObject = ((dbc)localObject2).getDataAry(str17);
/* 1139 */         if (arrayOfObject != null)
/*      */         {
/* 1141 */           String str2 = "";
/* 1142 */           String str1 = cls.ctemplate((String)localObject1, "{@}");
/* 1143 */           for (int k = 0; k < arrayOfObject.length; ++k)
/*      */           {
/* 1145 */             String str3 = str1;
/* 1146 */             localObject3 = (Object[][])(Object[][])arrayOfObject[k];
/* 1147 */             for (int l = 0; l < ((Object[][])(Object[][])localObject3).length; ++l)
/*      */             {
/* 1149 */               ((Object[][])(Object[][])localObject3)[l][0] = cls.getLRStr((String)((Object[][])(Object[][])localObject3)[l][0], "un_", "rightr");
/* 1150 */               str3 = str3.replace("{$" + cls.toString(((Object[][])(Object[][])localObject3)[l][0]) + "}", encode.htmlencode(cls.toString(((Object[][])(Object[][])localObject3)[l][1]), "1"));
/*      */             }
/* 1152 */             this.conf.rstAry = ((Object[][])(Object[][])localObject3);
/* 1153 */             String str21 = cls.toString(((dbc)localObject2).getValue(((Object[][])(Object[][])localObject3), "genre"));
/* 1154 */             String str22 = "";
/* 1155 */             if (!(cls.isEmpty(str14).booleanValue())) { str22 = str14;
/*      */             }
/* 1158 */             else if (!(str21.equals(str16)))
/*      */             {
/* 1160 */               str22 = this.conf.getActualRoute(str21);
/* 1161 */               if (!(cls.getRight(str22, Integer.valueOf(1)).equals("/"))) str22 = str22 + "/";
/*      */             }
/*      */ 
/* 1164 */             str3 = str3.replace("{$-i}", cls.toString(Integer.valueOf(k)));
/* 1165 */             str3 = str3.replace("{$-genre}", encode.htmlencode(str21));
/* 1166 */             str3 = str3.replace("{$-baseurl}", str22);
/*      */ 
/* 1168 */             for (int i2 = 2; i2 < 7; ++i2)
/*      */             {
/* 1170 */               int i3 = k % i2 + 1;
/* 1171 */               str3 = str3.replace("{$-!mod" + i2 + "}", cls.toString(Integer.valueOf(i3)));
/* 1172 */               if (i3 != i2) str3 = str3.replace("{$-!mod" + i2 + "-string}", "");
/*      */               else str3 = str3.replace("{$-!mod" + i2 + "-string}", cls.toString(str13));
/*      */             }
/*      */ 
/* 1176 */             str3 = this.conf.jt.creplace(str3);
/* 1177 */             str2 = str2 + str3;
/*      */           }
/* 1179 */           localObject1 = cls.ctemplates((String)localObject1, "{@}", str2);
/*      */         } else {
/* 1181 */           localObject1 = ""; }
/* 1182 */         localObject1 = this.conf.jt.creplace((String)localObject1);
/*      */       }
/*      */     }
/* 1185 */     return ((String)(String)(String)localObject1);
/*      */   }
/*      */ 
/*      */   public String isort(String paramString)
/*      */   {
/* 1190 */     String str1 = "";
/*      */ 
/* 1192 */     String str5 = paramString;
/* 1193 */     String str6 = cls.getParameter(str5, "tpl");
/* 1194 */     Object localObject1 = cls.getParameter(str5, "genre");
/* 1195 */     String str7 = cls.getParameter(str5, "lng");
/* 1196 */     String str8 = cls.getParameter(str5, "fid");
/* 1197 */     String str9 = cls.getParameter(str5, "vars");
/* 1198 */     String str10 = cls.getParameter(str5, "valids");
/* 1199 */     String str11 = "";
/* 1200 */     String str12 = this.conf.getNGenre();
/* 1201 */     if (cls.isEmpty(localObject1).booleanValue()) localObject1 = str12;
/* 1202 */     if (cls.isEmpty(str7).booleanValue()) str7 = this.conf.getNLng();
/* 1203 */     if (str10.equals("-1")) str10 = "";
/* 1204 */     if (!(((String)localObject1).equals(str12)))
/*      */     {
/* 1206 */       str11 = this.conf.getActualRoute((String)localObject1);
/* 1207 */       if (cls.getRight(str11, Integer.valueOf(1)) != "/") str11 = str11 + "/";
/*      */     }
/* 1209 */     if (str6.indexOf(".") != -1) str1 = this.conf.jt.itake(str6, "tpl");
/*      */     else str1 = this.conf.jt.itake("global.tpl_transfer." + str6, "tpl");
/* 1211 */     if (!(cls.isEmpty(str9).booleanValue()))
/*      */     {
/* 1213 */      Object[] localObject2 = str9.split(Pattern.quote("|"));
/* 1214 */       for (int i = 0; i < localObject2.length; ++i)
/*      */       {
/* 1216 */         Object localObject3 = localObject2[i];
/* 1217 */         if (cls.isEmpty(localObject3).booleanValue())
/*      */           continue;
/* 1219 */         String[] arrayOfString1 = ((String)localObject3).split(Pattern.quote("="));
/* 1220 */         if (arrayOfString1.length != 2) continue; str1 = str1.replace("{$" + arrayOfString1[0] + "}", arrayOfString1[1]);
/*      */       }
/*      */     }
/*      */ 
/* 1224 */     String str3 = "";
/* 1225 */     String str2 = cls.ctemplate(str1, "{@}");
/* 1226 */     Object localObject2 = new category(this.conf);
/* 1227 */     String[][] arrayOfString = ((category)localObject2).getCatAry((String)localObject1, cls.getNum(str7, Integer.valueOf(0)));
/* 1228 */     if (arrayOfString != null)
/*      */     {
/* 1230 */       for (int j = 0; j < arrayOfString.length; ++j)
/*      */       {
/* 1232 */         if ((!(cls.getNum(arrayOfString[j][2], Integer.valueOf(0)).equals(cls.getNum(str8, Integer.valueOf(0))))) || (
/* 1234 */           (!(cls.isEmpty(str10).booleanValue())) && (!(cls.cinstr(str10, cls.toString(cls.getNum(arrayOfString[j][0], Integer.valueOf(0))), ",").booleanValue()))))
/*      */           continue;
/* 1236 */         String str4 = str2;
/* 1237 */         str4 = str4.replace("{$topic}", encode.htmlencode(arrayOfString[j][1]));
/* 1238 */         str4 = str4.replace("{$id}", encode.htmlencode(arrayOfString[j][0]));
/* 1239 */         str4 = str4.replace("{$-genre}", (CharSequence)localObject1);
/* 1240 */         str4 = str4.replace("{$-baseurl}", str11);
/* 1241 */         str3 = str3 + str4;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1246 */     str1 = cls.ctemplates(str1, "{@}", str3);
/* 1247 */     str1 = this.conf.jt.creplace(str1);
/* 1248 */     return ((String)(String)str1);
/*      */   }
/*      */ 
/*      */   public String inavigation(String paramString)
/*      */   {
/* 1253 */     Object localObject1 = "";
/* 1254 */     String str1 = paramString;
/* 1255 */     Object localObject2 = cls.getParameter(str1, "genre");
/* 1256 */     String str2 = cls.getParameter(str1, "lng");
/* 1257 */     String str3 = cls.getParameter(str1, "class");
/* 1258 */     String str4 = cls.getParameter(str1, "genrelink");
/* 1259 */     String str5 = "";
/* 1260 */     String str6 = this.conf.getNGenre();
/* 1261 */     if (cls.isEmpty(localObject2).booleanValue()) localObject2 = str6;
/* 1262 */     if (cls.isEmpty(str2).booleanValue()) str2 = this.conf.getNLng();
/* 1263 */     if (!(((String)localObject2).equals(str6)))
/*      */     {
/* 1265 */       str5 = this.conf.getActualRoute((String)localObject2);
/* 1266 */       if (!(cls.getRight(str5, Integer.valueOf(1)).equals("/"))) str5 = str5 + "/";
/*      */     }
/* 1268 */     String str7 = this.conf.jt.itake("global.tpl_config.a_href_self", "tpl");
/* 1269 */     String str8 = this.conf.jt.itake("global.default.channel_title", "lng");
/* 1270 */     localObject1 = str7;
/* 1271 */     localObject1 = ((String)localObject1).replace("{$explain}", str8);
/* 1272 */     localObject1 = ((String)localObject1).replace("{$value}", this.conf.getActualRoute("./"));
/* 1273 */     if (localObject2 != "&hidden")
/*      */     {
/* 1275 */      String str9 = this.conf.jt.itake("global." + ((String)localObject2) + ":default.channel_title", "lng");
/* 1276 */       if (!(str4.equals("&hidden")))
/*      */       {
/* 1278 */         localObject1 = ((String)localObject1) + this.conf.navSpStr + str7;
/* 1279 */         if (cls.isEmpty(str4).booleanValue()) str4 = this.conf.getActualRoute((String)localObject2);
/* 1280 */         localObject1 = ((String)localObject1).replace("{$explain}", str9);
/* 1281 */         localObject1 = ((String)localObject1).replace("{$value}", str4);
/*      */       } else {
/* 1283 */         localObject1 = ((String)localObject1) + this.conf.navSpStr + str9; }
/*      */     }
/* 1285 */     String str9 = "<!--fixed-->{@}" + this.conf.navSpStr + str7 + "{@}<!--fixed-->";
/* 1286 */     str9 = str9.replace("{$explain}", "{$topic}");
/* 1287 */     str9 = str9.replace("{$value}", curl(str5, iurl("genre=" + ((String)localObject2) + ";type=list;key={$id}")));
/* 1288 */     if (!(cls.isEmpty(str3).booleanValue()))
/*      */     {
/* 1290 */       category localcategory = new category(this.conf);
/* 1291 */       localObject1 = ((String)localObject1) + localcategory.getFaCatHtml(str9, (String)localObject2, cls.getNum(str2, Integer.valueOf(0)), cls.getNum(str3, Integer.valueOf(0)));
/*      */     }
/* 1293 */     return ((String)(String)localObject1);
/*      */   }
/*      */ 
/*      */   public String iurl(String paramString)
/*      */   {
/* 1298 */     String str1 = "";
/* 1299 */     String str2 = "";
/* 1300 */     String str3 = paramString;
/* 1301 */     String str4 = cls.getParameter(str3, "type");
/* 1302 */     String str5 = cls.getParameter(str3, "genre");
/* 1303 */     String str6 = cls.getParameter(str3, "key");
/* 1304 */     String str7 = cls.getParameter(str3, "time");
/* 1305 */     String str8 = cls.getParameter(str3, "page");
/* 1306 */     String str9 = cls.getParameter(str3, "ctpage");
/* 1307 */     str2 = cls.getSafeString(str6);
/* 1308 */     if (cls.isEmpty(str2).booleanValue()) str2 = "0";
/* 1309 */     str8 = cls.getSafeString(str8);
/* 1310 */     if (cls.isEmpty(str8).booleanValue()) str8 = "0";
/* 1311 */     str9 = cls.getSafeString(str9);
/* 1312 */     if (cls.isEmpty(str9).booleanValue()) str9 = "0";
/* 1313 */     if (cls.isEmpty(str5).booleanValue()) str5 = this.conf.getNGenre();
/* 1314 */     Integer localInteger = cls.getNum(this.conf.jt.itake("global." + str5 + ":config.nurltype", "cfg"), Integer.valueOf(0));
/* 1315 */     String str10 = this.conf.jt.itake("global." + str5 + ":config.ncreatefolder", "cfg");
/* 1316 */     String str11 = this.conf.jt.itake("global." + str5 + ":config.ncreatefiletype", "cfg");
/* 1317 */    label1480: switch (localInteger.intValue())
/*      */     {
/*      */     case 0:
/* 1320 */       if (str4.equals("list")) { str1 = "?type=list&amp;class=" + str2; break label1480; }
/* 1321 */       if (str4.equals("detail")) { str1 = "?type=detail&amp;id=" + str2; break label1480; }
/* 1322 */       if (str4.equals("page")) { str1 = "?" + encode.htmlencode(cls.reparameter(this.conf.getNURS(), "page", str8)); break label1480; }
/* 1323 */       if (!(str4.equals("ctpage"))) break label1480; str1 = "?" + encode.htmlencode(cls.reparameter(this.conf.getNURS(), "ctpage", str9)); break;
/*      */     case 1:
/* 1326 */       if (str4.equals("list")) { str1 = str10 + "/list/" + str2 + "/1" + str11; break label1480; }
/* 1327 */       if (str4.equals("detail")) { str1 = str10 + "/detail/" + cls.formatDate(str7, Integer.valueOf(5)) + "/" + str2 + str11; break label1480; }
/* 1328 */       if (str4.equals("page")) { str1 = str10 + "/list/" + str2 + "/" + str8 + str11; break label1480; }
/* 1329 */       if (!(str4.equals("ctpage"))) break label1480; str1 = str10 + "/detail/" + cls.formatDate(str7, Integer.valueOf(5)) + "/" + str2 + "_" + str9 + str11; break;
/*      */     case 2:
/* 1332 */       if (str4.equals("list")) { str1 = "list-" + str2 + "-1.jsp"; break label1480; }
/* 1333 */       if (str4.equals("detail")) { str1 = "detail-" + str2 + ".jsp"; break label1480; }
/* 1334 */       if (str4.equals("page")) { str1 = "list-" + str2 + "-" + str8 + ".jsp"; break label1480; }
/* 1335 */       if (!(str4.equals("ctpage"))) break label1480; str1 = "detail-" + str2 + "-" + str9 + ".jsp"; break;
/*      */     case 3:
/* 1338 */       if (str4.equals("list")) { str1 = "list-" + str2 + "-1.htm"; break label1480;}
/* 1339 */       if (str4.equals("detail")) { str1 = "detail-" + str2 + ".htm"; break label1480;}
/* 1340 */       if (str4.equals("page")) { str1 = "list-" + str2 + "-" + str8 + ".htm"; break label1480; }
/* 1341 */       if (!(str4.equals("ctpage"))) break label1480; str1 = "detail-" + str2 + "-" + str9 + ".htm"; break;
/*      */     case 4:
/* 1344 */       if (str4.equals("list")) { str1 = "list-" + str2 + "-1.xhtml"; break label1480; }
/* 1345 */       if (str4.equals("detail")) { str1 = "detail-" + str2 + ".xhtml"; break label1480; }
/* 1346 */       if (str4.equals("page")) { str1 = "list-" + str2 + "-" + str8 + ".xhtml"; break label1480; }
/* 1347 */       if (!(str4.equals("ctpage"))) break label1480; str1 = "detail-" + str2 + "-" + str9 + ".xhtml"; break;
/*      */     case 5:
/* 1350 */       if (str4.equals("list")) { str1 = "list-" + str2 + "-1.html"; break label1480; }
/* 1351 */       if (str4.equals("detail")) { str1 = "detail-" + str2 + ".html"; break label1480; }
/* 1352 */       if (str4.equals("page")) { str1 = "list-" + str2 + "-" + str8 + ".html"; break label1480; }
/* 1353 */       if (!(str4.equals("ctpage"))) break label1480; str1 = "detail-" + str2 + "-" + str9 + ".html";
/*      */     }
/*      */ 
/* 1356 */     label1480: return str1;
/*      */   }
/*      */ 
/*      */   public String loadEditor(String paramString1, String paramString2)
/*      */   {
/* 1361 */     String str1 = "";
/* 1362 */     String str2 = paramString1;
/* 1363 */     String str3 = paramString2;
/* 1364 */     str1 = loadEditor(str2, str3, "1");
/* 1365 */     return str1;
/*      */   }
/*      */ 
/*      */   public String loadEditor(String paramString1, String paramString2, String paramString3)
/*      */   {
/* 1370 */     String str1 = "";
/* 1371 */     String str2 = paramString1;
/* 1372 */     String str3 = paramString2;
/* 1373 */     String str4 = paramString3;
/* 1374 */     str1 = loadEditor(str2, str3, str4, "300");
/* 1375 */     return str1;
/*      */   }
/*      */ 
/*      */   public String loadEditor(String paramString1, String paramString2, String paramString3, String paramString4)
/*      */   {
/* 1380 */     String str1 = "";
/* 1381 */     String str2 = paramString1;
/* 1382 */     String str3 = paramString2;
/* 1383 */     String str4 = paramString3;
/* 1384 */     String str5 = paramString4;
/* 1385 */     if (cls.getNum(str4, Integer.valueOf(-1)).intValue() != -1) str4 = "Style" + str4;
/* 1386 */     if (str2.substring(0, 1).equals("~")) str2 = cls.getLRStr(str2, "~", "rightr");
/*      */     else str1 = str1 + this.conf.jt.itake("global.tpl_common.editor_script", "tpl");
/* 1388 */     str1 = str1 + this.conf.jt.itake("global.tpl_common.editor_content", "tpl");
/* 1389 */     str1 = str1.replace("{$name}", encode.htmlencode(str2));
/* 1390 */     str1 = str1.replace("{$value}", encode.htmlencode(str3));
/* 1391 */     str1 = str1.replace("{$-style}", encode.htmlencode(str4));
/* 1392 */     str1 = str1.replace("{$-height}", encode.htmlencode(str5));
/* 1393 */     str1 = this.conf.jt.creplace(str1);
/* 1394 */     return str1;
/*      */   }
/*      */ 
/*      */   public String pagi(String paramString1, String paramString2, String paramString3, String paramString4)
/*      */   {
/* 1399 */     String str1 = "";
/* 1400 */     String str2 = paramString1;
/* 1401 */     String str3 = paramString2;
/* 1402 */     String str4 = paramString3;
/* 1403 */     String str5 = paramString4;
/* 1404 */     str1 = pagi(str2, str3, str4, str5, "");
/* 1405 */     return str1;
/*      */   }
/*      */ 
/*      */   public String pagi(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
/*      */   {
/* 1410 */     String str1 = "";
/*      */ 
/* 1412 */     Integer localInteger1 = Integer.valueOf(10);
/* 1413 */     Integer localInteger2 = localInteger1;
/* 1414 */     Object localObject = cls.getNum(paramString1, Integer.valueOf(0));
/* 1415 */     Integer localInteger3 = cls.getNum(paramString2, Integer.valueOf(0));
/* 1416 */     String str5 = cls.getString(paramString3);
/* 1417 */     String str6 = cls.getString(paramString4);
/* 1418 */     String str7 = cls.getString(paramString5);
/* 1419 */     if (cls.isEmpty(str7).booleanValue()) str7 = "pagi-1";
/* 1420 */     Integer localInteger4 = Integer.valueOf(0);
/* 1421 */     if (str6.equals("ct-cutepage")) localInteger4 = Integer.valueOf(1);
/* 1422 */     if (localInteger3.intValue() > localInteger4.intValue())
/*      */     {
/* 1424 */       str1 = this.conf.jt.itake("global.tpl_common." + str7, "tpl");
/* 1425 */       String str3 = "";
/* 1426 */       String str2 = cls.ctemplate(str1, "{@}");
/* 1427 */       if (((Integer)localObject).intValue() < 1) localObject = Integer.valueOf(1);
/* 1428 */       if (((Integer)localObject).intValue() > localInteger3.intValue()) localObject = localInteger3;
/* 1429 */       Integer localInteger5 = cls.getNum(cls.getLRStr(cls.toString(Double.valueOf(((Integer)localObject).intValue() - Math.floor(localInteger1.intValue() / 2))), ".", "left"), Integer.valueOf(0));
/* 1430 */       if (localInteger5.intValue() < 1) localInteger5 = Integer.valueOf(1);
/* 1431 */       Integer localInteger6 = Integer.valueOf(localInteger5.intValue() + localInteger2.intValue() - 1);
/* 1432 */       if (localInteger6.intValue() > localInteger3.intValue()) localInteger6 = localInteger3;
/* 1433 */       if (localInteger5.intValue() <= localInteger6.intValue())
/*      */       {
/* 1435 */         if (localInteger6.intValue() - localInteger5.intValue() < localInteger2.intValue() - 1)
/*      */         {
/* 1437 */           localInteger5 = Integer.valueOf(localInteger5.intValue() - (localInteger2.intValue() - 1 - (localInteger6.intValue() - localInteger5.intValue())));
/* 1438 */           if (localInteger5.intValue() < 1) localInteger5 = Integer.valueOf(1);
/*      */         }
/* 1440 */         for (int i = localInteger5.intValue(); i <= localInteger6.intValue(); ++i)
/*      */         {
/* 1442 */           String str4 = str2;
/* 1443 */           str4 = str4.replace("{$-num}", cls.toString(Integer.valueOf(i)));
/* 1444 */           str4 = str4.replace("{$-link}", str5.replace("[~page]", cls.toString(Integer.valueOf(i))));
/* 1445 */           if (i != ((Integer)localObject).intValue()) str4 = str4.replace("{$-class}", "");
/*      */           else str4 = str4.replace("{$-class}", "selected");
/* 1447 */           str3 = str3 + str4;
/*      */         }
/*      */       }
/* 1450 */       str1 = cls.ctemplates(str1, "{@}", str3);
/* 1451 */       str1 = str1.replace("{$-page1}", cls.toString(localObject));
/* 1452 */       str1 = str1.replace("{$-page2}", cls.toString(localInteger3));
/* 1453 */       str1 = str1.replace("{$-firstpagelink}", str5.replace("[~page]", "1"));
/* 1454 */       str1 = str1.replace("{$-lastpagelink}", str5.replace("[~page]", cls.toString(localInteger3)));
/* 1455 */       str1 = str1.replace("{$-tid}", encode.htmlencode(str6));
/* 1456 */       str1 = str1.replace("{$-value1}", cls.toString(Integer.valueOf((((Integer)localObject).equals(localInteger6)) ? ((Integer)localObject).intValue() : ((Integer)localObject).intValue() + 1)));
/* 1457 */       str1 = str1.replace("{$-baselink}", encode.scriptencode(encode.htmlencode(str5)));
/* 1458 */       str1 = this.conf.jt.creplace(str1);
/*      */     }
/* 1460 */     return ((String)str1);
/*      */   }
/*      */ 
/*      */   public String repathencode(String paramString)
/*      */   {
/* 1465 */     String str1 = paramString;
/* 1466 */     if ((!(cls.isEmpty(str1).booleanValue())) && (this.conf.repath.equals("1")))
/*      */     {
/* 1468 */       String str2 = this.conf.getNGenre();
/* 1469 */       String str3 = this.conf.getNURLPre() + this.conf.getNURL();
/* 1470 */       String str4 = str2 + "/";
/* 1471 */       String str5 = cls.getLRStr(str3, str2, "leftr");
/* 1472 */       str1 = str1.replace(str5, "{$->>repath}");
/*      */     }
/* 1474 */     return str1;
/*      */   }
/*      */ 
/*      */   public String repathdecode(String paramString)
/*      */   {
/* 1479 */     String str1 = paramString;
/* 1480 */     if (!(cls.isEmpty(str1).booleanValue()))
/*      */     {
/* 1482 */       String str2 = this.conf.getNGenre();
/* 1483 */       String str3 = this.conf.getNURLPre() + this.conf.getNURL();
/* 1484 */       String str4 = str2 + "/";
/* 1485 */       String str5 = cls.getLRStr(str3, str2, "leftr");
/* 1486 */       str1 = str1.replace("{$->>repath}", str5);
/*      */     }
/* 1488 */     return str1;
/*      */   }
/*      */ 
/*      */   public String selClass(String paramString)
/*      */   {
/* 1493 */     String str1 = "";
/* 1494 */     String str2 = paramString;
/* 1495 */     str1 = selClass(str2, "-1");
/* 1496 */     return str1;
/*      */   }
/*      */ 
/*      */   public String selClass(String paramString1, String paramString2)
/*      */   {
/* 1501 */     String str1 = "";
/*      */ 
/* 1503 */     String str5 = paramString1;
/* 1504 */     String str6 = paramString2;
/* 1505 */     String str7 = cls.getParameter(str5, "genre");
/* 1506 */     String str8 = cls.getParameter(str5, "lng");
/* 1507 */     String str9 = cls.getParameter(str5, "fid");
/* 1508 */     Integer localInteger1 = cls.getNum(cls.getParameter(str5, "class"), Integer.valueOf(0));
/* 1509 */     Integer localInteger2 = cls.getNum(cls.getParameter(str5, "inum"), Integer.valueOf(0));
/* 1510 */     str1 = this.conf.jt.itake("global.tpl_common.sel-class", "tpl");
/* 1511 */     String str3 = "";
/* 1512 */     String str2 = cls.ctemplate(str1, "{@}");
/* 1513 */     category localcategory = new category(this.conf);
/* 1514 */     String[][] arrayOfString = localcategory.getCatAry(str7, cls.getNum(str8, Integer.valueOf(0)));
/* 1515 */     if (arrayOfString != null)
/*      */     {
/* 1517 */       localInteger2 = Integer.valueOf(localInteger2.intValue() + 1);
/* 1518 */       for (int i = 0; i < arrayOfString.length; ++i)
/*      */       {
/* 1520 */         if ((!(cls.getNum(arrayOfString[i][2], Integer.valueOf(0)).equals(cls.getNum(str9, Integer.valueOf(0))))) || (
/* 1522 */           (!(str6.equals("-1"))) && (!(cls.cinstr(str6, arrayOfString[i][0], ",").booleanValue()))))
/*      */           continue;
/* 1524 */         String str4 = str2;
/* 1525 */         str4 = str4.replace("{$topic}", encode.htmlencode(arrayOfString[i][1]));
/* 1526 */         str4 = str4.replace("{$id}", encode.htmlencode(arrayOfString[i][0]));
/* 1527 */         if (!(cls.getNum(arrayOfString[i][0], Integer.valueOf(0)).equals(localInteger1))) str4 = str4.replace("{$-selected}", "");
/*      */         else str4 = str4.replace("{$-selected}", "selected=\"selected\"");
/* 1529 */         str4 = str4.replace("{$-prestr}", cls.getRepeatedString(this.conf.jt.itake("global.lng_common.sys-spsort", "lng"), localInteger2));
/* 1530 */         str4 = str4.replace("{$-child}", selClass("genre=" + str7 + ";lng=" + str8 + ";class=" + localInteger1 + ";inum=" + localInteger2 + ";fid=" + cls.getNum(arrayOfString[i][0], Integer.valueOf(0)), str6));
/* 1531 */         str3 = str3 + str4;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1536 */     str1 = cls.ctemplates(str1, "{@}", str3);
/* 1537 */     str1 = this.conf.jt.creplace(str1);
/* 1538 */     return str1;
/*      */   }
/*      */ 
/*      */   public Boolean sendMail(String paramString1, String paramString2, String paramString3)
/*      */   {
/* 1543 */     Boolean localBoolean = Boolean.valueOf(false);
/* 1544 */     String str1 = paramString1;
/* 1545 */     String str2 = paramString2;
/* 1546 */     String str3 = paramString3;
/* 1547 */     String str4 = this.conf.application.getInitParameter("mail_smtpusername");
/* 1548 */     if (cls.isEmpty(str4).booleanValue()) str4 = this.conf.jt.itake("global.config.mail-smtpusername", "cfg");
/* 1549 */     String str5 = this.conf.application.getInitParameter("mail_smtppassword");
/* 1550 */     if (cls.isEmpty(str5).booleanValue()) str5 = this.conf.jt.itake("global.config.mail-smtppassword", "cfg");
/* 1551 */     String str6 = this.conf.application.getInitParameter("mail_smtpfrom");
/* 1552 */     if (cls.isEmpty(str6).booleanValue()) str6 = this.conf.jt.itake("global.config.mail-smtpfrom", "cfg");
/* 1553 */     if (cls.isEmpty(str6).booleanValue()) str6 = str4;
/* 1554 */     String str7 = this.conf.application.getInitParameter("mail_smtpcharset");
/* 1555 */     if (cls.isEmpty(str7).booleanValue()) str7 = this.conf.jt.itake("global.config.mail-smtpcharset", "cfg");
/* 1556 */     String str8 = this.conf.application.getInitParameter("mail_smtpserver");
/* 1557 */     if (cls.isEmpty(str8).booleanValue()) str8 = this.conf.jt.itake("global.config.mail-smtpserver", "cfg");
/*      */     try
/*      */     {
/* 1560 */       String str9 = "";
/* 1561 */       Socket localSocket = new Socket(str8, 25);
/* 1562 */       BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localSocket.getInputStream()));
/* 1563 */       BufferedWriter localBufferedWriter = new BufferedWriter(new OutputStreamWriter(localSocket.getOutputStream()));
/* 1564 */       str9 = localBufferedReader.readLine();
/* 1565 */       if ((str9.substring(0, 3).equals("220")) || (str9.substring(0, 3).equals("250")))
/*      */       {
/* 1567 */         localBufferedWriter.write("HELO JTBC\n");
/* 1568 */         localBufferedWriter.flush();
/* 1569 */         str9 = localBufferedReader.readLine();
/* 1570 */         if (str9.substring(0, 3).equals("250"))
/*      */         {
/* 1572 */           localBufferedWriter.write("AUTH LOGIN\n");
/* 1573 */           localBufferedWriter.flush();
/* 1574 */           str9 = localBufferedReader.readLine();
/* 1575 */           if (str9.substring(0, 3).equals("334"))
/*      */           {
/* 1577 */             localBufferedWriter.write(encode.base64encode(str4.getBytes()) + "\n");
/* 1578 */             localBufferedWriter.flush();
/* 1579 */             str9 = localBufferedReader.readLine();
/* 1580 */             if (str9.substring(0, 3).equals("334"))
/*      */             {
/* 1582 */               localBufferedWriter.write(encode.base64encode(str5.getBytes()) + "\n");
/* 1583 */               localBufferedWriter.flush();
/* 1584 */               str9 = localBufferedReader.readLine();
/* 1585 */               if (str9.substring(0, 3).equals("235"))
/*      */               {
/* 1587 */                 localBufferedWriter.write("MAIL FROM: <" + str6 + ">\n");
/* 1588 */                 localBufferedWriter.flush();
/* 1589 */                 str9 = localBufferedReader.readLine();
/* 1590 */                 if (str9.substring(0, 3).equals("250"))
/*      */                 {
/* 1592 */                   localBufferedWriter.write("RCPT TO: <" + str1 + ">\n");
/* 1593 */                   localBufferedWriter.flush();
/* 1594 */                   str9 = localBufferedReader.readLine();
/* 1595 */                   if (str9.substring(0, 3).equals("250"))
/*      */                   {
/* 1597 */                     localBufferedWriter.write("DATA\n");
/* 1598 */                     localBufferedWriter.flush();
/* 1599 */                     str9 = localBufferedReader.readLine();
/* 1600 */                     if (str9.substring(0, 3).equals("354"))
/*      */                     {
/* 1602 */                       localBufferedWriter.write("MIME-Version: 1.0\nContent-type: text/html; charset=" + str7 + "\nTo: <" + str1 + ">\nFrom: <" + str6 + ">\nSubject: " + str2 + "\n\n" + str3 + "\n.\n");
/* 1603 */                       localBufferedWriter.flush();
/* 1604 */                       localBufferedWriter.write("QUIT\n");
/* 1605 */                       localBufferedWriter.flush();
/* 1606 */                       localBoolean = Boolean.valueOf(true);
/*      */                     }
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     } catch (Exception localException) {
/*      */     }
/* 1617 */     return localBoolean;
/*      */   }
/*      */ 
/*      */   public String webBase(String paramString)
/*      */   {
/* 1622 */     String str1 = "";
/* 1623 */     String str2 = paramString;
/* 1624 */     Integer localInteger = cls.getNum(this.conf.jt.itake("global.config.nbasehref", "cfg"), Integer.valueOf(0));
/* 1625 */     if (!(cls.isEmpty(str2).booleanValue())) localInteger = cls.getNum(this.conf.jt.itake("global." + str2 + ":config.nbasehref", "cfg"), Integer.valueOf(0));
/* 1626 */     if (localInteger.intValue() == 1)
/*      */     {
/* 1628 */       str1 = this.conf.jt.itake("global.tpl_public.base", "tpl");
/* 1629 */       str1 = str1.replace("{$-base}", cls.getLRStr(new StringBuilder().append(this.conf.getNURLPre()).append(this.conf.getNURI()).toString(), "/", "leftr") + "/");
/* 1630 */       str1 = this.conf.jt.creplace(str1);
/*      */     }
/* 1632 */     return str1;
/*      */   }
/*      */ 
/*      */   public String webHead(String paramString)
/*      */   {
/* 1637 */     String str1 = "";
/* 1638 */     String str2 = paramString;
/* 1639 */     str1 = this.conf.jt.itake("global.tpl_public." + str2, "tpl");
/* 1640 */     str1 = this.conf.jt.creplace(str1);
/* 1641 */     return str1;
/*      */   }
/*      */ 
/*      */   public String webFoot(String paramString)
/*      */   {
/* 1646 */     String str1 = "";
/* 1647 */     String str2 = paramString;
/* 1648 */     str1 = this.conf.jt.itake("global.tpl_public." + str2, "tpl");
/* 1649 */     str1 = this.conf.jt.creplace(str1);
/* 1650 */     return str1;
/*      */   }
/*      */ 
/*      */   public String webMessage(String paramString)
/*      */   {
/* 1655 */     String str1 = paramString;
/* 1656 */     String str2 = this.conf.jt.itake("global.tpl_common.wfront-message", "tpl");
/* 1657 */     str2 = str2.replace("{$message}", encode.htmlencode(str1));
/* 1658 */     str2 = this.conf.jt.creplace(str2);
/* 1659 */     return str2;
/*      */   }
/*      */ 
/*      */   public String webMessages(String paramString1, String paramString2)
/*      */   {
/* 1664 */     String str1 = paramString1;
/* 1665 */     String str2 = paramString2;
/* 1666 */     String str3 = this.conf.jt.itake("global.tpl_common.wfront-messages", "tpl");
/* 1667 */     str3 = str3.replace("{$message}", encode.htmlencode(str1));
/* 1668 */     str3 = str3.replace("{$backurl}", encode.htmlencode(str2));
/* 1669 */     str3 = this.conf.jt.creplace(str3);
/* 1670 */     return str3;
/*      */   }
/*      */ 
/*      */   public String xmlSelect(String paramString1, String paramString2, String paramString3)
/*      */   {
/* 1675 */     String str1 = "";
/* 1676 */     String str2 = paramString1;
/* 1677 */     String str3 = paramString2;
/* 1678 */     String str4 = paramString3;
/* 1679 */     str1 = xmlSelect(str2, str3, str4, "");
/* 1680 */     return str1;
/*      */   }
/*      */ 
/*      */   public String xmlSelect(String paramString1, String paramString2, String paramString3, String paramString4)
/*      */   {
/* 1685 */     String str1 = paramString1;
/* 1686 */     String str2 = paramString2;
/* 1687 */     String str3 = paramString3;
/* 1688 */     String str4 = paramString4;
/* 1689 */     String str5 = "";
/* 1690 */     String str6 = this.conf.jt.itake("global.tpl_config.xmlselect_" + str3, "tpl");
/* 1691 */     String str7 = this.conf.jt.itake("global.tpl_config.xmlselect_un" + str3, "tpl");
/* 1692 */     if ((!(cls.isEmpty(str6).booleanValue())) && (!(cls.isEmpty(str7).booleanValue())))
/*      */     {
/* 1694 */       String[][] arrayOfString = this.conf.jt.itakes(str1, "sel");
/* 1695 */       for (int i = 0; i < arrayOfString.length; ++i)
/*      */       {
/* 1697 */         if (cls.cinstr(str2, arrayOfString[i][0], ",").booleanValue()) str5 = str5 + str6;
/*      */         else str5 = str5 + str7;
/* 1699 */         str5 = str5.replace("{$value}", encode.htmlencode(arrayOfString[i][0]));
/* 1700 */         str5 = str5.replace("{$explain}", encode.htmlencode(arrayOfString[i][1]));
/*      */       }
/* 1702 */       if (!(cls.isEmpty(str4).booleanValue())) str5 = str5.replace("{$name}", encode.htmlencode(str4));
/* 1703 */       str5 = this.conf.jt.creplace(str5);
/*      */     }
/* 1705 */     return str5;
/*      */   }
/*      */ 
/*      */   public common(conf paramconf)
/*      */   {
/* 1710 */     this.conf = paramconf;
/*      */   }
/*      */ }