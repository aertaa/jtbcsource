<%@ page contentType = "text/html;charset=utf-8" %>
<%@ include file = "common/codefile/default.inc.jsp" %>
<%
module module = new module();
module.Init(request, response);
String nOutput = module.getOutput();
out.clear();
out.print(nOutput);
%>