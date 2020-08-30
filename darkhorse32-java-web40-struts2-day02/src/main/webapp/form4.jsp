<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 2020/1/1
  Time: 7:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/param/ParamAction4" method="post">
        list: <input type="text" name="list" /><br>
        list: <input type="text" name="list[1]" /><br>
        map: <input type="text" name="map['name']" /><br>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
