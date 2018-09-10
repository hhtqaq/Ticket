<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="head.txt"%>
<base href="<%=basePath%>">
<title></title>
 <script src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
$(function(){
	var startcity=$("#dropstartcity");
	var endcity=$("#dropendcity");
	$.ajax({
	url:"${pageContext.request.contextPath }/booking?method=findstartcity&time="+(new Date().getTime()),
	data:null,
	dataType:"json",
	type:"post",
	success:function(data){
	startcity.empty();
	endcity.empty();
	startcity.append("<option value=''>全部城市</option>")
	endcity.append("<option value=''>全部城市</option>");
	for(var i in data){
	startcity.append("<option value='"+data[i].name+"'>"+data[i].name+"</option>");
	endcity.append("<option value='"+data[i].name+"'>"+data[i].name+"</option>");
	}
	}});
	
	var dropAirlines=$("#dropAirlines");
	$.ajax({
	url:"${pageContext.request.contextPath }/admin/flight?method=findAllCompany&time="+(new Date().getTime()),
	data:null,
	dataType:"json",
	type:"post",
	success:function(data){
	dropAirlines.empty();
	dropAirlines.append("<option value=''>全部航司</option>")
	for(var i in data){
	dropAirlines.append("<option value='"+data[i].name+"'>"+data[i].name+"</option>");
	}
	}});
}
);

function change(){
	var startcity=$("#dropstartcity");
	var endcity=$("#dropendcity");
	var start=startcity.val();
	var end=endcity.val();
	startcity.val(end);
	endcity.val(start);
}
</script>
</head>
<body class="bg-body">
	<!-- header -->
	<nav class="navbar navbar-default navbar-fixed-top bg-white"> <!-- 小导航 -->
	<nav class="navbar navbar-default"
		style=" min-height:30px; line-height:30px; margin-bottom:0px; border-radius:0;">
	<div class="container font12">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<!--<span style=" margin-left:-15px; margin-right:20px;">天空任我行</span>-->
		</div>
		<ul class="nav navbar-nav nav-top-small" style="margin-left:-15px;">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false">您好，${sessionScope.user.username } <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="系统管理/修改密码.html">修改密码</a></li>
					<li><a href="${pageContext.request.contextPath}/user?method=exit"">退出</a></li>
				</ul></li>
		</ul>
		<ul class="nav navbar-nav navbar-right nav-top-small">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false"> <i class="icon iconfont font14 "
					style=" vertical-align:middle;">&#xe62b;</i> 028-12345678 <span
					class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="#">（或）028-12345678</a></li>
					<li><a href="#">（或）028-12345678</a></li>
					<!--<li role="separator" class="divider"></li>-->
				</ul></li>

		</ul>
		<ul class="nav navbar-nav navbar-right nav-top-small">
			<li><a href="帮助中心/help.html">帮助中心</a></li>
		</ul>
		<c:if test="${user==null }">
		请&nbsp;<a href="#modal-container-975191" role="button"
			data-toggle="modal">登录</a>
				</c:if>
			&nbsp;&nbsp;&nbsp; 或 &nbsp;<a
			href="#modal-container-975192" role="button" data-toggle="modal">免费注册</a>
		
	</div>
	</nav> <!-- 小导航结束 -->


	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class=" " href="差旅报告/汇总.html"><img
				src="${pageContext.request.contextPath}/css/imgs/logo.png"
				width="210" height="70" alt="系统LOGO" class="pull-left mar-right-30"
				style="margin-left:-15px;"></a>
			<!--<h4 class="navbar-brand font24" >成都万里行科技发展有限公司</h4>-->
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${pageContext.request.contextPath}/index.jsp"><i
						class="icon iconfont icon-nav-block font24">&#xe620;</i> 在线预订</a></li>
				<li><a href="${pageContext.request.contextPath}/need/OrderController"><i
						class="icon iconfont icon-nav-block font24">&#xe61c;</i> 订单管理</a></li>

			</ul>
		</div>
		<!-- /.navbar-collapse -->

	</div>
	</nav>
	<!-- header end -->
	<!-- 搜索 -->
	<div class="index-wall white " style=" ">
		<div class="container" style=" position:relative;padding-left: 100px;">
			<form class="form-inline"
				action="${pageContext.request.contextPath }/booking?method=searchFight"
				method="post">
				<div class="form-group  mar-left-8">
					<label for="">出发城市</label> <select id="dropstartcity"
						name="startcity" class="form-control" style=" width:120px;">
						
					</select>
				</div>
				<div class="form-group mar-left-10">
					<label for=""> — <a href="#" class="huan" onclick=change()>换</a> —
					</label>
				</div>
				<div class="form-group mar-left-10">
					<label for="">到达城市</label> <select id="dropendcity" name="endcity"
						class="form-control" style=" width:120px;">
					</select>
				</div>
				<div class="form-group mar-left-10">
					<label for="">出发日期</label> <input style="cursor: pointer;width:110px;" type="text" class="form-control " name="starttime"
						id="" value="<fmt:formatDate value="<%=new Date() %>"/>" placeholder="出发"
						onClick="WdatePicker()">
				</div>
				<div class="form-group mar-left-10">
				<label for="dropAirlines">航空公司</label>
					<select id="dropAirlines" class="form-control"
						style=" width:120px;" name="aircompany">
					</select>
				</div>
				<button type="submit" class="btn btn-warning mar-left-10">搜索</button>
			</form>
		</div>
	</div>
	<!-- 搜索结束 -->

	<!-- 日期开始 -->
	<div class="container mar-bottom-15 ">
		<div class=" bor-solid-1" style=" background:#EFF2F5;">
			<div class="arrow-left">
				<a href="#" style="">&nbsp;</a>
			</div>
			<div class="arrow-right">
				<a href="#" style="">&nbsp;</a>
			</div>
			<ul class="nav nav-tabs nav-justified " id="date">
			
				<li role="presentation" class="active"><a href="#">08-09<br>周四
				</a></li>
				<li role="presentation"><a href="#">08-10<br>周五
				</a></li>
				<li role="presentation"><a href="#">08-11<br>周六
				</a></li>
				<li role="presentation"><a href="#">08-12<br>周日
				</a></li>
				<li role="presentation"><a href="#">08-13<br>周一
				</a></li>
				<li role="presentation"><a href="#">08-14<br>周二
				</a></li>
				<li role="presentation"><a href="#">08-15<br>周三
				</a></li>
			</ul>
		</div>
	</div>
	<!-- 日期结束 -->
<div style=" height:300px;border:1px solid white ; " >

</div>
		<hr>
			<div>
				<div style="">
					<a href="http://www.qunar.com/site/zh/Qunar.in.China_1.2.shtml"
						target="_blank" rel="nofollow" class="about-link"> <span>|</span><a
						href="http://www.928383.com/?from=3W" target="_blank"
						rel="nofollow">酒店分销商加盟</a><span> |</span><a
						href="http://www.qunar.com/site/zh/Cooperate_4.shtml"
						target="_blank" rel="nofollow">业务合作</a><span>|</span><a
						href="http://qunar.zhiye.com/" target="_blank" rel="nofollow">加入我们</a><span>|</span>
						<a href="http://help.qunar.com/complaint.html" class=""
						target="_blank" rel="nofollow">"严重违规失信"专项整治举报</a><span>|</span><a
						href="http://security.qunar.com" target="_blank" rel="nofollow">安全中心</a><span>|</span>
						<a href="http://www.qunar.com/commonweal/index.html"
						target="_blank">星骆驼公益</a><span>|</span><a
						href="http://www.qunar.com/site/en/Qunar.in.China_1.1.shtml"
						target="_blank" rel="nofollow">About Us</a>
				</div>
				<p class="copyright">
					<span>Copyright &copy;2018 Qunar.com</span><span>京公网安备110108901530</span><a
						href="http://www.miibeian.gov.cn/" target="_blank" rel="nofollow">京ICP备05021087号</a><a
						href="http://www.qunar.com/site/company_info.htm" target="_blank"
						rel="nofollow">营业执照信息</a> <a
						href="http://baoxian.qunar.com/html/notify_zct.html"
						target="_blank" rel="nofollow">保险经纪资质</a><a
						href="http://www.qunar.com/site/company_drug_info.htm"
						target="_blank" rel="nofollow">(京)-非经营性-2016-0110</a> <span
						class="s24">天空任务行网客服电话95117</span>
				</p>
			</div>
		</div>

	<!-- 列表结束 -->

	<script type="text/javascript">
		$(document).ready(function() {
			$(".flip").click(function() {
				$(".panel").slideToggle("");
			});
		});
	</script>
</body>
</html>
