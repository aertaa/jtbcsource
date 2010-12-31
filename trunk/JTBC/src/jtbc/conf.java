package jtbc;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class conf
{
  public jt jt;
  public common common;
  public HttpSession session;
  public ServletContext application;
  public HttpServletRequest request;
  public HttpServletResponse response;
  public String ajaxPreContent;
  public String appName;
  public String adminFolder;
  public String charset;
  public String convert;
  public String connStr;
  public String dbtype;
  public String default_language;
  public String default_template;
  public String default_theme;
  public String imagesRoute;
  public String isApp;
  public String jtbccinfo;
  public String linkMode;
  public String navSpStr;
  public String ntitle;
  public String ntitleSpStr;
  public String[][] njtbcelement;
  public String repath;
  public Object[][] rsAry;
  public Object[][] rsbAry;
  public Object[][] rscAry;
  public Object[][] rstAry;
  public String sysName;
  public String xmlsfx;

  public conf()
  {
    this.ajaxPreContent = "<!--jtbc-->";
    this.appName = "";
    this.adminFolder = "";
    this.charset = "";
    this.convert = "";
    this.connStr = "";
    this.dbtype = "";
    this.default_language = "";
    this.default_template = "";
    this.default_theme = "";
    this.imagesRoute = "";
    this.isApp = "";
    this.jtbccinfo = "<!--jtbccinfo-->";
    this.linkMode = "";
    this.navSpStr = "";
    this.ntitle = "";
    this.ntitleSpStr = "";
    this.njtbcelement = ((String[][])null);
    this.repath = "";
    this.rsAry = ((Object[][])null);
    this.rsbAry = ((Object[][])null);
    this.rscAry = ((Object[][])null);
    this.rstAry = ((Object[][])null);
    this.sysName = "jtbc";
    this.xmlsfx = "";
  }

  public void cntitle(String paramString) {
    String str = paramString;
    if (cls.isEmpty(this.ntitle).booleanValue()) this.ntitle = encode.htmlencode(str);
    else this.ntitle = encode.htmlencode(str) + this.ntitleSpStr + this.ntitle;
  }

  public String decodeParameter(String paramString)
  {
    String str = paramString;
    if ((!(cls.isEmpty(str).booleanValue())) && 
      (this.convert.equals("1")))
    {
      try
      {
        str = new String(str.getBytes("iso-8859-1"), this.charset);
      }
      catch (Exception localException) {
      }
    }
    return str;
  }

  public String getAppKey(String paramString)
  {
    String str1 = "";
    String str2 = paramString;
    str1 = this.appName + str2;
    return str1;
  }

  public String getActiveThings(String paramString)
  {
    String str1 = "";
    String str2 = "";
    String str3 = "";
    String str4 = paramString;
    if (str4.equals("lng"))
    {
      str2 = "language";
      str3 = this.default_language;
    }
    else if (str4.equals("sel"))
    {
      str2 = "language";
      str3 = this.default_language;
    }
    else if (str4.equals("tpl"))
    {
      str2 = "template";
      str3 = this.default_template;
    }
    else if (str4.equals("theme"))
    {
      str2 = "theme";
      str3 = this.default_theme;
    }
    else
    {
      str2 = "language";
      str3 = this.default_language;
    }
    String str5 = cookies.getAttribute(this, getAppKey("config-" + str2));
    str5 = cls.getString(str5);
    if (!(cls.isEmpty(str5).booleanValue())) str1 = encode.htmlencode(str5);
    else str1 = encode.htmlencode(str3);
    return str1;
  }

  public String getActualRoute(String paramString)
  {
    String str1 = "";
    String str2 = paramString;
    str1 = getActualRoute(str2, getNroute());
    return str1;
  }

  public String getActualRouteB(String paramString)
  {
    String str1 = "";
    String str2 = paramString;
    str1 = getActualRoute(str2, getNroute(), "0");
    return str1;
  }

  public String getActualRoute(String paramString1, String paramString2)
  {
    String str1 = "";
    String str2 = paramString1;
    String str3 = paramString2;
    str1 = getActualRoute(str2, str3, "1");
    return str1;
  }

  public String getActualRoute(String paramString1, String paramString2, String paramString3)
  {
    Object localObject = "";
    String str1 = paramString1;
    String str2 = paramString2;
    String str3 = paramString3;
    String str4 = this.linkMode;
    if ((str3.equals("1")) && (!(cls.isEmpty(str4).booleanValue()))) localObject = str4 + str1;
    else if (str2.equals("greatgrandchild")) localObject = "../../../../" + str1;
    else if (str2.equals("grandchild")) localObject = "../../../" + str1;
    else if (str2.equals("child")) localObject = "../../" + str1;
    else if (str2.equals("node")) localObject = "../" + str1;
    else localObject = str1;

    return ((String)localObject);
  }

  public String getActiveGenre(String paramString1, String paramString2)
  {
    String str1 = paramString1;
    String str2 = paramString2;
    Object localObject = "";
    String str3 = "active_genre_" + str1;
    String str4 = (String)this.application.getAttribute(getAppKey(str3));
    if (!(cls.isEmpty(str4).booleanValue())) { localObject = str4;
    }
    else {
      localObject = getActiveGenre(str1, str2, null);
      if (this.isApp.equals("1")) this.application.setAttribute(getAppKey(str3), localObject);
    }
    return ((String)localObject);
  }

  public String getActiveGenre(String paramString1, String paramString2, String paramString3)
  {
    String str1 = paramString1;
    String str2 = paramString2;
    String str3 = paramString3;
    String str4 = str1;
    String str5 = "";
    String str6 = "";
    String str7 = "";
    String str8 = "";
    String str9 = "";
    File localFile = new File(this.application.getRealPath(getMapPath(str2)).toString());
    File[] arrayOfFile = localFile.listFiles();
    for (int i = 0; i < arrayOfFile.length; ++i)
    {
      if (!(arrayOfFile[i].isDirectory()))
        continue;
      str8 = arrayOfFile[i].getName();
      if ((str8.indexOf("=") != -1) || (str8.indexOf("&") != -1) || (str8.indexOf("'") != -1))
        continue;
      str5 = this.jt.getXRootAtt(str2 + str8 + "/common/" + str4 + this.xmlsfx, "mode");
      if (cls.isEmpty(str5).booleanValue())
        continue;
      str9 = str8;
      if (!(cls.isEmpty(str3).booleanValue())) str9 = str3 + "/" + str8;
      str6 = str6 + str9 + "|";
      str7 = getActiveGenre(str1, str2 + str8 + "/", str9);
      if (cls.isEmpty(str7).booleanValue()) continue; str6 = str6 + str7 + "|";
    }

    str6 = cls.getLRStr(str6, "|", "leftr");
    str6 = getActiveGenreOrdered(str3, str6);
    return str6;
  }

  public String getActiveGenreOrdered(String paramString1, String paramString2)
  {
    String str1 = paramString1;
    String str2 = paramString2;
    Object localObject = "";
    if (!(cls.isEmpty(str2).booleanValue()))
    {
      String str3 = "";
      if (cls.isEmpty(str1).booleanValue()) str3 = this.jt.itake("global.config.genre-order", "cfg");
      else str3 = this.jt.itake("global." + str1 + ":config.genre-order", "cfg");
      if (!(cls.isEmpty(str3).booleanValue()))
      {
        String str4 = "";
        String str5 = "";
        String str6 = "";
        String[] arrayOfString1 = str3.split(Pattern.quote(","));
        String[] arrayOfString2 = str2.split(Pattern.quote("|"));
        for (int i = 0; i < arrayOfString1.length; ++i)
        {
          str5 = arrayOfString1[i];
          if (!(cls.isEmpty(str1).booleanValue())) str5 = str1 + "/" + str5;
          if (!(cls.cinstr(str2, str5, "|").booleanValue()))
            continue;
          for (int j = 0; j < arrayOfString2.length; ++j)
          {
            str4 = arrayOfString2[j];
            if (!(str4.equals(str5))) continue; str6 = str6 + str4 + "|";
          }
        }

        for (int i = 0; i < arrayOfString2.length; ++i)
        {
          if (cls.cinstr(str6, arrayOfString2[i], "|").booleanValue()) continue; str6 = str6 + arrayOfString2[i] + "|";
        }
        str6 = cls.getLRStr(str6, "|", "leftr");
        localObject = str6;
      } else {
        localObject = str2; }
    }
    return ((String)localObject);
  }

  public String getMapPath(String paramString)
  {
    String str1 = "";
    String str2 = getNURI();
    String str3 = paramString;
    Integer localInteger = Integer.valueOf(0);
    str2 = cls.getLRStr(str2, "/", "leftr");
    do
    {
      if (str3.length() < 3) { localInteger = Integer.valueOf(1);
      }
      else {
        String str4 = str3.substring(0, 3);
        if (str4.equals("../"))
        {
          str3 = cls.getLRStr(str3, "../", "rightr");
          str2 = cls.getLRStr(str2, "/", "leftr");
        } else {
          localInteger = Integer.valueOf(1); } }
    }
    while (localInteger.intValue() == 0);
    str1 = str2 + "/" + str3;
    return str1;
  }

  public String getNURI()
  {
    String str = "";
    str = this.request.getServletPath();
    return str;
  }

  public String getNURS()
  {
    String str = "";
    str = this.request.getQueryString();
    if (str == null) str = "";
    return str;
  }

  public String getNURL()
  {
    String str1 = "";
    String str2 = getNURS();
    str1 = getNURI();
    if (!(cls.isEmpty(str2).booleanValue())) str1 = str1 + "?" + str2;
    return str1;
  }

  public String getNURLPre()
  {
    String str1 = "";
    String str2 = this.request.getRequestURL().toString();
    String str3 = cls.getLRStr(getNURI(), "/", "leftr");
    str1 = cls.getLRStr(str2, "/", "leftr");
    if (!(cls.isEmpty(str3).booleanValue())) str1 = cls.getLRStr(str1, str3, "leftr");
    return str1;
  }

  public String getNLng()
  {
    String str = "";
    str = this.common.getLngID(getActiveThings("lng"));
    if (cls.getNum(str, Integer.valueOf(-1)).intValue() == -1) str = "0";
    return str;
  }

  public String getNroute()
  {
    String str1 = "";
    String str2 = getNURI();
    String str3 = encode.base64encode(cls.getLRStr(str2, "/", "leftr").getBytes());
    String str4 = cls.getLRStr(cls.getLRStr(str2, "/", "leftr"), "/", "right");
    if (cls.isEmpty(str4).booleanValue()) str4 = ":root";
    str3 = str3 + encode.base64encode(str4.getBytes());
    str1 = getNroute(str3);
    if (cls.isEmpty(str1).booleanValue())
    {
      File localFile1 = new File(this.application.getRealPath(getMapPath("common/jtbc.guide")).toString());
      if (localFile1.exists()) { str1 = "root";
      }
      else {
    	File localObject = new File(this.application.getRealPath(getMapPath("../common/jtbc.guide")).toString());
        if (((File)localObject).exists()) { str1 = "node";
        }
        else {
          File localFile2 = new File(this.application.getRealPath(getMapPath("../../common/jtbc.guide")).toString());
          if (localFile2.exists()) { str1 = "child";
          }
          else {
            File localFile3 = new File(this.application.getRealPath(getMapPath("../../../common/jtbc.guide")).toString());
            if (localFile3.exists()) { str1 = "grandchild";
            }
            else {
              File localFile4 = new File(this.application.getRealPath(getMapPath("../../../../common/jtbc.guide")).toString());
              if (localFile4.exists()) str1 = "greatgrandchild";
            }
          }
        }
      }
      String[][] localObject = new String[1][2];
      localObject[0][0] = str3;
      localObject[0][1] = str1;
      if (this.isApp.equals("1")) this.application.setAttribute(getAppKey("route"), cls.mergeAry((String[][])(String[][])this.application.getAttribute(getAppKey("route")), localObject));
    }
    return ((String)str1);
  }

  public String getNroute(String paramString)
  {
    String[][] arrayOfString = (String[][])(String[][])this.application.getAttribute(getAppKey("route"));
    String str1 = "";
    if (arrayOfString != null)
    {
      String str2 = cls.getString(paramString);
      for (int i = 0; i < arrayOfString.length; ++i)
      {
        if (!(str2.equals(arrayOfString[i][0])))
          continue;
        str1 = arrayOfString[i][1];
        break;
      }
    }

    return str1;
  }

  public String getNGenre()
  {
    String str1 = "";
    String str2 = getNURI();
    str2 = cls.getLRStr(str2.toLowerCase(), "/", "leftr");
    String[] arrayOfString = str2.split(Pattern.quote("/"));
    int i = arrayOfString.length;
    String str3 = getNroute();
    if (str3.equals("greatgrandchild"))
    {
      if (i >= 4) str1 = arrayOfString[(i - 4)] + "/" + arrayOfString[(i - 3)] + "/" + arrayOfString[(i - 2)] + "/" + arrayOfString[(i - 1)];
    }
    else if (str3.equals("grandchild"))
    {
      if (i >= 3) str1 = arrayOfString[(i - 3)] + "/" + arrayOfString[(i - 2)] + "/" + arrayOfString[(i - 1)];
    }
    else if (str3.equals("child"))
    {
      if (i >= 2) str1 = arrayOfString[(i - 2)] + "/" + arrayOfString[(i - 1)];
    }
    else if ((str3.equals("node")) && 
      (i >= 1)) str1 = arrayOfString[(i - 1)];

    return str1;
  }

  public String getRemortIP()
  {
    String str = "";
    str = this.request.getHeader("x-forwarded-for");
    if (str == null) str = this.request.getRemoteAddr();
    return str;
  }

  public String getRequestURL()
  {
    String str1 = "";
    String str2 = getNURS();
    str1 = this.request.getRequestURL().toString();
    if (!(cls.isEmpty(str2).booleanValue())) str1 = str1 + "?" + str2;
    return str1;
  }

  public String getRequestParameter(String paramString)
  {
    String str = paramString;
    str = decodeParameter(this.request.getParameter(str));
    return str;
  }

  public String getRequestParameters(String paramString)
  {
    String str1 = "";
    String str2 = paramString;
    String[] arrayOfString = this.request.getParameterValues(str2);
    if (arrayOfString != null)
    {
      for (int i = 0; i < arrayOfString.length; ++i) str1 = str1 + arrayOfString[i] + ",";
      str1 = cls.getLRStr(str1, ",", "leftr");
    }
    str1 = decodeParameter(str1);
    return str1;
  }

  public String getRequestUsParameter(String paramString)
  {
    String str = paramString;
    str = getRequestParameter(str);
    try
    {
      str = URLDecoder.decode(str, this.charset);
    } catch (Exception localException) {
      str = ""; }
    return str;
  }

  public String getRequestUsParameters(String paramString)
  {
    String str = paramString;
    str = getRequestParameters(str);
    try
    {
      str = URLDecoder.decode(str, this.charset);
    } catch (Exception localException) {
      str = ""; }
    return str;
  }

  public String urlencode(String paramString)
  {
    String str = paramString;
    try
    {
      str = URLEncoder.encode(str, this.charset);
    } catch (Exception localException) {
      str = ""; }
    return str;
  }

  public void Init(Object paramObject1, Object paramObject2)
  {
    Object localObject1 = paramObject1;
    Object localObject2 = paramObject2;
    this.request = ((HttpServletRequest)localObject1);
    this.response = ((HttpServletResponse)localObject2);
    this.session = this.request.getSession();
    this.application = this.session.getServletContext();

    this.appName = this.application.getInitParameter("appName");
    this.adminFolder = this.application.getInitParameter("adminFolder");
    this.charset = this.application.getInitParameter("charset");
    this.convert = this.application.getInitParameter("convert");
    this.connStr = this.application.getInitParameter("connStr");
    this.dbtype = this.application.getInitParameter("dbtype");
    this.default_language = this.application.getInitParameter("default_language");
    this.default_template = this.application.getInitParameter("default_template");
    this.default_theme = this.application.getInitParameter("default_theme");
    this.imagesRoute = this.application.getInitParameter("imagesRoute");
    this.isApp = this.application.getInitParameter("isApp");
    this.linkMode = this.application.getInitParameter("linkMode");
    this.repath = this.application.getInitParameter("repath");
    this.xmlsfx = this.application.getInitParameter("xmlsfx");

    this.jt = new jt(this);
    this.common = new common(this);
  }
}