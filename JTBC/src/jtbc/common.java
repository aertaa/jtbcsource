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
		   import jtbc.dbc.dbc;
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
/*  335 */         if (localInteger.intValue() > ((String[])localObject).length) localInteger = Integer.valueOf(((String[])localObject).length);
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
/*  360 */       if (localObject != null) str1 = cls.toString(Integer.valueOf(((String[])localObject).length));
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
/*      */   public String getRsValue(String paramString1, String paramString2)
/*      */   {
/*  446 */     String str1 = paramString1;
/*  447 */     String str2 = paramString2;
/*  448 */     String str3 = "";
/*  449 */     Object[][] arrayOfObject = (Object[][])null;
/*  450 */     if (str1.equals("rs")) arrayOfObject = this.conf.rsAry;
/*  451 */     else if (str1.equals("rsb")) arrayOfObject = this.conf.rsbAry;
/*  452 */     else if (str1.equals("rsc")) arrayOfObject = this.conf.rscAry;
/*  453 */     else if (str1.equals("rst")) arrayOfObject = this.conf.rstAry;
/*  454 */     if (arrayOfObject != null)
/*      */     {
/*  456 */       for (int i = 0; i < arrayOfObject.length; ++i)
/*      */       {
/*  458 */         if (!(cls.toString(arrayOfObject[i][0]).equals(str2)))
/*      */           continue;
/*  460 */         str3 = cls.toString(arrayOfObject[i][1]);
/*  461 */         break;
/*      */       }
/*      */     }
/*      */ 
/*  465 */     return str3;
/*      */   }
/*      */ 
/*      */   public String getSearchOptions(String paramString)
/*      */   {
/*  470 */     String str1 = paramString;
/*  471 */     String str2 = "";
/*  472 */     if (!(cls.isEmpty(str1).booleanValue()))
/*      */     {
/*  474 */       Object localObject = "";
/*  475 */       String[] arrayOfString = str1.split(Pattern.quote(","));
/*  476 */       String str3 = this.conf.jt.itake("global.tpl_config.option_unselect", "tpl");
/*  477 */       for (int i = 0; i < arrayOfString.length; ++i)
/*      */       {
/*  479 */         localObject = str3;
/*  480 */         localObject = ((String)localObject).replace("{$explain}", this.conf.jt.itake("global.sel_search." + encode.htmlencode(arrayOfString[i]), "sel"));
/*  481 */         localObject = ((String)localObject).replace("{$value}", encode.htmlencode(arrayOfString[i]));
/*  482 */         str2 = str2 + ((String)localObject);
/*      */       }
/*      */     }
/*  485 */     return ((String)str2);
/*      */   }
/*      */ 
/*      */   public String getSwitchOptions(String paramString)
/*      */   {
/*  490 */     String str1 = paramString;
/*  491 */     String str2 = "";
/*  492 */     if (!(cls.isEmpty(str1).booleanValue()))
/*      */     {
/*  494 */       Object localObject = "";
/*  495 */       String[] arrayOfString = str1.split(Pattern.quote(","));
/*  496 */       String str3 = this.conf.jt.itake("global.tpl_config.option_unselect", "tpl");
/*  497 */       for (int i = 0; i < arrayOfString.length; ++i)
/*      */       {
/*  499 */         localObject = str3;
/*  500 */         localObject = ((String)localObject).replace("{$explain}", this.conf.jt.itake("global.sel_switch." + encode.htmlencode(arrayOfString[i]), "sel"));
/*  501 */         localObject = ((String)localObject).replace("{$value}", encode.htmlencode(arrayOfString[i]));
/*  502 */         str2 = str2 + ((String)localObject);
/*      */       }
/*      */     }
/*  505 */     return ((String)str2);
/*      */   }
/*      */ 
/*      */   public Integer getTopID(String paramString1, String paramString2)
/*      */   {
/*  510 */     Integer localInteger = Integer.valueOf(-1);
/*  511 */     String str1 = paramString1;
/*  512 */     String str2 = paramString2;
/*  513 */     dbc localdbc = db.newInstance(this.conf);
/*  514 */     String str3 = "select max(" + str2 + ") from " + str1;
/*  515 */     Object[] arrayOfObject = localdbc.getDataAry(str3);
/*  516 */     if (arrayOfObject != null) localInteger = cls.getNum(cls.toString(localdbc.getValue((Object[][])(Object[][])arrayOfObject[0], 0)), Integer.valueOf(0));
/*  517 */     return localInteger;
/*      */   }
/*      */ 
/*      */   public String getLngID(String paramString)
/*      */   {
/*  522 */     String str1 = paramString;
/*  523 */     String str2 = "";
/*  524 */     String[][] arrayOfString = this.conf.jt.itakes("global.sel_lng.all", "sel");
/*  525 */     for (int i = 0; i < arrayOfString.length; ++i)
/*      */     {
/*  527 */       String str3 = arrayOfString[i][0];
/*  528 */       if (str3.indexOf(":") == -1)
/*      */         continue;
/*  530 */       String[] arrayOfString1 = str3.split(Pattern.quote(":"));
/*  531 */       if ((arrayOfString1.length != 2) || 
/*  533 */         (!(arrayOfString1[0].equals(str1))))
/*      */         continue;
/*  535 */       str2 = arrayOfString1[1];
/*  536 */       break;
/*      */     }
/*      */ 
/*  541 */     return str2;
/*      */   }
/*      */ 
/*      */   public String getLngText(String paramString)
/*      */   {
/*  546 */     String str1 = paramString;
/*  547 */     String str2 = "";
/*  548 */     String[][] arrayOfString = this.conf.jt.itakes("global.sel_lng.all", "sel");
/*  549 */     for (int i = 0; i < arrayOfString.length; ++i)
/*      */     {
/*  551 */       String str3 = arrayOfString[i][0];
/*  552 */       if (str3.indexOf(":") == -1)
/*      */         continue;
/*  554 */       String[] arrayOfString1 = str3.split(Pattern.quote(":"));
/*  555 */       if ((arrayOfString1.length != 2) || 
/*  557 */         (!(arrayOfString1[1].equals(str1))))
/*      */         continue;
/*  559 */       str2 = arrayOfString1[0];
/*  560 */       break;
/*      */     }
/*      */ 
/*  565 */     return str2;
/*      */   }
/*      */ 
/*      */   public String itransfer(String paramString)
/*      */   {
/*  570 */     String str1 = "";
/*  571 */     String str2 = paramString;
/*  572 */     String str3 = cls.getParameter(str2, "method");
/*  573 */     if (cls.isEmpty(str3).booleanValue()) str1 = itransferStandard(str2);
/*  576 */     else if (str3.equals("sql")) str1 = itransferSQL(str2);
/*  577 */     else if (str3.equals("itakes")) str1 = itransferITakes(str2);
/*  578 */     else if (str3.equals("multigenre")) str1 = itransferMultiGenre(str2);
/*      */ 
/*  580 */     return str1;
/*      */   }
/*      */ 
/*      */   public String itransferStandard(String paramString)
/*      */   {
/*  585 */     Object localObject1 = "";
/*      */ 
/*  587 */     String str4 = paramString;
/*  588 */     String str5 = cls.getParameter(str4, "tpl");
/*  589 */     String str6 = cls.getParameter(str4, "tplstr");
/*  590 */     String str7 = cls.getParameter(str4, "type");
/*  591 */     Object localObject2 = cls.getParameter(str4, "genre");
/*  592 */     String str8 = cls.getParameter(str4, "ndatabase");
/*  593 */     String str9 = cls.getParameter(str4, "nfpre");
/*  594 */     String str10 = cls.getParameter(str4, "osql");
/*  595 */     String str11 = cls.getParameter(str4, "osqlorder");
/*  596 */     String str12 = cls.getParameter(str4, "ocname");
/*  597 */     String str13 = cls.getParameter(str4, "ocmode");
/*  598 */     String str14 = cls.getParameter(str4, "baseurl");
/*  599 */     String str15 = cls.getParameter(str4, "vars");
/*  600 */     Integer localInteger1 = cls.getNum(cls.getParameter(str4, "topx"), Integer.valueOf(-1));
/*  601 */     Integer localInteger2 = cls.getNum(cls.getParameter(str4, "cls"), Integer.valueOf(-1));
/*  602 */     Integer localInteger3 = cls.getNum(cls.getParameter(str4, "class"), Integer.valueOf(-1));
/*  603 */     Integer localInteger4 = cls.getNum(cls.getParameter(str4, "lng"), Integer.valueOf(-1));
/*  604 */     Integer localInteger5 = cls.getNum(cls.getParameter(str4, "bid"), Integer.valueOf(-1));
/*  605 */     Integer localInteger6 = cls.getNum(this.conf.dbtype, Integer.valueOf(0));
/*  606 */     if (localInteger4.intValue() == -1) localInteger4 = cls.getNum(this.conf.getNLng(), Integer.valueOf(-1));
/*  607 */     if (localInteger1.intValue() > 0)
/*      */     {
/*  609 */       String str16 = this.conf.getNGenre();
/*  610 */       if ((cls.isEmpty(str14).booleanValue()) && 
/*  612 */         (!(cls.isEmpty(localObject2).booleanValue())) && (!(((String)localObject2).equals(str16))))
/*      */       {
/*  614 */         str14 = this.conf.getActualRoute((String)localObject2);
/*  615 */         if (!(cls.getRight(str14, Integer.valueOf(1)).equals("/"))) str14 = str14 + "/";
/*      */       }
/*      */ 
/*  618 */       if (cls.isEmpty(localObject2).booleanValue()) localObject2 = str16;
/*  619 */       String str17 = "";
/*  620 */       String str18 = "";
/*  621 */       String str19 = "";
/*  622 */       if (!(cls.isEmpty(str8).booleanValue()))
/*      */       {
/*  624 */         str17 = cls.getString(str8);
/*  625 */         str18 = cls.getString(str9);
/*      */       }
/*  629 */       else if (cls.isEmpty(str12).booleanValue())
/*      */       {
/*  631 */         str17 = cls.getString(this.conf.jt.itake("global." + ((String)localObject2) + ":config.ndatabase", "cfg"));
/*  632 */         str18 = cls.getString(this.conf.jt.itake("global." + ((String)localObject2) + ":config.nfpre", "cfg"));
/*      */       }
/*      */       else
/*      */       {
/*  636 */         str17 = cls.getString(this.conf.jt.itake("global." + ((String)localObject2) + ":config.ndatabase-" + str12, "cfg"));
/*  637 */         str18 = cls.getString(this.conf.jt.itake("global." + ((String)localObject2) + ":config.nfpre-" + str12, "cfg"));
/*      */       }
/*      */ 
/*  640 */       str19 = cls.cfnames(str18, "id");
/*  641 */       if (!(cls.isEmpty(str17).booleanValue()))
/*      */       {
/*      */         Object localObject5;
/*  643 */         String str20 = "";
/*  644 */         String str21 = "";
/*  645 */         if (str7.equals("new"))
/*      */         {
/*  647 */           str20 = "select * from " + str17 + " where " + cls.cfnames(str18, "hidden") + "=0";
/*  648 */           str21 = " order by " + cls.cfnames(str18, "time") + " desc";
/*      */         }
/*  650 */         else if (str7.equals("-new"))
/*      */         {
/*  652 */           str20 = "select * from " + str17 + " where " + cls.cfnames(str18, "hidden") + "=0";
/*  653 */           str21 = " order by " + cls.cfnames(str18, "time") + " asc";
/*      */         }
/*  655 */         else if (str7.equals("@new"))
/*      */         {
/*  657 */           str20 = "select * from " + str17 + " where 1=1";
/*  658 */           str21 = " order by " + cls.cfnames(str18, "time") + " desc";
/*      */         }
/*  660 */         else if (str7.equals("top"))
/*      */         {
/*  662 */           str20 = "select * from " + str17 + " where " + cls.cfnames(str18, "hidden") + "=0";
/*  663 */           str21 = " order by " + str19 + " desc";
/*      */         }
/*  665 */         else if (str7.equals("-top"))
/*      */         {
/*  667 */           str20 = "select * from " + str17 + " where " + cls.cfnames(str18, "hidden") + "=0";
/*  668 */           str21 = " order by " + str19 + " asc";
/*      */         }
/*  670 */         else if (str7.equals("@top"))
/*      */         {
/*  672 */           str20 = "select * from " + str17 + " where 1=1";
/*  673 */           str21 = " order by " + str19 + " desc";
/*      */         }
/*  675 */         else if (str7.equals("commendatory"))
/*      */         {
/*  677 */           str20 = "select * from " + str17 + " where " + cls.cfnames(str18, "hidden") + "=0 and " + cls.cfnames(str18, "commendatory") + "=1";
/*  678 */           str21 = " order by " + cls.cfnames(str18, "time") + " desc";
/*      */         }
/*  680 */         else if (str7.equals("-commendatory"))
/*      */         {
/*  682 */           str20 = "select * from " + str17 + " where " + cls.cfnames(str18, "hidden") + "=0 and " + cls.cfnames(str18, "commendatory") + "=1";
/*  683 */           str21 = " order by " + cls.cfnames(str18, "time") + " asc";
/*      */         }
/*  685 */         else if (str7.equals("@commendatory"))
/*      */         {
/*  687 */           str20 = "select * from " + str17 + " where " + cls.cfnames(str18, "commendatory") + "=1";
/*  688 */           str21 = " order by " + cls.cfnames(str18, "time") + " desc";
/*      */         }
/*  690 */         else if (str7.equals("up"))
/*      */         {
/*  692 */           str20 = "select * from " + str17 + " where " + cls.cfnames(str18, "hidden") + "=0 and " + str19 + ">" + localInteger5;
/*  693 */           str21 = " order by " + str19 + " asc";
/*      */         }
/*  695 */         else if (str7.equals("down"))
/*      */         {
/*  697 */           str20 = "select * from " + str17 + " where " + cls.cfnames(str18, "hidden") + "=0 and " + str19 + "<" + localInteger5;
/*  698 */           str21 = " order by " + str19 + " desc";
/*      */         }
/*  700 */         if (localInteger2.intValue() != -1)
/*      */         {
/*  702 */           Object localObject3 = new category(this.conf);
/*  703 */           String str22 = ((category)localObject3).getClassChildIds((String)localObject2, localInteger4, cls.toString(localInteger2));
/*  704 */           if (!(cls.isEmpty(str22).booleanValue())) str20 = str20 + " and " + cls.cfnames(str18, "class") + " in (" + str22 + ")";
/*      */         }
/*  706 */         if (localInteger3.intValue() != -1) str20 = str20 + " and " + cls.cfnames(str18, "class") + "=" + localInteger3;
/*  707 */         if ((localInteger4.intValue() != -1) && (localInteger4.intValue() != -100)) str20 = str20 + " and " + cls.cfnames(str18, "lng") + "=" + localInteger4;
/*  708 */         if (!(cls.isEmpty(str10).booleanValue())) str20 = str20 + str10;
/*  709 */         if (!(cls.isEmpty(str11).booleanValue())) str21 = str11;
/*  710 */         str20 = str20 + str21;
/*  711 */         if ((localInteger6.intValue() >= 0) && (localInteger6.intValue() < 10)) str20 = str20 + " limit 0," + localInteger1;
/*  712 */         if ((localInteger6.intValue() >= 10) && (localInteger6.intValue() < 20)) str20 = "select top " + localInteger1 + " *" + cls.getLRStr(str20, "select *", "rightr");
/*  713 */         if ((localInteger6.intValue() >= 20) && (localInteger6.intValue() < 30)) str20 = str20 + " limit " + localInteger1;
/*  714 */         if (!(cls.isEmpty(str6).booleanValue())) localObject1 = str6;
/*  717 */         else if (str5.indexOf(".") != -1) localObject1 = this.conf.jt.itake(str5, "tpl");
/*      */         else localObject1 = this.conf.jt.itake("global.tpl_transfer." + str5, "tpl");
/*      */ 
/*  720 */         if (!(cls.isEmpty(str15).booleanValue()))
/*      */         {
/*  722 */           Object localObject3 = str15.split(Pattern.quote("|"));
/*  723 */           for (int i = 0; i < ((String[])localObject3).length; ++i)
/*      */           {
/*  725 */             Object localObject4 = ((String[])localObject3)[i];
/*  726 */             if (cls.isEmpty(localObject4).booleanValue())
/*      */               continue;
/*  728 */             localObject5 = ((String)localObject4).split(Pattern.quote("="));
/*  729 */             if (((String[])localObject5).length != 2) continue; localObject1 = ((String)localObject1).replace("{$" + ((String[])localObject5)[0] + "}", ((String[])localObject5)[1]);
/*      */           }
/*      */         }
/*      */ 
/*  733 */         Object localObject3 = db.newInstance(this.conf);
/*  734 */         Object[] arrayOfObject = ((dbc)localObject3).getDataAry(str20);
/*  735 */         if (arrayOfObject != null)
/*      */         {
/*  737 */           String str2 = "";
/*  738 */           String str1 = cls.ctemplate((String)localObject1, "{@}");
/*  739 */           for (int j = 0; j < arrayOfObject.length; ++j)
/*      */           {
/*  741 */             String str3 = str1;
/*  742 */             localObject5 = (Object[][])(Object[][])arrayOfObject[j];
/*  743 */             for (int k = 0; k < ((Object[][])(Object[][])localObject5).length; ++k)
/*      */             {
/*  745 */              // ((Object[][])(Object[][])localObject5)[k][0] = cls.getLRStr((String)localObject5[k][0], str18, "rightr");
						((Object[][])(Object[][])localObject5)[k][0] = cls.getLRStr((String)((Object[][])(Object[][])localObject5)[k][0], str18, "rightr");
/*  746 */               str3 = str3.replace("{$" + cls.toString(((Object[][])(Object[][])localObject5)[k][0]) + "}", encode.htmlencode(cls.toString(((Object[][])(Object[][])localObject5)[k][1]), "1"));
/*      */             }
/*  748 */             this.conf.rstAry = ((Object[][])localObject5);
/*  749 */             str3 = str3.replace("{$-i}", cls.toString(Integer.valueOf(j)));
/*  750 */             str3 = str3.replace("{$-genre}", (CharSequence)localObject2);
/*  751 */             str3 = str3.replace("{$-baseurl}", str14);
/*      */ 
/*  753 */             for (int k = 2; k < 7; ++k)
/*      */             {
/*  755 */               int l = j % k + 1;
/*  756 */               str3 = str3.replace("{$-!mod" + k + "}", cls.toString(Integer.valueOf(l)));
/*  757 */               if (l != k) str3 = str3.replace("{$-!mod" + k + "-string}", "");
/*      */               else str3 = str3.replace("{$-!mod" + k + "-string}", cls.toString(str13));
/*      */             }
/*      */ 
/*  761 */             str3 = this.conf.jt.creplace(str3);
/*  762 */             str2 = str2 + str3;
/*      */           }
/*  764 */           localObject1 = cls.ctemplates((String)localObject1, "{@}", str2);
/*      */         } else {
/*  766 */           localObject1 = ""; }
/*  767 */         localObject1 = this.conf.jt.creplace((String)localObject1);
/*      */       }
/*      */     }
/*  770 */     return ((String)(String)(String)(String)localObject1);
/*      */   }
/*      */ 
/*      */   public String itransferSQL(String paramString)
/*      */   {
/*  775 */     Object localObject1 = "";
/*      */ 
/*  777 */     String str4 = paramString;
/*  778 */     String str5 = cls.getParameter(str4, "sql");
/*  779 */     String str6 = cls.getParameter(str4, "tpl");
/*  780 */     String str7 = cls.getParameter(str4, "tplstr");
/*  781 */     Object localObject2 = cls.getParameter(str4, "genre");
/*  782 */     String str8 = cls.getParameter(str4, "ocmode");
/*  783 */     String str9 = cls.getParameter(str4, "baseurl");
/*  784 */     String str10 = cls.getParameter(str4, "vars");
/*  785 */     if (!(cls.isEmpty(str5).booleanValue()))
/*      */     {
/*  787 */       String str11 = cls.getLRStr(str5, " ", "left").toLowerCase();
/*  788 */       if (str11.equals("select"))
/*      */       {
/*      */         Object localObject5;
/*  790 */         String str12 = str5;
/*  791 */         String str13 = this.conf.getNGenre();
/*  792 */         if ((cls.isEmpty(str9).booleanValue()) && 
/*  794 */           (!(cls.isEmpty(localObject2).booleanValue())) && (!(((String)localObject2).equals(str13))))
/*      */         {
/*  796 */           str9 = this.conf.getActualRoute((String)localObject2);
/*  797 */           if (!(cls.getRight(str9, Integer.valueOf(1)).equals("/"))) str9 = str9 + "/";
/*      */         }
/*      */ 
/*  800 */         if (cls.isEmpty(localObject2).booleanValue()) localObject2 = str13;
/*      */ 
/*  802 */         if (!(cls.isEmpty(str7).booleanValue())) localObject1 = str7;
/*  805 */         else if (str6.indexOf(".") != -1) localObject1 = this.conf.jt.itake(str6, "tpl");
/*      */         else localObject1 = this.conf.jt.itake("global.tpl_transfer." + str6, "tpl");
/*      */ 
/*  808 */         if (!(cls.isEmpty(str10).booleanValue()))
/*      */         {
/*  810 */           Object localObject3 = str10.split(Pattern.quote("|"));
/*  811 */           for (int i = 0; i < ((Object[])localObject3).length; ++i)
/*      */           {
/*  813 */             Object localObject4 = ((Object[])localObject3)[i];
/*  814 */             if (cls.isEmpty(localObject4).booleanValue())
/*      */               continue;
/*  816 */             localObject5 = ((String)localObject4).split(Pattern.quote("="));
/*  817 */             if (((Object[])localObject5).length!= 2) continue; localObject1 = ((String)localObject1).replace("{$" + ((Object[])localObject5)[0] + "}", ((String[])localObject5)[1]);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  822 */         Object localObject3 = db.newInstance(this.conf);
/*  823 */         Object[] arrayOfObject = ((dbc)localObject3).getDataAry(str12);
/*  824 */         if (arrayOfObject != null)
/*      */         {
/*  826 */           String str2 = "";
/*  827 */           String str1 = cls.ctemplate((String)localObject1, "{@}");
/*  828 */           for (int j = 0; j < arrayOfObject.length; ++j)
/*      */           {
/*  830 */             String str3 = str1;
/*  831 */             localObject5 = (Object[][])(Object[][])arrayOfObject[j];
/*  832 */             for (int k = 0; k < ((Object[][])(Object[][])localObject5).length; ++k)
/*      */             {
/*  834 */               str3 = str3.replace("{$" + cls.toString(((Object[][])localObject5)[k][0]) + "}", encode.htmlencode(cls.toString(((Object[][])localObject5)[k][1]), "1"));
/*      */             }
/*  836 */             this.conf.rstAry = ((Object[][])localObject5);
/*  837 */             str3 = str3.replace("{$-i}", cls.toString(Integer.valueOf(j)));
/*  838 */             str3 = str3.replace("{$-genre}", (CharSequence)localObject2);
/*  839 */             str3 = str3.replace("{$-baseurl}", str9);
/*      */ 
/*  841 */             for (int k = 2; k < 7; ++k)
/*      */             {
/*  843 */               int l = j % k + 1;
/*  844 */               str3 = str3.replace("{$-!mod" + k + "}", cls.toString(Integer.valueOf(l)));
/*  845 */               if (l != k) str3 = str3.replace("{$-!mod" + k + "-string}", "");
/*      */               else str3 = str3.replace("{$-!mod" + k + "-string}", cls.toString(str8));
/*      */             }
/*      */ 
/*  849 */             str3 = this.conf.jt.creplace(str3);
/*  850 */             str2 = str2 + str3;
/*      */           }
/*  852 */           localObject1 = cls.ctemplates((String)localObject1, "{@}", str2);
/*      */         } else {
/*  854 */           localObject1 = ""; }
/*  855 */         localObject1 = this.conf.jt.creplace((String)localObject1);
/*      */       }
/*      */     }
/*  858 */     return ((String)(String)(String)(String)localObject1);
/*      */   }
/*      */ 
/*      */   public String itransferITakes(String paramString)
/*      */   {
/*  863 */     Object localObject1 = "";
/*      */ 
/*  865 */     String str4 = paramString;
/*  866 */     String str5 = cls.getParameter(str4, "xinfostr");
/*  867 */     String str6 = cls.getParameter(str4, "xinfotype");
/*  868 */     String str7 = cls.getParameter(str4, "xinfolimit");
/*  869 */     String str8 = cls.getParameter(str4, "tpl");
/*  870 */     String str9 = cls.getParameter(str4, "tplstr");
/*  871 */     Object localObject2 = cls.getParameter(str4, "genre");
/*  872 */     String str10 = cls.getParameter(str4, "ocmode");
/*  873 */     String str11 = cls.getParameter(str4, "baseurl");
/*  874 */     String str12 = cls.getParameter(str4, "vars");
/*  875 */     if (!(cls.isEmpty(str5).booleanValue()))
/*      */     {
/*      */       Object localObject3;
/*  877 */       String str13 = this.conf.getNGenre();
/*  878 */       if ((cls.isEmpty(str11).booleanValue()) && 
/*  880 */         (!(cls.isEmpty(localObject2).booleanValue())) && (!(((String)localObject2).equals(str13))))
/*      */       {
/*  882 */         str11 = this.conf.getActualRoute((String)localObject2);
/*  883 */         if (!(cls.getRight(str11, Integer.valueOf(1)).equals("/"))) str11 = str11 + "/";
/*      */       }
/*      */ 
/*  886 */       if (cls.isEmpty(localObject2).booleanValue()) localObject2 = str13;
/*      */ 
/*  888 */       if (!(cls.isEmpty(str9).booleanValue())) localObject1 = str9;
/*  891 */       else if (str8.indexOf(".") != -1) localObject1 = this.conf.jt.itake(str8, "tpl");
/*      */       else localObject1 = this.conf.jt.itake("global.tpl_transfer." + str8, "tpl");
/*      */ 
/*  894 */       if (!(cls.isEmpty(str12).booleanValue()))
/*      */       {
/*  896 */         String[] arrayOfString = str12.split(Pattern.quote("|"));
/*  897 */         for (int j = 0; j < arrayOfString.length; ++j)
/*      */         {
/*  899 */           String str14 = arrayOfString[j];
/*  900 */           if (cls.isEmpty(str14).booleanValue())
/*      */             continue;
/*  902 */           localObject3 = str14.split(Pattern.quote("="));
/*  903 */           if (((Object[])localObject3).length != 2) continue; localObject1 = ((String)localObject1).replace("{$" + ((Object[])localObject3)[0] + "}", ((String[])localObject3)[1]);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  908 */       int i = 0;
/*  909 */       if (cls.isEmpty(str6).booleanValue()) str6 = "lng";
/*  910 */       String[][] arrayOfString1 = this.conf.jt.itakes(str5, str6);
/*  911 */       if (arrayOfString1 != null)
/*      */       {
/*  913 */         String str2 = "";
/*  914 */         String str1 = cls.ctemplate((String)localObject1, "{@}");
/*  915 */         for (int k = 0; k < arrayOfString1.length; ++k)
/*      */         {
/*  917 */           localObject3 = arrayOfString1[k][0];
/*  918 */           String str15 = arrayOfString1[k][1];
/*  919 */           if ((!(cls.isEmpty(str7).booleanValue())) && (!(cls.cinstr(str7, (String)localObject3, ",").booleanValue())))
/*      */             continue;
/*  921 */           String str3 = str1;
/*  922 */           str3 = str3.replace("{$namestring}", (CharSequence)localObject3);
/*  923 */           str3 = str3.replace("{$valuestring}", str15);
/*  924 */           str3 = str3.replace("{$name}", encode.htmlencode((String)localObject3));
/*  925 */           str3 = str3.replace("{$value}", encode.htmlencode(str15));
/*  926 */           str3 = str3.replace("{$-i}", cls.toString(Integer.valueOf(i)));
/*  927 */           str3 = str3.replace("{$-genre}", (CharSequence)localObject2);
/*  928 */           str3 = str3.replace("{$-baseurl}", str11);
/*      */ 
/*  930 */           for (int l = 2; l < 7; ++l)
/*      */           {
/*  932 */             int i1 = i % l + 1;
/*  933 */             str3 = str3.replace("{$-!mod" + l + "}", cls.toString(Integer.valueOf(i1)));
/*  934 */             if (i1 != l) str3 = str3.replace("{$-!mod" + l + "-string}", "");
/*      */             else str3 = str3.replace("{$-!mod" + l + "-string}", cls.toString(str10));
/*      */           }
/*      */ 
/*  938 */           str2 = str2 + str3;
/*  939 */           ++i;
/*      */         }
/*      */ 
/*  942 */         localObject1 = cls.ctemplates((String)localObject1, "{@}", str2);
/*      */       } else {
/*  944 */         localObject1 = ""; }
/*  945 */       localObject1 = this.conf.jt.creplace((String)localObject1);
/*      */     }
/*  947 */     return ((String)(String)(String)localObject1);
/*      */   }
/*      */ 
/*      */   public String itransferMultiGenre(String paramString)
/*      */   {
/*  952 */     Object localObject1 = "";
/*      */ 	   Object localObject2 = "";
/*  954 */     String str4 = paramString;
/*  955 */     String str5 = cls.getParameter(str4, "tpl");
/*  956 */     String str6 = cls.getParameter(str4, "tplstr");
/*  957 */     String str7 = cls.getParameter(str4, "type");
/*  958 */     String str8 = cls.getParameter(str4, "genre");
/*  959 */     String str9 = cls.getParameter(str4, "field");
/*  960 */     String str10 = cls.getParameter(str4, "osql");
/*  961 */     String str11 = cls.getParameter(str4, "osqlorder");
/*  962 */     String str12 = cls.getParameter(str4, "ocmode");
/*  963 */     String str13 = cls.getParameter(str4, "baseurl");
/*  964 */     String str14 = cls.getParameter(str4, "vars");
/*  965 */     Integer localInteger1 = cls.getNum(cls.getParameter(str4, "topx"), Integer.valueOf(-1));
/*  966 */     Integer localInteger2 = cls.getNum(cls.getParameter(str4, "lng"), Integer.valueOf(-1));
/*  967 */     Integer localInteger3 = cls.getNum(this.conf.dbtype, Integer.valueOf(0));
/*  968 */     if (localInteger2.intValue() == -1) localInteger2 = cls.getNum(this.conf.getNLng(), Integer.valueOf(-1));
/*  969 */     if (localInteger1.intValue() > 0)
/*      */     {
/*  971 */       String str15 = this.conf.getNGenre();
/*  972 */       if (!(cls.isEmpty(str8).booleanValue()))
/*      */       {
/*      */         String str19;
/*      */         Object localObject3;
/*  974 */         String str16 = "";
/*  975 */         String str17 = "";
/*  976 */         str16 = str16 + "select * from (";
/*  977 */         String[] arrayOfString1 = str8.split(Pattern.quote("&"));
/*  978 */         for (int i = 0; i < arrayOfString1.length; ++i)
/*      */         {
/*  980 */           String str18 = arrayOfString1[i];
/*  981 */           str19 = cls.getString(this.conf.jt.itake("global." + str18 + ":config.ndatabase", "cfg"));
/*  982 */           localObject3 = cls.getString(this.conf.jt.itake("global." + str18 + ":config.nfpre", "cfg"));
/*  983 */           if (cls.isEmpty(str19).booleanValue())
/*      */             continue;
/*  985 */           str16 = str16 + "select " + cls.cfnames((String)localObject3, "id") + " as un_id,";
/*  986 */           String[] arrayOfString2 = str9.split(Pattern.quote("&"));
/*  987 */           for (int i1 = 0; i1 < arrayOfString2.length; ++i1)
/*      */           {
/*  989 */             String str22 = arrayOfString2[i1];
/*  990 */             str16 = str16 + cls.cfnames((String)localObject3, str22) + " as un_" + str22 + ",";
/*      */           }
/*  992 */           str16 = str16 + cls.cfnames((String)localObject3, "time") + " as un_time, '" + str18 + "' as un_genre from " + str19;
/*  993 */           if (str7.equals("new"))
/*      */           {
/*  995 */             str16 = str16 + " where " + cls.cfnames((String)localObject3, "hidden") + "=0";
/*  996 */             str17 = " order by un_time desc";
/*      */           }
/*  998 */           else if (str7.equals("-new"))
/*      */           {
/* 1000 */             str16 = str16 + " where " + cls.cfnames((String)localObject3, "hidden") + "=0";
/* 1001 */             str17 = " order by un_time desc";
/*      */           }
/* 1003 */           else if (str7.equals("@new"))
/*      */           {
/* 1005 */             str16 = str16 + " where 1=1";
/* 1006 */             str17 = " order by un_time desc";
/*      */           }
/* 1008 */           else if (str7.equals("commendatory"))
/*      */           {
/* 1010 */             str16 = str16 + " where " + cls.cfnames((String)localObject3, "hidden") + "=0 and " + cls.cfnames((String)localObject3, "commendatory") + "=1";
/* 1011 */             str17 = " order by un_time desc";
/*      */           }
/* 1013 */           else if (str7.equals("-commendatory"))
/*      */           {
/* 1015 */             str16 = str16 + " where " + cls.cfnames((String)localObject3, "hidden") + "=0 and " + cls.cfnames((String)localObject3, "commendatory") + "=1";
/* 1016 */             str17 = " order by un_time desc";
/*      */           }
/* 1018 */           else if (str7.equals("@commendatory"))
/*      */           {
/* 1020 */             str16 = str16 + " where " + cls.cfnames((String)localObject3, "commendatory") + "=1";
/* 1021 */             str17 = " order by un_time desc";
/*      */           }
/*      */           else
/*      */           {
/* 1025 */             str16 = str16 + " where " + cls.cfnames((String)localObject3, "hidden") + "=0";
/* 1026 */             str17 = " order by un_time desc";
/*      */           }
/* 1028 */           if ((localInteger2.intValue() != -1) && (localInteger2.intValue() != -100)) str16 = str16 + " and " + cls.cfnames((String)localObject3, "lng") + "=" + localInteger2;
/* 1029 */           str16 = str16 + " union all ";
/*      */         }
/*      */ 
/* 1032 */         if (str16.indexOf(" union all ") != -1) str16 = cls.getLRStr(str16, " union all ", "leftr");
/* 1033 */         str16 = str16 + ") t1 where 1=1";
/* 1034 */         if (!(cls.isEmpty(str10).booleanValue())) str16 = str16 + str10;
/* 1035 */         if (!(cls.isEmpty(str11).booleanValue())) str17 = str11;
/* 1036 */         str16 = str16 + str17;
/* 1037 */         if ((localInteger3.intValue() >= 0) && (localInteger3.intValue() < 10)) str16 = str16 + " limit 0," + localInteger1;
/* 1038 */         if ((localInteger3.intValue() >= 10) && (localInteger3.intValue() < 20)) str16 = "select top " + localInteger1 + " *" + cls.getLRStr(str16, "select *", "rightr");
/* 1039 */         if ((localInteger3.intValue() >= 20) && (localInteger3.intValue() < 30)) str16 = str16 + " limit " + localInteger1;
/*      */ 
/* 1041 */         if (!(cls.isEmpty(str6).booleanValue())) localObject1 = str6;
/* 1044 */         else if (str5.indexOf(".") != -1) localObject1 = this.conf.jt.itake(str5, "tpl");
/*      */         else localObject1 = this.conf.jt.itake("global.tpl_transfer." + str5, "tpl");
/*      */ 
/* 1047 */         if (!(cls.isEmpty(str14).booleanValue()))
/*      */         {
/* 1049 */           localObject2 = str14.split(Pattern.quote("|"));
/* 1050 */           for (int j = 0; j < ((Object[])localObject2).length; ++j)
/*      */           {
/* 1052 */             str19 = ((String[])localObject2)[j];
/* 1053 */             if (cls.isEmpty(str19).booleanValue())
/*      */               continue;
/* 1055 */             localObject3 = str19.split(Pattern.quote("="));
/* 1056 */             if (((Object[])localObject3).length != 2) continue; localObject1 = ((String)localObject1).replace("{$" + ((Object[])localObject3)[0] + "}", ((String[])localObject3)[1]);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/* 1061 */         localObject2 = db.newInstance(this.conf);
/* 1062 */         Object[] arrayOfObject = ((dbc)localObject2).getDataAry(str16);
/* 1063 */         if (arrayOfObject != null)
/*      */         {
/* 1065 */           String str2 = "";
/* 1066 */           String str1 = cls.ctemplate((String)localObject1, "{@}");
/* 1067 */           for (int k = 0; k < arrayOfObject.length; ++k)
/*      */           {
/* 1069 */             String str3 = str1;
/* 1070 */             localObject3 = (Object[][])(Object[][])arrayOfObject[k];
/* 1071 */             for (int l = 0; l < ((Object[][])localObject3).length; ++l)
/*      */             {
/* 1073 */               ((Object[][])localObject3)[l][0] = cls.getLRStr((String)((Object[][])localObject3)[l][0], "un_", "rightr");
/* 1074 */               str3 = str3.replace("{$" + cls.toString(((Object[][])localObject3)[l][0]) + "}", encode.htmlencode(cls.toString(((Object[][])localObject3)[l][1]), "1"));
/*      */             }
/* 1076 */             this.conf.rstAry = ((Object[][])localObject3);
/* 1077 */             String str20 = cls.toString(((dbc)localObject2).getValue((Object[][])localObject3, "genre"));
/* 1078 */             String str21 = "";
/* 1079 */             if (!(str20.equals(str15)))
/*      */             {
/* 1081 */               str21 = this.conf.getActualRoute(str20);
/* 1082 */               if (!(cls.getRight(str21, Integer.valueOf(1)).equals("/"))) str21 = str21 + "/";
/*      */             }
/* 1084 */             str3 = str3.replace("{$-i}", cls.toString(Integer.valueOf(k)));
/* 1085 */             str3 = str3.replace("{$-genre}", encode.htmlencode(str20));
/* 1086 */             str3 = str3.replace("{$-baseurl}", str21);
/*      */ 
/* 1088 */             for (int i2 = 2; i2 < 7; ++i2)
/*      */             {
/* 1090 */               int i3 = k % i2 + 1;
/* 1091 */               str3 = str3.replace("{$-!mod" + i2 + "}", cls.toString(Integer.valueOf(i3)));
/* 1092 */               if (i3 != i2) str3 = str3.replace("{$-!mod" + i2 + "-string}", "");
/*      */               else str3 = str3.replace("{$-!mod" + i2 + "-string}", cls.toString(str12));
/*      */             }
/*      */ 
/* 1096 */             str3 = this.conf.jt.creplace(str3);
/* 1097 */             str2 = str2 + str3;
/*      */           }
/* 1099 */           localObject1 = cls.ctemplates((String)localObject1, "{@}", str2);
/*      */         } else {
/* 1101 */           localObject1 = ""; }
/* 1102 */         localObject1 = this.conf.jt.creplace((String)localObject1);
/*      */       }
/*      */     }
/* 1105 */     return ((String)(String)(String)localObject1);
/*      */   }
/*      */ 
/*      */   public String isort(String paramString)
/*      */   {
/* 1110 */     String str1 = "";
/*      */ 
/* 1112 */     String str5 = paramString;
/* 1113 */     String str6 = cls.getParameter(str5, "tpl");
/* 1114 */     Object localObject1 = cls.getParameter(str5, "genre");
/* 1115 */     String str7 = cls.getParameter(str5, "lng");
/* 1116 */     String str8 = cls.getParameter(str5, "fid");
/* 1117 */     String str9 = cls.getParameter(str5, "vars");
/* 1118 */     String str10 = cls.getParameter(str5, "valids");
/* 1119 */     String str11 = "";
/* 1120 */     String str12 = this.conf.getNGenre();
/* 1121 */     if (cls.isEmpty(localObject1).booleanValue()) localObject1 = str12;
/* 1122 */     if (cls.isEmpty(str7).booleanValue()) str7 = this.conf.getNLng();
/* 1123 */     if (str10.equals("-1")) str10 = "";
/* 1124 */     if (!(((String)localObject1).equals(str12)))
/*      */     {
/* 1126 */       str11 = this.conf.getActualRoute((String)localObject1);
/* 1127 */       if (cls.getRight(str11, Integer.valueOf(1)) != "/") str11 = str11 + "/";
/*      */     }
/* 1129 */     if (str6.indexOf(".") != -1) str1 = this.conf.jt.itake(str6, "tpl");
/*      */     else str1 = this.conf.jt.itake("global.tpl_transfer." + str6, "tpl");
/* 1131 */     if (!(cls.isEmpty(str9).booleanValue()))
/*      */     {
/* 1133 */       Object localObject2 = str9.split(Pattern.quote("|"));
/* 1134 */       for (int i = 0; i < ((Object[])localObject2).length; ++i)
/*      */       {
/* 1136 */         Object localObject3 = ((Object[])localObject2)[i];
/* 1137 */         if (cls.isEmpty(localObject3).booleanValue())
/*      */           continue;
/* 1139 */         String[] arrayOfString1 = ((String)localObject3).split(Pattern.quote("="));
/* 1140 */         if (arrayOfString1.length != 2) continue; str1 = str1.replace("{$" + arrayOfString1[0] + "}", arrayOfString1[1]);
/*      */       }
/*      */     }
/*      */ 
/* 1144 */     String str3 = "";
/* 1145 */     String str2 = cls.ctemplate(str1, "{@}");
/* 1146 */     Object localObject2 = new category(this.conf);
/* 1147 */     String[][] arrayOfString = ((category)localObject2).getCatAry((String)localObject1, cls.getNum(str7, Integer.valueOf(0)));
/* 1148 */     if (arrayOfString != null)
/*      */     {
/* 1150 */       for (int j = 0; j < arrayOfString.length; ++j)
/*      */       {
/* 1152 */         if ((!(cls.getNum(arrayOfString[j][2], Integer.valueOf(0)).equals(cls.getNum(str8, Integer.valueOf(0))))) || (
/* 1154 */           (!(cls.isEmpty(str10).booleanValue())) && (!(cls.cinstr(str10, cls.toString(cls.getNum(arrayOfString[j][0], Integer.valueOf(0))), ",").booleanValue()))))
/*      */           continue;
/* 1156 */         String str4 = str2;
/* 1157 */         str4 = str4.replace("{$topic}", encode.htmlencode(arrayOfString[j][1]));
/* 1158 */         str4 = str4.replace("{$id}", encode.htmlencode(arrayOfString[j][0]));
/* 1159 */         str4 = str4.replace("{$-genre}", (CharSequence)localObject1);
/* 1160 */         str4 = str4.replace("{$-baseurl}", str11);
/* 1161 */         str3 = str3 + str4;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1166 */     str1 = cls.ctemplates(str1, "{@}", str3);
/* 1167 */     str1 = this.conf.jt.creplace(str1);
/* 1168 */     return ((String)(String)str1);
/*      */   }
/*      */ 
/*      */   public String inavigation(String paramString)
/*      */   {
/* 1173 */     Object localObject1 = "";
/* 1174 */     String str1 = paramString;
/* 1175 */     Object localObject2 = cls.getParameter(str1, "genre");
/* 1176 */     String str2 = cls.getParameter(str1, "lng");
/* 1177 */     String str3 = cls.getParameter(str1, "class");
/* 1178 */     String str4 = cls.getParameter(str1, "genrelink");
/* 1179 */     String str5 = "";
/* 1180 */     String str6 = this.conf.getNGenre();
/* 1181 */     if (cls.isEmpty(localObject2).booleanValue()) localObject2 = str6;
/* 1182 */     if (cls.isEmpty(str2).booleanValue()) str2 = this.conf.getNLng();
/* 1183 */     if (!(((String)localObject2).equals(str6)))
/*      */     {
/* 1185 */       str5 = this.conf.getActualRoute((String)localObject2);
/* 1186 */       if (!(cls.getRight(str5, Integer.valueOf(1)).equals("/"))) str5 = str5 + "/";
/*      */     }
/* 1188 */     String str7 = this.conf.jt.itake("global.tpl_config.a_href_self", "tpl");
/* 1189 */     String str8 = this.conf.jt.itake("global.default.channel_title", "lng");
/* 1190 */     localObject1 = str7;
/* 1191 */     localObject1 = ((String)localObject1).replace("{$explain}", str8);
/* 1192 */     localObject1 = ((String)localObject1).replace("{$value}", this.conf.getActualRoute("./"));
			
/* 1193 */     if (localObject2 != "&hidden")
/*      */     {
/* 1195 */       String str9 = this.conf.jt.itake("global." + ((String)localObject2) + ":default.channel_title", "lng");
/* 1196 */       if (!(str4.equals("&hidden")))
/*      */       {
/* 1198 */         localObject1 = ((String)localObject1) + this.conf.navSpStr + str7;
/* 1199 */         if (cls.isEmpty(str4).booleanValue()) str4 = this.conf.getActualRoute((String)localObject2);
/* 1200 */         localObject1 = ((String)localObject1).replace("{$explain}", str9);
/* 1201 */         localObject1 = ((String)localObject1).replace("{$value}", str4);
/*      */       } else {
/* 1203 */         localObject1 = ((String)localObject1) + this.conf.navSpStr + str9; }
/*      */     }
/* 1205 */     String str9 = "<!--fixed-->{@}" + this.conf.navSpStr + str7 + "{@}<!--fixed-->";
/* 1206 */     str9 = str9.replace("{$explain}", "{$topic}");
/* 1207 */     str9 = str9.replace("{$value}", curl(str5, iurl("genre=" + ((String)localObject2) + ";type=list;key={$id}")));
/* 1208 */     if (!(cls.isEmpty(str3).booleanValue()))
/*      */     {
/* 1210 */       category localcategory = new category(this.conf);
/* 1211 */       localObject1 = ((String)localObject1) + localcategory.getFaCatHtml(str9, (String)localObject2, cls.getNum(str2, Integer.valueOf(0)), cls.getNum(str3, Integer.valueOf(0)));
/*      */     }
/* 1213 */     return ((String)(String)localObject1);
/*      */   }
/*      */ 
public String iurl(String paramString)
{
  String str1 = "";
  String str2 = "";
  String str3 = paramString;
  String str4 = cls.getParameter(str3, "type");
  String str5 = cls.getParameter(str3, "genre");
  String str6 = cls.getParameter(str3, "key");
  String str7 = cls.getParameter(str3, "time");
  String str8 = cls.getParameter(str3, "page");
  String str9 = cls.getParameter(str3, "ctpage");
  str2 = cls.getSafeString(str6);
  if (cls.isEmpty(str2).booleanValue()) str2 = "0";
  str8 = cls.getSafeString(str8);
  if (cls.isEmpty(str8).booleanValue()) str8 = "0";
  str9 = cls.getSafeString(str9);
  if (cls.isEmpty(str9).booleanValue()) str9 = "0";
  if (cls.isEmpty(str5).booleanValue()) str5 = this.conf.getNGenre();
  Integer localInteger = cls.getNum(this.conf.jt.itake("global." + str5 + ":config.nurltype", "cfg"), Integer.valueOf(0));
  String str10 = this.conf.jt.itake("global." + str5 + ":config.ncreatefolder", "cfg");
  String str11 = this.conf.jt.itake("global." + str5 + ":config.ncreatefiletype", "cfg");
  label1480: switch (localInteger.intValue())
  {
  case 0:
    if (str4.equals("list")) { str1 = "?type=list&amp;class=" + str2; break label1480;  }
    if (str4.equals("detail")) { str1 = "?type=detail&amp;id=" + str2; break label1480; }
    if (str4.equals("page")) { str1 = "?" + encode.htmlencode(cls.reparameter(this.conf.getNURS(), "page", str8)); break label1480; }
    if (!(str4.equals("ctpage"))) break label1480; str1 = "?" + encode.htmlencode(cls.reparameter(this.conf.getNURS(), "ctpage", str9)); break;
  case 1:
    if (str4.equals("list")) { str1 = str10 + "/list/" + str2 + "/1" + str11; break label1480; }
    if (str4.equals("detail")) { str1 = str10 + "/detail/" + cls.formatDate(str7, Integer.valueOf(5)) + "/" + str2 + str11; break label1480; }
    if (str4.equals("page")) { str1 = str10 + "/list/" + str2 + "/" + str8 + str11; break label1480; }
    if (!(str4.equals("ctpage"))) break label1480; str1 = str10 + "/detail/" + cls.formatDate(str7, Integer.valueOf(5)) + "/" + str2 + "_" + str9 + str11; break;
  case 2:
    if (str4.equals("list")) { str1 = "list-" + str2 + "-1.jsp"; break label1480; }
    if (str4.equals("detail")) { str1 = "detail-" + str2 + ".jsp"; break label1480; }
    if (str4.equals("page")) { str1 = "list-" + str2 + "-" + str8 + ".jsp"; break label1480; }
    if (!(str4.equals("ctpage"))) break label1480; str1 = "detail-" + str2 + "-" + str9 + ".jsp"; break;
  case 3:
    if (str4.equals("list")) { str1 = "list-" + str2 + "-1.htm"; break label1480; }
    if (str4.equals("detail")) { str1 = "detail-" + str2 + ".htm"; break label1480; }
    if (str4.equals("page")) { str1 = "list-" + str2 + "-" + str8 + ".htm"; break label1480; }
    if (!(str4.equals("ctpage"))) break label1480; str1 = "detail-" + str2 + "-" + str9 + ".htm"; break;
  case 4:
    if (str4.equals("list")) { str1 = "list-" + str2 + "-1.xhtml"; break label1480; }
    if (str4.equals("detail")) { str1 = "detail-" + str2 + ".xhtml"; break label1480; }
    if (str4.equals("page")) { str1 = "list-" + str2 + "-" + str8 + ".xhtml"; break label1480; }
    if (!(str4.equals("ctpage"))) break label1480; str1 = "detail-" + str2 + "-" + str9 + ".xhtml"; break;
  case 5:
    if (str4.equals("list")) { str1 = "list-" + str2 + "-1.html"; break label1480; }
    if (str4.equals("detail")) { str1 = "detail-" + str2 + ".html"; break label1480; }
    if (str4.equals("page")) { str1 = "list-" + str2 + "-" + str8 + ".html"; break label1480; }
    if (!(str4.equals("ctpage"))) break label1480; str1 = "detail-" + str2 + "-" + str9 + ".html";
  }

  return str1;
}
/*      */ 
/*      */   public String loadEditor(String paramString1, String paramString2)
/*      */   {
/* 1281 */     String str1 = "";
/* 1282 */     String str2 = paramString1;
/* 1283 */     String str3 = paramString2;
/* 1284 */     str1 = loadEditor(str2, str3, "1");
/* 1285 */     return str1;
/*      */   }
/*      */ 
/*      */   public String loadEditor(String paramString1, String paramString2, String paramString3)
/*      */   {
/* 1290 */     String str1 = "";
/* 1291 */     String str2 = paramString1;
/* 1292 */     String str3 = paramString2;
/* 1293 */     String str4 = paramString3;
/* 1294 */     str1 = loadEditor(str2, str3, str4, "300");
/* 1295 */     return str1;
/*      */   }
/*      */ 
/*      */   public String loadEditor(String paramString1, String paramString2, String paramString3, String paramString4)
/*      */   {
/* 1300 */     String str1 = "";
/* 1301 */     String str2 = paramString1;
/* 1302 */     String str3 = paramString2;
/* 1303 */     String str4 = paramString3;
/* 1304 */     String str5 = paramString4;
/* 1305 */     if (cls.getNum(str4, Integer.valueOf(-1)).intValue() != -1) str4 = "Style" + str4;
/* 1306 */     if (str2.substring(0, 1).equals("~")) str2 = cls.getLRStr(str2, "~", "rightr");
/*      */     else str1 = str1 + this.conf.jt.itake("global.tpl_common.editor_script", "tpl");
/* 1308 */     str1 = str1 + this.conf.jt.itake("global.tpl_common.editor_content", "tpl");
/* 1309 */     str1 = str1.replace("{$name}", encode.htmlencode(str2));
/* 1310 */     str1 = str1.replace("{$value}", encode.htmlencode(str3));
/* 1311 */     str1 = str1.replace("{$-style}", encode.htmlencode(str4));
/* 1312 */     str1 = str1.replace("{$-height}", encode.htmlencode(str5));
/* 1313 */     str1 = this.conf.jt.creplace(str1);
/* 1314 */     return str1;
/*      */   }
/*      */ 
/*      */   public String pagi(String paramString1, String paramString2, String paramString3, String paramString4)
/*      */   {
/* 1319 */     String str1 = "";
/* 1320 */     String str2 = paramString1;
/* 1321 */     String str3 = paramString2;
/* 1322 */     String str4 = paramString3;
/* 1323 */     String str5 = paramString4;
/* 1324 */     str1 = pagi(str2, str3, str4, str5, "");
/* 1325 */     return str1;
/*      */   }
/*      */ 
/*      */   public String pagi(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
/*      */   {
/* 1330 */     String str1 = "";
/*      */ 
/* 1332 */     Integer localInteger1 = Integer.valueOf(10);
/* 1333 */     Integer localInteger2 = localInteger1;
/* 1334 */     Object localObject = cls.getNum(paramString1, Integer.valueOf(0));
/* 1335 */     Integer localInteger3 = cls.getNum(paramString2, Integer.valueOf(0));
/* 1336 */     String str5 = cls.getString(paramString3);
/* 1337 */     String str6 = cls.getString(paramString4);
/* 1338 */     String str7 = cls.getString(paramString5);
/* 1339 */     if (cls.isEmpty(str7).booleanValue()) str7 = "pagi-1";
/* 1340 */     Integer localInteger4 = Integer.valueOf(0);
/* 1341 */     if (str6.equals("ct-cutepage")) localInteger4 = Integer.valueOf(1);
/* 1342 */     if (localInteger3.intValue() > localInteger4.intValue())
/*      */     {
/* 1344 */       str1 = this.conf.jt.itake("global.tpl_common." + str7, "tpl");
/* 1345 */       String str3 = "";
/* 1346 */       String str2 = cls.ctemplate(str1, "{@}");
/* 1347 */       if (((Integer)localObject).intValue() < 1) localObject = Integer.valueOf(1);
/* 1348 */       if (((Integer)localObject).intValue() > localInteger3.intValue()) localObject = localInteger3;
/* 1349 */       Integer localInteger5 = cls.getNum(cls.getLRStr(cls.toString(Double.valueOf(((Integer)localObject).intValue() - Math.floor(localInteger1.intValue() / 2))), ".", "left"), Integer.valueOf(0));
/* 1350 */       if (localInteger5.intValue() < 1) localInteger5 = Integer.valueOf(1);
/* 1351 */       Integer localInteger6 = Integer.valueOf(localInteger5.intValue() + localInteger2.intValue() - 1);
/* 1352 */       if (localInteger6.intValue() > localInteger3.intValue()) localInteger6 = localInteger3;
/* 1353 */       if (localInteger5.intValue() <= localInteger6.intValue())
/*      */       {
/* 1355 */         if (localInteger6.intValue() - localInteger5.intValue() < localInteger2.intValue() - 1)
/*      */         {
/* 1357 */           localInteger5 = Integer.valueOf(localInteger5.intValue() - (localInteger2.intValue() - 1 - (localInteger6.intValue() - localInteger5.intValue())));
/* 1358 */           if (localInteger5.intValue() < 1) localInteger5 = Integer.valueOf(1);
/*      */         }
/* 1360 */         for (int i = localInteger5.intValue(); i <= localInteger6.intValue(); ++i)
/*      */         {
/* 1362 */           String str4 = str2;
/* 1363 */           str4 = str4.replace("{$-num}", cls.toString(Integer.valueOf(i)));
/* 1364 */           str4 = str4.replace("{$-link}", str5.replace("[~page]", cls.toString(Integer.valueOf(i))));
/* 1365 */           if (i != ((Integer)localObject).intValue()) str4 = str4.replace("{$-class}", "");
/*      */           else str4 = str4.replace("{$-class}", "selected");
/* 1367 */           str3 = str3 + str4;
/*      */         }
/*      */       }
/* 1370 */       str1 = cls.ctemplates(str1, "{@}", str3);
/* 1371 */       str1 = str1.replace("{$-page1}", cls.toString(localObject));
/* 1372 */       str1 = str1.replace("{$-page2}", cls.toString(localInteger3));
/* 1373 */       str1 = str1.replace("{$-firstpagelink}", str5.replace("[~page]", "1"));
/* 1374 */       str1 = str1.replace("{$-lastpagelink}", str5.replace("[~page]", cls.toString(localInteger3)));
/* 1375 */       str1 = str1.replace("{$-tid}", encode.htmlencode(str6));
/* 1376 */       str1 = str1.replace("{$-value1}", cls.toString(Integer.valueOf((((Integer)localObject).equals(localInteger6)) ? ((Integer)localObject).intValue() : ((Integer)localObject).intValue() + 1)));
/* 1377 */       str1 = str1.replace("{$-baselink}", encode.scriptencode(encode.htmlencode(str5)));
/* 1378 */       str1 = this.conf.jt.creplace(str1);
/*      */     }
/* 1380 */     return ((String)str1);
/*      */   }
/*      */ 
/*      */   public String repathencode(String paramString)
/*      */   {
/* 1385 */     String str1 = paramString;
/* 1386 */     if ((!(cls.isEmpty(str1).booleanValue())) && (this.conf.repath.equals("1")))
/*      */     {
/* 1388 */       String str2 = this.conf.getNGenre();
/* 1389 */       String str3 = this.conf.getNURLPre() + this.conf.getNURL();
/* 1390 */       String str4 = str2 + "/";
/* 1391 */       String str5 = cls.getLRStr(str3, str2, "leftr");
/* 1392 */       str1 = str1.replace(str5, "{$->>repath}");
/*      */     }
/* 1394 */     return str1;
/*      */   }
/*      */ 
/*      */   public String repathdecode(String paramString)
/*      */   {
/* 1399 */     String str1 = paramString;
/* 1400 */     if (!(cls.isEmpty(str1).booleanValue()))
/*      */     {
/* 1402 */       String str2 = this.conf.getNGenre();
/* 1403 */       String str3 = this.conf.getNURLPre() + this.conf.getNURL();
/* 1404 */       String str4 = str2 + "/";
/* 1405 */       String str5 = cls.getLRStr(str3, str2, "leftr");
/* 1406 */       str1 = str1.replace("{$->>repath}", str5);
/*      */     }
/* 1408 */     return str1;
/*      */   }
/*      */ 
/*      */   public String selClass(String paramString)
/*      */   {
/* 1413 */     String str1 = "";
/* 1414 */     String str2 = paramString;
/* 1415 */     str1 = selClass(str2, "-1");
/* 1416 */     return str1;
/*      */   }
/*      */ 
/*      */   public String selClass(String paramString1, String paramString2)
/*      */   {
/* 1421 */     String str1 = "";
/*      */ 
/* 1423 */     String str5 = paramString1;
/* 1424 */     String str6 = paramString2;
/* 1425 */     String str7 = cls.getParameter(str5, "genre");
/* 1426 */     String str8 = cls.getParameter(str5, "lng");
/* 1427 */     String str9 = cls.getParameter(str5, "fid");
/* 1428 */     Integer localInteger1 = cls.getNum(cls.getParameter(str5, "class"), Integer.valueOf(0));
/* 1429 */     Integer localInteger2 = cls.getNum(cls.getParameter(str5, "inum"), Integer.valueOf(0));
/* 1430 */     str1 = this.conf.jt.itake("global.tpl_common.sel-class", "tpl");
/* 1431 */     String str3 = "";
/* 1432 */     String str2 = cls.ctemplate(str1, "{@}");
/* 1433 */     category localcategory = new category(this.conf);
/* 1434 */     String[][] arrayOfString = localcategory.getCatAry(str7, cls.getNum(str8, Integer.valueOf(0)));
/* 1435 */     if (arrayOfString != null)
/*      */     {
/* 1437 */       localInteger2 = Integer.valueOf(localInteger2.intValue() + 1);
/* 1438 */       for (int i = 0; i < arrayOfString.length; ++i)
/*      */       {
/* 1440 */         if ((!(cls.getNum(arrayOfString[i][2], Integer.valueOf(0)).equals(cls.getNum(str9, Integer.valueOf(0))))) || (
/* 1442 */           (!(str6.equals("-1"))) && (!(cls.cinstr(str6, arrayOfString[i][0], ",").booleanValue()))))
/*      */           continue;
/* 1444 */         String str4 = str2;
/* 1445 */         str4 = str4.replace("{$topic}", encode.htmlencode(arrayOfString[i][1]));
/* 1446 */         str4 = str4.replace("{$id}", encode.htmlencode(arrayOfString[i][0]));
/* 1447 */         if (localInteger1 != cls.getNum(arrayOfString[i][0], Integer.valueOf(0))) str4 = str4.replace("{$-selected}", "");
/*      */         else str4 = str4.replace("{$-selected}", "selected=\"selected\"");
/* 1449 */         str4 = str4.replace("{$-prestr}", cls.getRepeatedString(this.conf.jt.itake("global.lng_common.sys-spsort", "lng"), localInteger2));
/* 1450 */         str4 = str4.replace("{$-child}", selClass("genre=" + str7 + ";lng=" + str8 + ";class=" + localInteger1 + ";inum=" + localInteger2 + ";fid=" + cls.getNum(arrayOfString[i][0], Integer.valueOf(0)), str6));
/* 1451 */         str3 = str3 + str4;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1456 */     str1 = cls.ctemplates(str1, "{@}", str3);
/* 1457 */     str1 = this.conf.jt.creplace(str1);
/* 1458 */     return str1;
/*      */   }
/*      */ 
/*      */   public Boolean sendMail(String paramString1, String paramString2, String paramString3)
/*      */   {
/* 1463 */     Boolean localBoolean = Boolean.valueOf(false);
/* 1464 */     String str1 = paramString1;
/* 1465 */     String str2 = paramString2;
/* 1466 */     String str3 = paramString3;
/* 1467 */     String str4 = this.conf.application.getInitParameter("mail_smtpusername");
/* 1468 */     if (cls.isEmpty(str4).booleanValue()) str4 = this.conf.jt.itake("global.config.mail-smtpusername", "cfg");
/* 1469 */     String str5 = this.conf.application.getInitParameter("mail_smtppassword");
/* 1470 */     if (cls.isEmpty(str5).booleanValue()) str5 = this.conf.jt.itake("global.config.mail-smtppassword", "cfg");
/* 1471 */     String str6 = this.conf.application.getInitParameter("mail_smtpfrom");
/* 1472 */     if (cls.isEmpty(str6).booleanValue()) str6 = this.conf.jt.itake("global.config.mail-smtpfrom", "cfg");
/* 1473 */     if (cls.isEmpty(str6).booleanValue()) str6 = str4;
/* 1474 */     String str7 = this.conf.application.getInitParameter("mail_smtpcharset");
/* 1475 */     if (cls.isEmpty(str7).booleanValue()) str7 = this.conf.jt.itake("global.config.mail-smtpcharset", "cfg");
/* 1476 */     String str8 = this.conf.application.getInitParameter("mail_smtpserver");
/* 1477 */     if (cls.isEmpty(str8).booleanValue()) str8 = this.conf.jt.itake("global.config.mail-smtpserver", "cfg");
/*      */     try
/*      */     {
/* 1480 */       String str9 = "";
/* 1481 */       Socket localSocket = new Socket(str8, 25);
/* 1482 */       BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localSocket.getInputStream()));
/* 1483 */       BufferedWriter localBufferedWriter = new BufferedWriter(new OutputStreamWriter(localSocket.getOutputStream()));
/* 1484 */       str9 = localBufferedReader.readLine();
/* 1485 */       if ((str9.substring(0, 3).equals("220")) || (str9.substring(0, 3).equals("250")))
/*      */       {
/* 1487 */         localBufferedWriter.write("HELO JTBC\n");
/* 1488 */         localBufferedWriter.flush();
/* 1489 */         str9 = localBufferedReader.readLine();
/* 1490 */         if (str9.substring(0, 3).equals("250"))
/*      */         {
/* 1492 */           localBufferedWriter.write("AUTH LOGIN\n");
/* 1493 */           localBufferedWriter.flush();
/* 1494 */           str9 = localBufferedReader.readLine();
/* 1495 */           if (str9.substring(0, 3).equals("334"))
/*      */           {
/* 1497 */             localBufferedWriter.write(encode.base64encode(str4.getBytes()) + "\n");
/* 1498 */             localBufferedWriter.flush();
/* 1499 */             str9 = localBufferedReader.readLine();
/* 1500 */             if (str9.substring(0, 3).equals("334"))
/*      */             {
/* 1502 */               localBufferedWriter.write(encode.base64encode(str5.getBytes()) + "\n");
/* 1503 */               localBufferedWriter.flush();
/* 1504 */               str9 = localBufferedReader.readLine();
/* 1505 */               if (str9.substring(0, 3).equals("235"))
/*      */               {
/* 1507 */                 localBufferedWriter.write("MAIL FROM: <" + str6 + ">\n");
/* 1508 */                 localBufferedWriter.flush();
/* 1509 */                 str9 = localBufferedReader.readLine();
/* 1510 */                 if (str9.substring(0, 3).equals("250"))
/*      */                 {
/* 1512 */                   localBufferedWriter.write("RCPT TO: <" + str1 + ">\n");
/* 1513 */                   localBufferedWriter.flush();
/* 1514 */                   str9 = localBufferedReader.readLine();
/* 1515 */                   if (str9.substring(0, 3).equals("250"))
/*      */                   {
/* 1517 */                     localBufferedWriter.write("DATA\n");
/* 1518 */                     localBufferedWriter.flush();
/* 1519 */                     str9 = localBufferedReader.readLine();
/* 1520 */                     if (str9.substring(0, 3).equals("354"))
/*      */                     {
/* 1522 */                       localBufferedWriter.write("MIME-Version: 1.0\nContent-type: text/html; charset=" + str7 + "\nTo: <" + str1 + ">\nFrom: <" + str6 + ">\nSubject: " + str2 + "\n\n" + str3 + "\n.\n");
/* 1523 */                       localBufferedWriter.flush();
/* 1524 */                       localBufferedWriter.write("QUIT\n");
/* 1525 */                       localBufferedWriter.flush();
/* 1526 */                       localBoolean = Boolean.valueOf(true);
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
/* 1537 */     return localBoolean;
/*      */   }
/*      */ 
/*      */   public String webBase(String paramString)
/*      */   {
/* 1542 */     String str1 = "";
/* 1543 */     String str2 = paramString;
/* 1544 */     Integer localInteger = cls.getNum(this.conf.jt.itake("global.config.nbasehref", "cfg"), Integer.valueOf(0));
/* 1545 */     if (!(cls.isEmpty(str2).booleanValue())) localInteger = cls.getNum(this.conf.jt.itake("global." + str2 + ":config.nbasehref", "cfg"), Integer.valueOf(0));
/* 1546 */     if (localInteger.intValue() == 1)
/*      */     {
/* 1548 */       str1 = this.conf.jt.itake("global.tpl_public.base", "tpl");
/* 1549 */       str1 = str1.replace("{$-base}", cls.getLRStr(new StringBuilder().append(this.conf.getNURLPre()).append(this.conf.getNURI()).toString(), "/", "leftr") + "/");
/* 1550 */       str1 = this.conf.jt.creplace(str1);
/*      */     }
/* 1552 */     return str1;
/*      */   }
/*      */ 
/*      */   public String webHead(String paramString)
/*      */   {
/* 1557 */     String str1 = "";
/* 1558 */     String str2 = paramString;
/* 1559 */     str1 = this.conf.jt.itake("global.tpl_public." + str2, "tpl");
/* 1560 */     str1 = this.conf.jt.creplace(str1);
/* 1561 */     return str1;
/*      */   }
/*      */ 
/*      */   public String webFoot(String paramString)
/*      */   {
/* 1566 */     String str1 = "";
/* 1567 */     String str2 = paramString;
/* 1568 */     str1 = this.conf.jt.itake("global.tpl_public." + str2, "tpl");
/* 1569 */     str1 = this.conf.jt.creplace(str1);
/* 1570 */     return str1;
/*      */   }
/*      */ 
/*      */   public String webMessage(String paramString)
/*      */   {
/* 1575 */     String str1 = paramString;
/* 1576 */     String str2 = this.conf.jt.itake("global.tpl_common.wfront-message", "tpl");
/* 1577 */     str2 = str2.replace("{$message}", encode.htmlencode(str1));
/* 1578 */     str2 = this.conf.jt.creplace(str2);
/* 1579 */     return str2;
/*      */   }
/*      */ 
/*      */   public String webMessages(String paramString1, String paramString2)
/*      */   {
/* 1584 */     String str1 = paramString1;
/* 1585 */     String str2 = paramString2;
/* 1586 */     String str3 = this.conf.jt.itake("global.tpl_common.wfront-messages", "tpl");
/* 1587 */     str3 = str3.replace("{$message}", encode.htmlencode(str1));
/* 1588 */     str3 = str3.replace("{$backurl}", encode.htmlencode(str2));
/* 1589 */     str3 = this.conf.jt.creplace(str3);
/* 1590 */     return str3;
/*      */   }
/*      */ 
/*      */   public String xmlSelect(String paramString1, String paramString2, String paramString3)
/*      */   {
/* 1595 */     String str1 = "";
/* 1596 */     String str2 = paramString1;
/* 1597 */     String str3 = paramString2;
/* 1598 */     String str4 = paramString3;
/* 1599 */     str1 = xmlSelect(str2, str3, str4, "");
/* 1600 */     return str1;
/*      */   }
/*      */ 
/*      */   public String xmlSelect(String paramString1, String paramString2, String paramString3, String paramString4)
/*      */   {
/* 1605 */     String str1 = paramString1;
/* 1606 */     String str2 = paramString2;
/* 1607 */     String str3 = paramString3;
/* 1608 */     String str4 = paramString4;
/* 1609 */     String str5 = "";
/* 1610 */     String str6 = this.conf.jt.itake("global.tpl_config.xmlselect_" + str3, "tpl");
/* 1611 */     String str7 = this.conf.jt.itake("global.tpl_config.xmlselect_un" + str3, "tpl");
/* 1612 */     if ((!(cls.isEmpty(str6).booleanValue())) && (!(cls.isEmpty(str7).booleanValue())))
/*      */     {
/* 1614 */       String[][] arrayOfString = this.conf.jt.itakes(str1, "sel");
/* 1615 */       for (int i = 0; i < arrayOfString.length; ++i)
/*      */       {
/* 1617 */         if (cls.cinstr(str2, arrayOfString[i][0], ",").booleanValue()) str5 = str5 + str6;
/*      */         else str5 = str5 + str7;
/* 1619 */         str5 = str5.replace("{$value}", encode.htmlencode(arrayOfString[i][0]));
/* 1620 */         str5 = str5.replace("{$explain}", encode.htmlencode(arrayOfString[i][1]));
/*      */       }
/* 1622 */       if (!(cls.isEmpty(str4).booleanValue())) str5 = str5.replace("{$name}", encode.htmlencode(str4));
/* 1623 */       str5 = this.conf.jt.creplace(str5);
/*      */     }
/* 1625 */     return str5;
/*      */   }
/*      */ 
/*      */   public common(conf paramconf)
/*      */   {
/* 1630 */     this.conf = paramconf;
/*      */   }
/*      */ }