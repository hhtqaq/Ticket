<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询航班信息</title>

<script>
	function add() {
	
	var name=prompt("请输入城市:");
	if(name!=""&&name!=undefined){
	$.ajax({
	url:"${pageContext.request.contextPath}/admin/flight?method=addCity",
	data:"name="+name,
	type:"post",
	success:function(data){
	if(data!=""){
	location.href="${pageContext.request.contextPath}/admin/flight?method=listCity"
	}
	}
	})
	}
	}
</script>

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
			<td width="33%" align="left">[查询城市信息]</td>
			<td width="63%" align="right"><a href="" onclick="add()"><img
					src="${pageContext.request.contextPath }/images/button/tianjia.gif" /></a>
				<!-- 高级查询 --> <a class="butbg"
				href="javascript:document.getElementById('myform').submit()"><img
					src="${pageContext.request.contextPath }/images/button/gaojichaxun.gif" /></a>
			</td>
			<td width="3%" align="right"><img
				src="${pageContext.request.contextPath }/images/tright.gif" /></td>
		</tr>
	</table>

	<!-- 查询条件：添加或选择马上查询 -->
	<form id="myform"
		action="${pageContext.request.contextPath }/admin/flight?method=listCity"
		method="post">
		<table width="88%" border="0" style="margin: 20px;">
			<tr>
				<td width="80px">查询条件：</td>
				<td width="300px"><input name="condition" size="20"
					value="${condition }" />（城市名称）</td>
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
			<td height="50px" align="center">城市编号</td>
			<td height="50px" align="center">城市名称</td>
		</tr>
		<span style="color:red">${msg }</span>
		<!-- 循环开始 -->
		<c:forEach items="${citys}" var="city">
			<tr class="tabtd2">
				<td align="center">${city.id }</td>
				<td align="center">${city.name }</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>
