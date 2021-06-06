<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 10/23/2019
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
</head>
<body>
<%--multipart/formdata多部分表单--%>
    <form action="${pageContext.request.contextPath}/fileuploadServlet2" method="post" enctype="multipart/form-data">
        <input type="text" name="username"> <br>
        <input type="file" name="filename">
        <input type="submit" value="上传">
    </form>
</body>
</html>
