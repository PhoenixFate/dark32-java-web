<%@ page import="java.util.Date" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: phoenixfate
  Date: 2019/10/8
  Time: 9:02 PM
  To change this template use File | Settings | File Templates.
--%>

<%--三大指令之一：page 指令--%>
<%--
page指令：
    language:"java"只能java    可不写
    contentType:"text/html;charset=UTF-8"  <==>   response.setContentType("text/html;charset=utf-8")   可不写，自动按照pageEncoding来填写contentType
    pageEncoding:"utf=8"   当前jsp文件的编码(磁盘上的编码方式)
    session:"true"  是否自动创建session
    import: "xxxx" 导包
    errorPage: 错误页面  404等错误需要在web.xml中配置全局的错误页面
    isErrorPage: true/false 是否是错误页面  ;  为true时，可以拿到内置的exception对象
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"  errorPage="error.jsp" %>
<html>
<head>
    <title>MyIndex</title>
</head>
<body>
    <%
        out.print("1 hello world ");
        out.print("2 hello world <br> ");
        out.print("3 hello world \n ");
        out.println("4 hello world ");
        out.println("5 hello world <br> ");
        out.println("6 hello world \n ");


        out.write("7 hello world ");
        out.write("8 hello world <br> ");
        out.write("9 hello world \n ");
    %>
<%--    jsp 注释--%>
    <br>
    <%=
       new Date().getTime()
    %>
    <br>
    <%
        int a=0;
        System.out.println(a);
    %>

    <%=
        a
    %>
    <br>
    <%=
        str
    %>

    <%!
        String str="abc";
    %>

<%--    <%--%>
<%--        List list=new ArrayList<String>();--%>
<%--        int c=1/0;--%>
<%--    %>--%>
</body>
</html>
