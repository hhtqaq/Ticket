<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <td width="44%" align="left">[查看订单详情信息]</td>
   
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
     <td width="7%" align="center">乘客姓名</td>
       <td width="7%" align="center">身份证</td>
    <td width="7%" align="center">乘客类型</td>
  	<td width="7%" align="center">机票价格</td> 
       <td width="7%" align="center">舱位</td>
    <td width="7%" align="center">航空公司</td>
    <td width="7%" align="center">机型</td>
    <td width="7%" align="center">机名</td>
  	<td width="7%" align="center">出发城市</td>
  	<td width="7%" align="center">到达城市</td>
  	<td width="7%" align="center">出发时间</td>
  	<td width="7%" align="center">到达时间</td>
  	<td width="8%" align="center">出发机场</td>
  	<td width="8%" align="center">抵达机场</td>
  </tr>
  <c:forEach items="${orderitems }" var="orderitem" >
  <tr class="tabtd1">
	<td width="7%" align="center">${orderitem.name }</td>
    <td width="7%" align="center">${orderitem.idcard }</td>
    <td width="7%" align="center">${orderitem.type }</td>
    <td width="7%" align="center"> 
    <c:choose>
     <c:when test="${orderitem.type=='成人' }">${orderitem.price }
     </c:when>
    <c:when test="${orderitem.type=='儿童' }">${orderitem.price*0.5 }
     </c:when>
     <c:otherwise>
     ${orderitem.price*0.8 }
     </c:otherwise>
     </c:choose> </td>
    <td width="7%" height="50px" align="center">${orderitem.seattype }</td>
    <td width="7%" height="50px" align="center">${orderitem.aircompany }</td>
    <td width="7%" height="50px" align="center">${orderitem.airtype }</td>
    <td width="7%" height="50px" align="center">${orderitem.airname }</td>
    <td width="7%" height="50px" align="center">${orderitem.startcity }</td>
    <td width="7%" height="50px" align="center">${orderitem.endcity }</td>
    <td width="7%" height="50px" align="center">${orderitem.starttime }</td>
    <td width="7%" height="50px" align="center">${orderitem.endtime }</td>
    <td width="8%" height="50px" align="center">${orderitem.startairport }</td>
    <td width="8%" height="50px" align="center">${orderitem.endairport }</td>
  </tr>
  </c:forEach>
</table>
</body>
</html>
