<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 4/16/2019
  Time: 1:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PageContext</title>
</head>
<body>
    <%
        //使用pageContext向request中存取数据
        request.setAttribute("name","zhangsan");
        pageContext.setAttribute("name","sunba");
        pageContext.setAttribute("name","lisi",PageContext.REQUEST_SCOPE);
        pageContext.setAttribute("name","wangwu",PageContext.SESSION_SCOPE);
        pageContext.setAttribute("name","tianqi",PageContext.APPLICATION_SCOPE);

    %>
<%=request.getAttribute("name")+"<br>"%>
<%=pageContext.getAttribute("name",PageContext.REQUEST_SCOPE)+"<br>"%>
<%=pageContext.getAttribute("name",PageContext.SESSION_SCOPE)+"<br>"%>
<%=pageContext.getAttribute("name",PageContext.APPLICATION_SCOPE)+"<br>"%>

-----------------------------
<%=pageContext.getAttribute("name")%><br>
    findAttribute会从小到大搜索域的范围的name<br>
    page域--request域--session域--application域<br>
<%=pageContext.findAttribute("name")%>

pageContext获得其他8大隐士对象
<%
    pageContext.getSession();
    pageContext.getRequest();
    pageContext.getPage();
    pageContext.getServletConfig();
    pageContext.getServletContext();
%>

</body>
</html>
