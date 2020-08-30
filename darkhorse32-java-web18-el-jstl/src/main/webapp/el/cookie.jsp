<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 4/20/2019
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cookie</title>
</head>
<body>
    <%
        Cookie cookie=new Cookie("name","cookie");
        response.addCookie(cookie);
    %>
</body>
</html>
