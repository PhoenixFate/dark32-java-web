<%--
  Created by IntelliJ IDEA.
  User: phoenixfate
  Date: 2019/10/9
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<html>
<head>
    <title>expression language</title>
</head>
<body>
    <%
        pageContext.setAttribute("name","test00");
        request.setAttribute("name","test01");
        session.setAttribute("name","test02");
        application.setAttribute("name","test03");
    %>

<%--    使用jsp脚本获得数据--%>
    使用jsp脚本获得数据：<br>
    <%=
        pageContext.getAttribute("name")
    %>
    <br>
    <%=
        request.getAttribute("name")
    %>
    <br>
    <%=
        session.getAttribute("name")
    %>
    <br>
    <%=
        pageContext.getAttribute("name")
    %>
    <br>

    使用el表达式获得数据：<br>
    ${pageScope.get("name")}
    <br>
    ${requestScope.get("name")}
    <br>
    ${sessionScope.get("name")}
    <br>
    ${applicationScope.get("name")}
    <br>

    依次获得数据$  { xxx }   <=======>   pageContext.findAttribute(string name)<br>
    <%=
        pageContext.findAttribute("name")
    %>
    <br>
    ${name}
    <br>

    获得请求参数：<br>
    <%=
        request.getParameter("name")
    %>
    <br>
    ${param.name}<br>
    ${param.get("name")}<br>
    <br>
    contextPath: <br>
    <%=
        request.getContextPath()
    %> <br>
    <%=
        ((HttpServletRequest)pageContext.getRequest()).getContextPath()
    %>
    <br>
    ${pageContext.request.contextPath}




</body>
</html>
