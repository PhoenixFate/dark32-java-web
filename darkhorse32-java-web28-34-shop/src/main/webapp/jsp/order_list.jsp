<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    <style>
        body {
            margin-top: 20px;
        }

        .carousel-inner .item img {
            width: 100%;
            height: 300px;
        }
    </style>
</head>
<body>
<!-- 引入header.jsp -->
<jsp:include page="/jsp/header.jsp"/>

<div class="container">
    <div class="row">
        <div style="margin: 10px auto 0;width: 950px;">
            <strong>我的订单</strong>
            <table class="table table-bordered">
                <c:forEach items="${pageBean.list}" var="order">
                    <tbody>
                    <tr class="success">
                        <th colspan="5">订单编号:${order.oid}---${order.state==0?"未付款":"已付款"}</th>
                    </tr>
                    <tr class="warning">
                        <th>图片</th>
                        <th>商品</th>
                        <th>价格</th>
                        <th>数量</th>
                        <th>小计</th>
                    </tr>
                    <c:forEach items="${order.orderItems}" var="orderItem" varStatus="vs">
                        <tr class="active">
                            <td width="60" width="40%">
                                <input type="hidden" name="id" value="22">
                                <img src="${pageContext.request.contextPath}/${orderItem.product.pimage}" width="70"
                                     height="60"></td>
                            <td width="30%">
                                <a target="_blank">${orderItem.product.pname}</a>
                            </td>
                            <td width="20%">￥${orderItem.product.shop_price}</td>
                            <td width="10%">${orderItem.count}</td>
                            <td width="15%"><span class="subtotal">￥${orderItem.subtotal}</span></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </c:forEach>
            </table>
        </div>
    </div>
    <div style="text-align: center;">
        <ul class="pagination">
            <c:if test="${pageBean.currentPage==1}">
                <li class="disabled">
                    <a href="javascript:void(0)" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                </li>
            </c:if>
            <c:if test="${pageBean.currentPage!=1}">
                <li>
                    <a href="${pageContext.request.contextPath}/orderServlet?method=findByPage&pageNumber=${pageBean.currentPage-1}"
                       aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${pageBean.totalPage}" var="pageIndex">
                <c:if test="${pageBean.currentPage==pageIndex}">
                    <li class="active"><a href="${pageContext.request.contextPath}/orderServlet?method=findByPage&pageNumber=${pageIndex}">${pageIndex}</a></li>
                </c:if>
                <c:if test="${pageBean.currentPage!=pageIndex}">
                    <li><a href="${pageContext.request.contextPath}/orderServlet?method=findByPage&pageNumber=${pageIndex}">${pageIndex}</a></li>
                </c:if>
            </c:forEach>
            <c:if test="${pageBean.currentPage==pageBean.totalPage}">
                <li class="disabled">
                    <a href="javascript:void(0)" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
                </li>
            </c:if>
            <c:if test="${pageBean.currentPage!=pageBean.totalPage}">
                <li>
                    <a href="${pageContext.request.contextPath}/orderServlet?method=findByPage&pageNumber=${pageBean.currentPage+1}"
                       aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<!-- 引入footer.jsp -->
<jsp:include page="footer.jsp"/>
</body>
</html>