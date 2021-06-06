<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 1/7/2020
  Time: 11:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Tag1</title>
</head>
<body>

<%--控制标签（迭代标签）--%>

<%--遍历标签iterator--%>
<%--第一种遍历方式--%>
<h2>第一种遍历方式</h2>
<s:iterator value="#list">
    <s:property/><br>
</s:iterator>

<%--第二种遍历方式--%>
<h2>第二种遍历方式</h2>
<s:iterator value="#list" var="sub">
    <s:property value="#sub"/><br>
</s:iterator>

<%--遍历数数--%>
<h2>遍历数数</h2>
<s:iterator begin="1" end="10" step="1">
    <s:property/><br>
</s:iterator>

<%--if else; else if--%>
<h2>if else;else if</h2>
<s:if test="#list.size==4">
    <h4>list 长度4</h4>
</s:if>
<s:elseif test="#list.size==3">
    <h4>list 长度3</h4>
</s:elseif>
<s:else>
    <h4>list 长度不3不4</h4>
</s:else>



<%--数据标签--%>
<%--property 配合ognl表达式页面取值--%>
<h2>property 配合ognl表达式页面取值</h2>
<s:property value="#list.size"/>
<s:property value="#session.user.name"/>

</body>
</html>
