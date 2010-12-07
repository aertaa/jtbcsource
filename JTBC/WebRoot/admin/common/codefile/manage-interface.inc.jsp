<%@ page import = "jtbc.*" %>
<%
class module extends jpage
{
  private admin admin;

  private String Module_Action_Login()
  {
    String tstate = "0";
    String tusername = cls.getSafeString(conf.getRequestUsParameter("username"));
    String tpassword = cls.getSafeString(conf.getRequestUsParameter("password"));
    String tvalcode = cls.getSafeString(conf.getRequestUsParameter("valcode"));
    if (conf.common.ckValcode(tvalcode))
    {
      if (!cls.isEmpty(tusername))
      {
        if (admin.ckLogins(tusername, tpassword)) tstate = "200";
      }
    }
    else tstate = "1";
    return tstate;
  }

  private String Module_Action_Logout()
  {
    admin.Logout();
    return "200";
  }

  private String Module_Action()
  {
    String tmpstr = "";
    String tAtype = cls.getString(conf.getRequestUsParameter("atype"));

    if (tAtype.equals("login")) tmpstr = Module_Action_Login();
    else if (tAtype.equals("logout")) tmpstr = Module_Action_Logout();

    return tmpstr;
  }

  private String Module_Login()
  {
    String tmpstr = "";
    tmpstr = conf.jt.ireplace("manage-interface.login", "tpl");
    tmpstr = conf.ajaxPreContent + tmpstr;
    return tmpstr;
  }

  private String Module_Desktop()
  {
    String tmpstr = "";
    if (admin.ckLogin())
    {
      tmpstr = conf.jt.itake("manage-interface.desktop", "tpl");
      tmpstr = tmpstr.replace("{$MenuHtml}", admin.getMenuHtml(conf.getActualRouteB("./")));
      tmpstr = conf.jt.creplace(tmpstr);
    }
    tmpstr = conf.ajaxPreContent + tmpstr;
    return tmpstr;
  }

  private String Module_Default()
  {
    String tmpstr = "";
    if (!admin.ckLogin()) tmpstr = Module_Login();
    else tmpstr = Module_Desktop();
    return tmpstr;
  }

  public String getOutput()
  {
    PageInit();
    PageNoCache();

    String tmpstr = "";

    admin = new admin(conf);
    admin.adminPstate = "public";

    String tType = cls.getString(conf.getRequestUsParameter("type"));

    if (tType.equals("action")) tmpstr = Module_Action();
    else if (tType.equals("login")) tmpstr = Module_Login();
    else if (tType.equals("desktop")) tmpstr = Module_Desktop();
    else tmpstr = Module_Default();

    PageClose();

    return tmpstr;
  }
}
%>