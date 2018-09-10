<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<link href="${pageContext.request.contextPath }/css/sys.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
$(function(){
//城市
	var startcity=$("#dropstartcity");
	var endcity=$("#dropendcity");
	var scity=$("#startcity").val();
	var ecity=$("#endcity").val();
	$.ajax({
	url:"${pageContext.request.contextPath }/booking?method=findstartcity",
	data:null,
	dataType:"json",
	type:"post",
	success:function(data){
	startcity.empty();
	endcity.empty();
	startcity.append("<option value=''>请选择</option>")
	endcity.append("<option value=''>请选择</option>");
	for(var i in data){
	var option="<option value='"+data[i].name+"'>"+data[i].name+"</option>"
	startcity.append(option);
	endcity.append(option);
	}
	$("#dropstartcity option").each(function(){
	var val=$(this).val();
	if(val==scity){
	$(this).attr("selected", true);
	}
	})
	$("#dropendcity option").each(function(){
	var val=$(this).val();
	if(val==ecity){
	$(this).attr("selected", true);
	}
	})
	}});
	
	//机场
	var startairport=$("#dropstartairport");
	var endairport=$("#dropendairport");
	$.ajax({
	url:"${pageContext.request.contextPath }/admin/flight?method=findAllAirport",
	data:null,
	dataType:"json",
	type:"post",
	success:function(data){
	startairport.empty();
	endairport.empty();
	startairport.append("<option value=''>请选择机场</option>")
	endairport.append("<option value=''>请选择机场</option>");
	for(var i in data){
	var option="<option value='"+data[i].name+"'>"+data[i].name+"</option>"
	startairport.append(option);
	endairport.append(option);
	}
	var sairport=$("#startairport").val();
	var eairport=$("#endairport").val();
	$("#dropstartairport option").each(function(){
	var val=$(this).val();
	if(val==sairport){
	$(this).attr("selected", true);
	}
	})
	$("#dropendairport option").each(function(){
	var val=$(this).val();
	if(val==eairport){
	$(this).attr("selected", true);
	}
	})
	}});
	
	//航空公司
	var dropaircompany=$("#dropaircompany");
	$.ajax({
	url:'${pageContext.request.contextPath }/admin/flight?method=findAllCompany',
	dataType:"json",
	data:null,
	type:"post",
	success:function(data){
	dropaircompany.empty();
	dropaircompany.append("<option value=''>请选择航空公司</option>")
	for(var i in data){
	var option="<option value='"+data[i].name+"'>"+data[i].name+"</option>";
	dropaircompany.append(option);
	}
	var aircompany=$("#aircompany").val();
	$("#dropaircompany option").each(function() {
		var val=$(this).val();
		if(val==aircompany){
		$(this).attr("selected",true);
		}
	})
	}
	})
	
}
);
</script>
</head>

<body class="emp_body">
 <table border="0" cellspacing="0" cellpadding="0" width="100%">
  <tr>
    <td class="topg"></td>
  </tr>
</table>

<table border="0" cellspacing="0" cellpadding="0"  class="wukuang"width="100%">
  <tr>
    <td width="1%"><img src="${pageContext.request.contextPath }/images/tleft.gif"/></td>
    <td width="44%" align="left">[添加或修改航班]</td>
   
    <td width="52%"align="right">
    	<!--<a href="listLog.html"><img src="${pageContext.request.contextPath }/images/button/find.gif" class="img"/></a>
        <a href="addLog.html"><img src="${pageContext.request.contextPath }/images/button/add.gif" class="img"/></a>~-->
        
       <!-- <a href="#"><img src="${pageContext.request.contextPath }/images/button/close.gif" class="img"/></a>-->
       <a href="javascript:void(0)" onclick="document.forms[0].submit()"><img src="${pageContext.request.contextPath }/images/button/save.gif" /></a>
       <a href="javascript:history.back(-1)"><img src="${pageContext.request.contextPath }/images/button/tuihui.gif" /></a>
    </td>
    <td width="3%" align="right"><img src="${pageContext.request.contextPath }/images/tright.gif"/></td>
  </tr>
</table>
<form action="${pageContext.request.contextPath }/admin/flight?method=addOrupdateFlight" method="post">
<input name="id" type="hidden" value="${flight.id }" />
<span style="color=red">${msg }</span>
<table width="88%" class="emp_table" style="size: 20px">
  <tr>
    <td width="80px" align="left">航空公司：<input id="aircompany" type="hidden" value="${flight.aircompany }"/></td>
    <td width="200px" align="left"><select name="aircompany" id="dropaircompany">
    <option value="">请选择</option>
    </select></td>
   </tr>
  <tr>
    <td align="left">起始城市:<input id="startcity" type="hidden" value="${flight.startcity }"/></td>
    <td align="left"><select name="startcity" id="dropstartcity">
    </select></td>
    <td align="left">起始机场:<input id="startairport" type="hidden" value="${flight.startairport }"/></td>
    <td align="left"><select name="startairport" id="dropstartairport">
    </select></td>
     <td align="left">出发时间:</td>
    <td align="left"><input type="date" name="starttime" value="${flight.starttime }"/>
    &nbsp;&nbsp;&nbsp;<select name="starthour">
    <c:forEach var="starthour" begin="0" end="23">
    <c:choose>
    <c:when test="${starthour==startHour }">
     <option value="${starthour }" selected="selected">${starthour }时</option>
     </c:when>
     <c:otherwise>
      <option value="${starthour }">${starthour }时</option>
     </c:otherwise>
    </c:choose>
    </c:forEach>
    </select>
     &nbsp;&nbsp;&nbsp;<select name="satrtmin">
    <c:forEach var="startmin" begin="0" end="59">
    <c:choose>
    <c:when test="${startmin==startMin }">
     <option value="${startmin }" selected="selected">${startmin }分</option>
     </c:when>
     <c:otherwise>
      <option value="${startmin }" >${startmin }分</option>
     </c:otherwise>
     </c:choose>
    </c:forEach>
    </select>
    </td>
  </tr>
  <tr>
    <td align="left">到达城市:<input id="endcity" type="hidden" value="${flight.endcity }"/></td>
    <td align="left"><select name="endcity" id="dropendcity">
    </select></td>
    <td align="left">到达机场:<input id="endairport" type="hidden" value="${flight.endairport }"/></td>
    <td align="left"><select name="endairport" id="dropendairport">
    </select></td>
     <td align="left">到达时间:</td>
    <td align="left"><input type="date" name="endtime" value="${flight.endtime }"/>
    &nbsp;&nbsp;&nbsp;<select name="endhour">
    <c:forEach var="endhour" begin="0" end="23">
    <c:choose>
    <c:when test="${endhour==endHour }">
     <option value="${endhour }" selected="selected">${endhour }分</option>
     </c:when>
     <c:otherwise>
      <option value="${endhour }" >${endhour }时</option>
     </c:otherwise>
     </c:choose>
    </c:forEach>
    </select>
     &nbsp;&nbsp;&nbsp;<select name="endmin">
    <c:forEach var="endmin" begin="0" end="59">
    <c:choose>
    <c:when test="${endmin==endMin }">
    <option value="${endmin }" selected="selected">${endmin }分</option>
    </c:when>
    </c:choose>
     <option value="${endmin }" >${endmin }分</option>
    </c:forEach>
    </select>
    </td>
  </tr>
  <tr>
    <td align="left">飞机类型：</td>
    <td align="left"><input type="text" name="airtype" value="${flight.airtype }"/></td>
    <td align="left">飞机名称：</td>
    <td align="left"><input type="text" name="airname" value="${flight.airname }"/></td>
  </tr>
  
  <tr>
    <td align="left">头等舱数量:</td>
    <td align="left"><input type="text" name="firstclassnum" value="${flight.firstclassnum }"/></td>
    <td align="left">商务舱数量:</td>
    <td align="left"><input type="text" name="secondclassnum" value="${flight.secondclassnum }" /></td>
    <td align="left">经济舱数量:</td>
    <td align="left"><input type="text" name="thirdclassnum" value="${flight.thirdclassnum }"/></td>
  </tr>
  <tr>
    <td align="left">头等舱价格:</td>
    <td align="left"><input type="text" name="firstprice" value="${flight.firstprice }"/></td>
    <td align="left">商务舱价格:</td>
    <td align="left"><input type="text" name="secondprice" value="${flight.secondprice }" /></td>
    <td align="left">经济舱价格:</td>
    <td align="left"><input type="text" name="thirdprice" value="${flight.thirdprice }"/></td>
  </tr>
  </table>
</form>
</body>
</html>
