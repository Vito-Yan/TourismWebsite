<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=path%>/admin/css/public.css" />
<script type="text/javascript" src="<%=path%>/admin/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/admin/js/public.js"></script>
<title>管理页面</title>
<script language=JavaScript>
function logout(){
	if (confirm("您确定要退出控制面板吗？"))
	top.location ="<%=path%>/AdminManagerServlet?flag=_logout";
	return false;
}
</script>
</head>

<body>
	<!-- 头部 -->
	<div class="head">
		<div class="headL">
			<img class="headLogo" src="<%=path%>/admin/img/headLogo.png" />
		</div>
		<div class="headR">
			<p class="p1">
				欢迎，
				<%=session.getAttribute("account")%>
			</p>
			<p class="p2">
				<a href="#" class="resetPWD">网站首页</a>&nbsp;&nbsp;<a
					href="#" target="_self" onClick="logout();">退出</a>
			</p>
		</div>
	</div>
</body>
</html>