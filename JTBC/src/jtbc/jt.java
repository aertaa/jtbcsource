package jtbc;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class jt
{
  public conf conf;
/**
 * 返回参数具体代表的信息
 * 参数分为两种
 * 一种是用引号表示的字符串
 * 另一种是JTBC语法中定义的变量和函数等
 * 例如$nuri转换为/passport/account/interface.jsp
 * @param paramString
 * @return
 */
public String cparameter(String paramString)
  {
     String str1 = "";
     String str2 = paramString;
     str2 = str2.trim();
     if (str2.substring(0, 1).equals("\""))
    {
				//取得最后一个双引号内的内容
       str1 = cls.getLRStr(cls.getLRStr(str2, "\"", "rightr"), "\"", "leftr");
    }
    else
    {
       str2 = str2.replace("'", "\"");
       str1 = cvalue(str2);
    }
     return str1;
  }
/**
 * paramString表示一个函数
 * 该方法用于解析函数的意义
 * 例如xx(xx,xx,xx)或将$nuri转意
 * 然后将对应的结果作为字符串返回
 * @param paramString
 * @return String
 */
public String cvalue(String paramString)
  {
     String str1 = paramString;

     if (str1 != "")
    {
       String str2 = cls.getLRStr(str1, "(", "left");//取得第一个(之前的内容
       String str3 = cls.getLRStr(cls.getLRStr(str1, "(", "rightr"), ")", "leftr");//取得最后的()内的内容
       String[] arrayOfString = fixParameterAry(str3.split(Pattern.quote(",")));
       Integer localInteger = Integer.valueOf(arrayOfString.length);
       if (str2.equals("$admin.theme")) { str1 = this.conf.common.getAdminTheme();
       } else if (str2.equals("$adminFolder")) { str1 = this.conf.adminFolder;
       } else if (str2.equals("$charset")) { str1 = this.conf.charset;
       } else if (str2.equals("$images")) { str1 = this.conf.imagesRoute;
       } else if (str2.equals("$global.images")) { str1 = this.conf.getActualRoute(this.conf.imagesRoute);
       } else if (str2.equals("$ngenre")) { str1 = this.conf.getNGenre();
       } else if (str2.equals("$nlng")) { str1 = this.conf.getNLng();
       } else if (str2.equals("$ntitle")) { str1 = this.conf.ntitle;
       } else if (str2.equals("$navSpStr")) { str1 = this.conf.navSpStr;
       } else if (str2.equals("$now")) { str1 = cls.getDate();
       } else if (str2.equals("$nurs")) { str1 = this.conf.getNURS();
       } else if (str2.equals("$nuri")) { str1 = this.conf.getNURI();
       } else if (str2.equals("$nurl")) { str1 = this.conf.getNURL();
       } else if (str2.equals("$nurlpre")) { str1 = this.conf.getNURLPre();
       } else if (str2.equals("$nuserip")) { str1 = this.conf.getRemortIP();
       } else if (str2.equals("$sysName")) { str1 = this.conf.sysName;
       } else if (str2.equals("base64encode")) { str1 = encode.base64encode(cparameter(str3).getBytes());
       } else if (str2.equals("concat"))
      {
         if (localInteger.intValue() == 2) str1 = cls.concat(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
      }
       else if (str2.equals("curl"))
      {
         if (localInteger.intValue() == 2) str1 = this.conf.common.curl(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
      }
       else if (str2.equals("cdatadecode")) { str1 = encode.cdatadecode(cparameter(str3));
       } else if (str2.equals("crValcodeTpl")) { str1 = this.conf.common.crValcodeTpl(cparameter(str3));
       } else if (str2.equals("encodeArticle")) { str1 = encode.encodeArticle(cparameter(str3));
       } else if (str2.equals("formatByte")) { str1 = cls.formatByte(cparameter(str3));
       } else if (str2.equals("formatDate"))
      {
         if (localInteger.intValue() == 1) str1 = cls.formatDate(cparameter(str3));
         if (localInteger.intValue() == 2) str1 = cls.formatDate(cparameter(arrayOfString[0]), cls.getNum(cparameter(arrayOfString[1])));
      }
       else if (str2.equals("formatText"))
      {
         if (localInteger.intValue() == 3) str1 = cls.formatText(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]));
      }
       else if (str2.equals("formatTextLine"))
      {
         if (localInteger.intValue() == 2) str1 = cls.formatTextLine(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
      }
       else if (str2.equals("formatUnixStampDate")) { str1 = cls.formatUnixStampDate(cls.getNum64(cparameter(str3), Long.valueOf(0L)));
       } else if (str2.equals("getActualRoute")) { str1 = this.conf.getActualRoute(cparameter(str3));
       } else if (str2.equals("getActualRouteB")) { str1 = this.conf.getActualRouteB(cparameter(str3));
       } else if (str2.equals("getActiveThings")) { str1 = this.conf.getActiveThings(cparameter(str3));
       } else if (str2.equals("getClassText"))
      {
         if (localInteger.intValue() == 3)
        {
           category localcategory = new category(this.conf);
           str1 = localcategory.getClassText(cparameter(arrayOfString[0]), cls.getNum(cparameter(arrayOfString[1]), Integer.valueOf(0)), cls.getNum(cparameter(arrayOfString[2]), Integer.valueOf(0)));
        }
      }
       else if (str2.equals("getCuteContent"))
      {
         if (localInteger.intValue() == 2) str1 = this.conf.common.getCuteContent(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
      }
       else if (str2.equals("getCuteContentCount")) { str1 = this.conf.common.getCuteContentCount(cparameter(str3));
       } else if (str2.equals("getLeft"))
      {
         if (localInteger.intValue() == 2) str1 = cls.getLeft(cparameter(arrayOfString[0]), cls.getNum(cparameter(arrayOfString[1]), Integer.valueOf(0)));
         if (localInteger.intValue() == 3) str1 = cls.getLeft(cparameter(arrayOfString[0]), cls.getNum(cparameter(arrayOfString[1]), Integer.valueOf(0)), cparameter(arrayOfString[2]));
      }
       else if (str2.equals("getLeftB"))
      {
         if (localInteger.intValue() == 2) str1 = cls.getLeftB(cparameter(arrayOfString[0]), cls.getNum(cparameter(arrayOfString[1]), Integer.valueOf(0)));
         if (localInteger.intValue() == 3) str1 = cls.getLeftB(cparameter(arrayOfString[0]), cls.getNum(cparameter(arrayOfString[1]), Integer.valueOf(0)), cparameter(arrayOfString[2]));
      }
       else if (str2.equals("getNum"))
      {
         if (localInteger.intValue() == 2) str1 = cls.toString(cls.getNum(cparameter(arrayOfString[0]), cls.getNum(cparameter(arrayOfString[1]), Integer.valueOf(0))));
      }
       else if (str2.equals("getLRStr"))
      {
         if (localInteger.intValue() == 3) str1 = cls.getLRStr(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]));
      }
       else if (str2.equals("getRsValue"))
      {
         if (localInteger.intValue() == 2) str1 = this.conf.common.getRsValue(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
      }
       else if (str2.equals("getString")) { str1 = cls.getString(cparameter(str3));
       } else if (str2.equals("getSafeString")) { str1 = cls.getSafeString(cparameter(str3));
       } else if (str2.equals("getRequestParameter")) { str1 = this.conf.getRequestParameter(cparameter(str3));
       } else if (str2.equals("getRequestParameters")) { str1 = this.conf.getRequestParameters(cparameter(str3));
       } else if (str2.equals("getRequestUsParameter")) { str1 = this.conf.getRequestUsParameter(cparameter(str3));
       } else if (str2.equals("getRequestUsParameters")) { str1 = this.conf.getRequestUsParameters(cparameter(str3));
       } else if (str2.equals("getSearchOptions")) { str1 = this.conf.common.getSearchOptions(cparameter(str3));
       } else if (str2.equals("getSwitchOptions")) { str1 = this.conf.common.getSwitchOptions(cparameter(str3));
       } else if (str2.equals("htmlencode"))
      {
         if (localInteger.intValue() == 1) str1 = encode.htmlencode(cparameter(str3));
         if (localInteger.intValue() == 2) str1 = encode.htmlencode(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
      }
       else if (str2.equals("itransfer")) { str1 = this.conf.common.itransfer(cparameter(str3));
       } else if (str2.equals("isort")) { str1 = this.conf.common.isort(cparameter(str3));
       } else if (str2.equals("inavigation")) { str1 = this.conf.common.inavigation(cparameter(str3));
       } else if (str2.equals("iurl")) { str1 = this.conf.common.iurl(cparameter(str3));
       } else if (str2.equals("itake"))
      {
         if (localInteger.intValue() == 2) str1 = itake(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
         if (localInteger.intValue() == 3) str1 = itake(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]));
         if (localInteger.intValue() == 4) str1 = itake(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]), cparameter(arrayOfString[3]));
      }
       else if (str2.equals("ireplace"))
      {
         if (localInteger.intValue() == 2) str1 = ireplace(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
         if (localInteger.intValue() == 3) str1 = ireplace(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]));
      }
       else if (str2.equals("keywordencode")) { str1 = encode.keywordencode(cparameter(str3));
       } else if (str2.equals("keyworddecode")) { str1 = encode.keyworddecode(cparameter(str3));
       } else if (str2.equals("loadEditor"))
      {
         if (localInteger.intValue() == 2) str1 = this.conf.common.loadEditor(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
         if (localInteger.intValue() == 3) str1 = this.conf.common.loadEditor(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]));
         if (localInteger.intValue() == 4) str1 = this.conf.common.loadEditor(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]), cparameter(arrayOfString[3]));
      }
       else if (str2.equals("pagi"))
      {
         if (localInteger.intValue() == 4) str1 = this.conf.common.pagi(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]), cparameter(arrayOfString[3]));
         if (localInteger.intValue() == 5) str1 = this.conf.common.pagi(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]), cparameter(arrayOfString[3]), cparameter(arrayOfString[4]));
      }
       else if (str2.equals("md5")) { str1 = encode.md5(cparameter(str3).getBytes());
       } else if (str2.equals("replace"))
      {
         if (localInteger.intValue() == 3) str1 = cls.replace(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]));
      }
       else if (str2.equals("repathdecode")) { str1 = this.conf.common.repathdecode(cparameter(str3));
       } else if (str2.equals("striptags")) { str1 = filter.striptags(cparameter(str3));
       } else if (str2.equals("selClass"))
      {
         if (localInteger.intValue() == 1) str1 = this.conf.common.selClass(cparameter(str3));
         if (localInteger.intValue() == 2) str1 = this.conf.common.selClass(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]));
      }
       else if (str2.equals("urlencode")) { str1 = this.conf.urlencode(cparameter(str3));
       } else if (str2.equals("ubb2html")) { str1 = encode.ubb2html(cparameter(str3));
       } else if (str2.equals("webBase")) { str1 = this.conf.common.webBase(cparameter(str3));
       } else if (str2.equals("webHead")) { str1 = this.conf.common.webHead(cparameter(str3));
       } else if (str2.equals("webFoot")) { str1 = this.conf.common.webFoot(cparameter(str3));
       } else if (str2.equals("xmlSelect"))
      {
         if (localInteger.intValue() == 3) str1 = this.conf.common.xmlSelect(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]));
         if (localInteger.intValue() == 4) str1 = this.conf.common.xmlSelect(cparameter(arrayOfString[0]), cparameter(arrayOfString[1]), cparameter(arrayOfString[2]), cparameter(arrayOfString[3]));
      }
    }
     return str1;
  }
	/**
	 * 将页面中的JTBC语法转换为html语法
	 * paramString为含有JTBC节点的字符串
	 * 可能用于函数的语法
	 * {$=itake("global.passport/account:default.login", "lng")}
	 * 转意为
	 * 登录
	 * @param paramString
	 * @return String
	 */
  public String creplace(String paramString)
  {
    Object localObject1;//jtbc节点集合
		Object localObject2;//临时内容
		Object localObject3;//id
		Object localObject4;//属性数组
     String str1 = paramString;
     if (str1.indexOf("</jtbc>") >= 0)
    {
       localObject1 = str1.split(Pattern.quote("</jtbc>"));//按照/JTBC节点分隔
       for (int i = 0; i < ((String[])localObject1).length - 1; ++i)
      {
        
         localObject2 = ((String[])localObject1)[i];
         if ((cls.isEmpty(localObject2).booleanValue()) || 
           (((String)localObject2).indexOf("<jtbc") < 0))
          continue;
         localObject2 = cls.getLRStr((String)localObject2, "<jtbc", "right");//属性和data
         localObject3 = "";//id
         String str3 = "";//属性集合
         String str4 = cls.getLRStr((String)localObject2, ">", "left");//属性
         String str5 = cls.getLRStr((String)localObject2, ">", "rightr");//data
         String str6 = "<jtbc" + ((String)localObject2) + "</jtbc>";//完整的节点内容
         if (!(cls.isEmpty(str4).booleanValue()))
        {
           localObject4 = str4.split(Pattern.quote(" "));
           for (int j = 0; j < ((String[])localObject4).length; ++j)
          {
             String str7 = ((String[])localObject4)[j].trim();
             if (cls.isEmpty(str7).booleanValue())
              continue;
             str7 = str7.replace("\"", "");
             String[] arrayOfString = str7.split(Pattern.quote("="));
             if (arrayOfString.length != 2)
              continue;
             if (arrayOfString[0].equals("id")) localObject3 = arrayOfString[1];
             str3 = str3 + str7 + ";";
          }

        }
         //str3不带有双引号

         if (!(cls.isEmpty(str3).booleanValue())) str3 = cls.getLRStr(str3, ";", "leftr");//去掉最后一个分号
         if (!(cls.isEmpty(localObject3).booleanValue()))
        {
           localObject4 = new String[1][2];//新属性组和data组的集合
           ((String[][])localObject4)[0][0] = str3;
           ((String[][])localObject4)[0][1] = str5;
           this.conf.njtbcelement = cls.mergeAry(this.conf.njtbcelement, ((String[][])localObject4));//修正this.conf.njtbcelement
        }
         str1 = str1.replace(str6, "");//完成一个节点删掉一个节点
      }

    }

    localObject2 = Pattern.compile("(\\{\\$=(.[^\\}]*)\\})");
     localObject3 = ((Pattern)localObject2).matcher(str1);
     while (((Matcher)localObject3).find())
    {
       localObject1 = ((Matcher)localObject3).group(1);
       String str2 = ((Matcher)localObject3).group(2);
       str1 = str1.replace((CharSequence)localObject1, cvalue(str2));
    }
     return ((String)(String)(String)(String)str1);
  }
  /**
   * 将参数数组进行自定义的格式化修正
   * 将头尾包含双引号或者本身就是双引号的参数去掉双引号
   * @param paramArrayOfString
   * @return String[]
   */
  public String[] fixParameterAry(String[] paramArrayOfString)
  {
     String[] arrayOfString1 = paramArrayOfString;
     String str1 = "";
     String str2 = "";
     String str3 = "";
     String str4 = "{@}a{@}b{@}c{@}";
     for (int i = 0; i < arrayOfString1.length; ++i)
    {
       str2 = arrayOfString1[i];
       str2 = str2.trim();
       if (!(cls.isEmpty(str2).booleanValue()))
      {//str2不为空
         if ((!(str2.equals("\""))) && (str2.substring(0, 1).equals("\"")) && (str2.substring(str2.length() - 1, str2.length()).equals("\"")))
        {//如果str2头尾不都包含双引号并且本身不是双引号
           if (!(cls.isEmpty(str3).booleanValue()))
          {//如果str3不为空则str1为本身+str3的开头到最后一个逗号+str4
             str1 = str1 + cls.getLRStr(str3, ",", "leftr") + str4;
             str3 = "";
          }
           str1 = str1 + str2 + str4;//否则由str2代替
           //str1的值为添加str3的"前逗号"部分和str4
        } else {
           str3 = str3 + str2 + ","; }
         //循环值为双引号则str3添加循环值和逗号
      }
       //循环值为空则str3添加逗号
      else str3 = str3 + ",";
    }
     //出循环后
     //str1为,str3为
     if (!(cls.isEmpty(str3).booleanValue())) str1 = str1 + cls.getLRStr(str3, ",", "leftr") + str4;
     str1 = cls.getLRStr(str1, str4, "leftr");
     String[] arrayOfString2 = str1.split(Pattern.quote(str4));
     return arrayOfString2;
  }
  /**
   * 获得应用程序名称的字符串
   * 将paramString1和paramString2转换成路径
   * 其中的/被替换为_
   * 包含../的情况
   * 即(JTBC/src/jtbc/cls.jtbc,sst)变成JTBC_src_jtbc_cls_sst
   * @param paramString1
   * @param paramString2
   * @return String
   */
  public String getAppString(String paramString1, String paramString2)
  {
     String str1 = paramString1;
     String str2 = paramString2;
     if (str1.indexOf("../") == -1)
    {//不包含../的情况
       String str3 = this.conf.getNGenre();//获得项目根路径
       if (!(cls.isEmpty(str3).booleanValue())) str1 = str3 + "/" + str1;
    }
     str1 = str1.replace("../", "");
     str1 = str1.replace(this.conf.xmlsfx, "");
     str1 = str1.replace("/", "_");
     str1 = str1 + "_" + str2;
     return str1;
  }
  /**
   * 查找paramArrayOfString[i][0]中第一个匹配paramString值
   * 并返回对应的paramArrayOfString[i][1]的值
   * @param paramArrayOfString
   * @param paramString
   * @return
   */
  public String getReturn(String[][] paramArrayOfString, String paramString)
  {
     String[][] arrayOfString = paramArrayOfString;
     String str1 = paramString;
     String str2 = "";
     if (arrayOfString != null)
    {
       for (int i = 0; i < arrayOfString.length; ++i)
      {
         if (!(arrayOfString[i][0].equals(str1)))
          continue;
         str2 = arrayOfString[i][1];
         break;
      }
    }
     return str2;
  }
  /**
   * getXInfo重载方法入口
   * 获取paramString1和paramString2组合起来所表示的文件的配置信息
   * @param paramString1
   * @param paramString2
   * @return
   */
  public String[][] getXInfo(String paramString1, String paramString2)
  {
     String str1 = paramString1;
     String str2 = paramString2;
     String[][] arrayOfString = getXInfo(str1, str2, "0");
     return arrayOfString;
  }
  /**
   * getXInfo中真正工作的重载方法
   * 获取paramString1和paramString2组合起来的文件路径中configure所指示的全部内容
   * 组合路径中不包括最后一个[.]之后的部分
   * 返回值中的二维数组里,二维维数表示所含节点的个数
   * 一维数组的维数表示字段的数目
   * 一维数组的内容为字段对应的值
   * @param paramString1 表示页面请求的路径(可包含../)
   * @param paramString2 表示页面请求的路径的最后一部分内容
   * @param paramString3 0表示正常执行,1表示异常情况下的执行
   * @return
   */
  public String[][] getXInfo(String paramString1, String paramString2, String paramString3)
  {
     String str1 = paramString1;
     String str2 = paramString1;//页面请求的路径(可包含../)
     String str3 = paramString2;//页面路径最后一部分
     String str4 = paramString3;//开关参数
     String str5 = getAppString(str1, str3);//真正链接路径
     String[][] arrayOfString1 = (String[][])(String[][])this.conf.application.getAttribute(this.conf.getAppKey(str5));//
     Object localObject1 = arrayOfString1;
     if (localObject1 == null)
    {
      try
      {
         File localFile = new File(this.conf.application.getRealPath(this.conf.getMapPath(str2)).toString());
         DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
         Document localDocument = localDocumentBuilder.parse(localFile);
         XPath localXPath = XPathFactory.newInstance().newXPath();
         Node localNode1 = (Node)localXPath.evaluate("/xml/configure/node", localDocument, XPathConstants.NODE);
         Node localNode2 = (Node)localXPath.evaluate("/xml/configure/field", localDocument, XPathConstants.NODE);
         Node localNode3 = (Node)localXPath.evaluate("/xml/configure/base", localDocument, XPathConstants.NODE);
         String str6 = localNode1.getFirstChild().getNodeValue();//节点
         String str7 = localNode2.getFirstChild().getNodeValue();//字段
         String str8 = localNode3.getFirstChild().getNodeValue();//基础
         if (str7.indexOf(",") >= 0)
        {
          Object localObject3;
          Object localObject2;
           Integer localInteger4;
           Integer localInteger1 = Integer.valueOf(0);//计数器
           Integer localInteger2 = Integer.valueOf(1);//记录计数器的变量值
           Integer localInteger3;//数组长度
           String[] arrayOfString = str7.split(Pattern.quote(","));
           for (localInteger1 = Integer.valueOf(0); localInteger1.intValue() < arrayOfString.length; localInteger3 = localInteger1 = Integer.valueOf(localInteger1.intValue() + 1))
          {
             if (arrayOfString[localInteger1.intValue()].equals(str3)) localInteger2 = localInteger1;//如果跟str3匹配,则记录下所在的数组的位置
             localObject2 = localInteger1;//记录最后一个数组值
          }

           if (localInteger2.intValue() == 0) localInteger2 = Integer.valueOf(1);//如果匹配值为0则修正为1.
           localInteger2 = Integer.valueOf(localInteger2.intValue() * 2 + 1);
           localObject2 = (NodeList)localXPath.evaluate("/xml/" + str8 + "/" + str6, localDocument, XPathConstants.NODESET);//nodelist
           localInteger3 = Integer.valueOf(((NodeList)localObject2).getLength());
           String[][] arrayOfString2 = new String[localInteger3.intValue()][2];//生成实际长度的节点数组
           for (localInteger1 = Integer.valueOf(0); localInteger1.intValue() < localInteger3.intValue(); localInteger4 = localInteger1 = Integer.valueOf(localInteger1.intValue() + 1))
          {
             localObject3 = ((NodeList)localObject2).item(localInteger1.intValue()).getChildNodes();
             arrayOfString2[localInteger1.intValue()][0] = ((NodeList)localObject3).item(1).getFirstChild().getNodeValue();
             arrayOfString2[localInteger1.intValue()][1] = ((NodeList)localObject3).item(localInteger2.intValue()).getFirstChild().getNodeValue();

             localObject3 = localInteger1;
          }

           if (this.conf.isApp.equals("1")) this.conf.application.setAttribute(this.conf.getAppKey(str5), arrayOfString2);//修改该配置立即生效
           localObject1 = arrayOfString2;
        }
      }
      catch (Exception localException)
      {
         this.conf.application.removeAttribute(this.conf.getAppKey(str5));
         if (str4.equals("0")) localObject1 = getXInfo(str1, str3, "1");
      }
    }
     return ((String[][])(String[][])(String[][])localObject1);
  }
  /**
   * 在paramString1路径所示的paramString2对应的文件中查找paramString3的属性信息
   * @param paramString1 表示路径,可能为相对路径或URL地址,格式为(global.)?XX.XX.XX.XX
   * @param paramString2 配置信息,可能表示属性,例如cfg等
   * @param paramString3 可选择的网站前台页面的语言、风格、模板等的名称,如果为空,程序中给默认值
   * @return
   */
  public String[][] getXInfoAry(String paramString1, String paramString2, String paramString3)
  {
     String str1 = paramString1;
     String str2 = paramString2;
     String str3 = paramString3;
     String[] arrayOfString = getXRouteInfo(str1, str2);
     if (cls.isEmpty(str3).booleanValue()) str3 = this.conf.getActiveThings(str2);
     return getXInfo(arrayOfString[0], str3);
  }
  /**
	 * 从paramString1表示的路径中中获取paramString2的文件路径信息
	 * paramString1中最后一个[.]不参与构成路径
	 * paramString1可能为相对路径或URL地址,(global.)?XX.XX.XX.XX
	 * paramString2可能表示命令开关,如cfg,lng,sel,tpl
	 * 其映射关系为cfg=common,lng=common/language,sel=common/language,tpl=common/template
	 * 
	 * arrayOfString[1]表示请求路径中的最后一部分路径
	 * 可能会是配置参数,例如.all
	 * 
	 * 对应的配置文件的位置存放在arrayOfString[0]
   * @param paramString1
   * @param paramString2
   * @return
   */
  public String[] getXRouteInfo(String paramString1, String paramString2)
  {
     String str1 = "";//表示文件夹路径
     String str2 = paramString1.toLowerCase();//地址
     String str3 = paramString2;//命令格式
     if (str3.equals("cfg")) str1 = "common";
     else if (str3.equals("lng")) str1 = "common/language";
     else if (str3.equals("sel")) str1 = "common/language";
     else if (str3.equals("tpl")) str1 = "common/template";
    else str1 = "common";
     if ((str2.length() >= 7) && 
       (str2.substring(0, 7).equals("global.")))
    {//如果是全局量str2则截取global之后的部分
       str2 = cls.getLRStr(str2, ".", "rightr");
       if (str2.indexOf(":") >= 0)
      {//如果含有冒号
         str1 = cls.getLRStr(str2, ":", "left") + "/" + str1;//取得冒号前的部分
         str2 = cls.getLRStr(str2, ":", "right");//取得相对地址
      }
       str1 = this.conf.getActualRouteB(str1.replace(".", "/"));//获取路径
    }
     //str2至此为参数下的冒号或者global之后的部分
     String[] arrayOfString = new String[2];
     arrayOfString[0] = str1 + "/" + cls.getLRStr(str2, ".", "leftr").replace(".", "/") + this.conf.xmlsfx;
     arrayOfString[1] = cls.getLRStr(str2, ".", "right");
     return arrayOfString;
  }
  /**
   * 获得paramString1所表示的路径下的XML文件的paramString2属性
   * @param paramString1
   * @param paramString2
   * @return
   */
  public String getXRootAtt(String paramString1, String paramString2)
  {
     String str1 = "";
     String str2 = paramString2;
     String str3 = paramString1;
    try
    {
       File localFile = new File(this.conf.application.getRealPath(this.conf.getMapPath(str3)).toString());
       DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
       Document localDocument = localDocumentBuilder.parse(localFile);
       str1 = localDocument.getDocumentElement().getAttribute(str2);
    } catch (Exception localException) {
    }
     return str1;
  }
  /**
   * 替换JTBC中的语法标签(公共部分标签)可能用于变量等
   * @param paramString1 路径
   * @param paramString2 命令开关,如cfg,lng等
   * @return
   */
  public String itake(String paramString1, String paramString2)
  {
     String str1 = "";
     String str2 = paramString1;
     String str3 = paramString2;
     str1 = itake(str2, str3, "", "");
     return str1;
  }
  /**
   * 替换JTBC中的语法标签(公共部分标签)可能用于变量等
   * @param paramString1 路径
   * @param paramString2 命令开关,如cfg,lng等
   * @param paramString3 标签语法中的属性集合,以|作为分隔,如name=1|id=12|l=5
   * @return
   */
  public String itake(String paramString1, String paramString2, String paramString3)
  {
     String str1 = "";
     String str2 = paramString1;
     String str3 = paramString2;
     String str4 = paramString3;
     str1 = itake(str2, str3, str4, "");
     return str1;
  }
  /**
   * 替换JTBC中的语法标签(公共部分标签)可能用于变量等
   * 路径为paramString1
   * 对应的配置文件为paramString2
   * 标签中的语法属性集合为paramString3
   * paramString1和paramString2用来获取具体使用哪个文件
   * paramString4配合paramString1和paramString2用来获取文件中paramString4的内容的集合
   * @param paramString1 形如global.passport/account:default.register的路径
   * @param paramString2 所需截取的属性信息,例如lng
   * @param paramString3 元素的属性集合,用|分隔开,例如 name=zhang|id=12|lng=chinese
   * @param paramString4 需要查找的属性
   * @return
   */
  public String itake(String paramString1, String paramString2, String paramString3, String paramString4)
  {
     String str1 = paramString1;//路径,路径是用点分隔的
     String str2 = paramString2;//命令组合,参照的配置信息
     String str3 = paramString3;//用|来分隔的属性的集合
     String str4 = paramString4;//真正要找的属性信息
     String[] arrayOfString1 = getXRouteInfo(str1, str2);
     String[][] arrayOfString = getXInfoAry(str1, str2, str4);
     String str5 = getReturn(arrayOfString, arrayOfString1[1]);//配置信息组
     String str6 = cls.getLRStr(str1, ".", "leftr");//全部路径,但是不包含最后的一部分
     String str7 = cls.getLRStr(cls.getLRStr(str1, ":", "leftr"), "global.", "right");//截取global右边并且冒号左边信息,即global所表示的全局变量的内容
     if ((cls.isEmpty(str7).booleanValue()) || (str7.equals(str1))) str7 = this.conf.getNGenre();
     str5 = str5.replace("{$>this}", str6);
     str5 = str5.replace("{$>this.genre}", str7);

     String[] arrayOfString2 = str7.split(Pattern.quote("/"));
     int i = arrayOfString2.length;
     if (i == 2) str5 = str5.replace("{$>this.genre.parents.1}", arrayOfString2[0]);
     if (i == 3)
    {
       str5 = str5.replace("{$>this.genre.parents.1}", arrayOfString2[0] + "/" + arrayOfString2[1]);
       str5 = str5.replace("{$>this.genre.parents.2}", arrayOfString2[0]);
    }
     if (i == 4)
    {
       str5 = str5.replace("{$>this.genre.parents.1}", arrayOfString2[0] + "/" + arrayOfString2[1] + "/" + arrayOfString2[2]);
       str5 = str5.replace("{$>this.genre.parents.2}", arrayOfString2[0] + "/" + arrayOfString2[1]);
       str5 = str5.replace("{$>this.genre.parents.3}", arrayOfString2[0]);
    }

     if (!(cls.isEmpty(str3).booleanValue()))
    {
       String[] arrayOfString3 = str3.split(Pattern.quote("|"));
       for (int j = 0; j < arrayOfString3.length; ++j)
      {
         String str8 = arrayOfString3[j];
         if (cls.isEmpty(str8).booleanValue())
          continue;
         String[] arrayOfString4 = str8.split(Pattern.quote("="));
         if (arrayOfString4.length != 2) continue; str5 = str5.replace("{$" + arrayOfString4[0] + "}", arrayOfString4[1]);
      }

    }

     str5 = str5.replace("{$>now}", cls.getDate());
     return str5;
  }
  /**
   * 获得paramString1路径下paramString2所对应表示的文件的节点内容集合
   * 即该文件下的全部item节点内容
   * 其中二维数组的维数表示节点个数
   * 一维数组的维数表示字段的个数
   * 字段定义在paramString2所对应文件的config节点下的field节点中
   * @param paramString1
   * @param paramString2
   * @return
   */
  public String[][] itakes(String paramString1, String paramString2)
  {
     String[][] arrayOfString = (String[][])null;
     String str1 = paramString1;
     String str2 = paramString2;
     arrayOfString = getXInfoAry(str1, str2, "");
     return arrayOfString;
  }
  
  /**
   * 获得paramString1路径下paramString2所对应表示的文件的节点中字段paramString3所表示的集合
   * 其中二维数组的维数表示节点个数
   * 一维数组的维数表示字段的个数
   * 字段定义在paramString2所对应文件的config节点下的field节点中
   * @param paramString1
   * @param paramString2
   * @return
   */
  public String[][] itakes(String paramString1, String paramString2, String paramString3)
  {
     String[][] arrayOfString = (String[][])null;
     String str1 = paramString1;
     String str2 = paramString2;
     String str3 = paramString3;
     arrayOfString = getXInfoAry(str1, str2, str3);
     return arrayOfString;
  }
  /**
   * 将JTBC语法格式的字符串输出成html格式的字符串
   * @param paramString1
   * @param paramString2
   * @return
   */
  public String ireplace(String paramString1, String paramString2)
  {
     String str1 = "";
     String str2 = paramString1;
     String str3 = paramString2;
     str1 = ireplace(str2, str3, "");
     return str1;
  }
  /**
   * 将JTBC语法格式的字符串输出成html格式的字符串
   * @param paramString1 路径
   * @param paramString2 命令开关 cfg
   * @param paramString3 属性集合 name=1|lng=s|id=12
   * @return String
   */
  public String ireplace(String paramString1, String paramString2, String paramString3)
  {
     String str1 = "";
     String str2 = paramString1;
     String str3 = paramString2;
     String str4 = paramString3;
     str1 = itake(str2, str3, str4);
     str1 = creplace(str1);
     return str1;
  }
  /**
   * 生成配置文件
   * 其中的paramString1和paramString2构成文件路径
   * 返回值true表示设置成功,false表示失败
   * @param paramString1 路径
   * @param paramString2 命令开关
   * @param paramString3 
   * @param paramString4
   * @return Boolean
   */
  public Boolean iset(String paramString1, String paramString2, String paramString3, String paramString4)
  {
     Boolean localBoolean = Boolean.valueOf(false);
     String str1 = paramString1;
     String str2 = paramString2;
     String str3 = paramString3;
     String str4 = paramString4;
     String[] arrayOfString1 = getXRouteInfo(str1, str2);
     String str5 = arrayOfString1[0];
     String str6 = arrayOfString1[1];
     if (!(cls.isEmpty(str5).booleanValue()))
    {
      try
      {
         File localFile = new File(this.conf.application.getRealPath(this.conf.getMapPath(str5)).toString());
         DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
         Document localDocument = localDocumentBuilder.parse(localFile);
         XPath localXPath = XPathFactory.newInstance().newXPath();
         Node localNode1 = (Node)localXPath.evaluate("/xml/configure/node", localDocument, XPathConstants.NODE);
         Node localNode2 = (Node)localXPath.evaluate("/xml/configure/field", localDocument, XPathConstants.NODE);
         Node localNode3 = (Node)localXPath.evaluate("/xml/configure/base", localDocument, XPathConstants.NODE);
         String str8 = localNode1.getFirstChild().getNodeValue();
         String str9 = localNode2.getFirstChild().getNodeValue();
         String str10 = localNode3.getFirstChild().getNodeValue();
         if (str9.indexOf(",") >= 0)
        {
           int i = 1;
           String[] arrayOfString2 = str9.split(Pattern.quote(","));
           for (int j = 0; j < arrayOfString2.length; ++j)
          {
             if (!(arrayOfString2[j].equals(str3))) continue; i = j;
          }
           if (i == 0) i = 1;
           i = i * 2 + 1;
           NodeList localNodeList1 = (NodeList)localXPath.evaluate("/xml/" + str10 + "/" + str8 + "[" + cls.getLRStr(str9, ",", "left") + "='" + str6 + "']", localDocument, XPathConstants.NODESET);
           if (localNodeList1 != null)
          {
             NodeList localNodeList2 = localNodeList1.item(0).getChildNodes();
             localNodeList2.item(i).getFirstChild().setNodeValue(str4);
             TransformerFactory localTransformerFactory = TransformerFactory.newInstance();
             Transformer localTransformer = localTransformerFactory.newTransformer();
             DOMSource localDOMSource = new DOMSource(localDocument);
             StreamResult localStreamResult = new StreamResult(new File(this.conf.application.getRealPath(this.conf.getMapPath(str5)).toString()));
             localTransformer.transform(localDOMSource, localStreamResult);
             localBoolean = Boolean.valueOf(true);
          }
        }
      } catch (Exception localException) {
      }
    }
     if (localBoolean.booleanValue() == true)
    {
       String str7 = getAppString(str5, str3);
       this.conf.application.removeAttribute(this.conf.getAppKey(str7));
    }
     return localBoolean;
  }
  /**
   * 构造函数
   * 需要一个conf
   * @param paramconf
   */
  public jt(conf paramconf)
  {
     this.conf = paramconf;
  }
}