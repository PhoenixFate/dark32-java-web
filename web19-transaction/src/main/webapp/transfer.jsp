<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 4/24/2019
  Time: 8:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transfer</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/transfer" method="post">
        转出账户：<input type="text" name="out"><br>
        转入账户：<input type="text" name="in"><br>
        金额：<input type="text" name="money"><br>
        <input type="submit" value="确认转账">
    </form>
</body>
</html>
