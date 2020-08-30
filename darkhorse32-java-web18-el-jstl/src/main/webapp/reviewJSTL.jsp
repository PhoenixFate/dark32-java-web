<%--
  Created by IntelliJ IDEA.
  User: phoenixfate
  Date: 2019/10/9
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>review JSTL</title>
</head>
<body>
    <%
        request.setAttribute("count","10");
    %>


<%--
    <c:if test="">

    </c:if>
--%>
    <c:if test="${count>=10}">
        ${count}
    </c:if>


</body>
</html>
