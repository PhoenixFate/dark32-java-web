<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		
		<!-- 弹出层插件 -->
		<link href="${pageContext.request.contextPath}/css/popup_layer.css" type="text/css" rel="stylesheet"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/popup_layer.js"></script>		
		<!-- 调用插件弹出层的方法 -->
		<script type="text/javascript">
			$(function(){
				//弹出层插件调用
				new PopupLayer({
					trigger:".clickedElement", //触发点： 点击谁显示弹出层
					popupBlk:"#showDiv", //弹出哪个div
					closeBtn:"#closeBtn",  //关闭按钮
					useOverlay:true
				});
				
			});

			// 点击订单详情
			function findOrderInfoByOid(oid) {
				// 显示加载图片
				$("#loading").show();
				// 清理上一次显示到内容
				$("#showDivTab").html("");
				$("#shodDivOid").html("");


				//ajax 异步访问数据
				$.post(
					"${pageContext.request.contextPath}/orderServlet?method=findDetailByOid",
						{"oid":oid},
						function (data) {
							// 隐藏加载图片
							$("#loading").hide();

							console.log(data);
							let content="<tr id='showTableTitle'>\n" +
									"<th width='20%'>图片</th>\n" +
									"<th width='25%'>商品</th>\n" +
									"<th width='20%'>价格</th>\n" +
									"<th width='15%'>数量</th>\n" +
									"<th width='20%'>小计</th>\n" +
									"</tr>";
							for(let i=0;i<data.length;i++){
								content+="<tr style='text-align: center;'>\n" +
										"<td>\n" +
										"<img src='${pageContext.request.contextPath }/"+data[i].pimage+"' width='70' height='60'>\n" +
										"</td>\n" +
										"<td><a target='_blank'>"+data[i].pname+"</a></td>\n" +
										"<td>￥"+data[i].shop_price+"</td>\n" +
										"<td>"+data[i].count+"</td>\n" +
										"<td><span class='subtotal'>￥"+data[i].subtotal+"</span></td>\n" +
										"</tr>";
							}

							$("#showDivTab").html(content);
							$("#shodDivOid").html(oid);

						},
						"json"

				)
			}

			function closeLayer() {
				// console.log("closeLayer")
				// $("#loading").show();
			}
			
		</script>
		
	</HEAD>
	<body>
	
		<form id="Form1" name="Form1" action="${pageContext.request.contextPath}/user/list.jsp" method="post">
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>订单列表</strong>
						</TD>
					</tr>



					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="10%">
										序号
									</td>
									<td align="center" width="10%">
										订单编号
									</td>
									<td align="center" width="10%">
										订单金额
									</td>
									<td align="center" width="10%">
										收货人
									</td>
									<td align="center" width="10%">
										订单状态
									</td>
									<td align="center" width="50%">
										订单详情
									</td>
								</tr>

								<c:forEach items="${pageBean.list}" var="order" varStatus="vs">
									<tr onmouseover="this.style.backgroundColor = 'white'"
										onmouseout="this.style.backgroundColor = '#F5FAFE';">
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="18%">
											${vs.index}
										</td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="17%">
											${order.oid}
										</td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="17%">
											${order.total}
										</td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="17%">
											${order.name}
										</td>
										<td style="CURSOR: hand; HEIGHT: 22px" align="center"
											width="17%">
											${order.state==0?'未付款':'已付款'}
										</td>
										<td align="center" style="HEIGHT: 22px">
											<input type="button" value="订单详情" class="clickedElement" onclick="findOrderInfoByOid('${order.oid}')"/>
										</td>
									</tr>
								</c:forEach>

								
							</table>
						</td>
					</tr>
					
				</TBODY>
			</table>
		</form>
		
		<!-- 弹出层 HaoHao added -->
        <div id="showDiv" class="blk" style="display:none;">
            <div class="main">
                <h2>订单编号：<span id="shodDivOid" style="font-size: 13px;color: #999">123456789</span></h2>
                <a href="javascript:void(0);" id="closeBtn" class="closeBtn" onclick="closeLayer()">关闭</a>
				<div id="loading" style="padding-top:30px;text-align: center;">
					<img alt="" src="${pageContext.request.contextPath }/images/loading.gif" style="width: 160px;height: 160px;">
				</div>
				<div style="padding:20px;">
					<table id="showDivTab" style="width:100%">
<%--						<tr id='showTableTitle'>--%>
<%--							<th width='20%'>图片</th>--%>
<%--							<th width='25%'>商品</th>--%>
<%--							<th width='20%'>价格</th>--%>
<%--							<th width='15%'>数量</th>--%>
<%--							<th width='20%'>小计</th>--%>
<%--						</tr>--%>

<%--						<tr style='text-align: center;'>--%>
<%--							<td>--%>
<%--								<img src='${pageContext.request.contextPath }/products/1/c_0014' width='70' height='60'>--%>
<%--							</td>--%>
<%--							<td><a target='_blank'>电视机</a></td>--%>
<%--							<td>￥3000</td>--%>
<%--							<td>3</td>--%>
<%--							<td><span class='subtotal'>￥9000</span></td>--%>
<%--						</tr>--%>
<%--						<tr style='text-align: center;'>--%>
<%--							<td>--%>
<%--								<img src='${pageContext.request.contextPath }/products/1/c_0014' width='70' height='60'>--%>
<%--							</td>--%>
<%--							<td><a target='_blank'>电视机</a></td>--%>
<%--							<td>￥3000</td>--%>
<%--							<td>3</td>--%>
<%--							<td><span class='subtotal'>￥9000</span></td>--%>
<%--						</tr>--%>
					</table>
				</div>
            </div>
        </div>
		
		
	</body>
</HTML>

