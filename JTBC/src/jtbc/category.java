/*     */ package jtbc;
/*     */ 
/*     */ import java.util.regex.Pattern;
/*     */ import javax.servlet.ServletContext;
/*     */ import jtbc.dbc.dbc;
/*     */ 
/*     */ public class category
/*     */ {
/*     */   public conf conf;
/*     */ 
/*     */   public void removeCacheData(String paramString, Integer paramInteger)
/*     */   {
/*  12 */     Integer localInteger = paramInteger;
/*  13 */     String str1 = paramString;
/*  14 */     String str2 = "sys_category_" + encode.cachenameencode(str1) + "_" + this.conf.common.getLngText(cls.toString(localInteger));
/*  15 */     this.conf.application.removeAttribute(this.conf.getAppKey(str2));
/*     */   }
/*     */ 
/*     */   public String[][] getCatAry(String paramString, Integer paramInteger)
/*     */   {
/*  20 */     Integer localInteger = paramInteger;
/*  21 */     String str1 = paramString;
/*  22 */     String str2 = "sys_category_" + encode.cachenameencode(str1) + "_" + this.conf.common.getLngText(cls.toString(localInteger));
/*  23 */     String[][] arrayOfString = (String[][])(String[][])this.conf.application.getAttribute(this.conf.getAppKey(str2));
/*  24 */     if (arrayOfString == null)
/*     */     {
/*  26 */       arrayOfString = getCatDbAry(str1, localInteger, Integer.valueOf(0));
/*  27 */       if (this.conf.isApp.equals("1")) this.conf.application.setAttribute(this.conf.getAppKey(str2), arrayOfString);
/*     */     }
/*  29 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */   public String[][] getCatDbAry(String paramString, Integer paramInteger1, Integer paramInteger2)
/*     */   {
/*  34 */     Integer localInteger1 = paramInteger1;
/*  35 */     Integer localInteger2 = paramInteger2;
/*  36 */     String str1 = paramString;
/*  37 */     dbc localdbc = db.newInstance(this.conf);
/*  38 */     String[][] arrayOfString1 = (String[][])null;
/*  39 */     String str2 = cls.getString(this.conf.jt.itake("global.config.sys->category-ndatabase", "cfg"));
/*  40 */     String str3 = cls.getString(this.conf.jt.itake("global.config.sys->category-nfpre", "cfg"));
/*  41 */     String str4 = cls.cfnames(str3, "id");
/*  42 */     String str5 = "select * from " + str2 + " where " + cls.cfnames(str3, "hidden") + "=0 and " + cls.cfnames(str3, "lng") + "=" + localInteger1 + " and " + cls.cfnames(str3, "genre") + "='" + str1 + "' and " + cls.cfnames(str3, "fid") + "=" + localInteger2 + " order by " + cls.cfnames(str3, "order") + " asc";
/*  43 */     Object[] arrayOfObject = localdbc.getDataAry(str5);
/*  44 */     if (arrayOfObject != null)
/*     */     {
/*  46 */       for (int i = 0; i < arrayOfObject.length; ++i)
/*     */       {
/*  48 */         Object[][] arrayOfObject1 = (Object[][])(Object[][])arrayOfObject[i];
/*  49 */         Integer localInteger3 = (Integer)localdbc.getValue(arrayOfObject1, str4);
/*  50 */         String[][] arrayOfString2 = new String[1][3];
/*  51 */         arrayOfString2[0][0] = cls.toString(localdbc.getValue(arrayOfObject1, str4));
/*  52 */         arrayOfString2[0][1] = cls.toString(localdbc.getValue(arrayOfObject1, cls.cfnames(str3, "topic")));
/*  53 */         arrayOfString2[0][2] = cls.toString(localdbc.getValue(arrayOfObject1, cls.cfnames(str3, "fid")));
/*  54 */         arrayOfString1 = cls.mergeAry(arrayOfString1, arrayOfString2);
/*  55 */         arrayOfString1 = cls.mergeAry(arrayOfString1, getCatDbAry(str1, localInteger1, localInteger3));
/*     */       }
/*     */     }
/*  58 */     return arrayOfString1;
/*     */   }
/*     */ 
/*     */   public String getFaCatHtml(String paramString1, String paramString2, Integer paramInteger1, Integer paramInteger2)
/*     */   {
/*  63 */     String str1 = paramString1;
/*  64 */     String str2 = paramString2;
/*  65 */     Integer localInteger1 = paramInteger1;
/*  66 */     Integer localInteger2 = paramInteger2;
/*  67 */     Integer localInteger3 = Integer.valueOf(0);
/*  68 */     String str3 = str1;
/*     */ 
/*  70 */     String str5 = "";
/*  71 */     String str4 = cls.ctemplate(str3, "{@}");
/*  72 */     String[][] arrayOfString1 = (String[][])null;
/*  73 */     String[][] arrayOfString2 = getCatAry(str2, localInteger1);
/*  74 */     if (arrayOfString2 != null)
/*     */     {
/*     */       int i;
/*     */       do {
/*  78 */         localInteger3 = localInteger2;
/*  79 */         for (i = 0; i < arrayOfString2.length; ++i)
/*     */         {
/*  81 */           if (!(cls.getNum(arrayOfString2[i][0], Integer.valueOf(0)).equals(localInteger2)))
/*     */             continue;
/*  83 */           String[][] arrayOfString3 = new String[1][3];
/*  84 */           arrayOfString3[0][0] = arrayOfString2[i][0];
/*  85 */           arrayOfString3[0][1] = arrayOfString2[i][1];
/*  86 */           arrayOfString3[0][2] = arrayOfString2[i][2];
/*  87 */           arrayOfString1 = cls.mergeAry(arrayOfString1, arrayOfString3);
/*  88 */           localInteger2 = cls.getNum(arrayOfString2[i][2], Integer.valueOf(0));
/*  89 */           break;
/*     */         }
/*     */ 
/*  92 */         if (localInteger3.equals(localInteger2)) break;
/*     */       }
/*  94 */       while (localInteger2.intValue() != 0);
/*  95 */       if (arrayOfString1 != null)
/*     */       {
/*  97 */         for (i = arrayOfString1.length - 1; i >= 0; --i)
/*     */         {
/*  99 */           String str6 = str4;
/* 100 */           str6 = str6.replace("{$id}", encode.htmlencode(arrayOfString1[i][0]));
/* 101 */           str6 = str6.replace("{$topic}", encode.htmlencode(arrayOfString1[i][1]));
/* 102 */           str6 = str6.replace("{$fid}", encode.htmlencode(arrayOfString1[i][2]));
/* 103 */           str5 = str5 + str6;
/*     */         }
/*     */       }
/*     */     }
/* 107 */     str3 = cls.ctemplates(str3, "{@}", str5);
/* 108 */     str3 = this.conf.jt.creplace(str3);
/* 109 */     return str3;
/*     */   }
/*     */ 
/*     */   public String getClassText(String paramString, Integer paramInteger1, Integer paramInteger2)
/*     */   {
/* 114 */     String str1 = "";
/* 115 */     String str2 = paramString;
/* 116 */     Integer localInteger1 = paramInteger1;
/* 117 */     Integer localInteger2 = paramInteger2;
/* 118 */     str1 = getClassInfo(str2, Integer.valueOf(1), localInteger1, localInteger2);
/* 119 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getClassInfo(String paramString, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3)
/*     */   {
/* 124 */     String str1 = paramString;
/* 125 */     Integer localInteger1 = paramInteger1;
/* 126 */     Integer localInteger2 = paramInteger2;
/* 127 */     Integer localInteger3 = paramInteger3;
/* 128 */     String str2 = "";
/* 129 */     String[][] arrayOfString = getCatAry(str1, localInteger2);
/* 130 */     if (arrayOfString != null)
/*     */     {
/* 132 */       for (int i = 0; i < arrayOfString.length; ++i)
/*     */       {
/* 134 */         if (!(cls.getNum(arrayOfString[i][0], Integer.valueOf(0)).equals(localInteger3)))
/*     */           continue;
/* 136 */         str2 = arrayOfString[i][localInteger1.intValue()];
/* 137 */         break;
/*     */       }
/*     */     }
/*     */ 
/* 141 */     return str2;
/*     */   }
/*     */ 
/*     */   public String getClassChildIds(String paramString1, Integer paramInteger, String paramString2)
/*     */   {
/* 146 */     String str1 = "";
/* 147 */     String str2 = paramString1;
/* 148 */     Integer localInteger = paramInteger;
/* 149 */     String str3 = paramString2;
/* 150 */     str1 = getClassChildIds(str2, localInteger, str3, "-1");
/* 151 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getClassChildIds(String paramString1, Integer paramInteger, String paramString2, String paramString3)
/*     */   {
/* 156 */     String str1 = paramString1;
/* 157 */     Integer localInteger1 = paramInteger;
/* 158 */     String str2 = paramString2;
/* 159 */     String str3 = paramString3;
/* 160 */     String str4 = "";
/* 161 */     if (cls.cidary(str2).booleanValue())
/*     */     {
/* 163 */       String[] arrayOfString = str2.split(Pattern.quote(","));
/* 164 */       for (int i = 0; i < arrayOfString.length; ++i)
/*     */       {
/* 166 */         Integer localInteger2 = cls.getNum(arrayOfString[i], Integer.valueOf(-1));
/* 167 */         if (((!(str3.equals("-1"))) && (!(cls.cinstr(str3, cls.toString(localInteger2), ",").booleanValue()))) || 
/* 169 */           (localInteger2.intValue() == -1))
/*     */           continue;
/* 171 */         str4 = str4 + cls.toString(localInteger2) + ",";
/* 172 */         String[][] arrayOfString1 = getCatAry(str1, localInteger1);
/* 173 */         if (arrayOfString1 == null)
/*     */           continue;
/* 175 */         for (int j = 0; j < arrayOfString1.length; ++j)
/*     */         {
/* 177 */           if ((!(cls.getNum(arrayOfString1[j][2], Integer.valueOf(0)).equals(localInteger2))) || (
/* 179 */             (!(str3.equals("-1"))) && (!(cls.cinstr(str3, arrayOfString1[j][0], ",").booleanValue())))) continue; str4 = str4 + getClassChildIds(str1, localInteger1, arrayOfString1[j][0]) + ",";
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 186 */       if (!(cls.isEmpty(str4).booleanValue())) str4 = cls.getLRStr(str4, ",", "leftr");
/*     */     }
/* 188 */     return str4;
/*     */   }
/*     */ 
/*     */   public category(conf paramconf)
/*     */   {
/* 193 */     this.conf = paramconf;
/*     */   }
/*     */ }