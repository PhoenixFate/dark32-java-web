<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 4/16/2019
  Time: 11:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Out</title>
</head>
<body>
    向页面输出内容的几种方式：<br>
    aaaaaaaaaaaaaaa<br>
    <%
        out.write("bbbbbbbbbbbbbbbb<br>");
        response.getWriter().write("cccccccccccc<br>");
    %>
    <%=
        "dddddddddddddddddd<br>"
    %>
    <%--
        1.2.4 最后都转换成out.write
    --%>
</body>
</html>
