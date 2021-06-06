<%--
  Created by IntelliJ IDEA.
  User: phoenix
  Date: 1/7/2020
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%--struts2 表单标签--%>
<%--好处1：内置了样式--%>
<%--好处2：自动根据栈中的内容，回写数据--%>
<%--theme: 默认是xhtml,自带一定样式，可以选择不带样式的主题 simple--%>
<s:form action="TagAction3" namespace="/tag" method="POST" theme="xhtml">
    <s:textfield name="username" label="用户名"/><br>
    <s:textfield name="nickname" label="昵称"/><br>
    <s:password name="password" label="密码"/><br>

    <s:radio list="{'男','女'}" label="性别gender" name="gender"/><br>
    <s:radio list="#{1:'男',0:'女'}" label="性别sex" name="sex"/><br>

    <s:checkboxlist list="#{1:'喝酒',0:'打游戏',2:'上网'}" label="兴趣" name="hobby"/><br>
    <s:select list="#{1:'本科',0:'专科',2:'硕士'}" headerKey="" headerValue="---请选择---" label="学历" name="edu"/><br>

    <s:file name="photo" lable="照片"/><br>
    <s:textarea name="desc" label="个人简介"/><br>
    <s:submit value="提交"/>
</s:form>

<%--非表单标签--%>
<%--取出错误信息--%>
<s:actionerror />

</body>
</html>
