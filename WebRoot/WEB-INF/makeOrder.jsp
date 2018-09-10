<%@page import="com.ecjtu.rwx.vo.FlightTicket"%>
<%@page import="com.ecjtu.rwx.bean.Flight"%>
<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ include file="head.txt"%>
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
<title>商旅系统</title>
<link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path %>/css/common.css" rel="stylesheet">
<link href="<%=path %>/css/corptravel.css" rel="stylesheet">
<link href="<%=path %>/css/enterprise.css" rel="stylesheet">
<!--<link href="js/icheck/skins/all.css" rel="stylesheet" type="text/css">-->
<!-- IconFont图标 -->
<link href="<%=path %>/css/iconfont.css" rel="stylesheet">
<script type="text/javascript" src="<%=path %>/js/jquery-3.3.1.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<!-- 兼容IE8 -->
<!--[if lte IE 9]>
<script type="text/javascript" src="js/html5shiv.min.js"></script>
<script type="text/javascript" src="js/respond.min.js"></script>
<![endif]-->
<!-- layer弹框 2.1 -->
<script type="text/javascript" src="<%=path %>/js/layer/layer.js"></script>
<!-- 日历控件 -->
<script language="javascript" type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body >
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
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class=" " href="${pageContext.request.contextPath}/index.jsp"><img
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
</nav>
<!-- header end -->

<!-- 管理模板 -->
<div class="container bg-gray-eee box-shadow mar-bottom-30" style="padding-right:0px; padding-left:0px; position:relative; margin-top:120px;">
<div class="col-sm-7 container bg-white  pad-bottom-20">
<!-- 航班信息 -->
<div style="overflow:hidden;">
  <h3 class="mar-bottom-20" style=" display:block; float:left;">乘机人</h3>
</div>

<div id="passengers">

 
 </div>
 
  <div class=" mar-top-10">
  <input onclick="checkTicket()" type="button" value="添加乘机人" class="btn btn-danger zw2" > 
</div>
  
<script>
	
	function getNowTime(){
		return (new Date()).getTime();
	}
	
	function showBox(){  //添加乘机人弹框
			personNum=adultnum+babynum+smallbaby;
				if(personNum==5){
					alert("最多只能订五张票");
					return;
				}
     layer.open({
	  type: 1,
	  title: '添加乘机人',
      area: ['400px', 'auto'],
      fix: false, //不固定
      maxmin: false,
      content: $('#rev'),
     });
    }
    
    function checkTicket() {
    var flightId=$("#flightId").val();
    var setTypeStr=$("#setType1").html();
    var setType;
    if(setTypeStr=="商务舱"){
    	setType="secondclassnum";
    }
    if(setTypeStr=="头等舱"){
    	setType="firstclassnum";
    }
    if(setTypeStr=="经济舱"){
    	setType="thirdclassnum";
    }
			$.ajax({
				type:"get",
				url:"${pageContext.request.contextPath}/need/MakeOrder?method=checkTicket&flightId=1&t="+getNowTime(),
				data:{
					"flightId":flightId,
					"setType":setType
				},
				async:true,
				success:function(data){
					if(data=="false"){
						alert("票数不够");
					}else{
						showBox();
					}
				}
			});
	}
	
</script>
  
  
  
<!-- 航班信息 结束 -->

<!-- 保险 -->
<div style="position:relative;">
  <h3 class="mar-bottom-20">保险</h3>
  <a href="" class="mar-right-10 safe">航意险</a>
  <select id="insuranceMoney" name="select" class="form-control input-sm w150 mar-right-10" style=" display: inline-block;">
            <option value="30" selected="selected">￥30/份X1</option>
  </select> 
  <span>保额￥260万/份</span> 
  
<!--航意险说明-->
  <div style="width:460px; border:1px solid #C4C4C4; padding:5px; position:absolute; left:0px; top:75px; background-color:#eee;" class="explain">
  <h5>航意险说明</h5>
  <p>1.保险费：<i class="rmb">¥</i>30/份。保险金额及保险责任：<i class="rmb">¥</i>260万/份。航空意外险由太平财产保险有限公司深圳分公司承保。</p>
  <p>2.保险购买即生效，不可单独退保。机票退款后，自动退保。机票改签后，保险责任保护到改签后航班。提供保险定额发票作为报销凭证。</p>
  </div>
</div>
  
  <!-- 联系人 -->
  <h3 class="mar-bottom-20">联系人</h3>
  <input id="buyMan" name="input" type="text" class="form-control input-sm" style=" display: inline-block; width:460px;" placeholder="姓名">
  <div class="mar-top-10">
  <select name="select" class="form-control input-sm w110 mar-right-10" style=" display: inline-block;">
       <option value="国家" selected="selected">中国&nbsp;&nbsp;+86</option>    
  </select>
  <input id="buyPhone" name="input" type="text" class="form-control input-sm" style=" display: inline-block; width:337px;" placeholder="手机号码，接收航班信息">
  </div> 

<div class="h100"></div>
<!-- 报销 -->
</div>
<div class="col-sm-5 bg-gray-eee">
<div style=" margin-left:-15px;">
<h4 style="overflow:hidden; margin-left:10px;">
<%
	FlightTicket flightTicket=(FlightTicket)session.getAttribute("flightTicket");
	String startT=flightTicket.getFlight().getStarttime();
	Date date=new Date(Long.parseLong(startT));
	int month=date.getMonth()+1;
	int day=date.getDate();
	int hours=date.getHours();
	int minutes=date.getMinutes();
	String endT=flightTicket.getFlight().getEndtime();
	Date date1=new Date(Long.parseLong(endT));
	int hours1=date1.getHours();
	int minutes1=date1.getMinutes();
	String weeks[]={"周日","周一","周二","周三","周四","周五","周六"};
	int index=date.getDay();
	String way=flightTicket.getFlight().getStartcity()+"-"+flightTicket.getFlight().getEndcity();
	String setType=flightTicket.getAirticket().getSeattype();
	String startPort=flightTicket.getFlight().getStartairport();
	String endPort=flightTicket.getFlight().getEndairport();
	String company=flightTicket.getFlight().getAircompany();
	String startDay=(month<10?("0"+month):String.valueOf(month))+"-"+(day<10?("0"+day):String.valueOf(day));
 	String startTime=(hours<10?("0"+hours):String.valueOf(hours))+":"+(minutes<10?("0"+minutes):String.valueOf(minutes));
	String endTime=(hours1<10?("0"+hours1):String.valueOf(hours1))+":"+(minutes1<10?("0"+minutes1):String.valueOf(minutes1));
 	long t=Long.parseLong(endT)-Long.parseLong(startT);
 	long h=t/(1000*60*60);
 	long m=(t-h*1000*60*60)/1000;
 	String allTime=h+"h"+m+"m";
 	int price=flightTicket.getAirticket().getPrice();
 	int ticketId=flightTicket.getAirticket().getId();
 	int flightId=flightTicket.getFlight().getId();
 %>
  <span style="display:block; float:left;"><%=startDay %>&nbsp;&nbsp;<%=weeks[index]%></span>
  <span style="display:block; float:left; margin-left:80px;"><%=way %></span>
  <span id="setType1" style=" display:block; float:right;color:#999; font-size:14px; margin-right:5px;"><%=setType %></span>
</h4>
</div>
<div style=" overflow:hidden; margin-bottom:20px;">
        <div class="flight-from" style="float:left;">
            <span class="time text-center"><%=startTime %></span><br/>
            <span class="text-center"><%=startPort %></span>
        </div>
        <div class="flight-info" style="float:left; margin-left:100px;">
            <img src="<%=basePath %>/img/a.png">
            <span class="font12 gray-999"><%=allTime %></span>
            <img src="<%=basePath %>/img/b.png"><br/>
            <span class="text-center font12 gray-999 mar-left-10"><%=company %></span><br/>
            
            <span class="airline-direct"></span>
            <span class="arrow"></span>
        </div>
        <div class="flight-to" style="float:right;">
            <span class="time text-center"><%=endTime %></span><br/>
            <span class="text-center"><%=endPort %></span>
        </div>
      </div>
<div>
<div class="tuigaiqian mar-top-50" style="cursor:pointer; color:#337ab7;">退改签说明</div>
<div class="instruction">
<table class="table table-bordered" style="height:150px;">
  <tr>
    <td>成人票</td>
    <td>退票扣费</td>
    <td>改期加收手续费</td>
    <td>签转</td>
  </tr>
  <tr>
    <td>起飞前24小时之前</td>
    <td>￥169/人</td>
    <td>￥368/人</td>
    <td>可以签转</td>
  </tr>
  <tr>
    <td>起飞前24小时之后</td>
    <td>￥338/人</td>
    <td>￥368/人</td>
    <td>可以签转</td>
  </tr>
</table>
</div>
</div>

<div style=" margin-left:-15px; overflow:hidden;">
<h4 class="mar-left-10" style="display:block; float:left;">订单总额</h4>
<h4 id="allPrice" class="red" style=" display:block; float:right; font-weight:bold;">￥0</h4>
</div>
<table class="table">
 
</table>
<div class="order-discount">
    </div>
</div>

      
</div>


<div class="text-center mar-top-10">
  <input id="agree" type="checkbox" name="choice">&nbsp;我已阅读并接受免责条款、费用扣除、退保等在内的重要事项，其中包括 <a href="">《网络电子客票协议》</a> <a href="">《航意险说明》</a> <a href="">《延误险说明》</a> <a href="">《保险经纪委托协议》</a><br/>
  <form method="post" action="${pageContext.request.contextPath }/need/MakeOrder">
	  	<input type="hidden" name="method" value="addOrder" />
		  <input type="hidden" name="passengerJson" id="passengerJson"/>
		  <input type="hidden" name="ticketId" id="ticketId" value="<%=ticketId%>"/>
		  <input type="hidden" name="insurance" id="insurance"/>
		  <input type="hidden" name="purchaserName" id="purchaserName"/>
		  <input type="hidden" name="purchaserPhone" id="purchaserPhone"/>
		  <input type="hidden" name="totalPrice" id="totalPrice"/>
		  <input id="submitButton" disabled="disabled" onclick="return dosubmit();"  type="submit" value="提交订单" class="btn btn-danger mar-top-20"> 
	</form>
	<input id="price" value="<%=price %>" type="hidden"  />
	<input id="flightId" value="<%=flightId %>" type="hidden"  />
</div>

<script>
		function dosubmit(){
			if((adultnum+babynum+smallbaby)==0){
				alert("请添加乘机人");
				return false;
			}
			
			var buyman=$("#buyMan").val();
			if(buyman==""){
				$("#buyMan").css("border-color","red");
				return false;
			}else{
				$("#buyMan").css("border-color","lightgray");
			}
			var buyphone=$("#buyPhone").val();
			phoneReg=/^0?1[3|4|5|8][0-9]\d{8}$/;
			if(!phoneReg.test(buyphone)){
					$("#buyPhone").css("border-color","red");
				return false;
			}else{
				$("#buyPhone").css("border-color","lightgray");
			}
			var passengerJson="[";
				var passengers=$("#passengers").children("div");
				for(var i=0;i<passengers.length;i++){
					var name=	passengers.eq(i).find("#pname").eq(0).val();
					var ptype=passengers.eq(i).find("#ptype").eq(0).val();
					var pidcard=passengers.eq(i).find("#pidcard").eq(0).val();
					passengerJson+="{ \"name\":\""+name+"\",\"type\":\""+ptype+"\",\"idcard\":\""+pidcard+"\"},";
				}
				passengerJson=passengerJson.substring(0,passengerJson.lastIndexOf(","))+"]";
				$("#passengerJson").val(passengerJson);
				$("#purchaserName").val($("#buyMan").val());
				$("#purchaserPhone").val($("#buyPhone").val());
				$("#insurance").val($("#insuranceMoney").val());
				$("#totalPrice").val(totalPrice);
				return true;
		}
	
</script>

<!-- 添加乘机人弹框 -->
<div id="rev" style="display:none">
	<div class="modal-body" >
       <div>
     <input id="name" name="input" type="text" class="form-control input-sm w200" style=" display: inline-block;" placeholder="姓名">
     <select id="type" name="select"  class="form-control input-sm w150" style=" display: inline-block;">
            <option value="成人" selected="selected">成人</option>
            <option value="儿童">儿童（2-12岁）</option>
            <option value="婴儿">婴儿（14天-2岁）</option>
     </select>
  </div>
  <div class="mar-top-10">
     <select  name="select" class="form-control input-sm w150" style=" display: inline-block;">
            <option value="身份证" selected="selected">身份证</option>
     </select>
     <input id="idcard" name="input" type="text" class="form-control input-sm w200" style=" display: inline-block;" placeholder="证件号码">
  </div>
    </div>
			
	<div class="modal-footer mar-top-5">
		 <button type="button" class="btn btn-primary" onClick="addPassenger()">确认添加</button>	
         <button type="button" class=" btn btn-default" onClick="closeWin()">取消</button>
	</div>
</div>
<script>

	
		var adultnum=0;
		var babynum=0;
		var smallbaby=0;
		var ticketPrice=$("#price").val();
		var totalPrice=0;
		var cardReg=/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			$(function(){
			
			$("#agree").change(function(){
					if($(this).is(":checked")){
						$("#submitButton").attr("disabled",false);
					}else{
						$("#submitButton").attr("disabled",true);
					}
				});
			
			$("#name").blur(function(){
				if($("#name").val()==""){
					$(this).css("border-color","red");
					return;
				}else{
					$(this).css("border-color","lightgray");  	
				}
			});
			$("#idcard").blur(function(){
				if(!cardReg.test($("#idcard").val())){
					$(this).css("border-color","red");
					return;
				}else{
					$(this).css("border-color","lightgray");  	
				}
			});
		});
		function addPassenger(){
			
				var name=$("#name").val();
				if(name==""){
					$("#name").css("border-color","red");
					return;
				}else{
					$("#name").css("border-color","lightgray");  	
				}
				var idcard=$("#idcard").val();
				if(!cardReg.test(idcard)){
					$("#idcard").css("border-color","red");
					return;
				}else{
					$("#idcard").css("border-color","lightgray");  	
				}
				
				var type=$("#type").val();
				
				
				var str="<div class='mar-top-10'><div><input readonly='readonly' id='pname' name='input' value='"+name+"' type='text' class='form-control input-sm w300' style=' display: inline-block;' placeholder='姓名，与登机所持证件中的姓名一致'>"+
     "<select disabled='disabled' name='select' id='ptype' class='form-control input-sm w150' style=' display: inline-block;'>"+
            "<option value='成人' "+(type=="成人"?"selected='selected'":"")+">成人</option>"+
            "<option value='儿童'"+(type=="儿童"?"selected='selected'":"")+">儿童（2-12岁）</option>"+
            "<option value='婴儿'"+(type=="婴儿"?"selected='selected'":"")+">婴儿（14天-2岁）</option>"+
     "</select><button onclick='deletePassenger(this)' class='btn btn-info'>删除</button>"+
  "</div>"+
  "<div class='mar-top-10'>"+
     "<select disabled='disabled' name='select' class='form-control input-sm w150' style=' display: inline-block;'>"+
            "<option value='身份证' selected='selected'>身份证</option>"+
     "</select>"+
     "<input id='pidcard' name='input' readonly='readonly' value='"+idcard+"' type='text' class='form-control input-sm w300' style=' display: inline-block;' placeholder='证件号码'>"+
  "</div></div>";
  	if(type=="婴儿"){
  			smallbaby++;
  	}
  	if(type=="成人"){
  		adultnum++;
  	}
  	if(type=="儿童"){
  		babynum++;
  	}
        $("#passengers").append(str);
        closeWin();
        changeTotalPrice();
		}
		
		function deletePassenger(t){
			var con=confirm("是否删除？");
			if(con==false)
				return;
			var type=$(t).parent("div").children("select").val();
			if(type=="婴儿"){
  			smallbaby--;
  	}
  	if(type=="成人"){
  		adultnum--;
  	}
  	if(type=="儿童"){
  		babynum--;
  	}
			$(t).parent("div").parent("div").remove();
			changeTotalPrice();
		}
		
	function closeWin(){
			$("#name").val("");
			$("#type").val("");
				$("#idcard").val("");
			 layer.closeAll();
	}
	
	
	function changeTotalPrice(){
			var priceTable=$(".table");
			priceTable.empty();
			if(adultnum!=0){
				var str="<tr><td>成人票</td><td>￥"+ticketPrice+"/人</td><td>x"+adultnum+"</td></tr>";
				priceTable.append(str);
			}
			if(babynum!=0){
				var str="<tr><td>儿童票</td><td>￥"+ticketPrice*0.8+"/人</td><td>x"+babynum+"</td></tr>"
				priceTable.append(str);
			}
			if(smallbaby!=0){
				var str="<tr><td>婴儿票</td><td>￥"+ticketPrice*0.5+"/人</td><td>x"+smallbaby+"</td></tr>"
				priceTable.append(str);
			}
			if((adultnum+babynum+smallbaby)!=0){
			var str="<tr><td>机建费</td><td>￥"+50+"/成人</td><td>x"+adultnum+"</td></tr>"+
								"<tr><td>航意险</td><td>￥"+30+"/人</td><td>x"+(adultnum+babynum+smallbaby)+"</td></tr>";
			priceTable.append(str);
			}
			var allPrice=(adultnum*ticketPrice)+(babynum*ticketPrice*0.8)+(smallbaby*ticketPrice*0.5)+(50*adultnum)+(30*(adultnum+babynum+smallbaby));
			totalPrice=allPrice;
			$("#allPrice").html("￥"+allPrice);
	}
</script>



<script type="text/javascript">

    $(function() {
        $('.bubbleinfo').each(function() {
            var distance = 10;
            var time = 10;
            var hideDelay = 100;

            var hideDelayTimer = null;

            var beingShown = false;
            var shown = false;
            var trigger = $('.trigger', this);
            var info = $('.popup', this).css('opacity', 0);


            $([trigger.get(0), info.get(0)]).mouseover(function() {
                if (hideDelayTimer) clearTimeout(hideDelayTimer);
                if (beingShown || shown) {
                    // don't trigger the animation again
                    return;
                } else {
                    // reset position of info box
                    beingShown = true;

                    info.css({
                        top: 30,
                        left:0,
                        display: 'block'
                    }).animate({
                        top: '-=' + distance + 'px',
                        opacity: 1
                    }, time, 'swing', function() {
                        beingShown = false;
                        shown = true;
                    });
                }

                return false;
            }).mouseout(function() {
                if (hideDelayTimer) clearTimeout(hideDelayTimer);
                hideDelayTimer = setTimeout(function() {
                    hideDelayTimer = null;
                    info.animate({
                        top: '-=' + distance + 'px',
                        opacity: 0
                    }, time, 'swing', function() {
                        shown = false;
                        info.css('display', 'none');
                    });

                }, hideDelay);

                return false;
            });
        });
    });
    
  
</script>


    
    
    

 
<script>
$('.explain').hide();
$('.safe').mouseenter(
		function(){
			$('.explain').show();
			}
).mouseleave(
		function(){
			$('.explain').hide();
			}
)

$('.instruction').hide();
$('.tuigaiqian').click(
		function(){
			$('.instruction').toggle();
			}
)
</script>




</body>
</html>
