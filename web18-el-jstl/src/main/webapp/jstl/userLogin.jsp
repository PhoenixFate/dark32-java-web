<%@ page import="com.example.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 4/22/2019
  Time: 8:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>userLogin</title>
</head>
<body>
    <%--模拟登录--%>
    <%
        User user=new User();
        user.setName("大王");
        session.setAttribute("user",user);
    %>
</body>
</html>
