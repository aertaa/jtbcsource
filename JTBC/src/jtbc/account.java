/*     */ package jtbc;
/*     */ 
/*     */ import javax.servlet.http.HttpSession;
/*     */ import jtbc.dbc.dbc;
/*     */ 
/*     */ public class account
/*     */ {
/*     */   public conf conf;
/*     */   public dbcache dbcache;
/*     */   public String ngenre;
/*     */   public String nuserid;
/*     */   public String nusername;
/*     */   public String ndatabase;
/*     */ 
/*     */   public void Init()
/*     */   {
/*  17 */     Init("");
/*     */   }
/*     */ 
/*     */   public void Init(String paramString)
/*     */   {
/*  22 */     String str = paramString;
/*  23 */     if (!(cls.isEmpty(str).booleanValue())) this.ngenre = str;
/*     */     else this.ngenre = this.conf.getNGenre();
/*  25 */     this.ndatabase = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.ndatabase", "cfg"));
/*     */   }
/*     */ 
/*     */   public void UserInit()
/*     */   {
/*  30 */     this.nuserid = ((String)this.conf.session.getAttribute(this.conf.getAppKey(this.ngenre + "-nuserid")));
/*  31 */     this.nusername = ((String)this.conf.session.getAttribute(this.conf.getAppKey(this.ngenre + "-nusername")));
/*     */   }
/*     */ 
/*     */   public void Logout()
/*     */   {
/*  36 */     cookies.removeAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-userid"));
/*  37 */     cookies.removeAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-username"));
/*  38 */     cookies.removeAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-password"));
/*  39 */     this.conf.session.removeAttribute(this.conf.getAppKey(this.ngenre + "-nuserid"));
/*  40 */     this.conf.session.removeAttribute(this.conf.getAppKey(this.ngenre + "-nusername"));
/*     */   }
/*     */ 
/*     */   public void updateProperty(String paramString1, String paramString2, String paramString3)
/*     */   {
/*  45 */     String str1 = paramString1;
/*  46 */     String str2 = paramString2;
/*  47 */     String str3 = paramString3;
/*  48 */     updateProperty(str1, str2, str3, this.nuserid);
/*     */   }
/*     */ 
/*     */   public void updateProperty(String paramString1, String paramString2, String paramString3, String paramString4)
/*     */   {
/*  53 */     String str1 = paramString1;
/*  54 */     String str2 = paramString2;
/*  55 */     String str3 = paramString3;
/*  56 */     String str4 = paramString4;
/*  57 */     Integer localInteger = cls.getNum(str4, Integer.valueOf(-1));
/*  58 */     if (localInteger.intValue() == -1)
/*     */       return;
/*  60 */     String str5 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.ndatabase", "cfg"));
/*  61 */     String str6 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.nfpre", "cfg"));
/*  62 */     String str7 = cls.cfnames(str6, "id");
/*  63 */     dbc localdbc = db.newInstance(this.conf);
/*  64 */     if (str3.equals("0")) localdbc.Execute("update " + str5 + " set " + cls.cfnames(str6, str1) + "=" + cls.cfnames(str6, str1) + "+" + cls.getNum(str2, Integer.valueOf(0)) + " where " + str7 + "=" + localInteger);
/*  65 */     else if (str3.equals("1")) localdbc.Execute("update " + str5 + " set " + cls.cfnames(str6, str1) + "=" + cls.getNum(str2, Integer.valueOf(0)) + " where " + str7 + "=" + localInteger);
/*  66 */     else if (str3.equals("2")) localdbc.Execute("update " + str5 + " set " + cls.cfnames(str6, str1) + "='" + encode.addslashes(str2) + "' where " + str7 + "=" + localInteger);
/*  67 */     this.dbcache.deleteCache(str5, str6, localInteger);
/*     */   }
/*     */ 
/*     */   public void setPasswordCookies(String paramString)
/*     */   {
/*  73 */     String str = cls.getSafeString(paramString);
/*  74 */     cookies.setAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-password"), str);
/*     */   }
/*     */ 
/*     */   public Boolean checkUsername(String paramString1, String paramString2, String paramString3)
/*     */   {
/*  79 */     Boolean localBoolean = Boolean.valueOf(false);
/*  80 */     Integer localInteger = cls.getNum(paramString1, Integer.valueOf(-1));
/*  81 */     String str1 = cls.getSafeString(paramString2);
/*  82 */     String str2 = cls.getSafeString(paramString3);
/*  83 */     if (localInteger.intValue() != -1)
/*     */     {
/*  85 */       dbc localdbc = db.newInstance(this.conf);
/*  86 */       String str3 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.ndatabase", "cfg"));
/*  87 */       String str4 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.nfpre", "cfg"));
/*  88 */       String str5 = cls.cfnames(str4, "id");
/*  89 */       String str6 = "select * from " + str3 + " where " + str5 + "=" + localInteger + " and " + cls.cfnames(str4, "lock") + "=0";
/*  90 */       Object[] arrayOfObject = localdbc.getDataAry(str6);
/*  91 */       if (arrayOfObject != null)
/*     */       {
/*  93 */         Object[][] arrayOfObject1 = (Object[][])(Object[][])arrayOfObject[0];
/*  94 */         String str7 = (String)localdbc.getValue(arrayOfObject1, cls.cfnames(str4, "username"));
/*  95 */         String str8 = (String)localdbc.getValue(arrayOfObject1, cls.cfnames(str4, "password"));
/*  96 */         if ((str7.equals(str1)) && (str8.equals(str2))) localBoolean = Boolean.valueOf(true);
/*     */       }
/*     */     }
/*  99 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   public Boolean checkUserLogin()
/*     */   {
/* 104 */     Boolean localBoolean = Boolean.valueOf(false);
/* 105 */     if ((!(cls.isEmpty(this.nuserid).booleanValue())) && (!(cls.isEmpty(this.nusername).booleanValue()))) { localBoolean = Boolean.valueOf(true);
/*     */     }
/*     */     else {
/* 108 */       String str1 = cls.getSafeString(cookies.getAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-userid")));
/* 109 */       String str2 = cls.getSafeString(cookies.getAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-username")));
/* 110 */       String str3 = cls.getSafeString(cookies.getAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-password")));
/* 111 */       if ((!(cls.isEmpty(str1).booleanValue())) && (!(cls.isEmpty(str2).booleanValue())) && (!(cls.isEmpty(str3).booleanValue())) && 
/* 113 */         (checkUsername(str1, str2, str3).booleanValue()))
/*     */       {
/* 115 */         this.nuserid = str1;
/* 116 */         this.nusername = str2;
/* 117 */         this.conf.session.setAttribute(this.conf.getAppKey(this.ngenre + "-nuserid"), str1);
/* 118 */         this.conf.session.setAttribute(this.conf.getAppKey(this.ngenre + "-nusername"), str2);
/* 119 */         localBoolean = Boolean.valueOf(true);
/*     */       }
/*     */     }
/*     */ 
/* 123 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   public String cfname(String paramString)
/*     */   {
/* 128 */     String str1 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.nfpre", "cfg"));
/* 129 */     String str2 = str1 + paramString;
/* 130 */     return str2;
/*     */   }
/*     */ 
/*     */   public String getDefMenuHtml()
/*     */   {
/* 135 */     String str1 = "";
/*     */ 
/* 137 */     str1 = this.conf.jt.itake("global." + this.ngenre + ":default.data_defmenu", "tpl");
/* 138 */     String str3 = "";
/* 139 */     String str2 = cls.ctemplate(str1, "{@}");
/* 140 */     String[][] arrayOfString = this.conf.jt.itakes("global." + this.ngenre + ":defmenu.all", "lng");
/* 141 */     for (int i = 0; i < arrayOfString.length; ++i)
/*     */     {
/* 143 */       String str5 = arrayOfString[i][0];
/* 144 */       String str6 = arrayOfString[i][1];
/* 145 */       str5 = str5.replace("{$-genre}", this.ngenre);
/* 146 */       str5 = this.conf.jt.creplace(str5);
/* 147 */       String str4 = str2;
/* 148 */       str4 = str4.replace("{$href}", encode.htmlencode(str5));
/* 149 */       str4 = str4.replace("{$text}", encode.htmlencode(str6));
/* 150 */       str3 = str3 + str4;
/*     */     }
/* 152 */     str1 = cls.ctemplates(str1, "{@}", str3);
/* 153 */     str1 = str1.replace("{$-genre}", this.ngenre);
/* 154 */     str1 = str1.replace("{$-nuserid}", cls.getString(this.nuserid));
/* 155 */     str1 = str1.replace("{$-nusername}", cls.getString(this.nusername));
/* 156 */     str1 = this.conf.jt.creplace(str1);
/* 157 */     return str1;
/*     */   }
/*     */ 
/*     */   public Integer getUserID(String paramString)
/*     */   {
/* 162 */     Integer localInteger = Integer.valueOf(0);
/* 163 */     String str1 = cls.getSafeString(paramString);
/* 164 */     String str2 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.ndatabase", "cfg"));
/* 165 */     String str3 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.nfpre", "cfg"));
/* 166 */     String str4 = cls.cfnames(str3, "id");
/* 167 */     String str5 = "select * from " + str2 + " where " + cls.cfnames(str3, "username") + "='" + str1 + "'";
/* 168 */     dbc localdbc = db.newInstance(this.conf);
/* 169 */     Object[] arrayOfObject = localdbc.getDataAry(str5);
/* 170 */     if (arrayOfObject != null)
/*     */     {
/* 172 */       Object[][] arrayOfObject1 = (Object[][])(Object[][])arrayOfObject[0];
/* 173 */       localInteger = cls.getNum(cls.toString(localdbc.getValue(arrayOfObject1, str4)), Integer.valueOf(0));
/*     */     }
/* 175 */     return localInteger;
/*     */   }
/*     */ 
/*     */   public Object[][] getUserAry(String paramString)
/*     */   {
/* 180 */     Object[][] arrayOfObject = (Object[][])null;
/* 181 */     Integer localInteger = cls.getNum(paramString, Integer.valueOf(-1));
/* 182 */     String str1 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.ndatabase", "cfg"));
/* 183 */     String str2 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.nfpre", "cfg"));
/* 184 */     if ((!(cls.isEmpty(str1).booleanValue())) && (localInteger.intValue() != -1)) arrayOfObject = this.dbcache.getAry(str1, str2, localInteger);
/* 185 */     return arrayOfObject;
/*     */   }
/*     */ 
/*     */   public Object getUserInfo(String paramString)
/*     */   {
/* 190 */     Object localObject = null;
/* 191 */     String str = cls.getString(paramString);
/* 192 */     localObject = getUserInfo(str, this.nuserid);
/* 193 */     return localObject;
/*     */   }
/*     */ 
/*     */   public Object getUserInfo(String paramString1, String paramString2)
/*     */   {
/* 198 */     Object localObject = null;
/* 199 */     String str1 = cls.getString(paramString1);
/* 200 */     String str2 = cls.getString(paramString2);
/* 201 */     Object[][] arrayOfObject = getUserAry(str2);
/* 202 */     if (arrayOfObject != null) localObject = cls.getAryValue(arrayOfObject, cfname(str1));
/* 203 */     return localObject;
/*     */   }
/*     */ 
/*     */   public Boolean Login(String paramString1, String paramString2)
/*     */   {
/* 208 */     Boolean localBoolean = Boolean.valueOf(false);
/* 209 */     String str1 = paramString1;
/* 210 */     String str2 = paramString2;
/* 211 */     localBoolean = Login(str1, str2, "0");
/* 212 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   public Boolean Login(String paramString1, String paramString2, String paramString3)
/*     */   {
/* 217 */     Boolean localBoolean = Boolean.valueOf(false);
/* 218 */     Logout();
/* 219 */     String str1 = cls.getSafeString(paramString1);
/* 220 */     String str2 = encode.md5(cls.getSafeString(paramString2).getBytes());
/* 221 */     Integer localInteger = cls.getNum(paramString3, Integer.valueOf(0));
/* 222 */     dbc localdbc = db.newInstance(this.conf);
/* 223 */     String str3 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.ndatabase", "cfg"));
/* 224 */     String str4 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.nfpre", "cfg"));
/* 225 */     String str5 = cls.cfnames(str4, "id");
/* 226 */     String str6 = "select * from " + str3 + " where " + cls.cfnames(str4, "username") + "='" + str1 + "' and " + cls.cfnames(str4, "password") + "='" + str2 + "' and " + cls.cfnames(str4, "lock") + "=0";
/* 227 */     Object[] arrayOfObject = localdbc.getDataAry(str6);
/* 228 */     if (arrayOfObject != null)
/*     */     {
/* 230 */       Object[][] arrayOfObject1 = (Object[][])(Object[][])arrayOfObject[0];
/* 231 */       String str7 = cls.toString(localdbc.getValue(arrayOfObject1, str5));
/* 232 */       if (localInteger.intValue() == 0)
/*     */       {
/* 234 */         cookies.setAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-userid"), str7);
/* 235 */         cookies.setAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-username"), str1);
/* 236 */         cookies.setAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-password"), str2);
/*     */       }
/*     */       else
/*     */       {
/* 240 */         cookies.setAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-userid"), str7, Integer.valueOf(31536000));
/* 241 */         cookies.setAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-username"), str1, Integer.valueOf(31536000));
/* 242 */         cookies.setAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-password"), str2, Integer.valueOf(31536000));
/*     */       }
/* 244 */       this.conf.session.setAttribute(this.conf.getAppKey(this.ngenre + "-nuserid"), str7);
/* 245 */       this.conf.session.setAttribute(this.conf.getAppKey(this.ngenre + "-nusername"), str1);
/* 246 */       localdbc.Execute("update " + str3 + " set " + cls.cfnames(str4, "prelasttime") + "=" + cls.cfnames(str4, "lasttime") + " where " + str5 + "=" + str7);
/* 247 */       localdbc.Execute("update " + str3 + " set " + cls.cfnames(str4, "lasttime") + "='" + cls.getDate() + "' where " + str5 + "=" + str7);
/* 248 */       localBoolean = Boolean.valueOf(true);
/*     */     }
/* 250 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   public account(conf paramconf)
/*     */   {
/* 255 */     this.conf = paramconf;
/* 256 */     this.dbcache = new dbcache(this.conf);
/*     */   }
/*     */ }