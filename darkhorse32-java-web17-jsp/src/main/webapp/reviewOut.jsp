<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 10/9/2019
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>review out</title>
</head>
<body>
    1111
    <%
        out.write("aaa");
        out.print("bbb");
    %>
    <%
<%--    tomcat默认从response 缓冲区 得到数据--%>
<%--    response缓冲区 在自己的数据基础上 从out里面得到数据 --%>
<%--    所以ccc ddd在前面--%>
        response.getWriter().write("ccc");
        response.getWriter().println("ddd");
    %>
    <%="ddd"

    %>
    2222
</body>
</html>
