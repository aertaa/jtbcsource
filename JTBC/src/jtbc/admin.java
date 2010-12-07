/*     */ package jtbc;
/*     */ 
/*     */ import java.util.regex.Pattern;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import jtbc.dbc.dbc;
/*     */ 
/*     */ public class admin
/*     */ {
/*     */   public conf conf;
/*     */   public int id;
/*     */   public int slng;
/*     */   public String username;
/*     */   public String popedom;
/*     */   public String myusername;
/*     */   public String mypassword;
/*     */   public String adminPstate;
/*     */ 
/*     */   public void Logout()
/*     */   {
/*  20 */     this.conf.session.removeAttribute(this.conf.getAppKey("admin-id"));
/*  21 */     this.conf.session.removeAttribute(this.conf.getAppKey("admin-username"));
/*  22 */     this.conf.session.removeAttribute(this.conf.getAppKey("admin-popedom"));
/*  23 */     cookies.removeAttribute(this.conf, this.conf.getAppKey("admin-username"));
/*  24 */     cookies.removeAttribute(this.conf, this.conf.getAppKey("admin-password"));
/*     */   }
/*     */ 
/*     */   public void removeMenuHtmlCacheData(int paramInt)
/*     */   {
/*  29 */     int i = paramInt;
/*  30 */     String str = "admin_menu_html_" + cls.toString(Integer.valueOf(i));
/*  31 */     this.conf.application.removeAttribute(this.conf.getAppKey(str));
/*     */   }
/*     */ 
/*     */   public Boolean ckLogin()
/*     */   {
/*  36 */     Boolean localBoolean = Boolean.valueOf(false);
/*  37 */     if (ckLogin(this.myusername, this.mypassword).booleanValue())
/*     */     {
/*  39 */       if (this.adminPstate.equals("public")) localBoolean = Boolean.valueOf(true);
/*  42 */       else if (ckPopedom(this.popedom, this.conf.getNGenre()).booleanValue()) localBoolean = Boolean.valueOf(true);
/*     */     }
/*     */ 
/*  45 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   public Boolean ckLogin(String paramString1, String paramString2)
/*     */   {
/*  50 */     Boolean localBoolean = Boolean.valueOf(true);
/*  51 */     if ((cls.isEmpty(this.username).booleanValue()) || (cls.isEmpty(this.popedom).booleanValue()))
/*     */     {
/*  53 */       dbc localdbc = db.newInstance(this.conf);
/*  54 */       String str1 = "";
/*  55 */       String str2 = cls.getSafeString(paramString1);
/*  56 */       String str3 = cls.getSafeString(paramString2);
/*  57 */       String str4 = cls.getString(this.conf.jt.itake("global.config.admin-ndatabase", "cfg"));
/*  58 */       String str5 = cls.getString(this.conf.jt.itake("global.config.admin-nfpre", "cfg"));
/*  59 */       String str6 = "select * from " + str4 + " where " + cls.cfnames(str5, "username") + "='" + str2 + "' and " + cls.cfnames(str5, "password") + "='" + str3 + "' and " + cls.cfnames(str5, "lock") + "=0";
/*  60 */       Object[] arrayOfObject = localdbc.getDataAry(str6);
/*  61 */       if (arrayOfObject != null)
/*     */       {
/*  63 */         Object[][] arrayOfObject1 = (Object[][])(Object[][])arrayOfObject[0];
/*  64 */         Integer localInteger = (Integer)localdbc.getValue(arrayOfObject1, cls.cfnames(str5, "id"));
/*  65 */         String str7 = (String)localdbc.getValue(arrayOfObject1, cls.cfnames(str5, "username"));
/*  66 */         String str8 = (String)localdbc.getValue(arrayOfObject1, cls.cfnames(str5, "password"));
/*  67 */         String str9 = (String)localdbc.getValue(arrayOfObject1, cls.cfnames(str5, "popedom"));
/*  68 */         this.conf.session.setAttribute(this.conf.getAppKey("admin-id"), cls.toString(localInteger));
/*  69 */         this.conf.session.setAttribute(this.conf.getAppKey("admin-username"), str7);
/*  70 */         this.conf.session.setAttribute(this.conf.getAppKey("admin-popedom"), str9);
/*  71 */         cookies.setAttribute(this.conf, this.conf.getAppKey("admin-username"), str7);
/*  72 */         cookies.setAttribute(this.conf, this.conf.getAppKey("admin-password"), str8);
/*  73 */         this.id = localInteger.intValue();
/*  74 */         this.username = str7;
/*  75 */         this.popedom = str9;
/*  76 */         str1 = "update " + str4 + " set " + cls.cfnames(str5, "lasttime") + "='" + cls.getDate() + "'," + cls.cfnames(str5, "lastip") + "='" + this.conf.getRemortIP() + "' where " + cls.cfnames(str5, "username") + "='" + str2 + "'";
/*     */       }
/*     */       else
/*     */       {
/*  80 */         localBoolean = Boolean.valueOf(false);
/*     */       }
/*  82 */       if (!(cls.isEmpty(str1).booleanValue())) localdbc.Execute(str1);
/*     */     }
/*  84 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   public Boolean ckLogins(String paramString1, String paramString2)
/*     */   {
/*  89 */     String str1 = paramString1;
/*  90 */     String str2 = paramString2;
/*  91 */     this.username = ""; this.popedom = "";
/*  92 */     this.conf.session.removeAttribute(this.conf.getAppKey("admin-id"));
/*  93 */     this.conf.session.removeAttribute(this.conf.getAppKey("admin-username"));
/*  94 */     this.conf.session.removeAttribute(this.conf.getAppKey("admin-popedom"));
/*  95 */     Boolean localBoolean = ckLogin(str1, encode.md5(str2.getBytes()));
/*  96 */     int i = 0;
/*  97 */     if (!(localBoolean.booleanValue())) i = 1;
/*  98 */     dbc localdbc = db.newInstance(this.conf);
/*  99 */     String str3 = cls.getString(this.conf.jt.itake("global.config.admin-userlog-ndatabase", "cfg"));
/* 100 */     String str4 = cls.getString(this.conf.jt.itake("global.config.admin-userlog-nfpre", "cfg"));
/* 101 */     String str5 = "insert into " + str3 + " (" + cls.cfnames(str4, "username") + "," + cls.cfnames(str4, "error") + "," + cls.cfnames(str4, "ip") + "," + cls.cfnames(str4, "time") + ") values ('" + cls.getSafeString(str1) + "'," + i + ",'" + this.conf.getRemortIP() + "','" + cls.getDate() + "')";
/* 102 */     localdbc.Execute(str5);
/* 103 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   public Boolean ckPopedom(String paramString1, String paramString2)
/*     */   {
/* 108 */     Boolean localBoolean = Boolean.valueOf(false);
/* 109 */     String str1 = paramString2;
/* 110 */     String str2 = paramString1;
/* 111 */     if (str2.equals("-1")) localBoolean = Boolean.valueOf(true);
/* 114 */     else if ((!(cls.isEmpty(str2).booleanValue())) && (!(cls.isEmpty(str1).booleanValue())) && 
/* 116 */       (cls.cinstr(str2, str1, ",").booleanValue())) localBoolean = Boolean.valueOf(true);
/*     */ 
/* 119 */     return localBoolean;
/*     */   }
/*     */ 
/*     */   public String formatMenuHtml(String[][] paramArrayOfString)
/*     */   {
/* 124 */     String[][] arrayOfString = paramArrayOfString;
/*     */ 
/* 126 */     String str4 = "";
/*     */ 
/* 128 */     if (arrayOfString != null)
/*     */     {
/* 130 */       int i = arrayOfString.length;
/* 131 */       int j = arrayOfString[0].length;
/* 132 */       if (j == 2)
/*     */       {
/* 134 */         str4 = this.conf.jt.itake("global.tpl_common.menucontent", "tpl");
/* 135 */         String str6 = "";
/* 136 */         String str5 = cls.ctemplate(str4, "{@}");
/* 137 */         for (int k = 0; k < i; ++k)
/*     */         {
/* 139 */           String str1 = arrayOfString[k][0];
/* 140 */           if ((!(cls.getLRStr(str1, ":", "right").equals("description"))) || (str1.indexOf("/") != -1))
/*     */             continue;
/* 142 */           String str7 = str5;
/* 143 */           String str2 = cls.getParameter(arrayOfString[k][1], "text", "@");
/* 144 */           str2 = str2 + formatMenuHtml(arrayOfString, cls.getLRStr(str1, ":", "leftr"));
/* 145 */           String str3 = cls.getParameter(arrayOfString[k][1], "link", "@");
/* 146 */           str7 = str7.replace("{$text}", str2);
/* 147 */           str7 = str7.replace("{$link}", str3);
/* 148 */           str6 = str6 + str7;
/*     */         }
/*     */ 
/* 151 */         str4 = cls.ctemplates(str4, "{@}", str6);
/* 152 */         str4 = this.conf.jt.creplace(str4);
/*     */       }
/*     */     }
/* 155 */     return str4;
/*     */   }
/*     */ 
/*     */   public String formatMenuHtml(String[][] paramArrayOfString, String paramString)
/*     */   {
/* 161 */     String[][] arrayOfString = paramArrayOfString;
/* 162 */     String str1 = paramString;
/*     */ 
/* 164 */     String str5 = "";
/*     */ 
/* 166 */     if (arrayOfString != null)
/*     */     {
/* 168 */       int i = arrayOfString.length;
/* 169 */       int j = arrayOfString[0].length;
/* 170 */       if (j == 2)
/*     */       {
/* 172 */         str5 = this.conf.jt.itake("global.tpl_common.menucontent-child", "tpl");
/* 173 */         String str7 = "";
/* 174 */         String str6 = cls.ctemplate(str5, "{@}");
/* 175 */         for (int k = 0; k < i; ++k)
/*     */         {
/* 177 */           String str2 = arrayOfString[k][0];
/* 178 */           if ((!(str2.equals(str1 + ":link"))) && (((!(cls.getLRStr(str2, ":", "right").equals("description"))) || (!(cls.getLRStr(str2, "/", "leftr").equals(str1))))))
/*     */             continue;
/* 180 */           String str8 = str6;
/* 181 */           String str3 = cls.getParameter(arrayOfString[k][1], "text", "@");
/* 182 */           if (cls.getLRStr(str2, ":", "leftr") != str1) str3 = str3 + formatMenuHtml(arrayOfString, cls.getLRStr(str2, ":", "leftr"));
/* 183 */           String str4 = cls.getParameter(arrayOfString[k][1], "link", "@");
/* 184 */           str8 = str8.replace("{$text}", str3);
/* 185 */           str8 = str8.replace("{$link}", str4);
/* 186 */           str7 = str7 + str8;
/*     */         }
/*     */ 
/* 189 */         if (cls.isEmpty(str7).booleanValue()) str5 = "";
/*     */         else str5 = cls.ctemplates(str5, "{@}", str7);
/*     */       }
/*     */     }
/* 193 */     return str5;
/*     */   }
/*     */ 
/*     */   public String getPopedomCategory(String paramString1, String paramString2)
/*     */   {
/* 198 */     Object localObject = "-1";
/* 199 */     String str1 = paramString2;
/* 200 */     String str2 = paramString1;
/* 201 */     if ((!(str2.equals("-1"))) && 
/* 203 */       (!(cls.isEmpty(str2).booleanValue())) && (!(cls.isEmpty(str1).booleanValue())))
/*     */     {
/*     */       String str3;
/* 205 */       if (str2.indexOf("," + str1 + ",") != -1)
/*     */       {
/* 207 */         str3 = cls.getLRStr(str2, "," + str1 + ",", "rightr");
/* 208 */         if (!(cls.isEmpty(str3).booleanValue())) str3 = cls.getLRStr(str3, "[", "rightr");
/* 209 */         if (!(cls.isEmpty(str3).booleanValue())) str3 = cls.getLRStr(str3, "]", "left");
/* 210 */         if (cls.cidary(str3).booleanValue()) localObject = str3;
/*     */ 
/*     */       }
/* 214 */       else if (cls.getLRStr(str2, ",", "left").equals(str1))
/*     */       {
/* 216 */         str3 = cls.getLRStr(str2, ",", "rightr");
/* 217 */         if (!(cls.isEmpty(str3).booleanValue())) str3 = cls.getLRStr(str3, "[", "rightr");
/* 218 */         if (!(cls.isEmpty(str3).booleanValue())) str3 = cls.getLRStr(str3, "]", "left");
/* 219 */         if (cls.cidary(str3).booleanValue()) localObject = str3;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 224 */     return ((String)localObject);
/*     */   }
/*     */ 
/*     */   public String getMenuHtml(String paramString)
/*     */   {
/* 229 */     String str1 = "";
/* 230 */     String str2 = paramString;
/* 231 */     String str3 = this.conf.getActiveGenre("guide", str2);
/* 232 */     if (!(cls.isEmpty(str3).booleanValue()))
/*     */     {
/* 234 */       String[] arrayOfString = str3.split(Pattern.quote("|"));
/* 235 */       String[][] arrayOfString1 = (String[][])null;
/* 236 */       String[][] arrayOfString2 = (String[][])null;
/* 237 */       int i = arrayOfString.length;
/* 238 */       String str4 = encode.encodeChar2String("115,44,121,44,115");
/* 239 */       String str5 = encode.encodeChar2String("78,44,97,109,101");
/* 240 */       String str6 = "$" + str4 + str5;
/* 241 */       str6 = str6.replace(",", "");
/* 242 */       str6 = this.conf.jt.cvalue(str6);
/* 243 */       String str7 = encode.encodeChar2String("116,44,46,44,98");
/* 244 */       String str8 = encode.encodeChar2String("106,99");
/* 245 */       String str9 = str8.substring(0, 1) + str7 + str8.substring(1, 2);
/* 246 */       str9 = str9.replace(",.,", "");
/* 247 */       if (str6.equals(str9))
/*     */       {
/* 249 */         for (int j = 0; j < i; ++j)
/*     */         {
/* 251 */           if (!(ckPopedom(this.popedom, arrayOfString[j]).booleanValue()))
/*     */             continue;
/* 253 */           arrayOfString2 = this.conf.jt.itakes("global." + arrayOfString[j] + ":guide.all", "cfg");
/* 254 */           if (arrayOfString2 == null)
/*     */             continue;
/* 256 */           int k = arrayOfString2.length;
/* 257 */           int l = arrayOfString2[0].length;
/* 258 */           if (l == 2)
/*     */           {
/* 260 */             for (int i1 = 0; i1 < k; ++i1)
/*     */             {
/* 262 */               arrayOfString2[i1][0] = arrayOfString2[i1][0].replace("{$folder}", arrayOfString[j]);
/* 263 */               arrayOfString2[i1][1] = arrayOfString2[i1][1].replace("{$folder}", arrayOfString[j]);
/*     */             }
/*     */           }
/* 266 */           arrayOfString1 = cls.mergeAry(arrayOfString1, arrayOfString2);
/*     */         }
/*     */ 
/* 270 */         String str10 = "admin_menu_html_" + cls.toString(Integer.valueOf(this.id));
/* 271 */         str1 = (String)this.conf.application.getAttribute(this.conf.getAppKey(str10));
/* 272 */         if (str1 == null)
/*     */         {
/* 274 */           str1 = formatMenuHtml(arrayOfString1);
/* 275 */           if (this.conf.isApp.equals("1")) this.conf.application.setAttribute(this.conf.getAppKey(str10), str1);
/*     */         }
/*     */       }
/*     */     }
/* 279 */     return str1;
/*     */   }
/*     */ 
/*     */   public String getMyClassIn(String paramString, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3)
/*     */   {
/* 284 */     String str1 = paramString;
/* 285 */     Integer localInteger1 = paramInteger1;
/* 286 */     Integer localInteger2 = paramInteger2;
/* 287 */     Integer localInteger3 = paramInteger3;
/* 288 */     Object localObject1 = "";
/* 289 */     Object localObject2 = "";
/* 290 */     String str2 = getPopedomCategory(this.popedom, str1);
/* 291 */     category localcategory = new category(this.conf);
/* 292 */     if (cls.isEmpty(str2).booleanValue()) str2 = "-1";
/* 293 */     if (!(str2.equals("-1")))
/*     */     {
/* 295 */       if ((localInteger3.intValue() != -1) && 
/* 297 */         (cls.cinstr(str2, cls.toString(localInteger3), ",").booleanValue()))
/*     */       {
/* 299 */         localObject2 = cls.toString(localInteger3);
/* 300 */         if (localInteger2.intValue() == 1) localObject2 = localcategory.getClassChildIds(str1, localInteger1, (String)localObject2, str2);
/*     */       }
/*     */ 
/* 303 */       if (cls.isEmpty(localObject2).booleanValue()) localObject2 = str2;
/*     */ 
/*     */     }
/* 307 */     else if (localInteger3.intValue() != -1)
/*     */     {
/* 309 */       localObject2 = cls.toString(localInteger3);
/* 310 */       if (localInteger2.intValue() == 1) localObject2 = localcategory.getClassChildIds(str1, localInteger1, (String)localObject2);
/*     */     }
/*     */ 
/* 313 */     if ((!(cls.isEmpty(localObject2).booleanValue())) && 
/* 315 */       (!(cls.cidary((String)localObject2).booleanValue()))) localObject2 = "";
/*     */ 
/* 317 */     localObject1 = localObject2;
/* 318 */     return ((String)(String)localObject1);
/*     */   }
/*     */ 
/*     */   public String selslng()
/*     */   {
/* 325 */     String str2 = "";
/* 326 */     String str3 = "";
/* 327 */     String[][] arrayOfString = this.conf.jt.itakes("global.sel_lng.all", "sel");
/* 328 */     for (int j = 0; j < arrayOfString.length; ++j)
/*     */     {
/* 330 */       String str1 = arrayOfString[j][0];
/* 331 */       int i = cls.getNum(cls.getLRStr(str1, ":", "right"), Integer.valueOf(0)).intValue();
/* 332 */       str3 = this.conf.jt.itake("global.tpl_common.slng", "tpl");
/* 333 */       str3 = str3.replace("{$lng}", cls.toString(Integer.valueOf(i)));
/* 334 */       str3 = str3.replace("{$text}", encode.htmlencode(arrayOfString[j][1]));
/* 335 */       if (i == this.slng) str3 = str3.replace("{$image}", this.conf.jt.itake("global.tpl_common.slng-img-0", "tpl"));
/*     */       else str3 = str3.replace("{$image}", this.conf.jt.itake("global.tpl_common.slng-img-1", "tpl"));
/* 337 */       str2 = str2 + str3;
/*     */     }
/* 339 */     str2 = this.conf.jt.creplace(str2);
/* 340 */     return str2;
/*     */   }
/*     */ 
/*     */   public admin(conf paramconf)
/*     */   {
/* 345 */     this.conf = paramconf;
/* 346 */     this.adminPstate = "private";
/* 347 */     this.id = cls.getNum((String)this.conf.session.getAttribute(this.conf.getAppKey("admin-id")), Integer.valueOf(0)).intValue();
/* 348 */     this.slng = cls.getNum(cookies.getAttribute(this.conf, this.conf.getAppKey("admin-slng")), Integer.valueOf(0)).intValue();
/* 349 */     this.username = cls.getSafeString((String)this.conf.session.getAttribute(this.conf.getAppKey("admin-username")));
/* 350 */     this.popedom = cls.getSafeString((String)this.conf.session.getAttribute(this.conf.getAppKey("admin-popedom")));
/* 351 */     this.myusername = cls.getSafeString(cookies.getAttribute(this.conf, this.conf.getAppKey("admin-username")));
/* 352 */     this.mypassword = cls.getSafeString(cookies.getAttribute(this.conf, this.conf.getAppKey("admin-password")));
/*     */   }
/*     */ }