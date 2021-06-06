<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 10/9/2019
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
    <%
        String contextPath=request.getContextPath();
        out.print("contextPath: ");
        out.print(contextPath+"<br>");
    %>

    <%
        session.setAttribute("name","zhangsan");
    %>

    <%
        out.print(session.getAttribute("name"));
    %>

<%--
    jsp 9 大 隐士对象

    ServletContext application; //所有用户共享的信息  // 域对象
    ServletConfig config;//1.getInitParameter  //2.getServletName  //3.getServletContext
    JspWriter out;
    HttpSession session;// 域对象
    Object page=this;

    HttpServletRequest request; // 域对象
    HttpServletResponse response;

    PageContext pageContext;//jsp 页面容器 //域对象
    java.lang.Throwable exception;// isErrorPage=true 时才会有的对象 //exception.getMessage()

--%>



</body>
</html>
