/*!
 * Copyright (C),2011,Simple.
 * FileName:conf.java
 * Translate By:WangJianQiang		Version:1.0	 	Data:2011/01/13
 * Description: JTBC配置文件，配置系统的一些主要参数
 * Function List:
 * History:
 */

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
  //jtbc.jt对象
  public jt jt;
  //jtbc.common对象
  public common common;
  //HttpSession对象
  public HttpSession session;
  //ServletContext 对象
  public ServletContext application;
  //request
  public HttpServletRequest request;
  //response
  public HttpServletResponse response;
  public String ajaxPreContent;
  //系统名称  jtbc_
  public String appName;
  //admin的文件夹
  public String adminFolder;
  //系统的编码
  public String charset;
  //是否允许转换编码 1是  0否
  public String convert;
  //数据库驱动连接字符串
  public String connStr;
  //数据库类型    2：sqlite
  public String dbtype;
  //默认的语言 chinese
  public String default_language;
  //默认的模板  tpl_default
  public String default_template;
  //默认的主题  default
  public String default_theme;
  //图片的路径  common/images
  public String imagesRoute;
  //是否是系统  1是
  public String isApp;
  public String jtbccinfo;
  public String linkMode;
  public String navSpStr;
  //网站系统的名称,初始值为 JTBC(2.0)
  public String ntitle;
  //页面标题的分割符  -
  public String ntitleSpStr;
  public String[][] njtbcelement;
  public String repath;
  public Object[][] rsAry;
  public Object[][] rsbAry;
  public Object[][] rscAry;
  public Object[][] rstAry;
  public String sysName;
  //   .jtbc
  public String xmlsfx;

  /**
   * 配置文件的无参构照函数
   * 
   */
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
  
  /**
   * 拼装页面的标题
   * 由当前页面标题+分割符+系统标题组成，如："首页 - JTBC(2.0)"
   * @param paramString   当前页面的title
   */
  public void cntitle(String paramString) {
    String str = paramString;
  	//判断系统标题是否为空，不为空就添加 分割符及系统标题
    if (cls.isEmpty(this.ntitle).booleanValue()){
    	this.ntitle = encode.htmlencode(str);
    }else{
    	this.ntitle = encode.htmlencode(str) + this.ntitleSpStr + this.ntitle;
    }
  }

  /**
   * 把字符串编码iso-8859-1转换为设置的编码格式
   * @param paramString   待转换的字符串
   * @return
   */
  public String decodeParameter(String paramString)
  {
    String str = paramString;
    if ((!(cls.isEmpty(str).booleanValue())) && (this.convert.equals("1")))
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

  /**
   * 在参数前添加系统名称  jtbc
   * appName+paramString  如 jtbcroute
   * @param paramString
   * @return    
   */
  public String getAppKey(String paramString)
  {
    String str1 = "";
    String str2 = paramString;
    str1 = this.appName + str2;
    return str1;
  }

  /**
   * 返回当前网站前台选择的语言、模板、风格的名称，未选择时返回默认值。
   * @param paramString
   * @return
   */
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
    if (!(cls.isEmpty(str5).booleanValue())) 
    	str1 = encode.htmlencode(str5);
    else 
    	str1 = encode.htmlencode(str3);
    return str1;
  }

  /**
   * 返回相对于当前地址的相对地址
   * 如{$=getActualRoute("products")}
   * 当在首页模板中使用时返回 products
   * 当在文章模块的模板中使用时返回 ../products
   * @param paramString 路径字符串
   * @return
   */
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

  
  /**
   * 返回相对于当前地址的相对地址
   * @param paramString1   路径字符串
   * @param paramString2   值：greatgrandchild  grandchild  child  node
   * @param paramString3   0或1
   * @return
   */
  public String getActualRoute(String paramString1, String paramString2, String paramString3)
  {
    Object localObject = "";
    String str1 = paramString1;
    String str2 = paramString2;
    String str3 = paramString3;
    String str4 = this.linkMode;
    if ((str3.equals("1")) && (!(cls.isEmpty(str4).booleanValue()))) 
    	localObject = str4 + str1;
    else if (str2.equals("greatgrandchild")) 
    	localObject = "../../../../" + str1;
    else if (str2.equals("grandchild")) 
    	localObject = "../../../" + str1;
    else if (str2.equals("child")) 
    	localObject = "../../" + str1;
    else if (str2.equals("node")) 
    	localObject = "../" + str1;
    else 
    	localObject = str1;
    return ((String)localObject);
  }

  public String getActiveGenre(String paramString1, String paramString2)
  {
    String str1 = paramString1;
    String str2 = paramString2;
    Object localObject = "";
    String str3 = "active_genre_" + str1;
    String str4 = (String)this.application.getAttribute(getAppKey(str3));
    if (!(cls.isEmpty(str4).booleanValue())) { 
    	localObject = str4;
    }else {
      localObject = getActiveGenre(str1, str2, null);
      if (this.isApp.equals("1")) 
    	  this.application.setAttribute(getAppKey(str3), localObject);
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
      if (!(cls.isEmpty(str3).booleanValue())) 
    	  str9 = str3 + "/" + str8;
      str6 = str6 + str9 + "|";
      str7 = getActiveGenre(str1, str2 + str8 + "/", str9);
      if (cls.isEmpty(str7).booleanValue()) 
    	  continue; 
      str6 = str6 + str7 + "|";
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

  /**
   * 获取路径
   * @param paramString  
   * @return
   */
  public String getMapPath(String paramString)
  {
    String str1 = "";
    String str2 = getNURI();
    String str3 = paramString;
    Integer localInteger = Integer.valueOf(0);
    str2 = cls.getLRStr(str2, "/", "leftr");
    do
    {
      if (str3.length() < 3) { 
    	  localInteger = Integer.valueOf(1);
      }
      else {
        String str4 = str3.substring(0, 3);
        if (str4.equals("../"))
        {
          str3 = cls.getLRStr(str3, "../", "rightr");
          str2 = cls.getLRStr(str2, "/", "leftr");
        } else {
          localInteger = Integer.valueOf(1); 
        } 
      }
    }
    while (localInteger.intValue() == 0);
    str1 = str2 + "/" + str3;
    return str1;
  }

  /**
   * 获得请求的路径
   * 在http://localhost:8080/news/main/list.jsp 
   *  返回/main/list.jsp 
   * @return
   */
  public String getNURI()
  {
    String str = "";
    str = this.request.getServletPath();
    return str;
  }

  /**
   * 获得请求的参数
   * 如http://localhost/test.do?a=b&c=d&e=f 
   * 返回 a=b&c=d&e=f 
   * @return
   */
  public String getNURS()
  {
    String str = "";
    str = this.request.getQueryString();
    if (str == null) str = "";
    return str;
  }

  /**
   * 返回请求的路径+参数
   * 如 /main/list.jsp ? a=b&c=d&e=f 
   * @return  
   */
  public String getNURL()
  {
    String str1 = "";
    //获得请求的参数
    String str2 = getNURS();
    //获得请求的路径
    str1 = getNURI();
    if (!(cls.isEmpty(str2).booleanValue())) 
    	str1 = str1 + "?" + str2;
    return str1;
  }

  /**
   * 把请求的全路径截取到只剩项目名
   *相当于 request.getContextPath()
   * @return
   */
  public String getNURLPre()
  {
    String str1 = "";
    //获得请求的全路径 到项目名称  /jtbc/admin/default.jsp
    String str2 = this.request.getRequestURL().toString();
    //  /admin/default.jsp  左边的第一个/后面的截掉   /admin
    String str3 = cls.getLRStr(getNURI(), "/", "leftr");
    // /jtbc/admin
    str1 = cls.getLRStr(str2, "/", "leftr");
    if (!(cls.isEmpty(str3).booleanValue())) 
    	str1 = cls.getLRStr(str1, str3, "leftr");
    return str1;
  }


  /**
   * 由当前的语言获得语言代码如chinese对应了0
   * @return 
   */
  public String getNLng()
  {
    String str = "";
    //getActiveThings("lng")  获得当前系统的语言chinese
    //getLngID(getActiveThings("lng"))  获得系统语言对应的编号0
    str = this.common.getLngID(getActiveThings("lng"));
    if (cls.getNum(str, Integer.valueOf(-1)).intValue() == -1) 
    	str = "0";
    return str;
  }

  /**
   * 不中的干嘛的，返回值目前知道的有root  child  greatgrandchild  grandchild
   * @return
   */
  public String getNroute()
  {
    String str1 = "";
    //str2获得项目名除外的完整路径  /passport/account/interface.jsp 
    String str2 = getNURI();
    //str3把出去jsp外的路径/passport/account转换为64位字符串 L3Bhc3Nwb3J0L2FjY291bnQ=  
    String str3 = encode.base64encode(cls.getLRStr(str2, "/", "leftr").getBytes());
    //str4把匹配右侧的数据截掉  account
    String str4 = cls.getLRStr(cls.getLRStr(str2, "/", "leftr"), "/", "right");
    //如果str4为空就 赋值  :root
    if (cls.isEmpty(str4).booleanValue()) 
    	str4 = ":root";
    str3 = str3 + encode.base64encode(str4.getBytes());
    str1 = getNroute(str3);
    if (cls.isEmpty(str1).booleanValue())
    {
      File localFile1 = new File(this.application.getRealPath(getMapPath("common/jtbc.guide")).toString());
      if (localFile1.exists()) { 
    	  str1 = "root";
      }else {
    	File localObject = new File(this.application.getRealPath(getMapPath("../common/jtbc.guide")).toString());
        if (((File)localObject).exists()) { 
        	str1 = "node";
        }else {
          File localFile2 = new File(this.application.getRealPath(getMapPath("../../common/jtbc.guide")).toString());
          if (localFile2.exists()) { 
        	  str1 = "child";
          }else {
            File localFile3 = new File(this.application.getRealPath(getMapPath("../../../common/jtbc.guide")).toString());
            if (localFile3.exists()) { 
            	str1 = "grandchild";
            } else {
              File localFile4 = new File(this.application.getRealPath(getMapPath("../../../../common/jtbc.guide")).toString());
              if (localFile4.exists()) 
            	  str1 = "greatgrandchild";
            }
          }
        }
      }
      String[][] localObject = new String[1][2];
      localObject[0][0] = str3;
      localObject[0][1] = str1;
      if (this.isApp.equals("1")) 
    	  this.application.setAttribute(getAppKey("route"), cls.mergeAry((String[][])(String[][])this.application.getAttribute(getAppKey("route")), localObject));
    }
    return ((String)str1);
  }

  /**
   * 不中的干嘛的，返回值目前知道的有root  child  greatgrandchild  grandchild
   * @param paramString   64位的字符串
   * @return
   */
  public String getNroute(String paramString)
  {
	//getAppKey("route") 获取系统名称  如  jtbc_route
    String[][] arrayOfString = (String[][])(String[][])this.application.getAttribute(getAppKey("route"));
    String str1 = "";
    if (arrayOfString != null)
    {
      //str2 是传入的64位的字符串
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
    //str2获得项目名除外的完整路径  /passport/account/interface.jsp 
    String str2 = getNURI();
    //截掉/interface.jsp  值为/passport/account
    str2 = cls.getLRStr(str2.toLowerCase(), "/", "leftr");
    //数组长度3 第一位为空
    String[] arrayOfString = str2.split(Pattern.quote("/"));
    //i = 3
    int i = arrayOfString.length;
    String str3 = getNroute();
   
    if (str3.equals("greatgrandchild"))
    {
      if (i >= 4) 
    	  str1 = arrayOfString[(i - 4)] + "/" + arrayOfString[(i - 3)] + "/" + arrayOfString[(i - 2)] + "/" + arrayOfString[(i - 1)];
    }
    else if (str3.equals("grandchild"))
    {
      if (i >= 3) 
    	  str1 = arrayOfString[(i - 3)] + "/" + arrayOfString[(i - 2)] + "/" + arrayOfString[(i - 1)];
    }
    else if (str3.equals("child"))
    {
      if (i >= 2) 
    	  str1 = arrayOfString[(i - 2)] + "/" + arrayOfString[(i - 1)];
    }
    else if ((str3.equals("node")) && (i >= 1)) 
    	str1 = arrayOfString[(i - 1)];
    return str1;
  }

  /**
   * 获取多级反向代理下获取客户端的真实IP地址
   * @return
   */
  public String getRemortIP()
  {
    String str = "";
    str = this.request.getHeader("x-forwarded-for");
    if (str == null) str = this.request.getRemoteAddr();
    return str;
  }
  
  /**
   * 获得请求的URL,如:/news/main/list.jsp?a=b
   * @return
   */
  public String getRequestURL()
  {
    String str1 = "";
    String str2 = getNURS();
    str1 = this.request.getRequestURL().toString();
    if (!(cls.isEmpty(str2).booleanValue())) 
    	str1 = str1 + "?" + str2;
    return str1;
  }

  /**
   * 获取以 GET,POST 方式提交过来的未经过处理的参数的值。
   * @param paramString  名称字符串
   * @return
   */
  public String getRequestParameter(String paramString)
  {
    String str = paramString;
    str = decodeParameter(this.request.getParameter(str));
    return str;
  }

  
  /**
   * 获取以 GET,POST 方式提交过来的未经过处理的参数的多个值，拼凑成字符串
   * @param paramString  名称字符串
   * @return
   */
  public String getRequestParameters(String paramString)
  {
    String str1 = "";
    String str2 = paramString;
    String[] arrayOfString = this.request.getParameterValues(str2);
    if (arrayOfString != null)
    {
      for (int i = 0; i < arrayOfString.length; ++i) 
    	  str1 = str1 + arrayOfString[i] + ",";
      str1 = cls.getLRStr(str1, ",", "leftr");
    }
    str1 = decodeParameter(str1);
    return str1;
  }
  
  /**
   * 获取以 GET,POST 方式提交过来的经过(URLDecoder.decode)处理的参数的值。
   * @param paramString 名称字符串
   * @return
   */
  public String getRequestUsParameter(String paramString)
  {
    String str = paramString;
    str = getRequestParameter(str);
    try
    {
      str = URLDecoder.decode(str, this.charset);
    } catch (Exception localException) {
      str = "";
    }
    return str;
  }

  /**
   * 获取以 GET,POST 方式提交过来的经过(URLDecoder.decode)处理的参数值。
   * @param paramString 名称字符串
   * @return
   */
  public String getRequestUsParameters(String paramString)
  {
    String str = paramString;
    str = getRequestParameters(str);
    try
    {
      str = URLDecoder.decode(str, this.charset);
    } catch (Exception localException) {
      str = ""; 
    }
    return str;
  }

  /**
   * 转换字符串为
   * @param paramString     url字符串
   * @return
   */
  public String urlencode(String paramString)
  {
    String str = paramString;
    try
    {
      str = URLEncoder.encode(str, this.charset);
    } catch (Exception localException) {
      str = ""; 
    }
    return str;
  }

  /**
   * 初始化配置文件的属性
   * @param paramObject1  request
   * @param paramObject2  response  
   */
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
//System.out.println("---"+this.appName +" " + this.adminFolder +"  "+this.charset+" "+this.convert);
//System.out.println(this.connStr +" " + this.dbtype +"  "+this.default_language+" "+this.default_template);
//System.out.println(this.default_theme +" " + this.imagesRoute +"  "+this.isApp+" "+this.linkMode);
//System.out.println(this.repath +" " + this.xmlsfx );

    this.jt = new jt(this);
    this.common = new common(this);
  }
  
  
}