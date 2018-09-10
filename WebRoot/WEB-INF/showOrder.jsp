<%@page import="com.ecjtu.rwx.bean.QueryCase"%>
<%@page import="com.ecjtu.rwx.bean.Airticket"%>
<%@page import="com.ecjtu.rwx.bean.Flight"%>
<%@page import="com.ecjtu.rwx.vo.OrderItemVo"%>
<%@page import="com.ecjtu.rwx.bean.Orderitem"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ecjtu.rwx.vo.OrderVo"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta charset="utf-8">
<!-- IE 浏览器运行最新的渲染模式-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 启用响应式特性 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 双核使用webkit内核 -->
<meta name="renderer" content="webkit">
<title>订单列表</title>
<link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path %>/css/common.css" rel="stylesheet">
<link href="<%=path %>/css/corptravel.css" rel="stylesheet">
<link href="<%=path %>/css/enterprise.css" rel="stylesheet">
<!-- IconFont图标 -->
<link href="<%=path %>/css/iconfont.css" rel="stylesheet">
<script type="text/javascript" src="<%=path %>/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/skty.js"></script>
<!-- 兼容IE8 -->
<!--[if lte IE 9]>
<script type="text/javascript" src="../../js/html5shiv.min.js"></script>
<script type="text/javascript" src="../../js/respond.min.js"></script>
<![endif]-->
<!-- layer弹框 2.1 -->
<script type="text/javascript" src="<%=path %>/js/layer/layer.js"></script>

<style>
.tabs { width:35px; padding:3px; ; background-color:#db514f; color:#fff; text-align:center; margin-top:2px; margin-bottom:2px; border-radius:3px; }
.startbox { overflow:hidden; margin-bottom:5px; }
.start { float:left; background-color:#f9a60a; padding:2px; color:#fff; border-radius:2px; margin-right:5px; }
.destination { float:left; background-color:#0096de; padding:2px; color:#fff; border-radius:2px; margin-right:5px; }
.startcity { float:left; font-size:12px; color:#666; }
.startcity span { font-size:14px; color:#000; font-weight:bold; }
.destination1 {float:left; background-color:#0096de; padding:2px; color:#fff; border-radius:2px; margin-right:5px; }
.destination2 {float:left; background-color:#0096de; padding:2px; color:#fff; border-radius:2px; margin-right:5px; }
</style>
</head>
<body class="bg-body">
<!-- header -->
<!-- 小导航 -->
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
				aria-expanded="false">您好，${sessionScope.user.username }<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="系统管理/修改密码.html">修改密码</a></li>
					<li><a href="${pageContext.request.contextPath}/user?method=exit">退出</a></li>
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
		请&nbsp;<a id="login" href="#modal-container-975191" role="button"
			data-toggle="modal">登录</a>
			&nbsp;&nbsp;&nbsp;或
			</c:if>
			 &nbsp;<a
			href="#modal-container-975192" role="button" data-toggle="modal">免费注册</a>
			
	</div>
</nav> <!-- 小导航结束 -->
<!-- 小导航结束 -->

  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class=" " href="${pageContext.request.contextPath}/index.jsp" ><img src="${pageContext.request.contextPath}/css/imgs/logo.png" width="210" height="70" alt="系统LOGO" class="pull-left mar-right-30" style="margin-left:-15px;" ></a>
      <!--<h4 class="navbar-brand font24" >成都万里行科技发展有限公司</h4>-->
    </div>
    
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${pageContext.request.contextPath}/index.jsp"><i
						class="icon iconfont icon-nav-block font24">&#xe620;</i> 在线预订</a></li>
				<li><a href="${pageContext.request.contextPath}/need/OrderController"><i
						class="icon iconfont icon-nav-block font24">&#xe61c;</i> 订单管理</a></li>

			</ul>
    </div><!-- /.navbar-collapse -->
    
  </div>
</nav>
<!-- header end -->
<!-- 管理模板 -->
<div class="container bg-gray-eee box-shadow mar-bottom-30" style="padding-right:0px; padding-left:0px; position:relative; margin-top:120px;">

  <!-- main -->
  <div class="rightCon" style="">
    <!-- 引导 -->
    <ol class="breadcrumb">
      <li>首页</li>
      <li>订单管理</li>
      <li class="active">订单列表</li>
    </ol>
    
    
    
   <form method="post" action="OrderController">
    <!-- 引导结束 -->
     <div class="row">
      <div class="col-sm-4">
        <div class="form-group" >
          <label for="apdiv" class="w120 text-right">预定时间</label>
          <input type="date" id="order_startT"  class="form-control input-sm" style=" width:90px; display:inline-block;"  placeholder="日期">
          &nbsp;至&nbsp;
          <input type="date" id="order_endT" value=""  class="form-control input-sm" style=" width:90px; display:inline-block;"  placeholder="日期">
       		<input type="hidden" id="os" name="order_startTime" />
       		<input type="hidden" id="oe" name="order_endTime" />
        </div>
         <script>
    	function processDate(){
		    	var dateStr=$("#order_startT").val();	
		    	if(dateStr!=""&&dateStr!=undefined)
		    	$("#os").val((new Date(dateStr)).getTime());
		    	var dateStr2=$("#order_endT").val();
		    	if(dateStr2!=""&&dateStr2!=undefined)
		    	$("#oe").val((new Date(dateStr2)).getTime());
		    		return true;
    	}
    	
    </script>
      </div>
      
       <div class="col-sm-4">
        <div class="form-group">
          <label for="apdiv2" class="w120 text-right">起飞日期</label>
          <input type="date" name="flight_startTime" class="form-control input-sm" style=" width:90px; display:inline-block;" id="input" placeholder="日期">
          &nbsp;至&nbsp;
          <input type="date" name="flight_endTime"  class="form-control input-sm" style=" width:90px; display:inline-block;" id="input" placeholder="日期">
        </div>
      </div>
     
    </div>
    <div class="row">
     
      <div class="col-sm-4">
        <div class="form-group">
          <label for="apdiv" class="w120 text-right">订单状态</label>
          <select name="order_ispaid" class="form-control input-sm" style=" width:200px; display:inline-block;">
           	<option value="">未选择 </option>
            <option value="1">已支付</option>
            <option value="0">未支付</option>
          </select>
        </div>
      </div>
      
      <div class="col-sm-4">
        <div class="form-group">
          <label for="apdiv" class="w120 text-right">起降城市</label>
          <input type="text" value="${cases.flight_startCity }" name="flight_startCity" class="form-control input-sm" style=" width:90px; display:inline-block;" id="" placeholder="城市">
          &nbsp;至&nbsp;
          <input type="text" value="${cases.flight_endCity }" name="flight_endCity" class="form-control input-sm" style=" width:90px; display:inline-block;" id="" placeholder="城市">
        </div>
      </div>
      
    </div>
    <div class="row">
      
      <div class="col-sm-4">
        <div class="form-group">
          <label for="apdiv" class="w120 text-right">联系人</label>
          <input type="text" value="${cases.order_purchasername }" name="order_purchasername" class="form-control input-sm" style=" width:200px; display:inline-block;" id="" placeholder="姓名">
        </div>
      </div>
      
    </div>
   
    <div class="text-center mar-top-10 bor-top-solid-1 pad-top-10 mar-bottom-10">
      <input type="hidden" name="method" value="showPage" />
      <button type="submit" onclick="return processDate()" class="btn btn-danger btn-sm mar-right-20">查询</button>
      <button type="reset" class="btn btn-default btn-sm mar-right-20">清空条件</button>
    </div>
    
    </form>
    
    <table border="0" cellspacing="0" cellpadding="0" class="table table-hover table-striped font12 table-bordered v-align-top" >
      <tr >
        <th>创建时间</th>
        <th>乘机人</th>
        <th>航班号</th>
        <th>舱位</th>
        <th>起飞行程</th>
        <th>到达行程</th>
        <th>结算总价</th>
        <th style="width:65px;">订单状态</th>
        <th>操作</th>
      </tr>
      
      
   
	<%
		List<OrderVo> orderVos=(List<OrderVo>)request.getAttribute("orderVos");
		if(orderVos!=null&&orderVos.size()>0){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2=new SimpleDateFormat("HH:mm");
		SimpleDateFormat format3=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(OrderVo orderVo:orderVos){
			Date oD=new Date(Long.parseLong(orderVo.getOrder().getTime()));
			String orderDate=format.format(oD);
			String hours=format2.format(oD);
			Flight flight=orderVo.getOrderitemVos().get(0).getAirticketVo().getFlight();
			Airticket airticket=orderVo.getOrderitemVos().get(0).getAirticketVo().getAirticket();
			
	 %>
      <tr>
        <td><%=orderDate %>
          <br><%=hours %></td>
       
        <td>
         <%
          for(OrderItemVo orderitemVo:orderVo.getOrderitemVos()){ %>
          <p><%=orderitemVo.getPassenger().getName() %></p>
          <%} %>
          </td>
     
          
        <td><%=flight.getAirname() %></td>
        <td><%=airticket.getSeattype()%></td>
        <td><div class="startbox">
           
            <div class="startcity"><span><%=flight.getStartcity() %></span> <%=flight.getStartairport() %><br/>
              <%=flight.getStarttime() %></div>
          </div></td>
        <td><div class="startbox">
          
          <div class="startcity"><span><%=flight.getEndcity() %></span> <%=flight.getEndairport() %><br/>
            <%=flight.getEndtime() %></div>
        </div></td>
        <td><%=orderVo.getOrder().getTotalprice() %></td>
        <td><%=orderVo.getOrder().getIspaid().equals("1")?"已支付":"未支付" %></td>
        <td><a href="${pageContext.request.contextPath}/need/OrderController?method=showDetail&orderId=<%=orderVo.getOrder().getId() %>">【查看详情】</a><br>
          <a href="javascript:;" onclick="cancelOrder(this,<%=orderVo.getOrder().getId() %>)" class="quxiao"><%=orderVo.getOrder().getIspaid().equals("1")?"":"【取消订单】"%></a><br>
          <a href="${pageContext.request.contextPath}/need/OrderController?method=gotoPay&orderId=<%=orderVo.getOrder().getId() %>" target="_blank" ><%=orderVo.getOrder().getIspaid().equals("1")?"":"【立即支付】" %></a></td>
      </tr>
      <%
       } 
       }
       %>
    </table>
    
    <script>
						function cancelOrder(th,orderId) {
							showBox(th,orderId);
						}

						function showBox(th,data) { //取消订单
							layer.confirm('您确定要取消该订单？', {
								title: '取消订单',
								btn: ['确定', '取消'] //按钮
							}, function() {
								$.ajax({
										type: "get",
										url: "${pageContext.request.contextPath}/need/OrderController?time="+(new Date().getTime()),
										data: {
											"method": "deleteOrder",
											"orderId": data
										},
										async: true,
										success: function(da) {
											if (da == "true") {
												$(th).parents("tr").eq(0).remove();
												layer.msg('该订单已取消', {icon: 1});
											} else {
												layer.msg('订单取消失败', {icon: 1});
											}
										}
										});
								});
							}					
					</script>
    
    <div class="clearfix"></div>
  </div>
</div>
<!-- main end -->
</div>
<!-- 管理模板 结束 -->



<!-- 退票弹框 -->
<div id="rev" style="display:none">
  <div class="modal-body" >
    <div class="pad-10">
    <h4>行程信息</h4>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered mar-bottom-10">
  <tr class=" bg-gray-f5">
    <th><input name="" type="checkbox" value=""></th>
    <th>航班号</th>
    <th>起飞时间</th>
    <th>到达时间</th>
    <th>行程</th>
  </tr>
  <tr>
    <td><input name="" type="checkbox" value=""></td>
    <td>CA8954</td>
    <td>2014-10-18 7:50:00</td>
    <td>2014-10-18 12:25:00</td>
    <td>成都双流 - 上海浦东</td>
  </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-bordered mar-bottom-10">
  <tr class=" bg-gray-f5">
    <th><input name="" type="checkbox" value=""></th>
    <th>姓名</th>
    <th>乘客类型</th>
    <th>证件类型</th>
    <th>证件号码</th>
    <th>机建/燃油</th>
    <th>企业结算价</th>
  </tr>
  <tr>
    <td><input name="" type="checkbox" value=""></td>
    <td>荣思佳</td>
    <td>成人</td>
    <td>身份证</td>
    <td>510301198808081018</td>
    <td>￥100/￥280</td>
    <td>￥1440</td>
  </tr>
  <tr>
    <td><input name="" type="checkbox" value=""></td>
    <td>李冬阳</td>
    <td>成人</td>
    <td>身份证</td>
    <td>510301198808081018</td>
    <td>￥100/￥280</td>
    <td>￥1440</td>
  </tr>
</table>
  <form class="form-horizontal">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-3 control-label">退票原因</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="inputEmail3" placeholder="请输入退票理由">
    </div>
  </div>
</form>
  
    </div>
  </div>
  <div class="modal-footer mar-top-5">
    <button type="button" class="btn btn-primary" onClick="layer.closeAll()">申请退票</button>
    </a>
<button type="button" class="btn btn-default"  onClick="layer.closeAll()" >关闭</button>
  </div>
</div>

<!-- 申请改签弹框 -->
<div id="gaiqian" style="display:none">
  <div class="modal-body" >
    <form class="form-horizontal">
      <div class="form-group">
        <label for="" class="col-sm-4 control-label">改签乘机人：</label>
        <div class="col-sm-6">
          <label class="checkbox-inline" style=" margin-left:10px;">
            <input type="checkbox" id="inlineCheckbox1" value="option1"> 孙靖
          </label>
           <label class="checkbox-inline">
            <input type="checkbox" id="inlineCheckbox1" value="option1"> 尚丁明
          </label>
           <label class="checkbox-inline">
            <input type="checkbox" id="inlineCheckbox1" value="option1"> 胡家富
          </label>
           <label class="checkbox-inline">
            <input type="checkbox" id="inlineCheckbox1" value="option1"> 刘述军
          </label>
           <label class="checkbox-inline">
            <input type="checkbox" id="inlineCheckbox1" value="option1"> 王博玉
          </label>
        </div>
      </div>
      
      <div class="form-group">
        <label for="" class="col-sm-4 control-label">期望日期：</label>
        <div class="col-sm-6">
           <input type="text" class="form-control" id="" placeholder="请选择日期" onClick="WdatePicker()">
        </div>
      </div>
      
      <div class="form-group">
        <label for="" class="col-sm-4 control-label">期望时间：</label>
        <div class="col-sm-6">
          <select name="select" class="form-control " >
            <option value="">不限</option>
            <option value="">上午</option>
            <option value="">中午</option>
            <option value="">下午</option>
            <option value="">晚上</option>
            <option value="">凌晨</option>
          </select>
          
        </div>
      </div>
      <div class="form-group">
        <label for="" class="col-sm-4 control-label">期望航司：</label>
        <div class="col-sm-6">
          <select name="select" class="form-control " >
            <option value="">不限</option>
            <option value="">中国航空</option>
            <option value="">南方航空</option>
            <option value="">四川航空</option>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label for="" class="col-sm-4 control-label">期望仓位：</label>
        <div class="col-sm-6">
          <select name="select" class="form-control " >
            <option value="">不限</option>
            <option value="">经济舱</option>
            <option value="">头等舱</option>
          </select>
        </div>
      </div>
      
      <div class="form-group">
        <label for="" class="col-sm-4 control-label">备注说明：</label>
        <div class="col-sm-6">
           <input type="text" class="form-control" id="" placeholder="限255字" >
        </div>
      </div>
    </form>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-primary" onClick="submittip()">确 定</button>
    <button type="button" class="btn btn-default"  onClick="layer.closeAll()">关 闭</button>  
  </div>
</div>

<script>
$(document).ready(function() {		
	
	
	$(".tuipiao").click(function(){  //申请退票
	  layer.open({
	  type: 1,
	  title: '申请退票',
      area: ['auto', 'auto'],
      fix: false, //不固定
      maxmin: false,
      content: $('#rev'),
      });
	});
	
	$(".qiye09").click(function(){  //申请改签
	   layer.open({
       type: 1,
       shift: 2,  //出场动画
       area: ['500px', 'auto'],
	   title :'申请改签',
       shadeClose: true, //开启遮罩关闭
       content: $('#gaiqian'),
       });
	});
		
});
</script>
</body>
</html>
