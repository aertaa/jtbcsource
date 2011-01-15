 package jtbc;
 
 import javax.servlet.http.HttpSession;
 import jtbc.dbc.dbc;
 
 public class account
 {
   public conf conf;
   public dbcache dbcache;
   public String ngenre;
   public String nuserid;
   public String nusername;
   public String ndatabase;
 
   public void Init()
   {
     Init("");
   }
 
   public void Init(String paramString)
   {
     String str = paramString;
     if (!(cls.isEmpty(str).booleanValue())) this.ngenre = str;
     else this.ngenre = this.conf.getNGenre();
     this.ndatabase = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.ndatabase", "cfg"));
   }
 
   public void UserInit()
   {
     this.nuserid = ((String)this.conf.session.getAttribute(this.conf.getAppKey(this.ngenre + "-nuserid")));
    this.nusername = ((String)this.conf.session.getAttribute(this.conf.getAppKey(this.ngenre + "-nusername")));
   }
 
   public void Logout()
   {
     cookies.removeAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-userid"));
     cookies.removeAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-username"));
     cookies.removeAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-password"));
     this.conf.session.removeAttribute(this.conf.getAppKey(this.ngenre + "-nuserid"));
     this.conf.session.removeAttribute(this.conf.getAppKey(this.ngenre + "-nusername"));
   }
 
   public void updateProperty(String paramString1, String paramString2, String paramString3)
   {
     String str1 = paramString1;
     String str2 = paramString2;
     String str3 = paramString3;
     updateProperty(str1, str2, str3, this.nuserid);
   }
 
   public void updateProperty(String paramString1, String paramString2, String paramString3, String paramString4)
   {
     String str1 = paramString1;
     String str2 = paramString2;
     String str3 = paramString3;
     String str4 = paramString4;
    Integer localInteger = cls.getNum(str4, Integer.valueOf(-1));
     if (localInteger.intValue() == -1)
       return;
     String str5 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.ndatabase", "cfg"));
     String str6 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.nfpre", "cfg"));
     String str7 = cls.cfnames(str6, "id");
     dbc localdbc = db.newInstance(this.conf);
     if (str3.equals("0")) localdbc.Execute("update " + str5 + " set " + cls.cfnames(str6, str1) + "=" + cls.cfnames(str6, str1) + "+" + cls.getNum(str2, Integer.valueOf(0)) + " where " + str7 + "=" + localInteger);
     else if (str3.equals("1")) localdbc.Execute("update " + str5 + " set " + cls.cfnames(str6, str1) + "=" + cls.getNum(str2, Integer.valueOf(0)) + " where " + str7 + "=" + localInteger);
     else if (str3.equals("2")) localdbc.Execute("update " + str5 + " set " + cls.cfnames(str6, str1) + "='" + encode.addslashes(str2) + "' where " + str7 + "=" + localInteger);
     this.dbcache.deleteCache(str5, str6, localInteger);
   }
 
   public void setPasswordCookies(String paramString)
   {
     String str = cls.getSafeString(paramString);
     cookies.setAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-password"), str);
   }
 
   public Boolean checkUsername(String paramString1, String paramString2, String paramString3)
   {
     Boolean localBoolean = Boolean.valueOf(false);
     Integer localInteger = cls.getNum(paramString1, Integer.valueOf(-1));
     String str1 = cls.getSafeString(paramString2);
     String str2 = cls.getSafeString(paramString3);
     if (localInteger.intValue() != -1)
     {
      dbc localdbc = db.newInstance(this.conf);
       String str3 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.ndatabase", "cfg"));
       String str4 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.nfpre", "cfg"));
       String str5 = cls.cfnames(str4, "id");
       String str6 = "select * from " + str3 + " where " + str5 + "=" + localInteger + " and " + cls.cfnames(str4, "lock") + "=0";
       Object[] arrayOfObject = localdbc.getDataAry(str6);
       if (arrayOfObject != null)
       {
         Object[][] arrayOfObject1 = (Object[][])(Object[][])arrayOfObject[0];
         String str7 = (String)localdbc.getValue(arrayOfObject1, cls.cfnames(str4, "username"));
         String str8 = (String)localdbc.getValue(arrayOfObject1, cls.cfnames(str4, "password"));
         if ((str7.equals(str1)) && (str8.equals(str2))) localBoolean = Boolean.valueOf(true);
       }
     }
     return localBoolean;
   }
 
   public Boolean checkUserLogin()
   {
     Boolean localBoolean = Boolean.valueOf(false);
     if ((!(cls.isEmpty(this.nuserid).booleanValue())) && (!(cls.isEmpty(this.nusername).booleanValue()))) { localBoolean = Boolean.valueOf(true);
     }
     else {
       String str1 = cls.getSafeString(cookies.getAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-userid")));
       String str2 = cls.getSafeString(cookies.getAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-username")));
       String str3 = cls.getSafeString(cookies.getAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-password")));
       if ((!(cls.isEmpty(str1).booleanValue())) && (!(cls.isEmpty(str2).booleanValue())) && (!(cls.isEmpty(str3).booleanValue())) && 
         (checkUsername(str1, str2, str3).booleanValue()))
       {
         this.nuserid = str1;
         this.nusername = str2;
         this.conf.session.setAttribute(this.conf.getAppKey(this.ngenre + "-nuserid"), str1);
         this.conf.session.setAttribute(this.conf.getAppKey(this.ngenre + "-nusername"), str2);
         localBoolean = Boolean.valueOf(true);
       }
     }
 
     return localBoolean;
   }
 
   public String cfname(String paramString)
   {
     String str1 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.nfpre", "cfg"));
     String str2 = str1 + paramString;
     return str2;
   }
 
   public String getDefMenuHtml()
   {
     String str1 = "";
 
     str1 = this.conf.jt.itake("global." + this.ngenre + ":default.data_defmenu", "tpl");
     String str3 = "";
     String str2 = cls.ctemplate(str1, "{@}");
     String[][] arrayOfString = this.conf.jt.itakes("global." + this.ngenre + ":defmenu.all", "lng");
     for (int i = 0; i < arrayOfString.length; ++i)
     {
       String str5 = arrayOfString[i][0];
       String str6 = arrayOfString[i][1];
       str5 = str5.replace("{$-genre}", this.ngenre);
       str5 = this.conf.jt.creplace(str5);
       String str4 = str2;
       str4 = str4.replace("{$href}", encode.htmlencode(str5));
       str4 = str4.replace("{$text}", encode.htmlencode(str6));
       str3 = str3 + str4;
     }
     str1 = cls.ctemplates(str1, "{@}", str3);
     str1 = str1.replace("{$-genre}", this.ngenre);
     str1 = str1.replace("{$-nuserid}", cls.getString(this.nuserid));
     str1 = str1.replace("{$-nusername}", cls.getString(this.nusername));
     str1 = this.conf.jt.creplace(str1);
     return str1;
   }
 
   public Integer getUserID(String paramString)
   {
     Integer localInteger = Integer.valueOf(0);
     String str1 = cls.getSafeString(paramString);
     String str2 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.ndatabase", "cfg"));
     String str3 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.nfpre", "cfg"));
     String str4 = cls.cfnames(str3, "id");
     String str5 = "select * from " + str2 + " where " + cls.cfnames(str3, "username") + "='" + str1 + "'";
     dbc localdbc = db.newInstance(this.conf);
     Object[] arrayOfObject = localdbc.getDataAry(str5);
     if (arrayOfObject != null)
     {
       Object[][] arrayOfObject1 = (Object[][])(Object[][])arrayOfObject[0];
       localInteger = cls.getNum(cls.toString(localdbc.getValue(arrayOfObject1, str4)), Integer.valueOf(0));
     }
     return localInteger;
   }
 
   public Object[][] getUserAry(String paramString)
   {
     Object[][] arrayOfObject = (Object[][])null;
     Integer localInteger = cls.getNum(paramString, Integer.valueOf(-1));
     String str1 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.ndatabase", "cfg"));
    String str2 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.nfpre", "cfg"));
     if ((!(cls.isEmpty(str1).booleanValue())) && (localInteger.intValue() != -1)) arrayOfObject = this.dbcache.getAry(str1, str2, localInteger);
     return arrayOfObject;
   }
 
   public Object getUserInfo(String paramString)
   {
     Object localObject = null;
     String str = cls.getString(paramString);
     localObject = getUserInfo(str, this.nuserid);
     return localObject;
   }
 
   public Object getUserInfo(String paramString1, String paramString2)
   {
     Object localObject = null;
     String str1 = cls.getString(paramString1);
     String str2 = cls.getString(paramString2);
     Object[][] arrayOfObject = getUserAry(str2);
     if (arrayOfObject != null) localObject = cls.getAryValue(arrayOfObject, cfname(str1));
     return localObject;
   }
 
   public Boolean Login(String paramString1, String paramString2)
   {
     Boolean localBoolean = Boolean.valueOf(false);
     String str1 = paramString1;
     String str2 = paramString2;
     localBoolean = Login(str1, str2, "0");
     return localBoolean;
   }
 
   public Boolean Login(String paramString1, String paramString2, String paramString3)
   {
     Boolean localBoolean = Boolean.valueOf(false);
     Logout();
     String str1 = cls.getSafeString(paramString1);
     String str2 = encode.md5(cls.getSafeString(paramString2).getBytes());
     Integer localInteger = cls.getNum(paramString3, Integer.valueOf(0));
     dbc localdbc = db.newInstance(this.conf);
     String str3 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.ndatabase", "cfg"));
     String str4 = cls.getString(this.conf.jt.itake("global." + this.ngenre + ":config.nfpre", "cfg"));
     String str5 = cls.cfnames(str4, "id");
     String str6 = "select * from " + str3 + " where " + cls.cfnames(str4, "username") + "='" + str1 + "' and " + cls.cfnames(str4, "password") + "='" + str2 + "' and " + cls.cfnames(str4, "lock") + "=0";
     Object[] arrayOfObject = localdbc.getDataAry(str6);
     if (arrayOfObject != null)
     {
       Object[][] arrayOfObject1 = (Object[][])(Object[][])arrayOfObject[0];
       String str7 = cls.toString(localdbc.getValue(arrayOfObject1, str5));
       if (localInteger.intValue() == 0)
       {
         cookies.setAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-userid"), str7);
         cookies.setAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-username"), str1);
        cookies.setAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-password"), str2);
       }
       else
       {
         cookies.setAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-userid"), str7, Integer.valueOf(31536000));
         cookies.setAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-username"), str1, Integer.valueOf(31536000));
         cookies.setAttribute(this.conf, this.conf.getAppKey(this.ngenre + "-password"), str2, Integer.valueOf(31536000));
       }
       this.conf.session.setAttribute(this.conf.getAppKey(this.ngenre + "-nuserid"), str7);
       this.conf.session.setAttribute(this.conf.getAppKey(this.ngenre + "-nusername"), str1);
       localdbc.Execute("update " + str3 + " set " + cls.cfnames(str4, "prelasttime") + "=" + cls.cfnames(str4, "lasttime") + " where " + str5 + "=" + str7);
       localdbc.Execute("update " + str3 + " set " + cls.cfnames(str4, "lasttime") + "='" + cls.getDate() + "' where " + str5 + "=" + str7);
       localBoolean = Boolean.valueOf(true);
     }
     return localBoolean;
   }
 
   public account(conf paramconf)
   {
     this.conf = paramconf;
     this.dbcache = new dbcache(this.conf);
   }
 }