<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ecjtu.rwx.bean.Order"%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<link href="${pageContext.request.contextPath }/css/sys.css" type="text/css" rel="stylesheet" />

</head>

<body class="emp_body">
 <table border="0" cellspacing="0" cellpadding="0" width="100%">
  <tr>
    <td class="topg"></td>
  </tr>
</table>


<table border="0" cellspacing="0" cellpadding="0"  class="wukuang"width="100%">

  <tr>
    <td width="1%"><img src="${pageContext.request.contextPath}/images/tleft.gif"/></td>
    <td width="44%" align="left">[查看订单信息]</td>
   
    <td width="52%"align="right">
    	<!--<a href="listLog.html"><img src="${pageContext.request.contextPath}/images/button/find.gif" class="img"/></a>
        <a href="addLog.html"><img src="${pageContext.request.contextPath}/images/button/add.gif" class="img"/></a>~-->
       <!-- <a href="#"><img src="${pageContext.request.contextPath}/images/button/close.gif" class="img"/></a>-->
       <a href="javascript:history.back(-1)"><img src="${pageContext.request.contextPath}/images/button/tuihui.gif" /></a>
    </td>
    <td width="3%" align="right"><img src="${pageContext.request.contextPath}/images/tright.gif"/></td>
  </tr>
</table>
<table border="0" cellspacing="0" cellpadding="0"
		style="margin-top:5px;">
		<tr>
			<td><img
				src="${pageContext.request.contextPath }/images/result.gif" /></td>
		</tr>
	</table>
<table width="100%" border="1" >
  
  <tr class="henglan" style="font-weight:bold;">
     <td width="10%" align="center">联系人</td>
       <td width="10%" align="center">联系人电话</td>
    <td width="10%" align="center">订单时间</td>
  	<td width="10%" align="center">保险金额</td> 
       <td width="10%" align="center">总价</td>
    <td width="10%" align="center">是否支付</td>
  	<td width="5%" align="center">查看</td>
  </tr>
  <%
 		List<Order> orders=(List<Order>)request.getAttribute("orders");
 		List<String> times=new ArrayList<String>();
 		for(Order order:orders ){
 		  Long time=Long.parseLong(order.getTime());
 		  Date date=new Date(time);
 		  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
 		  String fdate=sdf.format(date);
 		  times.add(fdate);
 		}
 		request.setAttribute("times", times);
   %>
  <c:forEach items="${orders }" var="order" varStatus="status">
  <tr class="tabtd2">
	<td height="50px" align="center">${order.purchasername }</td>
    <td height="50px" align="center">${order.purchaserphone }</td>
    <td height="50px" align="center">${times[status.index] }</td>
    <td height="50px" align="center">${order.insurance }</td>
    <td height="50px" align="center">${order.totalprice }</td>
    <td height="50px" align="center"><c:choose>
    <c:when test="${order.ispaid==0 }">未支付</c:when>
    <c:otherwise>支付</c:otherwise>
    </c:choose> </td>
    <td height="50px"  align="center"><a href="${pageContext.request.contextPath}/admin/customer?method=listOrderItems&id=${order.id}"><img src="${pageContext.request.contextPath }/images/button/modify.gif" class="img"/></a></td>
  </tr>
  </c:forEach>
</table>


</body>
</html>
