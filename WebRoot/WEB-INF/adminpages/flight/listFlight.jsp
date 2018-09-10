<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询航班信息</title>



<link href="${pageContext.request.contextPath }/css/sys.css"
	type="text/css" rel="stylesheet" />
</head>

<body>
	<table border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td class="topg"></td>
		</tr>
	</table>

	<table border="0" cellspacing="0" cellpadding="0" class="wukuang">
		<tr>
			<td width="1%"><img
				src="${pageContext.request.contextPath }/images/tleft.gif" /></td>
			<td width="33%" align="left">[查询航班信息]</td>
			<td width="63%" align="right">
			  <a href="${pageContext.request.contextPath }/admin/flight?method=addOrEditFlightUI"><img src="${pageContext.request.contextPath }/images/button/tianjia.gif" /></a>
				<!-- 高级查询 --> <a class="butbg"
				href="javascript:document.getElementById('myform').submit()"><img
					src="${pageContext.request.contextPath }/images/button/gaojichaxun.gif" /></a>
			</td>
			<td width="3%" align="right"><img
				src="${pageContext.request.contextPath }/images/tright.gif" /></td>
		</tr>
	</table>

	<!-- 查询条件：添加或选择马上查询 -->
	<form id="myform" action="${pageContext.request.contextPath }/admin/flight?method=listFlight"
		method="post">
		<input id="currentPage" type="hidden" name="currentPage" value="1" /> 
		<table width="88%" border="0" style="margin: 20px;">
			<tr>
				<td width="80px">查询条件：</td>
				<td width="300px"><input name="condition" size="20" value="${condition }" />（出发城市|到达城市）</td>
				<td></td>
			</tr>
		</table>
	</form>

	<table border="0" cellspacing="0" cellpadding="0"
		style="margin-top:5px;">
		<tr>
			<td><img
				src="${pageContext.request.contextPath }/images/result.gif" /></td>
		</tr>
	</table>
	<table width="100%" border="1">
		<tr class="henglan" style="font-weight:bold;">
			<td width="7%" height="50px" align="center">航班编号</td>
			<td width="7%" height="50px" align="center">航空公司</td>
			<td width="7%" height="50px" align="center">机型</td>
			<td width="7%" height="50px" align="center">机名</td>
			<td width="7%" height="50px" align="center">出发城市</td>
			<td width="7%" height="50px" align="center">到达城市</td>
			<td width="7%" height="50px" align="center">出发机场</td>
			<td width="7%" height="50px" align="center">到达机场</td>
			<td width="7%" height="50px" align="center">出发时间</td>
			<td width="7%" height="50px" align="center">到达时间</td>
			<td width="7%" height="50px" align="center">头等舱数量/价格</td>
			<td width="7%" height="50px" align="center">商务舱数量/价格</td>
			<td width="8%" height="50px" align="center">经济舱数量/价格</td>
			<td width="8%" height="50px" align="center">编辑</td>
		</tr>
		<span style="color:red">${msg }</span>
		<!-- 循环开始 -->
		<c:forEach items="${flights.data }" var="flight" >
					<tr class="tabtd2">
						<td align="center">${flight.id }</td>
						<td align="center">${flight.aircompany }</td>
						<td align="center">${flight.airtype }</td>
						<td align="center">${flight.airname }</td>
						<td align="center">${flight.startcity }</td>
						<td align="center">${flight.endcity }</td>
						<td align="center">${flight.startairport }</td>
						<td align="center">${flight.endairport }</td>
						<td align="center">${flight.starttime }</td>
						<td align="center">${flight.endtime }</td>
						<td align="center">${flight.firstclassnum }张/${flight.firstprice }元</td>
						<td align="center">${flight.secondclassnum }张/${flight.secondprice }元</td>
						<td align="center">${flight.thirdclassnum }/张${flight.thirdprice }元</td>
						<td align="center"><a href="${pageContext.request.contextPath }/admin/flight?method=addOrEditFlightUI&id=${flight.id }"><img
								src="${pageContext.request.contextPath }/images/button/modify.gif"
								class="img" /></a></td>
					</tr>
		</c:forEach>
	</table>


	<!-- 分页 -->
	<table border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td align="right" style=" padding-top: 20px;padding-right: 20px; ">
				<a href="javascript:void(0)" onclick="showpage(1)">首页</a>
				<c:forEach var="pagenum" begin="${flights.start }" end="${flights.end }">
					<!-- 获取 地址栏  param.name获取地址栏参数值-->
					<c:if test="${pagenum!=0 }">
						<c:choose>
							<c:when test="${flights.currentpage!=pagenum }">
								<a href="javascript:void(0)" onclick="showpage(${pagenum })">${pagenum }</a>
							</c:when>
							<c:otherwise>
								<a>${pagenum }</a>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach> <a href="javascript:void(0)" onclick="showpage(${flights.totalpages})">尾页</a>
			</td>
		</tr>
	</table>
	<script type="text/javascript">
	function showpage(num){
	document.getElementById("currentPage").value=num;
	document.forms[0].submit();
	}
	</script>
</body>
</html>
