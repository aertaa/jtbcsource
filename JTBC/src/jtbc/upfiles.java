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
/*  13 */     String str1 = paramString1;
/*  14 */     String str2 = paramString2;
/*  15 */     String str3 = paramString3;
/*  16 */     int i = paramInt;
/*  17 */     String str4 = paramString4;
/*  18 */     String str5 = cls.getString(this.conf.jt.itake("global.config.sys->upload-ndatabase", "cfg"));
/*  19 */     String str6 = cls.getString(this.conf.jt.itake("global.config.sys->upload-nfpre", "cfg"));
/*  20 */     String str7 = "insert into " + str5 + " (";
/*  21 */     str7 = str7 + cls.cfnames(str6, "genre") + ",";
/*  22 */     str7 = str7 + cls.cfnames(str6, "filename") + ",";
/*  23 */     str7 = str7 + cls.cfnames(str6, "field") + ",";
/*  24 */     str7 = str7 + cls.cfnames(str6, "foreback") + ",";
/*  25 */     str7 = str7 + cls.cfnames(str6, "username") + ",";
/*  26 */     str7 = str7 + cls.cfnames(str6, "time");
/*  27 */     str7 = str7 + ") values (";
/*  28 */     str7 = str7 + "'" + cls.getLeft(encode.addslashes(str1), Integer.valueOf(50)) + "',";
/*  29 */     str7 = str7 + "'" + cls.getLeft(encode.addslashes(str2), Integer.valueOf(255)) + "',";
/*  30 */     str7 = str7 + "'" + cls.getLeft(encode.addslashes(str3), Integer.valueOf(50)) + "',";
/*  31 */     str7 = str7 + i + ",";
/*  32 */     str7 = str7 + "'" + cls.getLeft(encode.addslashes(str4), Integer.valueOf(255)) + "',";
/*  33 */     str7 = str7 + "'" + cls.getDate() + "'";
/*  34 */     str7 = str7 + ")";
/*  35 */     dbc localdbc = db.newInstance(this.conf);
/*  36 */     localdbc.Execute(str7);
/*     */   }
/*     */ 
/*     */   public void UpdateDatabaseNote(String paramString1, String paramString2, String paramString3, int paramInt)
/*     */   {
/*  41 */     String str1 = paramString1;
/*  42 */     String str2 = paramString2;
/*  43 */     String str3 = paramString3;
/*  44 */     int i = paramInt;
/*  45 */     dbc localdbc = db.newInstance(this.conf);
/*  46 */     String str4 = cls.getString(this.conf.jt.itake("global.config.sys->upload-ndatabase", "cfg"));
/*  47 */     String str5 = cls.getString(this.conf.jt.itake("global.config.sys->upload-nfpre", "cfg"));
/*  48 */     String str6 = "update " + str4 + " set " + cls.cfnames(str5, "valid") + "=0," + cls.cfnames(str5, "vlreason") + "=2 where " + cls.cfnames(str5, "fid") + "=" + i + " and " + cls.cfnames(str5, "genre") + "='" + str1 + "' and " + cls.cfnames(str5, "field") + "='" + str3 + "'";
/*  49 */     localdbc.Execute(str6);
/*     */ 
/*  51 */     String[] arrayOfString = str2.split(Pattern.quote("|"));
/*  52 */     for (int j = 0; j < arrayOfString.length; ++j)
/*     */     {
/*  54 */       String str7 = cls.getSafeString(arrayOfString[j]);
/*  55 */       if (cls.isEmpty(str7).booleanValue()) continue; localdbc.Execute("update " + str4 + " set " + cls.cfnames(str5, "fid") + "=" + i + "," + cls.cfnames(str5, "valid") + "=1 where " + cls.cfnames(str5, "genre") + "='" + str1 + "' and " + cls.cfnames(str5, "field") + "='" + str3 + "' and " + cls.cfnames(str5, "filename") + "='" + str7 + "'");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void DeleteDatabaseNote(String paramString1, String paramString2)
/*     */   {
/*  61 */     String str1 = paramString1;
/*  62 */     String str2 = paramString2;
/*  63 */     String str3 = cls.getString(this.conf.jt.itake("global.config.sys->upload-ndatabase", "cfg"));
/*  64 */     String str4 = cls.getString(this.conf.jt.itake("global.config.sys->upload-nfpre", "cfg"));
/*  65 */     String str5 = cls.cfnames(str4, "id");
/*  66 */     if (!(cls.cidary(str2).booleanValue()))
/*     */       return;
/*  68 */     dbc localdbc = db.newInstance(this.conf);
/*  69 */     localdbc.Execute("update " + str3 + " set " + cls.cfnames(str4, "valid") + "=0," + cls.cfnames(str4, "vlreason") + "=1 where " + cls.cfnames(str4, "genre") + "='" + str1 + "' and " + cls.cfnames(str4, "fid") + " in (" + str2 + ")");
/*     */   }
/*     */ 
/*     */   public String uploadHTML(String paramString)
/*     */   {
/*  75 */     String str1 = paramString;
/*  76 */     String str2 = cls.getSafeString(this.conf.getRequestUsParameter("fid"));
/*  77 */     String str3 = cls.getSafeString(this.conf.getRequestUsParameter("fnid"));
/*  78 */     String str4 = cls.getSafeString(this.conf.getRequestUsParameter("fmode"));
/*  79 */     String str5 = cls.getSafeString(this.conf.getRequestUsParameter("fuptype"));
/*  80 */     String str6 = cls.getSafeString(this.conf.getRequestUsParameter("fupmaxsize"));
/*  81 */     String str7 = this.conf.jt.itake("global.tpl_common." + str1, "tpl");
/*  82 */     str7 = str7.replace("{$fid}", encode.htmlencode(str2));
/*  83 */     str7 = str7.replace("{$fnid}", encode.htmlencode(str3));
/*  84 */     str7 = str7.replace("{$fmode}", encode.htmlencode(str4));
/*  85 */     str7 = str7.replace("{$fname}", encode.htmlencode(str1));
/*  86 */     str7 = str7.replace("{$fuptype}", encode.htmlencode(str5));
/*  87 */     str7 = str7.replace("{$fupmaxsize}", encode.htmlencode(str6));
/*  88 */     str7 = str7.replace("{$errstate}", "-2");
/*  89 */     str7 = str7.replace("{$fullfilename}", "");
/*  90 */     str7 = str7.replace("{$-fuptype}", "");
/*  91 */     str7 = str7.replace("{$-fupmaxsize}", "");
/*  92 */     str7 = this.conf.jt.creplace(str7);
/*  93 */     return str7;
/*     */   }
/*     */ 
/*     */   public String uploadFiles(String paramString1, int paramInt, String paramString2)
/*     */   {
/*  98 */     String str1 = paramString1;
/*  99 */     int i = paramInt;
/* 100 */     String str2 = paramString2;
/* 101 */     int j = -1;
/* 102 */     int k = 0;
/* 103 */     Object localObject = "";
/* 104 */     String str3 = "";
/* 105 */     String str4 = "";
/* 106 */     String str5 = cls.getSafeString(this.conf.getRequestUsParameter("fid"));
/* 107 */     String str6 = cls.getSafeString(this.conf.getRequestUsParameter("fnid"));
/* 108 */     String str7 = cls.getSafeString(this.conf.getRequestUsParameter("fmode"));
/* 109 */     String str8 = cls.getSafeString(this.conf.getRequestUsParameter("fname"));
/* 110 */     String str9 = cls.getSafeString(this.conf.getRequestUsParameter("fuptype"));
/* 111 */     String str10 = cls.getSafeString(this.conf.getRequestUsParameter("fupmaxsize"));
/* 112 */     String str11 = str6;
/* 113 */     if (cls.isEmpty(str11).booleanValue()) str11 = str5;
/* 114 */     jupload localjupload = new jupload(this.conf, str1);
/* 115 */     String str12 = localjupload.getFileName();
			  //System.out.println(str12);
/* 116 */     int l = localjupload.getContentLength();
/* 117 */     if (l <= 0) { j = 0;
/*     */     }
/*     */     else {
/* 120 */       k = cls.getNum(this.conf.jt.itake("config.nupmaxsize", "cfg"), Integer.valueOf(-1)).intValue();
/* 121 */       if (k == -1) k = cls.getNum(this.conf.jt.itake("global.config.nupmaxsize", "cfg"), Integer.valueOf(-1)).intValue();
/* 122 */       int i1 = cls.getNum(str10, Integer.valueOf(-1)).intValue();
/* 123 */       if ((i1 != -1) && (i1 < k)) k = i1;
/* 124 */       if (l > k) { j = 1;
/*     */       }
/*     */       else {
/* 127 */         String str13 = cls.getLRStr(str12, ".", "right");
					
/* 128 */         str13 = str13.toLowerCase();
/* 129 */         localObject = cls.getString(this.conf.jt.itake("config.nuptype", "cfg"));
/* 130 */         String str14 = cls.getString(str9);
/* 131 */         if ((!(cls.isEmpty(str14).booleanValue())) && (cls.cinstrs((String)localObject, str14, ".").booleanValue())) localObject = str14;
/* 132 */         if (!(cls.cinstr((String)localObject, str13, ".").booleanValue())) { j = 2;
/*     */         }
/*     */         else {
/* 135 */           String str15 = cls.getString(this.conf.jt.itake("config.nuppath", "cfg")) + cls.formatDate(cls.getDate(), Integer.valueOf(5));
/* 136 */           String str16 = this.conf.application.getRealPath(this.conf.getMapPath(str15)).toString();
/* 137 */           if (!(this.conf.common.directoryCreate(str16).booleanValue())) { j = 3;
/*     */           }
/*     */           else {
/* 140 */             String str17 = cls.formatDate(cls.getDate(), Integer.valueOf(20)) + cls.getRandomString(2) + "." + str13;
/* 141 */             Boolean localBoolean = localjupload.saveAs(str16 + "/" + str17);
/* 142 */             str4 = str15 + "/" + str17;
/* 143 */             if ((localBoolean.booleanValue() == true) && (str7 != "1")) CreateDatabaseNote(this.conf.getNGenre(), str4, str11, i, str2);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 148 */     if (str8.indexOf(".") != -1) str3 = this.conf.jt.itake(str8, "tpl");
/*     */     else str3 = this.conf.jt.itake("global.tpl_common." + str8, "tpl");
/* 150 */     str3 = str3.replace("{$fid}", encode.htmlencode(str5));
/* 151 */     str3 = str3.replace("{$fnid}", encode.htmlencode(str6));
/* 152 */     str3 = str3.replace("{$fmode}", encode.htmlencode(str7));
/* 153 */     str3 = str3.replace("{$fname}", encode.htmlencode(str8));
/* 154 */     str3 = str3.replace("{$fuptype}", encode.htmlencode(str9));
/* 155 */     str3 = str3.replace("{$fupmaxsize}", encode.htmlencode(str10));
/* 156 */     str3 = str3.replace("{$errstate}", cls.toString(Integer.valueOf(j)));
/* 157 */     str3 = str3.replace("{$fullfilename}", encode.htmlencode(str4));
/* 158 */     str3 = str3.replace("{$-fuptype}", encode.htmlencode((String)localObject));
/* 159 */     str3 = str3.replace("{$-fupmaxsize}", encode.htmlencode(cls.formatByte(cls.toString(Integer.valueOf(k)))));
/* 160 */     str3 = this.conf.jt.creplace(str3);
/* 161 */     return ((String)str3);
/*     */   }
/*     */ 
/*     */   public upfiles(conf paramconf)
/*     */   {
/* 166 */     this.conf = paramconf;
/*     */   }
/*     */ }