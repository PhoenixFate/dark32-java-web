<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.domain.User" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 4/22/2019
  Time: 8:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>ForEach</title>
</head>
<body>
    <%
        //模拟List<String> strList
        List<String> stringList=new ArrayList<String>();
        stringList.add("虎头鲨");
        stringList.add("叮当猫");
        stringList.add("大丈夫");
        request.setAttribute("stringList",stringList);

        List<User> userList=new ArrayList<User>();
        User user1=new User();
        user1.setId("1");
        user1.setName("大王");
        user1.setPassword("123");
        User user2=new User();
        user2.setId("2");
        user2.setName("小王");
        user2.setPassword("123");
        userList.add(user1);
        userList.add(user2);
        request.setAttribute("userList",userList);


        //遍历Map<String,String> 的值
        Map<String,String> stringMap=new HashMap<String,String>();
        stringMap.put("name","lucy");
        stringMap.put("age","18");
        stringMap.put("address","address");
        stringMap.put("email","lucy@163.com");
        session.setAttribute("stringMap",stringMap);

        //遍历Map<String,User>的值
        Map<String,User> userMap=new HashMap<String,User>();
        userMap.put("user1",user1);
        userMap.put("user2",user2);
        request.setAttribute("userMap",userMap);
    %>
    <h1>取出stringList的数据</h1>
    <c:forEach items="${stringList}" var="str">
        ${str}<br>
    </c:forEach>
    <h1>取出userList的数据</h1>
    <c:forEach items="${userList}" var="user">
        ${user.name}<br>
    </c:forEach>
    <h1>取出stringMap的数据</h1>
    <c:forEach items="${stringMap}" var="entry">
        ${entry.key} : ${entry.value} <br>
    </c:forEach>
    <h1>取出userMap的数据</h1>
    <c:forEach items="${userMap}" var="entry">
        ${entry.key} : ${entry.value.id} ; ${entry.value.name} ; ${entry.value.password}<br>
    </c:forEach>
</body>
</html>
