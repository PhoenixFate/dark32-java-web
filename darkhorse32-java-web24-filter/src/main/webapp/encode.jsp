<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 5/9/2019
  Time: 2:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Encode</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/encodeServlet" method="post">

        <input type="text" name="username">
        <input type="submit" value="提交">
    </form>
</body>
</html>
