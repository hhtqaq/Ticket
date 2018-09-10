<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD Xhtml 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>机场后台管理系统</title>
</head>
<frameset rows="100,*" framespacing="0px" frameborder="no">
	<frame src="${pageContext.request.contextPath}/admin/frame?method=top" scrolling="no"/>
    <frameset id="main" cols="170,9,*" framespacing="0px" frameborder="no" >
        <frameset rows="30,*,40" framespacing="0px" frameborder="no" >
			<frame src="${pageContext.request.contextPath}/admin/frame?method=left1" scrolling="no"/>
            <frame src="${pageContext.request.contextPath}/admin/frame?method=left" scrolling="yes"/>
            <frame src="${pageContext.request.contextPath}/admin/frame?method=left2" scrolling="no"/>
        </frameset>
        <frame src="${pageContext.request.contextPath}/admin/frame?method=control" scrolling="no"/>
        <frame src="${pageContext.request.contextPath}/admin/frame?method=right" name="right" scrolling="no"/>
	</frameset>
</frameset>
</html>
