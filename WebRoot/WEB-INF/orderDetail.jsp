<%@page import="com.ecjtu.rwx.bean.Airticket"%>
<%@page import="com.ecjtu.rwx.bean.Flight"%>
<%@page import="com.ecjtu.rwx.vo.OrderVo"%>
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
<script src="<%=path %>/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=path %>/js/bootstrap.min.js"></script>
<!-- 兼容IE8 -->
<!--[if lte IE 9]>
<script type="text/javascript" src="../../js/html5shiv.min.js"></script>
<script type="text/javascript" src="../../js/respond.min.js"></script>
<![endif]-->

<!-- layer弹框 2.1 -->
<script type="text/javascript" src="<%=path %>/js/skty.js"></script>
<script type="text/javascript" src="<%=path %>/js/layer/layer.js"></script>
<script>
$(document).ready(function() {	
	$(".qiye01").click(function(){  //查看企业
	   layer.open({
       type: 1,
       shift: 2,  //出场动画
       area: ['400px', '550px'],
	   title :'企业详情',
       shadeClose: true, //开启遮罩关闭
       content: '<div class="pad-15">  <form class="form-horizontal">    <div class="form-group">      <label for="label" class="col-sm-4 control-label"><span class="red">*</span> 公司名称</label>      <div class="col-sm-8">        <p class="form-control-static">四川龙翔国际旅行社</p>      </div>    </div>    <div class="form-group">      <label for="label4" class="col-sm-4 control-label"><span class="red">*</span> 所在城市</label>      <div class="col-sm-8">        <p class="form-control-static">成都</p>      </div>    </div>    <div class="form-group">      <label for="label" class="col-sm-4 control-label"><span class="red">*</span> 公司性质</label>      <div class="col-sm-8">        <p class="form-control-static">上市公司</p>      </div>    </div>    <div class="form-group">      <label for="label2" class="col-sm-4 control-label"><span class="red">*</span> 所属行业</label>      <div class="col-sm-8">        <p class="form-control-static">计算机/互联网/通信/电子</p>      </div>    </div>    <div class="form-group">      <label for="label3" class="col-sm-4 control-label">公司规模</label>      <div class="col-sm-8">        <p class="form-control-static">200-500人</p>      </div>    </div>    <div class="form-group">      <label for="" class="col-sm-4 control-label">固定电话</label>      <div class="col-sm-8">        <p class="form-control-static">028-12345678</p>      </div>    </div>    <div class="form-group">      <label for="" class="col-sm-4 control-label">传真</label>      <div class="col-sm-8">        <p class="form-control-static">028-12345678</p>      </div>    </div>    <div class="form-group">      <label for="" class="col-sm-4 control-label">Email</label>      <div class="col-sm-8">        <p class="form-control-static">wangbaoqiang@720u.com</p>     </div>    </div>    <div class="form-group">      <div class="col-sm-offset-4 col-sm-8">  <button type="submit" class="btn btn-primary qiye02">修改</button>        <button type="submit" class="btn btn-default" onClick="javascript:window.history.back(-1);">返回</button>      </div>    </div>  </form></div>'
       });
	});

		

		transformDate($("#orderTime"));
		
	$(".qiye02").click(function(){  //添加企业
	   layer.open({
       type: 1,
       shift: 2,  //出场动画
       area: ['400px', '550px'],
	   title :'添加企业',
       shadeClose: true, //开启遮罩关闭
       content: '<div class="pad-15">  <form class="form-horizontal">  <div class="form-group">      <label for="label" class="col-sm-4 control-label"><span class="red">*</span> 公司名称</label>      <div class="col-sm-8">        <input type="text" class="form-control" id="label" placeholder="请输入公司名称" />      </div>    </div>    <div class="form-group">      <label for="label" class="col-sm-4 control-label"><span class="red">*</span> 所在城市</label>      <div class="col-sm-8">        <input type="text" class="form-control" id="label" placeholder="请输入所在城市" />      </div>    </div>    <div class="form-group">      <label for="label" class="col-sm-4 control-label"><span class="red">*</span> 公司性质</label>      <div class="col-sm-8">        <select name="select" class="form-control">          <option>外资 </option>          <option>合资</option>          <option>国企</option>          <option>民营</option>          <option>上市公司</option>          <option>政府机关</option>          <option>事业单位</option>          <option>非盈利机关</option>        </select>      </div>    </div>    <div class="form-group">      <label for="label2" class="col-sm-4 control-label"><span class="red">*</span> 所属行业</label>      <div class="col-sm-8">        <select name="select2" class="form-control">          <option>计算机/互联网/通信/电子 </option>         <option>会计/金融/银行/保险</option>          <option>贸易/消费/制造/营运</option>          <option>制药/医疗</option>          <option>广告/媒体</option>          <option>房地产/建筑</option>          <option>专业服务/教育/培训</option>          <option>服务业</option>          <option>物流/运输</option>          <option>能源/原材料</option>          <option>政府/非盈利机构/其他</option>        </select>      </div>    </div>    <div class="form-group">      <label for="label3" class="col-sm-4 control-label">公司规模</label>      <div class="col-sm-8">        <select name="select3" class="form-control">          <option>少于50人 </option>          <option>50-100人</option>          <option>100-200人</option>          <option>200-500人</option>          <option>500-1000人</option>          <option>1000人以上</option>        </select>      </div>    </div>    <div class="form-group">     <label for="" class="col-sm-4 control-label">固定电话</label>      <div class="col-sm-8">        <input type="text" class="form-control" id="" placeholder="请输入座机号">      </div>    </div>    <div class="form-group">     <label for="" class="col-sm-4 control-label">传真</label>      <div class="col-sm-8">        <input type="text" class="form-control" id="" placeholder="请输入QQ号">      </div>    </div>    <div class="form-group">      <label for="" class="col-sm-4 control-label">Email</label>      <div class="col-sm-8">        <input type="text" class="form-control" id="" placeholder="请输入Email">      </div>    </div>    <div class="form-group">      <div class="col-sm-offset-4 col-sm-8">        <button type="submit" class="btn btn-primary">确定</button>        <button type="submit" class="btn btn-default" onClick="javascript:window.history.back(-1);">取消</button>      </div>    </div></form></div>'
       });
	});
	
	$(".qiye03").click(function(){  //组织架构
	   layer.open({
       type: 2,
       shift: 2,  //出场动画
       area: ['800px', '80%'],
	   title :'组织架构',
       shadeClose: true, //开启遮罩关闭
       content: '新组织架构.html'
       });
	});
	
	$(".qiye04").click(function(){  //参数设置
	   layer.open({
       type: 1,
       shift: 2,  //出场动画
       area: ['360px', '180px'],
	   title :'参数设置',
       shadeClose: true, //开启遮罩关闭
       content: '<div class=" pad-15 "><form class="form-horizontal">        <div class="form-group">            <label for="inputEmail3" class="col-sm-4 control-label">公司统一后返</label>            <div class="col-sm-8">              <input type="text" class="form-control w100 mar-right-10 " style=" display:inline-block;" id="inputEmail3" placeholder="费率">‰                    </div>          </div> <div class="form-group">            <div class="col-sm-offset-4 col-sm-8">              <button type="submit" class="btn btn-primary" onClick="javascript: history.go(-1);">确定</button>            </div>          </div>        </form>          </div>'
       });
	});
	
	$(".qiye05").click(function(){  //企业状态
	   layer.confirm('您确定要禁用该企业？', {
	   title :'企业状态',
       btn: ['确定','取消'] //按钮
       }, function(){
       layer.msg('该企业已禁用', {icon: 1});
       });
	});
	
	
});
</script>


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
 <!-- nav -->
   
  <!-- nav end --> 

<!-- main -->
  <div class="rightCon">
    <!-- 引导 -->
    <ol class="breadcrumb">
      <li>首页</li>
  
      <li>订单管理</li> 
      <li>订单列表</li>
      <li class="active">订单详情</li>
    </ol>
    <!-- 引导结束 -->

    <h3>订单信息</h3>
    <table border="0" cellspacing="0" cellpadding="0" class="table font12  v-align-top bor-bottom-solid-1" >
      <tr class=" bg-gray-f5">
        <th style="width:20%">行程（单程）</th>
        <th style="width:25%">起飞时间/机场</th>
        <th style="width:25%">到达时间/机场</th>
        <th style="width:15%">航班号</th>
        <th style="width:15%">舱位</th>
      </tr>
      <tr class="">
      <% 
      		OrderVo orderVo=(OrderVo)request.getAttribute("orderVo");
      		
      		Flight flight=orderVo.getOrderitemVos().get(0).getAirticketVo().getFlight();
      		Airticket airticket=orderVo.getOrderitemVos().get(0).getAirticketVo().getAirticket(); 
       %>
      
      
        <td class="font16"><strong class="blue-0093dd"><%=flight.getStartcity() %></strong> <span class="font12">至</span> <strong class="blue-0093dd"><%=flight.getEndcity() %></strong></td>
        <td><p><span><%=flight.getStarttime() %></span>(<%=flight.getStartairport() %>)</p></td>
        <td><p><span><%=flight.getEndtime() %> </span>(<%=flight.getEndairport() %>)</p></td>
        <td>(<%=flight.getAircompany() %>)<%=flight.getAirname() %></td>
        <td><%=airticket.getSeattype() %></td>
      </tr>
    </table>
        <table border="0" cellspacing="0" cellpadding="0" class="table font12  v-align-top bor-bottom-solid-1" >
      <tr class="bg-gray-f5">
        <th>下单时间</th>
        <th>订单编号</th>
        <th>订单状态</th>
        <th class=" bg-gray-f5 text-right">结算总价</th>
        </tr>
      <tr class="">
        <td id="orderTime"><%=orderVo.getOrder().getTime() %></td>
        <td><%=orderVo.getOrder().getTime() %></td>
        <td><%=orderVo.getOrder().getIspaid().equals("1")?"已支付":"未支付" %></td>
        <td align="right" style="color:#ff0253; font-size:14px; font-weight:bold;"><%=orderVo.getOrder().getTotalprice() %>元</td>
      </tr>
    </table>
		
   
    <h3>乘客信息<small class="mar-left-10">联系人：${requestScope.orderVo.order.purchasername }（${requestScope.orderVo.order.purchaserphone }）</small></h3>
    <table border="0" cellspacing="0" cellpadding="0" class="table font12  v-align-top bor-bottom-solid-1" >
      <tr class=" bg-gray-f5">
        <th>乘客姓名</th>
        <th>证件类型</th>
        <th>证件号码</th>
        <th>票面价</th>
        <th>机建燃油</th>
        <th class="text-right">结算价</th>
      </tr>
      
      
      <c:forEach var="item" items="${requestScope.orderVo.orderitemVos }" >
      <tr class="">
        <td>${item.passenger.name }(${item.passenger.type })</td>
        <td>身份证</td>
        <td>${item.passenger.idcard }</td>
        <td>${item.airticketVo.airticket.price }</td>
        <td>50/0</td>
        <td align="right" style="color:#ff0253; font-size:14px; font-weight:bold;">
        <c:choose>
        	<c:when test="${item.passenger.type=='成人' }">
        		${item.airticketVo.airticket.price+30+50 }
        	</c:when>
        	<c:when test="${item.passenger.type=='儿童' }">
        		${item.airticketVo.airticket.price*0.8+30 }
        	</c:when>
        	<c:when test="${item.passenger.type=='婴儿' }">
        		${item.airticketVo.airticket.price*0.5+30 }
        	</c:when>
        </c:choose>
        </td>
      </tr>
      
   </c:forEach>   
      
    </table>
    <div class="text-center"><input type="button" value="返回" class="btn btn-default" onClick="javascript: window.location.href='${pageContext.request.contextPath }/need/OrderController';"></div>
    <div class="clearfix"></div>
    
  </div>
    
   
  </div>
<!-- main end -->

</div>
<!-- 管理模板 结束 -->


<script type="text/javascript"> 
$("#liebiao").click(
		function(){
       $("#biaoge").slideToggle();
 
});
</script>


</body>
</html>