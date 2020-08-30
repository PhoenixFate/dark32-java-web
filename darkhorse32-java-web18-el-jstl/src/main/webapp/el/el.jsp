<%@ page import="com.example.domain.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 4/20/2019
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ExpressLanguage</title>
</head>
<body>
    <!-- 模拟从域中区数据-->
    <%
        //存储字符串
        pageContext.setAttribute("company","pageContextCompany");
        //存储字符串
        request.setAttribute("company","myCompany");

        User user=new User();
        user.setId("1");
        user.setName("zhangsan");
        user.setPassword("123");
        //存储对象
        session.setAttribute("user",user);

        //存储集合
        List<User> list=new ArrayList<User>();
        User user1=new User();
        user1.setId("11");
        user1.setName("zhangsan");
        user1.setPassword("123");
        User user2=new User();
        user2.setId("12");
        user2.setName("李四");
        user2.setPassword("123");
        list.add(user1);
        list.add(user2);
        application.setAttribute("list",list);
    %>
    <%--使用脚本的方式取出域中的值--%>
    get value  by using jsp scriptlets<br>
    <%=request.getAttribute("company")%>
    <%
        User sessionUser=(User)session.getAttribute("user");
        out.write(sessionUser.getName()+"<br>");
    %>
    <%--使用el表达式获得域中的值--%>
    <br>
    <hr>
    ${requestScope.company}<br>
    ${sessionScope.user.name}<br>
    ${sessionScope.user.getName()}<br>
    ${applicationScope.list[1].name}<br>

    <hr>
    <%--使用el表达式全域查找想要的值--%>
    ${company}<br>
    ${user.name}


    <br>
    <%--el 可以执行表达式--%>
    ${1+1}<br>
    ${1==1?'aaa':'bbb'} <br>
    ${empty user} <br>
    ${empty ""} <br>


</body>
</html>
