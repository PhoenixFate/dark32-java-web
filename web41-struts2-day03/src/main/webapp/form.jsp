<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 2020/1/4
  Time: 5:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/valueStack/ValueStackAction2.action">
        用户名: <input type="text" name="name">
        <input type="submit" value="submit">
    </form>
</body>
</html>
