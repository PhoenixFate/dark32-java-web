<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 4/20/2019
  Time: 5:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>JSTL</title>
</head>
<body>
    <%
        request.setAttribute("count",10);
    %>
    <%--jstl 经常和el配合使用--%>
    <%--test代表的是条件，返回bool的条件--%>
    <c:if test="${1==1}">
        c:if成功！
    </c:if>
    <%--没有if-else--%>
    <c:if test="${1!=1}">
        没有if-else
    </c:if>


    <c:if  test="${count==10}">
        count==10
    </c:if>


    <%--第一种--%>
    <%--foreach 模拟
        for(int i=0;i<5;++i){
            sout(i);
        }
    --%>
    forEach:<br>
    <c:forEach begin="1" end="5" var="i" >
        ${i}<br>
    </c:forEach>

    <%--第二种--%>
    <%--模拟增强for
        for(Product product:product_list){
            sout(product.getPname());
        }
    --%>
    <%--items: 表示的是一个集合或数组  var: 表示集合中的某一个元素--%>
    <c:forEach items="${productList}" var="product">
        ${product.pname} <br>
    </c:forEach>

    <c:forEach items="${productList}" var="p">
        ${p.name} <br>
    </c:forEach>


</body>
</html>
