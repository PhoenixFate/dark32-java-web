<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改商品信息</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript">
        $(function () {
            //alert(1);
            let items = {"id": 1, "name": "测试商品", "price": 99.9, "detail": "测试商品描述", "pic": "123456.jpg"};

            // 	$.post(url,params,function(data){
            //$.post只能回调接受json，不能发送json字符串
            //主要$.post发送的contentType不是 "application/json;charset=UTF-8"
            // 	},"json");//
            $.ajax({
                url: "${pageContext.request.contextPath }/item/json.action",
                data: JSON.stringify(items),
                //只有$.ajax 能设置contextType
                contentType: "application/json",//发送数据的格式
                type: "post",
                dataType: "json",//回调
                success: function (data) {
                    console.log(data);
                }

            });
        });
    </script>
</head>
<body>
<!-- 上传图片是需要指定属性 enctype="multipart/form-data" -->
<!-- <form id="itemForm" action="" method="post" enctype="multipart/form-data"> -->
<form id="itemForm" action="${pageContext.request.contextPath }/item/postEdit.action" method="post"
      enctype="multipart/form-data">
    <input type="hidden" name="id" value="${item.id }"/> 修改商品信息：
    <table width="100%" border=1>
        <tr>
            <td>商品名称</td>
            <td><input type="text" name="name" value="${item.name }"/></td>
        </tr>
        <tr>
            <td>商品价格</td>
            <td><input type="text" name="price" value="${item.price }"/></td>
        </tr>

        <tr>
            <td>商品生产日期</td>
            <td><input type="text" name="createtime"
                       value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/></td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td>
                <c:if test="${item.pic !=null}">
                    <img src="/picture/${item.pic}" width=100 height=100/>
                    <br/>
                </c:if>
                <input type="file" name="pictureFile"/>
            </td>
        </tr>
        <tr>
            <td>商品简介</td>
            <td><textarea rows="3" cols="30" name="detail">${item.detail }</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="提交"/>
            </td>
        </tr>
    </table>

</form>
</body>

</html>