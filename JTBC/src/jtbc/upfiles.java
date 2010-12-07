/*     */ package jtbc;
/*     */ 
/*     */ import java.util.regex.Pattern;
/*     */ import javax.servlet.ServletContext;
/*     */ import jtbc.dbc.dbc;
/*     */ 
/*     */ public class upfiles
/*     */ {
/*     */   public conf conf;
/*     */ 
/*     */   private void CreateDatabaseNote(String paramString1, String paramString2, String paramString3, int paramInt, String paramString4)
/*     */   {
/*  12 */     String str1 = paramString1;
/*  13 */     String str2 = paramString2;
/*  14 */     String str3 = paramString3;
/*  15 */     int i = paramInt;
/*  16 */     String str4 = paramString4;
/*  17 */     String str5 = cls.getString(this.conf.jt.itake("global.config.sys->upload-ndatabase", "cfg"));
/*  18 */     String str6 = cls.getString(this.conf.jt.itake("global.config.sys->upload-nfpre", "cfg"));
/*  19 */     String str7 = "insert into " + str5 + " (";
/*  20 */     str7 = str7 + cls.cfnames(str6, "genre") + ",";
/*  21 */     str7 = str7 + cls.cfnames(str6, "filename") + ",";
/*  22 */     str7 = str7 + cls.cfnames(str6, "field") + ",";
/*  23 */     str7 = str7 + cls.cfnames(str6, "foreback") + ",";
/*  24 */     str7 = str7 + cls.cfnames(str6, "username") + ",";
/*  25 */     str7 = str7 + cls.cfnames(str6, "time");
/*  26 */     str7 = str7 + ") values (";
/*  27 */     str7 = str7 + "'" + cls.getLeft(encode.addslashes(str1), Integer.valueOf(50)) + "',";
/*  28 */     str7 = str7 + "'" + cls.getLeft(encode.addslashes(str2), Integer.valueOf(255)) + "',";
/*  29 */     str7 = str7 + "'" + cls.getLeft(encode.addslashes(str3), Integer.valueOf(50)) + "',";
/*  30 */     str7 = str7 + i + ",";
/*  31 */     str7 = str7 + "'" + cls.getLeft(encode.addslashes(str4), Integer.valueOf(255)) + "',";
/*  32 */     str7 = str7 + "'" + cls.getDate() + "'";
/*  33 */     str7 = str7 + ")";
/*  34 */     dbc localdbc = db.newInstance(this.conf);
/*  35 */     localdbc.Execute(str7);
/*     */   }
/*     */ 
/*     */   public void UpdateDatabaseNote(String paramString1, String paramString2, String paramString3, int paramInt)
/*     */   {
/*  40 */     String str1 = paramString1;
/*  41 */     String str2 = paramString2;
/*  42 */     String str3 = paramString3;
/*  43 */     int i = paramInt;
/*  44 */     dbc localdbc = db.newInstance(this.conf);
/*  45 */     String str4 = cls.getString(this.conf.jt.itake("global.config.sys->upload-ndatabase", "cfg"));
/*  46 */     String str5 = cls.getString(this.conf.jt.itake("global.config.sys->upload-nfpre", "cfg"));
/*  47 */     String str6 = "update " + str4 + " set " + cls.cfnames(str5, "valid") + "=0," + cls.cfnames(str5, "vlreason") + "=2 where " + cls.cfnames(str5, "fid") + "=" + i + " and " + cls.cfnames(str5, "genre") + "='" + str1 + "' and " + cls.cfnames(str5, "field") + "='" + str3 + "'";
/*  48 */     localdbc.Execute(str6);
/*     */ 
/*  50 */     String[] arrayOfString = str2.split(Pattern.quote("|"));
/*  51 */     for (int j = 0; j < arrayOfString.length; ++j)
/*     */     {
/*  53 */       String str7 = cls.getSafeString(arrayOfString[j]);
/*  54 */       if (cls.isEmpty(str7).booleanValue()) continue; localdbc.Execute("update " + str4 + " set " + cls.cfnames(str5, "fid") + "=" + i + "," + cls.cfnames(str5, "valid") + "=1 where " + cls.cfnames(str5, "genre") + "='" + str1 + "' and " + cls.cfnames(str5, "field") + "='" + str3 + "' and " + cls.cfnames(str5, "filename") + "='" + str7 + "'");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void DeleteDatabaseNote(String paramString1, String paramString2)
/*     */   {
/*  60 */     String str1 = paramString1;
/*  61 */     String str2 = paramString2;
/*  62 */     String str3 = cls.getString(this.conf.jt.itake("global.config.sys->upload-ndatabase", "cfg"));
/*  63 */     String str4 = cls.getString(this.conf.jt.itake("global.config.sys->upload-nfpre", "cfg"));
/*  64 */     String str5 = cls.cfnames(str4, "id");
/*  65 */     if (!(cls.cidary(str2).booleanValue()))
/*     */       return;
/*  67 */     dbc localdbc = db.newInstance(this.conf);
/*  68 */     localdbc.Execute("update " + str3 + " set " + cls.cfnames(str4, "valid") + "=0," + cls.cfnames(str4, "vlreason") + "=1 where " + cls.cfnames(str4, "genre") + "='" + str1 + "' and " + cls.cfnames(str4, "fid") + " in (" + str2 + ")");
/*     */   }
/*     */ 
/*     */   public String uploadHTML(String paramString)
/*     */   {
/*  74 */     String str1 = paramString;
/*  75 */     String str2 = cls.getSafeString(this.conf.getRequestUsParameter("fid"));
/*  76 */     String str3 = cls.getSafeString(this.conf.getRequestUsParameter("fnid"));
/*  77 */     String str4 = cls.getSafeString(this.conf.getRequestUsParameter("fmode"));
/*  78 */     String str5 = cls.getSafeString(this.conf.getRequestUsParameter("fuptype"));
/*  79 */     String str6 = cls.getSafeString(this.conf.getRequestUsParameter("fupmaxsize"));
/*  80 */     String str7 = this.conf.jt.itake("global.tpl_common." + str1, "tpl");
/*  81 */     str7 = str7.replace("{$fid}", encode.htmlencode(str2));
/*  82 */     str7 = str7.replace("{$fnid}", encode.htmlencode(str3));
/*  83 */     str7 = str7.replace("{$fmode}", encode.htmlencode(str4));
/*  84 */     str7 = str7.replace("{$fname}", encode.htmlencode(str1));
/*  85 */     str7 = str7.replace("{$fuptype}", encode.htmlencode(str5));
/*  86 */     str7 = str7.replace("{$fupmaxsize}", encode.htmlencode(str6));
/*  87 */     str7 = str7.replace("{$errstate}", "-2");
/*  88 */     str7 = str7.replace("{$fullfilename}", "");
/*  89 */     str7 = str7.replace("{$-fuptype}", "");
/*  90 */     str7 = str7.replace("{$-fupmaxsize}", "");
/*  91 */     str7 = this.conf.jt.creplace(str7);
/*  92 */     return str7;
/*     */   }
/*     */ 
/*     */   public String uploadFiles(String paramString1, int paramInt, String paramString2)
/*     */   {
/*  97 */     String str1 = paramString1;
/*  98 */     int i = paramInt;
/*  99 */     String str2 = paramString2;
/* 100 */     int j = -1;
/* 101 */     int k = 0;
/* 102 */     Object localObject = "";
/* 103 */     String str3 = "";
/* 104 */     String str4 = "";
/* 105 */     String str5 = cls.getSafeString(this.conf.getRequestUsParameter("fid"));
/* 106 */     String str6 = cls.getSafeString(this.conf.getRequestUsParameter("fnid"));
/* 107 */     String str7 = cls.getSafeString(this.conf.getRequestUsParameter("fmode"));
/* 108 */     String str8 = cls.getSafeString(this.conf.getRequestUsParameter("fname"));
/* 109 */     String str9 = cls.getSafeString(this.conf.getRequestUsParameter("fuptype"));
/* 110 */     String str10 = cls.getSafeString(this.conf.getRequestUsParameter("fupmaxsize"));
/* 111 */     String str11 = str6;
/* 112 */     if (cls.isEmpty(str11).booleanValue()) str11 = str5;
/* 113 */     jupload localjupload = new jupload(this.conf, str1);
/* 114 */     String str12 = localjupload.getFileName();
/* 115 */     int l = localjupload.getContentLength();
/* 116 */     if (l <= 0) { j = 0;
/*     */     }
/*     */     else {
/* 119 */       k = cls.getNum(this.conf.jt.itake("config.nupmaxsize", "cfg"), Integer.valueOf(-1)).intValue();
/* 120 */       if (k == -1) k = cls.getNum(this.conf.jt.itake("global.config.nupmaxsize", "cfg"), Integer.valueOf(-1)).intValue();
/* 121 */       int i1 = cls.getNum(str10, Integer.valueOf(-1)).intValue();
/* 122 */       if ((i1 != -1) && (i1 < k)) k = i1;
/* 123 */       if (l > k) { j = 1;
/*     */       }
/*     */       else {
/* 126 */         String str13 = cls.getLRStr(str12, ".", "right");
/* 127 */         str13 = str13.toLowerCase();
/* 128 */         localObject = cls.getString(this.conf.jt.itake("config.nuptype", "cfg"));
/* 129 */         String str14 = cls.getString(str9);
/* 130 */         if ((!(cls.isEmpty(str14).booleanValue())) && (cls.cinstrs((String)localObject, str14, ".").booleanValue())) localObject = str14;
/* 131 */         if (!(cls.cinstr((String)localObject, str13, ".").booleanValue())) { j = 2;
/*     */         }
/*     */         else {
/* 134 */           String str15 = cls.getString(this.conf.jt.itake("config.nuppath", "cfg")) + cls.formatDate(cls.getDate(), Integer.valueOf(5));
/* 135 */           String str16 = this.conf.application.getRealPath(this.conf.getMapPath(str15)).toString();
/* 136 */           if (!(this.conf.common.directoryCreate(str16).booleanValue())) { j = 3;
/*     */           }
/*     */           else {
/* 139 */             String str17 = cls.formatDate(cls.getDate(), Integer.valueOf(20)) + cls.getRandomString(2) + "." + str13;
/* 140 */             Boolean localBoolean = localjupload.saveAs(str16 + "/" + str17);
/* 141 */             str4 = str15 + "/" + str17;
/* 142 */             if ((localBoolean.booleanValue() == true) && (str7 != "1")) CreateDatabaseNote(this.conf.getNGenre(), str4, str11, i, str2);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 147 */     if (str8.indexOf(".") != -1) str3 = this.conf.jt.itake(str8, "tpl");
/*     */     else str3 = this.conf.jt.itake("global.tpl_common." + str8, "tpl");
/* 149 */     str3 = str3.replace("{$fid}", encode.htmlencode(str5));
/* 150 */     str3 = str3.replace("{$fnid}", encode.htmlencode(str6));
/* 151 */     str3 = str3.replace("{$fmode}", encode.htmlencode(str7));
/* 152 */     str3 = str3.replace("{$fname}", encode.htmlencode(str8));
/* 153 */     str3 = str3.replace("{$fuptype}", encode.htmlencode(str9));
/* 154 */     str3 = str3.replace("{$fupmaxsize}", encode.htmlencode(str10));
/* 155 */     str3 = str3.replace("{$errstate}", cls.toString(Integer.valueOf(j)));
/* 156 */     str3 = str3.replace("{$fullfilename}", encode.htmlencode(str4));
/* 157 */     str3 = str3.replace("{$-fuptype}", encode.htmlencode((String)localObject));
/* 158 */     str3 = str3.replace("{$-fupmaxsize}", encode.htmlencode(cls.formatByte(cls.toString(Integer.valueOf(k)))));
/* 159 */     str3 = this.conf.jt.creplace(str3);
/* 160 */     return ((String)str3);
/*     */   }
/*     */ 
/*     */   public upfiles(conf paramconf)
/*     */   {
/* 165 */     this.conf = paramconf;
/*     */   }
/*     */ }