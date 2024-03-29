<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style1.css"
          rel="stylesheet" type="text/css"/>
    <script  src="${pageContext.request.contextPath}/js/public.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
    <script type="text/javascript">
        function addCategory() {
            window.location.href = "${pageContext.request.contextPath}/admin/category/add.jsp";
        }
    </script>
</HEAD>
<body>
<br>
<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
    <TBODY>
    <tr>
        <td class="ta_01" align="center" bgColor="#afd1f3">
            <strong>分类列表</strong>
        </td>
    </tr>
    <tr>
        <td class="ta_01" align="right">
            <button type="button" id="add" name="add" value="添加"
                    class="button_add" onclick="addCategory()">
                &#28155;&#21152;
            </button>
        </td>
    </tr>
    <tr>
        <td class="ta_01" align="center" bgColor="#f5fafe">
            <table cellspacing="0" cellpadding="1" rules="all"
                   bordercolor="gray" border="1" id="DataGrid1"
                   style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
                <tr style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">
                    <td align="center" width="18%">序号</td>
                    <td align="center" width="17%">分类id</td>
                    <td align="center" width="17%">分类名称</td>
                    <td width="7%" align="center">编辑</td>
                    <td width="7%" align="center">删除</td>
                </tr>

                <c:forEach items="${pageBean.list}" var="category" varStatus="vs">
                    <tr onmouseover="this.style.backgroundColor = 'white'"
                        onmouseout="this.style.backgroundColor = '#F5FAFE';">
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">
                            ${vs.index}
                        </td>
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="18%">
                            ${category.cid}
                        </td>
                        <td style="CURSOR: hand; HEIGHT: 22px" align="center" width="17%">
                            ${category.cname}
                        </td>
                        <td align="center" style="HEIGHT: 22px">
                            <a href="${ pageContext.request.contextPath }/categoryServlet?method=updateCategoryUI&id=${category.cid}">
                                <img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
                            </a>
                        </td>
                        <td align="center" style="HEIGHT: 22px">
                            <a href="${ pageContext.request.contextPath }/categoryServlet?method=deleteCategory&cid=${category.cid}">
                                <img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
    </TBODY>
</table>




<div style="width: 380px; margin: 50px auto 0;">
    <ul class="pagination" style="text-align: center; margin-top: 10px;">
        <c:if test="${pageBean.currentPage==1}">
            <li class="disabled">
                <a href="javascript:void(0)" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${pageBean.currentPage>1}">
            <li>
                <a href="${pageContext.request.contextPath}/categoryServlet?method=listByPage&pageNumber=${pageBean.currentPage-1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${pageBean.totalPage}" var="pageIndex">
            <c:if test="${pageBean.currentPage==pageIndex}">
                <li class="active">
                    <a href="${pageContext.request.contextPath}/categoryServlet?method=listByPage&pageNumber=${pageIndex}">${pageIndex}</a>
                </li>
            </c:if>
            <c:if test="${pageBean.currentPage!=pageIndex}">
                <li >
                    <a href="${pageContext.request.contextPath}/categoryServlet?method=listByPage&pageNumber=${pageIndex}">${pageIndex}</a>
                </li>
            </c:if>
        </c:forEach>

        <c:if test="${pageBean.currentPage<pageBean.totalPage}">
            <li>
                <a href="${pageContext.request.contextPath}/categoryServlet?method=listByPage&pageNumber=${pageBean.currentPage+1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${pageBean.currentPage==pageBean.totalPage}">
            <li class="disabled">
                <a href="javascript:void(0)" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </c:if>

    </ul>
</div>
<!-- 分页结束 -->


</body>
</HTML>

