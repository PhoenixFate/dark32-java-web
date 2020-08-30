<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 4/14/2019
  Time: 4:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  session="true" errorPage="./error.jsp" %>
<%--引入JSTL核心库--%>
<%--<%@taglib prefix="c" uri="http://" %>--%>
<html>
<head>
    <title>Index</title>
</head>
<body>
    <%@include file="demo.jsp" %>
    <%
        session.setAttribute("name","zhangsan");
        int i=10;
        System.out.println("i= "+i);
        List<String> lis=new ArrayList<String>();
    %>
    <%=i

    %>
    <%!
        String str="wjeljwie";
        public void MyMethod(){

        }
        //java注释
        /*
        * java注释
        * */
    %>
    <%--jsp注释--%>
    <!--
        html注释
    -->

    <%=str

    %>

    <%--jsp内置9大隐士对象--%>
    <%--out对象 作用向客户端输出内容--%>
    <%--out类型：JspWriter--%>
    <%--具体看out.jsp--%>
    <%

    %>
</body>
</html>
