<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 10/9/2019
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Review PageContext</title>
</head>
<body>

<%--
    pageContext也是域对象
        setAttribute(String name, Object obj);
        getAttribute(String name, Object obj);
        removeAttribute(String name);

        // 向指定的其他域中存储数据
        // scope：PageContext.REQUEST_SCOPE;  PageContext.SESSION_SCOPE;  PageContext.APPLICATION_SCOPE
        setAttribute(String name, Object obj, int scope);
        getAttribute(String name, Object obj, int scope);
        removeAttribute(String name);

        // 依此从pageContext域、request域、session域、application域中获得对象
        // pageContext 习惯叫做page域
        findAttribute(String name);
--%>

<%
    request.setAttribute("name","nameRequest");
    session.setAttribute("name","nameSession");
    application.setAttribute("name","nameApplication");
    pageContext.setAttribute("name","namePageContext");

    pageContext.setAttribute("aaa","bbb",PageContext.REQUEST_SCOPE);
    pageContext.setAttribute("ccc","ddd",PageContext.SESSION_SCOPE);
    pageContext.setAttribute("eee","fff",PageContext.APPLICATION_SCOPE);
%>

<%=
    pageContext.getAttribute("name")
%>
<br>
<%=
    pageContext.findAttribute("name")
%>
<br>
<%=
pageContext.getAttribute("name",PageContext.REQUEST_SCOPE)
%>
<br>
<%=
pageContext.getAttribute("name",PageContext.SESSION_SCOPE)
%>
<br>
<%=
pageContext.getAttribute("name",PageContext.APPLICATION_SCOPE)
%>

<%--
    pageContext可以获得其他8大隐士对象
    pageContext.getRequest();
    pageContext.getSession();
--%>

<%
    pageContext.getRequest();
    pageContext.getServletConfig();
    pageContext.getSession();
    pageContext.getServletContext();
    pageContext.getPage();
    pageContext.getException();
    pageContext.getOut();
    pageContext.getResponse();
%>



</body>
</html>
