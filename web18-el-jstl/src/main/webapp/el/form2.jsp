<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 4/20/2019
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form2</title>
</head>
<body>
    <%--获得表单参数--%>
    方法一：<br>
    username:<%=request.getParameter("username")%><br>
    password:<%=request.getParameter("password")%><br>
    hobby:<%=request.getParameterValues("hobby")%><br>
    方法二：<br>
    username: ${param.username}<br>
    password: ${param.password}<br>
    hobby: ${paramValues.hobby}<br>


    取头<br>
    header.host:${header.Host}<br>
    有特殊字符的，需要用[]的方式取值：header["user-agent"]:${header["user-agent"]}<br>

    通过initParam来取值：${initParam.aaa}<br>

    通过cookie来获取cookie：${cookie.name.value}<br>

    <%--获取request对象--%>
    ${pageContext.request.contextPath} <br>
    
</body>
</html>
