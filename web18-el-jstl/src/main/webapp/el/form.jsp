<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 4/20/2019
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/el/form2.jsp" method="get">
        <input type="text" name="username"><br>
        <input type="password" name="password"><br>
        <input type="checkbox" name="hobby" value="zq">足球
        <input type="checkbox" name="hobby" value="pq">排球
        <input type="checkbox" name="hobby" value="ppq">乒乓球<br>
        <input type="submit" value="提交"><br>
    </form>

</body>
</html>
