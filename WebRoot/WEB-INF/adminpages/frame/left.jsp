<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<HTML><HEAD>
<link href="${pageContext.request.contextPath}/css/dtree.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dtree.js"></script>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
</HEAD>
<BODY bgColor=#DDF0FB leftMargin=0 topMargin=0 marginwidth="0" marginheight="0">
<table width="90%" border="0" cellspacing="1" cellpadding="2" align="center" >
<tr>
<div class="dtree">
	<script type="text/javascript">
		d = new dTree('d','${pageContext.request.contextPath}');
		d.add('01','-1','机场后台管理系统');
		d.add('0101','01','客户管理');
		/* 状态=="咨询中"*/
		d.add('010101','0101','查询客户信息','${pageContext.request.contextPath }/admin/customer?method=listCustomer','','right');
		/* 状态=="已报名"*/
		
		
		d.add('0102','01','航班管理');
		d.add('010201','0102','查询航班列表','${pageContext.request.contextPath }/admin/flight?method=listFlight','','right');
		d.add('010202','0102','查询城市列表','${pageContext.request.contextPath }/admin/flight?method=listCity','','right');
		d.add('010203','0102','查询航空公司列表','${pageContext.request.contextPath }/admin/flight?method=listAirCompany','','right');
		
		

		
		document.write(d);
	</script>
</div>
</tr>
</table>
</BODY>
</HTML>
      			
			


      